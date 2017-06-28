package com.hs.loan.contract.api;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/7/5.
 */
public interface AppContractSignApi {

    /**
     *@describe 电子合同重置
     *@author txia
     *datetime 2016/9/5 10:47
     *params {loanNo:贷款编号}
     *return int 大于0重置成功
     */
    public int resetSsq(String loanNo);

    /**
     *@describe 合同详细信息
     *@author txia
     *datetime 2016/8/30 16:40
     *params {loanNo:贷款编号}
     *return java.util.List<java.util.Map>
     */
    public List<Map> getContractInfoDetail(String loanNo);

    /**
     * @describe 合同生成上传尚尚签
     * @author txia
     * datetime 2016/8/12 10:00
     * params {loanNo:贷款编号, userProfile:用户信息,custMobile:客户电话,saleMobile:销售电话,branchMobile:商户电话}
     * return java.util.Map{downUrl:合同下载url,ssqCustSignUrl:尚尚签客户签名url,ssqSaleSignUrl:尚尚签销售签名url,ssqBranchSignUrl:尚尚签商户签名url,getContractViewURL:查看合同视图}
     */
    public java.util.Map buidContantSsq(java.util.Map paramMap) throws Exception;

    /**
     * 查询尚尚签是否已经签名
     * @param loanNo 贷款编号
     * @param signType 签名类型(cust sale branch)
     * @param userProfile 用户信息
     * @return boolean(true已经签名 false未签名)
     * @throws ServiceException
     * @throws AppException
     */
    public boolean isSign(String loanNo,String signType,UserProfile userProfile)throws ServiceException,AppException;


    public String getContractViewURL(String loanNo)throws ServiceException;
    public java.util.Map buidContant(String loanNo, UserProfile userProfile, String terminal) throws Exception;
}
