package com.hs.loan.cust.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.api.CustInfoExteApi;
import com.hs.loan.cust.dto.AppCustInfoExteDto;
import com.hs.loan.cust.entity.AppCustInfo;
import com.hs.loan.cust.entity.AppCustInfoExte;
import com.hs.loan.cust.service.AppCustInfoExteService;
import com.hs.loan.cust.service.AppCustInfoService;
import com.hs.utils.BeanUtils;

/**
 * 客户拓展信息Server
 * 
 * @author zhangxiaoqiang
 *
 */
@Service
@Transactional(readOnly = true)
public class CustInfoExteServer implements CustInfoExteApi {
	@Autowired
	private AppCustInfoExteService appCustInfoExteService;
	@Autowired
	private AppCustInfoService appCustInfoService;
	
	@Transactional
	public void save(AppCustInfoExteDto custInfoExte) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		AppCustInfo info=	appCustInfoService.getByNo(custInfoExte.getCustNo());
		if (info==null) {
			throw new AppException("未查询到该客户");
		}else {
			AppCustInfoExte appCustInfoExte = new AppCustInfoExte();
			BeanUtils.copyProperties(custInfoExte, appCustInfoExte);
			appCustInfoExte.setCustName(info.getCustName());
			appCustInfoExteService.insert(appCustInfoExte);
		}
	}

	@Transactional
	public void update(AppCustInfoExteDto custInfoExte) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		AppCustInfo info=	appCustInfoService.getByNo(custInfoExte.getCustNo());
		if (info==null) {
			throw new AppException("未查询到该客户");
		}else {
			Map<String, Object> upMap = null;
			upMap = BeanUtils.bean2mapExclude(custInfoExte, "exte8");
			upMap.put("exte8", custInfoExte.getExte8());
			upMap.put("exte9", custInfoExte.getExte9());
			upMap.put("exte10", custInfoExte.getExte10());
			appCustInfoExteService.updateByPrimaryKeySelective(upMap);
		}
	}

	@Transactional
	public void deleteInfoByNo(String custNo) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		if (appCustInfoExteService.getByPrimaryKey(custNo)!=null) {
			appCustInfoExteService.deleteByPrimaryKey(custNo);
		}else {
			throw new AppException("未查询到该客户的扩展信息");
		}
	}

	@Override
	public List<AppCustInfoExteDto> findAllByParam(Map<String, Object> params) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		List<AppCustInfoExte> listExte = appCustInfoExteService.queryForList(params);
		return BeanUtils.copyProperties(listExte, AppCustInfoExteDto.class);
	}

	@Override
	public AppCustInfoExteDto getByCustNo(String custNo) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		AppCustInfoExteDto custinfoDto = new AppCustInfoExteDto();
		AppCustInfoExte custInfo = appCustInfoExteService.getByPrimaryKey(custNo);
		if (custInfo!=null) {
			BeanUtils.copyProperties(custInfo, custinfoDto);
		}
		return custinfoDto;
	}

	@Override
	public Page<AppCustInfoExteDto> queryForPage(Page<AppCustInfoExteDto> page, UserProfile profile)
			throws ServiceException, AppException {
		// TODO Auto-generated method stub
		Page<AppCustInfoExte> pageExte = appCustInfoExteService.queryForPage(page.toPage(AppCustInfoExte.class));
		return pageExte.toPage(AppCustInfoExteDto.class);
	}

}
