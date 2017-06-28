package com.nm.service.login;


import com.nm.cmd.CodeApiCmd;

import java.util.List;
import java.util.Map;

public interface CodeCacheApiService {

	Map<String, Object> getCode(List<CodeApiCmd> codes);

	Map<String, Object> getCodeV2(List<CodeApiCmd> codes);

	String getCodeByNameType(String type,String name);

	String getCodeNameByTypeAndNum(String type,String codValue);

	String getCodeNo(String type,String name);

}
