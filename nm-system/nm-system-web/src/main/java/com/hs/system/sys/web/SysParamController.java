package com.hs.system.sys.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.web.BaseController;
import com.hs.commons.constants.CommonConstant;
import com.hs.system.api.SysParamApi;
import com.hs.system.entity.SysParam;
import com.hs.system.util.OpLogUtil;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;

/**
 * Created by Mifeng.He(bee) on 2015/10/24.
 */
@Controller
@RequestMapping("/param")
public class SysParamController extends BaseController{

    @Autowired
    private SysParamApi sysParamApi;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Page<SysParam> page){ return "/system/sysparam/index"; }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(Page<SysParam> page){ return "/system/sysparam/form"; }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysParam> list (Page<SysParam> page){
        page = sysParamApi.queryForPage(page);
        return page;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysParam> get (Page<SysParam> page, String id){

        return page;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysParam> save (Page<SysParam> page, SysParam sysParam){
        if (StringUtils.isEmpty(sysParam.getId())){
            sysParam.setId(RandomUtil.getUUID());
            sysParamApi.insert(sysParam);
        } else sysParamApi.updateByPrimaryKeySelective(BeanUtils.bean2map(sysParam));
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"新增或修改系统参数"+sysParam.getName(),"");
        return page;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysParam> delete (Page<SysParam> page, String id){
        sysParamApi.deleteByPrimaryKey(id);
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_DEL,userProfile.getStaffName()+"删除系统参数","");
        return page;
    }
}
