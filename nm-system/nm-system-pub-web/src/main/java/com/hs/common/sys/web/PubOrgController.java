package com.hs.common.sys.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hs.base.web.BaseController;
import com.hs.system.entity.SysOrg;
import com.hs.system.org.PubSysOrgService;

/**
 * 公用机构查询
 */
@Controller
@RequestMapping("/pub/org")
public class PubOrgController extends BaseController{

    @Autowired private PubSysOrgService pubSysOrgServer;

    /**
     * 机构列表查询
     * @param orgNo 机构号
     * @return List<SysOrg>
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public List<SysOrg> list (String orgNo) {
    	Map<String,Object> param = new HashMap<>();
    	param.put("orgNo", orgNo);
        List<SysOrg> list = pubSysOrgServer.queryForList(param);
        return list;
    }

    /**
     * 机构对象获取
     * @param orgNo 机构号
     * @return SysOrg
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public SysOrg get (String orgNo) {
        SysOrg sysOrg = pubSysOrgServer.getByOrgNo(orgNo);
        return sysOrg;
    }

    /**
     * 获取机构及子机构
     * @param orgNo
     * @return
     */
    @RequestMapping(value = "child/list", method = RequestMethod.POST)
    @ResponseBody
    public List<SysOrg> childList (String orgNo) {
        List<SysOrg> list = pubSysOrgServer.queryForListAuthr(orgNo);
        return list;
    }

}
