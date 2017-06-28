package com.hs.loan.finance.service;

import com.hs.base.entity.Page;
import com.hs.loan.finance.entity.AccLoanInst;
import com.hs.loan.finance.mapper.AccLoanInstMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ACC_还款计划 业务处理
 *
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly = true)
public class AccLoanInstService {
    @Autowired
    private AccLoanInstMapper accLoanInstMapper;

    /**
     *@describe 新销售系统(查询还款计划)
     *@author txia
     *datetime 2016/8/25 10:09
     *params{loanNo:贷款编号,certNo:身份证号,custName:客户名称,phoneNo:电话号码}
     *return List<Map>
     */
    public List<Map> billQueryForList(Map map){
        return accLoanInstMapper.billQueryForList(map);
    };
    @Transactional
    public int updateSetlAmtByLoanNo(HashMap<String, Object> map) {
        return accLoanInstMapper.updateSetlAmtByLoanNo(map);
    }

    /**
     * 更新预处理表中的当前还款金额
     *
     * @param map
     *            amt(金额) loanNo(贷款编号) repayNum(当前期数)
     * @return Integer
     */
    @Transactional
    public Integer updateCurAmt(HashMap<String, Object> map) {
        return accLoanInstMapper.updateCurAmt(map);
    }

    /**
     * 更新状态为锁表
     *
     * @param map
     *            loanNo(贷款编号) repayNum(还款期数)
     * @return
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public Integer updateInstStat(Map<String, Object> map) {
        return accLoanInstMapper.updateInstStat(map);
    }

    /**
     * 通过贷款编号查询是否存在锁表
     *
     * @param loanNo
     *            贷款编号
     * @return Integer
     */
    public Integer getCountByLoanNoInstStat(String loanNo) {
        return accLoanInstMapper.getCountByLoanNoInstStat(loanNo);
    }

    public HashMap<String, Object> selectSetlAmtByLoanNo(String loanNo) {
        return accLoanInstMapper.selectSetlAmtByLoanNo(loanNo);
    }

    /**
     * 通过贷款编号查询最近的一期
     *
     * @param loanNo
     * @return Integer
     */
    public HashMap<String, Object> getMinRepayNoByLoanNo(String loanNo) {
        return accLoanInstMapper.getMinRepayNoByLoanNo(loanNo);
    }

    /**
     * 新增 ACC_还款计划
     *
     * @param accLoanInst
     *            新增对象
     */
    @Transactional
    public void insert(AccLoanInst accLoanInst) {
        accLoanInstMapper.insert(accLoanInst);
    }

    /**
     * 通过主键修改 ACC_还款计划
     *
     * @param map
     *            修改参数Map
     */
    @Transactional
    public void updateByPrimaryKeySelective(Map<String, Object> map) {
        accLoanInstMapper.updateByPrimaryKeySelective(map);
    }

    /**
     * 通过主键删除 ACC_还款计划
     *
     * @param primaryKey
     *            主键
     */
    @Transactional
    public void deleteByPrimaryKey(String primaryKey) {
        accLoanInstMapper.deleteByPrimaryKey(primaryKey);
    }

    /**
     * 通过主键取得 ACC_还款计划 对象
     *
     * @param primaryKey
     *            主键
     * @return ACC_还款计划对象
     */
    public AccLoanInst getByPrimaryKey(String primaryKey) {
        return accLoanInstMapper.getByPrimaryKey(primaryKey);
    }

    /**
     * 查询 ACC_还款计划 列表
     *
     * @param param
     *            查询参数map
     * @return List<T>列表
     */
    public List<AccLoanInst> queryForList(Map<String, Object> param) {
        return accLoanInstMapper.queryForList(param);
    }

    /**
     * 查询 ACC_还款计划 分页列表
     *
     * @param page
     *            查询参数page
     * @return List<T>列表
     */
    public Page<AccLoanInst> queryForPage(Page<AccLoanInst> page) {
        accLoanInstMapper.queryForList(page.getPageParams());
        return (Page<AccLoanInst>) page.getPageParams().get(Page.KEY);
    }

    public List<AccLoanInst> queryInstForList(Map<String, Object> caseParam) {
        // TODO Auto-generated method stub
        return accLoanInstMapper.queryInstForList(caseParam);
    }
    @Transactional
    public Integer updateAccLoanInstSetlAmtAndUnLock(Map<String, Object> accAmtMap) {
        return accLoanInstMapper.updateAccLoanInstSetlAmtAndUnLock(accAmtMap);
    }
}