package com.hs.system.sys.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.web.BaseController;
import com.hs.commons.constants.CommonConstant;
import com.hs.system.api.SysRoleApi;
import com.hs.system.api.SysStaffApi;
import com.hs.system.dto.SysStaffRoleBO;
import com.hs.system.entity.SysRole;
import com.hs.system.entity.SysStaff;
import com.hs.system.entity.SysStaffOrg;
import com.hs.system.entity.SysStaffRole;
import com.hs.system.util.OpLogUtil;

/**
 * Created by Mifeng.He(bee) on 2015/10/18.
 */
@Controller
@RequestMapping("/staff")
public class SysStaffController extends BaseController {

    @Autowired
    private SysStaffApi sysStaffService;

    @Autowired
    private SysRoleApi sysRoleApi;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index (Page<Object> page, Model model) { 
    	return "/system/sysstaff/index"; 
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form (Page<Object> page, Model model) { return "/system/sysstaff/form"; }

    //角色选择
    @RequestMapping(value = "/role/list", method = RequestMethod.GET)
    public String roleList (Page<Object> page, Model model) { 
    	return "/system/sysstaff/role/list"; }

    //角色设置
    @RequestMapping(value = "/role/setting", method = RequestMethod.GET)
    public String roleSetting (Page<Object> page, Model model) { return "/system/sysstaff/role/setting"; }

    //机构设置
    @RequestMapping(value = "/org/setting", method = RequestMethod.GET)
    public String orgSetting (Page<SysStaff> page, Model model) { return "/system/sysstaff/org/setting"; }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Page<SysStaff> list (Page<SysStaff> page, SysStaff sysStaff) {
        page = sysStaffService.queryForPage(page);
        return page;
    }

    @ResponseBody
    @RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    public Page<Object> resetPwd (Page<Object> page,String staffNo) {
    	page=new Page<>();
        try {
			sysStaffService.resetPassword(staffNo);
		} catch (Exception e) {
			page.setMsg(e.getMessage());
			page.setSuccess(false);
		} 
        return page;
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public Page<Object> get (Page<Object> page, String id, String staffNo) {
        SysStaff sysStaff = null;

        if (StringUtils.isNotEmpty(id))
            sysStaff = sysStaffService.getByStaffId(id);
        else if (StringUtils.isNotEmpty(staffNo))
            sysStaff = sysStaffService.getByStaffNo(staffNo);

        page.setT(sysStaff);
        return page;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Page<Object> save (Page<Object> page, SysStaff sysStaff) {
    	UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"同时保存或更新 用户"+sysStaff.getStaffName(),"");
        sysStaffService.save(sysStaff);
        return page;
    }

    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Page<Object> del (Page<Object> page, SysStaff sysStaff) {
        //sysStaffService.
        return page;
    }
    
    //机构设置
    @ResponseBody
    @RequestMapping(value = "/org/save", method = RequestMethod.POST)
    public Page<Object> orgSave (Page<Object> page, SysStaffOrg sysStaffOrg) {
    	sysStaffService.saveStaffOrg(sysStaffOrg);
        return page;
    }
    

    //角色选择
    @ResponseBody
    @RequestMapping(value = "/role/list", method = RequestMethod.POST)
    public Page<SysRole> roleList (Page<SysRole> page, String staffNo) {
        page = sysRoleApi.queryForPage(page);
        return page;
    }


    //已拥有角色查询
    @ResponseBody
    @RequestMapping(value = "/role/had/list", method = RequestMethod.POST)
    public Page<SysStaffRoleBO> roleHadList (Page<SysStaffRoleBO> page, String staffNo) {
        List<SysStaffRoleBO> sysRoles = sysStaffService.queryCustRoles(staffNo);
        page.setList(sysRoles);
        return page;
    }

    //未拥有角色查询
    @ResponseBody
    @RequestMapping(value = "/role/having/list", method = RequestMethod.POST)
    public Page<SysRole> roleHavingList (Page<SysRole> page) {
        page = sysStaffService.queryCustNotExistRoles(page);
        return page;
    }

    //保存已设置的角色
    @ResponseBody
    @RequestMapping(value = "/role/had/save", method = RequestMethod.POST)
    public Page<SysStaffRole> roleHadSave(Page<SysStaffRole> page,  @RequestBody List<SysStaffRole> SysStaffRoles) {
        sysStaffService.saveStaffRoles(SysStaffRoles);
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"保存为用户所设置的角色","");
        return page;
    }

    //保存已设置的角色
    @ResponseBody
    @RequestMapping(value = "/role/had/del", method = RequestMethod.POST)
    public Page<SysStaffRole> roleHadDel(Page<SysStaffRole> page, @RequestParam(value = "ids[]") List<String> ids) {
        sysStaffService.removeStaffRoles(ids);
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_DEL,userProfile.getStaffName()+"删除为用户所设置的角色","");
        return page;
    }

}
