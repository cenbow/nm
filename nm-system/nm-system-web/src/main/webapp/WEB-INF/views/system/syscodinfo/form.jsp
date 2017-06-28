<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>码类维护</title>
    <style type="text/css">
        .otod-system-syscodinfo-form .form-horizontal{margin: 20px 20px;}
    </style>
    <script type="text/javascript">
        $(function (){ app.init(); });

        app.init('otod.system.syscodinfo.form', {
            vars: {
                itemVar: false,
                formValidatorVar: false
            },
            init: function () {
                var that = this;

                this.global = function () { return that; };

                app.code([{type: 'availableStat'}]);

                if (typeof that.modal != 'undefined') that.vars.itemVar = that.modal.param['item'];

                this.layout();

                this.form.init(that);
            },
            layout: function () {
                var that = this.global();

                var item = that.vars.itemVar;

                if (item) {
                    $('input[name=id]', that.selector).valChange(item['id']);
                    $('input[name=codTyp]', that.selector).valChange(item['codTyp']);
                    $('input[name=codName]', that.selector).valChange(item['codName']);
                    $('input[name=codVal]', that.selector).valChange(item['codVal']);
                    $('input[name=stat]', that.selector).valChange(item['stat']);


                    if (item['id']) $('input[name=codVal]', that.selector).readOnly(true);
                }

            },
            form: {
                init: function (that) {
                    this.global = function () { return that; };

                    this.layout();
                    this.validator();

                    this.handlers.init(that);
                },
                layout: function () {
                    var that = this.global();

                    $('.inputGroup', that.selector).inputGroup({
                        cssCol: 'col-xs-12',
                        cssLabel: 'col-xs-2',
                        cssInputGroup: 'col-xs-9'
                    });

                    $('input[name=stat]', that.selector).selectGroup({
                        cssCol: 'col-xs-12',
                        cssLabel: 'col-xs-2',
                        cssInputGroup: 'col-xs-9',
                        code: {
                            type: 'availableStat',
                            enable: {
                                status: false
                            }
                        }
                    });


                    $('.saveBtn', that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();
                        that.form.handlers.save();
                        return false;
                    });

                    $('.cancelBtn', that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        appui.modal.close({closed: false});
                        return false;
                    });

                },
                validator: function () {
                    var that = this.global();
                    that.vars.formValidatorVar = $('form', that.selector).validate({
                        rules: {
                            codName: { 
                            	required: true,
                            	rangelength:[1,40] 
                            },
                            codVal: { 
                            	required: true,
                            	isNumber: true,
                            	rangelength:[1,20]
                            },
                            stat: { required: true }
                        }
                    });
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

                        $this.disabled(true);

                        var $form = $($validator.currentForm);
                        var data = $form.serialize();

                        $.ajax({
                            url: 'code/info/save.json',
                            type: 'POST',
                            data: data,
                            success: function (data) {
                            	var data = app.data(data);
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

                    }
                }
            }
        });
    </script>
</head>
<body>
    <div class="container otod-system-syscodinfo-form">
        <form class="form-horizontal">
            <input type="hidden" name="id" />
            <input type="hidden" name="codTyp" />
            <div class="row">
                <input class="inputGroup" type="text" name="codName" options="码值名称" />
                <input class="inputGroup" type="text" name="codVal" options="码值编码" />
                <input class="selectGroup" type="text" name="stat" options="码值状态" />
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
