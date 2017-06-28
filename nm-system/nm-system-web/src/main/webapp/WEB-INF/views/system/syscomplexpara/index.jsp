<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>系统复杂参数 </title>
</head>
<body>
<script type="text/javascript">
	$(function(){
		app.init();
	});
    app.init('otod.system.sys.syscomplexpara',{
    	vars: {
    		codeGridVar: false,
    		formValidatorVar: false
        },
        init: function () {
        	var that =this;
        	this.global = function () { return that; };
        	app.code('availableStat');
        	this.layout();
        	this.codeGrid.init(that);
        },
        layout: function () {
        	
        },
        codeGrid : {
    		init: function(that){
   				this.global = function () { return that;};
   				this.layout();
   				this.formValidat();
   				this.handlers.init(that);
   			},
   			layout: function () {
   				var that = this.global();
   				
   				$(".inputGroup" , that.selector).inputGroup({
   	    			cssCol: 'col-xs-6',
   	    	        cssLabel: 'col-xs-3',
   	    	        cssInputGroup: 'col-xs-9'	
   	    		});
   			  	$("#stat", that.selector).selectGroup({
   	    			cssCol: 'col-xs-6',
   	    	        cssLabel: 'col-xs-3',
   	    	        cssInputGroup: 'col-xs-9',	
   		     	    code:{
   		     	    	type:'availableStat'
   		     	    }
   			  	});
   			  	
   			  	$("#paraStartOpr,#paraEndOpr", that.selector).selectGroup({
   	    			cssCol: 'col-xs-6',
   	    	        cssLabel: 'col-xs-3',
   	    	        cssInputGroup: 'col-xs-9',
   	    	        itemValue: 'label',
   		     	   	items: [{label: '+'},{label: '-'},{label: '*'},{label: '%'}]
   			  	});
   				
				$("#saveBtn", that.selector).bind('click', that, function (event) {
					event.preventDefault();
		            event.stopPropagation();
					var that = event.data.refresh();
					that.codeGrid.handlers.save();
					return false;
				});
   				
   				var cols = [
                      { title: '类别编码', name: 'typeNo', align: 'center', width: '100'},
                      { title: '类别名称', name: 'typeName', align: 'center', width: '100'},
                      { title: '参数名称', name: 'paraName', align: 'center', width: '100'},
                      { title: '是否有效', name: 'stat', align: 'center', width: '100' ,renderer: function(val, item, rowIndex){
                       	if (item['stat']=='10002001') {
                       		return  '<a class="fa text-success fa-toggle-on  start-status" href="javascript:;">正常</a>';
						} else {
							return	'<a class="fa text-danger fa-toggle-off  start-status" href="javascript:;">失效</a>';
						}
                      }},
 		            { title:'操作', name:'handle' ,width:100, align:'center', renderer: function(val, item, rowIndex){
		            	return '<a class="fa fa-edit  edit" href="javascript:;"> 修改</a>';
		            }}
                  ];
				var config = {
				        cols: cols,
		                url: 'complex/para/list',
			            multiSelect: false,
			            checkCol: false,
			            indexCol: true,
			            nowrap :false, //内容不折行
			            plugins: [],
			            events: {},
			            customEvents: []
			        };
								
   				config.plugins.push($('#codeGridPage', that.selector).gridGroupPaginator({}));
   				//自定义修改事件
				config.customEvents.push({
					target: '.edit',
					data: that,
					handler: function (event, item, rowIndex) {
						var that = event.data.refresh();
						that.codeGrid.handlers.edit(rowIndex);
						return false;
					}
				});
   				
				config.customEvents.push({
					target: '.start-status',
					data: that,
					handler: function (event, item, rowIndex) {
						event.preventDefault();
		                event.stopPropagation();
						$(this).toggleClass('text-danger')
						var that = event.data.refresh();
						that.codeGrid.handlers.changeStatus(item);
						return false;
					}
				});
				
   				
   				that.vars.codeGridVar = $("#codeGrid", that.selector).gridGroup(config);
   			},
   			
   		  	/*表单验证*/
   	      	formValidat :function(){
   	    	  	var that = this.global();
   		 	  	that.vars.formValidatorVar = $("form", that.selector).validate({
   		 		    onfocusout: false,
   		 			onkeyup: false,
   		 			focusCleanup: true, 
   	 		    	rules: {
   						typeNo: {required: true,rangelength:[1, 12]},
   						typeName: {required: true,rangelength:[1, 12]},
   					 	paraNo: {required: true,rangelength:[1, 12]},
   						paraName: {required: true,rangelength:[1, 12]},
   						paraValue: {required: true,rangelength:[1, 12]},
   						paraStartValue: {required: true,isNumber:true,rangelength:[1, 12]},
   						paraStartOpr: {required: true,rangelength:[1, 12]},
   						paraEndValue: {required: true,isNumber:true,rangelength:[1, 12]},
   						paraEndOpr: {required: true,rangelength:[1, 12]},
   						paraPrice: {required: true,rangelength:[1, 12]},
   						orderNo: {required: true,rangelength:[1, 12]},
   						stat: {required: true}, 
   						pNode:{required: true,rangelength:[1, 12]}
   	 		    		
   			    	}
   		 		})
   	     	},
   			
   			handlers : {
   				init: function(that){
   					this.global = function () { return that;}
   				},
   				changeStatus : function(item){
   					var that = this.global();
   					var validFlag = item['stat'];
   					var data = item;
   					data['stat'] = data['stat'] != '10002001' ? '10002001' : '10002002';
   	                
   	                $.ajax({
   	                    url: 'complex/para/save',
   	                    type: 'POST',
   	                    data: data,
   	                    success: function (data) {
   	                    	data = app.data(data);
   	                        if (!data.success){
   	                            appui.message.error(data.msg);
   	                            return;
   	                        }else{
   	                        	appui.message.success(data.msg);
   	                        }
   	                        //刷新typeGrid
   	                        that.vars.codeGridVar.load({pageNo: 1});
   	                    }
   	                });

   	                return false;
   					
   				},
   			  	save: function () {
   					var that = this.global();
   				  	var $validator = that.vars.formValidatorVar;
   				  	if(!$validator.form()) {
   					  	return false;
   				  	}
   				  	var $form = $($validator.currentForm);
   				  
   				  	var data = $form.serialize()
   				  
   				  	$.post(
   		           		"complex/para/save",
   		    			data,
   		    	    	function(data){
   		 		       	if(!data.success){
   		 		    	  	appui.modal.close({"error":"操作失败"});
   		 		    	  	return false;
   		 			   	}else{
   		                    appui.message.success('操作成功.');
   		                    //清输入框中所有内容
   		                 	$("input[type=text], input[type=hidden]", that.selector).valChange('');
   		                    $("#textarea", that.selector).valChange('');
   		                	that.vars.codeGridVar.load({"pageNo":1});
   		 			   	}
   	 		       	}, "json");
   	   			 },
   	   			edit: function (rowIndex) {
					var that = this.global();
					var item = that.vars.codeGridVar.row(rowIndex);
			        if (item) {
			            $('input[name=typeNo]', that.selector).valChange(item['typeNo']);
			            $('input[name=typeName]', that.selector).valChange(item['typeName']);
			            $('input[name=paraNo]', that.selector).valChange(item['paraNo']);
			            $('input[name=paraName]', that.selector).valChange(item['paraName']); 
			            $('input[name=paraValue]', that.selector).valChange(item['paraValue']);
			            $('input[name=paraStartValue]', that.selector).valChange(item['paraStartValue']);
			            $('input[name=paraStartOpr]', that.selector).valChange(item['paraStartOpr']);
			            $('input[name=paraEndValue]', that.selector).valChange(item['paraEndValue']);
			            $('input[name=paraEndOpr]', that.selector).valChange(item['paraEndOpr']);
			            $('input[name=paraPrice]', that.selector).valChange(item['paraPrice']);
			            $('input[name=stat]', that.selector).valChange(item['stat']);
			            $('input[name=orderNo]', that.selector).valChange(item['orderNo']);
			            $('input[name=pNode]', that.selector).valChange(item['pNode']);
			            $('input[name=remark]', that.selector).valChange(item['remark']);
			            $('input[name=id]', that.selector).valChange(item['id']);
			        }

					
					
   	   			}
   				
   			}
   		
        }
        
    })
