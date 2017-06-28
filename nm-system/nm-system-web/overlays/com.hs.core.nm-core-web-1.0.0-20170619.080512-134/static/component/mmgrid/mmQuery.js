!function($){
	
    var MMQuery = function(element, options){
        this.$el = $(element);
        this.opts = options;
    };

    MMQuery.prototype = {
        _initLayout: function(){
        },
        _plain: function(page, totalCount, limit){
        },
        _search: function(page, totalCount, limit){
        },
        load: function(params){
        },
        formatString: function(tmpl, args){
        	var format = { };  
    	    return tmpl.replace(/{(\w+)}/g, function(m1, m2) {  
    	        if (!m2)return "";  
    	        return (format && format[m2]) ? format[m2](args[m2]) : args[m2];  
    	    });  
        },
        params: function(){
            var opts = this.opts;
            var $el = this.$el;
            formatString = this.formatString;
            
            var params = {},n;
            var $inputs = $el.find(":input").each(function(){
            	n = $(this).attr("name");
            	if(n){
            		n = formatString(opts.param, {0: n});
            		if(!params[n]) params[n] = $(this).val();
            		else params[n] += "," + $(this).val();
            	}
            });
            return params;
        }, 
        init: function($grid){
        	var that = $grid.$query=this;
        	
        	 $(".btn-query", that).click(function(){
        		 if($grid.$paginator) $grid.$paginator.$el.data("page", 1);
        		 $grid.load();
        	 });
        }

    };

    $.fn.mmQuery = function(){
        if(arguments.length === 0 || typeof arguments[0] === 'object'){
            var option = arguments[0]
                , data = this.data('mmQuery')
                , options = $.extend(true, {}, $.fn.mmQuery.defaults, option);
            if (!data) {
                data = new MMQuery(this[0], options);
                this.data('mmQuery', data);
            }
            return $.extend(true, this, data);
        }
        if(typeof arguments[0] === 'string'){
            var data = this.data('mmQuery');
            var fn =  data[arguments[0]];
            if(fn){
                var args = Array.prototype.slice.call(arguments);
                return fn.apply(data,args.slice(1));
            }
        }
    };

    $.fn.mmQuery.defaults = {
    	param:"params[{0}]"
    };

    $.fn.mmQuery.Constructor = MMQuery;

}(window.jQuery);