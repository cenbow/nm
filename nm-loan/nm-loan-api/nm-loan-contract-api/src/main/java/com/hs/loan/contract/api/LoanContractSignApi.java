package com.hs.loan.contract.api;



import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface LoanContractSignApi {

	/**
	 * 下载老的合同
	 * @param loanNo 贷款编号
	 * @param userProfile 用户信息
	 * @return filePath 文件路径
	 * @throws Exception
	 */
	public  byte[] oldContantDown(String loanNo, UserProfile userProfile)throws Exception;

	/**
	 *@describe 电子合同重置
	 *@author txia
	 *datetime 2016/9/5 10:47
	 *params {loanNo:贷款编号}
	 *return int 大于0重置成功
	 */
	public int resetSsq(String loanNo);

	/**
	 *@describe 合同清单列表
	 *@author txia
	 *datetime 2016/8/30 16:35
	 *params [page]{custName:用户姓名,certNo:证件号码,loanNo:贷款编号}
	 *return java.util.List<java.util.Map>{custName:用户姓名,certNo:证件号码,contractNo:合同编号,loanNo:贷款编号}
	 */
	public Page<Map> getContractInfoList(UserProfile userProfile,Page<Map> page);

	/**
	 *@describe 合同详细信息
	 *@author txia
	 *datetime 2016/8/30 16:40
	 *params {loanNo:贷款编号}
	 *return java.util.List<java.util.Map>
	 */
	public List<Map> getContractInfoDetail(String loanNo);

	/**
	 * 查询尚尚签是否已经签名
	 * @param loanNo 贷款编号
	 * @param signType 签名类型(cust sale branch)
	 * @param userProfile 用户信息
	 * @return boolean(true已经签名 false未签名)
	 * @throws ServiceException
	 * @throws AppException
	 */
	public boolean isSign(String loanNo,String signType,UserProfile userProfile,String mobile)throws ServiceException,AppException;


    /**
     *@describe 查询合同是否上传至尚尚签
     *@author txia
     *datetime 2016/8/15 11:51
     *params {userProfile:用户信息,loanNo:贷款编号}
     *return java.util.Map{isUpload:是否上传(boolean),ossUrl:阿里云oss文件下载地址,getContractViewURL:尚尚签合同查看url,ssqCustSignUrl:客户签约url,ssqSaleSignUrl:销售签约url,ssqBranchSignUrl:商户签约url}
     */
    public java.util.Map fileIsUploadSsq(java.util.Map paramMap) throws Exception;


	/**
	 * @describe 合同生成上传尚尚签
	 * @author txia
	 * datetime 2016/8/12 10:00
	 * params {loanNo:贷款编号, userProfile:用户信息,custMobile:客户电话,saleMobile:销售电话,branchMobile:商户电话}
	 * return java.util.Map{downUrl:合同下载url,ssqCustSignUrl:尚尚签客户签名url,ssqSaleSignUrl:尚尚签销售签名url,ssqBranchSignUrl:尚尚签商户签名url,getContractViewURL:查看合同视图,allFinsh:合同是否签约完毕(true签约完成 false未签约完毕)，isSavePhone：电话号码是否保存,custMobile:客户电话,saleMobile:销售电话,branchMobile:商户电话}
	 */
	public java.util.Map buidContantSsq(java.util.Map paramMap) throws Exception;


	/**
	 * @describe 合同生成上传阿里oss
	 * @author txia
	 * datetime 2016/8/12 10:00
	 * params [loanNo:贷款编号, userProfile:用户信息]
	 * return java.util.Map{downUrl:oss文件下载url}
	 */
	public java.util.Map buidContantOss(String loanNo, UserProfile userProfile) throws Exception;

	/**
	 * @describe 合同生成同步上传阿里oss
	 * @author txia
	 * datetime 2016/8/12 10:00
	 * params [loanNo:贷款编号, userProfile:用户信息]
	 * return java.util.Map{downUrl:oss文件下载url}
	 */
	public java.util.Map buidContantOssAsync(String loanNo, UserProfile userProfile)throws Exception;


	/**
	 * 获取阿里云上传附件地址
	 *
	 * @param loanNo 贷款编号
	 * @return String
	 */
	public String getUrlOss(String loanNo) throws ServiceException, AppException;


	/**
	 *@describe 查看尚尚签合同视图
	 *@author txia
	 *datetime 2016/8/12 10:58
	 *params {loanNo:贷款编号}
	 *return java.lang.String
	 */
	public String getContractViewURL(String loanNo)throws ServiceException;

	public int dealSsqReturn(String signType,String code,String signID);
	public String getSsqSignUrl(String loanNo,String signType,String terminal,UserProfile userProfile) throws Exception;


	/**
	 * 合同
	 * @param loanNo
	 * @param userProfile
	 * @throws IOException
	 */
	public java.util.Map buidContant(String loanNo,UserProfile userProfile,String terminal) throws  ServiceException,AppException,Exception;


	/**
	 * 更新客户签约状态
	 * @param param
	 * @throws ServiceException
	 * @throws AppException
	 */
	public int updateCustSignStatus(Map<String,Object> param) throws  ServiceException,AppException;
	
	/**
	 * 获取合同上上签URL
	 * @param loanNo
	 * @throws ServiceException
	 * @throws AppException
	 */
	public String  getsignUrlByLoanNo(String loanNo) throws  ServiceException,AppException;
	
	
}
