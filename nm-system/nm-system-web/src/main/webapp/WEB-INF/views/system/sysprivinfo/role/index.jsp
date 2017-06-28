<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>角色</title>
		<style type="text/css">
			.otod-system-sysprivinfo-role {}
		</style>
		<script type="text/javascript">
			$(function(){ app.init(); });

			app.init('otod.system.sysprivinfo.role', {
				vars:{
					gridGroupVar: false,
					param: false
				},
				init:function(){
					var that = this;
					this.global = function () { return that;};
					app.code('availableStat');

					if (typeof that.modal != 'undefined') that.vars.param = that.modal.param;

					this.layout();
					this.grid.init(that);
				},
				layout: function() {
					var that = this.global();
				},
				grid: {
					init:function(that){
						this.global = function () { return that;};
						this.layout();
						this.handlers.init(that);
					},
					layout:function(){
						var that = this.global();
						$(".inputGroup", that.selector).inputGroup({
							cssCol: 'col-xs-4'
						});
						$(".roleStatSelect", that.selector).selectGroup({////////////////code
							cssCol: 'col-xs-4',
							code:{
								type:'availableStat'
							}
						});
						$("#resetBtn", that.selector).bind("click",that,function(event){
							event.preventDefault();
							event.stopPropagation();
							var that = event.data.refresh();
							$("input[name=roleName], input[name=roleNo], input[name=roleStat]", that.selector).valChange("");
							return false;
						});

						/*选择功能 */
						$("#saveBtn", that.selector).bind("click", that, function (event) {
							event.preventDefault();
							event.stopPropagation();
							var that = event.data.refresh();
							var items = that.vars.gridGroupVar.selectedRows();
							if (items.length == 0) return appui.message.error("请选择至少一条操作数据！") || false;

							that.grid.handlers.save(items);
							return false;
						});

						var cols = [
							{ title:'角色编号', name:'roleNo' ,width:100, align:'center'},
							{ title:'角色名称', name:'roleName' ,width:100, align:'center'},
							{ title:'角色状态', name:'roleStat' ,width:100, align:'center',renderer:function(val,item,index){
								return app.code.getText("availableStat",val,index);
							}}
						];
						var config = {
							height: "200px",
							cols: cols,
							url: 'priv/info/role/list.json',
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
								labels.push(items[i].roleName);
								values.push(items[i].roleNo);
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
		<div class="container-fluid  otod-system-sysprivinfo-role">
			<!--查询条件  -->
			<div class="ui-querybars row" id="gridQueryGroup">
				<input type="text" class="inputGroup" name="roleNo" options='{txtPrefix: "角色编号"}' />
				<input type="text" class="inputGroup" name="roleName" options='{txtPrefix: "角色名称"}' />
				<input type="text" class="roleStatSelect" name="roleStat" options='{txtPrefix: "角色状态"}' />
			</div>

			<!--按钮  -->
			<div class="ui-toolbars row">
				<div class="pull-left">
					<button class="btn btn-primary btn-sm" id="queryBtn"><i class="fa fa-search"></i> 查询</button>
					<button class="btn btn-danger btn-sm" id="resetBtn"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
				</div>
				<div class="pull-right">
					<button class="btn btn-primary btn-sm" id="saveBtn"><i class="fa fa-check"></i> 确定</button>
				</div>
			</div>

			<!--grid  -->
			<table id="gridGroup"></table>
			<div id="gridPageGroup"></div>

		</div>
	</body>
</html>