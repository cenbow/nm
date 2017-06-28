<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>销售群管理</title>
    <style type="text/css">
        .otod-system-sale-pubsalecrowd {}
        .otod-system-sale-pubsalecrowd .query-group {width: 300px;}
    </style>
    <script type="text/javascript">
        $(function () {
            app.init();
        });

        app.init('otod.system.sale.pubsalecrowd', {
            vars: {
                saleCrowdGridVar: false
            },
            init: function () {
                var that = this;
                this.global = function () { return that;};
                this.layout();
                this.saleCrowdGrid.init(that);
            },
            layout: function () {
            },
            saleCrowdGrid: {
                init: function (that) {
                    this.global = function () { return that;};
                    this.layout();
                    this.handlers.init(that);
                },
                layout: function () {
                    var that = this.global();

                    $("#addBtn", that.selector).bind('click', that, function (event) {
                        event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();
                        that.saleCrowdGrid.handlers.add();
                        return false;
                    });

                    var cols = [
                        {title:'群名称',name: 'crowdName',align: 'center', width: '100'},
		                { title: '群规则', name: 'crowdRule', align: 'center', width: '100'},
                        { title: '操作', name: 'id', align: 'center', width: '100', renderer: function (val, item, rowIndex) {
                            return '<a class="fa fa-edit edit-type" href="javascript:;" > 修改</a>&nbsp;&nbsp;'+
                            '<a class="fa fa-remove remove-type text-danger" href="javascript:;"> 删除</a>&nbsp;&nbsp;'+
                            '<a class="fa fa-play plus-type" href="javascript:;"> 查看销售</a>';
                        }}
                    ];

                    var config = {
                        cols: cols,
                        url: 'sale/crowd/list.json',
                        checkCol: false,
                        indexCol: true,
                        plugins:[],
                        events: {},
                        customEvents: [],
                        nowrap:false
                    };

                    config.plugins.push($("#saleCrowdGridQuery", that.selector).gridGroupQuery({ queryBtn: $('#saleCrowdGridQuery #queryBtn', that.selector)}));
                    config.plugins.push($("#saleCrowdGridPage", that.selector).gridGroupPaginator());

                    //events
                    config.events.cellClick = {
                        data: that,
                        handler: function (event, item, isSelected, rowIndex, colIndex) {
                            event.preventDefault();
                            event.stopPropagation();
                            var that = event.data.refresh();
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
                            that.saleCrowdGrid.handlers.edit(item);

                            return false;
                        }
                    });

                    //customevents
                    config.customEvents.push({
                        target: '.remove-type',
                        data: that,
                        handler: function (event, item, rowIndex) {
                            event.preventDefault();
                            event.stopPropagation();

                            var that = event.data.refresh();
                            that.saleCrowdGrid.handlers.del(item);
                            return false;
                        }
                    });

                    //customevents
                    config.customEvents.push({
                        target: '.plus-type',
                        data: that,
                        handler: function (event, item, rowIndex) {
                            event.preventDefault();
                            event.stopPropagation();

                            var that = event.data.refresh();
                            that.saleCrowdGrid.handlers.rule(item);
                            return false;
                        }
                    });

                    that.vars.saleCrowdGridVar = $("#saleCrowdGrid", that.selector).gridGroup(config);
                },
                handlers: {
                    init: function (that){
                        this.global = function () { return that;}
                    },
                    rule: function(item){
                    	var that = this.global();
                        appui.modal.open({
                            title: '查看规则',
                            url: 'sale/crowd/rule/index.htm',
                            cssModal: 'modal-lg',
                            styleBody: 'padding-left:0; padding-right:0',
                            param: {
                                that: that,
                                item:item
                            },
                            callback: {
                            }
                        });
                        return false;
                    },
                    add: function () {
                        var that = this.global();
                        appui.modal.open({
                            title: '添加销售群',
                            url: 'sale/crowd/form.htm',
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
                                    that.vars.saleCrowdGridVar.load({pageNo: 1});
                                }
                            }
                        });
                        return false;
                    },
                    edit: function (item) {
                        var that = this.global();

                        appui.modal.open({
                            title: '修改销售群',
                            url: 'sale/crowd/form.htm',
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
                                    //saleCrowdGrid
                                    that.vars.saleCrowdGridVar.load({pageNo: 1});
                                }
                            }
                        });

                        return false;
                    },
                    del: function (item) {
                        var that = this.global();
                        var data = {
                        	crowdNo: item['crowdNo']
                        };

                        appui.dialog.confirm({
    		                size: "modal-small",
    		                title: "消息提示", //optional
    		                message: "是否删除",//required
    		                callback: function(r){
    		                	if (!r) return false;
                                $.ajax({
                                    url: "sale/crowd/del.json",
                                    type:"POST",
                                    data: data,
                                    success: function(){
                                        appui.message.success('删除成功');
                                        that.vars.saleCrowdGridVar.load({pageNo: 1});
                                    }
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
<div class="container-fluid otod-system-sale-pubsalecrowd">
        <h3 class="page-header"><i class="fa fa-th-large"></i> 销售群</h3>
        <!--saleCrowdGrid toolbars-->
        <div class="row ui-toolbars typeToolBars">
            <!--saleCrowdGrid querybars-->
            <div class="pull-right" id="saleCrowdGridQuery">
                <div class="control-group query-group">
                    <div class="input-group input-group-sm">
                        <input id="crowdName" class="form-control" type="text" name="crowdName" placeholder="群名"/>
                        <div class="input-group-btn">
                            <button class="btn btn-primary btn-sm" id="queryBtn"><i class="fa fa-search"></i> 查询</button>
                            <button type="button" class="btn btn-success btn-sm" id="addBtn"><i class="fa fa-plus"></i> 添加</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--saleCrowdGrid-->
        <table id="saleCrowdGrid"></table>
        <div id="saleCrowdGridPage"></div>
</div>
</body>
</html>
