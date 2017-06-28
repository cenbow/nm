<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>子系统管理</title>
    <style type="text/css">
        .otod-system-syssub {}
    </style>
    <script type="text/javascript">
        $(function(){ app.init();});

        app.init('otod.system.syssub', {
            vars: {
                gridGroupVar: null //girdgroup
            },
            init:function(){
                var that = this;
                this.global = function () { return that;};

                //获取码表数据
                app.code('yon,availableStat');

                this.grid.init(that);
            },
            grid:{
                init:function(that){
                    this.global = function () { return that;};
                    this.layout();
                    this.handlers.init(that);
                },
                layout:function(){
                    var that = this.global();

                    $("#addBtn", that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();
                        that.grid.handlers.add();
                        return false;
                    });
                    
                    $("#delBtn", that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();
                        var items = that.vars.gridGroupVar.selectedRows();
                        if (items.length == 0) {
                            appui.message.info('请选择一条操作数据.');
                            return false;
                        }
                        that.grid.handlers.del(items);
                        return false;
                    });

                    var cols = [
                        { title:'子系统代码', name:'sysCod' ,width:100, align:'center' },
                        { title:'子系统名称', name:'sysName' ,width:100, align:'center' },
                        { title:'子系统地址', name:'sysIp' ,width:100, align:'center'},
                        { title:'状态', name:'isActived' ,width:100, align:'center',renderer:function(val,item,index){
                            return app.code.getText('availableStat', val, index);
                        }},
                        { title: '操作方式', name: 'id', align: 'center', width: '100', renderer: function (val, item, rowIndex) {
                        	return appui.fmt([
                                              '<a class="fa fa-edit todo-edit" href="javascript:;" > 修改</a>',
                                              '&nbsp;&nbsp;<a class="fa {cssDisabled} todo-activeOrNot" href="javascript:;"> {txtDisabled}</a>'
                                          ], {
                                              txtDisabled: item['isActived'] != '10002001' ? '启用' : '禁用',
                                              cssDisabled: item['isActived'] != '10002001' ? 'text-success fa-toggle-on' : 'text-danger fa-toggle-off'
                                          });
                        }}
                    ];
                    var config = {
                        cols: cols,
                        url: 'sub/list',
                        params: {},
                        multiSelect: true,
                        checkCol: true,
                        indexCol: true,
                        nowrap :false, //内容不折行
                        plugins: [],
                        events: {},
                        customEvents: []
                    };

                    //customevents
                    config.customEvents.push({
                        target: '.todo-edit',
                        data: that,
                        handler: function (event, item, rowIndex) {
                            event.preventDefault();
                            event.stopPropagation();

                            var that = event.data.refresh();
                            that.grid.handlers.edit(item);

                            return false;
                        }
                    });

                    //customevents
                    config.customEvents.push({
                        target: '.todo-del',
                        data: that,
                        handler: function (event, item, rowIndex) {
                            event.preventDefault();
                            event.stopPropagation();

                            var that = event.data.refresh();
                            that.grid.handlers.del(item);

                            return false;
                        }
                    });

                    //customevents
                    config.customEvents.push({
                        target: '.todo-activeOrNot',
                        data: that,
                        handler: function (event, item, rowIndex) {
                            event.preventDefault();
                            event.stopPropagation();

                            var that = event.data.refresh();
                            that.grid.handlers.activeOrNot(item);

                            return false;
                        }
                    });

                    that.vars.gridGroupVar = $('#gridGroup', that.selector).gridGroup(config);
                },
                handlers: {
                    init: function (that) {
                        this.global = function () { return that;};
                    },
                    add: function () {
                        var that = this.global();

                        appui.modal.open({
                            title: '添加子系统',
                            url: 'sub/form.htm',
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
                                    that.vars.gridGroupVar.load({pageNo: 1});
                                }
                            }
                        });

                        return false;
                    },
                    edit: function (item) {
                        var that = this.global();
                        appui.modal.open({
                            title: '修改子系统',
                            url: 'sub/form.htm',
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

                                    that.vars.gridGroupVar.load({pageNo: 1});
                                }
                            }
                        });

                        return false;
                    },
                    del: function (items) {
                        var that = this.global();
                        appui.dialog.confirm({
    		                size: "modal-small",
    		                title: "消息提示", //optional
    		                message: "确定删除",//required
    		                callback: function(r){
    		                	if (r) {
    		                		var ids='';
    		                       	for(var i=0;i<items.length;i++){
    		                       		ids+=items[i].id+',';
    		                       	}
    		                       	ids=ids.substring(0,ids.length-1);
    		                        var data = {
    		                            id: ids
    		                            //isActived : item['isActived'] == '10002001' ? '10002002' : '10002001'
    		                        };

    		                        $.ajax({
    		                            url: 'sub/del.json',
    		                            type: 'POST',
    		                            data: data,
    		                            success: function (data) {
    		                                if (!data.success){
    		                                    appui.message.error(data.msg);
    		                                    return;
    		                                }

    		                                appui.message.success('操作成功.');

    		                                that.vars.gridGroupVar.load({pageNo: 1});
    		                            }
    		                        });
    		                	};
    		                } 
    		            });
                        return false;
                    },
                    activeOrNot: function (item) {
                        var that = this.global();
                        var data = {
                            id: item.id,
                            isActived : item.isActived == '10002001' ? '10002002' : '10002001'
                        };

                        $.ajax({
                            url: 'sub/activeOrNot.json',
                            type: 'POST',
                            data: data,
                            success: function (data) {
                                if (!data.success){
                                    appui.message.error(data.msg);
                                    return;
                                }

                                appui.message.success('操作成功.');

                                that.vars.gridGroupVar.load({pageNo: 1});
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
<div class="container-fluid otod-system-syssub">
    <h3 class="page-header"><i class="fa fa-th-large"></i> 子系统列表</h3>
    <div class="row ui-toolbars">
        <button type="button" class="btn btn-success btn-sm" id="addBtn"><i class="fa fa-plus"></i> 添加</button>
        <button type="button" class="btn btn-warning btn-sm" id="delBtn"><i class="fa fa-remove"></i> 删除</button>
    </div>
    <table id="gridGroup"></table>
</div>
</body>
</html>