package com.hs.loan.finance.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.finance.bo.MerInfo;
import com.hs.loan.finance.bo.SingleDkDto;
import com.hs.loan.finance.bo.SingleDkResultBo;
import com.hs.loan.finance.bo.SingleRepayBo;
import com.hs.loan.finance.bo.TransItem;
import com.hs.loan.finance.contant.FinanceConstant;
import com.hs.loan.finance.entity.AccRepayFlow;
import com.hs.loan.finance.mapper.AccRepayFlowMapper;
import com.hs.loan.finance.util.AmountUtil;
import com.hs.loan.finance.util.DateUtil;
import com.hs.loan.finance.util.PayChanType;
import com.hs.loan.finance.util.RepayUtil;
import com.hs.utils.RandomUtil;

/**
 * ACC_还款流水 业务处理
 * @author autocreate
 * @create 2016-02-03
 */
@Service
@Transactional(readOnly=true)
public class  AccRepayFlowService{
	
	@Autowired 
	private AccLoanAcctInstService accLoanAcctInstService;
	
	@Autowired
	private AccRepayFlowMapper accRepayFlowMapper;
	
	/**
	 * 批量插入还款流水
	 * @param list
	 * @return
	 */
	@Transactional
	public Integer batchInsertRepayFlow(List<AccRepayFlow> list){
		return accRepayFlowMapper.batchInsertRepayFlow(list);
	}
	
	/**
	 * 新增 ACC_还款流水
	 * @param accRepayFlow 新增对象
	 */
	@Transactional
	public void insert(AccRepayFlow accRepayFlow){
		accRepayFlowMapper.insert(accRepayFlow);
	}

