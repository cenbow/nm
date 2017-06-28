package com.hs.loan.operate.sale.web;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.web.BaseController;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.operate.sale.bo.PubSalerGroupBo;
import com.hs.system.api.PubSalerGroupApi;
import com.hs.system.entity.PubSalerGroup;
import com.hs.system.entity.SysStaff;
import com.hs.system.util.OpLogUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 销售组与销售列表
 * @author yuhaoZhang
 *
 */
@Controller
@RequestMapping("/saler/group")
public class PubSalerGroupController extends BaseController{

	private static final String url = "/sale/pubsalegroup";
	
	@Autowired
	private PubSalerGroupApi pubSalerGroupApi;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model){
		return url + "/index";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String groupForm(Model model){
		return url + "/form";
	}
	
	@RequestMapping(value="/staff/index")
	public String staffIndex(Model model){
		return url + "/staff/index";
	}
	
	/**
	 * 获取销售组列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.POST)
	@ResponseBody
	public Page<PubSalerGroup> groupList(Page<PubSalerGroup> page){
		page = pubSalerGroupApi.querySalerGroup(page);
		return page;
	}
	
	/**
	 * 新增分组
	 * @param page
	 * @param pubSalerGroup
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public Page<PubSalerGroup> groupSave(Page<PubSalerGroup> page,PubSalerGroup pubSalerGroup){
		pubSalerGroupApi.save(pubSalerGroup);
		UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"新增销售分群","");
		return page;
	}

	/**
	 * 删除销售分组
	 * @param group
	 * @return
	 */
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public Page<PubSalerGroup> deleteGroup(@RequestBody PubSalerGroup group){
		Page<PubSalerGroup> page = new Page<PubSalerGroup>();
		pubSalerGroupApi.deleteByNo(group.getGroupNo());
		UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_DEL,userProfile.getStaffName()+"删除销售分群","");
		return page;
	}
	
	/**
	 * 销售人员列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/staff/had/list", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysStaff> staffList(Page<SysStaff> page){
		page = pubSalerGroupApi.querySaler(page);
		return page;
	}
	
	/**
	 * 销售人员列表 没有在分组内
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/staff/having/list", method=RequestMethod.POST)
	@ResponseBody
	public Page<SysStaff> staffListNotInGroup(Page<SysStaff> page){
		page = pubSalerGroupApi.queryNotInGrpSaler(page);
		return page;
	}
	
	/**
	 * 销售组添加销售
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/staff/had/save", method=RequestMethod.POST)
	@ResponseBody
	public Page<SysStaff> staffSave(Page<SysStaff> page, PubSalerGroupBo pubSalerGroupBo){
		pubSalerGroupApi.saveGrpSalerRel(pubSalerGroupBo.getGroupNo(), pubSalerGroupBo.getStaffNos());
		UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"为销售组添加销售人员","");
		return page;
	}
	
	/**
	 * 删除销售分组内的销售人员
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/staff/had/del", method=RequestMethod.POST)
	@ResponseBody
	public Page<SysStaff> staffDelete(Page<SysStaff> page, String groupNo, @RequestParam("staffNos[]") List<String> staffNos){
		pubSalerGroupApi.deleteGrpSalerRel(groupNo, staffNos);
		UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_DEL,userProfile.getStaffName()+"删除销售分组内的销售人员","");
		return page;
	}
}
