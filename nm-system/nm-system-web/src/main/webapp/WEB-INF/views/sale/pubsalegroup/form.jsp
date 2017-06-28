<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>销售列表</title>
	<style type="text/css">
		.otod-system-sale-pubsalergroup-form {}
	</style>
	<script type="text/javascript">
		$(function(){ 
			app.init();
		});
		
		app.init('otod.system.sale.pubsalergroup.form',{
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
//                         	groupNo: { required: true },
                        	groupName: { required: true },
                        	stat: { required: true }
                        }
                    });
                },
                setData: function () {
                    var that = this.global();
                    var item = that.vars.itemVar;
                    if (item) {
                        $('input[name=groupName]', that.selector).valChange(item['groupName']);
                        $('input[name=stat]', that.selector).valChange(item['stat']);
                        $('input[name=groupNo]', that.selector).valChange(item['groupNo']);
                        $('input[name=instDate]', that.selector).valChange(item['instDate']);
                        $('input[name=updtDate]', that.selector).valChange(item['updtDate']);

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
                            url: 'saler/group/save.json',
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

                    }
                }
            }
        });
		
	</script>
</head>
<body>
	<div class="container otod-system-sale-pubsalergroup-form">
        <form class="form-horizontal">
<!--             <input type="hidden" name="id" /> -->
			<input type="hidden" name="groupNo">
			<input type="hidden" name="instDate">
			<input type="hidden" name="updtDate">
            <div class="row">
                <input class="inputGroup" type="text" name="groupName" options="分组名称" maxlength="40"/>
                <input class="selectGroup" type="text" name="stat" options="状态" />
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