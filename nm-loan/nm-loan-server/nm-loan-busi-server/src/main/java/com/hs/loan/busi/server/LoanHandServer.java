package com.hs.loan.busi.server;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.busi.dto.LoanHandOutDto;
import com.hs.loan.cust.api.CustInfoApi;
import com.hs.loan.cust.dto.CustInfoDto;
import com.hs.loan.pub.hand.entity.AppLoanHand;
import com.hs.loan.pub.hand.service.AppLoanHandService;
import com.hs.loan.sale.api.LoanHandApi;
import com.hs.loan.sale.entity.AppLoanAcct;
import com.hs.loan.sale.service.AppLoanAcctService;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.SimpleCodeUtils;

/**
 * 分期经办登记查询
 * @author jqiu
 * @create 2015-10-27
 */
@Service
@Transactional(readOnly=true)
public class  LoanHandServer implements LoanHandApi{
	
	
	@Autowired
	private AppLoanHandService appLoanHandService;
	@Autowired
	private AppLoanAcctService appLoanAcctService;
	@Autowired
	private CustInfoApi busiCustInfoService;
	/**
	 * 手动分期经办登记新增（用于前台人员手动添加）
	 * @param loanNo	分期编号
	 * @param handDetailTyp	经办类型（码值）
	 * @param handPerson	经办人
	 * @param handDate		经办时间
	 * @param remark		说明
	 */
	@Transactional
	public void addHandInfoManual(LoanHandOutDto handOutDto){
		
		String loanNo=handOutDto.getLoanNo();
		
		String handDetailTyp =handOutDto.getHandDetail();
		String handPersonNo = handOutDto.getHandPersonNo();
		String handPersonName = handOutDto.getHandPersonName();
		Date handDate =handOutDto.getHandDate();
		String remark = handOutDto.getRemark();
		String custIdentifier = handOutDto.getCustIdentifier();
		AppLoanAcct appLoanAcct= appLoanAcctService.getByLoanNo(loanNo);
		if(appLoanAcct == null){
			throw new ServiceException("分期信息不存在");
		}
		String custNo = appLoanAcct.getCustNo();
		CustInfoDto cust=busiCustInfoService.getByNo(custNo);
		if(cust == null)throw new ServiceException("客户信息表中不存在客户信息，客户号："+custNo);
		appLoanHandService.saveAppLoanHand(loanNo,custNo,cust.getCustName(),handDetailTyp, PubBusinessConstant.LOANHANDMODEL_HAND, handPersonNo, handPersonName,handDate, remark,custIdentifier);
	}
	
	/**
	 * 自动分期经办登记新增（用于后台功能调用）
	 * @param loanNo	分期编号
	 * @param handDetailTyp	经办类型（码值）
	 * @param handPerson	经办人
	 * @param remark		说明
	 */
	@Transactional
	public void addHandInfoAuto(String loanNo,String handDetailTyp,String handPersonNo,String handPersonName,String remark){
		AppLoanAcct appLoanAcct= appLoanAcctService.getByLoanNo(loanNo);
		if(appLoanAcct == null){
			throw new ServiceException("分期信息不存在");
		}
		String custNo = appLoanAcct.getCustNo();
		CustInfoDto cust=busiCustInfoService.getByNo(custNo);
		if(cust == null)throw new ServiceException("客户信息表中不存在客户信息，客户号："+custNo);
		appLoanHandService.saveAppLoanHand(loanNo,custNo,cust.getCustName(), handDetailTyp, PubBusinessConstant.LOANHANDMODEL_SYS, handPersonNo, handPersonName,DateUtils.getCurrentTimestamp(), remark,PubBusinessConstant.CUST_ZC);
	}
	
