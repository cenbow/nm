package com.hs.system.sys.web;

import java.util.List;

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
import com.hs.system.api.SysCodeInfoApi;
import com.hs.system.api.SysGroupApi;
import com.hs.system.entity.SysGroup;
import com.hs.system.util.OpLogUtil;

/**
 * Created by Mifeng.He(bee) on 2015/10/10.
 */
@Controller
@RequestMapping("/code/group")
public class SysCodGroupController extends BaseController{

    @Autowired
    private SysGroupApi sysGroupApi;

    @Autowired
    private SysCodeInfoApi sysCodeInfoApi;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Page<SysGroup> page){ return "/system/syscodgroup/index"; }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(Page<SysGroup> page){ return "/system/syscodgroup/form"; }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysGroup> list (Page<SysGroup> page){
        List<SysGroup> list =sysGroupApi.queryByType(page.getParams());
        page.setList(list);
        page.getParams().remove(Page.KEY);
        return page;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public Page<Object> get (Page<Object> page, String id){
        if (StringUtils.isNotEmpty(id)) {
            List list = sysGroupApi.queryGrpCodeInfoLst(id);
            page.setList(list);
        } else {
            List list =sysCodeInfoApi.queryForList(page.getParams());
            page.setList(list);
        }

        return page;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysGroup> save (Page<SysGroup> page, SysGroup sysGroup){
        sysGroupApi.saveOrUpdateCodGrp(sysGroup);
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"新增系统码群","");
        return page;
    }


    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysGroup> delete (Page<SysGroup> page, String id){
        sysGroupApi.deleteCodGrp(id);
        UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_DEL,userProfile.getStaffName()+"删除系统码群","");
        return page;
    }

}
