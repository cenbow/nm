(function($){

	if("underline" == typeof toastr) throw new Error("jquery.validate.method.extend.js require toastr.js");

	/*
	 * 扩展jQuery validate 插件 中文提示
	 * */
	$.extend($.validator.messages, {
		required: "{vname} : 请输入字符",
		remote: "{vname} : 请重新输入有效的字符",
		email: "{vname} : 请输入正确格式的电子邮件",
		url: "{vname} : 请输入合法的网址",
		date: "{vname} : 请输入合法的日期",
		dateISO: "{vname} : 请输入合法的日期 .",
		number: "{vname} : 请输入合法的数字",
		digits: "{vname} : 请输入是整数数字",
		creditcard: "{vname} : 请输入合法的信用卡号",
		equalTo: "{vname} : 请输入输入相同的值",
		accept: "{vname} : 请输入合法后缀名",
		maxlength: $.validator.format("{vname} :长度最大长度{0}"),
		minlength: $.validator.format("{vname} :长度最小长度{0}"),
		rangelength: $.validator.format("{vname} :长度介于 {0} 和 {1}"),
		range: $.validator.format("{vname} :介于 {0} 和 {1}"),
		max: $.validator.format("{vname} :最大为 {0}"),
		min: $.validator.format("{vname} :最小为 {0}")
	});
	/*
	 * 扩展jQuery validate 插件提示信息
	 */
	$.validator.setDefaults( {
		submit: false, //提交时验证。设置为 false 就用其他方法去验证。 	true
		onfocusout: false, //失去焦点时验证（不包括复选框/单选按钮）。 	true
		onkeyup: false, //在 keyup 时验证。 	true
		onclick: false, //在点击复选框和单选按钮时验证。 	true
		focusInvalid: false, //提交表单后，未通过验证的表单（第一个或提交之前获得焦点的未通过验证的表单）会获得焦点。 	true
		focusCleanup: true, //如果是 true 那么当未通过验证的元素获得焦点时，移除错误提示。避免和 focusInvalid 一起用。
		errorPlacement : function(error, element){
			/*var $error = $(error); 
			if($error.hasClass("error")){
				var msg = $error.text() || "", vname = null;
				if(errtxt) {
					vname = $(element).attr("vname") || "该字段";
					msg = msg.replace("{vname}",vname);
					return msg;
				}
			}*/
			$(element).parents(".form-group, .control-group").addClass("has-error")/*.removeClass("has-success")*/;
		},
		success: function(error, element){
			$(element).parents(".form-group, .control-group")/*.addClass("has-success")*/.removeClass("has-error");
		},
		showErrors : function(messages, errors){
			if(errors){
				var msgs = [], msg = null, ele = null, vname = null;
				$.each(errors, function (i, error) {
					msg = error.message;
					ele = error.element;
					vname = $(ele).attr("vname") || $(ele).prev(".input-group-addon").text() || $(ele).parent().find(".input-group-addon").text()  || $(ele).parent().prev(".control-label").text() || $(ele).parent().find(".control-label").text() || $(ele).parent().find("label").text() || "该字段";
					msg = msg.replace("{vname}",vname);
					msgs.push(msg);
				});
				msgs = msgs.join("<br/>");
				if(msgs) toastr.error("",msgs);
			}
			this.defaultShowErrors();
		}
	});

	$.validator.prototype._resetForm = $.validator.prototype.resetForm;

	$.extend($.validator.prototype, {
		validGroup: function(group){
			this.prepareForm();
			var elements = $(group);
			for ( var i = 0; elements[ i ]; i++ ) {
				if (elements[ i ][ 'name' ]) this.check( elements[ i ] );
			}
			elements = $("input[type=text], input[type=checkbox], input[type=radio], textarea", group).not(':hidden');
			for ( var i = 0; elements[ i ]; i++ ) {
				if (elements[ i ][ 'name' ]) this.check( elements[ i ] );
			}
			this.showErrors();
			return this.valid();
		},
		resetForm: function () {
			this._resetForm();
			$(this.currentForm).find('.has-error').removeClass('has-error');
		}
	});
	
	//alert($.validator.validGroup);
})(jQuery);