	/**
	 * 手动分期经办登记删除（只能删除手动登记的）
	 * @param handId	记录id
	 * @param userId	操作人用户ID
	 */
	@Transactional
	public void deleteHandInfo(String handId,String userId){
		AppLoanHand appLoanHand = appLoanHandService.getByPrimaryKey(handId);
		if(PubBusinessConstant.LOANHANDMODEL_SYS.equals(appLoanHand.getTyp())){
			throw new ServiceException("只能删除手工经办信息");
		}
		if(!appLoanHand.getHandPersonNo().equals(userId)){
			throw new ServiceException("只能删除自己添加的经办信息");
		}
		appLoanHandService.deleteByPrimaryKey(handId);
	}
	
	/**
	 * 通过分期编号查询经办信息
	 * @param loanNo	分期编号
	 */
	public Page<LoanHandOutDto> queryHandInfoByLoanNo(Page<LoanHandOutDto> page,String loanNo,UserProfile userProfile){
		Set<String> roles = userProfile.getRoleNoSet();
		for (String role : roles) {
			if (PubBusinessConstant.ROLE_R_SALE_STAFF.equals(role)) {
				page.getPageParams().put("roleStat",
						PubBusinessConstant.LOANHANDTYPE_APPL + "," + PubBusinessConstant.LOANHANDTYPE_MODI + ","
								+ PubBusinessConstant.LOANHANDTYPE_UNDO + "," + PubBusinessConstant.LOANHANDTYPE_CHANC
								+ "," + PubBusinessConstant.LOANSTAT_SIGNING + "," + PubBusinessConstant.LOANHANDTYPE_CREDTLIMT);
				break;
			}
		}
		page.getPageParams().put("loanNo", loanNo);
		Page<AppLoanHand> page1 = appLoanHandService.queryForPage(page.toPage(AppLoanHand.class));
		Page<LoanHandOutDto> page2 = page1.toPage(LoanHandOutDto.class);
		List<LoanHandOutDto> list = page2.getList();
		for (LoanHandOutDto handOutDto : list) {
			handOutDto.setTypName(SimpleCodeUtils.getNameByCode(handOutDto.getTyp()));
			handOutDto.setHandDetailName(SimpleCodeUtils.getNameByCode(handOutDto.getHandDetail()));
 			handOutDto.setHandDate(DateUtils.convert2Date(DateUtils.dateToStr(handOutDto.getHandDate(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
		}
		return page2;
	}
	
	/**
	 * 通过客户编号查询经办信息
	 * @param custNo	客户编号
	 */
	public Page<LoanHandOutDto> queryHandInfoByDate(Page<LoanHandOutDto> page,UserProfile userProfile){
		Set<String> roles = userProfile.getRoleNoSet();
		for (String role : roles) {
			if (PubBusinessConstant.ROLE_R_SALE_STAFF.equals(role)) {
				page.getPageParams().put("roleStat",
						PubBusinessConstant.LOANHANDTYPE_APPL + "," + PubBusinessConstant.LOANHANDTYPE_MODI + ","
								+ PubBusinessConstant.LOANHANDTYPE_UNDO + "," + PubBusinessConstant.LOANHANDTYPE_CHANC
								+ "," + PubBusinessConstant.LOANSTAT_SIGNING);
				break;
			}
		}
		Page<AppLoanHand> page1 = appLoanHandService.queryForPage(page.toPage(AppLoanHand.class));
		Page<LoanHandOutDto> page2 = page1.toPage(LoanHandOutDto.class);
		List<LoanHandOutDto> list = page2.getList();
		for (LoanHandOutDto handOutDto : list) {
			handOutDto.setTypName(SimpleCodeUtils.getNameByCode(handOutDto.getTyp()));
		}
		return page2;
	}

	/**
	 * 获取经办信息
	 */
	
	@Override
	public LoanHandOutDto queryHandInfoByLoanNo(Map<String, Object> param) throws ServiceException, AppException {
		LoanHandOutDto outDto = new LoanHandOutDto();
		List<AppLoanHand> queryForList = appLoanHandService.queryForList(param);
		if(queryForList != null && queryForList.size() > 0){
			AppLoanHand appLoanHand = queryForList.get(0);
			 BeanUtils.copyProperties(appLoanHand, outDto);
		}
		return outDto;
	}
}