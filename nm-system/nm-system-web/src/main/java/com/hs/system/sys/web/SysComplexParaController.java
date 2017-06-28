package com.hs.system.sys.web;

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
import com.hs.system.api.ComplexParaApi;
import com.hs.system.entity.SysComplexPara;
import com.hs.system.util.OpLogUtil;
/**
 * 系统复杂参数
 * @author SuperL
 *
 */

@Controller
@RequestMapping("/complex/para")
public class SysComplexParaController  extends BaseController {
	private static final String url="system/syscomplexpara";
    @Autowired
    private ComplexParaApi complexParaServer;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model){
        return url+"/index";
    }
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
    public Page<?> get(Page<Object> page, Model model){
        return page;
    }
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
    @ResponseBody
    public Page<SysComplexPara> list(Page<SysComplexPara> page){
		Page<SysComplexPara> sysComplexParas =complexParaServer.querySysComplexParas(page);
        return sysComplexParas;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
    @ResponseBody
    public Page<Object> save(Page<Object> page,SysComplexPara complexPara ){
		complexParaServer.save(complexPara);
		UserProfile userProfile = this.getCurUser();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_SYSTEM+"12000001",CommonConstant.OP_TYPE_ADD,userProfile.getStaffName()+"新增系统复杂参数","");
        return page;
	}

}
