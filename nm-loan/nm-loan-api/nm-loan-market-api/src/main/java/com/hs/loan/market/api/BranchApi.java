package com.hs.loan.market.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.market.dto.BranchDto;
import com.hs.loan.market.dto.StaffDto;

/**
 * 网点 接口
 * @author zwr
 *
 */
public interface BranchApi {
	/**
	 * 更新网点信息
	 * @param branchDto
	 */
	public void update(BranchDto branchDto);
	/**
	 * 保存或更新 网点信息
	 */
	public void save(BranchDto branchDto) throws ServiceException,AppException;
	
	/**
	 * 通过branchNo删除网点基本信息(逻辑删除),
	 * 同时删除网点与产品，网点与销售，网点与网点组，的关联关系
	 * @param branchNo
	 */
	public void rmvByNo(String branchNo) throws ServiceException,AppException;
	
	/**
	 * 通过branchNo 删除网点基本信息 (物理删除，过时的方法)
	 * 同时删除网点与产品，网点与销售，网点与网点组，的关联关系
	 * @param branchNo
	 */
	@Deprecated
	public void deleteByNo(String branchNo) throws ServiceException,AppException;

	/**
	 * 通过branchNo获取网点基本信息
	 */
	public BranchDto getByNo(String branchNo) throws ServiceException,AppException;

	/**
	 * 查询网点列表
	 */
	public List<BranchDto> getList(Map<String, Object> param)  throws ServiceException,AppException;
	
	/**
	 * 查询所有，包括逻辑删除的和未逻辑删除的都会查出
	 * @param param
	 * @return
	 */
	public List<BranchDto> getListAll(Map<String, Object> param) throws ServiceException,AppException;
	
	/**
	 * 分页查询网点
	 */
	public Page<BranchDto> queryBranch(Page<BranchDto> page)  throws ServiceException,AppException;
	
	/**
	 * 分页查询 网点 所有，是否被逻辑删除都会查询出来
	 * @param page
	 * @return
	 */
	public Page<BranchDto> queryBranchAll(Page<BranchDto> page) throws ServiceException,AppException;

	/**
	 * 通过branchNo获取网点下的销售lst
	 * 必须的参数 branchNo
	 */
	public List<StaffDto> getBranchSalerLst(Map<String,Object> param)  throws ServiceException,AppException;

	/**
	 * 分页查询网点下的销售
	 * 必须的参数：branchNo
	 */
	public Page<StaffDto> getBranchSalerPage(Page<StaffDto> page) throws ServiceException,AppException;
	
	/**
	 * 删除网点和销售的关系
	 */
	public void rmvBrhSalerRel(String branchNo, List<String> staffNoLst) throws ServiceException,AppException;
	
	/**
	 * 保存或者更新 网点和销售关系
	 * list中的branchNo是必须的且都相同。
	 * @param pubSalerBranch
	 */
	public void saveBrhSalerRel(String branchNo , List<String> staffNoLst) throws ServiceException,AppException;

	/**
	 * 获取销售绑定的有效的（没被逻辑删除的） 网点
	 * 
	 * @param staffNo
	 * @return
	 */
	public List<BranchDto> getSalerBindedBranchList(String staffNo) throws ServiceException,AppException;
	
	/**
	 * 获取销售绑定的有效的网点(内置)
	 * 
	 * @param staffNo
	 * @return
	 */
	public List<BranchDto>  getSalerBindedBranchList(String staffNo,List<String> branchNos) throws ServiceException,AppException;
	/**
	 * 获取销售绑定的有效的网点(内置)
	 * 
	 * @param staffNo
	 * @return
	 */
	public List<BranchDto>  getSalerBindedBranchList(String staffNo,String branchNo) throws ServiceException,AppException;
	
	
	/**
	 * 获取销售绑定的有效的网点(非内置)
	 * 
	 * @param staffNo
	 * @return
	 */
	public List<BranchDto> getSalerBindedBranchListNotInner(String staffNo) throws ServiceException,AppException;
	
	
	/**
	 * 分页查询没在当前网点下的销售
	 * 必须的参数branchNo
	 * 
	 * @param page
	 * @return
	 */
	public Page<StaffDto> queryNotInBrhSaler(Page<StaffDto> page) throws ServiceException,AppException;


	/**
	 * 查询网点组不包含的网点信息列表
	 * @param page
	 * @return
	 */
	public Page<BranchDto> queryBranchGroupNotExists(Page<BranchDto> page);

	/**
	 *
	 * @param page
	 * @param prodNo
	 * @return
	 */
	public Page<BranchDto> queryBranchListByProdNo(Page<BranchDto> page);
	/**
	 * 获取挂单商户状态失效条数
	 * @param branchName
	 * @return
	 */
	public List<Map<String, Object>> judgeBranchStatusByFaild(String branchName);
	
}
