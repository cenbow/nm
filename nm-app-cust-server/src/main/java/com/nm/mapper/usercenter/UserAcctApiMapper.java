package com.nm.mapper.usercenter;

import com.hs.loan.finance.dto.SingleRepayDto;
import com.nm.cmd.RepayCmd;
import com.nm.mybatis.annotation.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wangqin
 *
 * @date 2017年5月10日 下午9:50:55
 */
@MyBatisRepository
public interface UserAcctApiMapper {
    /*查询客户贷款*/
	 List<Map<String,Object>> queryAcctByCustNo(List<String> custNos); // ?这儿不是该数组么 改啊
    /*查询客户贷款*/
    Map<String,Object> queryAcctByLoanNo(@Param("loanNo")String loanNo);
    /*查询单个贷款未还款信息*/
    List<Map<String,Object>> queryNoInst(@Param("loanNo")String loanNo);
    /*查询单个贷款已还款信息*/
    List<Map<String,Object>> queryYesInst(@Param("loanNo")String loanNo);
    /*查询贷款详情*/
    Map<String,Object> queryLoanDetails(@Param("loanNo")String loanNo);

    Map<String,Object> queryLoanDetails2(@Param("loanNo")String loanNo);

    Map<String,Object> queryLoanNumDetails(@Param("loanNo")String loanNo,@Param("num")String num);
    /*查询我的订单*/
    List<Map<String,String>> queryMyLoan(List<String> custNos);
    /*查询扣款信息*/
    RepayCmd queryRepayByLoanNoAndCurInstNum(Map<String, Object> map);

    SingleRepayDto querySingleRepay(Map<String, Object> params);

    String queryGoodsType(@Param("loanNo")String loanNo);

    List<String> queryLoanList(@Param("custNo")String custNo);
    /*查询是否有逾期账单*/
    int selectOverdueInst(@Param("loanNo")String loanNo);

    Map<String,Object> queryFirstAmt(@Param("loanNo") String loanNo);

    Map<String,Object> queryIsPay(@Param("loanNo")String loanNo,@Param("num")String num);

    void addPayDetail(Map<String, Object> params);

    Map<String,Object> queryOptSn(@Param("loanNo")String loanNo);

    Map<String,Object> queryIsOptSn(@Param("loanNo")String loanNo,@Param("optNum")String optNum);

    Map<String,String> queryBankInfo(@Param("loanNo")String loanNo);

    void addEvaluateInfo(Map<String, Object> params);

    Map<String,Object> getEvaluateInfo(@Param("loanNo") String loanNo);

    List<Map<String,Object>>  queryLoanAttInfo(Map<String, Object> map);
    
}
