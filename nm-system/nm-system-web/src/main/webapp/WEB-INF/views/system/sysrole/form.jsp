<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色维护</title>
    <style type="text/css">
        .otod-system-sysrole-form {}
    </style>
    <script type="text/javascript">
        $(function () { app.init();});

        app.init('otod.system.sysrole.form', {
            vars: {
                formValidatorVar: false,
                itemVar: false
            },
            init: function () {
                var that = this;
                this.global = function () { return that;};

                app.code('availableStat');

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
                        cssCol: 'col-xs-12',
                        cssLabel: 'col-xs-2',
                        cssInputGroup: 'col-xs-10'
                    });
                    $('input[name=roleStat]', that.selector).selectGroup({
                        cssCol: 'col-xs-12',
                        cssLabel: 'col-xs-2',
                        cssInputGroup: 'col-xs-10',
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
                            roleNo: {
                                required: true, rangelength: [3, 20]
                            },
                            roleName: {
                                required: true, isChinese: true
                            },
                            roleStat: {
                                required: true
                            },
                            remark: {
                                required: false
                            }
                        }
                    });
                },
                setData: function () {
                    var that = this.global();
                    var item = that.vars.itemVar;

                    if (item){
	                    $('input[name=id]', that.selector).valChange(item.id);
	                    $('input[name=roleNo]', that.selector).valChange(item.roleNo);
	                    $('input[name=roleName]', that.selector).valChange(item.roleName);
	                    $('input[name=roleStat]', that.selector).valChange(item.roleStat);
	                    $('textarea[name=remark]', that.selector).valChange(item.remark);
	                    if (item['id'] ) {
	                    	$('input[name=roleNo]', that.selector).readOnly(true);
	                    };
                    }

                },
                handlers: {
                    init: function (that) {
                        this.global = function () { return that; };
                    },
                    save: function () {
                        var that = this.global();
                        var $validator = that.vars.formValidatorVar;

                        if (!$validator.form()) return false;

                        var $saveBtn = $('#saveBtn', that.selector);
                        $saveBtn.disabled(true);

                        var item = $($validator.currentForm).serialize();

                        $.ajax({
                            url: 'role/save.json',
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
                            error: function(data) {
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
    <div class="container-fluid otod-system-sysrole-form">
        <form class="form-horizontal">
            <input type="hidden" name="id"/>
            <div class="row">
                <input type="text" name="roleNo" class="inputGroup" options="角色编码">
                <input type="text" name="roleName" class="inputGroup" options="角色名称">
                <input type="text" name="roleStat" class="selectGroup" options="角色状态">
                <textarea name="remark" class="inputGroup" options="角色描述"></textarea>
            </div>
        </form>
        <div class="form-tools text-right">
            <button class="btn btn-primary btn-md" type="button" id="saveBtn"><i class="fa fa-save"></i> 保存</button>
            <button class="btn btn-danger btn-md" type="button" id="closeBtn"><i class="fa fa-remove"></i> 取消</button>
        </div>
    </div>
</body>
</html>
