<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色设置</title>
    <style type="text/css">
        .otod-sysytem-sysstaff-form {}
    </style>
    <script type="text/javascript">
        $(function () { app.init();});

        app.init('otod.system.sysstaff.form', {
            vars: {
                formValidatorVar: false,
                itemVar: false
            },
            init: function () {
                var that = this;
                this.global = function () { return that;};

                app.code('availableStat, staffType');

                this.layout();
                this.form.init(that);
            },
            layout: function () {
                var that = this.global();

                if (typeof that.modal != 'undefined') that.vars.itemVar = that.modal.param.item;
            },
            form: {
                init: function (that) {
                    this.global = function () { return that; };

                    this.layout();
                    this.setData();
                    this.validator();

                    this.handlers.init(that);
                },
                layout: function () {
                    var that = this.global();

                    $('.inputGroup', that.selector).inputGroup({
                        cssCol: 'col-xs-6',
                        cssLabel: 'col-xs-4',
                        cssInputGroup: 'col-xs-8'
                    });
                    $('input[name=userTyp]', that.selector).selectGroup({
                        cssCol: 'col-xs-6',
                        cssLabel: 'col-xs-4',
                        cssInputGroup: 'col-xs-8',
                        code: {
                            type: 'staffType'
                        }
                    });
                    $('input[name=staffStat]', that.selector).selectGroup({
                        cssCol: 'col-xs-6',
                        cssLabel: 'col-xs-4',
                        cssInputGroup: 'col-xs-8',
                        code: {
                            type: 'availableStat'
                        }
                    });


                    $('#saveBtn', that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();

                        that.form.handlers.save();

                        return false;
                    });

                    $('#closeBtn', that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();

                        var that = event.data.refresh();
                        appui.modal.close({closed: false});
                        return false;
                    });
                },
                validator: function () {
                    var that = this.global();

                    that.vars.formValidatorVar = $('form', that.selector).validate({
                        onkeyup: false,
                        rules: {
                            loginNo: {required: true, rangelength: [3, 20] },
                            staffNo: {required: true, rangelength: [3, 20] },
                            staffName: {required: true, isChinese: true },
                            userTyp: {required: true},
                            staffStat: {required: true},
                            staffPosition: {required: false },
                            moblNo: {required: false, isMobile: true},
                            email: {required: false, email: true},
                            loginPwd:{required: true,minlength:6}
                        }
                    });
                },
                setData: function () {
                    var that = this.global();
                    var item = that.vars.itemVar;

                    if (item == false) return false;

                    $('input[name=id]', that.selector).valChange(item.id);
                    $('input[name=loginNo]', that.selector).valChange(item.loginNo);
                    $('input[name=loginPwd]', that.selector).valChange(item.loginPwd);
                    $('input[name=staffNo]', that.selector).valChange(item.staffNo);
                    $('input[name=staffName]', that.selector).valChange(item.staffName);
                    $('input[name=userTyp]', that.selector).valChange(item.userTyp);
                    $('input[name=staffStat]', that.selector).valChange(item.staffStat);
                    $('input[name=staffPosition]', that.selector).valChange(item.staffPosition);
                    $('input[name=moblNo]', that.selector).valChange(item.moblNo);
                    $('input[name=email]', that.selector).valChange(item.email);
                    $('input[name=openId]', that.selector).valChange(item.openId);
                    $('input[name=staffAutName]', that.selector).valChange(item.staffAutName);
                    if (item.loginPwd) $('input[name=loginPwd]', that.selector).disabled(true);
                },
                handlers: {
                    init: function (that) {
                        this.global = function () { return that; };
                    },
                    save: function () {
                        var that = this.global();
                        var $validator = that.vars.formValidatorVar;
                       
                        if (!$validator.form()) return false;
                       
                        if(that.vars.itemVar == false||$.isEmptyObject(that.vars.itemVar)){/*新增时初始密码必须为数字加密码组合*/
                        	
                        	 var now=$('input[name=loginPwd]', that.selector).val();
                            var re = new RegExp("[a-zA-Z]");
                            var len=re.test(now);
                            if(!len){
                            	 appui.message.error('密码必须为数字加字母组合');
                                 return false;
                            }
                            re = new RegExp("[0-9]");
                            len=re.test(now);
                            if(!len){
                           	 appui.message.error('密码必须为数字加字母组合');
                                return false;
                           }
                           
                        }
                        
                 
                        var $saveBtn = $('#saveBtn', that.selector);
                        $saveBtn.disabled(true);

                        var item = $($validator.currentForm).serialize();

                        $.ajax({
                            url: 'staff/save.json',
                            type: 'POST',
                            data: item,
                            success: function (data) {
                                $saveBtn.disabled(false);
                                if (!data.success) {
                                    appui.message.error('操作失败.');
                                    return false;
                                }
                                appui.modal.close(data);
                            },
                            error: function(data){
                            	$saveBtn.disabled(false);
                            	appui.message.error('操作失败.');
                            }
                        });


                    }
                }
            }
        })
    </script>
</head>
<body>
    <div class="container-fluid otod-system-sysstaff-form">
        <form class="form-horizontal">
            <input type="hidden" name="id"/>
            <div class="page-header"><i class="fa fa-list-alt"></i> 用户信息</div>
            <input type="text" class="inputGroup" name="loginNo" options="登陆账号"/>
            <input type="password" class="inputGroup" name="loginPwd" options="初始密码" value="" title="密码为大于6位数字加字母组合"/>

            <div class="page-header"><i class="fa fa-list-alt"></i> 员工信息</div>
            <input type="text" class="inputGroup" name="staffNo" options="工号"/>
            <input type="text" class="inputGroup" name="staffName" options="员工姓名"/>
            <input type="text" class="selectGroup" name="userTyp" options="员工类型"/>
            <input type="text" class="selectGroup" name="staffStat" options="员工状态"/>

            <div class="page-header"><i class="fa fa-list-alt"></i> 扩展信息</div>
            <input type="text" class="inputGroup" name="staffPosition" options="员工职位"/>
            
            <input type="text" class="inputGroup" name="staffAutName" options="分机号(认证名)"/>
            <input type="text" class="inputGroup" name="staffAutPwd" options="认证密码"/>
            
            <input type="text" class="inputGroup" name="moblNo" options="手机号码"/>
            <input type="text" class="inputGroup" name="email" options="电子邮箱"/>
            <input type="text" class="inputGroup" name="openId" options="微信号"/>

        </form>

        <div class="form-tools text-right">
            <button class="btn btn-primary btn-md" type="button" id="saveBtn"><i class="fa fa-save"></i> 保存</button>
            <button class="btn btn-danger btn-md" type="button" id="closeBtn"><i class="fa fa-remove"></i> 取消</button>
        </div>
    </div>
</body>
</html>
