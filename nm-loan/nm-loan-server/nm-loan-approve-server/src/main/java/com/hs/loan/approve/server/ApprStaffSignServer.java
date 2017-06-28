package com.hs.loan.approve.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.approv.dto.ApprStaffSignDto;
import com.hs.loan.approve.api.ApprStaffSignApi;
import com.hs.loan.approve.service.AppApprStaffSignService;
import com.hs.utils.StringUtils;
@Service
@Transactional(readOnly=true)
public class ApprStaffSignServer implements ApprStaffSignApi{
	@Autowired
	private AppApprStaffSignService staffSignService;

	@Override
	public String queryCurrentStat(UserProfile profile) throws ServiceException, AppException {
		String status = staffSignService.getStaffStat(profile.getStaffNo());
		if(StringUtils.isEmpty(status)){
			status = PubBusinessConstant.SIGNTYPE_OFFLINE;
		}
		return status;
	}

	@Override
	@Transactional
	public void onLine(UserProfile profile) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		staffSignService.updateStaffOnLine(profile);
	}

	@Override
	@Transactional
	public void offLine(UserProfile profile) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		staffSignService.updateStaffOffLine(profile);
	}

	@Override
	public Page<ApprStaffSignDto> queryStaffSignRst(Page<ApprStaffSignDto> page, UserProfile profile)
			throws ServiceException, AppException {
		// TODO Auto-generated method stub
		return null;
	}

}
