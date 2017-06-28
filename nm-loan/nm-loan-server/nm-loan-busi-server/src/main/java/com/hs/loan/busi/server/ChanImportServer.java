package com.hs.loan.busi.server;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.busi.dto.AppChanImportDto;
import com.hs.loan.sale.api.ChanImportApi;
import com.hs.loan.sale.entity.AppChanImport;
import com.hs.loan.sale.service.AppChanImportService;
import com.hs.utils.BeanUtils;
@Service
@Transactional(readOnly=true)
public class ChanImportServer implements ChanImportApi{

	@Autowired
	private AppChanImportService appChanImportService;
	
	@Override
	@Transactional
	public void importAppChanImports(List<AppChanImportDto> chanImportDtos,
			UserProfile profile) throws ServiceException, AppException {
		AppChanImport appChanImport=null;
		for (int i = 0; i < chanImportDtos.size(); i++) {
			appChanImport=new AppChanImport();
			BeanUtils.copyPropertiesNotForce(appChanImport, chanImportDtos.get(i));
			appChanImport.setInstDate(new Date());
			appChanImportService.insert(appChanImport);
		}
		
		
		
		
		
	}

}
