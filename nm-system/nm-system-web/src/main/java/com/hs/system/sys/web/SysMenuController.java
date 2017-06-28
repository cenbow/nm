package com.hs.system.sys.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.web.BaseController;
import com.hs.commons.constants.CommonConstant;
import com.hs.system.api.SysMenuApi;
import com.hs.system.entity.SysMenu;
import com.hs.system.util.OpLogUtil;
/**
 * 菜单管理
 * @author hf
 *
 */
@Controller
@RequestMapping("/menu")
public class SysMenuController extends BaseController{

	@Autowired
	private SysMenuApi sysMenuService;
	
	@RequestMapping(value = "/index")
	public String index(Model model){
		return "/system/sysmenu/index";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysMenu> list (Page<SysMenu> page) {
        List<SysMenu> list = sysMenuService.queryForList(page.getParams());
        page.setList(list);
        return page;
    }
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysMenu> save(Page<SysMenu> page, SysMenu entity) {
		SysMenu sysMenu = sysMenuService.save(entity);
        page.setT(sysMenu);
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"新增菜单"+entity.getMenuName(),"");
        return page;
    }
	@RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysMenu> get (Page<SysMenu> page, String id) {
		SysMenu sysMenu = sysMenuService.getByPrimaryKey(id);
        page.setT(sysMenu);
        return page;
    }
	@RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysMenu> delete(Page<SysMenu> page, String id) {
		sysMenuService.deleteMenu(id);
		UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_DEL,userProfile.getStaffName()+"删除系统菜单","");
        return page;
    }
}
