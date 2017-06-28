package com.nm.mapper.usercenter;

import com.nm.mybatis.annotation.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
/**
 * 
 * 
 * @author wangqin
 *
 * @date 2017年5月10日 上午11:07:35
 */
@MyBatisRepository
public interface VersionToolMapper {

	//查询版本信息
	Map<String,String> queryVersionDetail(@Param("appType") String appType);
}
