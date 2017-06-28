package com.hs.system.sys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.web.BaseController;
import com.hs.commons.constants.CommonConstant;
import com.hs.system.api.SysOrgApi;
import com.hs.system.api.SysPrivInfoApi;
import com.hs.system.api.SysRoleApi;
import com.hs.system.api.SysStaffApi;
import com.hs.system.dto.SysPrivManagerBo;
import com.hs.system.entity.SysOrg;
import com.hs.system.entity.SysPrivInfo;
import com.hs.system.entity.SysRole;
import com.hs.system.entity.SysStaff;
import com.hs.system.util.OpLogUtil;

/**
 * 特殊权限管理controller
 * @author zwr
 *
 */
@Controller
@RequestMapping("/priv/info")
public class SysPrivInfoController extends BaseController{

	@Autowired
	private SysPrivInfoApi sysPrivInfoApi;
	@Autowired
	private SysRoleApi sysRoleApi;
	@Autowired
	private SysStaffApi pubSysStaffServer;
	@Autowired
    private SysOrgApi pubSysOrgServer;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(){
		return "system/sysprivinfo/index";
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form() { return "system/sysprivinfo/form"; }

	@RequestMapping(value = "/role/index", method = RequestMethod.GET)
	public String roleList() { return "system/sysprivinfo/role/index"; }

	@RequestMapping(value = "/staff/index", method = RequestMethod.GET)
	public String staffList() { return "system/sysprivinfo/staff/index"; }

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Page<SysPrivManagerBo> list (Page<SysPrivManagerBo> page) {
		page = sysPrivInfoApi.queryPrivManagerBoPage(page);
		return page;
	}

	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Page<SysPrivInfo> save(Page<SysPrivInfo> page, SysPrivInfo sysPrivInfo) {
		sysPrivInfoApi.saveOrUpdate(sysPrivInfo);
		UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"新增或修改特殊权限","");
        return page;
	}

	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public Page<SysPrivInfo> del (Page<SysPrivInfo> page, @RequestParam(value = "ids[]") List<String> ids) {
		sysPrivInfoApi.deletePrivInfos(ids);
		UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_DEL,userProfile.getStaffName()+"删除特殊权限","");
		return new Page<>();
	}

	@ResponseBody
	@RequestMapping(value = "/org/list", method = RequestMethod.POST)
	public Page<SysOrg> orgList (Page<SysOrg> page) {
		List<SysOrg> list = pubSysOrgServer.queryForList(page.getParams());
		page.setList(list);
		return page;
	}

	@ResponseBody
	@RequestMapping(value = "/role/list", method = RequestMethod.POST)
	public Page<SysRole> roleList (Page<SysRole> page) {
		page = sysRoleApi.queryForPage(page);
		return page;
	}

	@ResponseBody
	@RequestMapping(value = "/staff/list", method = RequestMethod.POST)
	public Page<SysStaff> staffList (Page<SysStaff> page) {
		page = pubSysStaffServer.queryForPage(page);
		return page;
	}

}
