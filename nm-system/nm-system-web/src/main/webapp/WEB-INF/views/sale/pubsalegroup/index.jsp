<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>销售列表</title>
	<style type="text/css">
		.otod-system-sale-pubsalergroup {}
		.otod-system-sale-pubsalergroup .col-group-container {padding-left: 0; }
		.otod-system-sale-pubsalergroup .col-group-container:last-child {padding-right: 0; }
		.otod-system-sale-pubsalergroup .query-group {width: 200px;}
	</style>
	<script type="text/javascript">
		$(function () { app.init(); });
		
		app.init('otod.system.sale.pubsalergroup', {
			vars : {
				groupGridVar : false,
				personGridVar: false,
				groupNameEmpty:'$empty$'
			},
			init : function(){
				var that = this;
                this.global = function () { return that;};
                app.code('availableStat');

                that.layout(that);
                that.groupGrid.init(that);
                that.personGrid.init(that);
			},
			layout: function (that) {
				$("input[name=groupNo]", that.selector).val(that.vars.groupNameEmpty);
            },
            groupGrid : {
            	 init: function (that) {
                     this.global = function () { return that;};
                     this.layout();
                     this.handlers.init(that);
                 },
                 layout : function(){
                	 var that = this.global();
                	 
                	 $("#addGroupBtn", that.selector).bind('click', that, function(event){
                		 event.preventDefault();
                         event.stopPropagation();
                         var that = event.data.refresh();
                         that.groupGrid.handlers.add();
                         return false;
                	 });
                	 
                	 var cols=[
				   		/* {title:'组号',name:'groupNo',align: 'center', width: '150'},*/
						 {title:'组名',name:'groupName',align: 'center', width: '150'},
						 { title: '码类状态', name: 'stat', align: 'center', width: '100', renderer: function (val, item, rowIndex) {
							 return app.code.getText('availableStat', val, rowIndex);
						 }},
						 {title:'操作',name:'id',align: 'center', width: '50',renderer:function(val, item, rowIndex){
							 return '<a class="fa fa-edit edit-type" href="javascript:;" > 修改</a>&nbsp;&nbsp;<a class="fa fa-remove remove-type text-danger" href="javascript:;"> 删除</a>';
						 }}
					 ];
                	 var config = {
                             cols: cols,
                             url: 'saler/group/list.json',
                             checkCol: true,
                             indexCol: true,
                             plugins:[],
                             events: {},
                             customEvents: []
                         };
                	 
                	 config.plugins.push($("#saleGroupGridQuery",that.selector).gridGroupQuery({queryBtn : $("#saleGroupGridQuery #queryBtn",that.selector)}));
                	 config.plugins.push($("#saleGroupGridPage",that.selector).gridGroupPaginator());
                	 
                	//events
                     config.events.cellClick = {
                         data: that,
                         handler: function (event, item, isSelected, rowIndex, colIndex) {
                             event.preventDefault();
                             event.stopPropagation();

                             var that = event.data.refresh();
                             $("#salePersonGridQuery input[name=groupNo]", that.selector).val(item['groupNo']);
                             that.vars.personGridVar.load({pageNo: 1});

                             return false;
                         }
                     };
                	 config.customEvents.push({
                		 target: '.edit-type',
                		 data: that,
                		 handler: function (event, item, rowIndex){
                			 event.preventDefault();
                             event.stopPropagation();
                             var that = event.data.refresh();
                             
                             that.groupGrid.handlers.edit(item);
                             
                			 return false;
                		 }
                	 });
                	 
                	 config.customEvents.push({
                		 target: '.remove-type',
                		 data: that,
                		 handler: function (event, item, rowIndex){
                			 event.preventDefault();
                             event.stopPropagation();
                             var that = event.data.refresh();
                             
                             that.groupGrid.handlers.remove(item);
                             
                			 return false;
                		 }
                	 });
                	 
                	 that.vars.groupGridVar = $('#saleGroupGrid', that.selector).gridGroup(config);
                	 
                 },
                 handlers : {
                	 init: function (that){
                         this.global = function () { return that;}
                     },
                     add : function(){
                    	 var that = this.global();
                    	 appui.modal.open({
                    		 title : "添加销售分组",
                    		 url : "saler/group/form.htm",
                    		 cssModal: 'modal-md',
                    		 param: {
                                 that: that
                             },
                             callback : {
                            	 hidden: function (options) {
                                     var param = options.param;
                                     var that = param.that;
                                     var data = options.data;
                                     if (!data.closed) return;

                                     appui.message.success('操作成功.');
                                     
                                     that.vars.groupGridVar.load({pageNo: 1});

                                 }
                             }
                    	 });
                     },
                     edit : function(item){
                    	 var that = this.global();
                    	 appui.modal.open({
                    		 title : "修改销售分组",
                    		 url : "saler/group/form.htm",
                    		 cssModal: 'modal-md',
                    		 param: {
                                 that: that,
                                 item: item
                             },
                             callback : {
                            	 hidden: function (options) {
                                     var param = options.param;
                                     var that = param.that;
                                     var data = options.data;
                                     if (!data.closed) return;

                                     appui.message.success('操作成功.');
                                     
                                     that.vars.groupGridVar.load({pageNo: 1});

                                 }
                             }
                    	 });
                     },
                     remove : function(item){
                    	 var that = this.global();
          				appui.dialog.confirm({
        	                size: "modal-small",
        	                title: "消息提示", //optional
        	                message: "是否删除",//required
        	                callback: function(r){
        	                	if (r){
        	                    	 $.ajax({
        	                    		 url : 'saler/group/del.json',
        	                    		 type: 'POST',
        	                    		 data:JSON.stringify(item),
        	                    		 contentType: 'application/json',
        	                    		 success: function (data) {
        	                    			 that.vars.groupGridVar.load({pageNo: 1});
        	                    			 that.vars.personGridVar.load({pageNo: 1});
        	                    		 }
        	                    	 });
        	                	}
        	                } 
        	            });


                    	 
                     }
                 }
            },
            
            personGrid : {
            	init: function (that) {
                    this.global = function () { return that;};
                    this.layout();
                    this.handlers.init(that);
                },
                
                layout : function(){
                	var that = this.global();
               	 
					 $("#addPersonBtn", that.selector).bind('click', that, function(event){
						event.preventDefault();
						event.stopPropagation();
						var that = event.data.refresh();
						var items = that.vars.groupGridVar.selectedRows();
						if (items.length != 1) return  appui.message.warning('请选一条销售组数据.') || false;

						that.personGrid.handlers.add();
						return false;
					 });

					$("#delPersonBtn", that.selector).bind('click', that, function(event){
						event.preventDefault();
						event.stopPropagation();
						var that = event.data.refresh();
						var items = that.vars.personGridVar.selectedRows();
						if (items.length == 0) return appui.message.warning('请选至少一条销售人员数据.') || false;
						that.personGrid.handlers.remove(items);
						return false;
					});
               	 
               	var cols=[
					{title:'销售号',name:'staffNo',align: 'center', width: '150'},
					{title:'姓名',name:'staffName',align: 'center', width: '150'},
					{title:'操作',name:'id',align: 'center', width: '50',renderer:function(val, item, rowIndex){
						return '<a class="fa fa-remove remove-type text-danger" href="javascript:;"> 删除</a>';
					}}
			    ];
               	 
               	var config = {
                        cols: cols,
                        url: 'saler/group/staff/had/list.json',
						multiSelect: true,
                        checkCol: true,
                        indexCol: true,
                        plugins:[],
                        events: {},
                        customEvents: []
                    };
               	
               	config.customEvents.push({
           		 	target: '.remove-type',
           		 	data: that,
           		 	handler: function (event, item, rowIndex){
           			 	event.preventDefault();
                        event.stopPropagation();
                        var that = event.data.refresh();
                        
                        that.personGrid.handlers.remove([item]);
                        
           			 	return false;
           		 	}
           	 	});
               	
                config.plugins.push($("#salePersonGridQuery",that.selector).gridGroupQuery());
           	 	config.plugins.push($("#salePersonGridPage",that.selector).gridGroupPaginator());
           	 	
           	 	that.vars.personGridVar = $('#salePersonGrid', that.selector).gridGroup(config);
//                	 that.vars.per.load({pageNo:1})
                },
                
                handlers : {
                	init: function (that){
                        this.global = function () { return that;}
                    },
                    add : function(){

                   		var that = this.global();
                   		
                   		var item = {
                 			params: {
                 				roleNo: PubBusinessConstant.ROLE_R_SALE_STAFF
                 			}	
                   		};
                   		
                   	 	appui.modal.open({
                   			title : "添加销售",
                   		 	url : "pub/staff/index.htm",
                   		 	cssModal: 'modal-lg',
							styleBody: 'padding-left: 0; padding-right: 0;',
                   		 	param: {
                   		 		that: that,
                            	item: item
                            },
                            callback : {
                           		hidden: function (options) {
                                    var param = options.param;
                                    var that = param.that;
                                    var dataVar = options.data;
                                    if (!dataVar.closed) return false;
                                    delete dataVar.closed;
                                    
                                    var data=[];
                                    var groupNo = that.selector.find("input[name=groupNo]").val();
                                    data.push('groupNo=' + groupNo);
                                    $.each(options.data, function(i, item) {
                                    	data.push('staffNos[' + i + ']=' + item.staffNo)
                                    });
                                    $.ajax({
                               		 url : 'saler/group/staff/had/save',
                               		 type: 'POST',
                              		 data: data.join('&'),
                               		 success: function (data) {
                                            if (!data.success){
                                                appui.message.error(data.msg);
                                                return;
                                            } 
                                            
                                            appui.modal.close(data);
                                            appui.message.success('操作成功.');
                                            //typeGrid
                                            that.vars.personGridVar.load({pageNo: 1});
                                        }
                                    
                               	 	});
                                    if (!data.closed) return;


                                }
                            }
                   	 }); 
                    },
                    remove : function(items){
                    	var that = this.global();
						var groupNo = $("input[name=groupNo]",that.selector).val();
						var data = {
							groupNo: groupNo,
							staffNos: []
						};

						for (var i=0; i<items.length; i++){
							data.staffNos.push(items[i].staffNo);
						}

                    	$.ajax({
                    		url : 'saler/group/staff/had/del.json',
                    		data: data,
                    		type:'POST',
                    		success: function(data){
                    			appui.message.success('操作成功.');
                    			that.vars.personGridVar.load({pageNo: 1});
                    		}
                    	});
                    }
                }
                
            }
		});
	</script>
