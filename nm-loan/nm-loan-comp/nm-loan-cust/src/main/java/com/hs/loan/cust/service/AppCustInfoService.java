package com.hs.loan.cust.service;

import com.alibaba.fastjson.JSON;
import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.mapper.BaseMapper;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.cust.bo.CustInfoBo;
import com.hs.loan.cust.entity.AppCustInfo;
import com.hs.loan.cust.itface.ICustExtraInfo;
import com.hs.loan.cust.itface.ICustExtraInfoMapper;
import com.hs.loan.cust.mapper.AppCustInfoMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * APP_客户信息 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppCustInfoService{
	@Autowired
	private AppCustInfoMapper appCustInfoMapper;
	
	/**
	 * 新增 APP_客户信息
	 * @param appCustInfo 新增对象
	 */
	@Transactional
	public void insert(AppCustInfo appCustInfo){
		appCustInfoMapper.insert(appCustInfo);
	}

	/**
	 * 通过主键修改 APP_客户信息
	 * @param map 修改参数Map
	 */
	@Transactional
	private void updateByPrimaryKeySelective(Map<String, Object> map){
		appCustInfoMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_客户信息
	 * @param primaryKey 主键
	 */
	@Transactional
	private void deleteByPrimaryKey(String primaryKey){
		appCustInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 通过主键取得 APP_客户信息 对象
	 * @param primaryKey 主键
	 * @return APP_客户信息对象
	 */
	private AppCustInfo getByPrimaryKey(String primaryKey){
		return appCustInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_客户信息 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	private List<AppCustInfo> queryForList(Map<String, Object> param){
		return appCustInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_客户信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	private Page<AppCustInfo> queryForPage(Page<AppCustInfo> page){
		appCustInfoMapper.queryForList(page.getPageParams());
		return (Page<AppCustInfo>)page.getPageParams().get(Page.KEY);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 分页查询 客户基本信息
	 * @param page
	 * @return
	 */
	public Page<CustInfoBo> queryCustInfo(Page<CustInfoBo> page){
		appCustInfoMapper.queryCustInfo(page.getPageParams());
		return (Page<CustInfoBo>)page.getPageParams().get(page.KEY);
	}
	
	/**
	 * 通过客户号，获取客户基本信息（包含户籍信息）
	 * @param custNo
	 * @return
	 */
	public AppCustInfo getByNo(String custNo){
		return getByPrimaryKey(custNo);
	}
	
	/**
	 * 通过客户号 删除 客户基本信息
	 */
	@Transactional
	public void deleteByNo(String custNo){
		if(StringUtils.isEmpty(custNo)){
			throw new AppException("custNo 不可为空");
		}
		deleteByPrimaryKey(custNo);
	}
	
	/**
	 * 通过身份证号获取客户身份信息
	 * @param certNo
	 */
	public AppCustInfo getByCertNo(String certNo){
		Map<String,Object> param = new HashMap<>();
		param.put("certNo", certNo.trim());
		List<AppCustInfo> list =queryForList(param);
		if(list!=null && list.size()>0)
			return list.get(0);
		return null;
	}
	
	/**
	 * 通过客户名和身份证号验证客户
	 * 验证成功返回该客户基本信息，否则返回null
	 * 
	 * @param custName
	 * @param certNo
	 * @return
	 */
	public AppCustInfo validCust(String custName,String certNo){
		if(com.hs.utils.StringUtils.isEmpty(custName)){
			throw new AppException("客户名称不能为空");
		}
		if(StringUtils.isEmpty(certNo)){
			throw new AppException("证件号不能为空");
		}
		Map<String,Object> param = new HashMap<>();
		param.put("certNo", certNo.trim());
		param.put("custName", custName.trim());
		List<AppCustInfo> list =queryForList(param);
		if(list!=null && list.size()>0)
			return list.get(0);
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(String.valueOf(Math.round(Math.random()*1000000)));
	}
	
	/**
	 * 说明：当客户第二次来办单时，无论有无更新客户基本信息点击下一步都需要手动更新最后一次申请时间为当前时间；
	 * 当客户填完基本信息进入下一步后，再返回修改客户基本信息时，不需要更新最后一次申请时间
	 *
	 * 保存或者修改 客户基本信息
	 *
	 * 返回客户号
	 * @param appCustInfo
	 */
	@Transactional
	public String save(AppCustInfo appCustInfo) throws AppException{
		String custNo = appCustInfo.getCustNo();
		if(StringUtils.isEmpty(custNo)){//保存
			if(StringUtils.isEmpty(appCustInfo.getCertNo())){
				throw new AppException("身份证号不能为空");
			}

			//解决 新增用户  返回老的custNo
			/*List<AppCustInfo> lst = queryForList(BeanUtils.bean2map(appCustInfo));
			if(lst!=null && lst.size()>0){
				return lst.get(0).getCustNo();
			}*/
			Date now = new Date();
			custNo = appCustInfo.getCertNo()+"a"+String.valueOf(Math.round(Math.random()*1000000));
			appCustInfo.setCustNo(custNo);
			appCustInfo.setInstDate(now);
			appCustInfo.setLastApplyDate(now);
			appCustInfo.setUpdtDate(now);
			insert(appCustInfo);
		}else{//更新
			Map<String,Object> map =  BeanUtils.bean2mapExclude(appCustInfo,"instDate");
			map.put("updtDate", new Date());
			map.remove("instPerson");//更新不能改变登记人。
			map.remove("lastApplyDate");//更新不能更改最后申请时间
			updateByPrimaryKeySelective(map);
		}
		return custNo;
	}
	
	/**
	 * 更新客户最后一次申请时间为当前时间
	 * @param custNo
	 */
	@Transactional
	public void updateLastApplyDate(String custNo){
		Map<String,Object> param = new HashMap<>();
		param.put("lastApplyDate", new Date());
		param.put("custNo", custNo);
		updateByPrimaryKeySelective(param);
	}
	
	/**
	 * 保存或者更新 客户 附加信息，如客户的居住，工作，联系等信息
	 */
	@Transactional
	public <T extends ICustExtraInfo> void saveCustExtra(String custNo , List<T> tLst,BaseMapper<T> mapper) {
		System.out.println(JSON.toJSONString(tLst));
		if(StringUtils.isEmpty(custNo)){
			throw new AppException("custNo 不能为空");
		}
		if(tLst==null || tLst.size() == 0){
			throw new AppException("需要保存的信息不可为空");
		}
		Date beginDate = getByNo(custNo).getLastApplyDate();
		if(beginDate==null){
			beginDate=new Date();
		}
		Date endDate = CommonConstant.MAX_DATE;
		for(T t : tLst){
			String id = t.getId();
			if(StringUtils.isEmpty(id)){
				if(mapper.getClass().getName().equals(ICustExtraInfoMapper.class.getName())){
					Map<String,Object> param = BeanUtils.bean2mapExclude(t, "beginDate,endDate");
					param.put("availableDate", new Date());
					param.remove("id");
					List<T> lst = ((ICustExtraInfoMapper)mapper).getAvailableExtraInfoLst(param);
					if(lst!=null && lst.size() > 0){
						continue;
					}
				}
				
				t.setId(RandomUtil.getUUID());
				t.setBeginDate(beginDate);
				t.setEndDate(endDate);
				mapper.insert(t);
				continue;
			}
			if(null!=id&&!id.isEmpty()){
				Map<String, Object> map = BeanUtils.bean2map(t);
				if(null!=t.getBeginDate()){
					map.put("beginDate", DateFormatUtils.format(t.getBeginDate(),"yyyy-MM-dd HH:mm:ss"));
				}
				if(null!=t.getEndDate()){
					map.put("endDate",DateFormatUtils.format(t.getEndDate(),"yyyy-MM-dd HH:mm:ss"));
				}
				mapper.updateByPrimaryKeySelective(map);
				continue;
			}
			/*List<T> aoLst = mapper.queryForList(BeanUtils.bean2mapExclude(t, "beginDate,endDate"));
			T ao = null;
			if(aoLst!=null && aoLst.size()==1){
				ao = aoLst.get(0);
			}
			if(ao != null){
				continue;
			}*/
			if(t.getBeginDate().getTime() == beginDate.getTime()){
				Map<String,Object> param = BeanUtils.bean2map(t);
				param.remove("beginDate");
				param.remove("endDate");
				mapper.updateByPrimaryKeySelective(param);
				continue;
			}
			Map<String,Object> param = BeanUtils.bean2map(t);
			param.remove("beginDate");
			param.put("endDate", beginDate);
			mapper.updateByPrimaryKeySelective(param);
			
			t.setId(RandomUtil.getUUID());
			t.setBeginDate(beginDate);
			t.setEndDate(endDate);
			mapper.insert(t);
		}
	}
	
	/**
	 * 删除客户的一些附加信息
	 * 
	 * @param custNo
	 * @param ids
	 */
	@Transactional
	public <T extends ICustExtraInfo> void deleteCustExtra(String custNo,String[] ids,BaseMapper<T> mapper){
		if(StringUtils.isEmpty(custNo)){
			throw new AppException("custNo 不能为空");
		}
		if(ids==null || ids.length == 0){
			throw new AppException("需要删除的信息的id为空");
		}
		Date beginDate = getByNo(custNo).getLastApplyDate();
		for(String id : ids){
			if(StringUtils.isEmpty(id)){
				continue;
			}
			T t = mapper.getByPrimaryKey(id);
			if(beginDate != null){
			if(t.getBeginDate().getTime()==beginDate.getTime()){
				mapper.deleteByPrimaryKey(id);
				continue;
			}
			}
			Map<String,Object> map = BeanUtils.bean2map(t);
			map.put("beginDate", t.getBeginDate());
			if(beginDate == null){
			map.put("endDate", new Date());
			}else{
			map.put("endDate", beginDate);
			}
			mapper.updateByPrimaryKeySelective(map);
		}
	}

	public List<Integer> queryCustScore(String custNo) {
		return appCustInfoMapper.queryCustScore(custNo);
	}
	
}