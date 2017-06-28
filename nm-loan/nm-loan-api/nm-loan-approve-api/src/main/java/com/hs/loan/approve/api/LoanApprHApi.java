package com.hs.loan.approve.api;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.loan.approv.dto.LoanApprHDto;

/**
 * APP_分期审批信息 接口
 * @author autocreate
 * @create 2015-11-23
 */
public interface  LoanApprHApi{
	
	public Page<LoanApprHDto> queryForPage(Page<LoanApprHDto> page,UserProfile profile);
}