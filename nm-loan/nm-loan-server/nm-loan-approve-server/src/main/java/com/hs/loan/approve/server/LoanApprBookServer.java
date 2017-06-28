package com.hs.loan.approve.server;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.approv.dto.LoanApprBookDto;
import com.hs.loan.approv.dto.LoanApprRemarkDto;
import com.hs.loan.approve.api.LoanApprBookApi;
import com.hs.loan.approve.entity.AppLoanApprBook;
import com.hs.loan.approve.entity.AppLoanApprRemark;
import com.hs.loan.approve.service.AppLoanApprBookService;
import com.hs.loan.approve.service.AppLoanApprRemarkService;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional(readOnly=true)
public class LoanApprBookServer  implements LoanApprBookApi{
	
	@Autowired
	private AppLoanApprBookService appLoanApprBookService;
	@Autowired
	private AppLoanApprRemarkService appLoanApprRemarkService;
	@Transactional
    public String svaeApprRemark(LoanApprRemarkDto loanApprRemarkDto, UserProfile userProfile)throws ServiceException, AppException{
		/*String id=loanApprRemarkDto.getId();
		if(null==id){
			id=RandomUtil.getUUID();
			AppLoanApprRemark appLoanApprRemark= new AppLoanApprRemark();
			BeanUtils.copyPropertiesNotForce(appLoanApprRemark, loanApprRemarkDto);
			appLoanApprRemark.setId(id);
			appLoanApprRemark.setInstDate(DateUtils.getCurrentTimestamp());
			appLoanApprRemark.setOperateName(userProfile.getStaffName());
			appLoanApprRemark.setOperateNo(userProfile.getStaffNo());
			appLoanApprRemarkService.insert(appLoanApprRemark);
			appLoanApprRemark.setId(id);
		}else{
			AppLoanApprRemark appLoanApprRemark= new AppLoanApprRemark();
			BeanUtils.copyPropertiesNotForce(appLoanApprRemark, loanApprRemarkDto);
			appLoanApprRemark.setId(id);
			appLoanApprRemarkService.update(BeanUtils.bean2map(appLoanApprRemark));
		}
		return id;*/
		String  blockid = loanApprRemarkDto.getBlockId();
		String  loanNo = loanApprRemarkDto.getLoanNo();
		if(StringUtils.isEmpty(blockid) || StringUtils.isEmpty(loanNo)){
			throw new ServiceException("块编号/贷款编号不能为空");
		}
		appLoanApprRemarkService.deleteByblockId(blockid,loanNo);
		AppLoanApprRemark appLoanApprRemark= new AppLoanApprRemark();
		BeanUtils.copyPropertiesNotForce(appLoanApprRemark, loanApprRemarkDto);
		appLoanApprRemark.setId(RandomUtil.getUUID());
		appLoanApprRemarkService.insert(appLoanApprRemark);
		return "";
	}
	public List<LoanApprRemarkDto> queryLoanApprRemarkList(String loanNo, UserProfile userProfile){
		Map<String,Object> param = new HashMap<>();
		param.put("loanNo", loanNo);
		List<AppLoanApprRemark> list = appLoanApprRemarkService.queryForList(param);
		return BeanUtils.copyProperties(list, LoanApprRemarkDto.class);
	}
	@Override
	@Transactional
	public String saveApprBook(LoanApprBookDto apprBookDto, UserProfile userProfile)
			throws ServiceException, AppException {
		String id=apprBookDto.getId();
	 	if(StringUtils.isEmpty(id)){
			 id=RandomUtil.getUUID();
			AppLoanApprBook appLoanApprBook = new AppLoanApprBook();
			BeanUtils.copyPropertiesNotForce(appLoanApprBook, apprBookDto);
			appLoanApprBook.setId(id);
			appLoanApprBook.setInstDate(DateUtils.getCurrentTimestamp());
			appLoanApprBook.setOperateName(userProfile.getStaffName());
			appLoanApprBook.setOperateNo(userProfile.getStaffNo());
			appLoanApprBookService.insert(appLoanApprBook);
		}else{
			AppLoanApprBook appLoanApprBook = new AppLoanApprBook();
			BeanUtils.copyPropertiesNotForce(appLoanApprBook, apprBookDto);
			appLoanApprBook.setId(id);
			appLoanApprBookService.updateByPrimaryKeySelective(BeanUtils.bean2map(appLoanApprBook));
			
		} 
	 	return id;
	}

	@Override
	public List<LoanApprBookDto> queryLoanApprBookLst(String loanNo, UserProfile userProfile)
			throws ServiceException, AppException {
		Map<String,Object> param = new HashMap<>();
		param.put("loanNo", loanNo);
		List<AppLoanApprBook> list = appLoanApprBookService.queryForList(param);
		return BeanUtils.copyProperties(list, LoanApprBookDto.class);
	}

	@Override
	@Transactional
	public void removeApprBook(String id) {
		appLoanApprBookService.deleteByPrimaryKey(id);
	}
	@Override
	@Transactional
	public void removeApprBookByLoan(String loanNo) {
		appLoanApprBookService.deleteByLoanNo(loanNo);
	}
}
