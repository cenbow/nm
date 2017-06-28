<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>用户管理</title>
    <style type="text/css">
        .otod-system-sysrole-staff-setting{}
        .otod-system-sysrole-staff-setting .page-header {font-size: 16px; margin-top: 5px; margin-bottom: 10px;}
        .otod-system-sysrole-staff-setting .grid-container {padding-left: 2px; padding-right: 2px;}
    </style>
    <script type="text/javascript">
        $(function() { app.init(); });

        app.init('otod.system.sysrole.staff.setting', {
            vars: {
                havingGridVar: false,
                hadGridVar: false,
                roleItemVar: false
            },
            init: function () {
                var that = this;
                this.global = function () { return that; };

                if (typeof that.modal != 'undefined') that.vars.roleItemVar = that.modal.param.item;

                this.layout();
                this.havingGrid.init(that);
                this.hadGrid.init(that);
            },
            layout: function () {
                var that = this.global();
            },
            havingGrid: {
                init: function (that) {
                    this.global = function () { return that; };

                    this.layout();
                    this.handlers.init(that);
                },
                layout: function () {
                    var that = this.global();

                    //right移动
                    $('#rightMoveBtn', that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();
                        var items = that.vars.havingGridVar.selectedRows();

                        if (items.length == 0) {
                            appui.message.info('请选择操作数据.');
                            return false;
                        }
                        that.hadGrid.handlers.save(items);
                        return false;
                    });

                    //定义列
                    var cols = [
                        { title:'用户编号', name:'staffNo' ,width:100, align:'center'},
                        { title:'用户名称', name:'staffName' ,width:100, align:'center'}
                    ];

                    var config = {
                        cols: cols,
                        url: 'role/staff/having/list.json',
                        params: {},
                        //loadingText: 'dd',
                        multiSelect: true,
                        checkCol: true,
                        indexCol: true,
                        nowrap :false, //内容不折行
                        plugins: [],
                        events:{},
                        customEvents: []
                    };

                    if (that.vars.roleItemVar) config.params['params[roleNo]'] = that.vars.roleItemVar['roleNo'];
                    
                    config.plugins.push( $('#havingGridGroupPage', that.selector).gridGroupPaginator());
                    config.plugins.push($('#havingGridGroupQuery', that.selector).gridGroupQuery({ queryBtn: $('#queryBtn', that.selector)}));

                    that.vars.havingGridVar = $('#havingGridGroup', that.selector).gridGroup(config);
                },
                handlers: {
                    init: function (that) {
                        this.global = function () { return that; };
                    }
                }
            },
            hadGrid: {
                init: function (that) {
                    this.global = function () { return that; };

                    this.layout();
                    this.handlers.init(that);
                },
                layout: function () {
                    var that = this.global();

                    //左移动
                    $('#leftMoveBtn', that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();
                        var items = that.vars.hadGridVar.selectedRows();

                        if (items.length == 0) {
                            appui.message.info('请选择操作数据.');
                            return false;
                        }

                        that.hadGrid.handlers.del(items);

                        return false;
                    });

                    //定义列
                    var cols = [
                        { title:'用户编号', name:'staffNo' ,width:100, align:'center'},
                        { title:'用户名称', name:'staffName' ,width:100, align:'center'}
                    ];

                    var config = {
                        cols: cols,
                        url: 'role/staff/had/list.json',
                        params: {},
                        //loadingText: 'dd',
                        multiSelect: true,
                        checkCol: true,
                        indexCol: true,
                        nowrap :false, //内容不折行
                        plugins: [],
                        events:{},
                        customEvents: []
                    };

                    if (that.vars.roleItemVar) config.params['params[roleNo]'] = that.vars.roleItemVar['roleNo'];

                    config.plugins.push( $('#hadGridGroupPage', that.selector).gridGroupPaginator());
                    config.plugins.push($('#hadGridGroupQuery', that.selector).gridGroupQuery({ queryBtn: $('#queryHadBtn', that.selector)}));

                    that.vars.hadGridVar = $('#hadGridGroup', that.selector).gridGroup(config);
                },
                handlers: {
                    init: function (that) {
                        this.global = function () { return that; };
                    },
                    save: function (items) {
                        var that = this.global();
                        var role = that.vars.roleItemVar;

                        var $rightBtn =  $('#rightMoveBtn', that.selector);

                        $rightBtn.disabled(true);
                        var sysStaffRoles = new Array();
                        for (var i = 0; i < items.length; i++) {
                        	var obj = new Object();
                            //sysStaffRoles[i].push({
                            obj.staffId=items[i]['id'];
                            obj.roleId=role['id'];
                            sysStaffRoles.push(obj);    
                            //});
                        }

                        $.ajax({
                            url: 'role/staff/had/save.json',
                            data: JSON.stringify(sysStaffRoles),//
                            type: 'POST',
                            contentType: 'application/json',
                            success: function (data) {
                                $rightBtn.disabled(false);
                                if (!data.success) {
                                    appui.message.error('操作失败.');
                                    return false;
                                }
                                that.vars.havingGridVar.load({pageNo: 1});
                                that.vars.hadGridVar.load({pageNo: 1});
                            }
                        });
                    },
                    del: function (items) {
                        var that = this.global();
                        var $leftBtn = $('#leftMoveBtn', that.selector);
                        var ids = [];
                        $leftBtn.disabled(true);
                        for (var i = 0; i < items.length; i ++) ids.push(items[i]['rid']);

                        $.ajax({
                            url: 'role/staff/had/del.json',
                            data: {ids: ids},
                            type: 'POST',
                            success: function (data) {
                                $leftBtn.disabled(false);
                                if (!data.success) {
                                    appui.message.error('操作失败.');
                                    return false;
                                }
                                that.vars.havingGridVar.load({pageNo: 1});
                                that.vars.hadGridVar.load({pageNo: 1});
                            }
                        });
                    }
                }
            }
        });
    </script>
