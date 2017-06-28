define( [ "jquery" ], function() {
	"use strict";
	
	$( function() {
		
		$( "#btnChangePassword" ).bind( "click", function (event) {
			
			//app.modal.open() 写你的打开页面
			appui.modal.open({
                title: "修改密码",
                url: "pub/password/index.htm",
                cssModal: 'modal-md',
                callback: {
                    hidden: function(options) {
                       var data = options.data;
                       var that = options.param.that;
                       if (!data.closed) return;
                       appui.message.success('修改密码成功.');
                    }
                }
            });
		} );
		
	} ) ;
} );