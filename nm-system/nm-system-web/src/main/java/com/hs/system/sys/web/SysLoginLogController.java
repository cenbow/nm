package com.hs.system.sys.web;

import com.hs.base.entity.Page;
import com.hs.base.web.BaseController;
import com.hs.system.api.SysLoginLogApi;
import com.hs.system.entity.SysLoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("login/log")
public class SysLoginLogController extends BaseController {

	@Autowired
	private SysLoginLogApi sysLoginLogApi;


	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Page<SysLoginLog> page, Model model){return "system/sysloginlog/index";}


	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Page<SysLoginLog> list (Page<SysLoginLog> page) {
		page = sysLoginLogApi.queryForPage(page);
		return page;
	}

}
