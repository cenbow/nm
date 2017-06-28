<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>码表管理</title>
    <style type="text/css">
        .otod-system-syscodtyp {}
        .otod-system-syscodtyp .query-group {width: 200px;}
    </style>
    <script type="text/javascript">
        $(function () {
            app.init();
        });

        app.init('otod.system.syscodtyp', {
            vars: {
                typeGridVar: false,
                infoGridVar: false,
                codTypEmpty: '$empty$'
            },
            init: function () {
                var that = this;
                this.global = function () { return that;};

                app.code('availableStat');

                that.layout(that);

                that.typeGrid.init(that);
                that.infoGrid.init(that);

            },
            layout: function (that) {
                //设置empty
                $("input[name=codTyp]", that.selector).val(that.vars.codTypEmpty);
            },
            typeGrid: {
                init: function (that) {
                    this.global = function () { return that;};
                    this.layout();
                    this.handlers.init(that);
                },
                layout: function () {
                    var that = this.global();

                    $(".typeToolBars .addBtn", that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();
                        that.typeGrid.handlers.add();
                        return false;
                    });

                    $(".typeToolBars .delBtn", that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();
                        that.typeGrid.handlers.del();
                        return false;
                    });

                    var cols = [
                        { title: '码类编码', name: 'codTyp', align: 'center', width: '50'},
                        { title: '码类名称', name: 'codTypName', align: 'center', width: '100'},
                        { title: '码类状态', name: 'stat', align: 'center', width: '100', renderer: function (val, item, rowIndex) {
                            return app.code.getText('availableStat', val, rowIndex);
                        }},
                        { title: '操作方式', name: 'id', align: 'center', width: '100', renderer: function (val, item, rowIndex) {
                            return appui.fmt([
                                '<a class="fa fa-edit edit-type" href="javascript:;" > 修改</a>',
                                '&nbsp;&nbsp;<a class="fa {cssDisabled} disabled-type" href="javascript:;"> {txtDisabled}</a>',
                                '&nbsp;&nbsp;<a class="fa fa-eye-slash text-info view-type-group" href="javascript:;"> 查看码组</a>'
                            ], {
                                txtDisabled: item['stat'] != '10002001' ? '启用' : '禁用',
                                cssDisabled: item['stat'] != '10002001' ? 'text-success fa-toggle-on' : 'text-danger fa-toggle-off'
                            });
                        }}
                    ];

                    var config = {
                        cols: cols,
                        url: 'code/type/list.json',
                        checkCol: true,
                        indexCol: true,
                        plugins:[],
                        events: {},
                        customEvents: []
                    };

                    config.plugins.push($("#typeGridQuery", that.selector).gridGroupQuery({ queryBtn: $('#typeGridQuery #queryBtn', that.selector)}));
                    config.plugins.push($("#typeGridPage", that.selector).gridGroupPaginator());

                    //events
                    config.events.cellClick = {
                        data: that,
                        handler: function (event, item, isSelected, rowIndex, colIndex) {
                            event.preventDefault();
                            event.stopPropagation();

                            var that = event.data.refresh();
                            $(".infoGrid input[name=codTyp]", that.selector).val(item['codTyp']);
                            that.vars.infoGridVar.load({pageNo: 1});

                            return false;
                        }
                    };

                    //customevents
                    config.customEvents.push({
                        target: '.edit-type',
                        data: that,
                        handler: function (event, item, rowIndex) {
                            event.preventDefault();
                            event.stopPropagation();

                            var that = event.data.refresh();
                            that.typeGrid.handlers.edit(item);

                            return false;
                        }
                    });

                    config.customEvents.push({
                        target: '.disabled-type',
                        data: that,
                        handler: function (event, item, rowIndex) {
                            event.preventDefault();
                            event.stopPropagation();

                            var that = event.data.refresh();
                            that.typeGrid.handlers.disabled(item);

                            return false;
                        }
                    });

                    config.customEvents.push({
                        target: '.view-type-group',
                        data: that,
                        handler: function (event, item, rowIndex) {
                            event.preventDefault();
                            event.stopPropagation();

                            var that = event.data.refresh();
                            that.typeGrid.handlers.viewGroup(item);

                            return false;
                        }
                    });


                    that.vars.typeGridVar = $("#typeGrid", that.selector).gridGroup(config);
                },
                handlers: {
                    init: function (that){
                        this.global = function () { return that;}
                    },
                    add: function () {
                        var that = this.global();

                        appui.modal.open({
                            title: '添加码类',
                            url: 'code/type/form.htm',
                            cssModal: 'modal-md',
                            param: {
                                that: that
                            },
                            callback: {
                                hidden: function (options) {
                                    var param = options.param;
                                    var that = param.that;
                                    var data = options.data;

                                    if (!data.closed) return;


                                    appui.message.success('操作成功.');

                                    //typeGrid
                                    that.vars.typeGridVar.load({pageNo: 1});

                                    //infoGrid
                                    $(".infoGrid input[name=codTyp]", that.selector).val(that.vars.codTypEmpty);
                                    that.vars.infoGridVar.load({pageNo: 1});
                                }
                            }
                        });

                        return false;
                    },
                    edit: function (item) {
                        var that = this.global();

                        appui.modal.open({
                            title: '修改码类',
                            url: 'code/type/form.htm',
                            cssModal: 'modal-md',
                            param: {
                                that: that,
                                item: item
                            },
                            callback: {
                                hidden: function (options) {
                                    var param = options.param;
                                    var that = param.that;
                                    var data = options.data;

                                    if (!data.closed) return;

                                    appui.message.success('操作成功.');

                                    //typeGrid
                                    that.vars.typeGridVar.load({pageNo: 1});

                                    //infoGrid
                                    $(".infoGrid input[name=codTyp]", that.selector).val(that.vars.codTypEmpty);
                                    that.vars.infoGridVar.load({pageNo: 1});
                                }
                            }
                        });

                        return false;
                    },
                    del: function () {
                        var that = this.global();
                        var items = that.vars.typeGridVar.selectedRows();
                        if (items.length != 1) {
                            appui.message.info('请选择一条操作数据.');
                            return;
                        }

                        var item = items[0];

                        var data = {
                            id: item['id']
                        };
						
						appui.dialog.confirm({
			                size: "modal-small",
			                title: "消息提示", //optional
			                message: "是否删除",//required
			                callback: function(r){
			                	if (r) {
			                        $.ajax({
			                            url: 'code/type/del.json',
			                            type: 'POST',
			                            data: data,
			                            success: function (data) {
			                            	var data = app.data(data);
			                            	
			                                if (!data.success){
			                                    appui.message.error(data.msg);
			                                    return;
			                                }

			                                appui.message.success('操作成功.');

			                                //刷新typeGrid
			                                that.vars.typeGridVar.load({pageNo: 1});

			                                //清空已选择codTyp
			                                $("input[name=codTyp]", that.selector).val(that.vars.codTypEmpty);
			                                //刷新infoGrid
			                                that.vars.infoGridVar.load({pageNo: 1});
			                            }
			                        });
			                	};

			                } 
			            });
                        return false;
                    },
                    disabled: function (item) {
                        var that = this.global();

                        var data = item;

                        data['stat'] = data['stat'] != '10002001' ? '10002001' : '10002002';

                        delete data['instDate'];
                        delete data['updtDate'];

                        $.ajax({
                            url: 'code/type/save.json',
                            type: 'POST',
                            data: data,
                            success: function (data) {
                            	var data = app.data(data);
                        		
                                if (!data.success){
                                    appui.message.error(data.msg);
                                    return;
                                }

                                appui.message.success(data.msg);

                                //刷新typeGrid
                                that.vars.typeGridVar.load({
                                    pageNo: 1
                                });

                                /*//清空已选择codTyp
                                $("input[name=codTyp]", that.selector).val('');
                                //刷新infoGrid
                                that.vars.infoGridVar.load({
                                    pageNo: 1
                                });*/
                            }
                        });

                        return false;
                    },
                    viewGroup: function (item) {
                        var that = this.global();
                        appui.modal.open({
                            title: '查看码组 <i class="fa fa-arrows-h"></i> 码值明细',
                            url: 'code/group/index.htm',
                            cssModal: 'modal-fl',
                            styleBody: 'padding-left: 0; padding-right: 0;',
                            param: item
                        });
                    }
                }
            },
            infoGrid: {
                init: function (that) {
                    this.global = function () { return that;};

                    this.layout();

                    this.handlers.init(that);
                },
                layout: function () {
                    var that = this.global();

                    $(".infoToolBars .addBtn", that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();
                        that.infoGrid.handlers.add();
                        return false;
                    });

                    $(".infoToolBars .delBtn", that.selector).bind('click', that, function (event) {

                        var that = event.data.refresh();
                        that.infoGrid.handlers.del();
                        return false;
                    });

                    var cols = [
                        { title: '码值编码', name: 'codVal', align: 'center'},
                        { title: '码值名称', name: 'codName', align: 'center'},
                        { title: '码值状态', name: 'stat', align: 'center', width: '100', renderer: function (val, item, rowIndex) {
                            return app.code.getText('availableStat', val, rowIndex);
                        }},
                        { title: '操作方式', name: 'id', align: 'center', width: '100', renderer: function (val, item, rowIndex) {
                            return appui.fmt([
                                '<a class="fa fa-edit edit-info" href="javascript:;"> 修改</a>',
                                '&nbsp;&nbsp;<a class="fa {cssDisabled} disabled-info" href="javascript:;"> {txtDisabled}</a>'
                            ], {
                                txtDisabled: item['stat'] != '10002001' ? '启用' : '禁用',
                                cssDisabled: item['stat'] != '10002001' ? 'text-success fa-toggle-on' : 'text-danger fa-toggle-off'
                            });
                        }}
                    ];

                    var config = {
                        /*height: '920px',*/
                        cols: cols,
                        url: 'code/info/list.json',
                        indexCol: true,
                        plugins: [],
                        events: {},
                        customEvents: []
                    };

                    config.plugins.push( $("#infoGridQuery", that.selector).gridGroupQuery());
                    config.plugins.push($("#infoGridPage", that.selector).gridGroupPaginator());

                    config.customEvents.push({
                        target: '.edit-info',
                        data: that,
                        handler: function (event, item, rowIndex) {
                            event.preventDefault();
                            event.stopPropagation();

                            var that = event.data.refresh();
                            that.infoGrid.handlers.edit(item);

                            return false;
                        }
                    });

                    config.customEvents.push({
                        target: '.disabled-info',
                        data: that,
                        handler: function (event, item, rowIndex) {
                            event.preventDefault();
                            event.stopPropagation();

                            var that = event.data.refresh();
                            that.infoGrid.handlers.disabled(item);

                            return false;
                        }
                    });

                    that.vars.infoGridVar = $("#infoGrid", that.selector).gridGroup(config);


                },
                handlers: {
                    init: function (that){
                        this.global = function () { return that;}
                    },
                    add: function () {
                        var that = this.global();

                        var codTyp = $("input[name=codTyp]", that.selector).val();

                        if (codTyp == that.vars.codTypEmpty) {
                            appui.message.info('请选择""一条操作数据.');
                            return;
                        }

                        var item = {
                            codTyp: codTyp
                        };

                        appui.modal.open({
                            title: '添加码值',
                            url: 'code/info/form.htm',
                            cssModal: 'modal-md',
                            param: {
                                that: that,
                                item: item
                            },
                            callback: {
                                hidden: function (options) {
                                    var param = options.param;
                                    var that = param.that;
                                    var data = options.data;

                                    if (!data.closed) return;

                                    appui.message.success('操作成功.');

                                    //infoGrid
                                    that.vars.infoGridVar.load({pageNo: 1});
                                }
                            }
                        });

                        return false;
                    },
                    edit: function (item) {
                        var that = this.global();

                        appui.modal.open({
                            title: '修改码值',
                            url: 'code/info/form.htm',
                            cssModal: 'modal-md',
                            param: {
                                that: that,
                                item: item
                            },
                            callback: {
                                hidden: function (options) {
                                    var param = options.param;
                                    var that = param.that;
                                    var data = options.data;

                                    if (!data.closed) return;

                                    appui.message.success('操作成功.');

                                    //infoGrid
                                    that.vars.infoGridVar.load({pageNo: 1});
                                }
                            }
                        });

                        return false;
                    },
                    del: function () {
                        var that = this.global();
                        var items = that.vars.infoGridVar.selectedRows();
                        if (items.length != 1) {
                            appui.message.info('请选择一条操作数据.');
                            return;
                        }

                        var item = items[0];

                        var data = {
                            id: item['id']
                        };

                        $.ajax({
                            url: 'code/info/del.json',
                            type: 'POST',
                            data: data,
                            success: function (data) {
                            	var data = app.data(data);
                            	
                                if (!data.success){
                                    appui.message.error(data.msg);
                                    return;
                                }

                                appui.message.success(data.msg);

                                //刷新infoGrid
                                that.vars.infoGridVar.load({pageNo: 1});
                            }
                        });
                        return false;
                    },
                    disabled: function (item) {
                        var that = this.global();

                        var data = item;

                        data['stat'] = data['stat'] != '10002001' ? '10002001' : '10002002';

                        delete data['instDate'];
                        delete data['updtDate'];

                        $.ajax({
                            url: 'code/info/save.json',
                            type: 'POST',
                            data: data,
                            success: function (data) {
                            	var data = app.data(data);
                                if (!data.success){
                                    appui.message.error(data.msg);
                                    return;
                                }

                                appui.message.success(data.msg);

                                //刷新infoGrid
                                that.vars.infoGridVar.load({
                                    pageNo: 1
                                });
                            }
                        });

                        return false;
                    }
                }
            }
        });

    </script>
