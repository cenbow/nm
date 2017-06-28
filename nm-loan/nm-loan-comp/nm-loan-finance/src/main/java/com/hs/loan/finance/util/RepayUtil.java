package com.hs.loan.finance.util;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.bo.CallBackInfo;
import com.hs.loan.finance.bo.MerInfo;
import com.hs.loan.finance.bo.QuerySum;
import com.hs.loan.finance.bo.SingleDkDto;
import com.hs.loan.finance.bo.SingleOtherBusiBo;
import com.hs.loan.finance.bo.SingleRepayBo;
import com.hs.loan.finance.bo.TransItem;
import com.hs.loan.finance.bo.TransSum;
import com.hs.loan.finance.contant.FinanceConstant;
import com.hs.loan.finance.entity.AccCapWith;
import com.hs.utils.ParamUtils;
import com.hs.utils.StringUtils;

public class RepayUtil {
	@Autowired

	/**
	 * 单笔 催收和提前结清代扣
	 * 
	 * @param singleOtherBusiBo
	 * @return
	 * @throws ServiceException
	 */
	public static SingleDkDto getSingleDkVo(SingleOtherBusiBo singleOtherBusiBo) throws ServiceException {
		// 设置商户银联信息
		MerInfo merInfo = getMerInfo(singleOtherBusiBo.getTranType());
		// 设置交易摘要
		TransSum transSum = getTransSum("", singleOtherBusiBo.getTransAmtTotal(), singleOtherBusiBo.getTranType());
		// 设置交易明细
		if (StringUtils.isEmpty(singleOtherBusiBo.getLoanNo())) {
			throw new ServiceException("贷款编号不能为空！");
		}
		if (StringUtils.isEmpty(singleOtherBusiBo.getAcctNo())) {
			throw new ServiceException("扣款账号不能为空！");
		}
		if (StringUtils.isEmpty(singleOtherBusiBo.getAcctName())) {
			throw new ServiceException("扣款账户名不能为空！");
		}
		if (StringUtils.isEmpty(singleOtherBusiBo.getBankNo())) {
			throw new ServiceException("扣款银行代码不能为空！");
		}
		if (StringUtils.isEmpty(singleOtherBusiBo.getCertNo())) {
			throw new ServiceException("客户身份证号码不能为空！");
		}
		if (singleOtherBusiBo.getRepayNum() == null || singleOtherBusiBo.getRepayNum() == 0) {
			throw new ServiceException("扣款执行期数不能为空或者 0！");
		}
		if (StringUtils.isEmpty(singleOtherBusiBo.getId())) {
			throw new ServiceException("主键来源不能为空！");
		}
		if (singleOtherBusiBo.getTransAmtTotal().compareTo(new BigDecimal(0)) == 0) {
			throw new ServiceException("主键来源：［" + singleOtherBusiBo.getId() + "]交易金额不能为0");
		}
		TransItem transItem = new TransItem();
		transItem.setBankCode(singleOtherBusiBo.getBankNo());
		transItem.setAccountNo(singleOtherBusiBo.getAcctNo());
		transItem.setAccountName(singleOtherBusiBo.getAcctName());
		transItem.setAmount(AmountUtil.convertY2F(singleOtherBusiBo.getTransAmtTotal()));
		// transItem.setIdType(QhxgConstants.CERT_TYPE_TEMP.equals(custBaseInfoVO.getCertType())?"7":"0");
		transItem.setId(singleOtherBusiBo.getCertNo());// 此id未身份证
		transItem.setRemark(singleOtherBusiBo.getId());
		transItem.setReserve1(singleOtherBusiBo.getChanNo());
		transItem.setReserve2(singleOtherBusiBo.getLoanNo());
		transItem.setRepayNum(singleOtherBusiBo.getRepayNum());
		transItem.setRepayDate(singleOtherBusiBo.getRepayDate());
		SingleDkDto singleDkVo = new SingleDkDto();
		singleDkVo.setMerInfo(merInfo);
		singleDkVo.setTransSum(transSum);
		singleDkVo.setTransItem(transItem);
		return singleDkVo;
	}

	/**
	 * 获取单笔代扣VO数据
	 * 
	 * @param chanNo
	 *            渠道编号
	 * @param acctFlag
	 *            交易方
	 * @param totalSum
	 *            交易金额
	 * @param loanNo
	 *            贷款编号
	 * @param tableKey
	 *            操作表主键
	 * @return
	 * @throws ServiceException
	 */
	public static SingleDkDto getSingleDkVo(SingleRepayBo singleRepayBo, BigDecimal transBal) throws ServiceException {
		// 设置商户银联信息
		MerInfo merInfo = getMerInfo(singleRepayBo.getTranType());
		// 设置交易摘要
		TransSum transSum = getTransSum("", transBal, singleRepayBo.getChanNo());
		// 设置交易明细
		TransItem transItem = getTransItem(transBal, singleRepayBo);
		SingleDkDto singleDkVo = new SingleDkDto();
		singleDkVo.setMerInfo(merInfo);
		singleDkVo.setTransSum(transSum);
		singleDkVo.setTransItem(transItem);
		return singleDkVo;
	}

