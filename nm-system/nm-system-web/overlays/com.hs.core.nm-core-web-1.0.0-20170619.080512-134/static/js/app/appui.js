/**
 * @author Mifeng.He(bee)
 * @email 2504637134@qq.com
 * @date 2015.09.10
* */
(function($){
    var win = window;
    var appui = {};
    /**
     * get uuid
     * @param len 长度
     * @param radix 基数
     * @returns {string}
     */
    appui.uuid = function(len, radix) {
        if(!radix) radix = len * 2;
        var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
        var uuid = [], i;
        radix = radix || chars.length;

        if (len) {
            // Compact form
            for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
        } else {
            // rfc4122, version 4 form
            var r;

            // rfc4122 requires these characters
            uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
            uuid[14] = '4';

            // Fill in random data.  At i==19 set the high bits of clock sequence as
            // per rfc4122, sec. 4.1.5
            for (i = 0; i < 36; i++) {
                if (!uuid[i]) {
                    r = 0 | Math.random()*16;
                    uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
                }
            }
        }
        return uuid.join('');
    };

    /**
     * tempalte
     * @returns {string}
     */
    appui.fmt = function () {
        var tmpl = arguments[0] || "";
        if ($.isArray(tmpl)) tmpl = tmpl.join('');
        if (arguments.length == 2 && typeof(arguments[1]) != "array") {
            var dta = arguments[1];
            var format = { };
            return tmpl.replace(/{(\w+)}/g, function(m1, m2) {
                if (!m2)return "";
                return (format && format[m2]) ? format[m2](dta[m2]) || '' : dta[m2] || '';
            });
        } else {
            var args = arguments[1];
            return tmpl.replace(/{(\d+)}/g, function(match, number) {
                return typeof args[number + 1] != 'undefined' ? args[number + 1] || '' : match || '';
            });
        }

    };

    /*模板定义*/
    appui.templates= {};

    appui.loading = {
        init: function(options){
            var $loading = $("#loading");
            if(!$loading.length){
                var that = appui;
                if("undefined" == typeof options || options == null ) options = {type: "loading"};
                var tmp = that.templates[options.type] || that.templates["loading"];
                options = $.extend({}, tmp.defaults, options);
                $loading = $(that.fmt(tmp.template[0], {})).appendTo($("body"));
            }
            return $loading;
        },
        show: function(options, callback){
            if (typeof options == "function") {
                callback = options;
                options = null;
            }
            if (typeof cfback != "undefined") {
                callback = "";
            }
            $('body').addClass('ui-loading-open');
            return this.init(options).fadeIn(callback);
        },
        hide: function(options,callback){
            if (typeof options == "function") {
                callback = options;
                options = null;
            }
            if (typeof callback != "undefined") {
                callback = "";
            }
            $('body').removeClass('ui-loading-open');
            return this.init(options).fadeOut(callback);
        }
    };

    /**
     * input wrapper
     * @param options
     * @returns {$target}
     */
    appui.inputGroup = function(options){
        var that = this;
        if("undefined" == typeof(options)) options = {type: "inputGroup"};
        if("object" != typeof(options)) throw new Error("appui.inputGroup Illegal parameter exception! ");
        if("undefined" == typeof(options.type)) throw new Error("appui.inputGroup(options.type) Illegal parameter exception! ");
        var tmp = that.templates[options.type] || that.templates["inputGroup"];
        if("undefined" == typeof(tmp)) throw new Error("appui.inputGroup(options.type) Illegal parameter exception! ");
        options = $.extend(true, {}, tmp.defaults, options);


        var $target = options.$target;
        $target.addClass("form-control");
        options.before.call(that, options);
        var $Group = $(that.fmt(tmp.template[0], options)).insertAfter($target);

        $Group.find(".target").replaceWith($target);

        if(options.cssPrefix != 'hide' || options.cssSuffix != 'hide'){
            $target.wrap(tmp.template[1]);
            if(options.cssPrefix != 'hide') $(that.fmt(tmp.template[2], options)).insertBefore($target);
            if(options.cssSuffix != 'hide') $(that.fmt(tmp.template[3], options)).insertAfter($target);
            if(options.cssInputGroup.length){
                $Group.find('.input-group').wrap(that.fmt(tmp.template[4], options));
            }
        }else if(options.cssInputGroup.length){
            options.$target.wrap(that.fmt(tmp.template[4], options));
        }


        $target.removeAttr("options");

        return $Group;
    };

    /**
     * inputButtonGroup wrapper
     * @param options
     * @returns {*}
     */
    appui.inputButtonGroup = function(options){
        var that = this;
        if("undefined" == typeof(options)) options = {type: "inputButtonGroup"};

        var tmp = that.templates[options.type];
        if("undefined" == typeof(tmp)) throw new Error("appui.inputButtonGroup(options.type) Illegal parameter exception! ");
        options = $.extend(true, {}, tmp.defaults, options);


        options.before.call(that, options);
        if (typeof options.readOnly != 'boolean') options.readOnly = true;

        if (options.readOnly) options.$target.addClass("sr-only");

        var $Group = $(that.fmt(tmp.template[0], options)).insertAfter(options.$target);

        if (options.readOnly) $Group.find(".target").replaceWith(tmp.template[3]);
        else $Group.find(".target").replaceWith(options.$target.addClass('form-control'));

        options.$Group = $Group;

        if(options.cssInputGroup.length){
            options.$Group.find('.input-group').wrap(that.fmt(tmp.template[2], options));
        }

        var $targetShow = $Group.find('.form-control');
        if(options.cssPrefix != 'hide' || options.cssSuffix != 'hide'){
            if(options.cssPrefix != 'hide') $(that.fmt(tmp.template[1], options)).insertBefore($targetShow);
        }

        options.$button = $Group.find('button');

        options.$targetShow = $targetShow;

        if (options.readOnly) {
            /*chang event*/
            options.$target.bind('change.event.ui', options.events.change.data, options.events.change.handler);

            options.$target.bind('change.val.ui', options, function (event) {
                var options = event.data;
                var $target = options.$target;
                var $targetShow = options.$targetShow;
                var item = $target.data('VALUE');
                if (typeof item !='object') item = {};
                $targetShow.val(item[options.itemLabel] || '');
                $target.val(item[options.itemValue] || '');

                $target.triggerHandler('change.event.ui', item);

            });
        }

        options.$button.bind('click', {options: options, data: options.events.btnClick.data}, function (event) {
            var options = event.data.options;
            event.data = event.data.data;
            options.events.btnClick.handler.call(options.$target, event);
        });


        return options.$Group;
    };


    /**
     * radio wrapper
     * @param options
     */
    appui.radioGroup = function(options){
        var that = this;
        if("undefined" == typeof(options)) options = {type: "radioGroup"};

        var tmp = that.templates[options.type];
        if("undefined" == typeof(tmp)) throw new Error("appui.radioGroup(options.type) Illegal parameter exception! ");
        options = $.extend(true, {}, tmp.defaults, options);


        options.$target.addClass("sr-only");

        options.before.call(that, options);
        var $Group = $(that.fmt(tmp.template[0], options)).insertAfter(options.$target);
        $Group.find(".target").replaceWith(options.$target);
        options.$Group = $Group;

        options.cssBtnIcons = options.cssBtnIcon.split(',');
        if (options.cssBtnIcons.length < 2) throw Error('appui.radioGroup required two cssBtnIcon.');

        if(options.cssPrefix != 'hide' || options.cssSuffix != 'hide'){
            if(options.cssPrefix != 'hide') {
                var $btnGroup = $Group.find('.btn-group');
                $(that.fmt(tmp.template[1], options)).insertBefore($btnGroup);
                if (!options.cssInputGroup.length) $('<span class="hide"></span>').appendTo($btnGroup);
            }
        }

        options.init = function(options, that){

            var btns = [], bool = false, value = '', item = null;
            var activeValue = options.$target.val();
            for(var i = 0; i < options.items.length; i++){
                bool = options.items[i][options.itemValue] + '' === activeValue;
                btns.push(that.fmt(tmp.template[2], {
                    cssBtn: options.cssBtn + ( bool ? ' ' + options.cssActive : ''),
                    txtLabel: options.items[i][options.itemLabel],
                    txtValue: options.items[i][options.itemValue],
                    cssBtnIcon: bool ? options.cssBtnIcons[1] : options.cssBtnIcons[0]
                }));
                if (bool) {
                    value =  options.items[i][options.itemValue];
                    item = options.items[i];
                }

            }
            options.$target.bind('change.event.ui', options.events.change.data, options.events.change.handler);

            var $btns = $(btns.join('')).each(function(i){
                $(this).data('item', options.items[i]).bind('click', function(event){
                    $(this).blur();
                    if($(this).hasClass(options.cssActive)) return;

                    options.$target.focus().blur();
                    $btns.removeClass(options.cssActive).removeClass(options.cssBtnIcons[1]).addClass(options.cssBtnIcons[0]);

                    $(this).addClass(options.cssActive).addClass(options.cssBtnIcons[1]).removeClass(options.cssBtnIcons[0]);

                    var val = $(this).attr('value'), item = $(this).data('item');

                    options.$target.val(val);

                    options.$target.triggerHandler('change.event.ui', [val, item]);
                });
            });

            options.$Group.find('.btn-group').append($btns);

            if(options.cssInputGroup.length){
                options.$Group.find('.input-group').wrap(that.fmt(tmp.template[3], options));
            }

            options.$target.triggerHandler('change.event.ui', [value, item]);

            options.after.call($Group, options);

            options.$btns = $btns;

            /**
             * change handler
             */
            options.$target.bind("change.val.ui", options, function(e) {
                var options = e.data;
                var $btns = options.$btns;

                var $btn, val = null, item = null;
                var activeValue = options.$target.val();
                for (var i = 0; i < options.items.length; i++) {
                    $btn = $btns.eq(i);
                    if (options.items[i][options.itemValue] + '' === activeValue) {
                        $btn.addClass(options.cssActive).addClass(options.cssBtnIcons[1]).removeClass(options.cssBtnIcons[0]);;
                        val = $btn.attr('value');
                        item = $btn.data('item');
                    } else $btn.removeClass(options.cssActive).removeClass(options.cssBtnIcons[1]).addClass(options.cssBtnIcons[0]);
                }
                options.$target.triggerHandler('change.event.ui', [val, item]);
            });

        };

        options.codeFilter = function(filter, options, that) {
            options = $.extend(true, options, {
                itemLabel: options.code.itemLabel,
                itemValue: options.code.itemValue
            });
            var items = filter;
            if(options.code.enable.status) {
                items = [];
                var item = null;
                for(var i = 0; i < filter.length; i++) {
                    item = filter[i];
                    if(item[options.code.enable.key])
                        items.push(item);
                }
            }
            return items;
        };

        if (options.code.type) {
            var items = app.code.getData(options.code.type);
            if (typeof items == 'undefined') {
                var id = 'code_' + options.code.type + '_' + that.uuid(16, 32);
                var ecode = options.code.type + '.' + 'app.code.' +  id;
                $(app).bind(ecode, {
                    id: id,
                    options: options,
                    that: that
                }, function (e) {
                    var id = e.data.id,
                        ecode = e.data.ecode,
                        options = e.data.options,
                        that = e.data.that;

                    var items = app.code.getData(options.code.type);

                    if (typeof items == 'undefined') return false;
                    //关闭绑定事件
                    $(app).unbind(ecode, true);
                    items = options.code.callback(items[options.code.group]);
                    options.items = options.codeFilter(items, options, that);
                    options.init(options, that);
                });
            } else {
                items = options.code.callback(items[options.code.group]);
                options.items = options.codeFilter(items, options, that);
                options.init(options, that);
            }
        } else if(options.items){
           options.init(options, that);
        } else {
            $.ajax(options.remote.url, {
                type: options.remote.type,
                cache: options.remote.cache,
                data: options.remote.data,
                success: function(data, textStatus, jqXHR){
                    var data = options.remote.callback(data, textStatus, jqXHR);
                    if ($.isPlainObject(data)) data = data[options.remote.root];
                    options.items = data || [];

                    options.init(options, that);
                },
                dataType: options.remote.dataType || "json"
            });
        }

        return $Group;
    };

    /**
     * checkbox wrapper
     * @param options
     */
    appui.checkboxGroup = function(options){
        var that = this;
        if("undefined" == typeof(options)) options = {type: "checkboxGroup"};

        var tmp = that.templates[options.type];
        if("undefined" == typeof(tmp)) throw new Error("appui.checkboxGroup(options.type) Illegal parameter exception! ");
        options = $.extend(true, {}, tmp.defaults, options);

        options.$target.addClass("sr-only");

        options.before.call(that, options);

        options.cssBtnIcons = options.cssBtnIcon.split(',');
        if (options.cssBtnIcons.length < 2) throw Error('appui.checkboxGroup required two cssBtnIcon.');

        var $Group = $(that.fmt(tmp.template[0], options)).insertAfter(options.$target);
        $Group.find(".target").replaceWith(options.$target);
        options.$Group = $Group;

        if(options.cssPrefix != 'hide' || options.cssSuffix != 'hide'){
            if(options.cssPrefix != 'hide') {
                var $btnGroup = $Group.find('.btn-group');
                $(that.fmt(tmp.template[1], options)).insertBefore($btnGroup);
                if (!options.cssInputGroup.length) $('<span class="hide"></span>').appendTo($btnGroup);
            }
        }

        options.init = function(options, that){

            var btns = [], bool = false;
            var activeValue = options.$target.val().split(options.itemValueLimit);
            var vals = [], items = [];
            for(var i = 0; i < options.items.length; i++){
                bool = $.inArray(options.items[i][options.itemValue] + '', activeValue) >= 0;
                btns.push(that.fmt(tmp.template[2], {
                    cssBtn: options.cssBtn + ( bool ? ' ' + options.cssActive : ''),
                    txtLabel: options.items[i][options.itemLabel],
                    txtValue: options.items[i][options.itemValue],
                    cssBtnIcon: bool ? options.cssBtnIcons[1] : options.cssBtnIcons[0]
                }));
                if(bool) {
                    vals.push(options.items[i][options.itemValue]);
                    items.push(options.items[i]);
                }
            }
            options.$target.bind('change.event.ui', options.events.change.data, options.events.change.handler);
            options.$target.triggerHandler('change.event.ui', [vals, items]);

            var $btns = $(btns.join('')).each(function(i){
                $(this).data('item', options.items[i]).bind('click', function(event){
                    $(this).toggleClass(options.cssActive).blur();
                    $(this).toggleClass(options.cssBtnIcons[0]).toggleClass(options.cssBtnIcons[1]);
                    var vals = [], items = [];
                    $btns.filter("." + options.cssActive).each(function(){
                        vals.push($(this).attr('value'));
                        items.push($(this).data('item'));
                    });
                    options.$target.val(vals.join(options.itemValueLimit));
                    options.$target.focus().blur();

                    options.$target.triggerHandler('change.event.ui', [vals, items]);
                });
            });

            /**
             * change handler
             */
            options.$target.bind("change.val.ui", options, function(e){
                var options = e.data;
                var $target = $(this);
                var $btns = options.$btns;

                var $btn;
                var items = [];
                var activeValue = options.$target.val().split(options.itemValueLimit);
                for (var i = 0; i < options.items.length; i++) {
                    $btn = $btns.eq(i);
                    if ($.inArray(options.items[i][options.itemValue] + '', activeValue) >= 0){
                        items.push(options.items[i]);
                        $btn.addClass(options.cssActive);
                        $btn.removeClass(options.cssBtnIcons[0]).addClass(options.cssBtnIcons[1]);
                    }else
                        $btn.removeClass(options.cssActive).removeClass(options.cssBtnIcons[1]).addClass(options.cssBtnIcons[0]);
                }

                options.$target.triggerHandler('change.event.ui', [activeValue, items]);
            });

            options.$Group.find('.btn-group').append($btns);
            if(options.cssInputGroup.length){
                options.$Group.find('.input-group').wrap(that.fmt(tmp.template[3], options));
            }
            options.after.call($Group, options);

            options.$btns = $btns;
        };
    
        options.codeFilter = function(filter, options, that) {
            options = $.extend(true, options, {
                itemLabel: options.code.itemLabel,
                itemValue: options.code.itemValue
            });
            var items = filter;
            if(options.code.enable.status) {
                items = [];
                var item = null;
                for(var i = 0; i < filter.length; i++) {
                    item = filter[i];
                    if(item[options.code.enable.key])
                        items.push(item);
                }
            }
            return items;
        };
    
        if (options.code.type) {
            var items = app.code.getData(options.code.type);
            if (typeof items == 'undefined') {
                var id = 'code_' + options.code.type + '_' + that.uuid(16, 32);
                var ecode = options.code.type + '.' + 'app.code.' +  id;
                $(app).bind(ecode, {
                    id: id,
                    options: options,
                    that: that
                }, function (e) {
                    var id = e.data.id,
                        ecode = e.data.ecode,
                        options = e.data.options,
                        that = e.data.that;
                
                    var items = app.code.getData(options.code.type);
                
                    if (typeof items == 'undefined') return false;
                    //关闭绑定事件
                    $(app).unbind(ecode, true);
                    items = options.code.callback(items[options.code.group]);
                    options.items = options.codeFilter(items, options, that);
                    options.init(options, that);
                });
            } else {
                items = options.code.callback(items[options.code.group]);
                options.items = options.codeFilter(items, options, that);
                options.init(options, that);
            }
        } else if(options.items){
            options.init(options, that);
        } else {
            $.ajax(options.remote.url, {
                type: options.remote.type,
                data: options.remote.data,
                success: function(data, textStatus, jqXHR){
                    var data = options.remote.callback(data, textStatus, jqXHR);
                    if ($.isPlainObject(data)) data = data[options.remote.root];
                    options.items = data || [];

                    options.init(options, that);
                },
                dataType: options.remote.dataType || "json"
            });
        }

        return options.$Group;
    };


    /**
     * select wrapper
     * @param options
     * @returns {*}
     */
    appui.selectGroup = function(options){
        var that = this;
        if("undefined" == typeof(options)) options = {type: "selectGroup"};

        var tmp = that.templates[options.type];
        if("undefined" == typeof(tmp)) throw new Error("appui.selectGroup(options.type) Illegal parameter exception! ");
        options = $.extend(true, {}, tmp.defaults, options);


        options.before.call(that, options);

        options.$target.addClass("sr-only");

        var $Group = $(that.fmt(tmp.template[0], options)).insertAfter(options.$target);
        $Group.find(".target").replaceWith(options.$target);
        options.$Group = $Group;


        if(options.cssInputGroup.length){
            options.$Group.find('.input-group').wrap(that.fmt(tmp.template[4], options));
        }
        if(options.cssPrefix != 'hide' || options.cssSuffix != 'hide'){
            var $targetShow = $Group.find('.form-control');
            if(options.cssPrefix != 'hide') $(that.fmt(tmp.template[1], options)).insertBefore($targetShow);
        }

        /**
         * 初始化
         * @param options
         * @param that
         */
        options.init = function(options, that){
            var btns = [];
            var showItemValue = "";
            var showActive = "";
            var activeValue = options.$target.val();
            for(var i = 0; i < options.items.length; i++){
                if(options.items[i][options.itemValue] + '' === activeValue){
                    showItemValue = options.items[i][options.itemLabel];
                    showActive = ' ' + options.cssActive;
                } else showActive = '';
                btns.push(that.fmt(tmp.template[3], {
                    cssItemActive: showActive,
                    cssSuffixBtn: options.cssSuffixBtn,
                    txtLabel: options.items[i][options.itemLabel],
                    txtValue: options.items[i][options.itemValue]
                }));
            }
            var $btns = $(btns.join('')).each(function(i){
                $(this).bind('click', {item: options.items[i]},function(event){
                    /*event.preventDefault();
                    event.stopPropagation();*/
                    if($(this).hasClass(options.cssActive)) return;

                    options.$target.val($(this).attr('value'));
                    options.$targetShow.val($(this).text());
                    options.$target.focus().blur();
                    $btns.removeClass(options.cssActive);
                    $(this).addClass(options.cssActive);
                    options.$target.triggerHandler('change.event.ui', [event.data.item]);
                });
            });

            $Group.find(".dropdown-menu").append($btns);
            var $targetShow = $Group.find('.form-control').val(showItemValue);
            options.$btns = $btns;
            options.$targetShow = $targetShow;

            options.after.call($Group, options);

            /*chang event*/
            options.$target.bind('change.event.ui', options.events.change.data, options.events.change.handler);

            /**
             * change handler
             */
            options.$target.bind("change.val.ui", options, function(e){
                var options = e.data;
                var $btns = options.$btns;
                var $btn;


                var activeValue = options.$target.val();
                options.$targetShow.val('');
                if (!activeValue) $btns.each(function () {
                    $(this).removeClass(options.cssActive);
                });
                var item = null;
                for (var i = 0; i < options.items.length; i++) {
                    $btn = $btns.eq(i);
                    if(options.items[i][options.itemValue] + '' === activeValue){
                        $btn.addClass(options.cssActive);
                        options.$targetShow.val(options.items[i][options.itemLabel]);
                        item = options.items[i];
                    }
                    else {
                        $btn.removeClass(options.cssActive);
                    }
                }
                options.$target.triggerHandler('change.event.ui', [item]);
            });
        };

        options.codeFilter = function(filter, options, that) {
            options = $.extend(true, options, {
                itemLabel: options.code.itemLabel,
                itemValue: options.code.itemValue
            });
            var items = filter;
            if(options.code.enable.status) {
                items = [];
                var item = null;
                for(var i = 0; i < filter.length; i++) {
                    item = filter[i];
                    if(item[options.code.enable.key])
                        items.push(item);
                }
            }
            return items;
        };

        if (options.code.type) {
            var items = app.code.getData(options.code.type);
            if (typeof items == 'undefined') {
                var id = 'code_' + options.code.type + '_' + that.uuid(16, 32);
                var ecode = options.code.type + '.' + 'app.code.' +  id;
                $(app).bind(ecode, {
                    id: id,
                    options: options,
                    that: that
                }, function (e) {
                    var id = e.data.id,
                        ecode = e.data.ecode,
                        options = e.data.options,
                        that = e.data.that;

                    var items = app.code.getData(options.code.type);

                    if (typeof items == 'undefined') return false;
                    //关闭绑定事件
                    $(app).unbind(ecode, true);
                    items = options.code.callback(items[options.code.group]);
                    options.items = options.codeFilter(items, options, that);
                    options.init(options, that);
                });
            } else {
                items = options.code.callback(items[options.code.group]);
                options.items = options.codeFilter(items, options, that);
                options.init(options, that);
            }
        } else if(options.items){
            options.init(options, that);
        } else {
            $.ajax(options.remote.url, {
                type: options.remote.type,
                data: options.remote.data,
                success: function(data, textStatus, jqXHR){
                    var data = options.remote.callback(data, textStatus, jqXHR);
                    if ($.isPlainObject(data)) data = data[options.remote.root];
                    options.items = data || [];
                    options.init(options, that);
                },
                dataType: options.remote.dataType || "json"
            });
        }

        return options.$Group;
    };

    /**
     * selectarea wrapper
     * @param options
     * @returns {templates.inputGroup.defaults.$target|*|HTMLElement|Affix.$target|c.$target}
     */
    appui.selectAreaGroup = function(options){
        var that = this;
        if("undefined" == typeof(options)) options = {type: "selectAreaGroup"};

        var tmp = that.templates[options.type];
        if("undefined" == typeof(tmp)) throw new Error("appui.selectAreaGroup(options.type) Illegal parameter exception! ");
        options = $.extend(true, {}, tmp.defaults, options);

        options.before.call(that, options);

        options.$target.addClass("sr-only");

        if (options.requiredLevel == 'all') options.requiredLevel = options.showChildrenLevel;

        //build cascade select element
        var _arr = [];
        var realyValueFormat = [];

        for (var i = 0; i < options.showChildrenLevel; i++) {
            realyValueFormat.push('{' + i +'}');

            _arr.push(that.fmt(tmp.template[2], {
                placeholder: options.showChildrenLabels[i] || "请选择...",
                cssShowMaxHeight: options.cssShowMaxHeight,
                showStyleMenu: options.showStyleMenu,
                cssSuffixBtn: options.cssSuffixBtn,
                cssSuffixIcon: options.cssSuffixIcon,
                txtSuffix: options.txtSuffix
            }));
        }

        options.realyItem = [];
        options.realyValueFormat = realyValueFormat.join(options.itemValueLimit);

        var $Group = $(that.fmt(tmp.template[0], options)).insertAfter(options.$target);
        $Group.find(".target").replaceWith(options.$target);
        var $InputGroup = $Group.find(".input-group").append(_arr.join(""));

        _arr = [];
        if (!options.levelItemValueHide)
        for (var i = 0; i < options.showChildrenLevel; i++)
            _arr.push(that.fmt('<input type="hidden" class="hide sr-only level-value" name="{0}" />', [options.levelItemValueNames[i] || '']));

        if (_arr.length != 0) options.$vals = $(_arr.join('')).insertAfter(options.$target);


        if(options.cssInputGroup.length){
            $InputGroup.wrap(that.fmt(tmp.template[4], options));
        }
        if(options.cssPrefix != 'hide' || options.cssSuffix != 'hide'){
            var $targetShow = $Group.find('.form-control:eq(0)');
            if(options.cssPrefix != 'hide') $(that.fmt(tmp.template[1], options)).insertBefore($targetShow);
        }

        var $drops = $InputGroup.find(".dropdown-menu");
        var $inputs = $InputGroup.find(".form-control");
        //var $vals = $InputGroup.find(".level-value");

        options.$drops = $drops;
        options.$inputs = $inputs;
        options.realyValue = [];
        //options.$vals = $vals;


        /**
         * return dropmenu list html
         * @param index input index
         * @param items []
         * @param that appui
         * @returns {*} String
         */
        options.buildDropMenu = function(index, items, that, change){
            var options = this;
            var _menu = [];
            var activeValue = options.$target.val();
            if(activeValue !== ''){
                activeValue = activeValue.split(options.itemValueLimit);
                if(index >= activeValue.length) return new Error("appui.selectAreaGroup input value illegal realyValue exception !");
                activeValue = activeValue[index];
            }
            var cssItemActive;
            if(!change) {
                options.$inputs.eq(index).val('');
                options.$inputs.eq(index).data('value', '');
                if (options.$vals != null) options.$vals.eq(index).val('');
                options.realyValue[index] = '';
                options.realyItem[index] = null;
            }
            for(var i = 0; i < items.length; i++){
                if(items[i][options.itemValue] + '' === activeValue){
                    cssItemActive = ' ' + options.cssActive;
                    options.$inputs.eq(index).val(items[i][options.itemLabel]);
                    if (options.$vals != null) options.$vals.eq(index).val(items[i][options.itemValue]);
                    options.realyValue[index] = items[i][options.itemValue];
                    options.realyItem[index] = items[i];
                    if(index + 1 < options.showChildrenLevel) options.appendDropMenu(index + 1, items[i].children || [], that);
                    if(change) {
                        options.$inputs.eq(index).val(items[i][options.itemLabel]);
                        options.$inputs.eq(index).data('value', items[i][options.itemValue]);
                        options.$inputs.eq(index).data('index', i);
                        if (options.$vals != null) options.$vals.eq(index).val(items[i][options.itemValue]);
                        options.realyValue[index] = '';
                        options.realyItem[index] = null;
                    }
                } else {
                    cssItemActive = '';
                }
                _menu.push(that.fmt(tmp.template[3], {
                    cssItemActive: cssItemActive,
                    cssSuffixBtn: options.cssSuffixBtn,
                    txtLabel: items[i][options.itemLabel],
                    txtValue: items[i][options.itemValue]
                }));
            }

            return _menu.join("");
        };

        /**
         * build dropmenu element handler
         * @param index input index
         * @param items []
         * @param that appui
         */
        options.appendDropMenu = function(index, items, that, change){
            if (items.length == 0) {
                //console.log(index);
                for (var i = index; i < options.showChildrenLevel; i++) {
                    options.$inputs.eq(i).val('-').data("value",'');
                    if (options.$vals != null) options.$vals.eq(i).val('');
                    options.realyValue[i] = '';
                    options.realyItem[i] = null;
                    options.$drops.eq(i).empty();
                }

                if (!change) {
                    options.$target.val(that.fmt(options.realyValueFormat, options.realyValue)).focus().blur();
                    options.$target.triggerHandler('change.event.ui', [options.realyValue, options.realyItem]);
                }
                return;
            }
            var $btns=$(options.buildDropMenu(index, items, that, change)).appendTo(options.$drops.eq(index).empty());
            $btns.each(function(i){
                $(this).bind('click', {
                    index: index,
                    options: options,
                    item: items[i],
                    $input: options.$inputs.eq(index),
                    $btns: $btns,
                    that: that
                }, function(e){
                    var options = e.data.options;
                    var item = e.data.item;
                    var $input = e.data.$input;
                    e.data.$btns.removeClass(options.cssActive);
                    $(this).addClass(options.cssActive);
                    options.realyValue[index] = item[options.itemValue];
                    for(var i = e.data.index + 1; i < options.$inputs.length; i++){
                        options.$inputs.eq(i).val("");
                        if (options.$vals != null) options.$vals.eq(i).val("");
                        options.realyValue[i] = '';
                        options.realyItem[i] = null;
                        options.$drops.eq(i).html("");
                        options[i] = "";
                    }
                    $input.val(item[options.itemLabel]).data("value", item[options.itemValue]);
                    if (options.$vals != null) options.$vals.eq(index).val(item[options.itemValue]);
                    options.realyItem[index] = item;
                    options.realyValue[index] = item[options.itemValue];
                    if(e.data.index + 1 < options.showChildrenLevel){
                        options.$target.val("").focus().blur();
                        options.$target.triggerHandler('change.event.ui', [options.realyValue, options.realyItem]);

                        if('undefined' == typeof item[options.itemChildren] || item[options.itemChildren] == null) {

                            if (options.code.type && options.code.isNull) {
                                options.appendDropMenu(e.data.index + 1, [], that, false);
                            }else if (options.remote.url) {
                                var _data = $.extend(true, {}, options.remote.data);
                                var _vals = [];

                                for (var k = 0; k <= e.data.index; k++) {
                                    _vals.push(options.$inputs.eq(k).data("value"));
                                }
                                _data[options.itemValue] = _vals.join(options.realyValueLimit);

                                $.ajax(options.remote.url, {
                                    type: options.remote.type,
                                    data: _data,
                                    cache: options.remote.cache,
                                    success: function(data, textStatus, jqXHR){
                                        var data = options.remote.callback(data, textStatus, jqXHR);
                                        if ($.isPlainObject(data)) data = data[options.remote.root];
                                        item[options.itemChildren] = data || [];
                                        options.appendDropMenu(e.data.index + 1, item[options.itemChildren] || [], that, false);
                                    },
                                    dataType: options.remote.dataType || "json"
                                });
                            }


                        } else options.appendDropMenu(e.data.index + 1, item[options.itemChildren] || [], that, false);

                        if (options.requiredLevel <= e.data.index + 1) options.$target.val(that.fmt(options.realyValueFormat, options.realyValue)).focus().blur();
                    } else {
                        options.$target.val(that.fmt(options.realyValueFormat, options.realyValue)).focus().blur();
                        options.$target.triggerHandler('change.event.ui', [options.realyValue, options.realyItem]);
                    }
                });
            });

            if(change && (index + 1 < options.showChildrenLevel)){
                var _index = options.$inputs.eq(index).data('index');
                var item =items[_index];

                if (typeof item == 'undefined' || item == null){
                    for (var i = index; i < options.showChildrenLevel; i++) {
                        options.$inputs.eq(i).val('').data("value",'');
                        if (options.$vals != null) options.$vals.eq(i).val('');
                        options.realyValue[i] = '';
                        options.realyItem[index] = null;
                        if(i != 0) options.$drops.eq(i).empty();
                    }
                } else if ('undefined' == typeof item[options.itemChildren] || item[options.itemChildren] == null) {
                    if (options.remote.url) {
                        var _data = $.extend(true, {}, options.remote.data);
                        var _vals = [];

                        for (var k = 0; k <= index; k++) {
                            _vals.push(options.$inputs.eq(k).data("value"));
                        }
                        _data[options.itemValue] = _vals.join(options.realyValueLimit);

                        $.ajax(options.remote.url, {
                            type: options.remote.type,
                            data: _data,
                            cache: options.remote.cache,
                            success: function(data, textStatus, jqXHR){
                                var data = options.remote.callback(data, textStatus, jqXHR);
                                if ($.isPlainObject(data)) data = data[options.remote.root];
                                item[options.itemChildren] = data || [];
                                options.appendDropMenu(index + 1, item[options.itemChildren] || [], that, true);
                            },
                            dataType: options.remote.dataType || "json"
                        });
                    }
                } else options.appendDropMenu(index + 1, item[options.itemChildren] || [], that, true);
            }
        };

        /**
         * init handler
         */
        options.init = function(options, that){
            options.appendDropMenu(0, options.items, that, false);

            options.after.call($Group, options);

            var val = options.$target.val();
            if (val.length != 0) options.$target.triggerHandler('change.event.ui',[options.realyValue, options.realyItem]);
        };

        if (options.code.type) {
            var items = app.code.getData(options.code.type);
            if (typeof items == 'undefined') {
                var id = 'code_' + options.code.type + '_' + that.uuid(16, 32);
                var ecode = options.code.type + '.' + 'app.code.' +  id;
                $(app).bind(ecode, {
                    id: id,
                    options: options,
                    that: that
                }, function (e) {
                    var id = e.data.id,
                        ecode = e.data.ecode,
                        options = e.data.options,
                        that = e.data.that;

                    var items = app.code.getData(options.code.type);

                    if (typeof items == 'undefined') return false;
                    //关闭绑定事件
                    $(app).unbind(ecode, true);
                    options.items = options.code.callback(items);
                    options.init(options, that);
                });
            } else {
                options.items = options.code.callback(items);
                options.init(options, that);
            }
        } else if(options.items){
            options.init(options, that);
        } else {
            $.ajax(options.remote.url, {
                type: options.remote.type,
                data: options.remote.data,
                cache: options.remote.cache,
                success: function(data, textStatus, jqXHR){
                    var data = options.remote.callback(data, textStatus, jqXHR);
                    if ($.isPlainObject(data)) data = data[options.remote.root];
                    options.items = data || [];
                    options.init(options, that);
                },
                dataType: options.remote.dataType || "json"
            });
        }

        /**
         * change handler
         */
        options.$target.bind("change.val.ui", options, function(e){
            options.realyValue =  options.$target.val().split(options.realyValueLimit);
            options.$inputs.each(function (i) {
                $(this).val('');
                if (options.$vals != null) {
                    options.$vals.eq(i).val('');
                    options.realyValue[i] = '';
                }
                options.realyItem[i] = null;
            });
            options.appendDropMenu(0, options.items, that, true);
            for (var i = 1; i < options.showChildrenLevel; i++) {
                if (typeof options.realyValue[i] == 'undefined' || options.realyValue[i] == null ||  options.realyValue[i] == '') options.$drops.eq(i).empty();
            }
            options.$target.triggerHandler('change.event.ui',[options.realyValue, options.realyItem]);
        });
        
        options.$target.bind('change.event.ui', options.events.change.data, options.events.change.handler);

        return $Group;
    };


    /**
     * selectTree wrapper
     * @param options
     * @returns {*}
     */
    appui.selectTreeGroup = function(options){
        var that = this;
        if("undefined" == typeof(options)) options = {type: "selectTreeGroup"};

        var tmp = that.templates[options.type];
        if("undefined" == typeof(tmp)) throw new Error(options.$target.selector + " appui.selectTreeGroup(options.type) Illegal parameter exception! ");
        options = $.extend(true, {}, tmp.defaults, options);

        if (!options.show.multi && options.icons.checkIcon == tmp.defaults.icons.checkIcon) options.icons.checkIcon = 'fa-circle-o,fa-check-circle-o';

        options.icons.checkIcons = options.icons.checkIcon.split(',');
        if (options.icons.checkIcons.length < 2) throw new Error('selectTreeGroup icons.checkIcon required two icon.');

        if (options.show.multi && options.show.relCheck && options.icons.checkIcons.length < 3) throw new Error('selectTreeGroup icons.checkIcon required three icon.');

        options.icons.treeIcons = options.icons.treeIcon.split(',');
        if (options.icons.treeIcons.length < 1) throw new Error(options.$target.selector + ' selectTreeGroup icons.treeIcon required least one icon.');
        else if (options.icons.treeIcons.length == 1) options.icons.treeIcons.push(options.icons.treeIcon[0]);

        options.__isFinished__ = false;

        options.before.call(that, options);

        options.$target.addClass("sr-only");

        var $Group = $(that.fmt(tmp.template[0], options)).insertAfter(options.$target);
        $Group.find(".target").replaceWith(options.$target);
        options.$Group = $Group;

        options.itemInputs = [];
        if (typeof options['itemInputNames'] == 'undefined' || !$.isArray(options['itemInputNames'])) options['itemInputNames'] = [];

        var names;
        $.each(options['itemInputNames'], function (index, name) {
            names = name.split(':');
            if (names.length == 1) throw new Error(('name:' + options.$target.attr('name') || 'id:' + options.$target.attr('id'))  + ' selectTreeGroup ' + name + ' formator is NAME: ID.');
            names[0] = $.trim(names[0]);
            names[1] = $.trim(names[1]);
            options.itemInputs.push({
                name: names[0],
                id: names[1],
                $input: $(appui.fmt('<input type="hidden" name="{0}" id="{1}"/>', names)).insertAfter(options.$target)
            })
        });

        options.changeItemInputsValue = function (items) {
            var options = this;
            var val, name;
            $.each(items, function (i, item) {
                $.each(options.itemInputs, function (j, itemInput) {
                    val = item[itemInput.name];
                    if (val == null) throw Error(('name:' + options.$target.attr('name') || 'id:' + options.$target.attr('id')) + ' selectTreeGroup ' + name + ' is not in item prop.');
                    if (i != 0) val = itemInput.$input.val() + options.itemValueLimit + val;
                    itemInput.$input.val(val);
                });
            });
        };


        if(options.cssInputGroup.length){
            options.$Group.find('.input-group').wrap(that.fmt(tmp.template[4], options));
        }
        if(options.cssPrefix != 'hide' || options.cssSuffix != 'hide'){
            var $targetShow = $Group.find('.form-control');
            if(options.cssPrefix != 'hide') $(that.fmt(tmp.template[1], options)).insertBefore($targetShow);
        }

        options.$targetShow = options.$Group.find('.form-control');

        options.$ul = options.$Group.find('ul');

        options.$Group.find('.ul-select-tree-container').bind('click', function (event) {
            event.preventDefault();
            event.stopPropagation();
            return false;
        });


        /*chang event*/
        options.$target.bind('click.event.ui', options.events.click.data, options.events.click.handler);
        options.$target.bind('change.event.ui', options.events.change.data, options.events.change.handler);

        /**
         * change handler
         */
        options.$target.bind("change.val.ui", options, function(event){

            var options = event.data; if (!options.__isFinished__) return false;
            var vals = options.$target.val().split(options.itemValueLimit);
            var val = null, $checkbox = null, item = null,labels = [], items = [];

            options.$ul.find('li.active').each(function(){
                var $li = $(this);
                $li.removeClass('active');
                $li.find('.ui-selecttree-checkbox:eq(0)').removeClass(options.icons.checkIcons[1]).addClass(options.icons.checkIcons[0]);
            });

            for (var i = 0; i < vals.length; i++) {
                val = vals[i];
                options.$ul.find('.ui-selectree-li-' + val).each(function () {
                    $(this).addClass('active');
                    item = options.$target.data($(this).attr('_vid'));
                    items.push(item);
                    labels.push(item[options.itemLabel]);
                    $checkbox = $(this).find('.ui-selecttree-checkbox:eq(0)');
                    $checkbox.removeClass(options.icons.checkIcons[0]).addClass(options.icons.checkIcons[1]);
                });
            }

            options.$targetShow.val(labels.join(options.itemValueLimit));

            options.changeItemInputsValue(items);

            options.$target.triggerHandler('change.event.ui', [vals, items]);
        });

        options.build = function (index, options, selector, items, that) {
            var $selector = $(selector).empty();
            var $li = $selector.parent('li');
            if ($li.length) {

            }

            var htmls = options.buildHtml(index, options, items, that);

            var $tree = $(htmls.join('')).appendTo($selector);

            $tree.find('.nIcon').bind('click', options, function (event) {
                var options = event.data;
                $(this).toggleClass(options.icons.hideIcon).toggleClass(options.icons.showIcon);
                var $li = $(this).parent('li');
                var $liUl = $li.find('ul:eq(0)');
                var $icon = $li.find('.icon:eq(0)');
                if($liUl.length) {
                    $liUl.slideToggle('fast');
                    var icons = options.icons.treeIcon.split(',');
                    if (icons.length == 2) {
                        if($icon.hasClass(icons[0].trim()) || $icon.hasClass(icons[1].trim())) {
                            $icon.toggleClass(icons[0].trim()).toggleClass(icons[1].trim());
                        }
                    }
                }

                event.preventDefault();
                event.stopPropagation();

                return false;
            });

            $tree.find('.ui-selecttree-checkbox, .icon, span').bind('click', options, function(e){
                var options = e.data;
                var $this = $(this);
                var $li = $this.parents('li:eq(0)');
                var $checkbox = $li.find('.ui-selecttree-checkbox:eq(0)');

                var currentItem = options.$target.data($li.attr('_vid'));
                var labels = [], vals = [], item = null, items = [];


                if (options.show.multi) {

                    $li.toggleClass('active');
                    $checkbox.toggleClass(options.icons.checkIcons[0]).toggleClass(options.icons.checkIcons[1]);

                    var $lis = options.$ul.find('li.active');
                    $lis.each(function () {
                        item = options.$target.data($(this).attr('_vid'));
                        items.push(item);
                        labels.push(item[options.itemLabel]);
                        vals.push(item[options.itemValue]);
                    });

                } else {
                    var bool = $li.hasClass('active');

                    item = currentItem;

                    options.$ul.find('li.active').each(function(){
                        var $li = $(this);
                        $li.removeClass('active');
                        $li.find('.ui-selecttree-checkbox:eq(0)').removeClass(options.icons.checkIcons[1]).addClass(options.icons.checkIcons[0]);
                    });

                    if (!bool) {
                        $li.addClass('active');
                        $checkbox.removeClass(options.icons.checkIcons[0]).addClass(options.icons.checkIcons[1]);
                        items.push(item);
                        labels.push(item[options.itemLabel]);
                        vals.push(item[options.itemValue]);
                    }
                }

                options.$target.val(vals.join(options.itemValueLimit));
                options.$targetShow.val(labels.join(options.itemValueLimit));

                options.$target.triggerHandler('click.event.ui', [currentItem[options.itemValue], currentItem]);

                options.changeItemInputsValue(items);

                options.$target.triggerHandler('change.event.ui', [vals, items]);

                return false;
            });

            var expr = '', j = 1;
            for (var i = 1; i <= options.show.level; i++, j++) {
                if (j != 1) expr += 'div.ul > ul > li > ';
                if ($('> li > ' + expr + '.nIcon ', options.$ul).click().length == 1 && j < 4) i--;
            }

            var val = options.$target.val();
            options.__isFinished__ = true;
            if (val != null && val.length != 0) {
                options.$target.valChange();
            }

        };

        options.buildHtml = function (index, options, items, that) {
            var item, htmls = [], isleaf = false;
            for (var i = 0; i < items.length; i++) {
                item = items[i];

                var _vid = that.uuid(32, 64);
                options.$target.data(_vid, item);

                if (typeof item[options.data.children] != 'undefined') isleaf = false;
                else if (typeof item[options.data.isLeaf] != 'undefined' && item[options.data.isLeaf] == false) isleaf = false;
                else isleaf = true;

                htmls.push(that.fmt('<li class="{0} ui-selectree-li-{1}" _vid="{2}">', [options.css.liClass || '', item[options.data.id], _vid]));
                if (!isleaf)
                    htmls.push(that.fmt('<i class="nIcon fa {0}"></i>', [options.icons.hideIcon]));

                htmls.push('<div class="node">');
                htmls.push('<i class="xLine"></i>');

                if (i == 0)
                    htmls.push('<i class="yLine b50"></i>');
                else if (i == items.length - 1)
                    htmls.push('<i class="yLine t50"></i>');
                else
                    htmls.push('<i class="yLine"></i>');

                if (options.show.checkbox)
                    htmls.push(that.fmt('&nbsp;<i class="ui-selecttree-checkbox fa {0}"></i>&nbsp;', [options.icons.checkIcons[0]]));

                if (!isleaf)
                    htmls.push(that.fmt('<i class="icon fa {0}"></i>', [ item[options.data.icon] || options.icons.treeIcon.split(',')[0] ]));
                else
                    htmls.push(that.fmt('<i class="icon fa {0}"></i>', [ item[options.data.icon] || options.icons.leafIcon.split(',')[0] ]));

                htmls.push('<span class="">');
                htmls.push(item[options.data.name]);
                htmls.push('</span>');
                htmls.push('</div>');


                if (typeof item[options.data.children] != 'undefined'){
                    htmls.push(that.fmt('<div class="ul"><i class="yUlLine"></i><ul class="level-{0}" style="display:none;">', [index + 1]));
                    var subHtmls = options.buildHtml(index + 1, options, item[options.data.children], that);
                    htmls.push(subHtmls.join(''));
                    htmls.push('</ul></div>');
                }

                htmls.push('</li>');
            }

            return htmls;
        };

        options.buildStyle = function (options, that) {
            var styles = [];
            styles.push('<style type="text/css">');
            styles.push('.ui-tree-{uid}, .ui-tree ul, .ui-tree li {margin: 0; padding: 0; list-style: none; position: relative; overflow: visible;}');
            styles.push('.ui-tree-{uid} {font-size: {fontSize}; padding-left: 10px; margin-left: 10px; margin-right: 20px; color: {fontColor};}');
            styles.push('.ui-tree-{uid} li i.nIcon{font-size: {fontSize}; position: absolute; top: 7px;left: 3px; z-index: 13; cursor: pointer;}');
            styles.push('.ui-tree-{uid} li i.icon {font-size: {fontSize}; margin-right: 5px;}');
            styles.push('.ui-tree-{uid} li > div.node {display: block; z-index: 12; padding: 3px 3px 3px 25px; position: relative; cursor: pointer;}');
            styles.push('.ui-tree-{uid} li > div.ul {position: relative;}');
            styles.push('.ui-tree-{uid} li > div.ul .yUlLine {position: absolute; left: 9px; border-left: 1px dashed #ddd; height: 100%;}');
            styles.push('.ui-tree-{uid} li > div.ul > ul {padding-left: 25px; }');
            styles.push('.ui-tree-{uid} li:last-child > ul {border-left: none;}');
            styles.push('.ui-tree-{uid} li > div.node > i.xLine {position: absolute; left: 9px; width: 15px; height: 11px; border-bottom: 1px dashed #ccc;}');
            styles.push('.ui-tree-{uid} li > div.node > i.yLine {position:absolute; top:0; left: 9px; z-index: 11; width: 5px; height: 100%; border-left: 1px dashed #ccc;}');
            styles.push('.ui-tree-{uid} li > div.node > i.yLine.b50 {top: auto; bottom: 0; height: 50%;}');
            styles.push('.ui-tree-{uid} > li:first-child:last-child > div.node > i.yLine.b50 {border-left: none;}');
            styles.push('.ui-tree-{uid} li:first-child:last-child > div.ul > .yUlLine {border-left: none;}');
            styles.push('.ui-tree-{uid} li > div.node > i.yLine.t50 {height: 50%;}');
            styles.push('.ui-tree-{uid} li > div.ul > ul > li > div > i.yLine.b50 {top: auto; bottom: 0; height: 100%;}');
            styles.push('.ui-tree-{uid} li > div.ul > ul > li:last-child > div > i.yLine.b50 {top: 0; bottom:auto; height: 50%;}');
            styles.push('.ui-tree-{uid} li:last-child > div.ul > i.yUlLine {display: none;}');
            styles.push('.ui-tree-{uid} li span {cursor: pointer;}');
            styles.push('.ui-tree-{uid} li.active > div.node > span {color: {activeFontColor};}');
            styles.push('.ui-tree-{uid} li.active > div.node > .icon {color: {activeFontColor};}');
            styles.push('.ui-tree-{uid} li.active > div.node > .ui-selecttree-checkbox {color: {activeFontColor};}');
            styles.push('.ui-tree-{uid} .nIcon {color: {plusIconColor}; background-color: #fff;}');
            styles.push('.ui-tree-{uid} .icon {color: {nodeIconColor}; cursor: pointer;}');
            styles.push('.ui-tree-{uid} .ui-selecttree-checkbox {color: {checkboxIconColor};}');
            //styles.push('.ui-tree-{uid} li span {color: #337ab7;}');
            //styles.push('.ui-tree-{uid} li span:hover {color: #286090;}');
            styles.push('</style>');
            var styles = styles.join('');
            var uid = that.uuid(8, 16);
            options.$ul.addClass('ui-tree-' + uid).before(that.fmt(styles, {
                uid: uid,
                fontSize: options.css.fontSize,
                fontColor: options.css.fontSize,
                activeFontColor: options.css.activeFontColor,
                plusIconColor: options.css.plusIconColor,
                checkboxIconColor: options.css.checkboxIconColor,
                nodeIconColor: options.css.nodeIconColor
            }));
        };

        options.ajax = function (options, that) {

            var data = $.extend(true, {}, options.remote.otherParam);

            $.ajax(options.remote.url, {
                type: options.remote.type,
                data: data,
                cache: options.remote.cache,
                success: function(data, textStatus, jqXHR){
                    var data = options.remote.callback(data, textStatus, jqXHR);
                    if ($.isPlainObject(data)) data = data[options.remote.root];
                    options.items = data || [];
                    options.init(options, that);
                },
                dataType: options.remote.dataType || "json"
            });
        };

        /**
         * init handler
         */
        options.init = function(options, that){
            options.buildStyle(options, that);

            //options.after.call($Group, options);
            options.build(0, options, options.$ul, options.items, that);

            //options.$target.triggerHandler('change.val.ui');
        };

        options.codeFilter = function(filter, options, that) {
            options = $.extend(true, options, {
                itemLabel: options.code.itemLabel,
                itemValue: options.code.itemValue
            });
            var items = filter;
            if(options.code.enable.status) {
                items = [];
                var item = null;
                for(var i = 0; i < filter.length; i++) {
                    item = filter[i];
                    if(item[options.code.enable.key])
                        items.push(item);
                }
            }
            return items;
        };

        if (options.code.type) {
            var items = app.code.getData(options.code.type);
            options.data.id = options.data.pid = options.code.itemValue;
            options.data.name = options.code.itemLabel;
            if (typeof items == 'undefined') {
                var id = 'code_' + options.code.type + '_' + that.uuid(16, 32);
                var ecode = options.code.type + '.' + 'app.code.' +  id;
                $(app).bind(ecode, {
                    id: id,
                    options: options,
                    that: that
                }, function (e) {
                    var id = e.data.id,
                        ecode = e.data.ecode,
                        options = e.data.options,
                        that = e.data.that;

                    var items = app.code.getData(options.code.type);

                    if (typeof items == 'undefined') return false;
                    //关闭绑定事件
                    $(app).unbind(ecode, true);
                    items = options.code.callback(items[options.code.group]);
                    options.items = options.codeFilter(items, options, that);
                    options.init(options, that);
                });
            } else {
                items = options.code.callback(items[options.code.group]);
                options.items = options.codeFilter(items, options, that);
                options.init(options, that);
            }
        } else if (options.show.autoLoad) {
            if (options.items) {
                options.init(options, that);
            } else {
                options.ajax(options, that);
            }
        }
        return options.$Group;
    };


    appui.modal = {};

    appui.modal.modals = [];

    appui.modal.open = function(options){
        var that = appui;
        options = $.extend({
            type: "modal"
        }, options);

        var tmp = that.templates[options.type];
        if("undefined" == typeof(tmp)) throw new Error("appui.modal.open(options.type) Illegal parameter exception! ");
        options = $.extend(true, {}, tmp.defaults, options);

        if(!options.name) options.name = appui.uuid(16, 32);

        if(options.top != window && "undefined" != typeof options.top.appui) return options.top.appui.modal.open(options);
        else  options.top = window;

        var $modal = options.top.jQuery("#" + options.name + "Modal", options.body);
        if($modal.length)  return $modal.modal("show");

        $(app).data('context', '#' + options.name + "Modal");

        $modal = $(that.fmt(tmp.template[0], options)).appendTo(options.top.document.getElementsByTagName("body")).addClass("ui-modal");

        var $content = "";
        if(options.url){

            if (options.isWindow) {
                $content = $(that.fmt(tmp.template[1], {
                    name: options.name,
                    url: options.url,
                    cssModal: options.width == "auto" ? options.cssModal : "",
                    width: options.width == "auto" ? "100%" : options.width,
                    height: options.height == "auto" ? "100%" : options.height
                }));

                options.$target = $modal;
                $content.bind("load", options, function(e) {
                    var options = e.data;
                    if(options.height == '100%' || options.height == 'auto')
                        options.interval.index = setInterval(function() {
                            appui.modal.resize({
                                options: options,
                                $target: options.$target,
                                name: options.name,
                                parent: options.parent,
                                top: options.top,
                                param: options.param,
                                data: options.data
                            });
                            if (--options.interval.times == 0) clearInterval(options.interval.index);
                        }, options.interval.times);
                });
                $modal.find(".modal-body").append($content);
            } else {
                $modal.find(".modal-body").load(options.url + '.tpl', function () {
                    var globals = app.init(false), global;
                    options.globals = [];
                    for (var i = 0; i < globals.length; i++) {
                        global = globals[i];
                        global.context = $modal;
                        global.modal = global._modal = {
                            options: options,
                            $target: $modal,
                            name: options.name,
                            parent: options.parent,
                            top: options.top,
                            param: options.param,
                            data: options.data,
                            global:{
                                namespace: global.namespace
                            }
                        };
                        appui.modal.modals.unshift(global._modal);
                        options.globals.push(global.namespace);
                    }
                    app.init();
                });
            }
        }

        $modal.bind("show.bs.modal", options, function(e){
            var options = e.data;

            var $t = options.top.jQuery;
            if ( !$t("body").hasClass("ui-modal-open")) $t("body").addClass("ui-modal-open");
            if (typeof options.parent.jQuery != 'undefined' && options.top != options.parent) {
                var $p = options.parent.jQuery;
                if( !$p("body").hasClass("ui-modal-open")) $p("body").addClass("ui-modal-open");
            }

           if (options.isWindow){
               var sub = options.top.frames[options.name + "IFrame"];
               sub.modal = sub._modal = {
                   options: options,
                   $target: $modal,
                   name: options.name,
                   parent: options.parent,
                   top: options.top,
                   param: options.param,
                   data: options.data
               };
           }

            options.callback.show(options.parent, options);
        }).bind("shown.bs.modal", options, function(e){
            var options = e.data;

            options.callback.shown.call(options.parent, options);
        }).bind("hide.bs.modal", options, function(e){
            var options = e.data;
            options.callback.hide.call(options.parent, options);
        }).bind("hidden.bs.modal", options, function(e){
            var options = e.data;
            var $t = options.top.jQuery;
            if(!$t(".ui-modal.in").length) $t("body").removeClass("ui-modal-open");

            if (typeof options.parent.jQuery != 'undefined' && options.top != options.parent) {
                var $p = options.parent.jQuery;
                if(!$p(".ui-modal.in").length) $p("body").removeClass("ui-modal-open");
            }
            $(this).remove();
            var globals = options.globals; options.globals = null;
            app.die(globals);
            options.callback.hidden.call(options.parent, options);
            if (appui.modal.modals.length != 0){
                delete appui.modal.modals.shift();
            }
        }).bind("loaded.bs.modal", options, function(e){
            var options = e.data;
            options.callback.loaded.call(options.parent, options);
        });

        //that.modal.all.push($modal);
        $modal.modal(options.config);
    };

    appui.modal.close = function(data, modal){
        if (!modal) modal = window._modal;
        if (!modal) {
            if (this.modals.length != 0){
                modal = this.modals[ 0 ];
            }
            else return false;
        }
        if(typeof modal == 'undefined') return;

        if(data) modal.data = data;
        modal.options.data = $.extend(true, {
            closed: true
        }, modal.data);
        modal.$target.modal('hide');

        delete modal;
    };

    appui.modal.resize = function(modal){
        if (modal == null) modal = window._modal;
        if (modal == null) return false;
        var sup = modal.options.top || window;
        if (!sup.jQuery) return;

        var $contents = sup.jQuery("#" + modal.name + "IFrame");
        if (!$contents.length) return;
        var h = $contents.contents().find("body").height() || 300;
        h += 5;
        if (modal.options._resizeHeight | 0 < h) {
            modal.options._resizeHeight = h;
            $contents.height(h);
        }
    };

    appui.modal.closeAll = function(sup){
        if(!sup) sup = window._modal.options.top || window;
        if(!sup.jQuery) return;
        sup.jQuery('.ui-modal').each(function(){
           $(this).modal('hide');
        });
    };


    appui.treeGroup = function(options) {
        var that = this;
        if ("undefined" == typeof(options)) options = {type: "treeGroup"};

        var tmp = that.templates[options.type];
        if ("undefined" == typeof(tmp)) throw new Error("appui.treeGroup(options.type) Illegal parameter exception! ");
        options = $.extend(true, {}, tmp.defaults, options);

        options._isFinish = false, options._selected_ids = false;

        if (!options.show.multi && options.icons.checkIcon == tmp.defaults.icons.checkIcon) options.icons.checkIcon = 'fa-circle-o,fa-check-circle-o';

        options.icons.checkIcons = options.icons.checkIcon.split(',');
        if (options.icons.checkIcons.length < 2) throw new Error('TreeGroup icons.checkIcon required two icon.');

        if (options.show.multi && options.show.relCheck && options.icons.checkIcons.length < 3) throw new Error('TreeGroup icons.checkIcon required three icon.');

        options.icons.treeIcons = options.icons.treeIcon.split(',');
        if (options.icons.treeIcons.length < 1) throw new Error('TreeGroup icons.treeIcon required least one icon.');
        else if (options.icons.treeIcons.length == 1) options.icons.treeIcons.push(options.icons.treeIcon[0]);

        //options.before.call(that, options);

        /*chang event*/
        options.$target.bind('change.event.ui', options.events.change.data, options.events.change.handler);
        options.$target.bind('uiClick.event.ui', options.events.click.data, options.events.click.handler);

        options.build = function (index, options, selector, items, that) {
            var $selector = $(selector).empty();
            var $li = $selector.parent('li');
            if ($li.length) {

            }

            var htmls = options.buildHtml(index, options, items, that);

            var $tree = $(htmls.join('')).appendTo($selector);

            $tree.find('.nIcon').bind('click', options, function (event) {
                event.preventDefault();
                event.stopPropagation();

                var options = event.data;
                $(this).toggleClass(options.icons.hideIcon).toggleClass(options.icons.showIcon);
                var $li = $(this).parent('li');
                var $liUl = $li.find('ul:eq(0)');
                var $icon = $li.find('.icon:eq(0)');
                if($liUl.length) {
                    $liUl.slideToggle('fast');
                    $icon.toggleClass(options.icons.treeIcons[0]).toggleClass(options.icons.treeIcons[1]);
                }

                return false;
            });

            $tree.find('span, .icon, .ui-selecttree-checkbox').bind('click', options, function(event){
                event.preventDefault();
                event.stopPropagation();
                var options = event.data;
                var $this = $(this);
                var $li = $this.parents('li:eq(0)');

                var $checkbox = $li.find('.ui-selecttree-checkbox:eq(0)');

                var currentItem = options.$target.data($li.attr('_vid'));
                var labels = [], vals = [], item = null, items = [];

                if (options.show.multi) {
                    if (options.show.relCheck) {
                        if ($li.hasClass('active')) {
                            //全选时， 去除选择
                            if ($checkbox.hasClass(options.icons.checkIcons[1])) {
                                $li.removeClass('active');

                                //去除子集所有选择或半选状态
                                $li.find('li').removeClass('active').removeClass('half-active');
                                $checkbox
                                    .addClass(options.icons.checkIcons[0])
                                    .removeClass(options.icons.checkIcons[1])
                                    .removeClass(options.icons.checkIcons[2]);

                                $li.find('.ui-selecttree-checkbox')
                                    .addClass(options.icons.checkIcons[0])
                                    .removeClass(options.icons.checkIcons[1])
                                    .removeClass(options.icons.checkIcons[2]);


                            } else if ($checkbox.hasClass(options.icons.checkIcons[2])) {//半选时，全选子集
                                $li.addClass('active');

                                //子集全部为全选状态
                                $li.find('li').addClass('active').removeClass('half-active');
                                $checkbox
                                    .addClass(options.icons.checkIcons[1])
                                    .removeClass(options.icons.checkIcons[0])
                                    .removeClass(options.icons.checkIcons[2]);

                                $li.find('.ui-selecttree-checkbox')
                                    .addClass(options.icons.checkIcons[1])
                                    .removeClass(options.icons.checkIcons[0])
                                    .removeClass(options.icons.checkIcons[2]);
                            }
                        } else {//没选时， 半选
                            $li.addClass('active');
                            if ($li.find('ul').length != 0) {//有子集，半选
                                $li.addClass('half-active');
                                $checkbox
                                    .addClass(options.icons.checkIcons[2])
                                    .removeClass(options.icons.checkIcons[0])
                                    .removeClass(options.icons.checkIcons[1]);
                            } else {//没有子集， 全选
                                $checkbox
                                    .addClass(options.icons.checkIcons[1])
                                    .removeClass(options.icons.checkIcons[0])
                                    .removeClass(options.icons.checkIcons[2]);
                            }


                           /* //子集全部为全选状态
                            $li.find('li').addClass('active').removeClass('half-active');
                            $checkbox
                                .addClass(options.icons.checkIcons[1])
                                .removeClass(options.icons.checkIcons[0])
                                .removeClass(options.icons.checkIcons[2]);

                            $li.find('.ui-selecttree-checkbox')
                                .addClass(options.icons.checkIcons[1])
                                .removeClass(options.icons.checkIcons[0])
                                .removeClass(options.icons.checkIcons[2]);*/
                        }

                        //checkedAll 全选； checked 半选
                        var $parLi, _flag = true, $subLis, checkedAll, checked;
                        $parLi = $li;
                        while (_flag) {
                            if ($parLi.hasClass('half-active')) {
                                $parLi = $parLi.parents('li:eq(0)');
                                if ($parLi.hasClass('half-active')) break;

                                if (!$parLi.hasClass('active')) $parLi.addClass('active');
                                $parLi.addClass('half-active');
                                $parLi.find('.ui-selecttree-checkbox:eq(0)')
                                    .addClass(options.icons.checkIcons[1])
                                    .removeClass(options.icons.checkIcons[0])
                                    .removeClass(options.icons.checkIcons[2]);
                                continue;
                            }
                            $parLi = $parLi.parents('li:eq(0)');
                            if ($parLi.parent().hasClass('ui-tree-group')) _flag = false;
                            if ($parLi.length == 0) break;

                            $subLis = $parLi.find('ul:eq(0) > li');
                            checkedAll = true, checked = false;
                            for (var i = 0; i < $subLis.length ; i++) {
                                if ($subLis.eq(i).hasClass('active')) {
                                    if (!checked) checked = true;
                                    if (!checkedAll) break;
                                    //子集半选时， 父级也应是半选
                                    if ($subLis.eq(i).hasClass('half-active')) {
                                        checkedAll = false;  break;
                                    }
                                } else {
                                    if (checkedAll) checkedAll = false; //有一个子集未选，父级全选就没哟了
                                    if (checked) break;
                                }
                            }
                            if (checked) {
                                $parLi.addClass('active');
                                if (checkedAll) {
                                    $parLi.find('.ui-selecttree-checkbox:eq(0)')
                                        .addClass(options.icons.checkIcons[1])
                                        .removeClass(options.icons.checkIcons[0])
                                        .removeClass(options.icons.checkIcons[2]);
                                } else {
                                    $parLi.addClass('half-active');
                                    $parLi.find('.ui-selecttree-checkbox:eq(0)')
                                        .addClass(options.icons.checkIcons[2])
                                        .removeClass(options.icons.checkIcons[0])
                                        .removeClass(options.icons.checkIcons[1]);
                                }
                            } else {
                                $parLi.removeClass('active');
                                $parLi.find('.ui-selecttree-checkbox:eq(0)')
                                    .addClass(options.icons.checkIcons[0])
                                    .removeClass(options.icons.checkIcons[1])
                                    .removeClass(options.icons.checkIcons[2]);
                            }

                            //递归实现

                        }
                    } else {
                        $li.toggleClass('active');
                        $checkbox.toggleClass(options.icons.checkIcons[0]).toggleClass(options.icons.checkIcons[1]);
                    }

                    var $lis = options.$target.find('li.active');
                    $lis.each(function () {
                        item = options.$target.data($(this).attr('_vid'));
                        items.push(item);
                        labels.push(item[options.data.name]);
                        vals.push(item[options.data.id]);
                    });
                } else {
                    var bool = $li.hasClass('active');

                    item = currentItem;
                    //items.push(currentItem);

                    options.$target.find('li.active').each(function(){
                        var $li = $(this);
                        $li.removeClass('active');
                        $li.find('.ui-selecttree-checkbox:eq(0)').removeClass(options.icons.checkIcons[1]).addClass(options.icons.checkIcons[0]);
                    });

                    if (!bool) {
                        $li.addClass('active');
                        $checkbox.removeClass(options.icons.checkIcons[0]).addClass(options.icons.checkIcons[1]);
                        items.push(item);
                        labels.push(item[options.data.name]);
                        vals.push(item[options.data.id]);
                    }
                }

                options.$target.triggerHandler('uiClick.event.ui', [currentItem[options.data.id], currentItem]);

                options.$target.triggerHandler('change.event.ui', [vals, items]);

                return false;
            });

            var expr = '';
            for (var i = 1; i <= options.show.level; i++) {
                if (i != 1) expr += 'div.ul > ul > li > ';
                $('> li > ' + expr + '.nIcon ', options.$target).click();
            }

            options._isFinish = true;

            if (options._selected_ids == false && options.selectedIds.length != 0) options._selected_ids = options.selectedIds;

            if (options._selected_ids) options._selected(options._selected_ids);
        };

        options.buildHtml = function (index, options, items, that) {
            var item, htmls = [], isleaf = false, isChecked = false;
            for (var i = 0; i < items.length; i++) {
                item = items[i];

                isChecked = options.show.checked.enable && options.show.checked.handler(item[options.show.checked.key], item);

                var _vid = that.uuid(32, 64);
                options.$target.data(_vid, item);

                if (typeof item[options.data.children] != 'undefined') isleaf = false;
                else if (typeof item[options.data.isLeaf] != 'undefined' && item[options.data.isLeaf] == false) isleaf = false;
                else isleaf = true;

                htmls.push(that.fmt('<li class="{0} ui-selectree-li-{1}" _vid="{2}">', [isChecked ? 'active' : '', item[options.data.id], _vid]));
                if (!isleaf)
                    htmls.push(that.fmt('<i class="nIcon fa {0}"></i>', [options.icons.hideIcon]));

                htmls.push('<div class="node">');
                htmls.push('<i class="xLine"></i>');

                if (i == 0)
                    htmls.push('<i class="yLine b50"></i>');
                else if (i == items.length - 1)
                    htmls.push('<i class="yLine t50"></i>');
                else
                    htmls.push('<i class="yLine"></i>');

                if (options.show.checkbox)
                    htmls.push(that.fmt('&nbsp;<i class="ui-selecttree-checkbox fa {0}"></i>&nbsp;', [ isChecked ? options.icons.checkIcons[1] : options.icons.checkIcons[0]]));

                if (!isleaf)
                    htmls.push(that.fmt('<i class="icon fa {0}"></i>', [ item[options.data.icon] || options.icons.treeIcon.split(',')[0] ]));
                else
                    htmls.push(that.fmt('<i class="icon fa {0}"></i>', [ item[options.data.icon] || options.icons.leafIcon.split(',')[0] ]));

                htmls.push('<span class="">');
                htmls.push(item[options.data.name]);
                htmls.push('</span>');
                htmls.push('</div>');


                if (typeof item[options.data.children] != 'undefined'){
                    htmls.push(that.fmt('<div class="ul"><i class="yUlLine"></i><ul class="level-{0}" style="display:none;">', [index + 1]));
                    var subHtmls = options.buildHtml(index + 1, options, item[options.data.children], that);
                    htmls.push(subHtmls.join(''));
                    htmls.push('</ul></div>');
                }

                htmls.push('</li>');
            }

            return htmls;
        };

        options.buildStyle = function (options, that) {
            var styles = [];
            styles.push('<style type="text/css">');
            styles.push('.ui-tree-{uid}, .ui-tree ul, .ui-tree li {margin: 0; padding: 0; list-style: none; position: relative; overflow: visible;}');
            styles.push('.ui-tree-{uid} {font-size: {fontSize}; padding-left: 10px; margin-left: 10px; margin-right: 20px; color: {fontColor};}');
            styles.push('.ui-tree-{uid} li i.nIcon{font-size: {fontSize}; position: absolute; top: 7px;left: 3px; z-index: 13; cursor: pointer;}');
            styles.push('.ui-tree-{uid} li i.icon {font-size: {fontSize}; margin-right: 5px;}');
            styles.push('.ui-tree-{uid} li > div.node {display: block; z-index: 12; padding: 3px 3px 3px 25px; position: relative;}');
            styles.push('.ui-tree-{uid} li > div.ul {position: relative;}');
            styles.push('.ui-tree-{uid} li > div.ul .yUlLine {position: absolute; left: 9px; border-left: 1px dashed #ddd; height: 100%;}');
            styles.push('.ui-tree-{uid} li > div.ul > ul {padding-left: 25px; }');
            styles.push('.ui-tree-{uid} li:last-child > ul {border-left: none;}');
            styles.push('.ui-tree-{uid} li > div.node > i.xLine {position: absolute; left: 9px; width: 15px; height: 11px; border-bottom: 1px dashed #ccc;}');
            styles.push('.ui-tree-{uid} li > div.node > i.yLine {position:absolute; top:0; left: 9px; z-index: 11; width: 5px; height: 100%; border-left: 1px dashed #ccc;}');
            styles.push('.ui-tree-{uid} li > div.node > i.yLine.b50 {top: auto; bottom: 0; height: 50%;}');
            styles.push('.ui-tree-{uid} > li:first-child:last-child > div.node > i.yLine.b50 {border-left: none;}');
            styles.push('.ui-tree-{uid} li:first-child:last-child > div.ul > .yUlLine {border-left: none;}');
            styles.push('.ui-tree-{uid} li > div.node > i.yLine.t50 {height: 50%;}');
            styles.push('.ui-tree-{uid} li > div.ul > ul > li > div > i.yLine.b50 {top: auto; bottom: 0; height: 100%;}');
            styles.push('.ui-tree-{uid} li > div.ul > ul > li:last-child > div > i.yLine.b50 {top: 0; bottom:auto; height: 50%;}');
            styles.push('.ui-tree-{uid} li:last-child > div.ul > i.yUlLine {display: none;}');
            styles.push('.ui-tree-{uid} li span {cursor: pointer;}');
            styles.push('.ui-tree-{uid} li.active > div.node > span {color: {activeFontColor};}');
            styles.push('.ui-tree-{uid} li.active > div.node > .icon {color: {activeFontColor};}');
            styles.push('.ui-tree-{uid} li.active > div.node > .ui-selecttree-checkbox {color: {activeFontColor};}');
            styles.push('.ui-tree-{uid} .nIcon {color: {plusIconColor}; background-color: #fff;}');
            styles.push('.ui-tree-{uid} .icon {color: {nodeIconColor}; cursor: pointer; }');
            styles.push('.ui-tree-{uid} .ui-selecttree-checkbox {color: {checkboxIconColor}; cursor: pointer;}');
            //styles.push('.ui-tree-{uid} li span {color: #337ab7;}');
            //styles.push('.ui-tree-{uid} li span:hover {color: #286090;}');
            styles.push('</style>');
            var styles = styles.join('');
            var uid = that.uuid(8, 16);
            options.$target.addClass('ui-tree-' + uid).before(that.fmt(styles, {
                uid: uid,
                fontSize: options.css.fontSize,
                fontColor: options.css.fontSize,
                activeFontColor: options.css.activeFontColor,
                plusIconColor: options.css.plusIconColor,
                checkboxIconColor: options.css.checkboxIconColor,
                nodeIconColor: options.css.nodeIconColor
            }));
        };

        /**
         * init handler
         */
        options.init = function(options, that){
            options._isFinish = false;
            options.buildStyle(options, that);

            //options.after.call($Group, options);
            options.build(0, options, options.$target, options.items, that);
            
            if ( typeof options.expandAll == "boolean" && options.expandAll == true ) {
            	options.$target.find( ".nIcon" ).each( function() {
            		 $(this).removeClass(options.icons.hideIcon).addClass(options.icons.showIcon);
                     var $li = $(this).parent('li');
                     var $liUl = $li.find('ul:eq(0)');
                     var $icon = $li.find('.icon:eq(0)');
                     if($liUl.length) {
                         $liUl.slideDown('fast');
                         $icon.removeClass(options.icons.treeIcons[0]).addClass(options.icons.treeIcons[1]);
                     }
            	} );
            }
        };
        
        
        options.ajax = function (options, that) {

            var data = $.extend(true, {}, options.remote.otherParam);

            $.ajax(options.remote.url, {
                type: options.remote.type,
                data: data,
                cache: options.remote.cache,
                success: function(data, textStatus, jqXHR){
                    var data = options.remote.callback(data, textStatus, jqXHR);
                    if ($.isPlainObject(data)) {
                        _selected_ids = data[options.remote.selectedIds];
                        if (_selected_ids != null) {
                            if (typeof _selected_ids == "string") options.selectedIds = _selected_ids.split(',');
                            else if($.isArray(_selected_ids)) options.selectedIds = _selected_ids;
                        }
                        data = data[options.remote.root];
                    }
                    options.items = data || [];
                    options.init(options, that);
                },
                dataType: options.remote.dataType || "json"
            });
        };
        if (options.show.autoLoad) {
            if(options.items){
                options.init(options, that);
            } else {
                options.ajax(options, that);
            }
        }

        /*初始化选择或changval*/
        options._selected = function (ids) {
            var options = this, item, items = [], labels = [], vals = [], id;

            var $lis = options.$target.find('li.active');
            $lis.each(function () {
                $(this).find('.ui-selecttree-checkbox:eq(0)').addClass(options.icons.checkIcons[0]).removeClass(options.icons.checkIcons[1]);
                $(this).removeClass('active').removeClass('half-active');
            });


            if (ids.length == 1 && !Boolean(ids[0])) return false;

            for (var i = 0; i < ids.length; i++) {
                id = ids[i];
                if (!id) continue;
                options.$target.find('.ui-selectree-li-' + id).each(function () {
                    $(this).find('.ui-selecttree-checkbox:eq(0)').addClass(options.icons.checkIcons[1]).removeClass(options.icons.checkIcons[0]);
                    item = options.$target.data($(this).attr('_vid'));
                    $(this).addClass('active');
                });

                if(item) {
                    items.push(item);
                    labels.push(item[options.data.name]);
                    vals.push(item[options.data.id]);
                }
            }

            if (options.show.multi && options.show.relCheck) {
                var $parLi, flag;
                options.$target.find('li:not(.active)').each(function () {
                    $parLi = $(this);
                    while (!$parLi.parent().hasClass('ui-tree-group')) {
                        $parLi = $(this).parents('li:eq(0)');
                        if (!$parLi.hasClass('active')) break;
                        if ($parLi.hasClass('half-active')) break;
                        $parLi.addClass('half-active');
                        $parLi.find('.ui-selecttree-checkbox:eq(0)')
                            .addClass(options.icons.checkIcons[2])
                            .removeClass(options.icons.checkIcons[0])
                            .removeClass(options.icons.checkIcons[1]);
                    }
                });
            }

            options.$target.triggerHandler('change.event.ui', [vals, items]);
            options._selected_ids = false;
            options.selectedIds = [];
        };

        return {
            options: options,
            refresh: function (id) {
                if (id) {
                    return false;
                }

                this.options.$target.empty();
                this.options.ajax(this.options, appui);
            },
            selected: function (id) {
                if (id == null) return false;
                var ids = [];
                if (this.options.show.multi) {
                    if (!$.isArray(id)) ids.push(id);
                } else if ($.isArray(id) && id.length != 1) throw new Error(this.options.$target.selector + ' TreeGroup is single ,not set many ids.');
                else ids.push(id);


                if (this.options._isFinish == false) {
                    options._selected_ids = ids;
                    return false;
                }

                options._selected(ids);

            },
            node: function (id) {
                var item = false;
                options.$target.find('.ui-selectree-li-' + id).each(function () {
                    item = options.$target.data($(this).attr('_vid'));
                });
                return item;
            },
            selectedNodes: function () {
                var options = this.options, items = [], item = false;
                var $lis = options.$target.find('li.active');
                $lis.each(function () {
                    item = options.$target.data($(this).attr('_vid'));
                    items.push(item);
                });
                return items;
            }

        };
    };



    /*window appui*/
    win.appui = appui;

    /**
     * jquery extend appui
     */
    $.fn.extend({
        uiGroup: function(options) {
            $(this).find('.inputGroup').not('.notAuto').inputGroup(options);
            $(this).find('.inputButtonGroup').not('.notAuto').inputButtonGroup(options);
            $(this).find('.radioGroup').not('.notAuto').radioGroup(options);
            $(this).find('.checkboxGroup').not('.notAuto').checkboxGroup(options);
            $(this).find('.selectGroup').not('.notAuto').selectGroup(options);
            $(this).find('.selectAreaGroup').not('.notAuto').selectAreaGroup(options);
        },
        inputGroup: function(options){
            if ($(this).length == 0) try { console.log($(this).selector + ' appui.InputGroup not find one jQuery Element Object!'); } catch (e) {}
           return $(this).each(function(){
               var $that = $(this);
               if(!$that.hasClass('ui-group')){
                   $that.addClass('ui-group');
                   var opts = $that.attr("options") || {};
                   $that.removeAttr("options");
                   if("string" == typeof opts)
                        if(opts.indexOf('{') == 0) opts = eval('('+ opts + ')');
                        else opts = {txtLabel: opts};
                   var opts = $.extend({}, options, opts);
                   opts.$target = $that;
                   if(!opts.type) opts.type = "inputGroup";
                   appui.inputGroup(opts).addClass("ui-input-group");
               }
           });
        },
        inputButtonGroup: function (options){
            if ($(this).length == 0) try { console.log($(this).selector + ' appui.InputButtonGroup not find one jQuery Element Object!'); } catch (e) {}
            return $(this).each(function(){
                var $that = $(this);
                if(!$that.hasClass('ui-group')){
                    $that.addClass('ui-group');
                    var opts = $that.attr("options") || {};
                    $that.removeAttr("options");
                    if("string" == typeof opts)
                        if(opts.indexOf('{') == 0) opts = eval('('+ opts + ')');
                        else opts = {txtLabel: opts};
                    var opts = $.extend({}, options, opts);
                    opts.$target = $that;
                    if(!opts.type) opts.type = "inputButtonGroup";
                    appui.inputButtonGroup(opts).addClass("ui-input-button-group");
                }
            });
        },
        radioGroup: function(options){
            if ($(this).length == 0) try { console.log($(this).selector + ' appui.RadioGroup not find one jQuery Element Object!'); } catch (e) {}
            return $(this).each(function(){
                var $that = $(this);
                if(!$that.hasClass('ui-group')){
                    $that.addClass('ui-group');
                    var opts = $that.attr("options") || {};
                    $that.removeAttr("options");
                    if("string" == typeof opts)
                        if(opts.indexOf('{') == 0) opts = eval('('+ opts + ')');
                        else opts = {txtLabel: opts};
                    opts = $.extend({}, options, opts);
                    opts.$target = $that;
                    if(!opts.type) opts.type = "radioGroup";
                    appui.radioGroup(opts).addClass('ui-radio-group');
                }
            });
        },
        checkboxGroup: function(options){
            if ($(this).length == 0) try { console.log($(this).selector + ' appui.CheckboxGroup not find one jQuery Element Object!'); } catch (e) {}
            return $(this).each(function(){
                var $that = $(this);
                if(!$that.hasClass('ui-group')){
                    $that.addClass('ui-group');
                    var opts = $that.attr("options") || {};
                    $that.removeAttr("options");
                    if("string" == typeof opts)
                        if(opts.indexOf('{') == 0) opts = eval('('+ opts + ')');
                        else opts = {txtLabel: opts};
                    opts = $.extend({}, options, opts);
                    opts.$target = $that;
                    if(!opts.type) opts.type = "checkboxGroup";
                    appui.checkboxGroup(opts).addClass('ui-checkbox-group');
                }
            });
        },
        selectGroup: function(options){
            if ($(this).length == 0) try { console.log($(this).selector + ' appui.SelectGroup not find one jQuery Element Object!'); } catch (e) {}
            return $(this).each(function(){
                var $that = $(this);
                if(!$that.hasClass('ui-group')){
                    $that.addClass('ui-group');
                    var opts = $that.attr("options") || {};
                    $that.removeAttr("options");
                    if("string" == typeof opts)
                        if(opts.indexOf('{') == 0) opts = eval('('+ opts + ')');
                        else opts = {txtLabel: opts};
                    opts = $.extend({}, options, opts);
                    opts.$target = $that;
                    if(!opts.type) opts.type = "selectGroup";
                    appui.selectGroup(opts).addClass('ui-select-group');
                }
            });
        },
        selectAreaGroup: function(options){
            if ($(this).length == 0) try { console.log($(this).selector + ' appui.SelectAreaGroup not find one jQuery Element Object!'); } catch (e) {}
            return $(this).each(function(){
                var $that = $(this);
                if(!$that.hasClass('ui-group')){
                    $that.addClass('ui-group');
                    var opts = $that.attr("options") || {};
                    $that.removeAttr("options");
                    if("string" == typeof opts)
                        if(opts.indexOf('{') == 0) opts = eval('('+ opts + ')');
                        else opts = {txtLabel: opts};
                    opts = $.extend({}, options, opts);
                    opts.$target = $that;
                    if(!opts.type) opts.type = "selectAreaGroup";
                    appui.selectAreaGroup(opts).addClass('ui-selectarea-group');
                }
            });
        },
        selectTreeGroup: function(options){
            if ($(this).length == 0) try { console.log($(this).selector + ' appui.SelectTreeGroup not find one jQuery Element Object!'); } catch (e) {}
            return $(this).each(function(){
                var $that = $(this);
                if(!$that.hasClass('ui-group')){
                    $that.addClass('ui-group');
                    var opts = $that.attr("options") || {};
                    $that.removeAttr("options");
                    if("string" == typeof opts)
                        if(opts.indexOf('{') == 0) opts = eval('('+ opts + ')');
                        else opts = {txtLabel: opts};
                    opts = $.extend({}, options, opts);
                    opts.$target = $that;
                    if(!opts.type) opts.type = "selectTreeGroup";
                    appui.selectTreeGroup(opts).addClass('ui-selecttree-group');
                }
            });
        },
        treeGroup: function(options){
            if ($(this).length != 1) try { console.log($(this).selector + ' appui.TreeGroup not only one jQuery Element Object!'); } catch (e) {}
            var opts = options;
            opts.$target = $(this).addClass('ui-tree-group');
            if (!opts.type) opts.type = "treeGroup";
            if (!$(this).hasClass('ui-tree')) $(this).addClass('ui-tree');
            return appui.treeGroup(opts);
        },
        valChange: function(val){
           return $(this).each(function () {
               if(val != null) {
                   /*if($.isPlainObject(val))
                       $(this).data('VALUE', val);
                   else $(this).val(val);*/
                   $(this).data('VALUE', val).val(val)
               }
               return $(this).triggerHandler("change.val.ui");
           });
        },
        readOnly: function(readOnly) {
            var bool = Boolean(readOnly);
            return $(this).each(function(){
                var $parent = $(this).parents('.form-group, .control-group').eq(0);
                if ($(this).hasClass('ui-group')) {
                    if (bool) {
                        $parent.find(':input').attr('readOnly', 'readOnly');
                        $parent.find('input[type=button], button').attr('disabled', 'disabled');
                    } else {
                        $parent.find(':input').removeAttr('readOnly');
                        $parent.find('input[type=button], button').removeAttr('disabled');
                    }
                } else
                    if (bool) $(this).attr('readOnly', 'readOnly');
                    else $(this).removeAttr('readOnly');
            });
        },
        disabled: function(disabled) {
            var bool = Boolean(disabled);
            return $(this).each(function(){

                if ($(this).hasClass('ui-group'))
                    if(bool)
                        $(this).parents('.form-group, .control-group').eq(0).find(':input').attr('disabled', 'disabled');
                    else
                        $(this).parents('.form-group, .control-group').eq(0).find(':input').removeAttr('disabled');
                else
                    if (bool) $(this).attr('disabled', 'disabled');
                    else $(this).removeAttr('disabled');
            });
        },
        visible: function(visible) {
            var bool = Boolean(visible);
            return $(this).each(function(){

                if ($(this).hasClass('ui-group'))
                    if(bool)
                        $(this).parents('.form-group, .control-group').show();
                    else
                        $(this).parents('.form-group, .control-group').hide();
                else
                    if (bool) $(this).show();
                    else $(this).hide();
            });
        }
    });

})(jQuery);
