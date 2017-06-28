package com.hs.system.staff.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.api.PubSalerGroupApi;
import com.hs.system.entity.PubGroupStaff;
import com.hs.system.entity.PubSalerGroup;
import com.hs.system.entity.SysStaff;
import com.hs.system.staff.mapper.PubGroupStaffMapper;
import com.hs.system.staff.mapper.PubSalerGroupMapper;
import com.hs.system.staff.mapper.SysStaffMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * PUB_销售组信息 业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubSalerGroupService implements PubSalerGroupApi{
	@Autowired
	private PubSalerGroupMapper pubSalerGroupMapper;
	//销售组与员工关系mapper
	@Autowired
	private PubGroupStaffMapper pubGroupStaffMapper;
	//员工mapper
	@Autowired
	private SysStaffMapper sysStaffMapper;
//	@Autowired
//	private PubProdTeamService pubProdTeamService;
	//TODO 未处理产品的关联关系
	
	/**
	 * 新增 PUB_销售组信息
	 * @param pubSalerGroup 新增对象
	 */
	@Transactional
	public void insert(PubSalerGroup pubSalerGroup){
		pubSalerGroupMapper.insert(pubSalerGroup);
	}

	/**
	 * 通过主键修改 PUB_销售组信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubSalerGroupMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_销售组信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubSalerGroupMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PUB_销售组信息 对象
	 * @param primaryKey 主键
	 * @return PUB_销售组信息对象
	 */
	public PubSalerGroup getByPrimaryKey(String primaryKey){
		return pubSalerGroupMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_销售组信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubSalerGroup> queryForList(Map<String, Object> param){
		return pubSalerGroupMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_销售组信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubSalerGroup> queryForPage(Page<PubSalerGroup> page){
		pubSalerGroupMapper.queryForList(page.getPageParams());
		return (Page<PubSalerGroup>)page.getPageParams().get(Page.KEY);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 保存或者更新 销售组基本信息
	 * @param pubSalerGroup
	 */
	@Transactional
	public void save(PubSalerGroup pubSalerGroup){
		String no = pubSalerGroup.getGroupNo();
		if(StringUtils.isEmpty(no)){//保存
			List<PubSalerGroup> lst = queryForList(BeanUtils.bean2mapExclude(pubSalerGroup, "instDate,updtDate"));
			if(lst!=null&&lst.size()>0){return;}
			pubSalerGroup.setGroupNo(RandomUtil.getUUID());
			pubSalerGroup.setInstDate(new Date());
			insert(pubSalerGroup);
		}else{//修改
			Map<String,Object> param = BeanUtils.bean2map(pubSalerGroup);
			param.put("updtDate", new Date());
			updateByPrimaryKeySelective(param);
		}
	}
	
	/**
	 * 通过组编号 删除 销售组，同时会删除其与销售的关系
	 * @param groupNo
	 */
	@Transactional
	public void deleteByNo(String groupNo){
		if(StringUtils.isEmpty(groupNo)){
			throw new AppException("groupNo不可为空");
		}
		
//		if(pubProdTeamService.queryExistRelaProdAndgroupNo(groupNo)){
			deleteByPrimaryKey(groupNo);
			//删除与销售的关系
			deleteSaler(groupNo);
			return;
//		}
//		throw new ServiceException("该销售组已被产品关联不可删除！");
	}
	
	/**
	 * 通过组编号 获取 销售组的基本信息
	 * @param groupNo
	 * @return
	 */
	public PubSalerGroup getByNo(String groupNo){
		return getByPrimaryKey(groupNo);
	}
	
	/**
	 * 获取 销售组基本信息 list
	 * @param param
	 * @return
	 */
	public List<PubSalerGroup> getList(Map<String,Object> param){
		return queryForList(param);
	}
	
	/**
	 * 分页查询 销售组基本信息
	 * @param page
	 * @return
	 */
	public Page<PubSalerGroup> querySalerGroup(Page<PubSalerGroup> page ){
		return queryForPage(page);
	}
	
	/**
	 * 获取销售组下的 销售List
	 * 必须的参数 groupNo
	 * @param param
	 * @return
	 */
	public List<SysStaff> getSalerList(Map<String,Object> param){
		if(StringUtils.isEmpty((String)param.get("groupNo"))){
			throw new AppException("groupNo不可为空");
		}
		return sysStaffMapper.queryForList(param);
	}
	
	/**
	 * 分页查询销售组下的 销售
	 * 必须的参数 groupNo
	 * @param page
	 * @return
	 */
	public Page<SysStaff> querySaler(Page<SysStaff> page){
		if(StringUtils.isEmpty((String)page.getParams().get("groupNo"))){
			throw new AppException("groupNo不可为空");
		}
		sysStaffMapper.queryForList(page.getPageParams());
		return (Page<SysStaff>)page.getPageParams().get(page.KEY);
	}
	
	/**
	 * 分页查询 没在当前销售组下的 销售
	 * 必须的参数 groupNo
	 * @param page
	 * @return
	 */
	public Page<SysStaff> queryNotInGrpSaler(Page<SysStaff> page){
		if(StringUtils.isEmpty((String)page.getParams().get("groupNo"))){
			throw new AppException("groupNo不可为空");
		}
		sysStaffMapper.queryNotInGrpSalerList(page.getPageParams());
		return (Page<SysStaff>)page.getPageParams().get(page.KEY);
	}
	
	/**
	 * 保存或更新 销售组 与 销售的关系
	 */
	@Transactional
	public void saveGrpSalerRel(String groupNo,List<String> staffNos){
		if(StringUtils.isEmpty(groupNo)){
			throw new AppException("groupNo不可为空");
		}
		if(staffNos==null || staffNos.size()==0){
			throw new AppException("销售不可为空");
		}
		for(String staffNo : staffNos){
			//先删除组和销售的关系
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("groupNo",groupNo);
			param.put("staffNo", staffNo);
			pubGroupStaffMapper.deleteGrpSalerRel(param);
			
			//重新保存组和销售的关系
			PubGroupStaff pgs = new PubGroupStaff();
			pgs.setId(RandomUtil.getUUID());
			pgs.setGroupNo(groupNo);
			pgs.setStaffNo(staffNo);
			pubGroupStaffMapper.insert(pgs);
		}
	}
	
	/**
	 * 保存或者更新 销售组 与 销售关系
	 * @param pubSalerGroup
	 * @param staffNos
	 */
	@Transactional
	public void saveGrpSaler(PubSalerGroup pubSalerGroup,List<String> staffNos){
		save(pubSalerGroup);
		saveGrpSalerRel(pubSalerGroup.getGroupNo(), staffNos);
	}
	
	/**
	 * 删除销售组下的 所有 销售
	 * @param groupNo
	 */
	@Transactional
	public void deleteSaler(String groupNo){
		if(StringUtils.isEmpty(groupNo)){
			throw new AppException("groupNo不可为空");
		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("groupNo",groupNo);
		pubGroupStaffMapper.deleteGrpSalerRel(param);
	}
	
	/**
	 * 删除销售组下  的  销售
	 * @param groupNo
	 * @param staffNoLst
	 */
	@Transactional
	public void deleteGrpSalerRel(String groupNo,List<String> staffNoLst){
		if(StringUtils.isEmpty(groupNo)){
			throw new AppException("groupNo不可为空");
		}
		for(String staffNo : staffNoLst){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("groupNo",groupNo);
			param.put("staffNo", staffNo);
			pubGroupStaffMapper.deleteGrpSalerRel(param);
		}
	}
	
	
}