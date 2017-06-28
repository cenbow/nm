package com.hs.loan.cust.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.loan.cust.api.CustCallRegisterApi;
import com.hs.loan.cust.dto.CustCallRegisterDto;
import com.hs.loan.cust.entity.AppCustCallRegister;
import com.hs.loan.cust.service.AppCustCallRegisterService;
import com.hs.utils.BeanUtils;

/**
 * 客户来电记录 服务
 * 
 * @author zwr
 *
 */
@Service
@Transactional(readOnly = true)
public class CustCallRegisterServer implements CustCallRegisterApi {

	@Autowired
	private AppCustCallRegisterService appCustCallRegisterService;

	@Override
	public List<CustCallRegisterDto> getCustCallRegisterLst(String custNo) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 分页查询 客户来电记录 按时间倒序排序 必须的参数 custNo
	 * 
	 * @param page
	 * @return
	 */
	@Override
	public Page<CustCallRegisterDto> queryCustCallRegister(Page<CustCallRegisterDto> page) {
		return appCustCallRegisterService.queryCustCallRegister(page.toPage(AppCustCallRegister.class))
				.toPage(CustCallRegisterDto.class);
	}

	/**
	 * 新增 客户来电记录
	 * 
	 * @param custCallRegisterDto
	 */
	@Transactional
	public void insert(CustCallRegisterDto custCallRegisterDto) {
		AppCustCallRegister appCustCallRegister = new AppCustCallRegister();
		BeanUtils.copyProperties(custCallRegisterDto, appCustCallRegister);
		appCustCallRegisterService.insert(appCustCallRegister);
	}
}
