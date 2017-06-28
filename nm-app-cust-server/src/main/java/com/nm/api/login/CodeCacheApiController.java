package com.nm.api.login;

import com.nm.base.framework.core.cmd.Command;
import com.nm.cmd.CodeApiCmd;
import com.nm.service.login.CodeCacheApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class CodeCacheApiController {
	
	@Autowired private CodeCacheApiService codeCacheApiService;
	
	@ResponseBody
	@RequestMapping(value = "/api/code/cache", method=RequestMethod.POST)
	public Command codeCache(Command cmd) {
		List<CodeApiCmd> codes = cmd.getInArray("codes", CodeApiCmd.class);
		Map<String, Object> map = codeCacheApiService.getCode(codes);
		cmd.setOut(map);
		return cmd;
	}

	@ResponseBody
	@RequestMapping(value = "/api/code/cachev2", method=RequestMethod.POST)
	public Command codeCacheV2(Command cmd) {
		List<CodeApiCmd> codes = cmd.getInArray("codes", CodeApiCmd.class);
		Map<String, Object> map = codeCacheApiService.getCodeV2(codes);
		cmd.setOut(map);
		return cmd;
	}
	@ResponseBody
	@RequestMapping(value="/api/code/getCodeNum")
	public Command codeByNameType(Command cmd){
		String type=cmd.getInString("type");
		String name=cmd.getInString("name");
		String  code=codeCacheApiService.getCodeByNameType(type,name);
		cmd.setOut(code);
		return cmd;
	}

}
