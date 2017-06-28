package com.nm.api.usercenter;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nm.base.framework.core.cmd.Command;
import com.nm.core.base.NewBaseClientApiController;
import com.nm.service.login.RegisterApiService;

/**
 * 
 * 
 * @author wangqin
 *
 * @date 2017年5月10日 下午5:45:39
 */
@Controller
@RequestMapping("/api/my")
public class MyApiController extends NewBaseClientApiController 
{
	
	 @Resource
	    private RegisterApiService registerApiService;

	

	/**
	 * 修改密码
	 * @param cmd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/modfiyPwd/v1", method=RequestMethod.POST)
	public Command modfiyPwdByStaffNo(Command cmd)
	{
		Map<String, Object> reqMap = cmd.getInMapObject("userInfo");
		
		registerApiService.updatePassWord(reqMap);
		
		return cmd;
	}
	
	

	

}
