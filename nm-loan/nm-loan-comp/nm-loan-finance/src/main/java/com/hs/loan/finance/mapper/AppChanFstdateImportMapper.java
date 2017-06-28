package com.hs.loan.finance.mapper;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.finance.entity.AppChanFstdateImport;

/**
 *  mapper
 * @author autocreate
 * @create 2016-09-26
 */
@MyBatisRepository
public interface  AppChanFstdateImportMapper extends BaseMapper<AppChanFstdateImport>{
	/**
	 * 调用存储过程
	 */
	void callDcFlow();
	
}