</script>
	<div class="container-fluid  otod-system-sys-syscomplexpara">
		<h3 class="page-header"><i class="fa fa-home"></i> 系统复杂参数 </h3>
		<div class="col-xs-6">
			<form action="" id='formValidat' class="form-horizontal" >
				<div class='row'>
				<input class="inputGroup" type="text" name="typeNo" options="类别编码" /> 
				<input class="inputGroup" type="text" name="typeName" options="类别名称" />
				<input class="inputGroup" type="text" name="paraNo" options="参数编码" />
				<input class="inputGroup" type="text" name="paraName" options="参数名称" />
				<input class="inputGroup" type="text" name="paraValue" options="参数值" />
				<input class="inputGroup" type="text" name="paraStartValue"  options="参数开始值"/> 
				<input id="paraStartOpr" class="selectGroup" type="text" name="paraStartOpr"  options="开始运算符"/> 
				<input id="paraEndOpr" class="selectGroup" type="text" name="paraEndOpr"  options="结束运算符"/> 
				<input class="inputGroup" type="text" name="paraEndValue"  options="参数结束值"/>
				<input class="inputGroup" type="text" name="paraPrice"  options="参数精度"/> 
				<input id="stat" type="text" class="selectGroup " name="stat" options="状态"/>
				<input class="inputGroup" type="text" name="orderNo"  options="序号"/>
				<input class="inputGroup" type="text" name="pNode"  options="父节点"/>
				<textarea id="textarea" class="inputGroup" rows="3" cols="20"  name="remark" options="备注"></textarea>
				<input class="inputGroup" type="hidden" name="id" /> 
<!-- 				<input class="inputGroup" type="hidden" name="dataSource" />  -->
				</div>
			</form>
			<div class="ui-toolbars row">
		        <div class="pull-right">
		            <button id="saveBtn" type="button" class="btn btn-primary btn-sm" ><i class="fa fa-save"></i> 保存</button>
		        </div>
		    </div>
		</div>
		<div class="col-xs-6">
		     <table id="codeGrid"></table>
		     <div id="codeGridPage"></div>
		</div>
	</div>
</body>
</html>
