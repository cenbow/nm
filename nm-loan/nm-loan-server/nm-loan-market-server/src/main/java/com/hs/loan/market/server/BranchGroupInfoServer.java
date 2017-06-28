package com.hs.loan.market.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.loan.market.api.BranchGroupInfoApi;
import com.hs.loan.market.dto.BranchDto;
import com.hs.loan.market.dto.BranchGroupDto;
import com.hs.loan.market.dto.BranchGroupInfoDto;
import com.hs.loan.market.entity.PubBranch;
import com.hs.loan.market.entity.PubBranchGroupInfo;
import com.hs.loan.market.service.PubBranchGroupInfoService;
import com.hs.utils.BeanUtils;


/**
 * 网点组 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly=true)
public class BranchGroupInfoServer implements BranchGroupInfoApi {

	@Autowired
	private PubBranchGroupInfoService pubBranchGroupInfoService;
	
	/**
	 * 保存或更新 网点组基本信息
	 */
	@Transactional
	public void save(BranchGroupInfoDto branchGroupInfoDto) throws ServiceException{
		PubBranchGroupInfo pubBranchGroupInfo = new PubBranchGroupInfo();
		BeanUtils.copyProperties(branchGroupInfoDto, pubBranchGroupInfo);
		pubBranchGroupInfoService.save(pubBranchGroupInfo);
	}
	
	/**
	 * 保存或跟新 网点组及包含的网点关联信息
	 * 根据网点组的id判断是更新还是保存
	 * 
	 */
	@Transactional
	public void saveBrhGrp(BranchGroupDto branchGroupDto, List<String> branchNoLst){
		PubBranchGroupInfo pubBranchGroupInfo = new PubBranchGroupInfo();
		BeanUtils.copyProperties(branchGroupDto, pubBranchGroupInfo);
		pubBranchGroupInfoService.saveBrhGrp(pubBranchGroupInfo, branchNoLst);
	}
	
	/**
	 * 通过网点组编码 获取 网点组的基本信息
	 */
	public BranchGroupInfoDto getByNo(String groupNo){
		BranchGroupInfoDto branchGroupInfoDto = new BranchGroupInfoDto();
		PubBranchGroupInfo pubBranchGroupInfo = pubBranchGroupInfoService.getByNo(groupNo);
		BeanUtils.copyProperties(pubBranchGroupInfo, branchGroupInfoDto);
		return branchGroupInfoDto;
	}
	
	/**
	 * 查询网点组list
	 * @param param
	 * @return
	 */
	public List<BranchGroupInfoDto> getList(Map<String,Object> param){
		return BeanUtils.copyProperties(pubBranchGroupInfoService.getList(param),BranchGroupInfoDto.class);
	}
	
	/**
	 * 分页查询网点组
	 * @param page
	 * @return
	 */
	public Page<BranchGroupInfoDto> queryBrhGrp(Page<BranchGroupInfoDto> page){
		return pubBranchGroupInfoService.queryBrhGrp(page.toPage(PubBranchGroupInfo.class)).toPage(BranchGroupInfoDto.class);
	}
	
	/**
	 * 获取网点组包含的 网点 list
	 * @param groupNo
	 * @return
	 */
	public List<BranchDto> getBranchLst(String groupNo){
		return BeanUtils.copyProperties(pubBranchGroupInfoService.getBranchLst(groupNo),BranchDto.class);
	}
	
	/**
	 * 分页查询 网点组包含的 网点
	 * 参数必须包含groupNo
	 * @param groupNo
	 * @return
	 */
	public Page<BranchDto> queryBranchPage(Page<BranchDto> page){
		return pubBranchGroupInfoService.queryBranchPage(page.toPage(PubBranch.class)).toPage(BranchDto.class);
	}

	/**
	 * 分页查询 未在该网点组 下的网点
	 * 必须的参数 groupNo
	 * 
	 * @param page
	 * @return
	 */
	public Page<BranchDto> queryNotInGrpBrhPage(Page<BranchDto> page){
		return pubBranchGroupInfoService.queryNotInGrpBrhPage(page.toPage(PubBranch.class)).toPage(BranchDto.class);
	}
	
	/**
	 * 通过groupNo删除网点组，同时会删除网点组和网点的关联关系
	 */
	@Transactional
	public void deleteByNo(String groupNo) {
		pubBranchGroupInfoService.deleteByNo(groupNo);
	}
	
	/**
	 * 移除网点组和网点的关系
	 * @param pubBranchGroup
	 */
	@Transactional
	public void rmvBrhGrpRel(String groupNo,List<String> branchNoLst ){
		pubBranchGroupInfoService.rmvBrhGrpRel(groupNo,branchNoLst);
	}
	
	/**
	 * 保存或更新 网点组和网点的关系
	 * list里的groupNo是必须的，且没项的groupNo应该且必须是相同的
	 * @param pubBranchGroupLst
	 */
	@Transactional
	public void saveBrhGrpRel(String groupNo, List<String> branchNoLst){
		pubBranchGroupInfoService.saveBrhGrpRel(groupNo,branchNoLst);
	}

	
	
}