	/**
	 * 通过主键修改 ACC_还款流水
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		accRepayFlowMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 ACC_还款流水
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		accRepayFlowMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 ACC_还款流水 对象
	 * @param primaryKey 主键
	 * @return ACC_还款流水对象
	 */
	public AccRepayFlow getByPrimaryKey(String primaryKey){
		return accRepayFlowMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 ACC_还款流水 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AccRepayFlow> queryForList(Map<String, Object> param){
		return accRepayFlowMapper.queryForList(param);
	}
	
	/**
	 * 查询 ACC_还款流水 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AccRepayFlow> queryForPage(Page<AccRepayFlow> page){
		accRepayFlowMapper.queryForList(page.getPageParams());
		return (Page<AccRepayFlow>)page.getPageParams().get(Page.KEY);
	}
	
	/**
	 * 保存还款流水
	 * @param flowVO	//预处理VO
	 * @param singleDkVo	//单笔代扣数据VO
	 * @param retCode	//返回码
	 * @throws ServiceException 
	 */
	public String saveRepayFlow(SingleRepayBo singleRepayBo,SingleDkDto singleDkVo,SingleDkResultBo dkResultBo,UserProfile userProFile) throws Exception{
		AccRepayFlow flowVO = new AccRepayFlow();
		MerInfo merInfo = singleDkVo.getMerInfo();
		TransItem transItem = singleDkVo.getTransItem();
		PropertyUtils.copyProperties(flowVO, singleRepayBo);
		if (PayChanType.ALLINPAY.toString().equals(singleRepayBo.getTranType())) { //通联
			//交易渠道
			flowVO.setTranChan(PubBusinessConstant.ALINPAY_SIGLE);
		}else if (PayChanType.CHINAPAY.toString().equals(singleRepayBo.getTranType())) { //银联
			//交易渠道
			flowVO.setTranChan(PubBusinessConstant.TRANCHAN_SIGNTYPE);
		}else if (PayChanType.LYCHPAY.toString().equals(singleRepayBo.getTranType())) { // 快付通
			//交易渠道
			flowVO.setTranChan(PubBusinessConstant.KFT_BRANCH);
		}else if (PayChanType.ZJPAY.toString().equals(singleRepayBo.getTranType())) { //中金
			//交易渠道
			flowVO.setTranChan(PubBusinessConstant.ZJ_SIGLE);
		} else if (PayChanType.LIANLIANPAY.toString().equals(singleRepayBo.getTranType())) { //连连
			//交易渠道
			flowVO.setTranChan(PubBusinessConstant.LL_SIGLE);
		} 
		
		//交易日期
		flowVO.setTranDate(DateUtil.getDay("yyyyMMdd"));
		//设置交易金额 当前当前应还金额 CUR_RCV_AMT
		flowVO.setTranAmt(new BigDecimal(AmountUtil.convertF2Y(dkResultBo.getRetItem().getAmount())));
		//是否结算：否
		flowVO.setSetlFlag(CommonConstant.COMMON_NO);
		//创建日期
		flowVO.setInstDate(new Date());
		//交易类型
//		flowVO.setTranType(PubBusinessConstant.REPAY_TYPE_NOM);
		
		//还款账户
		flowVO.setAcctNo(transItem.getAccountNo());
		//还款户名
		flowVO.setAcctName(transItem.getAccountName());
		//交易行号
		flowVO.setTranOrg(transItem.getBankCode());
		//交易行名称
		//交易状态：当前日期与账单日比较，如果
		flowVO.setLoanStat(RepayUtil.getLoanStat(flowVO.getRepayDate()));
		//交易大类:正常
		flowVO.setTranType(PubBusinessConstant.TRANDTYPE_ZC);
		//转入账号
		flowVO.setCntAcctNo(merInfo.getMerchantId());
		//转入户名
		flowVO.setCntAcctName("");
		flowVO.setTranStaff(userProFile.getLoginNo());
		// 100310-平台/100320-资方
		if (singleRepayBo.getChanNo()!=null&&singleRepayBo.getChanNo().equals("006")) {
			flowVO.setTranObj("100320");
		}else {
			flowVO.setTranObj("100310");
		}
		//经办人
		//交易是否成功
		boolean isSucc = false;
		if(FinanceConstant.TRAN_ST_SUCC.equals(dkResultBo.getRetItem().getRetCode())){
			isSucc = true;
		}
		//是否提前结清 如果交易金额和结清金额相等 默认为结清
		Boolean isCleard = false;
		if(singleRepayBo.getTotlAmt().compareTo(new BigDecimal(dkResultBo.getRetItem().getAmount())) == 0){//结清
			isCleard = true;
		}
		//老系统方法
		String saveRepayFlow = saveRepayFlow(flowVO, isSucc, isCleard);
		if(!FinanceConstant.SUCC.equals(saveRepayFlow)){
			throw new ServiceException(saveRepayFlow);
		}
		return saveRepayFlow;
	}
	
	/**
	 * 正常交易
	 * 交易结束后处理借贷流水及预处理表
	 * @param loanRepayFlowVO
	 * @param isSucc 交易是否成功
	 * @param isFund 是否为资方扣款交易
	 * @param isClear 是否提前结清
	 * @return
	 * @throws ServiceException
	 */
	public String saveRepayFlow(AccRepayFlow loanRepayFlowVO, Boolean isSucc, Boolean isClear)throws ServiceException{
		String loanNo = loanRepayFlowVO.getLoanNo();
		String rs = FinanceConstant.SUCC;
		//贷款编号为空
		if(loanNo == null || loanNo.equals("")){
			return "P001-贷款编号为空";
		}
		//如果交易成功则记录借贷流水
		if(isSucc){
			//返回结果
			rs = saveRepay(loanRepayFlowVO);
			//保存还款流水不成功
			if(!rs.equals("SUCC")){
				return rs;
			}
			//提前结清处理
			if(isClear){
				//修改预处理表状态
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("loanNo", loanNo);
				paramMap.put("stat", FinanceConstant.PRETREAT_STAT_CLEARD);
				paramMap.put("updt", new Date());
				//提前结清更新所有预处理的表状态为结清
				//更新为已结清
				rs = accLoanAcctInstService.updateAccLoanAcctStat(loanNo,-1,FinanceConstant.PRETREAT_STAT_CLEARD);
			}else{
				//如果不是结清，则更新当前期数的数据，并且解锁当前期的表状态
				BigDecimal ACURR_SHL_AMT = loanRepayFlowVO.getTranAmt();
				//如果交易金额为0则返回错误码
				if(ACURR_SHL_AMT == null || ACURR_SHL_AMT.compareTo(new BigDecimal(0)) == 0){
					return "P003-交易金额不正确";
				}
				//当前资方实还金额
				//获取全部预处理表信息
				if (loanRepayFlowVO.getTranObj().equals("100320")) {
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("loanNo", loanNo);
					paramMap.put("stat", FinanceConstant.PRETREAT_STAT_UNLOCK);
					paramMap.put("repayNum", loanRepayFlowVO.getRepayNum());
					//减掉预处理表 当日应还fundCurRcvAmt 金额
					
					paramMap.put("fundCurRcvAmt", ACURR_SHL_AMT);
					paramMap.put("updt", new Date());
					rs = accLoanAcctInstService.updateXtAlaiCramtAndIns(paramMap);
				}else {
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("loanNo", loanNo);
					paramMap.put("stat", FinanceConstant.PRETREAT_STAT_UNLOCK);
					paramMap.put("repayNum", loanRepayFlowVO.getRepayNum());
					//减掉预处理表 当日应还curRcvAmt 金额
					
					paramMap.put("curRcvAmt", ACURR_SHL_AMT);
					paramMap.put("updt", new Date());
					rs = accLoanAcctInstService.updateAlaiCramtAndIns(paramMap);
				}
			}
			//修改预处理表出错
			if(!rs.equals(FinanceConstant.SUCC)){
				return "P004-修改预处理表出错［"+loanNo+","+loanRepayFlowVO.getRepayNum()+"］";
			}
		}
		return rs;
		
	}
	
	
	/**
	 * 记录实还交易流水
	 * @param loanRepayFlowVO
	 * @return
	 * @throws ServiceException
	 */
	public String saveRepay(AccRepayFlow loanRepayFlowVO)throws ServiceException{
		//对象为空
		if(loanRepayFlowVO == null){
			return "F001-对象为空";
		}
		//贷款编号为空
		if(loanRepayFlowVO.getLoanNo() == null || loanRepayFlowVO.getLoanNo().equals("")){
			return "F002-贷款编号为空";
		}
		//还款日为空
		if(loanRepayFlowVO.getRepayDate() == null || loanRepayFlowVO.getRepayDate().equals("")){
			return "F003-还款日为空";
		}
		//还款金额为空
		if(loanRepayFlowVO.getTranAmt() == null || loanRepayFlowVO.getTranAmt().compareTo(new BigDecimal(0)) == 0 ){
			return "F004-还款金额为空";
		}
		//转入账号为空
//		if(loanRepayFlowVO.getCntAcctNo() == null || loanRepayFlowVO.getCntAcctNo().equals("")){
//			return "F005-转入账号为空";
//		}
		//渠道为空
		if(loanRepayFlowVO.getTranChan() == null || loanRepayFlowVO.getTranChan().equals("")){
			return "F006-渠道为空";
		}
		//发起机构为空
		if(loanRepayFlowVO.getTranOrg() == null || loanRepayFlowVO.getTranOrg().equals("")){
			return "F008-发起机构为空";
		}
		//发起人为空
		if(loanRepayFlowVO.getTranStaff() == null || loanRepayFlowVO.getTranStaff().equals("")){
			return "F009-发起人为空";
		}
		//交易大类为空
		if(loanRepayFlowVO.getTranType() == null || loanRepayFlowVO.getTranType().equals("")){
			return "F010-交易大类为空";
		}
		//交易流水
		loanRepayFlowVO.setId(RandomUtil.getUUID());
		//交易时间
		loanRepayFlowVO.setTranDate(DateUtil.getDay("yyyyMMdd"));
		//是或否结算-否
		loanRepayFlowVO.setSetlFlag(CommonConstant.COMMON_NO);
		loanRepayFlowVO.setUpdtDate(new Date());
		//保存借贷流水
//		Map<String, Object> map = new HashMap<>();
//		map.put("loanNo", loanRepayFlowVO.getLoanNo());
//		map.put("tranDate", loanRepayFlowVO.getTranDate());
//		map.put("tranAmt", loanRepayFlowVO.getTranAmt());
//		map.put("tranChan", loanRepayFlowVO.getTranChan());
//		map.put("repayDate", loanRepayFlowVO.getRepayDate());
//		accRepayFlowMapper.deleteByparam(map);
		accRepayFlowMapper.insert(loanRepayFlowVO);
		return FinanceConstant.SUCC;
	}

	public Map<String, Object> findOpenBankOrg(String loanNo) {
		// TODO Auto-generated method stub
		return accRepayFlowMapper.findOpenBankOrg(loanNo);
	}
	@Transactional
	public Integer updateOpenBankOrg(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return	accRepayFlowMapper.updateOpenBankOrg(param);
	}

	public Map<String, Object> findInstMap(String loanNo) {
		// TODO Auto-generated method stub
		return accRepayFlowMapper.findInstMap(loanNo);
	}
	@Transactional
	public Integer updateInstBusnDate(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return accRepayFlowMapper.updateInstBusnDate(param);
	}

	public Map<String, Object> findDgReg(String dgid) {
		// TODO Auto-generated method stub
		return accRepayFlowMapper.findDgReg(dgid);
	}

	public Map<String, Object> findRepayFlow(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return accRepayFlowMapper.findRepayFlow(param);
	}
	@Transactional
	public Integer updateDgReg(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return accRepayFlowMapper.updateDgReg(param);
	}
	@Transactional
	public Integer updateRepayFlow(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return accRepayFlowMapper.updateRepayFlow(param);
	}
}