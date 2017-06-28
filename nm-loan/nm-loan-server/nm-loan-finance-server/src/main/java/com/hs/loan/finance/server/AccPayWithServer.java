package com.hs.loan.finance.server;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.finance.api.AccPayWithApi;
import com.hs.loan.finance.bo.RetItemBo;
import com.hs.loan.finance.dto.AccPayWithDto;
import com.hs.loan.finance.dto.RetItemDto;
import com.hs.loan.finance.entity.AccPayWith;
import com.hs.loan.finance.service.AccPayWithService;
import com.hs.utils.BeanUtils;

/**
 * 代扣文件服务实现
 * @author hwen
 *
 */
@Service
@Transactional(readOnly = true)
public class AccPayWithServer implements AccPayWithApi {
	
	@Autowired
	private AccPayWithService accPayWithService;

	/**
	 * 查询分期代扣信息
	 * @param page
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Override
	public Page<AccPayWithDto> queryLoanPayWithInfo(Page<AccPayWithDto> page)
			throws ServiceException, AppException {
		return accPayWithService.queryForPage(page.toPage(AccPayWith.class)).toPage(AccPayWithDto.class);
	}
	
	

	/**
	 * 批量导出代扣文件 返回文件url地址
	 */
	@Override
	@Transactional
	public String batchDkFileExport(Map<String, Object> params) throws ServiceException {
		String fileUrl = accPayWithService.executeBatchDkFileExport(params);
		if(StringUtils.isEmpty(fileUrl)){
			throw new ServiceException("代扣文件生成失败");
		}
		return fileUrl;
	}

	/**
	 * 回盘文件导入
	 */
	@Transactional
	public void executeBatchDkFileImport(String fileName,List<RetItemDto> lst,UserProfile user) throws ServiceException {
		List<RetItemBo> boLst =  BeanUtils.copyProperties(lst, RetItemBo.class);
		accPayWithService.executeBatchDkFileImport(fileName,boLst,user);
	}
	
	
}
