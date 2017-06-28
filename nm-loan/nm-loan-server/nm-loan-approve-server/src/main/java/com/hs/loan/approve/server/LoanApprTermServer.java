package com.hs.loan.approve.server;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.approv.dto.AppLoanApprTermDto;
import com.hs.loan.approve.api.LoanApprTermApi;
import com.hs.loan.approve.entity.AppLoanApprTerm;
import com.hs.loan.approve.service.AppLoanApprTermService;
import com.hs.utils.BeanUtils;
import com.hs.utils.ParamUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

@Service
@Transactional
public class LoanApprTermServer  implements LoanApprTermApi{
	
	@Autowired
	private  AppLoanApprTermService apploanapprtermservice;

	@Override
	public List<AppLoanApprTermDto> getApprTermList(String jobtype) throws ServiceException, AppException {
		Map<String,Object> map =new HashMap<>();
		map.put("jobTyp", jobtype);
		String TermdefaultNum = ParamUtils.getParam("TERM_DEF_VAL");
		map.put("termdefaultNum", TermdefaultNum);
		List<AppLoanApprTerm> termList = apploanapprtermservice.queryForList(map);
		List<AppLoanApprTermDto> apploanapprtermdtos = BeanUtils.copyProperties(termList,
				AppLoanApprTermDto.class);
		return apploanapprtermdtos;
	}

	@Override
	public void saveorupdateApprTermDto(AppLoanApprTermDto termdto, UserProfile userProFile) throws ServiceException, AppException {
		AppLoanApprTerm apploanapprterm = new AppLoanApprTerm();
		if(StringUtils.isEmpty(termdto.getTermId())){
		String termId = RandomUtil.getUUID();
		String redAddMan = userProFile.getStaffNo();
		apploanapprterm.setTermId(termId);
		apploanapprterm.setTermDesc(termdto.getTermDesc());
		apploanapprterm.setRedAddMan(redAddMan);
		apploanapprterm.setStat(CommonConstant.STAT_ENABLE);
		apploanapprterm.setJobTyp(termdto.getJobTyp());
		apploanapprterm.setInstDate(new Date());
		apploanapprterm.setUpdtDate(new Date());
		apploanapprtermservice.insert(apploanapprterm);
		}else{
		Map<String,Object> map =new HashMap<>();
		String redAddMan = userProFile.getStaffNo();
		map.put("termId",termdto.getTermId());
		map.put("jobTyp",termdto.getJobTyp());
		map.put("termDesc", termdto.getTermDesc());
		map.put("stat", termdto.getStat());
		map.put("redAddMan", redAddMan);
		map.put("instDate", termdto.getInstDate());
		map.put("updtDate", new Date());
		apploanapprtermservice.updateByPrimaryKeySelective(map);
		}
		
	}

	@Override
	public void updateApprTermStatDto(AppLoanApprTermDto termdto, UserProfile userProFile) throws ServiceException, AppException {
		Map<String,Object> map =new HashMap<>();
		String redAddMan = userProFile.getStaffNo();
		map.put("termId",termdto.getTermId());
		if(CommonConstant.STAT_ENABLE.equals(termdto.getStat())){
		map.put("stat", CommonConstant.STAT_DISABLE);
		}else if(CommonConstant.STAT_DISABLE.equals(termdto.getStat())){
		map.put("stat", CommonConstant.STAT_ENABLE);	
		}
		map.put("redAddMan",redAddMan);
		apploanapprtermservice.updateByPrimaryKeySelective(map);
	}

	@Override
	public Page<AppLoanApprTermDto> getApprTermPage(Page<AppLoanApprTermDto> page) throws ServiceException, AppException {
		Page<AppLoanApprTerm> pageRsl =  apploanapprtermservice.queryForPage(page.toPage(AppLoanApprTerm.class));
		return pageRsl.toPage(AppLoanApprTermDto.class);
	}


}
