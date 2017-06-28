package com.hs.loan.cust.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.hs.loan.cust.mapper.AppCustCreditInfoMapper;
import com.hs.system.cardbin.PubCardBinService;
import com.hs.system.entity.PubCardBin;
import com.hs.loan.cust.entity.AppCustCreditInfo;
import com.hs.utils.BeanUtils;
import com.hs.utils.StringUtils;
import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;

/**
 * APP_客户信用卡信息 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustCreditInfoService{
	@Autowired
	private AppCustCreditInfoMapper appCustCreditInfoMapper;
	//客户基本信息service
	@Autowired
	private AppCustInfoService appCustInfoService;
	
	@Autowired
	private PubCardBinService pubCardBinService;
	
	/**
	 * 新增 APP_客户信用卡信息
	 * @param appCustCreditInfo 新增对象
	 */
	@Transactional
	public void insert(AppCustCreditInfo appCustCreditInfo){
		appCustCreditInfoMapper.insert(appCustCreditInfo);
	}

	/**
	 * 通过主键修改 APP_客户信用卡信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustCreditInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户信用卡信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustCreditInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户信用卡信息 对象
	 * @param primaryKey 主键
	 * @return APP_客户信用卡信息对象
	 */
	public AppCustCreditInfo getByPrimaryKey(String primaryKey){
		return appCustCreditInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户信用卡信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustCreditInfo> queryForList(Map<String, Object> param){
		return appCustCreditInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户信用卡信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustCreditInfo> queryForPage(Page<AppCustCreditInfo> page){
		appCustCreditInfoMapper.queryForList(page.getPageParams());
		return (Page<AppCustCreditInfo>)page.getPageParams().get(Page.KEY);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 根据客户号后去 客户信用卡信息 list
	 * @param custNo
	 * @return
	 */
	public List<AppCustCreditInfo> getListByNo(String custNo){
		if(StringUtils.isEmpty(custNo)){
			throw new AppException("custNo 不可为空");
		}
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		return queryForList(param);
	}
	
	/**
	 * 保存或者更新 客户信用卡信息，
	 * 必须的参数，custNo
	 * 
	 * @param appCustCreditInfo
	 */
	@Transactional
	public void save(String custNo,List<AppCustCreditInfo> creditLst){
		 
		/*for (AppCustCreditInfo appCustCreditInfo : creditLst) {
			
			PubCardBin carBin = pubCardBinService.getByPrimaryKey(appCustCreditInfo.getCardNum().substring(0, 6));
			if(carBin == null){
				throw new ServiceException("不支持该银行卡");
			}
		}*/
		appCustInfoService.saveCustExtra(custNo, creditLst, appCustCreditInfoMapper);
	}
	
	/**
	 * 获取 客户 在有效时间段里的 信用卡信息 列表
	 * 
	 * @param availableDate 有效时间
	 * @return
	 */
	public List<AppCustCreditInfo> getCustCreditLstByDate(String custNo,Date availableDate){
		if(StringUtils.isEmpty(custNo) || availableDate == null){
			throw new AppException("custNo或时间不可为空");
		}
		Map<String,Object> param = new HashMap<>();
		param.put("availableDate", availableDate);
		param.put("custNo", custNo);
		//return appCustCreditInfoMapper.getCustCreditLstByDate(param);
		return appCustCreditInfoMapper.getAvailableExtraInfoLst(param);
	}
	
	
	/**
	 * 获取客户当前 有效的 信用卡信息 列表
	 * 
	 * @return
	 */
	public  List<AppCustCreditInfo> getCrtCustCreditInfoLst(String custNo){
		return getCustCreditLstByDate(custNo, new Date());
	}
	
	/**
	 * 通过id 获取 客户信用卡信息
	 */
	public AppCustCreditInfo getById(String id){
		return getByPrimaryKey(id);
	}
	
	/**
	 * 获取 客户信用卡信息 list
	 * @param param
	 * @return
	 */
	public List<AppCustCreditInfo> getList(Map<String,Object> param){
		return queryForList(param);
	}
	
	/**
	 * 删除 客户信用卡信息
	 * @param ids
	 */
	@Transactional
	public void delete(String custNo,String... ids){
		appCustInfoService.deleteCustExtra(custNo, ids, appCustCreditInfoMapper);
	}
	
	/**
	 * 获取编辑过的 信用卡信息
	 * 
	 * @param appCustCreditInfo
	 * @return
	 */
	public AppCustCreditInfo getEditedCreditInfo(AppCustCreditInfo appCustCreditInfo){
		String custNo = appCustCreditInfo.getCustNo();
		if(StringUtils.isEmpty(custNo)){
			throw new AppException("custNo不可为空");
		}
		if(StringUtils.isEmpty(appCustCreditInfo.getId())){
			appCustCreditInfo.setBeginDate(appCustInfoService.getByNo(custNo).getLastApplyDate());
			appCustCreditInfo.setEndDate(CommonConstant.MAX_DATE);
		}
		Map<String,Object> param = BeanUtils.bean2map(appCustCreditInfo);
		param.put("beginDate",appCustCreditInfo.getBeginDate());
		param.put("endDate", appCustCreditInfo.getEndDate());
		param.put("availableDate", new Date());
		
		List<AppCustCreditInfo> lst = appCustCreditInfoMapper.getAvailableExtraInfoLst(param);
		return lst!=null&&lst.size()>0?lst.get(0):null;
	}

}