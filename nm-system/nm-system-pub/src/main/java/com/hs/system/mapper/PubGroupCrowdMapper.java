package com.hs.system.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.group.bo.PubCrowdGroupBO;

@MyBatisRepository
public interface PubGroupCrowdMapper {

	public List<PubCrowdGroupBO> getGrpCrowdList(Map<String, Object> param);

	public void deleteGrp(Map<String, Object> param);

	public void deleteCrowd(Map<String, Object> param);

	

}
