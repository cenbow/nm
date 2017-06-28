<%--
  User: Mifeng.He(bee)
  Date: 2016/02/25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>报表</title>
    <script type="text/javascript">
    	$( function () { app.init(); });
    	app.init("common.iframe", {
    		vars: {
    			paramStr: "${paramStr}"
    		},
    		init: function () {
    			var that = this;
    			this.global = function() { return that; };
				this.handlers.init( that );
				
				if ( typeof that.page != "undefined" ) that.vars.item = that.page.param;
				
				this.layout();
    		},
    		layout: function () {
    			var that = this.global();
    			that.vars.$frame = that.selector.find( "iframe" );
    			if ( that.vars.item ) {
    				var url = that.vars.item[ "url" ] || "";
    				if (url.length >0 ) {
						url += url.indexOf("?") != -1 ? "&" : "?";
						url += that.vars.paramStr;
	    				that.vars.$frame.attr( "src",  url);
    				} 
    			}
    			
    			$( window ).resize( function () {
    				that.handlers.resize();
    			} );
    			
    			that.handlers.resize();
    			
    		},
    		handlers: {
    			init: function( that ) {
    				this.global = function() { return that; };
    			},
    			resize: function () {
    				var that = this.global();
    				
    				that.vars.$frame.css( {
    					height: $("#sections").height() - 5
    				} );
    			}
    		}
    	});
    	
    </script>
</head>
<body>
    <div class="common-iframe" style="overflow: hidden;">
        <iframe src="about:_blank;" frameborder="0" style="overflow: auto; width: 100%; border: none;"></iframe>
    </div>
</body>
</html>