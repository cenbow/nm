<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	if (basePath == null || basePath.equals(""))
		basePath = "/";

	String themeKey = "theme";//获取主题KEY值
	String themesKey = "themes";//支持主题皮肤KEY值
	String themes = "cyanine";//追加主题皮肤
	String[] themeArr = themes.split(",");
	String defaultTheme = "cyanine"; //默认主题皮肤

	//获取显示 主题皮肤
	String theme = request.getParameter(themeKey);

	Boolean isExists = false;
	Cookie[] cookies = request.getCookies();;
	Cookie themeCookie = null;
	Cookie themesCookie = null;
	
	//主题皮肤未显示配置，从Cookie里获取
	if (theme != null && theme.length() > 0 ) {
		theme = theme.trim();
		if (!theme.equals(defaultTheme)) {
			for (String t : themeArr) {
				if (!t.trim().equals(theme))
					continue;
				isExists = true;
				break;
			}
		} else {
			isExists = true;
		}
		
		
	}
	if (!isExists) {
		for (Cookie cookie : cookies) {
			if (!cookie.getName().equals(themeKey))
				continue;
			themeCookie = cookie;
			theme = cookie.getValue();
			isExists = true;
			break;
		}
	}
	
	if (!isExists) theme = defaultTheme;


	//设置主题皮肤cookie
	if (themeCookie == null) {
		themeCookie = new Cookie(themeKey, theme);
		themeCookie.setPath(basePath);
		response.addCookie(themeCookie);
	} else {
		themeCookie.setValue(theme);
		themeCookie.setMaxAge(2 * 24 * 60 * 60);
	}
	
	for (Cookie cookie : cookies) {
		if (!cookie.getName().equals(themesKey))
			continue;
		themesCookie = cookie;
		break;
	}
	
	if (themesCookie == null) {
		themesCookie = new Cookie(themesKey, themes);
		themesCookie.setPath(basePath);
		response.addCookie(themesCookie);
	} else {
		if (!themesCookie.getValue().equals(themes)) {
			themesCookie.setValue(themes);
			themesCookie.setMaxAge(2 * 24 * 60 * 60);
		}
	}

	String url = "protal.index.theme." + theme + ".jsp";

	pageContext.setAttribute("url", url);
%>
<jsp:include page="${url}" />