package com.hs.loan.finance.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.finance.bo.SingleRepayBo;
import com.hs.loan.finance.entity.AccCapWith;

/**
 * mapper
 * 
 * @author autocreate
 * @create 2016-02-03
 */
@MyBatisRepository
public interface AccCapWithMapper extends BaseMapper<AccCapWith> {
	/**
	 * 根据贷款编号查询交易方
	 * 
	 * @param loanNo
	 * @return String
	 */
	public String selectTranObjByLoanNo(String loanNo);

	/**
	 * 根据贷款编号更新扣款状态
	 * 
	 * @param map
	 *            loanNo(贷款编号) repayNum(支付期数)
	 * @return Integer
	 */
	public Integer updateWithStat(Map<String, Object> map);

	/**
	 * 查询代扣信息 根据贷款编号 还款期数
	 * 
	 * @param params
	 */
	public AccCapWith queryCapWith(Map<String, Object> params);

	/**
	 * id更新代扣表代扣状态
	 * 
	 * @param params
	 * @return
	 */
	public Integer updateAccCapWithStatById(Map<String, Object> params);

	/**
	 * 分页查询代扣纪录列表
	 * 
	 * @param params
	 * @return
	 */
	public Page<SingleRepayBo> querySingleRepayBoForList(Page<SingleRepayBo> page);

	/**
	 * 查询代扣纪录列表
	 * 
	 * @param params
	 * @return
	 */
	public List<SingleRepayBo> querySingleRepayBoForList(Map<String, Object> params);

	public Integer updateByDkReturn(Map<String, Object> params);

	/**
	 * 批量更新 根据批量代扣查询条件批量更改代扣纪录为处理中并更新预处理表更新时间
	 * 
	 * @param params
	 * @return
	 */
	@Deprecated
	public Integer batchUpdateAccCapWithByBatckDk(Map<String, Object> params);

	public void updateAccCapWithByStatByLoanNo(Map<String, Object> map);

	public String getChanNoByMap(Map<String, Object> chanParam);

	/**
	 * 判断该条贷款编号所扣期数 之前是否有未还款记录
	 * 
	 * @param withMap
	 * @return
	 */
	public List<AccCapWith> queryWithNStat(Map<String, Object> withMap);

	/**
	 * 根据贷款编号查扣款日志
	 * 
	 * @param loanNo
	 * @param repayDate
	 * @return
	 */
	public Map<String, Object> findTranLog(Map<String, Object> map);
	/**
	 * 中金扣款次数核实
	 * @param loanNo
	 * @param repayNum
	 * @return
	 */
	public Integer queryCapSize(String loanNo);
	/**
	 * 快付通扣款次数核实
	 * @param loanNo
	 * @param repayNum
	 * @return
	 */
	public Integer queryPaySize(String loanNo);

	public Integer queryLianLianPayCapSize(String loanNo);
	
	/**
	 * 获取下一个快付通扣款编号
	 * @return
	 */
	public String getNextKftBatchNo();
	
	/**
	 * 更新快付通扣款编号
	 * @param kftBatchNo
	 */
	public Integer updateKftNo(String kftBatchNo);

	public List<SingleRepayBo> querySingleRepayBoForListOnKFT(Map<String, Object> params);

}