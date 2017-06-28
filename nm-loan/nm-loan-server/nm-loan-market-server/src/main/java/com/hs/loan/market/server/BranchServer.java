package com.hs.loan.market.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.hs.commons.constants.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.loan.market.api.BranchApi;
import com.hs.loan.market.bo.PubBranchBo;
import com.hs.loan.market.dto.BranchDto;
import com.hs.loan.market.dto.StaffDto;
import com.hs.loan.market.entity.PubBranch;
import com.hs.loan.market.service.PubBranchService;
import com.hs.loan.prod.service.PubProdStrService;
import com.hs.system.area.PubSysRegionalBelongService;
import com.hs.system.entity.SysStaff;
import com.hs.utils.BeanUtils;
import com.hs.utils.SimpleCodeUtils;

/**
 * 网点 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly=true)
public class BranchServer implements BranchApi{
	@Autowired
	private PubBranchService pubBranchService;
	@Autowired
	private PubProdStrService pubProdStrService;
	
	@Autowired
	private PubSysRegionalBelongService regionalBelongService;	//区县
	/**
	 * 更新网点信息
	 * @param branchDto
	 */
	@Transactional
	public void update(BranchDto branchDto){
		PubBranch pubBranch = new PubBranch();
		BeanUtils.copyProperties(branchDto, pubBranch);
		//状态设置为正常
		pubBranch.setStat(CommonConstant.STAT_ENABLE);
		pubBranchService.update(pubBranch);
	}
	/**
	 * 保存网点信息
	 */
	@Transactional
	public void save(BranchDto branchDto){
		PubBranch pubBranch = new PubBranch();
		BeanUtils.copyProperties(branchDto, pubBranch);
		pubBranchService.save(pubBranch);
	}
	
	/**
	 * 通过branchNo删除网点基本信息,（逻辑删除）
	 * 
	 * @param branchNo
	 */
	@Transactional
	public void rmvByNo(String branchNo){
		if(pubProdStrService.queryExistRelaProdAndbranchNo(branchNo)){
			pubBranchService.rmvByNo(branchNo);
			return;
		}
		throw new ServiceException("该网点已关联产品不可删除！");
	}

	/**
	 * 通过branchNo 删除网点基本信息 (物理删除)
	 * 同时删除网点与产品，网点与销售，网点与网点组，的关联关系
	 * @param branchNo
	 */
	@Transactional
	@Deprecated
	public void deleteByNo(String branchNo){
		if(pubProdStrService.queryExistRelaProdAndbranchNo(branchNo)){
			pubBranchService.deleteByNo(branchNo);
			return;
		}
		throw new ServiceException("该网点已关联产品不可删除！");
	}
	
	/**
	 * 通过branchNo获取网点基本信息
	 */
	public BranchDto getByNo(String branchNo) {
		PubBranch pubBranch = pubBranchService.getByNo(branchNo);
		BranchDto branchDto =(BranchDto) BeanUtils.copyPropertiesNotNull( new BranchDto(), pubBranch);
		setBranchAddr(branchDto);
		return branchDto;
	}

	/**
	 * 查询网点列表
	 */
	public List<BranchDto> getList(Map<String, Object> param) {
		List<BranchDto> dtoLst =  BeanUtils.copyProperties(pubBranchService.getList(param), BranchDto.class);
		for(BranchDto dto : dtoLst){
			setBranchAddr(dto);
		}
		return dtoLst;
	}
	
	/**
	 * 查询所有，包括逻辑删除的和未逻辑删除的都会查出
	 * @param param
	 * @return
	 */
	public List<BranchDto> getListAll(Map<String, Object> param){
		List<BranchDto> dtoLst = BeanUtils.copyProperties(pubBranchService.getListAll(param),BranchDto.class);
		for(BranchDto dto : dtoLst){
			setBranchAddr(dto);
		}
		return dtoLst;
	}

	/**
	 * 查询网点组不包含的网点信息列表
	 * @param page
	 * @return
	 */
	public Page<BranchDto> queryBranchGroupNotExists(Page<BranchDto> page){
		page = pubBranchService.queryBranchGroupNotExists(page.toPage(PubBranchBo.class)).toPage(BranchDto.class);
		return page;
	}

	/**
	 * 通过产品编号查询未与产品关联的网点列表
	 * @param page
	 * @param prodNo
	 * @return
	 */
	public Page<BranchDto> queryBranchListByProdNo(Page<BranchDto> page){
		List<BranchDto> dtoLst = page.getList();
		for(BranchDto dto : dtoLst){
			setBranchAddr(dto);
		}
		Page<BranchDto> page1 = pubBranchService.queryBranchListByProdNo(page.toPage(PubBranchBo.class)).toPage(BranchDto.class);
		return page1;
	}

	/**
	 * 分页查询网点
	 */
	public Page<BranchDto> queryBranch(Page<BranchDto> page) {
		page = pubBranchService.queryBranchList(page.toPage(PubBranchBo.class)).toPage(BranchDto.class);
		List<BranchDto> dtoLst = page.getList();
		for(BranchDto dto : dtoLst){
			setBranchAddr(dto);
		}
		return page;
	}
	
	/**
	 * 分页查询 网点 所有，是否被逻辑删除都会查询出来
	 * @param page
	 * @return
	 */
	public Page<BranchDto> queryBranchAll(Page<BranchDto> page) {
		page = pubBranchService.queryBranchAll(page.toPage(PubBranch.class)).toPage(BranchDto.class);
		List<BranchDto> dtoLst = page.getList();
		for(BranchDto dto : dtoLst){
			setBranchAddr(dto);
		}
		return page;
	}

	/**
	 * 保存或更新 网点及其关联的销售的关联关系
	 */
	@Transactional
	public void saveBranchSaler(BranchDto branchDto,List<String> staffNoLst ) {
		PubBranch pubBranch = new PubBranch();
		BeanUtils.copyProperties(branchDto, pubBranch);
		pubBranchService.saveBranchSaler(pubBranch, staffNoLst);
	}
	
	/**
	 * 通过branchNo获取网点下的销售lst
	 * 必须的参数branchNo,
	 */
	public List<StaffDto> getBranchSalerLst(Map<String,Object> param) {
		return BeanUtils.copyProperties(pubBranchService.getBranchSalerLst(param),StaffDto.class);
	}

	/**
	 * 分页查询网点下的销售
	 * 必须的参数：branchNo
	 */
	public Page<StaffDto> getBranchSalerPage(Page<StaffDto> page) {
		return pubBranchService.getBranchSalerPage(page.toPage(SysStaff.class)).toPage(StaffDto.class);
	}
	
	/**
	 * 删除网点和销售的关系
	 */
	@Transactional
	public void rmvBrhSalerRel(String branchNo, List<String> staffNoLst){
		pubBranchService.rmvBrhSalerRel(branchNo,staffNoLst);
	}
	
	/**
	 * 保存或者更新 网点和销售关系
	 * list中的branchNo是必须的且都相同。
	 * @param pubSalerBranch
	 */
	@Transactional
	public void saveBrhSalerRel(String branchNo , List<String> staffNoLst){
		pubBranchService.saveBrhSalerRel(branchNo ,staffNoLst);
	}
	
	/**
	 * 获取销售绑定的有效的（没被逻辑删除的） 网点
	 * 
	 * @param staffNo
	 * @return
	 */
	public List<BranchDto> getSalerBindedBranchList(String staffNo){
		List<BranchDto> dtoLst = BeanUtils.copyProperties(pubBranchService.getSalerBindedBranchList(staffNo), BranchDto.class);
		for(BranchDto dto : dtoLst){
			setBranchAddr(dto);
		}
		return dtoLst;
	}
	
	/**
	 * 获取销售绑定的有效的（没被逻辑删除的） 网点内置
	 * 
	 * @param staffNo
	 * @return
	 */
	public List<BranchDto> getSalerBindedBranchList(String staffNo,List<String> branchNos){
		List<BranchDto> dtoLst = BeanUtils.copyProperties(pubBranchService.getSalerBindedBranchList(staffNo,branchNos), BranchDto.class);
		for(BranchDto dto : dtoLst){
			setBranchAddr(dto);
		}
		return dtoLst;
	}
	
	/**
	 * 获取销售绑定的有效的（没被逻辑删除的） 网点内置
	 * 
	 * @param staffNo
	 * @return
	 */
	public List<BranchDto> getSalerBindedBranchList(String staffNo,String branchNo){
		List<String> branchNos = new ArrayList<String>();
		branchNos.add(branchNo);
		List<BranchDto> dtoLst = BeanUtils.copyProperties(pubBranchService.getSalerBindedBranchList(staffNo,branchNos), BranchDto.class);
		for(BranchDto dto : dtoLst){
			setBranchAddr(dto);
		}
		return dtoLst;
	}
	
	
	/**
	 * 获取销售绑定的有效的（没被逻辑删除的） 网点非内置
	 * 
	 * @param staffNo
	 * @return
	 */
	public List<BranchDto> getSalerBindedBranchListNotInner(String staffNo){
		List<BranchDto> dtoLst = BeanUtils.copyProperties(pubBranchService.getSalerBindedBranchListNotInner(staffNo), BranchDto.class);
		for(BranchDto dto : dtoLst){
			setBranchAddr(dto);
		}
		return dtoLst;
	}
	
	/**
	 * 分页查询没在当前网点下的销售
	 * 必须的参数branchNo
	 * 
	 * @param page
	 * @return
	 */
	public Page<StaffDto> queryNotInBrhSaler(Page<StaffDto> page){
		return pubBranchService.queryNotInBrhSaler(page.toPage(SysStaff.class)).toPage(StaffDto.class);
	}
	
	/**
	 * 设置网点机构和地址信息
	 * @param BranchDto
	 */
	public void setBranchAddr(BranchDto dto){
		if(dto==null) return;
		
		dto.setBranchProvName(regionalBelongService.getProvName(dto.getBranchProv()));
		dto.setBranchCityName(regionalBelongService.getCityName(dto.getBranchCity()));
		dto.setBranchAreaName(regionalBelongService.getCountName(dto.getBranchArea()));
		
		dto.setOpenOrgName(SimpleCodeUtils.getNameByCode(dto.getOpenOrg()));
	}
	/**
	 * 获取挂单商户失效条数
	 */
	@Override
	public List<Map<String, Object>> judgeBranchStatusByFaild(String branchName) {
		// TODO Auto-generated method stub
		return pubBranchService.judgeBranchStatusByFaild(branchName);
	}
	
}
