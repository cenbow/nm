package com.hs.loan.finance.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.finance.entity.AccLoanInst;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ACC_还款计划 mapper
 *
 * @author autocreate
 * @create 2016-02-03
 */
@MyBatisRepository
public interface AccLoanInstMapper extends BaseMapper<AccLoanInst> {

    /**
     *@describe 新销售系统(查询还款计划)
     *@author txia
     *datetime 2016/8/25 10:09
     *params{loanNo:贷款编号,certNo:身份证号,custName:客户名称,phoneNo:电话号码}
     *return List<Map>
     */
    public List<Map> billQueryForList(Map map);

    public int updateSetlAmtByLoanNo(HashMap<String, Object> map);

    /**
     * 更新预处理表中的当前还款金额
     *
     * @param map
     *            amt(金额) loanNo(贷款编号) repayNum(当前期数)
     * @return Integer
     */
    public Integer updateCurAmt(HashMap<String, Object> map);

    /**
     * 更新状态为锁表
     *
     * @param map
     *            loanNo(贷款编号) repayNum(还款期数)
     * @return
     */
    public Integer updateInstStat(Map<String, Object> map);

    public HashMap<String, Object> selectSetlAmtByLoanNo(String loanNo);

    /**
     * 通过贷款编号查询最近的一期
     *
     * @param loanNo
     *            贷款编号
     * @return Integer
     */
    public HashMap<String, Object> getMinRepayNoByLoanNo(String loanNo);

    /**
     * 通过贷款编号查询是否存在锁表
     *
     * @param loanNo
     *            贷款编号
     * @return Integer
     */
    public Integer getCountByLoanNoInstStat(String loanNo);

    public List<AccLoanInst> queryInstForList(Map<String, Object> caseParam);

    public Integer updateAccLoanInstSetlAmtAndUnLock(Map<String, Object> accAmtMap);
}