</head>
<body>
    <div class="container-fluid otod-system-sysrole-staff-setting">
        <div class="col-xs-6 grid-container">
            <div class="page-header"><i class="fa fa-list-ul"></i> 未授权用户</div>
            <div class="row ui-querybars" id="havingGridGroupQuery">
                <div class="pull-left">
                    <div class="input-group input-group-sm query-group">
                        <input type="text" class="form-control" name="staffName" placeholder="用户名称"/>
                        <div class="input-group-btn">
                            <button class="btn btn-primary btn-sm" id="queryBtn"><i class="fa fa-search"></i> 查询</button>
                        </div>
                    </div>
                </div>
                <div class="pull-right">
                    <button class="btn btn-primary btn-sm" id="rightMoveBtn"><i class="fa fa-angle-double-right fa-lg"></i> 右移 <i class="fa fa-angle-double-right fa-lg"></i></button>
                </div>
            </div>
            <table id="havingGridGroup"></table>
            <div id="havingGridGroupPage"></div>
        </div>
        <div class="col-xs-6 grid-container">
            <div class="page-header"><i class="fa fa-list-ul"></i> 已授权用户</div>
            <div class="row ui-querybars">
                <div class="pull-left">
                    <button class="btn btn-primary btn-sm" id="leftMoveBtn"><i class="fa fa-angle-double-left fa-lg"></i> 左移 <i class="fa fa-angle-double-left fa-lg"></i></button>
                </div>
                <div class="pull-right query-group" id="hadGridGroupQuery">
                    <div class="input-group input-group-sm">
                        <input type="text" class="form-control" name="staffName" placeholder="用户名称"/>
                        <div class="input-group-btn">
                            <button class="btn btn-primary btn-sm" id="queryHadBtn"><i class="fa fa-search"></i> 查询</button>
                        </div>
                    </div>
                </div>
            </div>
            <table id="hadGridGroup"></table>
            <div id="hadGridGroupPage"></div>
        </div>
    </div>
</body>
</html>
