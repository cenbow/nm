package com.hs.loan.cust.service;

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
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.cust.entity.AppCustBankAcct;
import com.hs.loan.cust.entity.AppCustInfo;
import com.hs.loan.cust.mapper.AppCustBankAcctMapper;
import com.hs.system.cardbin.PubCardBinService;
import com.hs.system.entity.PubCardBin;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * APP_客户银行账户信息 业务处理
 * @author autocreate
 * @create 2015-10-27
 */
@Service
@Transactional(readOnly=true)
public class  AppCustBankAcctService{
	@Autowired
	private AppCustBankAcctMapper appCustBankAcctMapper;
	@Autowired
	private PubCardBinService pubCardBinService;
	@Autowired
	private AppCustInfoService appCustInfoService;
	
	/**
	 * 新增 APP_客户银行账户信息
	 * @param appCustBankAcct 新增对象
	 */
	@Transactional
	public void insert(AppCustBankAcct appCustBankAcct){
		appCustBankAcctMapper.insert(appCustBankAcct);
	}

	/**
	 * 通过主键修改 APP_客户银行账户信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustBankAcctMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过custNo修改 APP_客户银行账户信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByCustNo(Map<String, Object> map){
		appCustBankAcctMapper.updateByCustNo(map);
	}
	
	
	/**
	 * 通过主键删除 APP_客户银行账户信息
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appCustBankAcctMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_客户银行账户信息 对象
	 * @param primaryKey 主键
	 * @return APP_客户银行账户信息对象
	 */
	public AppCustBankAcct getByPrimaryKey(String primaryKey){
		return appCustBankAcctMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户银行账户信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppCustBankAcct> queryForList(Map<String, Object> param){
		return appCustBankAcctMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户银行账户信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppCustBankAcct> queryForPage(Page<AppCustBankAcct> page){
		appCustBankAcctMapper.queryForList(page.getPageParams());
		return (Page<AppCustBankAcct>)page.getPageParams().get(Page.KEY);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 获取客户有效的银行账户信息 按时间倒序排序
	 * @param custNo
	 * @return
	 */
	public List<AppCustBankAcct> getListByNo(String custNo) {
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		param.put("stat", CommonConstant.STAT_ENABLE);
		return queryForList(param);
	}
	
	/**
	 * 获取客户无效的银行账户信息 按时间倒序排序
	 * @param custNo
	 * @return
	 */
	public List<AppCustBankAcct> getInvalidListByNo(String custNo) {
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		param.put("stat", CommonConstant.STAT_DISABLE);
		return queryForList(param);
	}
	
	/**
	 * 获取客户的银行账户信息，按时间倒序，包括有效的和无效的
	 * @param custNo
	 * @return
	 */
	public List<AppCustBankAcct> getListAll(String custNo){
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		return queryForList(param);
	}
	
	/**
	 * 通过主键id 获取 客户银行账户信息
	 * @param id
	 * @return
	 */
	public AppCustBankAcct getById(String id){
		return getByPrimaryKey(id);
	}
	
	/**
	 * 获取cardBin信息
	 * @param cardBin
	 * @return
	 */
	public PubCardBin getCardBinInfo(String cardBin){
		return pubCardBinService.getByPrimaryKey(cardBin);
	}
	
	/**
	 * 保存（insert）银行卡信息,银行卡不可修改
	 * 
	 * @param appCustBankAcct
	 */
	@Transactional
	public void save(AppCustBankAcct appCustBankAcct){
		if(StringUtils.isEmpty(appCustBankAcct.getCustNo())){
			throw new AppException("银行账户信息中custNo不可为空");
		}
		AppCustInfo cust =  appCustInfoService.getByNo(appCustBankAcct.getCustNo());
		if(cust == null){
			throw new ServiceException("该客户不存在");
		}
		
		if(!cust.getCustName().equals(appCustBankAcct.getAcctName())){
			throw new ServiceException("账户名不是该客户本人");
		}
		/*20160304取消zym*/
		/*PubCardBin carBin = getCardBinInfo(appCustBankAcct.getAcctNo().substring(0, 6));
		if(carBin == null){
			throw new ServiceException("不支持该银行卡");
		}*/

		//验证该客户是否已经添加该银行帐号
		int existsBankNoByCusResult = appCustBankAcctMapper.selectExistsBankNoByCus(appCustBankAcct);
		if(existsBankNoByCusResult>0){
			throw new ServiceException("该客户已经存在该银行帐号");
		}

		Map<String,Object> param = BeanUtils.bean2mapExclude(appCustBankAcct, "instPerson,instDate");
		param.put("stat", CommonConstant.STAT_ENABLE);
		List<AppCustBankAcct> lst =  queryForList(param);
		if(lst!=null && lst.size()>0){
			throw new ServiceException("该银行卡已经保存");
		}
		
		Date instDate =cust.getLastApplyDate();
		appCustBankAcct.setId(RandomUtil.getUUID());
		appCustBankAcct.setStat(CommonConstant.STAT_ENABLE);
		appCustBankAcct.setInstDate(instDate);
		insert(appCustBankAcct);
	}
	
	/**
	 * 保存（insert）银行卡信息,银行卡不可修改
	 * 
	 * @param appCustBankAcct
	 */
	@Transactional
	public void saveBankLoanNo(AppCustBankAcct appCustBankAcct){
		if(StringUtils.isEmpty(appCustBankAcct.getCustNo())){
			throw new AppException("银行账户信息中custNo不可为空");
		}
		AppCustInfo cust =  appCustInfoService.getByNo(appCustBankAcct.getCustNo());
		if(cust == null){
			throw new ServiceException("该客户不存在");
		}
		
		if(!cust.getCustName().equals(appCustBankAcct.getAcctName())){
			throw new ServiceException("账户名不是该客户本人");
		}
		Date instDate =cust.getLastApplyDate();
		appCustBankAcct.setId(RandomUtil.getUUID());
		appCustBankAcct.setStat(CommonConstant.STAT_ENABLE);
		appCustBankAcct.setInstDate(instDate);
		insert(appCustBankAcct);
	}
	
	/**
	 * 删除银行卡信息
	 * @param id
	 */
	@Transactional
	public void removeById(String id){
		if(StringUtils.isEmpty(id)){
			throw new AppException("银行账户id不可为空");
		}
		Map<String,Object> param = new HashMap<>();
		param.put("id", id);
		param.put("stat", CommonConstant.STAT_DISABLE);
		updateByPrimaryKeySelective(param);
	}
	
	/**
	 * 获取客户的银行卡信息
	 * 
	 * @param custNo 客户号
	 * @param acctNo 银行账户号
	 */
	public AppCustBankAcct getCustBankCard(String custNo,String acctNo){
		if(StringUtils.isEmpty(custNo)){
			throw new AppException("custNo不可为空");
		}
		if(StringUtils.isEmpty(acctNo)){
			throw new AppException("银行账户号不可为空");
		}
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		param.put("acctNo", acctNo);
		param.put("stat", CommonConstant.STAT_ENABLE);
		List<AppCustBankAcct> lst = queryForList(param);
		return lst!=null && lst.size()>0 ? lst.get(0):null;
	}

	/**
	 * 删除客户银行卡信息
	 * 
	 * @param custNo 客户号
	 */
	public void removeByCustNo(String custNo) {
		Map<String,Object> param = new HashMap<>();
		param.put("custNo", custNo);
		param.put("stat", CommonConstant.STAT_DISABLE);
		updateByCustNo(param);
	}
	
	/**
	 * 更换银行卡号
	 */
	/*private void changeCustBankAcct(){//////////////////////////////////////////////////beanutils为空的bug
		//将原银行卡置为无效
		//将原来的 银行卡和分期 关联关系的状态置为无效
		
		//将新的银行卡信息插入
		
		//绑定新的 银行卡和分期的 关联关系
		
	}*/
	
	/**
	 * 将客户的银行卡信息置为无效
	 */
	/*private void invalidCustBankAcct(){
		//更改该银行卡状态为无效
		
		//将原来的 银行卡和分期 关联关系状态置为无效
		
	}*/
	
	/*public static void main(String[] args) {
		String a = "123456789";
		System.out.println(a.substring(0,6));
		
	}*/
	
	
	
}