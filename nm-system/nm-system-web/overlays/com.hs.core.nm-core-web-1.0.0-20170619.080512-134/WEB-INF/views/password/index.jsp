<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>业务渠道 </title>
   <style type="text/css">
/*         .otod.marketing.channels{} */
/*         .otod.marketing.channels .form-control{margin:0;padding:0;} */
    </style> 
<script type="text/javascript">
$(function(){
	app.init();
});
app.init('otod.pub.staff.changepwd', {
	vars: {
		itemVar: false,
		formValidatorVar: false,
		npwdConfirmVar: false
    },
	init: function(){
		var that=this;
		//获取码表数据
	    if (typeof that.modal != 'undefined') that.vars.itemVar = that.modal.param['item'];
		this.layout(that);
		that.handlers.init(that);
	},
	layout: function (that) {
		$(".inputGroup", that.selector).inputGroup({
			  cssCol: 'col-xs-10 col-md-10 col-lg-10 col-sg-10 col-mg-10',
			  cssLabel: 'col-xs-3',
			  cssInputGroup: 'col-xs-9'	
		 });
		
		$("#saveBtn", that.selector).bind('click', that, function (event) {
	  		event.preventDefault();
            event.stopPropagation();
	  		var that = event.data.refresh();
	  		that.handlers.save();
	  		return false;
	  	 });
		
		$("#closeBtn", that.selector).bind('click', that, function(){
			appui.modal.close({closed: false});
		});
		
		$('#npwd_confirm', that.selector).bind('change', that, function(event) {
			event.preventDefault();
            event.stopPropagation();
	  		var that = event.data.refresh();
	  		
	  		var npwd = $('#npwd', that.selector).val();
	  		var npwd_confirm = $('#npwd_confirm', that.selector).val();
	  		if (npwd_confirm != npwd) {
	  			appui.message.warning('两次密码输入不一致');
// 	  			$('#npwd_confirm', that.selector).parent().parent().addClass('has-error');
	  			$('#npwd_confirm', that.selector).css('border-color', '#843534');
	  			that.vars.npwdConfirmVar = false;
	  		}else {
// 	  			$('#npwd_confirm', that.selector).parent().parent().removeClass('has-error');
	  			$('#npwd_confirm', that.selector).css('border-color', '#ccc');
	  			that.vars.npwdConfirmVar = true;
	  		}
	  		
	  		return false;
		});
		
	},
	handlers: {
		init: function(that) {
			this.global = function () { return that;};
			that.handlers.formValidat();
		},
		formValidat: function() {
			var that = this.global();
			that.vars.formValidatorVar = $("#formValidat", that.selector).validate({
		 		onfocusout: false,
		 		onkeyup: false,
		 		focusCleanup: true,
	 		    rules: {
	 		    	opwd: {required: true},
	 		    	npwd: {required: true},
	 		    	npwd_confirm: {required: true}
			    }
		 	});
		},
		save: function() {
			var that = this.global();
			var opwd = that.selector.find('#opwd').val();
			var npwd = that.selector.find('#npwd').val();
			if(!that.vars.formValidatorVar.form()) {
				  return false;
			 }
			if (!that.vars.npwdConfirmVar) return false;
			var $saveBtn = that.selector.find('#saveBtn');
			
			$.ajax({
				url: 'pub/staff/changepwd',
				data: {opwd : opwd, npwd : npwd},
				type: 'POST',
				beforeSend: function() {
					$saveBtn.disabled(true);
				},
				success: function(data) {
                    if (!data.success){
                        appui.message.error(data.msg);
                        return;
                    }
                    appui.modal.close(data);
				},
				complete: function() {
					$saveBtn.disabled(false);
				}
			});
		}
	}
});
    
</script>
</head>
<body>
<div class="container-fluid  otod-pub-staff-changepwd">
	
	<form id="formValidat" class="form-horizontal">
		<div class="row">
			<input id="opwd" type="password" class="inputGroup" name="opwd" options="原密码" />
		</div>
		<div class="row">
			<input id="npwd" type="password" class="inputGroup" name="npwd" options="新密码" />
		</div>
		<div class="row">
			<input id="npwd_confirm" type="password" class="inputGroup" name="npwd_confirm" options="确认密码" />
		</div>
		<div class="row pull-right">
		    <button type="button" id="saveBtn" class="btn btn-primary"><i class="fa fa-save"></i> 提&#12288;交</button>
		    <button type="button" id="closeBtn" class="btn btn-danger"><i class="glyphicon glyphicon-remove-circle"></i> 取&#12288;消</button>
		</div>
	</form>
</div>
</body>
</html>
