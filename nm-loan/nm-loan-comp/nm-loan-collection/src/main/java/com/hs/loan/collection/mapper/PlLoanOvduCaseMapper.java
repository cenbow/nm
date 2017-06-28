package com.hs.loan.collection.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.collection.entity.AccCustAndBankBo;
import com.hs.loan.collection.entity.AppLoanAcctBo;
import com.hs.loan.collection.entity.PlLoanOvduCase;

/**
 * PL_逾期案件 mapper
 * 
 * @author autocreate
 * @create 2015-12-02
 */
@MyBatisRepository
public interface PlLoanOvduCaseMapper extends BaseMapper<PlLoanOvduCase> {

	List queryForParam(Map<String, Object> pageParams);

	AccCustAndBankBo getCustAndBank(String loanNo);

	List queryCaseAllotListForParam(Map<String, Object> param);

	AppLoanAcctBo getAppLoanAcctBo(String loanNo);

	List queryLoanCustInfoForList(Map<String, Object> param);

	List queryLoanOvduCaseFlowList(Map<String, Object> pageParams);

}