package com.hs.loan.market.api;

import java.util.List;
import java.util.Map;

import com.hs.base.entity.Page;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.market.dto.BranchDto;
import com.hs.loan.market.dto.BranchGroupDto;
import com.hs.loan.market.dto.BranchGroupInfoDto;

/**
 * 网点组 接口
 * @author zwr
 *
 */
public interface BranchGroupInfoApi {

	/**
	 * 保存或更新 网点组基本信息
	 */
	public void save(BranchGroupInfoDto branchGroupInfoDto) throws ServiceException,AppException;
	
	/**
	 * 保存或跟新 网点组及包含的网点关联信息
	 * 根据网点组的id判断是更新还是保存
	 * 
	 */
	public void saveBrhGrp(BranchGroupDto branchGroupDto, List<String> branchNoLst) throws ServiceException,AppException;
	/**
	 * 通过网点组编码 获取 网点组的基本信息
	 */
	public BranchGroupInfoDto getByNo(String groupNo) throws ServiceException,AppException;
	
	/**
	 * 查询网点组list
	 * @param param
	 * @return
	 */
	public List<BranchGroupInfoDto> getList(Map<String,Object> param) throws ServiceException,AppException;
	
	/**
	 * 分页查询网点组
	 * @param page
	 * @return
	 */
	public Page<BranchGroupInfoDto> queryBrhGrp(Page<BranchGroupInfoDto> page) throws ServiceException,AppException;
	
	/**
	 * 获取网点组包含的 网点 list
	 * @param groupNo
	 * @return
	 */
	public List<BranchDto> getBranchLst(String groupNo) throws ServiceException,AppException;
	
	/**
	 * 分页查询 网点组包含的 网点
	 * 参数必须包含groupNo
	 * @param groupNo
	 * @return
	 */
	public Page<BranchDto> queryBranchPage(Page<BranchDto> page) throws ServiceException,AppException;

	/**
	 * 通过groupNo删除网点组，同时会删除网点组和网点的关联关系
	 */
	public void deleteByNo(String groupNo)  throws ServiceException,AppException;
	
	/**
	 * 移除网点组和网点的关系
	 * @param pubBranchGroup
	 */
	public void rmvBrhGrpRel(String groupNo,List<String> branchNoLst ) throws ServiceException,AppException;
	
	/**
	 * 保存或更新 网点组和网点的关系
	 * list里的groupNo是必须的，且没项的groupNo应该且必须是相同的
	 * @param pubBranchGroupLst
	 */
	public void saveBrhGrpRel(String groupNo, List<String> branchNoLst) throws ServiceException,AppException;
	
	/**
	 * 分页查询 未在该网点组 下的网点
	 * 必须的参数 groupNo
	 * 
	 * @param page
	 * @return
	 */
	public Page<BranchDto> queryNotInGrpBrhPage(Page<BranchDto> page) throws ServiceException,AppException;
}
