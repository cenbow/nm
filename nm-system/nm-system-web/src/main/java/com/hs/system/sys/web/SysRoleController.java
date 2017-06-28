package com.hs.system.sys.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.hs.system.api.SysRoleMenuApi;
import com.hs.system.api.SysStaffApi;
import com.hs.system.api.SysSubApi;
import com.hs.system.dto.SysRoleMenuBo;
import com.hs.system.dto.SysRoleStaffBO;
import com.hs.system.entity.SysRole;
import com.hs.system.entity.SysStaff;
import com.hs.system.entity.SysStaffRole;
import com.hs.system.entity.SysSub;
import com.hs.system.util.OpLogUtil;

/**
 * Created by Mifeng.He(bee) on 2015/10/8.
 */
@Controller
@RequestMapping("/role")
public class SysRoleController extends BaseController{

    @Autowired
    private SysRoleApi sysRoleApi;

    @Autowired
    private SysStaffApi pubSysStaffServer;

    @Autowired
    private SysSubApi sysSubApi;

    @Autowired
    private SysRoleMenuApi sysRoleMenuApi;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index (Page<SysRole> page, Model model) { return "system/sysrole/index"; }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String add (Page<SysRole> page, Model model) { return "system/sysrole/form"; }

    @RequestMapping(value = "/staff/setting", method = RequestMethod.GET)
    public String staffSetting (Page<SysRole> page, Model model) { return "system/sysrole/staff/setting"; }

    @RequestMapping(value = "/menu/setting", method = RequestMethod.GET)
    public String menuSetting (Page<SysRole> page, Model model) { return "system/sysrole/menu/setting"; }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysRole> list (Page<SysRole> page) {
        page = sysRoleApi.queryForPage(page);
        page.getParams().remove(Page.KEY);
        return page;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Page<Object> save (Page<Object> page, SysRole sysRole) {
        sysRoleApi.save(sysRole);
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"保存系统角色","");
        return page;
    }

    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Page<Object> del (Page<Object> page, SysRole sysRole) {
        //sysStaffApi.
        return page;
    }


    //已拥有角色查询
    @ResponseBody
    @RequestMapping(value = "/staff/had/list", method = RequestMethod.POST)
    public Page<SysRoleStaffBO> roleHadList (Page<SysRoleStaffBO> page, String roleNo) {
        page = sysRoleApi.queryRolesExistStaff(page);
        return page;
    }

    //未拥有角色查询
    @ResponseBody
    @RequestMapping(value = "/staff/having/list", method = RequestMethod.POST)
    public Page<SysStaff> roleHavingList (Page<SysStaff> page) {
        page = sysRoleApi.queryRolesNotExistStaff(page);
        return page;
    }

    //保存已设置的角色
    @ResponseBody
    @RequestMapping(value = "/staff/had/save", method = RequestMethod.POST)
    public Page<SysStaffRole> roleHadSave(Page<SysStaffRole> page, @RequestBody List<SysStaffRole> sysStaffRoles) {
    	pubSysStaffServer.saveStaffRoles(sysStaffRoles);
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"保存已为员工设置的角色","");
        return page;
    }

    //保存已设置的角色
    @ResponseBody
    @RequestMapping(value = "/staff/had/del", method = RequestMethod.POST)
    public Page<SysStaffRole> roleHadDel(Page<SysStaffRole> page, @RequestParam(value = "ids[]") List<String> ids) {
    	pubSysStaffServer.removeStaffRoles(ids);
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_DEL,userProfile.getStaffName()+"删除已为员工设置的角色","");
        return page;
    }

    //菜单设置子系统查询
    @ResponseBody
    @RequestMapping(value = "/menu/sub/list", method = RequestMethod.POST)
    public Page<SysSub> menuSubList (Page<SysSub> page) {
    	page.getParams().put("isActived", CommonConstant.STAT_ENABLE);
        List<SysSub> sysSubs = sysSubApi.queryForList(page.getParams());
        page.setList(sysSubs);
        return page;
    }

    //未拥有菜单授权
    @ResponseBody
    @RequestMapping(value = "/menu/having/list", method = RequestMethod.POST)
    public Page<SysRoleMenuBo> menuHavingList (Page<SysRoleMenuBo> page) {
        return page;
    }

    //已拥有菜单授权
    @ResponseBody
    @RequestMapping(value = "/menu/had/list", method = RequestMethod.POST)
    public Page<SysRoleMenuBo> menuHadList (Page<SysRoleMenuBo> page) {
        List<SysRoleMenuBo> having = sysRoleMenuApi.querySysRoleMenuList(page.getParams());
        //List<String> had = sysRoleMenuApi.querySubSysRoleHadMenu(page.getParams().get("roleId").toString(), page.getParams().get("sysId").toString());
        List<SysRoleMenuBo> sysRoleMenuBos = sysRoleMenuApi.querySysRoleMenuCheckedList(page.getParams());
        List<String> had = new ArrayList<>();
        for (SysRoleMenuBo sysRoleMenuBo : sysRoleMenuBos) {
            had.add(sysRoleMenuBo.getId());
        }
        page.setRetMap(new HashMap<String, Object>());
        page.getRetMap().put("having", having);//系统菜单
        page.getRetMap().put("had", had);//已授权的菜单ID
        return page;
    }

    //菜单设置子系统查询
    @ResponseBody
    @RequestMapping(value = "/menu/had/save", method = RequestMethod.POST)
    public Page<SysSub> menuHadSave (Page<SysSub> page, String roleId, @RequestParam(value = "menuIds[]") List<String> menuIds, String sysId) {
        sysRoleMenuApi.save(sysId, roleId, menuIds);
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"为子系统查询设置菜单","");
        return page;
    }
}