	/**
	 * 设置商户银联信息
	 * 
	 * @param tranType
	 *            //渠道编号
	 * @param acctFlag
	 *            //交易方
	 * @return
	 * @throws ServiceException
	 */
	public static MerInfo getMerInfo(String tranType) throws ServiceException {
		// 为空 默认走广银联
		MerInfo merInfo = new MerInfo();
		if (PayChanType.CHINAPAY.toString().equals(tranType)) { // 银联
			merInfo.setUnionPayUrl(ParamUtils.getParam(FinanceConstant.GZ_CHINAPAY_URL));
			merInfo.setMerchantId(ParamUtils.getParam(FinanceConstant.GZ_CHINAPAY_MERID));
			merInfo.setUserName(ParamUtils.getParam(FinanceConstant.GZ_CHINAPAY_USER_USERNAME));
			merInfo.setUserPass(ParamUtils.getParam(FinanceConstant.GZ_CHINAPAY_USER_PASSWORD));
			merInfo.setMerKeyPath(ParamUtils.getParam(FinanceConstant.GZ_CHINAPAY_MERKEY_FILEPATH));
			merInfo.setPubKeyPath(ParamUtils.getParam(FinanceConstant.GZ_CHINAPAY_PUBKEY_FILEPATH));
			merInfo.setUnionPayTmpBatch(ParamUtils.getParam(FinanceConstant.gz_template_batchDk_classpath));
			merInfo.setUnionPayTmpSingle(ParamUtils.getParam(FinanceConstant.gz_template_singleDk_classpath));
			merInfo.setUnionPayTmpQuery(ParamUtils.getParam(FinanceConstant.gz_template_transQuery_classpath));
		}
		// else if (PayChanType.ALLINPAY.toString().equals(tranType)) { // 通联
		// merInfo.setUnionPayUrl(ParamUtils.getParam(FinanceConstant.TL_CHINAPAY_URL));
		// merInfo.setMerchantId(ParamUtils.getParam(FinanceConstant.TL_CHINAPAY_MERID));
		// merInfo.setUserName(ParamUtils.getParam(FinanceConstant.TL_CHINAPAY_USER_USERNAME));
		// merInfo.setUserPass(ParamUtils.getParam(FinanceConstant.TL_CHINAPAY_USER_PASSWORD));
		// merInfo.setMerKeyPath(ParamUtils.getParam(FinanceConstant.TL_CHINAPAY_MERKEY_FILEPATH));
		// merInfo.setPubKeyPath(ParamUtils.getParam(FinanceConstant.TL_CHINAPAY_PUBKEY_FILEPATH));
		// merInfo.setUnionPayTmpBatch(ParamUtils.getParam(FinanceConstant.tl_template_batchDk_classpath));
		// merInfo.setUnionPayTmpSingle(ParamUtils.getParam(FinanceConstant.tl_template_singleDk_classpath));
		// merInfo.setUnionPayTmpQuery(ParamUtils.getParam(FinanceConstant.tl_template_transQuery_classpath));
		// }
		return merInfo;
	}

	/**
	 * 设置交易摘要
	 * 
	 * @param totalItem
	 *            交易总笔数
	 * @param totalSum
	 *            交易金额
	 * @param chalCode
	 *            渠道号
	 * @param direction
	 *            扣款方向
	 * @return
	 */
	public static TransSum getTransSum(String totalItem, BigDecimal totalSum, String chalCode) {
		TransSum transSum = new TransSum();
		// 交易流水号
		transSum.setReqSn(System.currentTimeMillis() + "");
		// 交易总金额
		transSum.setTotalSum(AmountUtil.convertY2F(totalSum));
		// 交易总笔数
		transSum.setTotalItem(totalItem);
		// 渠道号
		transSum.setChalCode(chalCode);
		// 扣款方向
		transSum.setDirection("");
		return transSum;
	}

