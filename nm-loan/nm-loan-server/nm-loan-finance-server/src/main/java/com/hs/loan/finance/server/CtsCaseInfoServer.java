package com.hs.loan.finance.server;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.api.CaseInfoApi;
import com.hs.loan.finance.contant.FinanceConstant;
import com.hs.loan.finance.dto.CtsCaseInfoDto;
import com.hs.loan.finance.entity.CtsCaseInfo;
import com.hs.loan.finance.service.CtsCaseInfoService;
import com.hs.utils.BeanUtils;
import com.hs.utils.RandomUtil;

/**
 * 安全合规
 * 
 * @author zhangxiaoqiang
 *
 */
@Service
@Transactional(readOnly = true)
public class CtsCaseInfoServer implements CaseInfoApi {

	@Autowired
	private CtsCaseInfoService ctsCaseInfoService;

	/**
	 * 查询所有安全合规案件
	 */
	@Override
	public Page<CtsCaseInfoDto> queryCaseInfoPage(Page<CtsCaseInfoDto> page) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		return ctsCaseInfoService.queryForPage(page.toPage(CtsCaseInfo.class)).toPage(CtsCaseInfoDto.class);
	}

	/**
	 * 上报安全合规
	 */
	@Override
	@Transactional
	public void insertCase(CtsCaseInfoDto caseinfo, UserProfile user) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		CtsCaseInfo info = new CtsCaseInfo();
		BeanUtils.copyProperties(caseinfo, info);
		info.setId(RandomUtil.getUUID());
		info.setInstDate(new Date());
		info.setUpdtDate(new Date());
		info.setStaffNo(user.getStaffNo());
		info.setStaffName(user.getStaffautName());
		info.setCaseStat("60021001");
		info.setDealStat(FinanceConstant.ALL_ZT_FALSE);
		if (caseinfo.getCaseNo()!=null) {
			boolean flag = ctsCaseInfoService.queryParam(caseinfo.getCaseNo());
			if (flag ) {
				ctsCaseInfoService.insert(info);
			}else {
				throw new ServiceException("该笔贷款已上报");
			}
		}else {
			ctsCaseInfoService.insert(info);
		}
		 
	}

	/**
	 * 标记安全合规
	 */
	@Override
	@Transactional
	public void insertMarkCase(Map<String, Object> param, UserProfile user) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		
		

		CtsCaseInfo info = new CtsCaseInfo();
		info.setId(RandomUtil.getUUID());
		info.setInstDate(new Date());
		info.setUpdtDate(new Date());
		info.setStaffNo(user.getStaffNo());
		info.setStaffName(user.getStaffautName());
		info.setCaseStat("60021001");
		info.setDealStat(FinanceConstant.ALL_ZT_FALSE);
		info.setArchNote(param.get("archNote"));
		info.setCaseDesc(param.get("caseDesc"));
		info.setCaseSource(param.get("caseSource").toString());
		info.setCaseType(param.get("caseType").toString());
		info.setPrTyp(param.get("prTyp").toString());
		info.setCaseNo(param.get("loanNo") == null ? "" : param.get("loanNo").toString());
		boolean flag =false;
        flag = ctsCaseInfoService.queryParam(param.get("loanNo").toString());
		if (flag) {
			ctsCaseInfoService.insert(info);
		}else {
			throw new ServiceException("该笔贷款已被标记");
		}
	}

	/**
	 * 复核合规案件
	 */
	@Override
	@Transactional
	public void updateCase(Map<String, Object> param) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		ctsCaseInfoService.updateByPrimaryKeySelective(param);
	}

	/**
	 * 批量复核合规案件
	 */
	@Override
	@Transactional
	public void updateCaseList(List<Map<String, Object>> list) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		ctsCaseInfoService.updateCaseList(list);
	}

}
