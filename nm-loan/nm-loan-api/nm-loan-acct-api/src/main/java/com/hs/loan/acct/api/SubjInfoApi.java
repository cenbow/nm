package com.hs.loan.acct.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.acct.dto.SubjInfoDto;


/**
 * PUB_科目信息 接口
 * @author autocreate
 * @create 2015-10-15
 */
public interface  SubjInfoApi{

	/**
	 * 通过id获取一个有效的科目信息
	 * @param subjId
	 * @return
	 */
	public SubjInfoDto getById(String subjId) throws ServiceException,AppException;
	
	/**
	 * 获取有效的 科目信息列表
	 * @param param
	 * @return
	 */
	public List<SubjInfoDto> getList(Map<String,Object> param) throws ServiceException,AppException;
	
	/**
	 * 分页查询有效的 科目信息
	 * @param page
	 * @return
	 */
	public Page<SubjInfoDto> querySubjInfo(Page<SubjInfoDto> page) throws ServiceException,AppException;
	
	
}