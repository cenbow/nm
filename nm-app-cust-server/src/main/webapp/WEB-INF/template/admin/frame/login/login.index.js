( function( $ ) {
	
	function Global( vars ) {
		
		var message = toastr;
		//toastr.options.positionClass = "toast-bottom-full-width";
		toastr.options.hideDuration = 550055500;
		var that = this;
		
		this.selector = $("body");
		this.vars = {};
		vars = this.vars = $.extend( true, this.vars, vars ); 
		var handlers = this.handlers = {};
		this.handlers.getGlobal = this.getGlobal = function() { return that; };
		
		this.init = function() {
			var that = this.getGlobal();
			
			this.selector.find( "#btnSubmit" ).bind( "click", function() {
				that.handlers.login();
			} );
			
			that.selector.bind( "keydown", function( event ) {
				if ( event.keyCode == "13" ) {
					that.handlers.login();
				}
 			} );
			
			that.selector.find( ":input[name]" ).val( "" );
		};
		
		
		handlers.login = function() {
			var that = this.getGlobal();
			var name, password;
			name = that.selector.find( ":input[name=name]" ).val();
			password = that.selector.find( ":input[name=password]" ).val();
			
			if ( name.length < 4 || name.length > 20 ) {
				return message.error( "用户名：请输入4-20字符。" );
			}
			
			if ( password.length < 6 || password.length > 20 ) {
				return message.error( "密码：请输入6-20字符。" );
			}
			
			that.selector.find( "#btnSubmit" ).attr( "disabled", "disabled" ).text( "登录中..." );
			
			$.ajax( {
				url: "admin/frame/login",
				type: "POST",
				dataType: "JSON",
				data: {
					"in[loginNo]": name,
					"in[loginPwd]": password,
					code: "123456"
				},
				success: function( command ) {
					if ( !command.success ) {
						return message.error( command.message );
					}
					
					message.success( "登录成功，马上跳转..." );
					setTimeout( function(){
						window.location.reload( true );
					}, 700 );
				},
				error: function( xhr ) {debugger;
					var command = xhr.responseJSON;
					if ( typeof command == "undefined" ) {
						command = xhr.responseText;
						if ( typeof command == "string" && data.length != 0 ) {
							command = ( new Function( "return " + command ) )();
						}
					}
					if ( !command.success ) {
						return message.error( command.message );
					}
				},
				complete: function() {
					that.selector.find( "#btnSubmit" ).removeAttr( "disabled" ).text( "登录" );
				}
			} )
			
		};
		
	};
	
	$( function() {
		new Global().init();
	} );
	
} )( jQuery );