package com.hs.common.sys.web;

import java.rmi.ServerException;

import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hs.base.entity.Page;
import com.hs.base.web.BaseController;
import com.hs.commons.tools.SpringContextHolder;
import com.hs.system.api.SysStaffApi;
import com.hs.system.entity.SysStaff;
import com.hs.utils.StringUtils;


/**
 * 公用员工查询
 */
@Controller
@RequestMapping("/pub/staff")
public class PubStaffController extends BaseController{
	private static final String url = "/pub/staff";
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model){
		return url + "/index";
	}
	/**
	 * 销售人员列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysStaff> staffList(Page<SysStaff> page,String roleNo){
		page.getParams().put("roleNo", roleNo);
		SysStaffApi sysStaffApi = SpringContextHolder.getBean("pubSysStaffServer");
		page=sysStaffApi.queryStaffByRoleForPage(page);
		return page;
	}
	/**
	 * 修改密码
	 * @param page
	 * @return
	 * @throws ServerException 
	 */
	@RequestMapping(value="/changepwd", method = RequestMethod.POST)
	@ResponseBody
	public Page<?> staffList(Page<?> page,String opwd,String npwd) throws ServerException {
		SysStaffApi sysStaffApi = SpringContextHolder.getBean("pubSysStaffServer");
		String staffNo = super.getCurUser().getStaffNo();
		if(StringUtils.isEmpty(staffNo)){
			throw new ServerException("登录失效,请重新登录");
		}
		opwd = StringUtils.MD5Encode(opwd).toLowerCase();
		npwd = StringUtils.MD5Encode(npwd).toLowerCase();
		sysStaffApi.changePassword(staffNo,opwd,npwd);
		page.setSuccess(true);
		return page;
	}
	
	

}
