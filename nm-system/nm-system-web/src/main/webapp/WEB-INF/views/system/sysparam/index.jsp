<%--
  User: MifengHe(bee)
  Date: 2015/10/24
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>业务参数管理</title>
    <style type="text/css">
        .otod-system-sysparam{}
    </style>
    <script type="text/javascript">
        $(function () { app.init(); });

        app.init('otod.system.sysparam', {
            vars: {
                gridVar: false
            },
            init: function () {
                var that = this;
                this.global = function () { return that;};

                app.code("availableStat");

                this.grid.init(that);
            },
            grid: {
                init: function (that) {
                    this.global = function () { return that;};

                    this.layout();
                    this.handlers.init(that);
                },
                layout: function () {
                    var that = this.global();

                    $(".inputGroup", that.selector).inputGroup();

                    $("input[name=stat]", that.selector).selectGroup({
                        code: {
                            type: 'availableStat'
                        }
                    });

                    $("#addBtn", that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();
                        that.grid.handlers.add();
                        return false;
                    });

                    var cols = [
                        { title:'编码', name:'cod' ,width:100, align:'center'},
                        { title:'参数名', name:'name' ,width:100, align:'center'},
                        { title:'值', name:'val' ,width:100, align:'center'},
                        { title: '状态', name: 'stat', align: 'center', width: '100', renderer: function (val, item, rowIndex) {
                            return app.code.getText('availableStat', val, rowIndex);
                        }},
                        { title: '操作方式', name: 'id', align: 'center', width: '100', renderer: function (val, item, rowIndex) {
                            return appui.fmt([
                                '<a class="fa fa-edit edit-param" href="javascript:;" > 修改</a>',
                                '&nbsp;&nbsp;<a class="fa {cssDisabled} disabled-param" href="javascript:;"> {txtDisabled}</a>',
                                '&nbsp;&nbsp;<a class="fa fa-remove text-danger del-param" href="javascript:;"> 删除</a>'
                            ], {
                                txtDisabled: item['stat'] != '10002001' ? '启用' : '禁用',
                                cssDisabled: item['stat'] != '10002001' ? 'text-success fa-toggle-on' : 'text-danger fa-toggle-off'
                            });
                        }}
                    ];

                    var config = {
                        cols: cols,
                        url: 'param/list.json',
                        params: {
                        },
                        multiSelect: false,
                        checkCol: false,
                        indexCol: true,
                        nowrap :false, //内容不折行
                        plugins: [],
                        events: {},
                        customEvents: []
                    };

                    config.plugins.push($('#gridGroupPage',that.selector).gridGroupPaginator({}));
                    config.plugins.push($("#gridGroupQuery",that.selector).gridGroupQuery({ queryBtn: $("#queryBtn",that.selector),resetBtn: $("#resetBtn", that.selector)}));

                    //customevents
                    config.customEvents.push({
                        target: '.edit-param',
                        data: that,
                        handler: function (event, item, rowIndex) {
                            event.preventDefault();
                            event.stopPropagation();

                            var that = event.data.refresh();
                            that.grid.handlers.edit(item);

                            return false;
                        }
                    });

                    config.customEvents.push({
                        target: '.disabled-param',
                        data: that,
                        handler: function (event, item, rowIndex) {
                            event.preventDefault();
                            event.stopPropagation();

                            var that = event.data.refresh();
                            that.grid.handlers.disabled(item);

                            return false;
                        }
                    });

                    config.customEvents.push({
                        target: '.del-param',
                        data: that,
                        handler: function (event, item, rowIndex) {
                            event.preventDefault();
                            event.stopPropagation();

                            var that = event.data.refresh();
                            that.grid.handlers.del(item);

                            return false;
                        }
                    });


                    that.vars.gridVar = $('#gridGroup',that.selector).gridGroup(config);

                },
                handlers: {
                    init: function (that) {
                        this.global = function () { return that;};
                    },
                    add: function () {
                        var that = this.global();

                        appui.modal.open({
                            title: '添加参数',
                            url: 'param/form.htm',
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
                                    that.vars.gridVar.load({pageNo: 1});
                                }
                            }
                        });

                        return false;
                    },
                    edit: function (item) {
                        var that = this.global();
                        appui.modal.open({
                            title: '修改参数',
                            url: 'param/form.htm',
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

                                    that.vars.gridVar.load({pageNo: 1});
                                }
                            }
                        });

                        return false;
                    },
                    del: function (item) {
                        var that = this.global();
                        var data = {
                            id: item['id']
                        };

                        $.ajax({
                            url: 'param/del.json',
                            type: 'POST',
                            data: data,
                            success: function (data) {
                            	data = app.data(data);
                                if (!data.success){
                                    appui.message.error(data.msg);
                                    return;
                                }else{
	                                appui.message.success(data.msg);
                                }


                                that.vars.gridVar.load({pageNo: 1});
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
                            url: 'param/save.json',
                            type: 'POST',
                            data: data,
                            success: function (data) {
                            	data = app.data(data);
                                if (!data.success){
                                    appui.message.error(data.msg);
                                    return;
                                }else{
	                                appui.message.success(data.msg);
                                }
                                that.vars.gridVar.load({ pageNo: 1 });

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
    <div class="container-fluid otod-system-sysparam">
        <h3 class="page-header"><i class="fa fa-th-large"></i> 业务参数</h3>
        <div class="ui-querybars row" id="gridGroupQuery">
            <input type="text" class="inputGroup" name="cod" options='{txtPrefix: "编码"}' />
            <input type="text" class="inputGroup" name="name" options='{txtPrefix: "参数名"}' />
            <input id="stat" type="text" class="selectGroup " name="stat" options='{txtPrefix: "参数状态"}' />
        </div>
        <div class="ui-toolbars row">
            <div class="pull-left">
                <button class="btn btn-success btn-sm" id="addBtn"><i class="fa fa-plus"></i> 添加</button>
            </div>

            <div class="pull-right">
                <button class="btn btn-primary btn-sm" id="queryBtn"><i class="fa fa-search"></i> 查询</button>
                <button class="btn btn-danger btn-sm" id="resetBtn"><i class="fa fa-repeat"></i> 重置</button>
            </div>
        </div>
        <table id="gridGroup"></table>
        <div id="gridGroupPage"></div>
    </div>
</body>
</html>
