package com.hs.loan.cust.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.cust.api.CustRevisitInfoApi;
import com.hs.loan.cust.dto.CustRevisitInfoDto;
import com.hs.loan.cust.entity.AppCustRevisitInfo;
import com.hs.loan.cust.service.AppCustRevisitInfoService;
import com.hs.utils.BeanUtils;

/**
 * APP_客户回访信息 业务处理
 * @author autocreate
 * @create 2016-06-21
 */
@Service
@Transactional(readOnly=true)
public class  CustRevisitInfoServer implements CustRevisitInfoApi{
	@Autowired
	private AppCustRevisitInfoService appCustRevisitInfoService;

	@Override
	@Transactional
	public void save(CustRevisitInfoDto custRevisitInfoDto,
			UserProfile userProfile) throws ServiceException, AppException {
		AppCustRevisitInfo appCustRevisitInfo=new AppCustRevisitInfo();
		BeanUtils.copyProperties(custRevisitInfoDto, appCustRevisitInfo);
		appCustRevisitInfo.setRevisitDate(custRevisitInfoDto.getRevisitDate());
		appCustRevisitInfo.setTyp(PubBusinessConstant.LOANHANDMODEL_HAND);
		appCustRevisitInfo.setStaffId(userProfile.getStaffNo());
		appCustRevisitInfo.setStaffName(userProfile.getStaffName());
		appCustRevisitInfoService.insert(appCustRevisitInfo);
	}

	@Override
	public Page<CustRevisitInfoDto> queryForPage(Page<CustRevisitInfoDto> page)
			throws ServiceException, AppException {
		// TODO Auto-generated method stub
		return appCustRevisitInfoService.queryForPage(page.toPage(AppCustRevisitInfo.class)).toPage(CustRevisitInfoDto.class);
	}

	@Override
	@Transactional
	public void deleteRevisitInfo(String id, String staffId)
			throws ServiceException, AppException {
		AppCustRevisitInfo appCustRevisitInfo = appCustRevisitInfoService.getByPrimaryKey(id);
		if(PubBusinessConstant.LOANHANDMODEL_SYS.equals(appCustRevisitInfo.getTyp())){
			throw new ServiceException("只能删除手工回访信息");
		}
		if(!appCustRevisitInfo.getStaffId().equals(staffId)){
			throw new ServiceException("只能删除自己添加的回访信息");
		}
		appCustRevisitInfoService.deleteByPrimaryKey(id);
	}
}