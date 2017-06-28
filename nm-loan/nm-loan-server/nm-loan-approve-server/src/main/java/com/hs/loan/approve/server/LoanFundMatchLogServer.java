package com.hs.loan.approve.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.ServiceException;
import com.hs.loan.approv.dto.LoanFundMatchLogDto;
import com.hs.loan.approve.api.LoanFundMatchLogApi;
import com.hs.loan.approve.entity.AppLoanFundMatchLog;
import com.hs.loan.approve.service.AppLoanFundMatchLogService;
import com.hs.utils.BeanUtils;
import com.hs.utils.StringUtils;


@Service
@Transactional(readOnly=true)
public class LoanFundMatchLogServer implements LoanFundMatchLogApi {

	@Autowired
	private AppLoanFundMatchLogService appLoanFundMatchLogService;
	
	@Override
	public List<LoanFundMatchLogDto> queryLoanFundMathcLog(String loanNo) {
		
		if(StringUtils.isEmpty(loanNo)){
			throw new ServiceException("分期编号为空");
		}
		Map<String,Object> map =new HashMap<>();
		map.put("loanNo", loanNo);
		List<AppLoanFundMatchLog> list = appLoanFundMatchLogService.queryForList(map);
		return BeanUtils.copyProperties(list, LoanFundMatchLogDto.class);
	}

	@Override
	public Page<LoanFundMatchLogDto> queryLoanFundMathcLogForPage(Page<LoanFundMatchLogDto> page,UserProfile profile)
	{
		//权限控制
		Page<AppLoanFundMatchLog> page1=appLoanFundMatchLogService.queryForPage(page.toPage(AppLoanFundMatchLog.class));
		return page1.toPage(LoanFundMatchLogDto.class);
	}

}
