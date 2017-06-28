/**
 * Created by Administrator on 2015/10/16.
 */
(function () {

  require( ["static/js/protal/protal.password"] );

//Make sure jQuery has been loaded before protal.js
	if (typeof jQuery === "undefined") {
		throw new Error("protal requires jQuery");
	}

	$.protal = {
		animationSpeed: 500,
		screenSizes: {
			xs: 480,
			sm: 768,
			md: 992,
			lg: 1200
		}
	};


	$.protal.options = {
		navbarMenuSlimscroll: true,
		navbarMenuSlimscrollWidth: "3px", //The width of the scroll bar
		navbarMenuHeight: "200px", //The height of the inner menu


		animationSpeed: 500,
		sidebarToggleSelector: "[data-toggle='offcanvas']",
		sidebarPushMenu: true,
		sidebarSlimScroll: true,

		sidebarExpandOnHover: false,

		enableFastclick: true,
		//Control Sidebar Options
		enableControlSidebar: true,
		controlSidebarOptions: {
			//Which button should trigger the open/close event
			toggleBtnSelector: "[data-toggle='control-sidebar']",
			//The sidebar selector
			selector: ".control-sidebar",
			//Enable slide over content
			slide: true
		},

		screenSizes: {
			xs: 480,
			sm: 768,
			md: 992,
			lg: 1200
		}
	};


	/* ControlSidebar
	 * ==============
	 * Adds functionality to the right sidebar
	 *
	 * @type Object
	 * @usage $.protal.controlSidebar.activate(options)
	 */
	$.protal.controlSidebar = {
		//instantiate the object
		activate: function () {
			//Get the object
			var _this = this;
			//Update options
			var o = $.protal.options.controlSidebarOptions;
			//Get the sidebar
			var sidebar = $(o.selector);
			//The toggle button
			var btn = $(o.toggleBtnSelector);

			//Listen to the click event
			btn.on('click', function (e) {
				e.preventDefault();
				//If the sidebar is not open
				/*if (!sidebar.hasClass('control-sidebar-open')
					&& !$('body').hasClass('control-sidebar-open')) {
					//Open the sidebar
					_this.open(sidebar, o.slide);
				} else {
					_this.close(sidebar, o.slide);
				}*/
				
				$("#frameTabs").empty();
				var page = $.protal.page;
				page.items = [];
				$.each(page.pages, function (index, options) {
					appui.page.remove(options);
				});
				page.pages = [];
				$("#mainWelcome").fadeIn();
				return false;
			});

			//If the body has a boxed layout, fix the sidebar bg position
			var bg = $(".control-sidebar-bg");
			_this._fix(bg);

			//If the body has a fixed layout, make the control sidebar fixed
			if ($('body').hasClass('fixed')) {
				_this._fixForFixed(sidebar);
			} else {
				//If the content height is less than the sidebar's height, force max height
				if ($('.content-wrapper, .right-side').height() < sidebar.height()) {
					_this._fixForContent(sidebar);
				}
			}
		},
		//Open the control sidebar
		open: function (sidebar, slide) {
			//Slide over content
			if (slide) {
				sidebar.addClass('control-sidebar-open');
			} else {
				//Push the content by adding the open class to the body instead
				//of the sidebar itself
				$('body').addClass('control-sidebar-open');
			}
		},
		//Close the control sidebar
		close: function (sidebar, slide) {
			if (slide) {
				sidebar.removeClass('control-sidebar-open');
			} else {
				$('body').removeClass('control-sidebar-open');
			}
		},
		_fix: function (sidebar) {
			var _this = this;
			if ($("body").hasClass('layout-boxed')) {
				sidebar.css('position', 'absolute');
				sidebar.height($(".wrapper").height());
				$(window).resize(function () {
					_this._fix(sidebar);
				});
			} else {
				sidebar.css({
					'position': 'fixed',
					'height': 'auto'
				});
			}
		},
		_fixForFixed: function (sidebar) {
			sidebar.css({
				'position': 'fixed',
				'max-height': '100%',
				'overflow': 'auto',
				'padding-bottom': '50px'
			});
		},
		_fixForContent: function (sidebar) {
			$(".content-wrapper, .right-side").css('min-height', sidebar.height());
			$("#sections").css("height", sidebar.height() - 45 );
			$("#mainWelcome").css("height", sidebar.height());
			$(".main-sidebar").slimScroll({height: sidebar.height()});
		}
	};


	/* Layout
	 * ======
	 * Fixes the layout height in case min-height fails.
	 *
	 * @type Object
	 * @usage $.protal.layout.activate()
	 *        $.protal.layout.fix()
	 *        $.protal.layout.fixSidebar()
	 */
	$.protal.layout = {
		activate: function () {
			var that = this;
			that.fix();
			that.fixSidebar();
			$(window, ".wrapper").resize(function () {
				that.fix();
				that.fixSidebar();
			});
		},
		fix: function () {
			//Get window height and the wrapper height
			var neg = $('.main-header').outerHeight() + $('.main-footer').outerHeight();
			var window_height = $(window).height();
			var sidebar_height = $(".sidebar").height();
			//Set the min-height of the content and sidebar based on the
			//the height of the document.
			if ($("body").hasClass("fixed")) {
				$(".content-wrapper, .right-side").css('min-height', window_height - $('.main-footer').outerHeight());
				$("#sections").css("height", window_height - $('.main-footer').outerHeight() - 45 );
				$("#mainWelcome").css("height", window_height - $('.main-footer').outerHeight());
			} else {
				var postSetWidth;
				if (window_height >= sidebar_height) {
					$(".content-wrapper, .right-side").css('min-height', window_height - neg);
					$("#sections").css("height", window_height - neg - 45 );
					$("#mainWelcome").css("height", window_height - neg);
					postSetWidth = window_height - neg;
				} else {
					$(".content-wrapper, .right-side").css('min-height', sidebar_height);
					$("#sections").css("height", sidebar_height - 45 );
					$("#mainWelcome").css("height", sidebar_height);
					postSetWidth = sidebar_height;
				}

				//Fix for the control sidebar height
				/* var controlSidebar = $($.protal.options.controlSidebarOptions.selector);
				 if (typeof controlSidebar !== "undefined") {
				 if (controlSidebar.height() > postSetWidth)
				 $(".content-wrapper, .right-side").css('min-height', controlSidebar.height());
				 }*/

			}
		},
		fixSidebar: function () {

			//Make sure the body tag has the .fixed class
			if (!$("body").hasClass("fixed")) {
				if (typeof $.fn.slimScroll != 'undefined') {
					$(".sidebar").slimScroll({destroy: true}).height("auto");
				}
				return;
			} else if (typeof $.fn.slimScroll == 'undefined' && window.console) {
				window.console.error("Error: the fixed layout requires the slimscroll plugin!");
			}

			//Enable slimscroll for fixed layout
			if ($.protal.options.sidebarSlimScroll) {
				if (typeof $.fn.slimScroll != 'undefined') {
					//Destroy if it exists
					$(".sidebar").slimScroll({destroy: true}).height("auto");
					//Add slimscroll
					$(".sidebar").slimscroll({
						height: ($(window).height() - $(".main-header").height()) + "px",
						color: "rgba(0,0,0,0.2)",
						size: "3px"
					});
				}
			}

		}
	};


	/* PushMenu()
	 * ==========
	 * Adds the push menu functionality to the sidebar.
	 *
	 * @type Function
	 * @usage: $.protal.pushMenu("[data-toggle='offcanvas']")
	 */
	$.protal.pushMenu = {
		activate: function (toggleBtn) {

			//Get the screen sizes
			var screenSizes = $.protal.options.screenSizes;

			//Enable sidebar toggle
			$(toggleBtn).on('click', function (e) {
				e.preventDefault();
				e.stopPropagation();

				//Enable sidebar push menu
				if ($(window).width() > (screenSizes.sm - 1)) {
					if ($("body").hasClass('sidebar-collapse')) {
						$("body").removeClass('sidebar-collapse').trigger('expanded.pushMenu');
						$.removeCookie('protal-sidebar-class')
					} else {
						$("body").addClass('sidebar-collapse').trigger('collapsed.pushMenu');
						$.cookie('protal-sidebar-class', 'sidebar-collapse');
					}
				}
				//Handle sidebar push menu for small screens
				else {
					if ($("body").hasClass('sidebar-open')) {
						$("body").removeClass('sidebar-open').removeClass('sidebar-collapse').trigger('collapsed.pushMenu');
						$.removeCookie('protal-sidebar-class')
					} else {
						$("body").addClass('sidebar-open').trigger('expanded.pushMenu')
						$.cookie('protal-sidebar-class', 'sidebar-open');
					}
				}
				return false;
			});


			$(".content-wrapper").click(function () {
				//Enable hide menu when clicking on the content-wrapper on small screens
				if ($(window).width() <= (screenSizes.sm - 1) && $("body").hasClass("sidebar-open")) {
					$("body").removeClass('sidebar-open');
				}
			});

			//Enable expand on hover for sidebar mini
			if ($.protal.options.sidebarExpandOnHover
				|| ($('body').hasClass('fixed')
				&& $('body').hasClass('sidebar-mini'))) {
				this.expandOnHover();
			}
		},
		expandOnHover: function () {
			var that = this;
			var screenWidth = $.protal.options.screenSizes.sm - 1;
			//Expand sidebar on hover
			$('.main-sidebar').hover(function () {
				if ($('body').hasClass('sidebar-mini')
					&& $("body").hasClass('sidebar-collapse')
					&& $(window).width() > screenWidth) {
					that.expand();
				}
			}, function () {
				if ($('body').hasClass('sidebar-mini')
					&& $('body').hasClass('sidebar-expanded-on-hover')
					&& $(window).width() > screenWidth) {
					that.collapse();
				}
			});
		},
		expand: function () {
			$("body").removeClass('sidebar-collapse').addClass('sidebar-expanded-on-hover');
		},
		collapse: function () {
			if ($('body').hasClass('sidebar-expanded-on-hover')) {
				$('body').removeClass('sidebar-expanded-on-hover').addClass('sidebar-collapse');
			}
		}

	};

	/* Tree()
	 * ======
	 * Converts the sidebar into a multilevel
	 * tree view menu.
	 *
	 * @type Function
	 * @Usage: $.protal.tree('.sidebar')
	 */
	$.protal.tree = function (menu) {

		var animationSpeed = $.protal.options.animationSpeed;

		$(document).on('click', menu + ' li a:not(.menu-parent)', function (event) {
			//Get the clicked link and the next element
			var $that = $(this);
			var $subMenu = $that.next();

			//Check if the next element is a menu and is visible
			if (($(this).parent().is('.tree-view')) && ($subMenu.is(':visible'))) {
				//Close the menu
				$subMenu.slideUp(animationSpeed, function () {
					$subMenu.removeClass('menu-open');
					//Fix the layout in case the sidebar stretches over the height of the window
					//_this.layout.fix();
				});
				$subMenu.parent("li").removeClass("active");
			}
			//If the menu is not visible
			else if (($(this).parent().is('.tree-view')) && (!$subMenu.is(':visible'))) {
				//Get the parent menu
				var parent = $that.parents('ul').first();
				//Close all open menus within the parent
				var ul = parent.find('ul:visible').slideUp(animationSpeed);
				//Remove the menu-open class from the parent
				ul.removeClass('menu-open');
				//Get the parent li
				var parent_li = $that.parent("li");

				//Open the target menu and add the menu-open class
				$subMenu.slideDown(animationSpeed, function () {
					//Add the class active to the parent li
					$subMenu.addClass('menu-open');
					parent.find('li.active').removeClass('active');
					parent_li.addClass('active');
					//Fix the layout in case the sidebar stretches over the height of the window
					$.protal.layout.fix();
				});
			}
			//if this isn't a link, prevent the page from being redirected
			if ($subMenu.is('.tree-view-menu')) {
				event.preventDefault();
			}

			if ($subMenu.length == 0) {
				event.preventDefault();
				event.stopPropagation();
				var parent_li = $(this).parent();
				var parent_ul = parent_li.parent();
				parent_ul.find('li.active').removeClass('active');
				parent_li.addClass('active');
				var id = $(this).attr('id');
				$.protal.page.open($.protal.leftMenu.items[id]);

				return false;
			}
		});
		
		
		$(document).on('click', menu + ' li a.menu-parent', function (event) {
			event.preventDefault();
			event.stopPropagation();
			$( "#sidebarMenuChild" ).show();
			var level = $( this ).attr( "data-level" );
			var uls = $( "#sidebarMenuChild" ).find( ".child-menu-container > ul" );
			uls.hide();
			
			var format = appui.fmt; 
			
			uls.filter( format( "[data-level=\"\{0}\"]", [ level ] ) ).show();
			
			console.log( uls.filter( format( "[data-level=\"\level-{0}\"]", [ level ] ) ) );
			
			$( document ).one( "click", function() {
				$( "#sidebarMenuChild" ).hide();
			} );
			return false;
		} );
		
		$(document).on('click', ' #sidebarMenuChild li a', function (event) {
			event.preventDefault();
			event.stopPropagation();
			var parent_li = $(this).parent();
			var parent_ul = parent_li.parent();
			parent_li.addClass('active');
			var id = $(this).attr('id'); 
			$.protal.page.open($.protal.leftMenu.items[id]);
			$( "#sidebarMenuChild" ).hide();
			return false;
		});
	};

	/* leftmenu()
	 * ======
	 * Converts the sidebar into a multilevel
	 * leftmenu view menu.
	 *
	 * @type Function
	 * @Usage: $.protal.leftMenu.ajax()
	 */
	$.protal.leftMenu = {
		ajax: function () {
			$.ajax({
				url: 'protal/menu/list.json',
				type: 'POST',
				success: function (data) {
					if (!data.success) return false;
					var menus = app.toTreeJson(data.list, {
						id: 'id',
						pid: 'parMenuId',
						children: 'list'
					});
					if (menus.length == 1 && menus[0].list.length != 0) menus = menus[0].list || [];
					$.protal.leftMenu.init('#leftMenu.sidebar-menu', menus);
				}
			});
		},
		items: {},
		init: function (leftMenu, menus) {
			var $leftMenu = $(leftMenu);

			/*leftMenus.push({ id: '', name: '菜单管理',  url: 'menu/index', icon: 'fa fa-list-alt', list: []});
			 leftMenus.push({ id: '', name: '机构管理',  url: 'org/index', icon: 'fa fa-list-ul', list: []});
			 leftMenus.push({ id: '', name: '用户管理',  url: 'staff/index', icon: 'fa fa-user-plus', list: []});
			 leftMenus.push({ id: '', name: '角色管理',  url: 'role/index', icon: 'fa fa-user-md', list: []});
			 leftMenus.push({ id: '', name: '码表管理',  url: 'code/type/index', icon: 'fa fa-th-list', list: []});
			 leftMenus.push({ id: '', name: '业务参数',  url: 'param/index', icon: 'fa fa-tasks', list: []});*/
			var _html = [];
			var item = false;
			for (var i = 0; i < menus.length; i++) {
				item = menus[i];
				_html = _html.concat(this.build(item, i));
			}
			$leftMenu.append(_html.join(''));

			//Enable sidebar tree view controls
			$.protal.tree('#leftMenu.sidebar-menu');
			$.protal.history.activate('.history-sidebar-menu');
			$.protal.page.activate('.history-sidebar-menu');
			if (typeof PAGE_SKIP_PAGE_ID != 'undefined') {
				var $skip = $('#page_' + PAGE_SKIP_PAGE_ID), $skipParent = $skip.parent();

				while (!$skipParent.hasClass('sidebar-menu')) {
					if ($skipParent.is('li')) $skipParent.addClass('active');
					else if ($skipParent.is('ul')) $skipParent.addClass('menu-open');
					$skipParent = $skipParent.parent();
				}

				$skip.trigger('click');
			}
			
			var $childMenu = $( "#sidebarMenuChild > div" );
			
			var menu, htmls = [];
			for ( var index = 0; index < menus.length; index++ ) {
				menu = menus[ index ];
				
				if ( typeof menu.list == "object" && $.isArray( menu.list ) ) {
					htmls.push( this.buildChild( menu.list, "level-" + index ) );
				}
			}
			$childMenu.html( htmls.join( "" ) );
			
			$("#sidebarMenuChild >div").slimScroll({
				 position: 'right',
				 color: "#ccc",
				 height: $( window ).height() - 50 - 5
			});
			
			
		},
		build: function( item, index ) {
			if (item.id.length == 0) item.id = appui.uuid(16, 32);
			item.id = 'page_' + item.id;
			this.items[item.id] = item;
			

			var html = [];
			html.push('<li class="tree-view">');
			html.push(appui.fmt('<a id="{id}" href="{href}" url="{url}" target="{target}" class="{parent}" data-level="{level}"><i class="{icon}"></i> <span>{name}</span>', {
				id: item.id,
				href: Boolean(item.url && $.trim(item.url) != 'javascript:;') ? '?jsonStr=' + item.id.substring(5) : item.url,
				url: item['url'],
				icon: item['img'],
				name: item['menuName'],
				target: item['target'] || '_self',
				parent:  ( typeof item.list != 'undefined' && $.isArray(item.list) && item.list.length != 0 ) ? "menu-parent" : "",
				level: "level-" + index
			}));

			if (typeof item.list != 'undefined' && $.isArray(item.list) && item.list.length != 0) {
				html.push('<i class="fa fa-angle-left pull-right"></i></a>');
				/*html.push('<ul class="tree-view-menu">');
				for (var i = 0; i < item.list.length; i++) {
					html.push(this.build(item.list[i]).join(''));
				}
				html.push('</ul>');*/
			} else html.push('</a>')

			html.push('</li>');
			return html;
		},
		buildChild: function (items, level) {
			
			var item, htmls, format;
			
			format = appui.fmt;
			htmls = [];
			
			if ( typeof level == "undefined" ) level = "";
			
			htmls.push( format( '<ul data-level="{0}">', [ level ] ) );
		
			for ( var index = 0; index < items.length; index++ ) {
				item = items[ index ];
				if (item.id.length == 0) item.id = appui.uuid(16, 32);
				item.id = 'page_' + item.id;
				this.items[ item.id ] = item;
				
				htmls.push( format( '<li><a  id="{id}" href="{href}" url="{url}" target="{target}"><i class="fa fa-square"></i> <span>{name}</span></a></li>', {
					id: item.id,
					href: Boolean(item.url && $.trim(item.url) != 'javascript:;') ? '?jsonStr=' + item.id.substring(5) : item.url,
					url: item['url'],
					icon: item['img'],
					name: item['menuName'],
					target: item['target'] || '_self'
				} ) );
				
				if ( typeof item.list == "object" && $.isArray( item.list ) ) {
					htmls.push( this.buildChild( item.list ) );
				}
			}
			
			htmls.push( "</ul>" )
			
			return htmls.join( "" );
		}
	};

	/* histroy()
	 * ======
	 * Converts the sidebar into a multilevel
	 * tree view menu.
	 *
	 * @type Function
	 * @Usage: $.protal.history('.history-sidebar-menu')
	 */
	$.protal.history = {
		activate: function (history) {
			$(history).delegate('a', 'click', function (event) {
				event.preventDefault();
				event.stopPropagation();

				$('[data-toggle=control-sidebar]').blur().triggerHandler('click');

				var $li = $(this).parent();

				var index = $li.index();
				var page = $.protal.page;
				if (index + 1 == page.pages.length) return false;

				var items = page.items.splice(index, 1);
				page.items.push(items[0]);

				var pages = page.pages.splice(index, 1);
				page.pages.push(pages[0]);

				$li.parent().append($li);

				appui.page.open(pages[0]);
				return false;
			});
		}
	};

	/* page.open()
	 * ======
	 * Converts the sidebar into a multilevel
	 * tree view menu.
	 *
	 * @type Function
	 * @Usage: $.protal.page.open('.history-sidebar-menu')
	 */
	$.protal.page = {
		pages: [],
		items: [],
		activate: function (histroy) {
			this.$history = $(histroy);
		},
		open: function (item) {
			var index = $.inArray(item, this.items);
			if (index != -1) {
				var page = $.protal.page;
				delete page.items.splice(index, 1)[0];

				var options = page.pages.splice(index, 1);
				this.$history.children().eq(index).remove();

				appui.page.remove(options[0]);
				delete options;
			}

			this.items.push(item);
			
			if ( $("#mainWelcome").is(":visible") ) {
				$("#mainWelcome").hide();
			}
			
			var page;
			if ( item.url.indexOf("http://") != -1 || item.url.indexOf("https://") != -1 ) {
				page = appui.page.open({
					url: 'iframe.tpl',
					context: '#sections',
					effect: 'slideUp',
					empty: false,
					param: {
						url: item.url
					}
				});
			} else {
				page = appui.page.open({
					url: item.url + '.tpl',
					context: '#sections',
					effect: 'slideUp',
					empty: false
				});
			}

			this.pages.push(page);

			page.callback._protal_loaded = page.callback.loaded;

			page.callback.loaded = function (page) {
				page.$close.bind('click', item, function (event) {
					event.preventDefault();
					event.stopPropagation();
					var item = event.data;
					var page = $.protal.page;
					var index = $.inArray(item, page.items);
					delete page.items.splice(index, 1)[0];
					delete page.pages.splice(index, 1)[0];
					delete item;
					return false;
				});
			};
			
			var $frameTabs = $("#frameTabs");
			$("#frameTabs").find("li.active").each(function () {
				$(this).removeClass("active");
			
			});
			
			var $tab = $frameTabs.find( "[itemid='"+item.id+"']" );
			if ( $tab.length > 0 ) {
				$tab.appendTo($frameTabs).addClass("active");
			} else {
				$('<li class="active"><a href="javascript:;"><i class="fa fa-caret-right"></i> '+ item['menuName'] +'</a><i class="fa fa-remove nav-tabs-close"></i></li>')
				.appendTo($frameTabs).attr("itemid", item.id).data("item", item);
			}

			/*this.$history.append(appui.fmt('<li class="tree-view"><a id="{id}" href="{url}" target="{target}"><i class="{icon}"></i> <span>{name}</span></a></li>', {
				id: item.id,
				url: item.url,
				icon: item['img'],
				name: item['menuName'],
				target: item['target'] || '_self'
			}));*/

		}
	};
	
	$.protal.tabs = function() {
			var $frameTabsHeader = $("#frameTabsHeader")
			, $frameTabs = $("#frameTabs")
			, $sections = $("#sections");
		$frameTabs.data("left", 0);
		var count = -1;
		$frameTabs.data("width", $frameTabsHeader.width());
		
		$("#frameTabsHeader .nav-tabs-arrow-left").bind('click', function (event) {
			if (count < 0) {
				if (count != -1) count = -1;
				return false;
			}
			var left = 0
			$("#frameTabs li").each(function ( index ) {
				if (index >= count ) return false;
				left += $(this).outerWidth();
			});
			$frameTabs.data("left", left);
			if (left > 1) left -= 1;
			left = (0 - left) + "px";
			$frameTabs.animate({left: left});
			count--;
			return false;
		});
		
		$("#frameTabsHeader .nav-tabs-arrow-right").bind('click', function (event) {
			var left =  Number($frameTabs.data("left")) || 0;
			var headerWidth = $frameTabsHeader.outerWidth();
			var tabsWidth = 50 * 2 - left;
			console.log(left)
			$("#frameTabs li").each(function (index) {
				tabsWidth += $(this).outerWidth();
				console.log(tabsWidth + "==" + $(this).outerWidth() + "==" + index);
				if (headerWidth < tabsWidth) {
					left += tabsWidth - headerWidth;
					$frameTabs.data("left", left);
					left = (0 - left) + "px";
					$frameTabs.animate({left: left});
					count++;
					return false;
				}
			});
			return false;
		});
		
		$frameTabs.delegate("li > .nav-tabs-close", "click", function (event) {
			var $li = $(this).parent();
			var item = $li.data("item");
			if (item) {
				
				var page = $.protal.page;
				var index = $.inArray( item, page.items );
				delete page.items.splice(index, 1)[0];

				var options = page.pages.splice(index, 1);
				appui.page.remove(options[0]);
				var $parent = $li.parent();
				$li.remove();
				var $last = $parent.find(">li:last");
				if ( $last.length > 0 ) {
					item = $last.data("item");
					page.open(item);
				} else {
					$("#mainWelcome").fadeIn();
				}
				
			}
			/*if ( !$li.hasClass("active") ) return false;
			
			$li = $frameTabs.find("li:last").addClass("active");
			var item = $li.data("item");
			if (item) {
				var page = $.protal.page;
				var index = $.inArray(item, page.items);
				appui.page.close(page.pages[index]);
			}*/
			return false;
		});
		
		$frameTabs.delegate("li > a", "click", function (event) {
			var $li = $(this).parent();
			if ($li.hasClass("active")) return false;
			$li.parent().find(".active").removeClass("active");
			var item = $li.addClass("active").data("item");
			if ( item ) {
				var page = $.protal.page;
				var index = $.inArray( item, page.items);
				appui.page.open( page.pages[ index ] );
			}
			return false;
		});
	};

	$(function () {
		$("html").css("overflow", "visible");
		var childMenu = $( '<div class="sidebar-menu-child" id="sidebarMenuChild"><div class="child-menu-container"></div></div>' );
		childMenu.insertAfter( "#leftMenu" );
		
		$("#leftMenu").slimScroll({
			 position: 'right',
			 color: "#ddd",
			 height: $(window).height() - $(".user-panel").outerHeight() - 50 - 5
		});
		
		$("body").removeClass("hold-transition").addClass('skin-yellow').addClass('sidebar-mini');

		var sidebar_class = $.cookie('protal-sidebar-class');
		if (sidebar_class) $("body").addClass(sidebar_class);

		var o = $.protal.options;

		$.protal.leftMenu.ajax();
		
		$.protal.tabs();

		//Activate the layout maker
		$.protal.layout.activate();

		//Enable control sidebar
		if (o.enableControlSidebar) {
			$.protal.controlSidebar.activate();
		}

		//Add slimscroll to navbar dropdown
		if (o.navbarMenuSlimscroll && typeof $.fn.slimscroll != 'undefined') {
			$(".navbar .menu").slimscroll({
				height: o.navbarMenuHeight,
				alwaysVisible: false,
				size: o.navbarMenuSlimscrollWidth
			}).css("width", "100%");
		}

		//Activate sidebar push menu
		if (o.sidebarPushMenu) {
			$.protal.pushMenu.activate(o.sidebarToggleSelector);
		}


		/*
		 * INITIALIZE BUTTON TOGGLE
		 * ------------------------
		 */
		$('.btn-group[data-toggle="btn-toggle"]').each(function () {
			var group = $(this);
			$(this).find(".btn").on('click', function (e) {
				group.find(".btn.active").removeClass("active");
				$(this).addClass("active");
				e.preventDefault();
			});
		});

		$('a[data-toggle-fullscreen]').bind('click', function (event) {
			event.preventDefault();
			event.stopPropagation();
			screenfull.enabled ? (screenfull.toggle(), n(r)) : appui.message.error('当前浏览器不支持全屏显示');
			return false;
		});

	});

})();