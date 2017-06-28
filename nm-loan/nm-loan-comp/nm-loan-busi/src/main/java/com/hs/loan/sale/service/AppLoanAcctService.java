package com.hs.loan.sale.service;

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
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.sale.bo.HandFundMatchBo;
import com.hs.loan.sale.bo.LoanListHistoryOutBo;
import com.hs.loan.sale.bo.LoanListOutBo;
import com.hs.loan.sale.entity.AppLoanAcct;
import com.hs.loan.sale.entity.SysPrivInfo;
import com.hs.loan.sale.mapper.AppLoanAcctMapper;
import com.hs.utils.StringUtils;

/**
 * APP_分期基本信息 业务处理
 * @author autocreate
 * @create 2015-10-26
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanAcctService{
	@Autowired
	private AppLoanAcctMapper appLoanAcctMapper;
	public String existsAccDownPaymentInfo(String loanNo){
		return appLoanAcctMapper.existsAccDownPaymentInfo(loanNo);
	}
	public Integer insertAccDownPaymentInfo(Map map){return appLoanAcctMapper.insertAccDownPaymentInfo(map);};
	public List<AppLoanAcct> queryForList(Map map){
		return appLoanAcctMapper.queryForList(map);
	}
	public Map<String,Object> getContractCustPhone(java.util.Map map){
		return appLoanAcctMapper.getContractCustPhone(map);
	}
	public List<HashMap<String,Object>> getCustInfo(java.util.Map map){
		return appLoanAcctMapper.getCustInfo(map);
	}
	public Map getFeeAmtByProd(java.util.Map map){
		return appLoanAcctMapper.getFeeAmtByProd(map);
	}
	public List<HashMap<String,Object>> getAddressList(java.util.Map map){
		return appLoanAcctMapper.getAddressList(map);
	}
	public HashMap<String,Object> getAcct(java.util.Map map){
		return appLoanAcctMapper.getAcct(map);
	}
	public HashMap<String,Object> getLoanAcctByLoanNo(java.util.Map map){
		return appLoanAcctMapper.getLoanAcctByLoanNo(map);
	}
	/**
	 *@describe 根据贷款编号查询附件信息(附件的类型)
	 *@author txia
	 *@Date 2016/8/3 14:26
	 *@param {loanNo:贷款编号}
	 *@return List<Map>
	 */
	public List<Map> getAttachmentType(java.util.Map map){
		return appLoanAcctMapper.getAttachmentType(map);
	}
	/**
	 * 插入匹配资金日志表
	 * @param map
	 * @return int
	 */
	@Transactional
	public int insertFundMatchLog(HashMap<String,Object> map){
		return  appLoanAcctMapper.insertFundMatchLog(map);
	}
	/**
	 * 更新渠道号
	 * @param HandFundMatchBo
	 * @return int
	 */
	@Transactional
	public int updateChanNoByLoanNo(HandFundMatchBo b){
		return appLoanAcctMapper.updateChanNoByLoanNo(b);
	}
	@Transactional
	public int insertFundMatch(HandFundMatchBo b){
		return appLoanAcctMapper.insertFundMatch(b);
	}
	/**
	 * HashMap
	 * @param loanNo
	 * @return HashMap
	 */
	public HashMap<String,Object> selectCustByNo(String loanNo){
		return appLoanAcctMapper.selectCustByNo(loanNo);
	}
	/**
	 * 根据贷款编号变更匹配结果为匹配变更
	 * @param loan
	 * @return int
	 */
	@Transactional
	public int updateMatchResultByLoan(String loan){
		return appLoanAcctMapper.updateMatchResultByLoan(loan);
	}
	/**
	 * 查询该贷款编号是否已经资方匹配过
	 * @param loanNo
	 * @return int
	 */
	public int selectCountByLoanNo(String loanNo){
		return appLoanAcctMapper.selectCountByLoanNo(loanNo);
	}
	
	/**
	 * 新增 APP_分期基本信息
	 * @param appLoanAcct 新增对象
	 */
	@Transactional
	public void insert(AppLoanAcct appLoanAcct){
		appLoanAcctMapper.insert(appLoanAcct);
	}

	/**
	 * 通过分期编号修改 APP_分期基本信息
	 * @param map 修改参数Map
	 */
	@Transactional
	public int updateByLoanNoSelective(Map<String, Object> map){
		return appLoanAcctMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过分期编号删除 APP_分期基本信息
	 * @param loanNo 分期编号
	 */
	@Transactional
	public void deleteByLoanNo(String loanNo){
		appLoanAcctMapper.deleteByPrimaryKey(loanNo);
	}

	/**
	 * 批量更新
	 */
	@Transactional
	public int batchModifyByLoanNoLst(Map<String, Object> map)
	{
		return appLoanAcctMapper.batchModifyByLoanNoLst(map);
	}

	/**
	 * 通过分期编号取得 APP_分期基本信息 对象
	 * @param loanNo 分期编号
	 * @return APP_分期基本信息对象
	 */
	public AppLoanAcct getByLoanNo(String loanNo){
		return appLoanAcctMapper.getByPrimaryKey(loanNo);
	}

	/**
	 * 查询客户分期列表
	 * @param custNo 客户号
	 * @param userProfile
	 * @return List<LoanListOutBo>
	 */
	public List<LoanListOutBo> queryLoanByCustNo(String custNo,UserProfile userProfile){
		Map<String, Object> param = new HashMap<>();
		param.put("custNo", custNo);
		return appLoanAcctMapper.queryLoanList(param);
	}

	/**
	 * 根据条件查询客户分期列表
	 * @param custNo 客户号
	 * @param userProfile
	 * @return List<LoanListOutBo>
	 */
	public List<LoanListOutBo> queryLoanByCondition(Map<String,Object> param)
	{
		return appLoanAcctMapper.queryLoanList(param);
	}

	public int queryLoanList2Count(){
		return appLoanAcctMapper.queryLoanList2Count();
	}
	public List<LoanListOutBo> queryLoanList2(HashMap<String, Object> param){
		int pageNo=(null==param.get("pageNo")||""==param.get("pageNo").toString().trim())?1:Integer.parseInt(param.get("pageNo").toString().trim());
		int pageSize=(null==param.get("pageSize")||""==param.get("pageSize").toString().trim())?10:Integer.parseInt(param.get("pageSize").toString().trim());
		HashMap<String,Object> paramMap=new HashMap<>();
		paramMap.put("pageNo",pageNo);
		paramMap.put("pageSize",pageSize);
		return appLoanAcctMapper.queryLoanList2(paramMap);
	}
	/**
	 * 随心还款历史
	 * @param map
	 * @return HashMap<String,Object>
	 */
	public HashMap<String,Object> flexiblePaymentHistory(java.util.Map map){
		return appLoanAcctMapper.flexiblePaymentHistory(map);
	}
	/**
	 * 随心还款
	 * @param page
	 * @param userProfile
	 * @return Page<HashMap<String,Object>>
	 */
	public Page<HashMap<String,Object>> flexiblePayment(Page<HashMap<String,Object>> page,UserProfile userProfile){
		Map<String, Object> pageParams = page.getPageParams();
		List<HashMap<String, Object>> list = appLoanAcctMapper.flexiblePayment(pageParams);
		return (Page<HashMap<String,Object>>)page.getPageParams().get(Page.KEY);
	}


	public List<Map> getPubProd(Map map){
		return appLoanAcctMapper.getPubProd(map);
	}

	/**
	 * 查询分期信息列表
	 * @param page
	 *      page.params 
	 * 		参数(custName,certNo,acctNo,salerName,areMangerName,orgNo,
	 * 			branchName,branchProv,branchCity,branchArea,
	 * 			applyDate,regDate,distrDate,stat,aprvDate[审批时间]))
	 * @param userProfile 用户信息（过滤权限）
	 * @return List
	 */
	public Page<LoanListOutBo> queryLoanList(Page<LoanListOutBo> page,UserProfile userProfile){
		try{
			Map<String, Object> pageParams = page.getPageParams();
			//销售人员编号  销售查看自己的，运营和审批查看全部的单子
			//pageParams.put("staffNo",userProfile.getStaffNo());
			
			Map map = this.authority(userProfile,page.getPageParams());
			
			Object fileNo =  page.getPageParams().get("fileNo");
			if (fileNo != null && StringUtils.isNotEmpty( fileNo.toString())){
			Integer ifileNo = Integer.parseInt((String) fileNo);
			int file = ifileNo.intValue();
			if(2015 == ifileNo){
				map.put("fileNo2015", fileNo);
				map.put("fileNo", null);
			} 
			} 
			
			String prodNoString=threeNull(map.get("PROD_NO"));
			if(null!=prodNoString){
				String[] split = prodNoString.split(",");
				map.put("prodNoArray",split);
			}
			List<LoanListOutBo> list = null;
			Object obj = page.getPageParams().get("approveFlag");
			if(obj != null && (obj instanceof String) && "approveFlag".equals((String)obj) )
			{
				list = appLoanAcctMapper.queryLoanListTwo(map);
			}
			else
			{
				list = appLoanAcctMapper.queryLoanList(map);
			}
			
			/*for(LoanListOutBo attLoan :list){
				attLoan.setContractUrl(OssUtil.getPresignedUrl(attLoan.getAttNo()));
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}
		return (Page<LoanListOutBo>)page.getPageParams().get(Page.KEY);
	}
	private String threeNull(Object obj) {
		return (null == obj || "".equalsIgnoreCase(obj.toString().trim())) ? null : obj.toString().trim();
	}
	public Map<String,Object> authority(UserProfile userProfile,Map<String,Object> map){
		Set<String> roles = userProfile.getRoleNoSet();	
		Set<SysPrivInfo> SysPrivInfolist = appointInfo(userProfile.getStaffNo());
		List<String> appointorgnoList = new ArrayList<String>();
		if(SysPrivInfolist.size()>0){
			for(SysPrivInfo sysprivinfo : SysPrivInfolist){
				List<String> orgnolist = belongToOrgNo(sysprivinfo.getAppointOrgNo());
				appointorgnoList.addAll(orgnolist);
			}

			//将set转为list
			List<SysPrivInfo> list=new ArrayList<>(SysPrivInfolist);
			//将销售经理排在第一位
			for (SysPrivInfo sysPrivInfo : list) {
				if(PubBusinessConstant.ROLE_R_SALE_MGR.equals(sysPrivInfo.getAppointRoleNo())){
					SysPrivInfo privInfo = sysPrivInfo;
					list.remove(sysPrivInfo);
					list.add(0,privInfo);
				}
			}

		    for(SysPrivInfo sysprivinfo : list){
		    	if(PubBusinessConstant.ROLE_R_SALE_MGR.equals(sysprivinfo.getAppointRoleNo())){
					map.put("authority","_SELF_AND_SUB");
					map.put("orgCodPath", userProfile.getOrgNo());
					map.put("appointorgCodPath", appointorgnoList);
					break;
				}else if(PubBusinessConstant.ROLE_R_SALE_MGR_REGION.equals(sysprivinfo.getAppointRoleNo())){
					map.put("authority","_SELF_AND_SUB");
					map.put("orgCodPath", userProfile.getOrgNo());
					map.put("appointorgCodPath", appointorgnoList);
					break;
				}else if(PubBusinessConstant.ROLE_R_SALE_MGR_AREA.equals(sysprivinfo.getAppointRoleNo())){
					map.put("authority","_SELF_AND_SUB");
					map.put("orgCodPath", userProfile.getOrgNo());
					map.put("appointorgCodPath", appointorgnoList);
					break;
				}else if(PubBusinessConstant.ROLE_R_SALE_STAFF.equals(sysprivinfo.getAppointRoleNo())){
					map.put("authority","_SELF");
					map.put("staffNo", userProfile.getStaffNo());
					break;
				}else{
					map.put("authority","_ALL");
				}
		    }
		    
		}else{

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
		}
		return map;
		
	}
	/**
	 * 根据人员ID查询指定角色信息
	 * @param staffNo
	 * @return
	 */
	public Set<SysPrivInfo> appointInfo(String staffNo){
		Set<SysPrivInfo> list = appLoanAcctMapper.appointInfo(staffNo);
		return list;
		
	}
	/**
	 * 根据机构ID查询本级及下级机构信息
	 * @return
	 */
	public List<String> belongToOrgNo(String orgNo){
       return 	appLoanAcctMapper.belongToOrgNo(orgNo);	
	}
	/**
	 * 根据客户身份证号码查历史申请纪录
	 * @param certNo
	 * @return
	 */
	public List<LoanListHistoryOutBo> queryLoanHistoryList(String certNo){
		return appLoanAcctMapper.queryLoanHistoryList(certNo);
	}
	
	/**
	 * 根据客户身份证号码、姓名，贷款编号查 已签约的贷款信息
	 * @param certNo
	 * @return
	 */
	public List<AppLoanAcct> queryLoan(Map<String,Object> param){
		return appLoanAcctMapper.queryLoan(param);
		
	}
	
	@Transactional
	public String  loanFundMatch(String loanNo){
		String retStat = "00";
		Map<String,Object> map = new HashMap<>();
		map.put("I_LOAN_NO", loanNo);
		try{
			appLoanAcctMapper.loanFundMatch(map);
		}catch(Exception e){
			e.printStackTrace();
			return "01";
		}
		String ret = (String) map.get("O_STATUS");
		if(!"00".equals(ret)){
			return "01";
		}
		return retStat;
	}
	
	public static void main(String[] args) {
		Object fileNo = "401";
		Integer ifileNo = Integer.parseInt((String) fileNo);
		int file = ifileNo.intValue();
		  System.out.println(file%4); 
		
	}
	
	public  Map<String, Object> findCustSettleAmt(String loanNo, String tranDate) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("I_LOAN_NO", loanNo);
		map.put("I_DATE", tranDate);
		return appLoanAcctMapper.findCustSettleAmt(map);
	}
	public List<HashMap<String, Object>> getCallList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return appLoanAcctMapper.getCallList(param);
	}
	
}