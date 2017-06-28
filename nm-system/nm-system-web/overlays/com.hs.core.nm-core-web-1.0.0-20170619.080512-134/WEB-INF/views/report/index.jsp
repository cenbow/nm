<%--
  User: Mifeng.He(bee)
  Date: 2015/12/16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>报表</title>
    <script type="text/javascript">
    	app.init("common.report", {
    		vars: {
    			item: false
    		},
    		init: function () {
    			var that = this;
    			this.global = function() { return that; };
				this.handlers.init( that );
				this.layout();
    		},
    		layout: function () {
    			var that = this.global();
    			that.vars.$frame = that.selector.find("iframe");
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
    	
    	$( function () { app.init(); });
    </script>
</head>
<body>
    <div class="common-report" style="overflow: hidden;">
        <iframe src="${reportAddr}" frameborder="0" style="overflow: auto; width: 100%; border: none;"></iframe>
    </div>
</body>
</html>
