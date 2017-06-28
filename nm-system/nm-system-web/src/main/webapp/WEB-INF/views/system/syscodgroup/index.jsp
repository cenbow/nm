<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>码组维护</title>
    <style type="text/css">
        .otod-system-syscodgroup {}
    </style>
    <script type="text/javascript">
        $(function () {
            app.init();
        });

        app.init('otod.system.syscodgroup', {
            vars: {
                groupGridVar: null,
                infoGridVar: null,
                itemVar: false
            },
            init: function () {
                var that = this;
                this.global = function () { return that;};

                app.code('availableStat');


                //赋值
                if (typeof that.modal != 'undefined') that.vars.itemVar = that.modal.param;


                this.layout(that);

                this.groupGrid.init(that);
                this.infoGrid.init(that);
            },
            layout: function () {
                var that = this.global();
                if (that.vars.itemVar) {
                    var item = that.vars.itemVar;

                    $('input[name=codTyp]', that.selector).val(item['codTyp']);
                    $('input[name=typId]', that.selector).val(item['id']);
                }
            },
            groupGrid: {
                init: function (that) {
                    this.global = function () { return that; };

                    this.layout();

                    this.handlers.init(that);
                },
                layout: function () {
                    var that = this.global();

                    $(".groupToolBars .addBtn", that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();

                        var that = event.data.refresh();
                        that.groupGrid.handlers.add();

                        return false;
                    });

                    $(".groupToolBars .delBtn", that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();

                        var that = event.data.refresh();
                        that.groupGrid.handlers.del();

                        return false;
                    });

                    var cols = [
                        { title: '码组编码', name: 'groupCod', align: 'center', width: '100'},
                        { title: '操作方式', name: 'id', align: 'center', width: '100', renderer: function (val, item, rowIndex) {
                            return appui.fmt([
                                '<a class="fa fa-edit {cssDisabled} eidt-group" href="javascript:;"> 修改</a>'
                            ], {
                                cssDisabled: Boolean(val) ? 'edit-group' : 'disabled'
                            });
                        }}
                    ];

                    var config = {
                        cols: cols,
                        url: 'code/group/list.json',
                        indexCol: true,
                        plugins:[],
                        events: {},
                        customEvents: []
                    };

                    config.plugins.push( $("#groupGridQuery", that.selector).gridGroupQuery());
                    config.plugins.push($("#groupGridPage", that.selector).gridGroupPaginator());

                    config.events.cellClick = {
                        data: that,
                        handler: function (event, item, isSelected, rowIndex, colIndex) {
                            var that = event.data.refresh();
                            $("input[name=groupId]", that.selector).val(item['id']);

                            that.vars.infoGridVar.load({pageNo: 1});
                        }
                    };

                    config.customEvents.push({
                        target: '.eidt-group',
                        data: that,
                        handler: function (event, item, rowIndex) {
                            event.preventDefault();
                            event.stopPropagation();

                            var that = event.data.refresh();
                            that.groupGrid.handlers.edit(item);

                            return false;
                        }
                    });

                    that.vars.groupGridVar = $("#groupGrid", that.selector).gridGroup(config);
                },
                handlers: {
                    init: function (that) {
                        this.global = function () { return that; };
                    },
                    add: function () {
                        var that = this.global();
                        var item = {};
                        if (that.vars.itemVar) item ={
                            typId: that.vars.itemVar['id'],
                            codTyp: that.vars.itemVar['codTyp']
                        };

                        appui.modal.open({
                            title: '添加码组',
                            url: 'code/group/form.htm',
                            cssModal: 'modal-lg',
                            param: {
                                that: that,
                                item: item
                            },
                            callback: {
                                hidden: function (options) {
                                    var param = options.param;
                                    var data = options.data;
                                    var that = param.that;

                                    if(!data.closed) return false;

                                    appui.message.success('保存成功');

                                    //groupGrid
                                    that.vars.groupGridVar.load({pageNo: 1});

                                    //infoGrid
                                    $("input[name=groupId]", that.selector).val(item['id']);
                                    that.vars.infoGridVar.load({pageNo: 1});
                                }
                            }
                        });

                        return false;
                    },
                    edit: function (item) {
                        var that = this.global();

                        if (!item['id']) {
                            appui.message.warning('不能修改 默认 码组.');
                            return false;
                        }

                        appui.modal.open({
                            title: '修改码组',
                            url: 'code/group/form.htm',
                            cssModal: 'modal-lg',
                            param: {
                                that: that,
                                item: item
                            },
                            callback: {
                                hidden: function (options) {
                                    var param = options.param;
                                    var data = options.data;
                                    var that = param.that;

                                    if(!data.closed) return false;

                                    appui.message.success('操作成功');

                                    //groupGrid
                                    that.vars.groupGridVar.load({pageNo: 1});

                                    //infoGrid
                                    $("input[name=groupId]", that.selector).val(item['id']);
                                    that.vars.infoGridVar.load({pageNo: 1});
                                }
                            }
                        });

                        return false;
                    },
                    del: function () {
                        var that = this.global();
                        var items = that.vars.groupGridVar.selectedRows();
                        if (items.length != 1) {
                            appui.message.info('请选择一条操作数据.');
                            return false;
                        }

                        var item = items[0];

                        if (!item['id']) {
                            appui.message.warning('不能删除"默认"码组.');
                            return false;
                        }

                        var data = {
                            id: item['id']
                        };

                        $.ajax({
                            url: 'code/group/del.json',
                            type: 'POST',
                            data: data,
                            success: function (data) {
                            	var data = app.data(data);
                                if (!data.success){
                                    appui.message.error(data.msg);
                                    return false;
                                }

                                appui.message.success(data.msg);

                                //刷新groupGrid
                                that.vars.groupGridVar.load({pageNo: 1});

                                //清空已选择groupId
                                $("input[name=groupId]", that.selector).val('');
                                //刷新infoGrid
                                that.vars.infoGridVar.load({pageNo: 1});
                            }
                        });

                        return false;
                    }
                }
            },
            infoGrid: {
                init: function (that) {
                    this.global = function () { return that; };

                    this.layout();

                    this.handlers.init(that);
                },
                layout: function () {
                    var that = this.global();

                    $(".infoToolBars .addBtn", that.selector).bind('click', that, function (event) {
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
                            return app.code.getText('stat', val, rowIndex);
                        }},
                        { title: '操作方式', name: 'id', align: 'center', width: '100', renderer: function (val, item, rowIndex) {
                            return appui.fmt([
                                '<a class="fa fa-edit" href="javascript:;" onclick="{namespace}.infoGrid.handlers.edit('+rowIndex+');"> 修改</a>',
                                '&nbsp;&nbsp;<a class="fa {cssDisabled}" href="javascript:;" onclick="{namespace}.infoGrid.handlers.disabled('+rowIndex+');"> {txtDisabled}</a>'
                            ], {
                                namespace: that.namespace,
                                rowIndex: rowIndex,
                                txtDisabled: item['stat'] != '10002001' ? '启用' : '禁用',
                                cssDisabled: item['stat'] != '10002001' ? 'text-success fa-toggle-on' : 'text-danger fa-toggle-off'
                            });
                        }}
                    ];

                    that.vars.infoGridVar = $("#infoGrid", that.selector).gridGroup({
                        cols: cols,
                        url: 'code/info/list.json',
                        indexCol: true,
                        plugins:[
                            $("#infoGridQuery", that.selector).gridGroupQuery(),
                            $("#infoGridPage", that.selector).gridGroupPaginator()
                        ]
                    });
                },
                handlers: {
                    init: function (that){
                        this.global = function () { return that;}
                    },
                    add: function () {
                        var that = this.global();

                        var codTyp = $("#infoGridQuery input[name=codTyp]", that.selector).val();

                        if (!codTyp) {
                            appui.message.error('请指定"码值".');
                            return false;
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

                                    $("#infoGridQuery input[name=groupId]", that.selector).val('');

                                    //infoGrid
                                    that.vars.infoGridVar.load({pageNo: 1});
                                }
                            }
                        });

                        return false;
                    },
                    edit: function (rowIndex) {
                        var that = this.global();

                        var item = that.vars.infoGridVar.row(rowIndex);

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
                    disabled: function (rowIndex) {
                        var that = this.global();
                        var item = that.vars.infoGridVar.row(rowIndex);

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
    <div class="container-fluid otod-system-syscodgroup">
        <div class="col-xs-5">
            <h4 class="page-header"><i class="fa fa-th-large"></i> 码组明细</h4>
            <div class="row ui-toolbars groupToolBars">
                <div class="pull-left">
                    <button type="button" class="btn btn-primary btn-sm addBtn"><i class="fa fa-plus"></i> 添加</button>
                    <button type="button" class="btn btn-danger btn-sm delBtn"><i class="fa fa-minus"></i> 删除</button>
                </div>
            </div>
            <table id="groupGrid"></table>
            <div id="groupGridQuery">
                <input type="hidden" name="codTyp" disabled/>
                <input type="hidden" name="typId" />
            </div>
            <div id="groupGridPage"></div>
        </div>
        <div class="col-xs-7">
            <h4 class="page-header"><i class="fa fa-th-large"></i> 码值明细</h4>
            <div class="row ui-toolbars infoToolBars">
                <div class="pull-left">
                    <button type="button" class="btn btn-primary btn-sm addBtn"><i class="fa fa-plus"></i> 添加</button>
                    <button type="button" class="btn btn-danger btn-sm delBtn"><i class="fa fa-minus"></i> 删除</button>
                </div>
            </div>
            <table id="infoGrid"></table>
            <div id="infoGridQuery">
                <input type="hidden" name="codTyp" />
                <input type="hidden" name="groupId" />
            </div>
            <div id="infoGridPage"></div>
        </div>

    </div>
</body>
</html>
