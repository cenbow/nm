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
import com.hs.system.api.SysCodeTypeApi;
import com.hs.system.entity.SysCodTyp;
import com.hs.system.util.OpLogUtil;

/**
 * Created by Mifeng.He(bee) on 2015/10/10.
 */
@Controller
@RequestMapping("/code/type")
public class SysCodTypController extends BaseController{

    @Autowired
    private SysCodeTypeApi sysCodeTypeApi;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Page<SysCodTyp> page){return "/system/syscodtyp/index"; }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(Page<SysCodTyp> page){ return "/system/syscodtyp/form"; }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysCodTyp> list (Page<SysCodTyp> page){
        page = sysCodeTypeApi.queryForPage(page);
        return page;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysCodTyp> get (Page<SysCodTyp> page, String id){

        return page;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysCodTyp> save (Page<SysCodTyp> page, SysCodTyp sysCodTyp){
        sysCodeTypeApi.save(sysCodTyp);
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"新增系统码类型","");
        return page;
    }


    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysCodTyp> delete (Page<SysCodTyp> page, String id){
        sysCodeTypeApi.deleteByPrimaryKey(id);
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"删除系统码类型","");
        return page;
    }

}
