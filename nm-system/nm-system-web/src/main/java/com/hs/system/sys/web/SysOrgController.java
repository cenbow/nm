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
import com.hs.system.api.SysOrgApi;
import com.hs.system.entity.SysOrg;
import com.hs.system.util.OpLogUtil;

/**
 * Created by Mifeng.He(bee) on 2015/9/28.
 */
@Controller
@RequestMapping("/org")
public class SysOrgController extends BaseController{

    @Autowired
    private SysOrgApi sysOrgService;

    @RequestMapping(value = "/index" ,method = RequestMethod.GET)
    public String index (Page<SysOrg> page, Model model) {return "system/sysorg/index";}

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysOrg> list (Page<SysOrg> page) {
        List<SysOrg> list = sysOrgService.queryForList(page.getParams());
        page.setList(list);
        page.getParams().remove(Page.KEY);
        return page;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysOrg> get (Page<SysOrg> page, String id) {
        SysOrg sysOrg = sysOrgService.getByOrgNo(page.getParams().get("orgNo").toString());
        page.setT(sysOrg);
        return page;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysOrg> save(Page<SysOrg> page, SysOrg entity) {
        SysOrg sysOrg = sysOrgService.save(entity);
        page.setT(sysOrg);
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"新增或修改机构"+entity.getOrgName(),"");
        return page;
    }


    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysOrg> delete(Page<SysOrg> page, String id) {
    	sysOrgService.deleteByOrgId(id);
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_DEL,userProfile.getStaffName()+"删除机构","");
        return page;
    }

}
