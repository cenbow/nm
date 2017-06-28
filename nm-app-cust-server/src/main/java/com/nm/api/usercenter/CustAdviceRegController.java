package com.nm.api.usercenter;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nm.base.framework.core.cmd.Command;
import com.nm.core.base.NewBaseClientApiController;
import com.nm.service.usercenter.CustAdviceRegService;
/**
 * 意见反馈
 * 
 * @author wangqin
 *
 * @date 2017年5月12日 上午11:29:25
 */
@Controller
@RequestMapping("/api/advice")
public class CustAdviceRegController extends NewBaseClientApiController {
	
	
	     @Resource
	    private CustAdviceRegService custAdviceRegService;
	     
		/**
		 * 新增意见反馈
		 * @param cmd
		 * @return
		 */
	     @ResponseBody
	     @RequestMapping(value = "/addAdvice/v1", method = RequestMethod.POST)
	     public Command getCurrentPrincipal(Command cmd) {
	         Map<String, Object> reqMap = cmd.getInMapObject("adviceInfo");
	         custAdviceRegService.addAdviceReg(reqMap);

	         return cmd;
	     }
	     
	     
	     

}