	/**
	 * 
	 * @param totalSum
	 * @param singleRepayBo
	 * @param acwr
	 * @return
	 * @throws ServiceException
	 */
	public static TransItem getTransItem(BigDecimal totalSum, SingleRepayBo singleRepayBo) throws ServiceException {
		if (StringUtils.isEmpty(singleRepayBo.getLoanNo())) {
			throw new ServiceException("数据错误，请关闭页面重新操作！");
		}
		// 设置交易明细
		if (StringUtils.isEmpty(singleRepayBo.getLoanNo())) {
			throw new ServiceException("贷款编号不能为空！");
		}
		if (StringUtils.isEmpty(singleRepayBo.getAcctNo())) {
			throw new ServiceException("扣款账号不能为空！");
		}
		if (StringUtils.isEmpty(singleRepayBo.getAcctName())) {
			throw new ServiceException("扣款账户名不能为空！");
		}
		if (StringUtils.isEmpty(singleRepayBo.getBankNo())) {
			throw new ServiceException("扣款银行代码不能为空！");
		}
		if (StringUtils.isEmpty(singleRepayBo.getCertNo())) {
			throw new ServiceException("客户身份证号码不能为空！");
		}
		if (singleRepayBo.getRepayNum() == null || singleRepayBo.getRepayNum() == 0) {
			throw new ServiceException("扣款执行期数不能为空或者 0！");
		}
		if (StringUtils.isEmpty(singleRepayBo.getId())) {
			throw new ServiceException("主键来源不能为空！");
		}
		if (totalSum.compareTo(new BigDecimal(0)) == 0) {
			throw new ServiceException("主键来源：［" + singleRepayBo.getId() + "]交易金额不能为0");
		}
		TransItem transItem = new TransItem();
		transItem.setBankCode(singleRepayBo.getBankNo());// 扣款银行开户机构号
		transItem.setAccountNo(singleRepayBo.getAcctNo());// 扣款银行账号
		transItem.setAccountName(singleRepayBo.getAcctName());// 扣款账户姓名
		transItem.setAmount(AmountUtil.convertY2F(totalSum));// 交易总金额（分）
		transItem.setId(singleRepayBo.getCertNo());// 此id未身份证
		transItem.setRemark(singleRepayBo.getId());// 代扣id
		transItem.setReserve1(singleRepayBo.getChanNo());// 渠道
		transItem.setReserve2(singleRepayBo.getLoanNo());
		transItem.setRepayNum(singleRepayBo.getRepayNum());
		transItem.setRepayDate(singleRepayBo.getRepayDate());
		return transItem;
	}

	/**
	 * 实时批量代扣回盘后回调信息
	 * 
	 * @param action
	 * @param method
	 * @param webUrl
	 * @return
	 */
	@Deprecated
	public static CallBackInfo getCallBackInfo() {
		CallBackInfo info = new CallBackInfo();
		// Properties systemProps = BaseEnv.getInstance().getSystemProps();
		// info.setAction(systemProps.getProperty("batch_repay_callback_action"));
		// info.setMethod(systemProps.getProperty("batch_repay_callback_method"));
		info.setWebUrl(ParamUtils.getParam("batch_repay_callback_webUrl"));
		return info;
	}

	/**
	 * 银联批扣查询摘要
	 * 
	 * @param querySn
	 *            要查询的交易流水
	 * @param queryRemark
	 *            查询的备注
	 * @param chalCode
	 *            渠道号
	 * @param direction
	 *            扣款方向
	 * @return
	 */
	public static QuerySum getQuerySum(String querySn, String queryRemark, String chalCode, String direction) {
		QuerySum querySum = new QuerySum();
		if (PayChanType.CHINAPAY.toString().equals(chalCode)) { // 银联
			querySum.setReqSn(System.currentTimeMillis() + "");
		} else if (PayChanType.ALLINPAY.toString().equals(chalCode)) { // 通联
			querySum.setReqSn(
					ParamUtils.getParam(FinanceConstant.GZ_CHINAPAY_MERID) + "-" + System.currentTimeMillis());
		}
		querySum.setQuerySn(querySn);
		querySum.setQueryRemark(queryRemark);
		querySum.setChalCode(chalCode);
		querySum.setDirection(direction);
		return querySum;
	}

	/**
	 * 取得交易状态 当前日期-账单日>阈值 ==>逾期 当前日期-账单日<=阈值 ==>正常
	 * 
	 * @param repayDate
	 * @return
	 * @throws ServiceException
	 */
	public static String getLoanStat(String repayDate) throws ServiceException {
		int dateDiff = com.hs.loan.finance.util.DateUtil.dateDiff(DateUtil.getDay("yyyyMMdd"), repayDate);
		// 取得配置阈值
		int loanStat = 3;
		if (dateDiff > loanStat) {
			return FinanceConstant.LOAN_STAT_YQ;
		} else {
			return FinanceConstant.LOAN_STAT_ZC;
		}
	}
}