</head>
<body>
<div class="container-fluid otod-system-syscodtyp">
    <div class="col-xs-6">
        <h3 class="page-header"><i class="fa fa-th-large"></i> 码类明细</h3>

        <!--typegrid toolbars-->
        <div class="row ui-toolbars typeToolBars">
            <div class="pull-left">
                <button type="button" class="btn btn-primary btn-sm addBtn"><i class="fa fa-plus"></i> 添加</button>
                <button type="button" class="btn btn-danger btn-sm delBtn"><i class="fa fa-minus"></i> 删除</button>
            </div>

            <!--typegrid querybars-->
            <div class="pull-right" id="typeGridQuery">
                <div class="control-group query-group">
                    <div class="input-group input-group-sm">
                        <input type="text" class="form-control" name="codTypName" placeholder="名称"/>
                        <div class="input-group-btn">
                            <button class="btn btn-primary btn-sm" id="queryBtn"><i class="fa fa-search"></i> 查询</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--typegrid-->
        <table id="typeGrid"></table>
        <div id="typeGridPage"></div>
    </div>

    <div class="col-xs-6 infoGrid">
        <h3 class="page-header"><i class="fa fa-th-large"></i> 码值明细</h3>

        <!--infogrid toolbars-->
        <div class="row ui-toolbars infoToolBars">
            <div class="pull-left">
                <button type="button" class="btn btn-primary btn-sm addBtn"><i class="fa fa-plus"></i> 添加</button>
                <button type="button" class="btn btn-danger btn-sm delBtn"><i class="fa fa-minus"></i> 删除</button>
            </div>
        </div>

        <!--infogrid-->
        <div id="infoGridQuery">
            <input type="hidden" name="codTyp" value="$empty$"/>
        </div>
        <table id="infoGrid"></table>
        <div id="infoGridPage"></div>
    </div>

</div>
</body>
</html>
