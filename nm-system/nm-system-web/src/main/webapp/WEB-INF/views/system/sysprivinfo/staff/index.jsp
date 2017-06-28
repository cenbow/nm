<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>员工</title>
		<style type="text/css">
			.otod-system-sysprivinfo-staff {}
		</style>
		<script type="text/javascript">
			$(function(){ app.init(); });
			app.init('otod.system.sysprivinfo.staff', {
				vars:{
					gridGroupVar: false,
					param: false
				},
				init:function(){
					var that = this;
					this.global = function () { return that;};
					app.code('availableStat, autType');
					
					if (typeof that.modal != 'undefined') that.vars.param = that.modal.param;
					
					this.grid.init(that);
				},
				layout: function () {
				},
				grid:{
					init: function (that) {
						this.global = function () { return that;};
						this.layout();
						this.handlers.init(that);
					},
					layout: function () {
						var that = this.global();

						$(".inputGroup", that.selector).inputGroup({
							cssCol: 'col-xs-4'
						});
						$(".staffStatSelect", that.selector).selectGroup({
							cssCol: 'col-xs-4',
							code:{
								type:'availableStat'
							}
						});
						$(".userTypSelect", that.selector).selectGroup({
							cssCol: 'col-xs-4',
							code:{
								type:'autType'
							}
						});

						/*选择功能 */
						$("#saveBtn",that.selector).bind("click",that,function(event){
							event.preventDefault();
							event.stopPropagation();
							var that = event.data.refresh();
							var items = that.vars.gridGroupVar.selectedRows();
							if (items.length == 0) return appui.message.error("请选择至少一条操作数据！") || false;

							that.grid.handlers.save(items);
							return false;
						});

						var cols = [
							{ title:'员工编号', name:'staffNo' ,width:100, align:'center'},
							{ title:'员工名称', name:'staffName' ,width:100, align:'center'},
							{ title:'员工职位', name:'staffPosition' ,width:100, align:'center'},
							{ title:'员工类型', name:'userTyp' ,width:100, align:'center',renderer:function(val,item,index){
								return app.code.getText("autType",val,index);
							}},
							{ title:'员工状态', name:'staffStat' ,width:100, align:'center',renderer:function(val,item,index){
								return app.code.getText("availableStat",val,index);
							} }
						];

						var config = {
							height: "200px",
							cols: cols,
							url: 'priv/info/staff/list.json',
							params: {
							},
							multiSelect: false,
							checkCol: true,
							indexCol: true,
							nowrap :false, //内容不折行
							plugins: [],
							events: {},
							customEvents: []
						};
						if (that.vars.param) config.multiSelect = that.vars.param.multiSelect;
						
						
						config.plugins.push($("#gridPageGroup", that.selector).gridGroupPaginator());
						config.plugins.push($("#gridQueryGroup", that.selector).gridGroupQuery({queryBtn: $("#queryBtn", that.selector), resetBtn: $("#resetBtn", that.selector)}));

						that.vars.gridGroupVar = $('#gridGroup', that.selector).gridGroup(config);

					},
					handlers: {
						init: function (that) {
							this.global = function () { return that;};
						},
						save: function (items) {
							var labels = [];
							var values = [];
							for(var i=0 ;i < items.length; i++){
								labels.push(items[i].staffName);
								values.push(items[i].staffNo);
							}
							appui.modal.close({
								label: labels.join(';'),
								value: values.join(';')
							});
							return false;
						}
					}
				}
			});
		</script>
	</head>
	<body>
		<div class="container-fluid otod-system-sysprivinfo-staff">
			<!--查询条件  -->
			<div class="ui-querybars row" id="gridQueryGroup">
				<input type="text" class="inputGroup" name="staffNo" options='{txtPrefix: "员工编号"}' />
				<input type="text" class="inputGroup" name="staffName" options='{txtPrefix: "员工名称"}' />
				<input type="text" class="staffStatSelect" name="staffStat" options='{txtPrefix: "员工状态"}' />
				<input type="text" class="inputGroup" name="staffPosition" options='{txtPrefix: "员工职位"}' />
				<input type="text" class="userTypSelect" name="userTyp" options='{txtPrefix: "员工类型"}' />
			</div>

			<!--按钮  -->
			<div class="ui-toolbars row">
				<div class="pull-left">
					<button class="btn btn-primary btn-sm" id="saveBtn"><i class="fa fa-check"></i> 确定</button>
				</div>
				<div class="pull-right">
					<button class="btn btn-primary btn-sm" id="queryBtn"><i class="fa fa-search"></i> 查询</button>
					<button class="btn btn-danger btn-sm" id="resetBtn"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
				</div>
			</div>

			<!--grid  -->
			<table id="gridGroup"></table>
			<div id="gridPageGroup"></div>
		</div>
	</body>
</html>