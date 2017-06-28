package com.hs.system.area.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.system.api.RegionalBelongApi;
import com.hs.system.area.PubSysRegionalBelongService;
import com.hs.system.entity.SysRegionalBelong;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * SYS_地域归属表 业务处理
 * @author autocreate
 * @create 2015-10-30
 */
@Service
@Transactional(readOnly=true)
public class  PubSysRegionalBelongServer implements RegionalBelongApi{
	@Autowired
	private PubSysRegionalBelongService pubSysRegionalBelongService;

	@Override
	public List<SysRegionalBelong> querySysRegionalBelong(Map<String, Object> map) {
		return pubSysRegionalBelongService.queryForList(map);
	}

	@Override
	@Transactional
	public void save(SysRegionalBelong belong) {
		 if(StringUtils.isEmpty(belong.getId())){
			 belong.setId(RandomUtil.getUUID());
			 pubSysRegionalBelongService.insert(belong);
		 }else{
			 pubSysRegionalBelongService.updateByPrimaryKeySelective(BeanUtils.bean2map(belong));
		 }
	}

	@Override
	@Transactional
	public void delete(String id) {
		pubSysRegionalBelongService.deleteByPrimaryKey(id);
	}

	@Override
	public List<SysRegionalBelong> queryArea(String areano) {
		 List<SysRegionalBelong> belongs = pubSysRegionalBelongService.queryArea(areano);
		return belongs;
	}

	 
}