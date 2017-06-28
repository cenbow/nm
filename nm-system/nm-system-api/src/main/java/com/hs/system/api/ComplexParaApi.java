package com.hs.system.api;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.entity.SysComplexPara ;

/**
 * SYS_复杂参数表 接口
 * @author autocreate
 * @create 2015-10-30
 */
public interface  ComplexParaApi{

	/**
	 * 分页查询参数列表
	 * @param page
	 * @return
	 */
	public Page<SysComplexPara> querySysComplexParas(Page<SysComplexPara> page) throws ServiceException,AppException;
	
	/**
	 * 通过ID获取参数对象
	 * @param id
	 * @return
	 */
	public SysComplexPara getSysComplexParaById(String id) throws ServiceException,AppException;
	
	/**
	 * 保存和更新复杂参数信息
	 * @param complexPara
	 */
	public void save(SysComplexPara complexPara) throws ServiceException,AppException ;
	
	/**
	 * 将参数设置为禁用
	 * @param id
	 */
	public void disabledSysComplexPara(String id) throws ServiceException,AppException;
	
	/**
	 * 将参数设置为启用
	 * @param id
	 */
	public void enableSysComplexPara(String id) throws ServiceException,AppException;
	/**
	 * 用过参数编码和参数类型获取参数对象
	 * @param id
	 */
	public SysComplexPara getSysComplexParaByTypeCode(String typeNo,String 	paraNo) throws ServiceException,AppException;
}