package com.hs.loan.operation.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.operation.entity.PubLoanContractFile;

import java.util.HashMap;
import java.util.List;

/**
 * PUB_合同模版 mapper
 * @author autocreate
 * @create 2015-10-15
 */
@MyBatisRepository
public interface  PubLoanContractFileMapper extends BaseMapper<PubLoanContractFile>{
    public List<String> getSendLoanApply(java.util.Map map);
    public List<String> getSendLoanResult(java.util.Map map);
    public List<String> getSendSuccessRepayment(java.util.Map map);
	public int insertAppEntr(java.util.Map map);
    public List<HashMap<String,Object>> getAppEntr(java.util.Map map);
}