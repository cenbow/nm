package com.hs.system.sys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.web.BaseController;
import com.hs.commons.constants.CommonConstant;
import com.hs.system.api.SysCodeInfoApi;
import com.hs.system.entity.SysCodInfo;
import com.hs.system.util.OpLogUtil;

/**
 * Created by Mifeng.He(bee) on 2015/10/10.
 */
@Controller
@RequestMapping("/code/info")
public class SysCodInfoController extends BaseController{

    @Autowired
    private SysCodeInfoApi sysCodeInfoApi;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Page<SysCodInfo> page){ return "/system/syscodinfo/index"; }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(Page<SysCodInfo> page){ return "/system/syscodinfo/form"; }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysCodInfo> list (Page<SysCodInfo> page){
        page = sysCodeInfoApi.queryForPage(page);
        return page;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysCodInfo> get (Page<SysCodInfo> page, String id){

        return page;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysCodInfo> save (Page<SysCodInfo> page, SysCodInfo sysCodInfo){
        sysCodeInfoApi.save(sysCodInfo);
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"新增系统码信息","");
        return page;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysCodInfo> delete (Page<SysCodInfo> page, String id){
        sysCodeInfoApi.deleteByPrimaryKey(id);
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_DEL,userProfile.getStaffName()+"删除系统码信息","");
        return page;
    }

}
