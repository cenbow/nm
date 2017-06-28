package com.hs.loan.operation.api;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.operation.dto.LoanContractFileDto;

import java.util.HashMap;
import java.util.List;

/**
 * 模板 接口
 * @author 
 *
 */
public interface LoanContractApi {
	/**
	 * 信托文件是否导出
	 * @param map dataFormat 日期
	 * @return boolean(返回true导出，返回false没有数据)
	 * @throws ServiceException
	 * @throws AppException
	 */
	public boolean isDownEntr(java.util.Map map)throws ServiceException,AppException;
	/**
	 * 信托文件导出数据
	 * @param map dataFormat 日期
	 * @return List<HashMap<String,Object>>  fileName文件名 in输入流
	 * @throws ServiceException
	 * @throws AppException
	 */
	public List<HashMap<String,Object>> downEntr(java.util.Map map)throws ServiceException,AppException;
	public int insertAppEntr(String context)throws ServiceException, AppException;
	public Page<HashMap<String,Object>> getAppEntr(java.util.Map map);
	/**
	 * 保存或更新 模板信息
	 */
	public void save(LoanContractFileDto loanContractFileDto) throws ServiceException,AppException;
	 
	/**
	 * 通过fileId 删除模板基本信息 
	 * 删除代码模板信息
	 * @param branchNo
	 */
	public void deleteByNo(String fileId) throws ServiceException,AppException;

	/**
	 * 通过fileId获取模板基本信息
	 */
	public LoanContractFileDto getByNo(String fileId) throws ServiceException,AppException;

	/**
	 * 查询模板列表
	 */
	public List<LoanContractFileDto> getList(String chanlNo)  throws ServiceException,AppException;
	/**
	 * 查询模板信息
	 */
	public List<LoanContractFileDto> queryContractListByType(String fileTyp,String chanNo) throws ServiceException,AppException;
	
}
