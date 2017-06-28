package com.hs.loan.busi.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.ServiceException;
import com.hs.loan.busi.dto.LoanAttInDto;
import com.hs.loan.sale.api.LoanAttApi;
import com.hs.loan.sale.entity.AppLoanAtt;
import com.hs.loan.sale.service.AppLoanAttService;
import com.hs.system.code.CodeService;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.StringUtils;

/**
 * 分期附件信息
 * @author IT-009
 *
 */
@Service
@Transactional(readOnly=true)
public class LoanAttServer implements LoanAttApi{

	@Autowired
	private AppLoanAttService appLoanAttService;
	
	@Autowired
	private CodeService codeService;
	
	@Override
	@Transactional
	public void saveLoanAttInfo(LoanAttInDto inDto, UserProfile profile) {
		if(inDto ==null){
			throw new ServiceException("参数为空");
		}
		if(StringUtils.isEmpty(inDto.getAttNo())){
			throw new ServiceException("附件编号为空");
		}
		if(StringUtils.isEmpty(inDto.getLoanNo())){
			throw new ServiceException("分期编号为空");
		}
		if(StringUtils.isEmpty(inDto.getCustNo())){
			throw new ServiceException("客户号为空");
		}
		if(StringUtils.isEmpty(inDto.getAttTyp())){
			throw new ServiceException("附件类型为空");
		}
		
		this.deleteLoanAtt(inDto.getLoanNo(), inDto.getAttTyp());
		
		AppLoanAtt appLoanAtt = new AppLoanAtt();
		 BeanUtils.copyPropertiesNotForce(appLoanAtt, inDto);
		 String attFileName=inDto.getAttFile();
		 if(!StringUtils.isEmpty(attFileName)){
			 String attFile = attFileName.split("\\?")[0];
			 appLoanAtt.setAttFile(attFile);
		 }
		 appLoanAtt.setInstDate(DateUtils.getCurrentDate());
		 appLoanAtt.setInstUser(profile.getStaffNo());
		 appLoanAtt.setInstOrg(profile.getOrgNo());
		appLoanAttService.insert(appLoanAtt);
	}

	@Override
	@Transactional
	public void deleteLoanAtt(String loanNo,String attType) {
		 
		if(StringUtils.isEmpty(loanNo)){
			throw new ServiceException("分期编号为空");
		}
		if(StringUtils.isEmpty(attType)){
			throw new ServiceException("附件类型为空");
		}
		Map<String,String> map = new java.util.HashMap<>();
		map.put("loanNo", loanNo);
		map.put("attTyp", attType);
		appLoanAttService.deleteByPrimaryKey(map);
	}

	@Override
	public List<LoanAttInDto> queryLoanAttInfo(String loanNo) {
		if(StringUtils.isEmpty(loanNo)){
			throw new ServiceException("分期编号为空");
		}
		Map<String,Object> map = new java.util.HashMap<>();
		map.put("loanNo", loanNo);
		List<LoanAttInDto> retlist= new ArrayList<>();
		List<AppLoanAtt> list= appLoanAttService.queryForList(map);
		for (AppLoanAtt appLoanAtt : list) {
			LoanAttInDto attInDto = new LoanAttInDto();
			BeanUtils.copyPropertiesNotForce(attInDto, appLoanAtt);
			retlist.add(attInDto);
		}
		return retlist;
	}

	
	@Override
	public LoanAttInDto queryLoanAtt(String loanNo,String attType) {
		if(StringUtils.isEmpty(loanNo)){
			throw new ServiceException("分期编号为空");
		}
		if(StringUtils.isEmpty(attType)){
			throw new ServiceException("附件类型为空");
		}
		Map<String,Object> map = new java.util.HashMap<>();
		map.put("loanNo", loanNo);
		map.put("attTyp", attType);
		List<AppLoanAtt> list= appLoanAttService.queryForList(map);
		LoanAttInDto dto = new LoanAttInDto();
		if(list !=null && list.size() > 0){
			BeanUtils.copyPropertiesNotForce(dto, list.get(0));
		}
		return dto;
	}
	
	@Override
	public List<LoanAttInDto> queryLoanAttByGroupCod(String loanNo, String groupCodStrs) {
		return queryLoanAttByGroupCod(loanNo, "fileType", groupCodStrs);
	}

	@Override
	public List<LoanAttInDto> queryLoanAttByGroupCod(String loanNo, String codTyp, String groupCodStrs) {
		if(StringUtils.isEmpty(loanNo)){
			throw new ServiceException("分期编号为空");
		}
		if(StringUtils.isEmpty(codTyp)){
			throw new ServiceException("附件码类为空");
		}
		if(StringUtils.isEmpty(groupCodStrs)){
			throw new ServiceException("附件码组为空");
		}
		
		List<String> groupCods = new ArrayList<>();
		String[] arr = groupCodStrs.split(",");
		for (String groupCod : arr) {
			if (StringUtils.isBlank(groupCod)) continue;
			groupCods.add(groupCod.trim());
		}
		
		
		Map<String,Object> map = new java.util.HashMap<>();
		map.put("loanNo", loanNo);
		map.put("codTyp", codTyp);
		map.put("groupCods", groupCods);
		
		List<LoanAttInDto> retlist= new ArrayList<>();
		List<AppLoanAtt> list= appLoanAttService.queryLoanAttByGroupCod(map);
		for (AppLoanAtt appLoanAtt : list) {
			LoanAttInDto attInDto = new LoanAttInDto();
			BeanUtils.copyPropertiesNotForce(attInDto, appLoanAtt);
			retlist.add(attInDto);
		}
		
		return retlist;
	}
	@Override
	public List<LoanAttInDto> queryLoanAttByGroupCodFilter(String loanNo, String groupCodStrs) {
		return queryLoanAttByGroupCodFilter(loanNo, "fileType", groupCodStrs);
	}
	@Override
	public List<LoanAttInDto> queryLoanAttByGroupCodFilter(String loanNo, String codTyp, String groupCodStrs) {
		if(StringUtils.isEmpty(loanNo)){
			throw new ServiceException("分期编号为空");
		}
		if(StringUtils.isEmpty(codTyp)){
			throw new ServiceException("附件码类为空");
		}
		if(StringUtils.isEmpty(groupCodStrs)){
			throw new ServiceException("附件码组为空");
		}
		
		List<String> groupCods = new ArrayList<>();
		String[] arr = groupCodStrs.split(",");
		for (String groupCod : arr) {
			if (StringUtils.isBlank(groupCod)) continue;
			groupCods.add(groupCod.trim());
		}
		
		if (groupCods.size() == 0) throw new ServiceException("附件码组无效");
		
		Map<String,Object> map = new java.util.HashMap<>();
		map.put("loanNo", loanNo);
		map.put("codTyp", codTyp);
		map.put("groupCodFilters", groupCods);
		
		List<LoanAttInDto> retlist= new ArrayList<>();
		List<AppLoanAtt> list= appLoanAttService.queryLoanAttByGroupCod(map);
		for (AppLoanAtt appLoanAtt : list) {
			LoanAttInDto attInDto = new LoanAttInDto();
			BeanUtils.copyPropertiesNotForce(attInDto, appLoanAtt);
			retlist.add(attInDto);
		}
		
		return retlist;
	}
	
	
}
