package com.hs.loan.acct.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.acct.entity.PubSubjInfo;
import com.hs.loan.acct.mapper.PubSubjInfoMapper;
import com.hs.system.entity.SysOrg;

/**
 * PUB_科目信息 业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubSubjInfoService{
	@Autowired
	private PubSubjInfoMapper pubSubjInfoMapper;
	
	/**
	 * 新增 PUB_科目信息
	 * @param pubSubjInfo 新增对象
	 */
	@Transactional
	public void insert(PubSubjInfo pubSubjInfo){
		pubSubjInfoMapper.insert(pubSubjInfo);
	}

	/**
	 * 通过主键修改 PUB_科目信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubSubjInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_科目信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubSubjInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PUB_科目信息 对象
	 * @param primaryKey 主键
	 * @return PUB_科目信息对象
	 */
	public PubSubjInfo getByPrimaryKey(String primaryKey){
		return pubSubjInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_科目信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubSubjInfo> queryForList(Map<String, Object> param){
		return pubSubjInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_科目信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubSubjInfo> queryForPage(Page<PubSubjInfo> page){
		pubSubjInfoMapper.queryForList(page.getPageParams());
		return (Page<PubSubjInfo>)page.getPageParams().get(Page.KEY);
	}
	////////////////////////////////////////////////////////////////////////

	/**
	 * 通过id获取一个有效的科目信息
	 * @param subjId
	 * @return
	 */
	public PubSubjInfo getById(String subjId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("subjId", subjId);
		map.put("isUse", CommonConstant.COMMON_YES);
		List<PubSubjInfo> list= pubSubjInfoMapper.queryForList(map);
		PubSubjInfo subj = null;
		if(list != null && list.size() > 0){
			subj = list.get(0);
		}
		return subj;
	}
	
	/**
	 * 获取有效的 科目信息列表
	 * @param param
	 * @return
	 */
	public List<PubSubjInfo> getList(Map<String,Object> param){
		param.put("isUse", CommonConstant.COMMON_YES);
		return queryForList(param);
	}
	
	/**
	 * 分页查询有效的 科目信息
	 * @param page
	 * @return
	 */
	public Page<PubSubjInfo> querySubjInfo(Page<PubSubjInfo> page){
		page.getPageParams().put("isUse", CommonConstant.COMMON_YES);
		return queryForPage(page);
	}
	
	
}