package com.hs.loan.cust.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.cust.entity.AppCustInfo;
import com.hs.loan.cust.entity.AppCustTeam;
import com.hs.loan.cust.entity.AppCustTeamInfo;
import com.hs.loan.cust.mapper.AppCustInfoMapper;
import com.hs.loan.cust.mapper.AppCustTeamInfoMapper;
import com.hs.loan.cust.mapper.AppCustTeamMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;

/**
 * APP_客户分组 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustTeamInfoService{
	@Autowired
	private AppCustTeamInfoMapper appCustTeamInfoMapper;
	//客户分组和客户的关系mapper
	@Autowired
	private AppCustTeamMapper appCustTeamMapper;
	//客户信息mapper
	@Autowired
	private AppCustInfoMapper appCustInfoMapper;
	
	/**
	 * 新增 APP_客户分组
	 * @param appCustTeamInfo 新增对象
	 */
	@Transactional
	public void insert(AppCustTeamInfo appCustTeamInfo){
		appCustTeamInfoMapper.insert(appCustTeamInfo);
	}

	/**
	 * 通过主键修改 APP_客户分组
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustTeamInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户分组
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustTeamInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户分组 对象
	 * @param primaryKey 主键
	 * @return APP_客户分组对象
	 */
	public AppCustTeamInfo getByPrimaryKey(String primaryKey){
		return appCustTeamInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户分组 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustTeamInfo> queryForList(Map<String, Object> param){
		return appCustTeamInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户分组 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustTeamInfo> queryForPage(Page<AppCustTeamInfo> page){
		appCustTeamInfoMapper.queryForList(page.getPageParams());
		return (Page<AppCustTeamInfo>)page.getPageParams().get(Page.KEY);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 新增或修改 客户分组信息
	 * @param appCustTeamInfo
	 */
	@Transactional
	public void save(AppCustTeamInfo appCustTeamInfo){
		String no = appCustTeamInfo.getCustTeam();
		if(StringUtils.isEmpty(no)){
			appCustTeamInfo.setCustTeam(RandomUtil.getUUID());
			appCustTeamInfo.setInstDate(new Date());
			insert(appCustTeamInfo);
		}else{
			Map<String,Object> param = BeanUtils.bean2map(appCustTeamInfo);
			param.put("endDate", appCustTeamInfo.getEndDate());
			param.put("beginDate", appCustTeamInfo.getBeginDate());
			updateByPrimaryKeySelective(param);
		}
	}
	
	/**
	 * 通过分组编号 删除分组
	 * @param custTeam
	 */
	@Transactional
	public void deleteByNo(String custTeam){
		deleteByPrimaryKey(custTeam);
		Map<String,Object> param = new HashMap<>();
		param.put("custTeam", custTeam);
		//删除组和客户的关系
		appCustTeamMapper.deleteGrpCustRel(param);
	}
	
	/**
	 * 分页查询 客户分组
	 * @param page
	 * @return
	 */
	public Page<AppCustTeamInfo> queryCustTeam(Page<AppCustTeamInfo> page){
		return queryForPage(page);
	}
	
	/**
	 * 分页查询 客户分组下的 客户信息
	 * 
	 * 必须的参数 custTeam
	 * 
	 * @param page
	 * @return
	 */
	public Page<AppCustInfo> queryGrpCust(Page<AppCustInfo> page){
		appCustInfoMapper.queryForList(page.getPageParams());
		return (Page<AppCustInfo>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 分页查询 不在当前客户组下的 客户信息
	 * 必须的参数 custTeam
	 * 
	 * @param page
	 * @return
	 */
	public Page<AppCustInfo> queryNotInGrpCust(Page<AppCustInfo> page){
		appCustTeamMapper.queryNotInGrpCust(page.getPageParams());
		return (Page<AppCustInfo>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 删除客户分组和客户的关系
	 * @param appCustTeam
	 */
	@Transactional
	public void deleteGrpCustRel(String custTeam ,List<String> custNoLst){
		if(custNoLst!=null && custNoLst.size()>0){
			for(String custNo:custNoLst){
				Map<String,Object> param = new HashMap<>();
				param.put("custTeam",custTeam);
				param.put("custNo", custNo);
				appCustTeamMapper.deleteGrpCustRel(param);
			}
		}
		
	}
	
	/**
	 * 保存或者更新 客户组和客户的关系
	 * 
	 * @param custTeam
	 * @param custNoLst
	 */
	@Transactional
	public void saveGrpCustRel(String custTeam,List<String> custNoLst){
		if(custNoLst!=null && custNoLst.size() > 0){
			for(String custNo:custNoLst){
				AppCustTeam act = new AppCustTeam();
				act.setCustTeam(custTeam);
				act.setCustNo(custNo);
				//先删除原来的关联关系
				appCustTeamMapper.deleteGrpCustRel(BeanUtils.bean2map(act));
				//保存新的关联关系
				act.setId(RandomUtil.getUUID());
				appCustTeamMapper.insert(act);
			}
		}
	}
	
	/**
	 * 同时保存或更新 客户组和其下的客户
	 * @param appCustTeamInfo
	 * @param custNoLst
	 */
	@Transactional
	public void saveGrpCust(AppCustTeamInfo appCustTeamInfo,List<String> custNoLst){
		save(appCustTeamInfo);
		saveGrpCustRel(appCustTeamInfo.getCustTeam(), custNoLst);
	}
	
}