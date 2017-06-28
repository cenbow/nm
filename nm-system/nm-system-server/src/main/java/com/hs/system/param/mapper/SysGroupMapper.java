package com.hs.system.param.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.entity.SysCodInfo;
import com.hs.system.entity.SysGroup;

/**
 * 编码组 mapper
 * @author autocreate
 * @create 2015-09-26
 */
@MyBatisRepository
public interface  SysGroupMapper extends BaseMapper<SysGroup>{

	/**
	 * 通过grpid删除关联表sys_cod_group中关联的信息
	 */
	void deleteSysCodGrpByGrpId(@Param("id") String id);

	/**
	 * 保存codeinfo和grp的关系
	 * @param infoList
	 */
	void saveCodeGrpRelation(Map<String,Object> param);

	SysGroup querySysGrpBaseInfo(String groupId);

	List<Map<String, Object>> queryGrpCodeInfoLst(@Param("groupId") String groupId);

}