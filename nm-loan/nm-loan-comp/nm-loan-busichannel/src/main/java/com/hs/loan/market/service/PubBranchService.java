package com.hs.loan.market.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hs.loan.market.bo.PubBranchBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.market.entity.PubBranch;
import com.hs.loan.market.entity.PubSalerBranch;
import com.hs.loan.market.mapper.PubBranchGroupMapper;
import com.hs.loan.market.mapper.PubBranchMapper;
import com.hs.loan.market.mapper.PubSalerBranchMapper;
//import com.hs.loan.prod.service.PubProdStrService;
import com.hs.system.entity.SysStaff;
import com.hs.system.staff.PubSysStaffService;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * PUB_网点信息 业务处理
 * @author autocreate
 * @create 2015-10-15
 */
@Service
@Transactional(readOnly=true)
public class  PubBranchService {
	@Autowired
	private PubBranchMapper pubBranchMapper;
	//网点与销售关联关系mapper
	@Autowired
	private PubSalerBranchMapper pubSalerBranchMapper;
	//员工service
	@Autowired
	private PubSysStaffService pubSysStaffService;
	@Autowired
	private PubBranchGroupMapper pubBranchGroupMapper;
	//产品与网点的关系 组件
//	@Autowired
//	private PubProdStrService prodStrService;
	
	
	/**
	 * 新增 PUB_网点信息
	 * @param pubBranch 新增对象
	 */
	@Transactional
	public void insert(PubBranch pubBranch){
		pubBranchMapper.insert(pubBranch);
	}

