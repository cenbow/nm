define( [ "app/frame/frame.base" ] ,function() {
	var base = require( "app/base" )
	, tools = base.tools
	, format = tools.format
	, uuid = tools.uuid
	, App = base.constructor
	, page = require( "app/ui/page" );

	var $ = window.$ = require( "jquery" );
	
	$( document ).bind( "main.resize", function( event, width, height ) {
		if ( typeof height != "number" ) height = $( window ).height();

		$( "#nmMenuScroll" ).slimScroll( {
			height: height - $( "#nmFrameBanner:visible" ).height(),
			color: "#265a88",
			railColor: "#337ab7",
			position: "left",
			opacity: ".6",
			alwaysVisible: true
		} );

		height -= $( "#nMFrameHeader:visible" ).height();
		$( "#nMFrameBody" ).css( "height", height );

		height -= $( "#nmTabBtns:visible" ).height();
		$( "#nmTabContents" ).css( "height", height );
	} );

	
	function Protal( selector, toggle ) {
		this.settings = {
			ready: false,
			data: false,
			selector: selector,
			toggle: toggle
		};
		this.cache();
	};
	
	Protal.prototype.cache = function() {
		var _this = this;
		$.ajax( {
			url: "admin/frame/resources",
			type: "POST",
			success: function ( data ) {
				var ds = data.list;
				_this.settings.data = tools.toTreeJson(ds, {
					id: "id",
					pid: "parMenuId"
				} );
				_this.init();
			},
			dataType: 'json'
		} );
	};
	
	Protal.prototype.init = function( ready ) {
		if ( typeof ready == "boolean" ) this.settings.ready = ready;
		if ( this.settings.ready == false || this.settings.data == false ) { return; }
		var ds = this.settings.data;
		if ( ds.length == 1 && ds[ 0 ][ "children" ] ) {
			ds = ds[ 0 ][ "children" ];
		}
		
		var $nav = this.$nav = $( this.settings.selector ).parent();
		var $subs = this.$subs = $( this.settings.sub );
		var $selector = this.$selector = $( this.settings.selector );
		
		$selector.html( this.generate( ds, 0 ) );
		
		this.events();
	};
	Protal.prototype.generate = function( ds, level ) {
		var Menu = this, htmls = [], d, p;
		for ( var index = 0; index < ds.length; index++ ) {
			d = ds[ index ];
			if ( typeof d[ "id" ] == "undefined" && d[ "id" ] == "" ) d[ "id" ] = String(Math.random()).substring(2);
			
			p = typeof d[ "children" ] != "undefined" &&  $.isArray( d[ "children" ] ) && d[ "children" ].length > 0;
			
			if ( typeof d.url == "string" && d.url.length > 0 ) {
				if ( d.url.indexOf( "://" ) == -1 && d.url.indexOf( "." ) == -1 ) d.url += ".html";
				else if ( d.url.indexOf( "resources/" ) == 0 ) d.url = GLOBAL_PARAMS.BASE_PATH + d.url;
			}
			
			htmls.push( format( 
				[
					'<li>',
					'	<a id="{id}" href="{url}" class="nm-menu-item menutree-{node} {floder}">',
					'		<i class="fa fa-bar-chart"></i>',
					'		<span class="menutree-text">{label}</span>',
					'		<i class="fa fa-plus {suffix}"></i>',
					'	</a>',
					'{children}',
					'</li>'
                 ], 
                 {
					node: level == 0 ? "node" : "child",
					floder: p ? "menutree-floder" : "nm-menu-link",
					suffix: p ? "" : "hide",
					prefix: d.menuIcon,
					children: p ? this.generate( d.children, level + 1 ) : '',
					label: d.menuName,
					url: p ? "javascript:;" : d.url || "javascript:;",
					id: d.id
				 } 
			) );
			
			htmls.push( "</li>" );
		};
		
		return format( "<ul>{0}</ul>", [ htmls.join( "" ) ] );
	};
	
	Protal.prototype.events = function() {
		var $selector = this.$selector = $( this.settings.selector );
		$selector.delegate( "a.menutree-floder", "click", function( event ) {
			event.preventDefault();
			event.stopPropagation(); 
			var $li = $( this ).parent();
			
			var $icon = $( this ).find( " > i:last-child " );
			
			
			
			if ( $li.is( ".active" ) ) {
				$icon.removeClass( "fa-minus" ).addClass( "fa-plus" );
			} else if ( $li.find( " > ul:visible" ).length != 0 ) {
				$li.addClass( "active" );
				$icon.removeClass( "fa-minus" ).addClass( "fa-plus" );
			} else {
				$icon.removeClass( "fa-plus" ).addClass( "fa-minus" );
			}
			
			
			if ( $li.hasClass( "active" ) ) {
				$li.removeClass( "active" );
				$li.find( "ul:eq(0)" ).slideUp( "normal" );
				return false;
			}
			
			var $activeLi = $selector.find( "li.active" );
			
			
			
			if ( $activeLi.length != 0 ) {
				$activeLi.removeClass( "active" );
				if ( $activeLi.find( $li ).length != 0 ) {
					$li.addClass( "active" );
					$li.find( "ul:eq(0)" ).slideDown( "normal" );
				} else {
					if ( $li.find( " > a" ).is( ".menutree-child" ) ) {
						$li.addClass( "active" );
						$li.find( "ul:eq(0)" ).slideDown( "normal" );
					} else {
						var $activeUl = $selector.find( " > ul > li > ul:visible " );
						$li.addClass( "active" );
						$activeUl.slideUp( "normal" );
						$activeUl.parent().find( " > a > i:last-child " ).removeClass( "fa-minus" ).addClass( "fa-plus" );
						$li.find( "ul:eq(0)" ).slideDown( "normal" );
					}
				}
			} else {
				$li.addClass( "active" );
				$li.find( "ul:eq(0)" ).slideDown( "normal" );
			}
			
			return false;
		} );
		
		
		$selector.delegate( "a.nm-menu-link", "click", function( event ) {
			event.preventDefault();
			event.stopPropagation();
			
			var $li = $( this ).parent();
			$selector.find( "li.active" ).not( $li ).removeClass( "active" );
			$li.addClass( "active" );
			
			var url = $( this ).attr( "href" )
			, title = $( this ).text()
			, id = "page" + $( this ).attr( "id" );
			
			var icon = $( this ).find( "i:first-child" ).attr( "class" );
			
			tabs.open( id, title, url, icon );
			
			return false;
		} );

		$selector.contextmenu( {
			menus: [
				{ action: "global-nav-drop-file", icon: "fa fa-file", css: "global-nav-drop-menu-link", text: "新页面打开"}
			],
			menusBeforeShowHandler: function( event ) {
				if ( event.target == null ) {
					return false;
				}
				var $link = $( event.target );
				if ( !$link.is( "a" ) ) $link = $link.parent();
				if ( !$link.is( "a" ) ) return false;
				if ( $link.is( ".nm-menu-link" ) ) {
					$( this ).find( ".global-nav-drop-menu-link" ).data( "href", $link.attr( "href" ) ).show()
				}
				return false;
			},
			menusHandler: function ( event, action ) {
				if ( action == "global-nav-drop-minus" || action == "global-nav-drop-plus" ) {
					return;
				}

				if ( action == "global-nav-drop-file" ) {
					if ( !event.target ) return;

					var $li = $( event.target );
					if ( !$li.is( "li" ) ) $li = $li.parents( "li:first" );
					var url = $li.data( "href" );
					window.open( url );
					return;
				}
			}
		} )
		
	};
	
	var protal = new Protal( "#nmMenuTree" );
	$( document ).bind( "main.ready.protal", function( event ) {
		protal.init( true );
	} );
		
	function TabPage() {};
	TabPage.prototype.init = function() {
		var $nmTabBtns = this.$nmTabBtns = $( "#nmTabBtns" )
			, $nmTabs = this.$nmTabs = $( "#nmTabs")
			, $nmTabsHeader = this.$nmTabsHeader = $( "#nmTabBtns" )
			, $nmTabContents = this.$nmTabContents = $( "#nmTabContents" )
			, $nmTabLoading = this.$nmTabLoading = $( "#nmTabLoading" );
			
		$nmTabContents.delegate( ".section.active", "main.resize", function() {
			$( document ).trigger( "main.resize" );
		} );
		
		$nmTabs.delegate( "li.nm-tab ", "close", function( event ) {
			event.preventDefault();
			event.stopPropagation();
			var $tab = $( this )
				, ns = $tab.data( "ns" ) || [];
			base.die( ns );
			$tab.remove();


			if (  $nmTabs.find( "li.nm-tab.active" ).length == 0 ) {
				$tab = $nmTabs.find( "li:eq(0)" ).addClass( "active" );
				var pageId = $tab.attr( "pageid" );
				var page = $( "#" + pageId ).show().addClass( "active" );
				setTimeout( function(){
					page.trigger( "section.active" );
					page.find( ".section.active" ).trigger( "section.active" );
				}, 0 );
			}

			if ( $nmTabs.is( ":empty" ) && $nmTabs.is( ":visible" ) ) {
				$( "#welcome" ).show().addClass( "active" ).trigger( "section.active" );
				//$nmTabsHeader.hide();
				$( document ).trigger( "main.resize");
			}
			
			//$( document ).trigger( "resize" );
			
			return false;
		});
		
		$nmTabs.delegate( "li.nm-tab", "click", function( event ) {
			event.preventDefault();
			event.stopPropagation();
			var $tab = $( this );
			if ( $tab.hasClass( "active" ) ) return false;
			var pageid = $nmTabs.find( "li.active" ).removeClass( "active" ).attr( "pageid" );

			$( "#" + pageid ).removeClass("active").hide();
			
			$tab.addClass( "active" );
			pageid = $tab.attr( "pageid" );
			
			var page = $( "#" + pageid ).addClass( "active" ).show();
			
			setTimeout( function(){
				page.trigger( "section.active" );
				page.find( ".section.active" ).trigger( "section.active" );
			}, 0 );
			

			return false;
		});

		$( document ).bind( "main.resize.tabpage.section", function( event, width, height ) {
			if ( typeof width != "number" ) {
				width = $( window ).width();
				height = $( window ).height();
			}

			var h = height - $( "#nMFrameHeader:visible" ).height();
			if ( $nmTabsHeader.is( ":visible" ) ) {
				h -= $nmTabsHeader.height();
			}
			var w = width - $( "#nmMenuTree:visible" ).width();
			$nmTabContents.css( "height", h + "px" );
			$nmTabContents.find( ".section" ).each( function() {
				$( this ).trigger( "section.resize", [ w, h ] );
			} );
		} );
		
		$nmTabContents.delegate( ".section", "section.resize", function( event, width, height ) {
			event.preventDefault();
			event.stopPropagation();
			
			if ( typeof width != "number" ) {
				width = $nmTabContents.width();
			}
			if ( typeof height != "number" ) {
				height = $nmTabContents.height();
			}
			
			$( this ).trigger( "section.custom.resize", [ width, height ] );
			return false;
		} );
		
		
		$( "#nmMenuTreeToggleBtn" ).bind( "click", function() {
			$( "body" ).toggleClass( "nm-menu-toggle-open" );
			$( document ).trigger( "nm.menu.toggle.open" );
		} );
		
		
		$( document ).bind( "nm.menu.toggle.open", function() {
			var pageid = $nmTabs.find( "li.active" ).attr( "pageid" );
			var $section = $( "#" + pageid ).css( "overflow", "hidden" );
			setTimeout( function() {
				$section.trigger( "section.resize" ).css( "overflow", "visible" );
			}, 300 );
		} );
		
		$( document ).delegate( "ul.nav.nav-tabs > li > a", "click", function( event ) {
			var content, $content, $parent;
			content = $( this ).attr( "href" );
			if ( !content ) return;
			$parent = $( this ).parent().parent().parent(); // ==> a > li > ul > div
			$content = $parent.find( content );
			if ( $content.length == 0 ) return;
			$content.find( ".section.active" ).trigger( "section.active" );
		} );

	};
	TabPage.prototype.open = function( id, title, url, icon ) {
		var $nmTabs = this.$nmTabs
			, $nmTabContents = this.$nmTabContents
			, $nmTabLoading = this.$nmTabLoading;
		
		var css = "";
		
		var args = [].slice.apply( arguments );
		
		if ( arguments.length == 1 ) {
			var args = arguments;
			url = args[2];
			title = args[1];
			id = args[0];
		} else if ( arguments.length == 2) {
			url = title;
			title = false;
		}
		
		if ( url.trim() == "javascript:;" ) {
			return base.message.info( base.tools.format( "{0} 功能开发中...", [ title ] ) );
		}
		
		$nmTabLoading.show();
		
		// 当前菜单没有相应的加载的页面, 那查找之前是否有其他加载的页面，关闭/隐藏它们。
		var $tab = $nmTabs.find( "li.active" ).removeClass( "active" );
		$nmTabContents.find( ">.section.active" ).each( function() {
			$( this ).hide().removeClass( "active" );
		} );
		
		if ( title !== false ) {
			$tab = $nmTabs.find( "[pageid=TAB_PAGE_" + id + "]" );
			if ( $tab.length == 0 ) {
				
				$tab = $( format( [ '<li class="nm-tab active">',
				                    '	<a href="javascript:;">',
				                    '		<span>&nbsp;{0}&nbsp;</span>',
				                    '	</a>',
				                    '</li>'
				                    ], [ title ] ) ).attr( "pageid" , "TAB_PAGE_" + id);
				
				var menuCss = id == "homePage" ? "hide" : "";
				
				$tab.contextmenu( {
					menus: [
						{ action: "tab-drop-refresh", icon: "fa fa-refresh", text: "刷新当前页"},
						{ action: "tab-drop-close", css: menuCss, icon: "fa fa-remove", text: "关闭当前页"},
						{ action: "tab-drop-close-other", icon: "fa fa-remove", text: "关闭其他页"},
						{ action: "tab-drop-close-all", css: menuCss, icon: "fa fa-remove", text: "关闭所有页"}
			        ],
			        menusHandler: function( event, action ) {
			        	if ( action == "tab-drop-refresh" ) {
							var args = $tab.data( "args" );
							base.tabs.open.apply( base.tabs, args );
							return;
						}

						if ( action == "tab-drop-close" ) {
							$tab.trigger( "close" );
							return;
						}

						if ( action == "tab-drop-close-other" ) {
							$tab.addClass( "not-close-tab" );
							$nmTabs.find( "li.nm-tab" ).filter( ":not(.not-close-tab,[pageid=TAB_PAGE_homePage])" ).each( function () {
								if ( $( this ) == $tab ) return true;
								$( this ).trigger( "close" );
							} );
							$tab.removeClass( "not-close-tab" );
							return;
						}

						if ( action == "tab-drop-close-all" ) { 
							$nmTabs.find( "li.nm-tab" ).filter( ":not([pageid=TAB_PAGE_homePage])" ).each( function () {
								$( this ).trigger( "close" );
							} );
						}
			        }
				} );
				
				$tab.hide();
				$nmTabs.append( $tab );
				
				$tab.data( "args", args );
			} else {
				$tab.addClass( "active" );
				var ns = $tab.data( "ns" );
				base.die( ns );
			}
		}
		
		$( document ).trigger( "main.resize.tabpage.section" );
		
		
		var params = {};
		if ( url.indexOf( "http://" ) == 0 || url.indexOf( "https://" ) == 0 ) {
			params.url = url;
			title = false;
			url = "frame/iframe/index";
		}
		
		css = "section-report";
		if ( url.indexOf( "report/frame/" )  == 0 ) {
			title = false;
			css = "section-report";
			if ( typeof icon != "string" ) icon = "fa fa-bar-chart";
		}
		
		if ( id == "homePage" ) title = false;
		
		if ( typeof icon != "string" ) {
			icon = "fa fa-file";
		}
		
		if ( url.substring( url.length - 5 ) == ".html" ) url = url.substring( 0, url.length - 5 );
		
		page.open( {
			id: "TAB_PAGE_" + id,
			title: title,
			css: css,
			url: url + ".tpl",
			params: params,
			content: $nmTabContents
		}, function( apps ) {
			var ns = [];
			for ( var i = 0; i < apps.length; i++ ) {
				ns.push( apps[ i ].__namespace__ );
			}
			$tab.data( "ns", ns );
			$nmTabLoading.fadeOut( 700 );
			
			if ( $tab.is( ":hidden" ) ) {
				$tab.show();
			}
		}, function() {
			$nmTabLoading.hide();
			$tab.find( ".nav-tabs-close" ).trigger( "click" );
		} );
		
		/*if ( !$nmTabs.is( ":empty" ) && $nmTabsHeader.is( ":hidden" ) ) {
			//$( document ).trigger( "main.resize");
		}*/
	};
	
	//TabPage
	
	var tabs = App.prototype.tabs = new TabPage();
	$( document ).bind( "main.ready.tabpage", function( event ) {
		tabs.init();
		$( document ).trigger( "main.resize.tabpage.section" );
		tabs.open( "homePage", "首页", "admin/frame/homepage" );
	} );
	
	var dialog = new ( require( "app/ui/dialog" ) )();
	$( document ).bind( "request.ajax.error.510", function( event, message ) {
		dialog.alert( {
			closeBtn: false,
			title: "登录提示",
			content: message || "登录已失效",
			buttonHandler: function() {
				window.location.href = "admin/login";
			} 
		} )
	} );
	
	//================================
	//初始化事件机制
	$( window ).bind( "resize", function( event ) {
		var width, height;
		width = $( window ).width();
		height = $( window ).height();
		$( document ).trigger( "main.resize", [ width, height ] );
		
		$( "body" ).addClass( "nm-menu-toggle-open" );
	} );
} );