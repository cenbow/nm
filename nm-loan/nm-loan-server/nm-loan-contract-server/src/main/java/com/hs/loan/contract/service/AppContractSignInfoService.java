package com.hs.loan.contract.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.contract.entity.AppContractSignInfo;
import com.hs.loan.contract.mapper.AppContractSignInfoMapper;
//import com.nm.cache.busi.service.OperateCacheServiceApi;

/**
 *  业务处理
 * @author autocreate
 * @create 2015-12-25
 */
@Service
@Transactional(readOnly=true)
public class  AppContractSignInfoService{
	@Autowired
	private AppContractSignInfoMapper appContractSignInfoMapper;
	
//	@Autowired
//	private OperateCacheServiceApi operateCacheService;
	
	public List<Map> getAttByLoanNo(Map<String,Object> map){
		return appContractSignInfoMapper.getAttByLoanNo(map);
	}
	public int updateStateLoanAcct(Map<String,Object> param){
		return appContractSignInfoMapper.updateStateLoanAcct(param);
	}
	public Page<Map> getContractInfoList(UserProfile userProfile,Page<Map> page){
		if(userProfile == null){
			throw new ServiceException("登录信息为空,请重新登录");
		}
		Map<String,Object> map = this.authority(userProfile, page.getPageParams());
		appContractSignInfoMapper.getContractInfoList(map);
		return  (Page<Map>)page.getPageParams().get(Page.KEY);
	}
	
	
	public Map<String,Object> authority(UserProfile userProfile,Map<String,Object> map){
		Set<String> roles = userProfile.getRoleNoSet();	
		List<String> appointorgnoList = new ArrayList<String>();
            //将set转为list
            List<String> list=new ArrayList<>(roles);
            //将销售经理排在第一位
            for (String str : list) {
                if(PubBusinessConstant.ROLE_R_SALE_MGR.equals(str)){
                    String s = str;
                    list.remove(str);
                    list.add(0,s);
                }
            }

			for (String role : list) {
				if(PubBusinessConstant.ROLE_R_SALE_MGR.equals(role)){
					map.put("authority","_SELF_AND_SUB");
					map.put("orgCodPath", userProfile.getOrgNo());
					break;
				}else if(PubBusinessConstant.ROLE_R_SALE_MGR_REGION.equals(role)){
					map.put("authority","_SELF_AND_SUB");
					map.put("orgCodPath", userProfile.getOrgNo());
					break;
				}else if(PubBusinessConstant.ROLE_R_SALE_MGR_AREA.equals(role)){
					map.put("authority","_SELF_AND_SUB");
					map.put("orgCodPath", userProfile.getOrgNo());
					break;
				}else if(PubBusinessConstant.ROLE_R_SALE_STAFF.equals(role)){
					map.put("authority","_SELF");
					map.put("staffNo", userProfile.getStaffNo());
					break;
				}else{
					map.put("authority","_ALL");
					break;
				}
		}
		return map;
		
	}
	
    public List<Map> fileIsUploadSsq(Map<String,Object> param){
        return appContractSignInfoMapper.fileIsUploadSsq(param);
    }
	public Map getContractByLoanNo(Map<String,Object> param){
		return appContractSignInfoMapper.getContractByLoanNo(param);
	}
	@Transactional
	public Integer updateAcctStat(Map<String,Object> param){
		return appContractSignInfoMapper.updateAcctStat(param);
	}
	public List<Map> selectcontractNo(Map<String,Object> param){
		return appContractSignInfoMapper.selectcontractNo(param);
	}
	public List<Map> getContract(Map<String,Object> param){
		return appContractSignInfoMapper.getContract(param);
	}
	@Transactional
	public int deleteContractByLoanNo(Map<String,Object> param){
		return appContractSignInfoMapper.deleteContractByLoanNo(param);
	}
	@Transactional
	public int updateSignStatus(Map<String,Object> param){
		return appContractSignInfoMapper.updateSignStatus(param);
	}
	public java.util.Map getXYPage(Map<String,Object> param){
//		List<Map<String,Object>> lst = operateCacheService.queryCacheByCondition("PUB_LOAN_CONTRACT_FILE", param);
//		Map<String,Object> map = null;
//		if(!lst.isEmpty()){
//			map = lst.get(0);
//		}
//		return map;
		return appContractSignInfoMapper.getXYPage(param);
	}
	@Transactional
	public Integer insertBatch(Map<String,Object> param){
		return appContractSignInfoMapper.insertBatch(param);
	}
	public String getProdType(Map<String,Object> param){
		return  appContractSignInfoMapper.getProdType(param);
	}
	public Map getBranchMobl(Map<String,Object> param){
		return appContractSignInfoMapper.getBranchMobl(param);
	}
	public Map getStaffMobl(Map<String,Object> param){
//		List<Map<String, Object>> lst = operateCacheService.queryCacheByCondition("SYS_STAFF", param);
//		Map<String,Object> map = new HashMap<String,Object>();
//		if(!lst.isEmpty()){
//			map.put("MOBL_NO",lst.get(0).get("moblNo"));
//			map.put("STAFF_NAME", lst.get(0).get("staffName"));
//		}
//		return map;
		return appContractSignInfoMapper.getStaffMobl(param);
	}
	public Map getFeeRatByLoanNo(Map map){
		return appContractSignInfoMapper.getFeeRatByLoanNo(map);
	}
	public String getContractNoByLoanNo(Map<String,Object> param){
		return appContractSignInfoMapper.getContractNoByLoanNo(param);
	}
	/**
	 * 新增 
	 * @param appContractSignInfo 新增对象
	 */
	@Transactional
	public void insert(AppContractSignInfo appContractSignInfo){
		appContractSignInfoMapper.insert(appContractSignInfo);
	}

	/**
	 * 通过主键修改 
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appContractSignInfoMapper.updateByPrimaryKeySelective(map);
	}
	
	/**
	 * 通过合同号修改 
	 * @param param 修改参数Map
	 */
	@Transactional
	public void updateByContractNo(Map<String, Object> param){
		appContractSignInfoMapper.updateByContractNo(param);
	}

	/**
	 * 通过主键删除 
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appContractSignInfoMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得  对象
	 * @param primaryKey 主键
	 * @return 对象
	 */
	public AppContractSignInfo getByPrimaryKey(String primaryKey){
		return appContractSignInfoMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询  列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppContractSignInfo> queryForList(Map<String, Object> param){
		return appContractSignInfoMapper.queryForList(param);
	}
	
	/**
	 * 查询  分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppContractSignInfo> queryForPage(Page<AppContractSignInfo> page){
		appContractSignInfoMapper.queryForList(page.getPageParams());
		return (Page<AppContractSignInfo>)page.getPageParams().get(Page.KEY);
	}
}