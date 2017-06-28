package com.hs.system.group;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.system.group.bo.PubCrowdGroupBO;
import com.hs.system.mapper.PubGroupCrowdMapper;
import com.hs.utils.StringUtils;

@Service
@Transactional(readOnly=true)
public class PubGroupCrowdService {

	@Autowired
	private PubGroupCrowdMapper pubGroupCrowdMapper;
	
	/**
	 * 参数 teamName
	 * @param param
	 * @return
	 */
	public List<PubCrowdGroupBO> getGrpCrowdList(Map<String,Object> param){
		return pubGroupCrowdMapper.getGrpCrowdList(param);
	}
	
	/**
	 * 分页
	 * @param page
	 * @return
	 */
	public Page<PubCrowdGroupBO> queryGrpCrowd(Page<PubCrowdGroupBO> page){
		pubGroupCrowdMapper.getGrpCrowdList(page.getPageParams());
		return (Page<PubCrowdGroupBO>)page.getPageParams().get(page.KEY);
	}
	
	/**
	 * 删除销售群/组
	 * @param param
	 */
	public void deleteGrpCrowd(Map<String,Object> param){
		String property = (String)param.get("property");
		String crowdNo = (String)param.get("crowdNo");
		
		if(PubBusinessConstant.SALETYPE_GROUP.equals(property) && StringUtils.isNotEmpty(crowdNo)){
			param.put("groupNo", crowdNo);
			pubGroupCrowdMapper.deleteGrp(param);
			return;
		}
		
		if(PubBusinessConstant.SALETYPE_CROWD.equals(property) && StringUtils.isNotEmpty(crowdNo)){
			param.put("crowdNo", crowdNo);
			pubGroupCrowdMapper.deleteCrowd(param);
			return;
		}
		
	}
	
}
