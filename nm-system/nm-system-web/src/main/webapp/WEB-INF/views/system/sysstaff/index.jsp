<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <style type="text/css">
        .otods-system-sysstaff {}
    </style>
    <script type="text/javascript">
        $(function () { app.init(); });

        app.init('otods.system.sysstaff', {
            vars: {
                gridVar: false,
                roleSelectInputVar: false
            },
            init: function () {
                var that = this;
                this.global = function () { return that; };

                app.code('availableStat');

                this.layout();
                this.grid.init(that);
            },
            layout: function () {
                var that = this.global();
            },
            grid: {
                init: function (that) {
                    this.global = function () { return that; };

                    this.layout();
                    this.handlers.init(that);
                },
                layout: function () {
                    var that = this.global();

                    $('.inputGroup', that.selector).inputGroup();

                    $('input[name=belgOrgNo]', that.selector).selectTreeGroup({
                        itemLabel: 'orgName',
                        itemValue: 'orgNo',
                        data: {
                            id: 'orgNo',
                            pid: 'parOrgNo',
                            name: 'orgName',
                            children: 'list'
                        },
                        show: {
                            multi: false
                           /* checkbox: false*/
                        },
                        remote: {
                            url: 'org/list.json',
                            type: 'POST',
                            callback: function (data) {
                                return app.toTreeJson(data.list, {
                                    id: 'orgNo',
                                    pid: 'parOrgNo',
                                    children: 'list'
                                });
                            }
                        }
                    });

                    that.vars.roleSelectInputVar = $('input[name=roleNo]', that.selector).inputButtonGroup({
                        itemLabel: 'roleName',
                        itemValue: 'roleNo',
                        events: {
                            btnClick: {
                                data: that,
                                handler: function (event) {
                                    event.preventDefault();
                                    event.stopPropagation();
                                    var that = event.data.refresh();
                                    that.grid.handlers.roleSelect();
                                    return false;
                                }
                            }
                        }
                    });

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

                        if (items[0].staffStat == '10002002') {
                            appui.message.info('当前用户已是"锁定(失效)"状态.');
                            return false;
                        }

                        items[0].staffStat = '10002002';

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

                        if (items[0].staffStat == '10002001') {
                            appui.message.info('当前用户已是"解锁(正常)"状态.');
                            return false;
                        }

                        items[0].staffStat = '10002001';

                        that.grid.handlers.disabled(items[0]);

                        return false;
                    });
                    $('#roleSettingBtn', that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();

                        var items = that.vars.gridVar.selectedRows();
                        if (items.length != 1) {
                            appui.message.info('请选择一条操作数据.');
                            return false;
                        }

                        that.grid.handlers.roleSetting(items[0]);

                        return false;
                    });

                    $('#orgSettingBtn', that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();

                        var items = that.vars.gridVar.selectedRows();
                        if (items.length != 1) {
                            appui.message.info('请选择一条操作数据.');
                            return false;
                        }

                        that.grid.handlers.orgSetting(items[0]);

                        return false;
                    });
                    //重置密码
                    $('#resetPwdBtn', that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();

                        var items = that.vars.gridVar.selectedRows();
                        if (items.length != 1) {
                            appui.message.info('请选择一条操作数据.');
                            return false;
                        }

                       
                        appui.dialog.confirm('是否重置该用户密码?', function(r){
        					if(r){
        						
        						$.ajax({
        							url : 'staff/resetPwd',
        							type: 'POST',
        							data: {staffNo:items[0].staffNo},
        							success: function(data){
        								
        								if (data && !data.success){
        			                       appui.message.error(data.msg);
        			                       return;
        			                    }
        								appui.message.success('重置密码成功');
        								appui.modal.close(data);
        							}
        						});
        					}
        				});
                        
                        
                        

                        return false;
                    });

                    //定义列
                    var cols = [
                        { title: '工号', name: 'staffNo' ,width: 100, align: 'center'},
                        { title: '登陆账号', name: 'loginNo' ,width: 100, align: 'center'},
                        { title: '用户姓名', name: 'staffName' ,width: 100, align: 'center'},
                        { title: '用户状态', name: 'staffStat' ,width: 100, align: 'center', renderer: function(val, item, index) {
                            return app.code.getText('availableStat', val, index);
                        }}
                    ];

                    var config = {
                        cols: cols,
                        url: 'staff/list.json',
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


                    config.plugins.push($('#gridGroupPage', that.selector).gridGroupPaginator({}));
                    config.plugins.push($("#gridGroupQuery", that.selector).gridGroupQuery({queryBtn: $("#queryBtn", that.selector)}));


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
                            title: '添加员工',
                            url: 'staff/form.htm',
                            cssModal: 'modal-lg',
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
                            title: '修改员工',
                            url: 'staff/form.htm',
                            cssModal: 'modal-lg',
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

                        delete item.createDate;
                        delete item.modifyDate;
                        delete item.lastLoginDate;

                        $.ajax({
                            url: 'staff/save.json',
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
                    roleSelect: function () {
                        var that = this.global();

                        appui.modal.open({
                            title: '角色选择',
                            url: 'staff/role/list.htm',
                            cssModal: 'modal-lg',
                            styleBody: 'padding-left: 0; padding-right: 0;',
                            param: {
                                that: that
                            },
                            callback: {
                                hidden: function (options) {
                                    var param = options.param;
                                    var data = options.data;
                                    if (!data.closed) return false;

                                    var item = data.item;

                                    that.vars.roleSelectInputVar.valChange(item);
                                }
                            }
                        });

                        return false;
                    },
                    roleSetting: function (item) {
                        var that = this.global();

                        appui.modal.open({
                            title: '角色设置',
                            url: 'staff/role/setting.htm',
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
                    orgSetting: function (item) {
                        var that = this.global();

                        appui.modal.open({
                            title: '机构设置',
                            url: 'staff/org/setting.htm',
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
    <div class="container-fluid otods-system-sysstaff">
        <div class="page-header"><i class="fa fa-th"></i> 用户管理</div>
        <div class="row ui-querybars" id="gridGroupQuery">
            <input type="text" class="inputGroup" name="staffNo" options="{txtPrefix: '工号'}"/>
            <input type="text" class="inputGroup" name="staffName" options="{txtPrefix: '用户姓名'}"/>
            <input type="text" class="inputButtonGroup" name="belgOrgNo" options="{txtPrefix: '所属机构'}"/>
            <input type="text" class="selectTreeGroup" name="roleNo" options="{txtPrefix: '拥有角色'}"/>
        </div>
        <div class="row ui-toolbars">
            <div class="pull-left">
                <button class="btn btn-success btn-sm" id="addBtn"><i class="fa fa-plus"></i> 添加</button>
                <button class="btn btn-primary btn-sm" id="editBtn"><i class="fa fa-edit"></i> 编辑</button>
                <button class="btn btn-danger btn-sm" id="lockBtn"><i class="fa fa-lock"></i> 锁定</button>
                <button class="btn btn-primary btn-sm" id="unlockBtn"><i class="fa fa-unlock"></i> 解锁</button>
                <button class="btn btn-primary btn-sm" id="roleSettingBtn"><i class="fa fa-gear"></i> 角色设置</button>
                <button class="btn btn-primary btn-sm" id="orgSettingBtn"><i class="fa fa-gear"></i> 机构设置</button>
                 <button class="btn btn-primary btn-sm" id="resetPwdBtn"><i class="fa fa-gear"></i> 重置密码</button>
            </div>
            <div class="pull-right">
                <button class="btn btn-primary btn-sm" id="queryBtn"><i class="fa fa-search"></i> 查询</button>
            </div>
        </div>
        <table id="gridGroup"></table>
        <div id="gridGroupPage"></div>
    </div>
</body>
</html>