</head>
<body>
	<div class="container-fluid otod-system-sale-pubsalergroup">
		<div class="col-xs-6 col-group-container">
			<h3 class="page-header"><i class="fa fa-th-large"></i> 销售组列表</h3>
			<div class="row ui-toolbars">
				<div class='pull-left'>
					<button type="button" class="btn btn-success btn-sm" id="addGroupBtn"><i class="fa fa-plus"></i> 添加</button>
				</div>
				<div class="pull-right" id="saleGroupGridQuery">
               		<div class="control-group query-group">
                   		<div class="input-group input-group-sm">
                       		<input id="groupName" class="form-control" type="text" name="groupName" placeholder="组名"/>
                       		<div class="input-group-btn">
                           		<button class="btn btn-primary btn-sm" id="queryBtn"><i class="fa fa-search"></i> 查询</button>
                       		</div>
                   		</div>
               		</div>
            	</div>
			</div>
			<table id="saleGroupGrid"></table>
        	<div id="saleGroupGridPage"></div>
		</div>
		
		<div class="col-xs-6 col-group-container">
			<h3 class="page-header"><i class="fa fa-th-large"></i> 销售人员列表</h3>
			<div class="row ui-toolbars">
				<button class="btn btn-success btn-sm" id="addPersonBtn"><i class="fa fa-plus"></i> 添加</button>
				<button class="btn btn-danger btn-sm" id="delPersonBtn"><i class="fa fa-remove"></i> 删除</button>
			</div>
			
			<div id="salePersonGridQuery">
            	<input type="hidden" name="groupNo" value=""/>
       	 	</div>
       	 	
			<table id="salePersonGrid"></table>
        	<div id="salePersonGridPage"></div>
		</div>
		
	</div>
</body>
</html>