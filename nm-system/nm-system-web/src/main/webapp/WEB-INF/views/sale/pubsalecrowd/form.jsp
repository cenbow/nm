<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>码类维护</title>
    <style type="text/css">
        .otod-system-sale-pubsalecrowd-form .form-horizontal{margin: 20px 20px;}
    </style>
    <script type="text/javascript">
        $(function (){ app.init(); });

        app.init('otod.system.sale.pubsalecrowd.form', {
            vars: {
                itemVar: false,
                formValidatorVar: false,
                isCheck : false
            },
            init: function () {
                var that = this;

                this.global = function () { return that; };

                if (typeof that.modal != 'undefined') that.vars.itemVar = that.modal.param['item'];

                this.layout();

                this.form.init(that);
            },
            layout: function () {
                var that = this.global();
            },
            form: {
                init: function (that) {
                    this.global = function () { return that; };
                    this.layout();
                    this.validator();
                    this.setData();
                    this.handlers.init(that);
                },
                layout: function () {
                    var that = this.global();

                    $('.inputGroup', that.selector).inputGroup({
                        cssCol: 'col-xs-12',
                        cssLabel: 'col-xs-2',
                        cssInputGroup: 'col-xs-9'
                    });
                    
                    $(".inputButtonGroup", that.selector).inputButtonGroup({
                    	cssCol: 'col-xs-12',
                        cssLabel: 'col-xs-2',
                        cssInputGroup: 'col-xs-9',
                        readOnly : false,
                        txtSuffix : '校验',
                        events: {
                            btnClick: {
                                handler: function (event) {
                                    that.form.handlers.check();
                                }
                            },
                        }
                    });


                    $('.saveBtn', that.selector).bind('click', that, function (event) {
                        var that = event.data.refresh();
                        that.form.handlers.save();
                    });

                    $('.cancelBtn', that.selector).bind('click', that, function (event) {
                        appui.modal.close({closed: false});
                    });

                },
                validator: function () {
                    var that = this.global();
                    that.vars.formValidatorVar = $('form', that.selector).validate({
                        rules: {
                            crowdName: { required: true, rangelength:[1,40] },
                            crowdRule: { required: true},
                            crowdDesc: { rangelength: [0, 300]}
                        }
                    });
                },
                setData: function () {
                    var that = this.global();
                    var item = that.vars.itemVar;
                    if (item) {
                        $('input[name=crowdNo]', that.selector).valChange(item['crowdNo']);
                        $('input[name=crowdName]', that.selector).valChange(item['crowdName']);
                        $('textarea[name=crowdRule]', that.selector).valChange(item['crowdRule']);
                        if (item['crowdRule'] != null) that.vars.isCheck = true;
                        $('textarea[name=crowdDesc]', that.selector).valChange(item['crowdDesc']);
                    }
                },
                handlers: {
                    init: function (that) {
                        this.global = function () { return that; };
                    },
                    save: function () {
                        var that = this.global();
                        var $validator = that.vars.formValidatorVar;
                        var $this = $('.saveBtn', that.selector);

                        if (!$validator.form()) return false;
                        if (!that.vars.isCheck) return appui.message.info("请校验规则") || false;

                        $this.disabled(true);

                        var $form = $($validator.currentForm);
                        var data = $form.serialize();

                        $.ajax({
                            url: 'sale/crowd/save.json',
                            type: 'POST',
                            data: data,
                            success: function (data) {
                                $this.disabled(false);

                                if (!data.success){
                                    appui.message.error(data.msg);
                                    return;
                                }

                                appui.modal.close(data);
                            },
                            error: function(data) {
                            	$this.disabled(false);
                            	appui.message.error(data.msg);
                            }
                        });

                    },
                    check : function(){
                    	var that = this.global();
                    	var rule = $("textarea[name=crowdRule]", that.selector).val();
                    	var data = {rule : rule};
                    	if (rule == null || rule == '') return appui.message.info("请输入规则") || false;
                    	$.ajax({
                    		url: 'sale/crowd/check.json',
                            type: 'POST',
                            data: data,
                            success: function (data) {
                            	if (data.t){
                            		appui.message.success("规则校验通过");
                            		that.vars.isCheck = true;
                            	}else{
                            		appui.message.error("规则校验不通过");
                            		that.vars.isCheck = false;
                            	}
                            	
                            	if (!data.success){
                                    appui.message.error(data.msg);
                                    return;
                                }
                            }
                    	});
                    }
                }
            }
        });
    </script>
</head>
<body>
    <div class="container otod-system-sale-pubsalecrowd-form">
        <form class="form-horizontal">
            <input type="hidden" name="crowdNo" />
            <div class="row">
                <input class="inputGroup" type="text" name="crowdName" options="群名称" />
                <textarea class="inputButtonGroup" type="text" name="crowdRule" options="群规则" />
                <textarea class="inputGroup" type="text" name="crowdDesc" options="群说明" />
            </div>
            <div class="row">
                <div class="pull-right">
                    <button type="button" class="btn btn-primary btn-md saveBtn"><i class="fa fa-save"></i> 提交</button>
                    <button type="button" class="btn btn-danger btn-md cancelBtn"><i class="fa fa-remove"></i> 取消</button>
                </div>
            </div>
        </form>

    </div>

</body>
</html>
