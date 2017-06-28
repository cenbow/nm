package com.hs.loan.finance.server;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.api.ChanFstdateImportApi;
import com.hs.loan.finance.dto.AppChanFstdateImportDto;
import com.hs.loan.finance.entity.AppChanFstdateImport;
import com.hs.loan.finance.service.AppChanFstdateImportService;
import com.hs.utils.BeanUtils;

/**
 * 资方首次还款日导入
 * 
 * @author zhangxiaoqiang
 *
 */
@Service
@Transactional(readOnly = true)
public class AppChanFstdateImportServer implements ChanFstdateImportApi {
	@Autowired
	private AppChanFstdateImportService appChanFstdateImportService;

	@Override
	public Page<AppChanFstdateImportDto> queryListForPage(Page<AppChanFstdateImportDto> page)
			throws ServiceException, AppException {
		return appChanFstdateImportService.queryForPage(page.toPage(AppChanFstdateImport.class))
				.toPage(AppChanFstdateImportDto.class);
	}

	@Transactional
	public void insertList(List<AppChanFstdateImportDto> list) throws ServiceException, AppException {
		List<AppChanFstdateImport> importList = BeanUtils.copyProperties(list, AppChanFstdateImport.class);
		for (AppChanFstdateImport imp : importList) {
			if ((imp.getLoanNo() == null || imp.getLoanNo().equals(""))
					|| (imp.getFstDate() == null || imp.getFstDate().equals(""))) {
				throw new ServiceException("该批次的文件中存在贷款编号或首次还款日为空的记录！");
			}
			imp.setInstDate(new Date());
		}
		appChanFstdateImportService.insertList(importList);
	}

}
