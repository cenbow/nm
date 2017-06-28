<%--
  User: Mifeng.He(bee)
  Date: 2015/10/24
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>业务参数维护</title>
    <style type="text/css">
        .otod-system-sysparam-form {}
    </style>
    <script type="text/javascript">
        $(function (){ app.init(); });

        app.init('otod.system.sysparam.form', {
            vars: {
                itemVar: false,
                formValidatorVar: false
            },
            init: function () {
                var that = this;

                this.global = function () { return that; };

                app.code('availableStat');

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
                            cod: { required: true,rangelength:[1,20]},
                            name: { required: true,rangelength:[1,20]},
                            val: { required: true,rangelength:[1,20],},
                            stat: { required: true }
                        }
                    });
                },
                setData: function () {
                    var that = this.global();
                    var item = that.vars.itemVar;
                    if (item) {
                        $('input[name=id]', that.selector).valChange(item['id']);
                        $('input[name=cod]', that.selector).valChange(item['cod']);
                        $('input[name=name]', that.selector).valChange(item['name']);
                        $('input[name=val]', that.selector).valChange(item['val']);
                        $('input[name=stat]', that.selector).valChange(item['stat']);

                        if (item['id']) $('input[name=cod]', that.selector).readOnly(true);
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

                        $this.disabled(true);

                        var $form = $($validator.currentForm);
                        var data = $form.serialize();

                        $.ajax({
                            url: 'param/save.json',
                            type: 'POST',
                            data: data,
                            success: function (data) {
                            	data = app.data(data);
                                $this.disabled(false);
                                if (!data.success){
                                    appui.message.error(data.msg);
                                    return;
                                }else{
                                	appui.message.success(data.msg);
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
<div class="container otod-system-sysparam-form">
    <form class="form-horizontal">
        <input type="hidden" name="id" />
        <div class="row">
            <input class="inputGroup" type="text" name="cod" options="编码" />
            <input class="inputGroup" type="text" name="name" options="参数名" />
            <input class="inputGroup" type="text" name="val" options="参数值" />
            <input type="text" class="selectGroup " name="stat" options="参数状态"/>
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
