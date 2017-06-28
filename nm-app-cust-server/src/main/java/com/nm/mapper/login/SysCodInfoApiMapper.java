package com.nm.mapper.login;

import com.hs.system.entity.SysCodInfo;
import com.nm.mybatis.annotation.MyBatisRepository;
import com.nm.mybatis.mapper.common.base.select.SelectMapper;
import com.nm.mybatis.mapper.common.example.SelectByExampleMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface SysCodInfoApiMapper extends SelectMapper<SysCodInfo>,  SelectByExampleMapper<SysCodInfo> {
	
	List<Map<String, Object>> selectCodeGroupInfo(@Param("types") List<String> types);

	String selectCodeByNameType(@Param("type") String type, @Param("name") String name);

	String getCodeNameByTypeAndNum(@Param("type") String type, @Param("codValue") String codValue);

}
