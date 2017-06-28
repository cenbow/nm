<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>用户管理</title>
    <style type="text/css">
        .otod-system-sysstaff-role-setting{}
        .otod-system-sysstaff-role-setting .page-header {font-size: 16px; margin-top: 5px; margin-bottom: 10px;}
        .otod-system-sysstaff-role-setting .grid-container {padding-left: 2px; padding-right: 2px;}
    </style>
    <script type="text/javascript">
        $(function() { app.init(); });

        app.init('otod.system.sysstaff.role.setting', {
            vars: {
                havingGridVar: false,
                hadGridVar: false,
                staffItemVar: false
            },
            init: function () {
                var that = this;
                this.global = function () { return that; };

                if (typeof that.modal != 'undefined') that.vars.staffItemVar = that.modal.param.item;

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

                    //右移动
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
                        //再执行一次查询，保证数据是最新的数据
                        //$("#queryBtn", that.selector).click();
                        that.vars.havingGridVar.load({
                        	pageNo: 1
                        })
                        
                        //右移之后取消全选框
                        $("input:checked").removeAttr("checked");
                        return false;
                    });

                    //定义列
                    var cols = [
                        { title:'角色编号', name:'roleNo' ,width:100, align:'center'},
                        { title:'角色名称', name:'roleName' ,width:100, align:'center'}
                    ];

                    var config = {
                        cols: cols,
                        url: 'staff/role/having/list.json',
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

                    if (that.vars.staffItemVar) config.params['params[staffNo]'] = that.vars.staffItemVar['staffNo'];

                    config.plugins.push( $('#havingGridGroupPage', that.selector).gridGroupPaginator());
                    config.plugins.push($("#havingGridGroupQuery", that.selector).gridGroupQuery({queryBtn: $("#queryBtn", that.selector)}));
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
						//左移之后取消全选框
                        $("input:checked").removeAttr("checked");
                        return false;
                    });

                    //定义列
                    var cols = [
                        { title:'角色编号', name:'roleNo' ,width:100, align:'center'},
                        { title:'角色名称', name:'roleName' ,width:100, align:'center'}
                    ];

                    var config = {
                        cols: cols,
                        url: 'staff/role/had/list.json',
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

                    if (that.vars.staffItemVar) config.params['staffNo'] = that.vars.staffItemVar['staffNo'];

                    that.vars.hadGridVar = $('#hadGridGroup', that.selector).gridGroup(config);
                },
                handlers: {
                    init: function (that) {
                        this.global = function () { return that; };
                    },
                    save: function (items) {
                        var that = this.global();
                        var staff = that.vars.staffItemVar;


                        var $rightBtn =  $('#rightMoveBtn', that.selector);

                        $rightBtn.disabled(true);
                        var data = [];
                        for (var i = 0; i < items.length; i++) {
                            data.push({
                                staffId: staff['id'],
                                roleId: items[i]['id']
                            });
                        }

                        $.ajax({
                            url: 'staff/role/had/save.json',
                            data: JSON.stringify(data),
                            type: 'POST',
                            contentType: 'application/json',
                            success: function (data) {
                                $rightBtn.disabled(false);
                                if (!data.success) {
                                    appui.message.error('操作失败.');
                                    return false;
                                }
                                that.vars.havingGridVar.load({pageNo: 1});
                                that.vars.hadGridVar.load({pageNo: 1});;
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
                            url: 'staff/role/had/del.json',
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
    <div class="container-fluid otod-system-sysstaff-role-setting">
        <div class="col-xs-6 grid-container">
            <div class="page-header"><i class="fa fa-list-ul"></i> 未拥有角色</div>
            <div class="row ui-querybars query-group" id="havingGridGroupQuery">
                <div class="pull-left" style="width:300px" >
                    <div class="input-group input-group-sm">
                        <input type="text" class="form-control" name="roleName" placeholder="角色名称"/>
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
            <div class="page-header"><i class="fa fa-list-ul"></i> 已拥有角色</div>
            <div class="row ui-querybars">
                <div class="pull-left">
                    <button class="btn btn-primary btn-sm" id="leftMoveBtn"><i class="fa fa-angle-double-left fa-lg"></i> 左移 <i class="fa fa-angle-double-left fa-lg"></i></button>
                </div>
                <div class="pull-right">
                </div>
            </div>
            <table id="hadGridGroup"></table>
        </div>
    </div>
</body>
</html>
