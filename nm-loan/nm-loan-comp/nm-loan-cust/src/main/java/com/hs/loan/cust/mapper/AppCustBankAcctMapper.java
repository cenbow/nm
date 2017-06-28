package com.hs.loan.cust.mapper;

import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.entity.AppCustBankAcct;

/**
 * APP_客户银行账户信息 mapper
 * @author autocreate
 * @create 2015-10-27
 */
@MyBatisRepository
public interface  AppCustBankAcctMapper extends BaseMapper<AppCustBankAcct>{
    /**
     * 验证一个客户只能绑定一个银行帐号
     * @param appCustBankAcct
     * @return
     */
	public int selectExistsBankNoByCus(AppCustBankAcct appCustBankAcct);

	public void updateByCustNo(Map<String, Object> map);
}