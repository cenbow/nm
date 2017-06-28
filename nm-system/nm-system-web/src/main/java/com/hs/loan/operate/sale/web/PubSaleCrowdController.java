package com.hs.loan.operate.sale.web;

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
import com.hs.system.api.PubSaleCrowdApi;
import com.hs.system.entity.PubSaleCrowd;
import com.hs.system.entity.SysStaff;
import com.hs.system.util.OpLogUtil;

/**
 * 产品与销售群
 * @author SuperL
 *
 */
@Controller
@RequestMapping("/sale/crowd")
public class PubSaleCrowdController extends BaseController{
	private static final String url="/sale/pubsalecrowd";

	@Autowired
	private PubSaleCrowdApi saleCrowdApi;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model){
        return url+"/index";
    }
	
	@RequestMapping(value = "/rule/index", method = RequestMethod.GET)
	public String ruleIndex(Model model){
		return url+"/rule/index";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form(Model model){
        return url+"/form";
    }
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
    public Page<?> get(Page<Object> page, Model model){
        return page;
    }
	
	//页面信息展示
	@RequestMapping(value="/list", method=RequestMethod.POST)
    @ResponseBody
    public Page<?> list(Page<PubSaleCrowd> page){
		page = saleCrowdApi.querySaleCrowd(page);
		return page;
	}
	
	//页面信息展示
	@RequestMapping(value="/rule/exec", method=RequestMethod.POST)
	@ResponseBody
	public Page<?> executeRule(Page<SysStaff> page){
		page = saleCrowdApi.executeRulePage(page);
		return page;
	}
	
	/**
	 * 保存与更新
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
    @ResponseBody
    public Page<?> save(Page<Object> page, PubSaleCrowd pubSaleCrowd){
		saleCrowdApi.save(pubSaleCrowd);
		UserProfile userProfile = this.getCurUser();
    	//OpLogUtil.saveOperaterLog(CommonConstant.+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"新增客户分群","");
		return page;
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
    @ResponseBody
    public Page<?> del(Page<Object> page,String crowdNo){
		saleCrowdApi.deleteByNo(crowdNo);
		return page;
	}
	
	@RequestMapping(value="/check", method=RequestMethod.POST)
    @ResponseBody
	public Page<?> check(Page<Object> page,String rule){
		boolean isCheck = saleCrowdApi.validRule(rule);
		page.setT(isCheck);
		return page;
	}

}
