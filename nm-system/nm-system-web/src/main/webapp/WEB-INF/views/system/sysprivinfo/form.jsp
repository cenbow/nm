<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>权限添加</title>
		<style type="text/css">
			.otod-system-sysprivinfo-form {}
			.otod-system-sysprivinfo-form h5.page-header { font-weight: 700;}
		</style>
		<script type="text/javascript">
			$(function(){ app.init(); });

			app.init('otod.system.sysprivinfo.form', {
				vars: {
					formValidatorVar: false,
					itemVar: false,
					appointOrgName: []
				},
				init: function(){
					var that = this;
					this.global = function () { return that;};
					app.code('busiFunc, orgMatchTyp, autType, orgAttr');

					if (typeof that.modal != 'undefined') that.vars.itemVar = that.modal.param.item;
					
					this.form.init(that);
				},
				form: {
					init: function (that) {
						this.global = function () { return that;};

						this.layout();
						this.validator();
						this.setData();
						this.handlers.init(that);
					},
					layout: function () {
						var that = this.global();

						$("#saveBtn", that.selector).bind('click', that, function (event) {
							event.preventDefault();
							event.stopPropagation();
							var that = event.data.refresh();
							that.form.handlers.save();
							return false;
						});

						$("#cancelBtn", that.selector).bind('click', that, function (event) {
							event.preventDefault();
							event.stopPropagation();

							appui.modal.close({closed: false});
							return false;
						});

						$(".busiTypSelect", that.selector).selectGroup({
							cssCol: 'col-xs-6',
							cssLabel: 'col-xs-4 ',
							cssInputGroup: 'col-xs-8',
							code:{
								type:'busiFunc'
							}
						});
						$(".typeSelect", that.selector).selectGroup({
							cssCol: 'col-xs-6',
							cssLabel: 'col-xs-4 ',
							cssInputGroup: 'col-xs-8',
							items:[
								{label: '角色',value:'0'},
								{label: '员工',value:'1'}
							],
							events:{
								change: {
									data: that,
									handler: function (event) {
										event.preventDefault();
										event.stopPropagation();
										var that = event.data.refresh();
										var value = $(this).val();
										if(value=='0'){//角色
											$("#staffNo_div", that.selector).visible(false);//不可见
											$("#orgPath_div,#orgMatchTyp_div,#roleNo_div",that.selector).visible(true);//可见
										}else if(value == '1'){
											$("#staffNo_div", that.selector).visible(true);//可见
											$("#orgPath_div,#orgMatchTyp_div,#roleNo_div",that.selector).visible(false);//不可见
										}
										return false;
									}
								}
							}
						});
						$(".orgMatchSelect", that.selector).selectGroup({
							cssCol: 'col-xs-6',
							cssLabel: 'col-xs-4 ',
							cssInputGroup: 'col-xs-8',
							code:{
								type:'orgMatchTyp'
							}
						});
						$(".autTypCodSelect", that.selector).selectGroup({
							cssCol: 'col-xs-6',
							cssLabel: 'col-xs-4 ',
							cssInputGroup: 'col-xs-8',
							code:{
								type:'autType'
							},
							events:{
								change: {
									data: that,
									handler: function (event) {
										event.preventDefault();
										event.stopPropagation();
										var that = event.data.refresh();
										var value = $(this).val();
										if(value!='11005005'){//非指定类型权限 
											$("#appoint_div").visible(false);//不可见
										}else{//指定
											$("#appoint_div").visible(true);//可见
										}
										return false;
									}
								}
							}
						});

						$(".appointOrgClassSelect", that.selector).selectGroup({
							cssCol: 'col-xs-6',
							cssLabel: 'col-xs-4 ',
							cssInputGroup: 'col-xs-8',
							code:{
								type:'orgAttr'
							}
						});
						$(".inputBtnGroup", that.selector).inputButtonGroup({
							cssCol: 'col-xs-6',
							cssLabel: 'col-xs-4 ',
							cssInputGroup: 'col-xs-8',
							txtSuffix:'选择',
							cssSuffixIcon:'fa fa-search',
							events: {
								btnClick: {
									data: that,
									handler: function (event) {
										event.preventDefault();
										event.stopPropagation();
										var that = event.data.refresh();
										that.form.handlers.openModal({
											that: that,
											title: '角色',
											type: '0',
											multiSelect: false,
											url: 'priv/info/role/index.htm'
										});
										return false;
									}
								}
							}
						});

						$(".staffInputBtnGroup", that.selector).inputButtonGroup({
							cssCol: 'col-xs-6',
							cssLabel: 'col-xs-4 ',
							cssInputGroup: 'col-xs-8',
							txtSuffix:'选择',
							cssSuffixIcon:'fa fa-search',
							events: {
								btnClick: {
									data: that,
									handler: function (event) {
										event.preventDefault();
										event.stopPropagation();
										var that = event.data.refresh();
										that.form.handlers.openModal({
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

						$(".appointRoleInputBtnGroup", that.selector).inputButtonGroup({
							cssCol: 'col-xs-6',
							cssLabel: 'col-xs-4 ',
							cssInputGroup: 'col-xs-8',
							txtSuffix:'指定',
							cssSuffixIcon:'fa fa-search',
							events: {
								btnClick: {
									data: that,
									handler: function (event) {
										event.preventDefault();
										event.stopPropagation();
										var that = event.data.refresh();
										var param = {
											that:that,
											title:'角色',
											type:'2',
											multiSelect:true,
											url:'priv/info/role/index.htm'
										};
										that.form.handlers.openModal(param)
										return false;
									}
								}
							}
						});
						$(".appointStaffInputBtnGroup",that.selector).inputButtonGroup({
							cssCol: 'col-xs-6',
							cssLabel: 'col-xs-4 ',
							cssInputGroup: 'col-xs-8',
							txtSuffix:'指定',
							cssSuffixIcon:'fa fa-search',
							events: {
								btnClick: {
									data: that,
									handler: function (event) {
										event.preventDefault();
										event.stopPropagation();
										var that = event.data.refresh();
										that.form.handlers.openModal({
											that:that,
											title:'员工',
											type:'3',
											multiSelect:true,
											url:'priv/info/staff/index.htm'
										});
										return false;
									}
								}
							}
						});

						$('.orgPathSelectTreeGroup', that.selector).selectTreeGroup({
							cssCol: 'col-xs-6',
							cssLabel: 'col-xs-4 ',
							cssInputGroup: 'col-xs-8',
							itemLabel: 'orgName',
							itemValue: 'orgCodPath',
							data: {
								id: 'orgNo',
								pid: 'parOrgNo',
								name: 'orgName',
								children: 'list'
							},
							show:{
								level: 1,
								multi: false,
								checkbox: true
							},
							remote: {
								url: 'priv/info/org/list.json',
								type: 'POST',
								data: {},
								callback: function (data) {
									return app.toTreeJson(data.list, {
										id: 'orgNo',
										pid: 'parOrgNo',
										children: 'list'
									});
								}
							},
							events: {
								change: {
									handler: function (event, values, items) {
										if(items && items.length > 0)
											$("input[name =orgPathName]", that.selector).valChange(items[0].orgName);
									}
								}
							}
						});

						$('.appointOrgSelectTreeGroup', that.selector).selectTreeGroup({
							cssCol: 'col-xs-6',
							cssLabel: 'col-xs-4 ',
							cssInputGroup: 'col-xs-8',
							itemLabel: 'orgName',
							itemValue: 'orgNo',
							itemValueLimit:';',
							data: {
								id: 'orgNo',
								pid: 'parOrgNo',
								name: 'orgName',
								children: 'list'
							},
							show:{
								level: 2,
								multi: true
							},
							remote: {
								url: 'priv/info/org/list.json',
								type: 'POST',
								data: {
								},
								callback: function (data) {
									return app.toTreeJson(data.list, {
										id: 'orgNo',
										pid: 'parOrgNo',
										children: 'list'
									});
								}
							},
							events: {
								change: {
									handler: function (event, values, items) {
										event.preventDefault();
										event.stopPropagation();
										if(items && items.length>0){
											that.vars.appointOrgName=[];
											for(var i = 0 ;i<items.length;i++){
												that.vars.appointOrgName.push(items[i]['orgName']);
											}
											$("input[name=appointOrgName]", that.selector).valChange(that.vars.appointOrgName.join(";"));
										}
										return false;
									}
								}
							}
						});
					},
					validator:function(){
						var that = this.global();
						that.vars.formValidatorVar = $('form', that.selector).validate({
							rules:{
								busiTypCod: {required:true },
								autTypCod: {required:true },
								privType: {required:true },
								orgPath: {required:true },
								orgMatchTyp: {required:true },
								roleNo: {required:true },
								staffNo: {required:true }
							}
						});
					},
					setData:function(){//给表单赋值
						var that = this.global();
						if (!that.vars.itemVar) return false;
						var item = that.vars.itemVar;

						$('input[name=id]', that.selector).valChange(item.id);
						$('input[name=instDate]', that.selector).valChange(item.instDate);
						$('input[name=updtDate]', that.selector).valChange(item.updtDate);
						$("input[name=roleNo]",that.selector).valChange({label:item.roleName,value: item.roleNo});
						$("input[name =staffNo ]",that.selector).valChange({label:item.staffName,value:item.staffNo});
						$('input[name=appointRoleNo]', that.selector).valChange({label:item.appointRoleName,value:item.appointRoleNo});
						$("input[name=appointRoleNo]",that.selector).valChange({label:item.appointRoleName,value: item.appointRoleNo});
						$("input[name=appointRoleName]",that.selector).valChange(item.appointRoleName);
						$("input[name=appointStaffNo ]",that.selector).valChange({label:item.appointStaffName,value:item.appointStaffNo});
						$("input[name=appointStaffName]",that.selector).valChange(item.appointStaffName);
						$('input[name=busiTypCod]', that.selector).valChange(item.busiTypCod);
						$('input[name=orgMatchTyp]', that.selector).valChange(item.orgMatchTyp);
						$('input[name=roleName]', that.selector).valChange(item.roleName);
						$('input[name=autTypCod]', that.selector).valChange(item.autTypCod);
						$('input[name=appointOrgClass]', that.selector).valChange(item.appointOrgClass);
						$('input[name=appointOrgNo]', that.selector).valChange(item.appointOrgNo);
						$('input[name=appointOrgName]', that.selector).valChange(item.appointOrgName);
						/* console.info(item.orgPath+"---"+item.orgPathName) */
						$('input[name=orgPath]', that.selector).valChange(item.orgPath.substring(item.orgPath.lastIndexOf(",")));
						$('input[name=orgPathName]', that.selector).valChange(item.orgPathName);
						if(item.orgPath){//角色类型
							$('input[name=privType]', that.selector).valChange('0');
						}else{//员工类型
							$('input[name=privType]', that.selector).valChange('1');
						}
					},
					handlers: {
						init: function (that) {
							this.global = function () { return that;};
						},
						save: function () {
							var that = this.global();
							var $validator = that.vars.formValidatorVar;
							if (!$validator.form()) return false;
							var $saveBtn = $('#saveBtn', that.selector);
							$saveBtn.disabled(true);
							var item = $($validator.currentForm).serialize();
							$.ajax({
								url: 'priv/info/save.json',
								type: 'POST',
								data: item,
								error:function(){
									$saveBtn.disabled(false);
								},
								success: function (data) {
									
									if (!data.success) {
										appui.message.error('操作失败.')
										return false;
									}
									appui.modal.close(data);
								}
							});
						},
						openModal: function (param) { //打开角色和人员modal
							var that = this.global();
							//return;
							appui.modal.open({
								title: param.title,
								cssModal: 'modal-lg',
								url:param.url,
								param: param,
								data: {},//约定 子窗体回值
								callback: {
									hidden: function(options){
										var data = options.data;
										var that = options.param.that;
										if(!data.closed) return;
										//给角色和员工赋值////////////////////////////////////////////////
										if(param.type == '0'){//角色
											$("input[name=roleNo]",that.selector).valChange({label:data.label,value: data.value});
										}else if(param.type == '1'){//员工
											$("input[name =staffNo ]",that.selector).valChange({label:data.label,value:data.value});
										}else if(param.type == '2'){//指定角色
											$("input[name=appointRoleNo]",that.selector).valChange({label:data.label,value: data.value});
											$("input[name=appointRoleName]",that.selector).valChange(data.label);
										}else if(param.type == '3'){//指定员工
											$("input[name=appointStaffNo]",that.selector).valChange({label:data.label,value:data.value});
											$("input[name=appointStaffName]",that.selector).valChange(data.label);
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
		<div class="container-fluid otod-system-sysprivinfo-form">
			<form class="form-horizontal" >
					<div class="hide">
						<input type="hidden" name="id">
						<input type="hidden" name="instDate">
						<input type="hidden" name="updtDate">
						<input type="hidden" name="appointRoleName">
						<input type="hidden" name="appointStaffName">
						<input type="hidden"  name="appointOrgName">
						<input type="hidden"  name="orgPathName">
					</div>
					<h5 class="page-header"><i class="fa fa-list-alt"></i> 权限类型</h5>
					<div class="row">
					<input type="text" class="busiTypSelect" name="busiTypCod" options="业务功能"/>
					<input type="text" class="typeSelect" name="privType" options="类型"/>
					</div>


					<h5 class="page-header"><i class="fa fa-list-alt"></i> 权限对象</h5>
					<div class="row">
						<div id="orgPath_div"><input type="text" class="orgPathSelectTreeGroup " name="orgPath" options="机构"/></div><!--机构下拉树  -->
						<div id="orgMatchTyp_div"><input type="text" class="orgMatchSelect " name="orgMatchTyp" options="机构匹配方式"/></div>
					</div>
					<div class="row">
						<div id="roleNo_div">
						<input type="text" class="inputBtnGroup " name="roleNo" options="角色"/>
						</div>
						<div id="staffNo_div"><input type="text" class="staffInputBtnGroup " name="staffNo" options="员工"/></div>
					</div>

					<h5 class="page-header"><i class="fa fa-list-alt"></i> 权限</h5>
					<div class="row">
					<input type="text" class="autTypCodSelect" name="autTypCod" options="权限类型"/>
					</div>
					
					<div id = "appoint_div">
					<div class="row">
					<input type="text" class="appointOrgSelectTreeGroup " name="appointOrgNo" options="指定机构"/><!--机构下拉树  -->
					<input type="text" class="appointOrgClassSelect " name="appointOrgClass" options="指定机构属性"/>
					</div>
					<div class="row">
					<input type="text" class="appointRoleInputBtnGroup " name="appointRoleNo" options="指定角色"/>
					<input type="text" class="appointStaffInputBtnGroup " name="appointStaffNo" options="指定人员"/>
					</div>
					</div>
			</form>
			<div class="toolbars">
				<div class="pull-right">
					<button type="submit" id="saveBtn" class="btn btn-primary"><i class="fa fa-save"></i> 保存</button>
					<button type="button" id="cancelBtn" class="btn btn-danger"><i class="fa fa-remove"></i> 取消</button>
				</div>
			</div>
		</div>
	</body>
</html>