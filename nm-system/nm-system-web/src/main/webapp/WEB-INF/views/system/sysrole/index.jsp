<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色管理</title>
    <style type="text/css">
        .otod-system-sysrole {}
        .otod-system-sysrole .query-group{}
    </style>
    <script type="text/javascript">

        $(function () { app.init(); });

        app.init('otod.system.sysrole', {
            vars: {
                gridVar: null
            },
            init: function(){
                var that = this;
                this.global = function () { return that; };
                app.code('availableStat');

                this.layout(that);
                this.grid.init(that);

            },
            layout: function () {

            },
            grid: {
                init: function (that) {
                    this.global = function () { return that; };
                    this.layout();
                    this.handlers.init(that);
                },
                layout: function () {
                    var that = this.global();

                    $('#addBtn', that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();

                        that.grid.handlers.add();

                        return false;
                    });
                    $('#editBtn', that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();

                        var items = that.vars.gridVar.selectedRows();
                        if (items.length != 1) {
                            appui.message.info('请选择一条操作数据.');
                            return false;
                        }
                        if (items[0].roleStat == '10002002') {
                            appui.message.info('当前角色已失效.');
                            return false;
                        }

                        that.grid.handlers.edit(items[0]);

                        return false;
                    });
                    $('#lockBtn', that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();

                        var items = that.vars.gridVar.selectedRows();
                        if (items.length != 1) {
                            appui.message.info('请选择一条操作数据.');
                            return false;
                        }

                        if (items[0].roleStat == '10002002') {
                            appui.message.info('当前角色已是"锁定(失效)"状态.');
                            return false;
                        }

                        items[0].roleStat = '10002002';

                        that.grid.handlers.disabled(items[0]);

                        return false;
                    });
                    $('#unlockBtn', that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();

                        var items = that.vars.gridVar.selectedRows();
                        if (items.length != 1) {
                            appui.message.info('请选择一条操作数据.');
                            return false;
                        }

                        if (items[0].roleStat == '10002001') {
                            appui.message.info('当前角色已是"解锁(正常)"状态.');
                            return false;
                        }

                        items[0].roleStat = '10002001';

                        that.grid.handlers.disabled(items[0]);

                        return false;
                    });
                    $('#staffSettingBtn', that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();

                        var items = that.vars.gridVar.selectedRows();
                        if (items.length != 1) {
                            appui.message.info('请选择一条操作数据.');
                            return false;
                        }
                        if (items[0].roleStat == '10002002') {
                            appui.message.info('当前角色已失效.');
                            return false;
                        }

                        that.grid.handlers.staffSetting(items[0]);

                        return false;
                    });

                    $('#menuSettingBtn', that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();

                        var items = that.vars.gridVar.selectedRows();
                        if (items.length != 1) {
                            appui.message.info('请选择一条操作数据.');
                            return false;
                        }
                        if (items[0].roleStat == '10002002') {
                            appui.message.info('当前角色已失效.');
                            return false;
                        }

                        that.grid.handlers.menuSetting(items[0]);

                        return false;
                    });


                    var cols = [
                        { title:'角色编号', name:'roleNo' ,width:100, align:'center'},
                        { title:'角色名称', name:'roleName' ,width:100, align:'center'},
                        { title:'角色状态', name:'roleStat' ,width:100, align:'center', renderer: function (val, item, rowIndex) {
                            return app.code.getText('availableStat', val, rowIndex);
                        }}
                    ];

                    var config = {
                        cols: cols,
                        url: 'role/list.json',
                        params: {},
                        //loadingText: 'dd',
                        noDataText: '没有数据',
                        multiSelect: false,
                        checkCol: true,
                        indexCol: true,
                        nowrap :false, //内容不折行
                        plugins: [],
                        events:{},
                        customEvents: []
                    };

                    config.plugins.push($('#pageGroup', that.selector).gridGroupPaginator({}));
                    config.plugins.push($("#queryGroup", that.selector).gridGroupQuery({queryBtn: $("#queryBtn", that.selector)}));

                    that.vars.gridVar = $('#gridGroup', that.selector).gridGroup(config);
                },
                handlers: {
                    init: function (that) {
                        this.global = function () { return that; };
                    },
                    add: function () {
                        var that = this.global();

                        var item = {};

                        appui.modal.open({
                            title: '添加角色',
                            url: 'role/form.htm',
                            cssModal: 'modal-md',
                            param: {
                                that: that,
                                item: item
                            },
                            callback: {
                                hidden: function (options) {
                                    var param = options.param;
                                    var data = options.data;

                                    if (!data.closed) return false;

                                    appui.message.success('操作成功');

                                    var that = param.that;

                                    that.vars.gridVar.load({pageNo: 1});
                                }
                            }
                        });
                    },
                    edit: function (item) {
                        var that = this.global();

                        appui.modal.open({
                            title: '修改角色',
                            url: 'role/form.htm',
                            cssModal: 'modal-md',
                            param: {
                                that: that,
                                item: item
                            },
                            callback: {
                                hidden: function (options) {
                                    var param = options.param;
                                    var data = options.data;

                                    if (!data.closed) return false;

                                    appui.message.success('操作成功');

                                    var that = param.that;

                                    that.vars.gridVar.load({pageNo: 1});
                                }
                            }
                        });
                    },
                    disabled: function (item) {
                        var that = this.global();

                        delete item['instDate'];
                        delete item['updtDate'];

                        $.ajax({
                            url: 'role/save.json',
                            type: 'POST',
                            data: item,
                            success: function (data) {
                                if (!data.success) {
                                    appui.message.error('操作失败.');
                                    return false;
                                }

                                appui.message.success('操作成功.');
                                that.vars.gridVar.load({pageNo: 1});
                            }
                        });

                    },
                    staffSetting: function (item) {
                        var that = this.global();

                        appui.modal.open({
                            title: '用户设置',
                            url: 'role/staff/setting.htm',
                            cssModal: 'modal-fl',
                            styleBody: 'padding-left: 0; padding-right: 0;',
                            param: {
                                that: that,
                                item: item
                            },
                            callback: {
                                hidden: function (options) {
                                    var param = options.param;
                                    var data = options.data;

                                    if (!data.closed) return false;

                                    appui.message.success('操作成功.');

                                    var that = param.that;

                                    that.vars.gridVar.load({pageNo: 1});
                                }
                            }
                        });
                    },
                    menuSetting: function (item) {
                        var that = this.global();

                        appui.modal.open({
                            title: '菜单设置',
                            url: 'role/menu/setting.htm',
                            cssModal: 'modal-md',
                            /*styleBody: 'padding-left: 0; padding-right: 0;',*/
                            param: {
                                that: that,
                                item: item
                            },
                            callback: {
                                hidden: function (options) {
                                    var param = options.param;
                                    var data = options.data;

                                    if (!data.closed) return false;

                                    appui.message.success('操作成功.');

                                    var that = param.that;

                                    that.vars.gridVar.load({pageNo: 1});
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
    <div class="container-fluid otod-system-sysrole">

        <h3 class="page-header"><i class="fa fa-th-large"></i> 角色管理</h3>
        <div class="ui-toolbars row">
            <div class="pull-left">
                <button class="btn btn-success btn-sm" id="addBtn"><i class="fa fa-plus"></i> 添加</button>
                <button class="btn btn-primary btn-sm" id="editBtn"><i class="fa fa-edit"></i> 编辑</button>
                <button class="btn btn-danger btn-sm" id="lockBtn"><i class="fa fa-lock"></i> 锁定</button>
                <button class="btn btn-primary btn-sm" id="unlockBtn"><i class="fa fa-unlock"></i> 解锁</button>
                <button class="btn btn-primary btn-sm" id="staffSettingBtn"><i class="fa fa-gear"></i> 用户设置</button>
                <button class="btn btn-primary btn-sm" id="menuSettingBtn"><i class="fa fa-gear"></i> 菜单设置</button>
            </div>
            <div class="pull-right" id="queryGroup">
                <div class="input-group input-group-sm query-group">
                    <input type="text" class="form-control" name="roleName" placeholder="角色名称"/>
                    <div class="input-group-btn">
                        <button class="btn btn-primary btn-sm" id="queryBtn"><i class="fa fa-search"></i> 查询</button>
                    </div>

                </div>
            </div>
        </div>
        <table id="gridGroup"></table>
        <div id="pageGroup"></div>
    </div>
</body>
</html>
