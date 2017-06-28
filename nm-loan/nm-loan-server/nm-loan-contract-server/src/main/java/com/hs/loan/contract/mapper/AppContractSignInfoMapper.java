package com.hs.loan.contract.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.contract.entity.AppContractSignInfo;

import java.util.List;
import java.util.Map;

/**
 *  mapper
 * @author autocreate
 * @create 2015-12-25
 */
@MyBatisRepository
public interface AppContractSignInfoMapper extends BaseMapper<AppContractSignInfo>{
	public List<Map> getAttByLoanNo(Map<String,Object> map);
	public int updateStateLoanAcct(Map<String,Object> param);
	public List<Map> getContractInfoList(Map<String,Object> param);
    public List<Map> fileIsUploadSsq(Map<String,Object> param);
	public Map getContractByLoanNo(Map<String,Object> param);
	public Integer updateAcctStat(Map<String,Object> param);
	public List<Map> selectcontractNo(Map<String,Object> param);
	public List<Map> getContract(Map<String,Object> param);
	public int deleteContractByLoanNo(Map<String,Object> param);
	public int updateSignStatus(Map<String,Object> param);
	public java.util.Map getXYPage(Map<String,Object> param);
	public Integer insertBatch(Map<String,Object> param);
	public String getProdType(Map<String,Object> param);
	public Map getBranchMobl(Map<String,Object> param);
	public Map getStaffMobl(Map<String,Object> param);
	public Map getFeeRatByLoanNo(Map map);
	public String getContractNoByLoanNo(Map<String,Object> param);
	public void updateByContractNo(Map<String,Object> param);
}