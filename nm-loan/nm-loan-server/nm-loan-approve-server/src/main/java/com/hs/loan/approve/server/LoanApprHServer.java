package com.hs.loan.approve.server;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.approv.dto.LoanApprHDto;
import com.hs.loan.approve.api.LoanApprHApi;
import com.hs.loan.approve.entity.AppLoanApprH;
import com.hs.loan.approve.service.AppLoanApprHService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
public class LoanApprHServer implements  LoanApprHApi{

	@Autowired
	private AppLoanApprHService appLoanApprHService;
	
	/**
	 * 分期审批历史查询
	 */
	@Override
	public Page<LoanApprHDto> queryForPage(Page<LoanApprHDto> page, UserProfile profile)throws ServiceException, AppException {
		//员工编号
		String staffNo=profile.getStaffNo();
		//基本数据判断
		if(org.apache.commons.lang3.StringUtils.isBlank(staffNo)){
			throw new com.hs.base.exception.ServiceException("员工编号为空");
		}
		//员工角色集合
		List<String> roleList = appLoanApprHService.selectRoleByStaffNo(staffNo);
		//角色集合为空
		if(null==roleList){
			return  null;
		}
		//是admin和审批经理
		boolean isAuth=false;
		for (String s : roleList) {
			//判断是否有权限查看
            if(StringUtils.equals(s,"r_appr_mgr")||StringUtils.equals(s,"r_sys_super")||StringUtils.equals(s,"r_appr_staff")){
				isAuth=true;
			}
			//判断是否是审批人员   //零时需求20160518
			if(StringUtils.equals(s,"r_appr_staff")){
				//page.getParams().put("apprNo",staffNo);
			}
		}
		if(isAuth){
			Page<AppLoanApprH> pageRsl= appLoanApprHService.queryLoanAprHis(page.toPage(AppLoanApprH.class),profile);
			return pageRsl.toPage(LoanApprHDto.class);
		}else{
			throw new ServiceException("没有权限查看");
		}

	}

}
