/**
 * Created by Mifeng.He(bee) on 2015/10/11.
 */
(function($, app, appui){

    appui.page = {};

    appui.page.z_index = 99;


    appui.templates.page = {
        defaults: {
            id: 'auto',
            prevPage: 'auto',
            context: '#sections',
            url: '',
            top: top,
            parent: window,
            param: {},
            data: {},
            _filter: '.ui-main-loading, .modal, .nav-tabs-header',
            filter: '',
            effect: 'fade',
            empty: false,
            main: function () {
                return app.init();
            },
            callback: {
                loaded: function (options) {},
                show: function(options){},
                shown: function(options){},
                hide: function(options){},
                hidden: function(options){}
            }
        }
    };

    appui.page.remove = function (options) {
        if(typeof options == 'undefined' || options == null || !$.isPlainObject(options)) return false;

        if (!options.empty) {
        	if (!options.$prevPage.is(":empty")) options.$prevPage.addClass('active').show();
        	else options.$prevPage.remove();
        }

        options.$page.remove();

        if (typeof options.globals != 'undefined') app.die(options.globals);
        delete  options;
        return false;
    };
    appui.page.close = function (data, options) {

        if (typeof data == 'string') {
            options = $(options).data('UI-PAGE-OPTIONS');
            options.data = {};
        }

        if(typeof data == 'undefined' || !$.isPlainObject(data)) options.data = {};

        if (options == null) {
            options = $('.ui-page.active:eq(0)').data('UI-PAGE-OPTIONS');
        }

        if(typeof options == 'undefined' || options == null || !$.isPlainObject(options)) return false;

        options.$body.addClass('ui-page-opening');

        if (!options.empty) options.$prevPage.addClass('active').show();
        options.callback.hide.call(options.parent, options);

        options.$page.removeClass('active');


        if (options.effect == 'slideUp')
            options.$loading.slideDown('slow', function() {
                options.$body.removeClass('ui-page-opening');
                options.$page.remove();
                options.callback.hidden.call(options.parent, options);
            });
        else if (options.effect == 'fade')
            options.$loading.fadeOut('slow', function() {
                options.$body.removeClass('ui-page-opening');
                options.$page.remove();
                options.callback.hidden.call(options.parent, options);
            });
        else {
            options.$body.removeClass('ui-page-opening');
            options.$page.remove();
            options.callback.hidden.call(options.parent, options);
        }


        if (typeof options.globals != 'undefined')
            for (var i = 0; i < options.globals.length; i++) {
                try {
                    $(app).removeData(options.globals[i]);
                    eval('delete ' + options.globals[i])
                } catch (e) {
                    try {console.log(e);} catch(e){}
                };
            }

        delete  options;
        return false;
    };

    appui.page.init = function (options) {
        if(options.prevPage == 'auto') {
            options.$prevPage = $('.ui-page.active');
            if(options.$prevPage.length == 0) {
                if(options._filter.length != 0 && options.filter.length != 0) options.filter += ',' + options.filter;
                options.$prevPage = $('<div class=""></div>').append(options.$body.children().not(options._filter + options.filter));
                options.$prevPage.appendTo(options.$body);
            }
        } else {
            options.$prevPage = $(options.prevPage);
        }

        options.$prevPage.addClass('ui-page').removeClass('active');

        options.$prevPage.hide();
    };

    appui.page.open = function (options) {
        var that = appui;

        if (typeof options != 'undefined' && $.isPlainObject(options)) {
            if (typeof options.$page != 'undefined') {
                //options.$body.addClass('ui-page-context');

                options.$body.addClass('ui-page-opening');

                var zIndex =  ++appui.page.z_index;

                options.$page.css({'z-index': zIndex + 1});

                options.$page.fadeIn();

                if (!options.empty) this.init(options);

                appui.page.openEffect(options);

                return options;
            }


        }

        var options = $.extend(true, {}, that.templates.page.defaults, options);

        if (!$.inArray(options.effect, ['fade', 'slideDown', 'slideUp'])) options.effect = 'fade';

        options._isInit = true;

        var uuid = that.uuid(16, 32);
        var id = uuid + 'UIPage';
        var zIndex =  ++appui.page.z_index;
        
        options.$body = $(options.context);

        if (options.empty) options.$body.empty();

        options.$body.addClass('ui-page-context');

        options.$body.addClass('ui-page-opening');

        if (!options.empty) this.init(options);

        options.$page = $(that.fmt('<div id="{0}" class="ui-page" style="z-index: {1}"></div>', [id, zIndex + 1])).appendTo(options.$body);

        if ($.inArray(options.effect, ['fade', 'slideUp']) != -1) {
            options.$loading = $('<div class="ui-page-loading"></div>').appendTo(options.$page);
            options.$container = $('<div class="ui-page-container"></div>').appendTo(options.$page);
        } else if($.inArray(options.effect, ['slideDown']) != -1) {
            options.$container = $('<div class="ui-page-container" style="display: none;" data-spy="affix"></div>').appendTo(options.$page);
            options.$loading = $('<div class="ui-page-loading"></div>').appendTo(options.$page);
        } else {
            options.$loading = $('<div class="ui-page-loading"></div>').appendTo(options.$page);
            options.$container = $('<div class="ui-page-container"></div>').appendTo(options.$page);
        }

        options.$container.load(options.url, function () {
            if (!options.empty) options.$close = $('<div class="ui-page-close"><i href="javascript:;" class="fa fa-power-off fa-2x"></i></div>').appendTo(options.$container);

            if (!options.empty) options.$close.bind('click', options, function (event) {
                var options = event.data;
                appui.page.close(options);
            });

            options.callback.loaded.call(options.parent, options);

            if(options.effect != 'slideDown') options.$container.fadeIn('fast');

            var globals = app.init(false), global;
            options.globals = [];
            for (var i = 0; i < globals.length; i++) {
                global = globals[i];
                global.context = options.$page;
                global.page = {
                    options: options,
                    param : options.param,
                    global:{
                        namespace: global.namespace
                    }
                };
                options.globals.push(global.namespace);
            }

            app.init();

            appui.page.openEffect(options);

        });
        options.$page.data('UI-PAGE-OPTIONS', options);

        options._isInit = false;


        return options;

    };

    appui.page.openEffect = function (options) {
        if (options.effect == 'slideUp')
            options.$loading.slideUp('fast', function() {
                options.$body.removeClass('ui-page-opening');
                if (options._isInit) options.callback.shown.call(options.parent, options);
                options.$page.addClass('active');
            });
        else if (options.effect == 'fade')
            options.$loading.fadeOut('fast', function() {
                options.$body.removeClass('ui-page-opening');
                if (options._isInit) options.callback.shown.call(options.parent, options);
                options.$page.addClass('active');
            });
        else if(options.effect == 'slideDown')
            options.$container.slideDown('slow', function() {
                options.$loading.hide();
                options.$body.removeClass('ui-page-opening');
                if (options._isInit)  options.callback.shown.call(options.parent, options);
                options.$page.addClass('active');
            });
        else{
            options.$loading.hide();
            options.$container.show();
            options.$body.removeClass('ui-page-opening');
            if (options._isInit) options.callback.shown.call(options.parent, options);
            options.$page.addClass('active');
        }
    }

})(jQuery, app, appui);