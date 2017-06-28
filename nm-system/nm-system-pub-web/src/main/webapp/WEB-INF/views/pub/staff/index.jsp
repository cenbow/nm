<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>销售列表</title>
	<style type="text/css">
		.otod-system-pub-staff {}
		.otod-system-pub-staff .query-group {width: 300px;}
	</style>
	<script type="text/javascript">
		$(function () { 
			app.init(); 
		});
		
		app.init('otod.system.pub.staff', {
			vars : {
				staffGridVar : false,
				itemVar : {
					indexCol: true, //是否显示序列
					multi: false, //是否多选
					checkCol: true,//是否显示checkbox
					confirmBtn: true //是否显示确定按钮
				}
			},
			init : function(){
				var that = this;
                this.global = function () { return that;};

                if (typeof that.modal != 'undefined') that.vars.itemVar = $.extend(that.vars.itemVar, that.modal.param['item']);
                that.layout(that);
                
                that.staffGrid.init(that);
			},
			layout: function () {
				var that = this.global();
				var item = that.vars.itemVar;
				that.selector.find('.confirmBtn').bind('click', that, function (event) {
					event.preventDefault();
                    event.stopPropagation();
                   	var that = event.data.refresh();
                   	that.staffGrid.handlers.confirm();
                    return false;
				});
            },
            staffGrid : {
            	 init: function (that) {
                     this.global = function () { return that;};
                     this.layout();
                     this.handlers.init(that);
                 },
                 layout : function(){
                	 var that = this.global();
                	 
                	 var cols=[
              	           {title:'人员编号',name:'staffNo',align: 'center', width: '150'},
              	           {title:'人员名称',name:'staffName',align: 'center', width: '150'},
            	           ];
                	 var config = {
                           cols: cols,
                           url: 'pub/staff/list.json',
                           checkCol: true,
                           multiSelect:true,
                           indexCol: true,
                           plugins:[],
                           events: {},
                           customEvents: []
                       };
                	 
                	 config = $.extend(true, config, that.vars.itemVar);
                	 
                	 config.plugins.push($("#saleStaffGridQuery",that.selector).gridGroupQuery({queryBtn : $("#saleStaffGridQuery #queryBtn",that.selector)}));
                	 config.plugins.push($("#staffGridPage",that.selector).gridGroupPaginator());
                	 
                	 that.vars.staffGridVar = $('#staffGrid', that.selector).gridGroup(config);
                	 
                 },
                 handlers : {
                	 init: function (that){
                         this.global = function () { return that;}
                     },
                     confirm: function () {
                    	 var that = this.global();
                    	 var items = that.vars.staffGridVar.selectedRows();
                    	 if (!items || items.length == 0) {
                    		 appui.message.error('请选择操作数据.');
                    		 return false;
                    	 }
                    	 appui.modal.close(items);
                     }
                 }
            }
            
		});
	</script>
</head>
<body>
	<div class="container-fluid otod-system-pub-staff">
		<div class="row ui-toolbars">
			<div class="pull-right" id="saleStaffGridQuery">
				<input type="hidden" name="groupNo" />
				<div class="control-group query-group">
					<div class="input-group input-group-sm">
						<input id="staffName" class="form-control" type="text" name="staffName" placeholder="人员名称"/>
						<div class="input-group-btn">
							<button class="btn btn-info btn-sm" id="queryBtn"><i class="fa fa-search"></i> 查询</button>
							<button type="button" class="btn btn-primary btn-sm confirmBtn"><i class="fa fa-check"></i> 确定</button>
						</div>
					</div>
				</div>
			</div>

		</div>
		<table id="staffGrid"></table>
		<div id="staffGridPage"></div>
	</div>
		
</body>
</html>