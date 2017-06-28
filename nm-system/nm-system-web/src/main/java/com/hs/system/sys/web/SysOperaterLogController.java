package com.hs.system.sys.web;

import com.hs.system.api.SysSubApi;
import com.hs.system.entity.SysSub;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hs.base.entity.Page;
import com.hs.base.web.BaseController;
import com.hs.system.api.SysOperaterLogApi;
import com.hs.system.entity.SysOperaterLog;

/**
 * Created by Mifeng.He(bee) on 2015/9/21.
 */
 @Controller
 @RequestMapping("/operater/log")
public class SysOperaterLogController extends BaseController{
	
	@Autowired
    private SysOperaterLogApi  sysOperaterLogApi;

    @Autowired
    private SysSubApi sysSubApi;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String handleLog(Model model){
        return "system/sysoperaterLog/index";
    }

	@RequestMapping(value="/list", method=RequestMethod.POST)
    @ResponseBody
    public Page<?> list(Page<SysOperaterLog> page){
		page = sysOperaterLogApi.queryForPage(page);
		return page;
    }
	/**
	 * 子系统查询
	 * @param page
	 * @return
	 */
    @RequestMapping(value="/sub/list", method=RequestMethod.POST)
    @ResponseBody
    public Page<?> subList(Page<SysSub> page){
//        page = sysSubApi.queryForPage(page);
        List<SysSub> queryForList = sysSubApi.queryForList(page.getParams());
        page.setList(queryForList);
        return page;
    }
    
    
 
}
