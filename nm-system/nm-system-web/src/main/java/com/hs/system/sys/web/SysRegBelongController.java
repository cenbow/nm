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
import com.hs.system.api.RegionalBelongApi;
import com.hs.system.entity.SysRegionalBelong;
import com.hs.system.util.OpLogUtil;
@Controller
@RequestMapping("reg/belong")
public class SysRegBelongController extends BaseController{

    @Autowired
    private RegionalBelongApi regionalBelongApi;

    /**
     * 进入操作页
     * @param page
     * @param model
     * @return
     */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Page<SysRegionalBelong> page, Model model) {
		return "system/sysregbelong/index";
	}

	/**
	 * 查询省市县列表
	 * @param page
	 * @return
	 */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysRegionalBelong> list (Page<SysRegionalBelong> page,String prono) {
//        List<SysRegionalBelong> list = regionalBelongApi.querySysRegionalBelong(page.getParams());
        List<SysRegionalBelong> list = regionalBelongApi.queryArea(prono);
        page.setList(list);
        page.getParams().remove(Page.KEY);
        return page;
    }

//    @RequestMapping(value = "/get", method = RequestMethod.POST)
//    @ResponseBody
//    public Page<SysOrg> get (Page<SysOrg> page, String id) {
//        SysOrg sysOrg = regionalBelongApi.getByOrgNo(page.getParams().get("orgNo").toString());
//        page.setT(sysOrg);
//        return page;
//    }

    /**
     * 保存省市县设置
     * @param page
     * @param entity
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysRegionalBelong> save(Page<SysRegionalBelong> page, SysRegionalBelong entity) {
    	regionalBelongApi.save(entity);
    	UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"新增或修改省市县","");
        return page;
    }


    /**
     * 删除省市县记录
     * @param page
     * @param id
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysRegionalBelong> delete(Page<SysRegionalBelong> page, String id) {
    	regionalBelongApi.delete(id);
    	UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_DEL,userProfile.getStaffName()+"删除省市县","");
        return page;
    }
}
