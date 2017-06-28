<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改码组</title>
    <style type="text/css">
        .otod-system-syscodgroup-from {}
        .otod-system-syscodgroup-from .form-horizontal {margin-top: 10px;}
    </style>
    <script type="text/javascript">
        $(function (){
            app.init();
        });

        app.init('otod.system.syscodgroup.form', {
            vars: {
                formValidatorVar: false,
                infoGridVar: false,
                itemVar: false
                
            },
            init: function () {
                var that = this;
                this.global = function () { return that;};
                jQuery.validator.addMethod("nonChinese", function(value, element) {       
            		return this.optional(element) || !/^[\u4E00-\u9FA0]+$/.test(value);       
            	}, "{vname} : 不能包含中文。");
                app.code('availableStat');
                //赋值
                if (typeof that.modal != 'undefined') that.vars.itemVar = that.modal.param['item'];
                this.layout();
                this.handlers.init(that);
                this.infoGrid.init(that);
                this.form.init(that);
               

            },
            layout: function () {
                var that = this.global();

                if (that.vars.itemVar) {
                    var item = that.vars.itemVar;
                    $("#infoGridQuery input[name=id]", that.selector).val(item['id']);

                    $("input[name=id]", that.selector).val(item['id']);
                    $("input[name=typId]", that.selector).val(item['typId']);
                    $("input[name='params[codTyp]']", that.selector).val(item['codTyp']);

                    $("input[name=groupCod]", that.selector).val(item['groupCod']);
                    $(":input[name=remark]", that.selector).val(item['remark']);

                    if (item['id']) {

                    }
                }

                $('.inputGroup', that.selector).inputGroup({
                    cssCol: 'col-xs-12',
                    cssLabel: 'col-xs-3 col-sm-2',
                    cssInputGroup: 'col-xs-9 col-sm-8 col-md-7 col-lg-6'
                });

                $("#saveBtn", that.selector).bind('click', that, function (event) {
                    var that = event.data.refresh();
                    var $validator = that.vars.formValidatorVar;
                    var $infoGrid = that.vars.infoGridVar;
                    if ($validator.form()) {
                        var $form = $($validator.currentForm);

                        var data = $form.serialize();

                        var items = $infoGrid.selectedRows();

                        var infoList = [];
                        for (var i = 0; i < items.length; i++) {
                            infoList.push('&infoList[' + i + '].id=' + items[i]['id']);
                        }
                        data += infoList.join('');

                        $(this).disabled(true);
                        $.ajax({
                            url: 'code/group/save.json',
                            type: 'POST',
                            data: data,
                            success: function (data) {
                            	var data = app.data(data);
                                $(this).disabled(false);

                                if (!data.success){
                                    appui.message.error(data.msg);
                                    return;
                                }

                                appui.modal.close(data);
                            }
                        });

                    }

                });

                $("#cancelBtn", that.selector).click(function () {
                    appui.modal.close({
                        closed: false
                    });
                });
            },
            handlers: {
                init: function (that) {
                    this.global = function () {return that;};
                }
            },
            form: {
                init: function (that) {
                    this.global = function () {return that;};

                    this.validator();
                },
//                 /^[\u4E00-\u9FA0]+$/
                validator: function () {
                    var that = this.global();
                    that.vars.formValidatorVar = $('form', that.selector).validate({
                        rules: {
                            groupCod: {
                                required: true,
                                nonChinese: true,
                            	rangelength:[1,20]
                            },
                            remark: {
                                required: true,
                                rangelength:[1,60]
                            }

                        }
                    });
                }
            },
            infoGrid: {
                init: function (that) {
                    this.global = function () {return that;};

                    this.layout();
                },
                layout: function () {
                    var that = this.global();

                    var cols = [
                        { title: '码值编码', name: 'codVal', align: 'center'},
                        { title: '码值名称', name: 'codName', align: 'center'},
                        { title: '码值状态', name: 'stat', align: 'center', width: '100', renderer: function (val, item, rowIndex) {
                            return app.code.getText('stat', val, rowIndex);
                        }}
                    ];


                    var config = {
                        height: '320px',
                        cols: cols,
                        url: 'code/group/get.json',
                        indexCol: true,
                        checkCol: true,
                        multiSelect: true,
                        checked: {
                            enable: true,
                            key: 'checked',
                            handler: function (val, item, rowIndex) {
                                return val != null && val == 1;
                            }
                        },
                        plugins:[
                            $("#infoGridQuery", that.selector).gridGroupQuery({
                                param: '{0}'
                            })
                        ],
                        events: {
                            layoutFinished: {
                                data: that,
                                handler: function (event, loadData) {
                                    //appui.modal.resize(modal);
                                }
                            }
                        }
                    };

                    that.vars.infoGridVar = $("#infoGrid", that.selector).gridGroup(config);


                }
            }
        });
    </script>
</head>
<body>
    <div class="container-fluid otod-system-syscodgroup-form">

        <!--form-->
        <form class="form-horizontal">

            <input type="hidden" name="id" />
            <input type="hidden" name="typId" />

            <div class="row">
                <input type="text" class="inputGroup" name="groupCod" options="码组编码"/>
                <textarea class="inputGroup" name="remark" options="码组备注"></textarea>
            </div>
        </form>

        <!--grid-->
        <table id="infoGrid"></table>
        <div id="infoGridQuery">
            <input type="hidden" name="id" />
            <input type="hidden" name="params[codTyp]" />
        </div>
        <div id="infoGridPage"></div>


        <!--表单按钮-->
        <div class="row ui-toolbars">
            <div class="pull-right">
                <button type="button" class="btn btn-primary btn-md" id="saveBtn"><i class="fa fa-save"></i> 保存</button>
                <button type="button" class="btn btn-danger btn-md" id="cancelBtn"><i class="fa fa-remove"></i> 取消</button>
            </div>
        </div>


    </div>
</body>
</html>
