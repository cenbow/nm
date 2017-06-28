<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>特殊权限管理</title>
		<style type="text/css">
			.otod-system-sysprivinfo {}
		</style>
		<script type="text/javascript">
			$(function(){ app.init(); });

			app.init('otod.system.sysprivinfo', {
				vars:{
					gridGroupVar: false
				},
				init:function(){
					var that = this;
					this.global = function () { return that;};
					app.code('busiFunc, autType, orgAttr ,orgMatchTyp');
					this.grid.init(that);
				},
				grid: {
					init: function (that) {
						this.global = function () {
							return that;
						};

						this.layout(that);
						this.handlers.init(that);
					},
					layout: function (that) {
						$("#addBtn", that.selector).bind('click', that, function (event) {
							event.preventDefault();
							event.stopPropagation();
							var that = event.data.refresh();
							that.grid.handlers.add();
							return false;
						});

						$("#editBtn", that.selector).bind('click', that, function (event) {
							event.preventDefault();
							event.stopPropagation();
							var that = event.data.refresh();
							that.grid.handlers.edit();
							return false;
						});

						$("#delBtn", that.selector).bind('click', that, function (event) {
							event.preventDefault();
							event.stopPropagation();
							var that = event.data.refresh();
							that.grid.handlers.del();
							return false;
						});

						$(".busiTypCodSelect", that.selector).selectGroup({
							itemValue: 'label',
							code: {
								type: 'busiFunc'
							}
						});
						$(".orgMatchTypSelect", that.selector).selectGroup({
							code: {
								type: "orgMatchTyp"
							}
						});
						$(".autTypCodSelect", that.selector).selectGroup({
							code: {
								type: "autType"
							}
						});

						$(".roleInputBtnGroup", that.selector).inputButtonGroup({
							txtSuffix: '查询',
							cssSuffixIcon: 'fa fa-search',
							events: {
								btnClick: {
									data: that,
									handler: function (event) {
										event.preventDefault();
										event.stopPropagation();
										var that = event.data.refresh();
										that.grid.handlers.openQueryModal({
											that:that,
											title:'角色',
											type:'0',
											multiSelect:false,
											url:'priv/info/role/index.htm'
										});
										return false;
									}
								}
							}
						});

						$(".staffInputBtnGroup", that.selector).inputButtonGroup({
							txtSuffix: '查询',
							cssSuffixIcon: 'fa fa-search',
							events: {
								btnClick: {
									data: that,
									handler: function (event) {
										event.preventDefault();
										event.stopPropagation();
										var that = event.data.refresh();
										that.grid.handlers.openQueryModal({
											that:that,
											title:'员工',
											type:'1',
											multiSelect:false,
											url:'priv/info/staff/index.htm'
										});
										return false;
									}
								}
							}
						});


						var cols = [
							{title: '业务功能', name: 'busiTypCod', width: 100, align: 'center', renderer: function (val, item, index) {
								return app.code.getText("busiFunc", val, index);
							}},
							{title: '角色', name: 'roleName', width: 100, align: 'center'},
							{title: '员工', name: 'staffName', width: 100, align: 'center'},
							{title: '机构匹配方式', name: 'orgMatchTyp', width: 100, align: 'center', renderer: function (val, item, index) {
								return app.code.getText("orgMatchTyp", val, index);
							}},
							{title: '机构', name: 'orgPathName', width: 100, align: 'center'},
							{title: '权限类型', name: 'autTypCod', width: 100, align: 'center', renderer: function (val, item, index) {
								return app.code.getText("autType", val, index);
							}},
							{title: '指定机构', name: 'appointOrgName', width: 100, align: 'center'},
							{title: '指定角色', name: 'appointRoleName', width: 100, align: 'center'},
							{title: '指定人员', name: 'appointStaffName', width: 100, align: 'center'},
							{title: '指定机构类型', name: 'appointOrgClass', width: 100, align: 'center', renderer: function (val, item, index) {
								return app.code.getText("orgAttr", val, index);
							}}
							/*{title: '创建时间', name: 'instDate', width: 100, align: 'center'},
							{title: '更新时间', name: 'updtDate', width: 100, align: 'center'}*/
						];

						var config = {
							cols: cols,
							url: 'priv/info/list.json',
							params: {},
							multiSelect: true,
							checkCol: true,
							indexCol: true,
							nowrap: false, //内容不折行
							plugins: [],
							events: {},
							customEvents: []
						};

						config.plugins.push($('#gridPageGroup', that.selector).gridGroupPaginator({}));
						config.plugins.push($("#gridQueryGroup", that.selector).gridGroupQuery({queryBtn: $("#queryBtn", that.selector), resetBtn: $("#resetBtn", that.selector)}));

						that.vars.gridGroupVar = $('#gridGroup', that.selector).gridGroup(config);
					},
					handlers: {
						init: function (that) {
							this.global = function () { return that;};
						},
						add: function () {
							var that = this.global();

							appui.modal.open({
								title: '添加参数',
								url: 'priv/info/form.htm',
								cssModal: 'modal-lg',
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

										that.vars.gridGroupVar.load({pageNo: 1});
									}
								}
							});

							return false;
						},
						edit: function () {
							var that = this.global();
							var items = that.vars.gridGroupVar.selectedRows();
							if (items.length != 1) return appui.message.info('请选择一条操作数据.') || false;
							var item = items[0];
							appui.modal.open({
								title: '修改参数',
								url: 'priv/info/form.htm',
								cssModal: 'modal-lg',
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
						del: function () {
							var that = this.global();
							var items = that.vars.gridGroupVar.selectedRows();
							if (items.length == 0 ) {
								appui.message.info('请至少选择一条数据.');
								return;
							}

							var ids = [];
							for (var i = 0; i < items.length; i++) ids.push(items[i].id);
							
							
							appui.dialog.confirm({
				                size: "modal-small",
				                title: "消息提示", //optional
				                message: "是否删除",//required
				                callback: function(r){
				                	if (r) {
										$.ajax({
											url: 'priv/info/del.json',
											type: 'POST',
											data: { ids: ids},
											success: function (data) {
												if (!data.success) {
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
						openQueryModal:function(param){
							var that = this.global();
							appui.modal.open({
								title: param.title,
								cssModal: 'modal-lg',
								styleBody: 'padding-left: 0; padding-right: 0;',
								url:param.url,
								param: param,
								data: {},//约定 子窗体回值
								callback: {
									hidden: function(options){
										var data = options.data;
										var that = options.param.that;
										if(!data.closed) return;
										//给角色和员工赋值
										if(param.type == '0'){//角色
											$("input[name = roleNo]", that.selector).valChange({label:data.label, value: data.value});
										}else if(param.type == '1'){//员工
											$("input[name =staffNo ]", that.selector).valChange({label:data.label, value:data.value});
										}
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
		<div class="container-fluid otod-system-sysprivinfo">
			<h3 class="page-header"><i class="fa fa-th"></i> 特殊权限设置</h3>
			<div class="ui-querybars row" id="gridQueryGroup">
				<input type="text" class="busiTypCodSelect" name="busiTypCod" options='{txtPrefix: "业务功能"}' />
				<input type="text" class="roleInputBtnGroup" name="roleNo" options='{txtPrefix: "角色"}' />
				<input type="text" class="staffInputBtnGroup" name="staffNo" options='{txtPrefix: "员工"}' />
				<input type="text" class="orgMatchTypSelect" name="orgMatchTyp" options='{txtPrefix: "机构匹配方式"}' />
				<input type="text" class="autTypCodSelect" name="autTypCod" options='{txtPrefix: "权限类型"}' />
			</div>

			<div class="ui-toolbars row">
				<div class="pull-left">
					<button class="btn btn-success btn-sm" id="addBtn"><i class="fa fa-plus"></i> 添加</button>
					<button class="btn btn-primary btn-sm" id="editBtn"><i class="fa fa-edit"></i> 修改</button>
					<button class="btn btn-danger btn-sm" id="delBtn"><i class="fa fa-remove"></i> 删除</button>
				</div>
				<div class="pull-right">
					<button class="btn btn-primary btn-sm" id="queryBtn"><i class="fa fa-search"></i> 查询</button>
					<button class="btn btn-danger btn-sm" id="resetBtn"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
				</div>
			</div>
			<table id="gridGroup"></table>
			<div id="gridPageGroup"></div>

		</div>
	</body>
</html>
