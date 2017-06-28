package com.hs.system.sys.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.hs.system.api.SysSubApi;
import com.hs.system.entity.SysSub;
import com.hs.system.util.OpLogUtil;
import com.hs.utils.ArrayUtils;

/**
 * 子系统管理控制层
 * @author hf
 *
 */
@Controller
@RequestMapping("/sub")
public class SysSubController extends BaseController{
	@Autowired
	private SysSubApi sysSubApi;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Page<SysSub> page, Model model){return "system/syssub/index";}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(Page<SysSub> page, Model model){ return "system/syssub/form";}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysSub> list (Page<SysSub> page) {
		List<SysSub> list = sysSubApi.queryForList(page.getParams());
		page.setList(list);
        return page;
    }
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Page<SysSub> save(Page<SysSub> page, SysSub entity) {
		SysSub sysSub = sysSubApi.save(entity);
		page.setT(sysSub);
		UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"新增或修改子系统"+entity.getSysName(),"");
        return page;
    }

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysSub> del(Page<SysSub> page, String id, String isActived) {
		String ids[] = null;
		if(StringUtils.isNotEmpty(id)){
			ids = id.split(",");
		}
		if(ArrayUtils.isNotEmpty(ids)){
			for(String idKey:ids)
				sysSubApi.deleteByPrimaryKey(idKey);
		}
		UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_DEL,userProfile.getStaffName()+"删除子系统","");
        return page;
	}
	
	@RequestMapping(value = "/activeOrNot", method = RequestMethod.POST)
	@ResponseBody
	public Page<SysSub> activeOrNot(Page<SysSub> page, String id, String isActived) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("isActived", isActived);
		sysSubApi.updateByPrimaryKeySelective(map);
		UserProfile userProfile = this.getCurUser();
		OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_DEL,userProfile.getStaffName()+"删除子系统","");
		return page;
	}

}
