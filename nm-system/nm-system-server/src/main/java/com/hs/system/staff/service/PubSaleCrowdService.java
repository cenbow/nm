package com.hs.system.staff.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.system.api.PubSaleCrowdApi;
import com.hs.system.entity.PubSaleCrowd;
import com.hs.system.entity.SysStaff;
import com.hs.system.group.PubGroupCrowdService;
import com.hs.system.staff.mapper.PubSaleCrowdMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * PUB_销售群 业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubSaleCrowdService implements PubSaleCrowdApi{
	@Autowired
	private PubSaleCrowdMapper pubSaleCrowdMapper;
	@Autowired
	private PubSalerGroupService pubSalerGroupService;
	@Autowired
	private PubGroupCrowdService pubGroupCrowdService;
//	@Autowired
//	private PubProdGroupService PubProdGroupService;
	//TODO 未处理产品的关联关系
	
	/**
	 * 新增 PUB_销售群
	 * @param pubSaleCrowd 新增对象
	 */
	@Transactional
	public void insert(PubSaleCrowd pubSaleCrowd){
		pubSaleCrowdMapper.insert(pubSaleCrowd);
	}

	/**
	 * 通过主键修改 PUB_销售群
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubSaleCrowdMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_销售群
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubSaleCrowdMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PUB_销售群 对象
	 * @param primaryKey 主键
	 * @return PUB_销售群对象
	 */
	public PubSaleCrowd getByPrimaryKey(String primaryKey){
		return pubSaleCrowdMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_销售群 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubSaleCrowd> queryForList(Map<String, Object> param){
		return pubSaleCrowdMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_销售群 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubSaleCrowd> queryForPage(Page<PubSaleCrowd> page){
		pubSaleCrowdMapper.queryForList(page.getPageParams());
		return (Page<PubSaleCrowd>)page.getPageParams().get(Page.KEY);
	}
	
	//////////////////////////////////////////////////////////////////////////////
	/**
	 * 保存或者更新 销售群
	 * @param pubSaleCrowd
	 */
	@Transactional
	public void save(PubSaleCrowd pubSaleCrowd){
		String no = pubSaleCrowd.getCrowdNo();
		if(StringUtils.isEmpty(no)){//保存
			List<PubSaleCrowd> lst = queryForList(BeanUtils.bean2map(pubSaleCrowd));
			if(lst!=null && lst.size()>0){return;}
			pubSaleCrowd.setCrowdNo(RandomUtil.getUUID());
			insert(pubSaleCrowd);
		}else{//更新
			Map<String,Object> param = BeanUtils.bean2map(pubSaleCrowd);
			updateByPrimaryKeySelective(param);
		}
	}
	
	/**
	 * 根据CrowdNo 删除 销售群
	 * @param CrowdNo
	 */
	@Transactional
	public void deleteByNo(String crowdNo){
		if(StringUtils.isEmpty(crowdNo)){
			throw new AppException("群编号不可为空");
		}
//		if(PubProdGroupService.queryExistRelaProdAndcrowd(crowdNo)){
			deleteByPrimaryKey(crowdNo);
			return;
//		}
//		throw new ServiceException("该销售群已被产品关联不可删除！");
	}
	
	/**
	 * 根据crowdNo 获取 销售群
	 * @param crowdNo
	 */
	public PubSaleCrowd getByNo(String crowdNo){
		return getByPrimaryKey(crowdNo);
	}
	
	/**
	 * 获取 销售群 list
	 * @param param
	 * @return
	 */
	public List<PubSaleCrowd> getList(Map<String,Object> param){
		return queryForList(param);
	}
	
	/**
	 * 分页查询 销售群
	 * @param page
	 * @return
	 */
	public Page<PubSaleCrowd> querySaleCrowd(Page<PubSaleCrowd> page){
		return queryForPage(page);
	}
	
	/**
	 * 执行 销售群 规则 ，返回销售list
	 * @param CrowdNo
	 * @return
	 */
	public List<SysStaff> executeRule(String crowdNo){
		if(StringUtils.isEmpty(crowdNo)){
			throw new AppException("群编号不可为空");
		}
		PubSaleCrowd crowd = getByNo(crowdNo);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("rule", crowd.getCrowdRule());
		return pubSaleCrowdMapper.executeRule(param);
	}
	
	/**
	 * 分页执行 销售群 规则 ，返回销售list
	 * 必须的参数 CrowdNo
	 * 可选传入的参数 staffName
	 * 
	 * @param page
	 * @return
	 */
	public Page<SysStaff> executeRulePage(Page<SysStaff> page){
		String crowdNo = (String)page.getParams().get("crowdNo");
		if(StringUtils.isEmpty(crowdNo)){
			throw new AppException("群编号不可为空");
		}
		PubSaleCrowd crowd = getByNo(crowdNo);
		String rule = crowd.getCrowdRule();
		String staffName = (String)page.getParams().get("staffName");
		if(!StringUtils.isEmpty(staffName)){
			rule = "SELECT * FROM ( "+rule+" ) r WHERE r.STAFF_NAME like '%"+staffName+"%' ";
		}
		page.getPageParams().put("rule", rule);
		pubSaleCrowdMapper.executeRule(page.getPageParams());
		return (Page<SysStaff>)page.getPageParams().get(page.KEY);
	}

	/**
	 * 校验规则是否可用
	 * 
	 * @return
	 */
	public boolean validRule(String rule){
		if(StringUtils.isEmpty(rule)) throw new AppException("规则不可以为空");
		Map<String , Object> param = new HashMap<>();
		param.put("rule", rule);
		try {
			List<SysStaff> lst = pubSaleCrowdMapper.executeRule(param);
			if(lst!=null) return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 分页查询销售组/销售群下的 员工
	 * 必须的参数property,crowdNo
	 * 
	 * @param page
	 * @return
	 */
	public Page<SysStaff> queryGrpCrowdStaff(Page<SysStaff> page) {
		String property = (String)page.getParams().get("property");
		String groupNo = (String) page.getParams().get("groupNo");
		
		if(PubBusinessConstant.SALETYPE_GROUP.equals(property) && StringUtils.isNotEmpty(groupNo)){//销售组
			page.getParams().put("groupNo", groupNo);
			return pubSalerGroupService.querySaler(page);
		}
		if(PubBusinessConstant.SALETYPE_CROWD.equals(property) && StringUtils.isNotEmpty(groupNo) ){//销售群
			page.getParams().put("crowdNo", groupNo);
			return executeRulePage(page);
		}
		return page;
	}
	
	/**
	 * 删除销售群/销售组
	 * @param param
	 */
	@Transactional
	public void deleteGrpCrowd(Map<String,Object> param){
		pubGroupCrowdService.deleteGrpCrowd(param);
	}
	
}