	/**
	 * 通过主键修改 PUB_网点信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		pubBranchMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 PUB_网点信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		pubBranchMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 PUB_网点信息 对象
	 * @param primaryKey 主键
	 * @return PUB_网点信息对象
	 */
	public PubBranch getByPrimaryKey(String primaryKey){
		return pubBranchMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 PUB_网点信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<PubBranch> queryForList(Map<String, Object> param){
		return pubBranchMapper.queryForList(param);
	}
	
	/**
	 * 查询 PUB_网点信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<PubBranch> queryForPage(Page<PubBranch> page){
		pubBranchMapper.queryForList(page.getPageParams());
		return (Page<PubBranch>)page.getPageParams().get(Page.KEY);
	}

	/**
	 * 查询网点组不包含的网点信息列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<com.hs.loan.market.bo.PubBranchBo> queryBranchGroupNotExists(Page<com.hs.loan.market.bo.PubBranchBo> page){
		//查询网点组不包含的网点信息列表
		pubBranchMapper.queryBranchGroupNotExists(page.getPageParams());
		return (Page<com.hs.loan.market.bo.PubBranchBo>)page.getPageParams().get(Page.KEY);
	}

	/**
	 * 通过产品编号查询未与产品关联的网点列表
	 * @param proNo 产品编号
	 * @return
	 */
	public Page<com.hs.loan.market.bo.PubBranchBo> queryBranchListByProdNo(Page<com.hs.loan.market.bo.PubBranchBo> page){
		pubBranchMapper.queryBranchListByProdNo(page.getPageParams());
		Page<PubBranchBo> page1 = (Page<PubBranchBo>)page.getPageParams().get(Page.KEY);
		return page1;
	}



	/**
	 * 查询 PUB_网点信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<com.hs.loan.market.bo.PubBranchBo> queryBranchList(Page<com.hs.loan.market.bo.PubBranchBo> page){

		//pubBranchMapper.queryList(page.getPageParams());

		//查询网点组不包含的网点信息列表
		pubBranchMapper.queryBranchGroupNotExists(page.getPageParams());

		return (Page<com.hs.loan.market.bo.PubBranchBo>)page.getPageParams().get(Page.KEY);
	}
	/**
	 * 更新 网点信息
	 * @param pubBranch
	 */
	@Transactional
	public void update(PubBranch pubBranch){
		if(org.apache.commons.lang.StringUtils.isBlank(pubBranch.getBankNo())){
			throw new AppException("网点编号不可为空");
		}
		if(!pubBranch.getBranchNo().matches("[0-9A-Za-z-]*")){
			throw new AppException("网点编号只能是［数字］［字母］［-］");
		}
		Map<String,Object> param = BeanUtils.bean2map(pubBranch);
		param.put("updtDate", new Date());
		param.remove("stat");
		param.remove("instDate");
		updateByPrimaryKeySelective(param);
	}
	////////////////////////////////////////////////////////////////////////////
	/**
	 * 保存或更新 网点信息
	 * branchNo 是必须的
	 */
	@Transactional
	public void save(PubBranch pubBranch){
		if(StringUtils.isEmpty(pubBranch.getBranchNo())){
			throw new AppException("网点编号不可为空");
		}
		if(!pubBranch.getBranchNo().matches("[0-9A-Za-z-]*")){
			throw new AppException("网点编号只能是［数字］［字母］［-］");
		}
			//查询禁用状态已经存在的网点
			List<String> selectExistsDisableState = pubBranchMapper.selectExistsDisableState(pubBranch);
			if(null!=selectExistsDisableState&&0<selectExistsDisableState.size()){
			//参数map
				HashMap<String,Object> paramMap=new HashMap<>();
				//网点编码
				paramMap.put("branchNo",selectExistsDisableState.get(0));
				//设置状态为正常
				paramMap.put("stat",CommonConstant.STAT_ENABLE);
				//根据网点编码更新状态
				updateByPrimaryKeySelective(paramMap);
			}else{
				//查询是否已经存在该网点(根据网点名称，账户名称，账户号查询是否已经存在该网点)
				int pubBranchExistsResult=pubBranchMapper.selectExistPubBranch(pubBranch);
				//通过网点编号查询该网点是否存在
				int existsPubBranchByBranchNoResult = pubBranchMapper.selectExistsPubBranchByBranchNo(pubBranch);
				if(pubBranchExistsResult>0||existsPubBranchByBranchNoResult>0){
					throw new AppException("该网点已经存在");
				}

				//保存
				pubBranch.setInstDate(new Date());
				//设置状态为正常
				pubBranch.setStat(CommonConstant.STAT_ENABLE);
				insert(pubBranch);
			}
	}
	
	/**
	 * 通过branchNo删除网点基本信息,(逻辑删除)
	 * 禁用状态
	 * @param branchNo
	 */
	@Transactional
	public void rmvByNo(String branchNo){
		if(StringUtils.isEmpty(branchNo)){
			throw new AppException("branchNo 不可为空");
		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("branchNo", branchNo);
		param.put("stat", CommonConstant.STAT_DISABLE);
		updateByPrimaryKeySelective(param);
	}
	
	/**
	 * 通过branchNo 删除网点基本信息 (过时的方法)
	 * 同时删除网点与产品，网点与销售，网点与网点组，的关联关系
	 * @param branchNo
	 */
	@Transactional
	@Deprecated
	public void deleteByNo(String branchNo){
		if(StringUtils.isEmpty(branchNo)){
			throw new AppException("branchNo 不可为空");
		}
		deleteByPrimaryKey(branchNo);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("branchNo", branchNo);
		//删除网点与销售的关系
		pubSalerBranchMapper.rmvBrhSalerRel(param);
		//删除网点与网点组的关系
		pubBranchGroupMapper.rmvBrhGrpRel(param);
		//删除网点与产品的关系--------------
		//prodStrService.deleteBybranchNo(branchNo);
	}

	/**
	 * 通过branchNo获取网点基本信息
	 */
	public PubBranch getByNo(String branchNo) {
		return getByPrimaryKey(branchNo);
	}

	/**
	 * 查询网点列表，只会查出没有被逻辑删除的
	 */
	public List<PubBranch> getList(Map<String, Object> param) {
		param.put("stat", CommonConstant.STAT_ENABLE);
		return queryForList(param);
	}

	/**
	 * 查询所有，包括逻辑删除的和未逻辑删除的都会查出
	 * @param param
	 * @return
	 */
	public List<PubBranch> getListAll(Map<String, Object> param){
		return queryForList(param);
	}
	
	
	/**
	 * 分页查询网点，只会查询出未被逻辑删除的
	 */
	public Page<PubBranchBo> queryBranch(Page<PubBranchBo> page) {
		page.getParams().put("stat", CommonConstant.STAT_ENABLE);
		return queryBranchList(page);
	}
	
	/**
	 * 分页查询 网点 所有，是否被逻辑删除都会查询出来
	 * @param page
	 * @return
	 */
	public Page<PubBranch> queryBranchAll(Page<PubBranch> page) {
		return queryForPage(page);
	}

	/**
	 * 保存或更新 网点 及 其关联的销售的关联关系
	 */
	@Transactional
	public void saveBranchSaler(PubBranch pubBranch, List<String> staffNoLst) {
		//先保存或者更新网点
		save(pubBranch);
		//保存新的关联关系
		saveBrhSalerRel(pubBranch.getBranchNo(),staffNoLst);
	}

	/**
	 * 通过branchNo获取网点下的销售lst
	 * 必须的参数branchNo,
	 * 
	 */
	public List<SysStaff> getBranchSalerLst(Map<String,Object> param) {
		if(StringUtils.isEmpty((String)param.get("branchNo"))){
			throw new AppException("网点编号不可为空");
		}
		List<PubSalerBranch> lst = pubSalerBranchMapper.queryForList(param);
		if(lst==null || lst.size()==0){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for(PubSalerBranch psb : lst){
			sb.append("'"+psb.getId()+"'").append(",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		param.put("staffNos", sb.toString());
		List<SysStaff> staffLst = pubSysStaffService.getIncludeStaffLst(param);
		return staffLst;
	}

	/**
	 * 分页查询网点下的销售
	 * 必须的参数：branchNo
	 */
	public Page<SysStaff> getBranchSalerPage(Page<SysStaff> page) {
		if(StringUtils.isEmpty((String)page.getParams().get("branchNo"))){
			throw new AppException("网点编号不可为空");
		}
		List<PubSalerBranch> lst = pubSalerBranchMapper.queryForList(page.getParams());
		if(lst==null || lst.size() == 0){
			page.setList(null);
			return page;
		}
		
		StringBuffer sb = new StringBuffer();
		for(PubSalerBranch psb : lst){
			sb.append("'"+psb.getStaffNo()+"'").append(",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		page.getParams().put("staffNos", sb.toString());
		
		pubSysStaffService.getIncludeStaffLst(page.getPageParams());
		return (Page<SysStaff>) page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 分页查询没在当前网点下的销售
	 * 必须的参数branchNo
	 * 
	 * @param page
	 * @return
	 */
	public Page<SysStaff> queryNotInBrhSaler(Page<SysStaff> page){
		if(StringUtils.isEmpty((String)page.getParams().get("branchNo"))){
			throw new AppException("网点编号不可为空");
		}
		
		List<PubSalerBranch> lst = pubSalerBranchMapper.queryForList(page.getParams());
		if(lst!=null && lst.size() >0){
			StringBuffer sb = new StringBuffer();
			for(PubSalerBranch psb : lst){
				sb.append("'"+psb.getStaffNo()+"'").append(",");
			}
			sb.deleteCharAt(sb.lastIndexOf(","));
			page.getParams().put("staffNos", sb.toString());
		}
		
		pubSysStaffService.getNotIncludeStaffLst(page.getPageParams());
		return (Page<SysStaff>) page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 获取销售绑定的网点
	 * 
	 * @param staffNo
	 * @return
	 */
	public List<PubBranch> getSalerBindedBranchList(String staffNo){
		if(StringUtils.isEmpty(staffNo)){
			throw new AppException("销售编号不能为空");
		}
		Map<String,Object> param = new HashMap<>();
		param.put("staffNo", staffNo);
		param.put("stat", CommonConstant.STAT_ENABLE);
		return pubBranchMapper.getSalerBindedBranchList(param);
	}
	
	/**
	 * 获取销售绑定的网点 内置
	 * 
	 * @param staffNo
	 * @return
	 */
	public List<PubBranch> getSalerBindedBranchList(String staffNo,List<String> branchNos){
		if(StringUtils.isEmpty(staffNo)){
			throw new AppException("销售编号不能为空");
		}
		Map<String,Object> param = new HashMap<>();
		param.put("staffNo", staffNo);
		param.put("stat", CommonConstant.STAT_ENABLE);
		param.put("branchNos", branchNos);
		return pubBranchMapper.getSalerBindedBranchList(param);
	}
	/**
	 * 获取销售绑定的网点 非内置
	 * 
	 * @param staffNo
	 * @return
	 */
	public List<PubBranch> getSalerBindedBranchListNotInner(String staffNo){
		if(StringUtils.isEmpty(staffNo)){
			throw new AppException("销售编号不能为空");
		}
		Map<String,Object> param = new HashMap<>();
		param.put("staffNo", staffNo);
		param.put("stat", CommonConstant.STAT_ENABLE);
		param.put("notInner", "999");
		return pubBranchMapper.getSalerBindedBranchList(param);
	}
	
	
	
	/**
	 * 删除网点和销售的关系
	 */
	@Transactional
	public void rmvBrhSalerRel(String branchNo, List<String> staffNoLst){
		if(StringUtils.isEmpty(branchNo)){
			throw new AppException("网点编号不可为空");
		}
		if(staffNoLst==null || staffNoLst.size()==0){
			throw new AppException("销售(员工)编号不可为空");
		}
		for(String staffNo : staffNoLst){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("branchNo",branchNo);
			param.put("staffNo", staffNo);
			pubSalerBranchMapper.rmvBrhSalerRel(param);
		}
	}
	
	
	/**
	 * 保存或者更新 网点和销售关系saveBranchSalerRel
	 * list中的branchNo是必须的且都相同。
	 * @param pubSalerBranch
	 */
	@Transactional
	public void saveBrhSalerRel(String branchNo , List<String> staffNoLst){
		if(StringUtils.isEmpty(branchNo)){
			throw new AppException("网点编号不能为空");
		}
		if(staffNoLst==null || staffNoLst.size()==0){
			throw new AppException("销售(员工)编号不可为空");
		}
		for(String staffNo : staffNoLst){
			Map<String,Object> param = new HashMap<>();
			param.put("branchNo", branchNo);
			param.put("staffNo", staffNo);
			pubSalerBranchMapper.rmvBrhSalerRel(param);
			
			//保存
			PubSalerBranch psb = new PubSalerBranch();
			psb.setBranchNo(branchNo);
			psb.setStaffNo(staffNo);
			psb.setId(RandomUtil.getUUID());
			pubSalerBranchMapper.insert(psb);
		}
	}
	/**
	 * 获取挂单商户失效条数
	 * @param branchName
	 * @return
	 */
	public List<Map<String, Object>> judgeBranchStatusByFaild(String branchName) {
		// TODO Auto-generated method stub
		return pubBranchMapper.judgeBranchStatusByFaild(branchName);
	}
	
}