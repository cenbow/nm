package com.hs.loan.approve.server;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.approv.dto.ApprGroupDto;
import com.hs.loan.approv.dto.ApprStaffGroupDto;
import com.hs.loan.approve.api.ApprGroupApi;
import com.hs.loan.approve.contant.ApproveContant;
import com.hs.loan.approve.entity.AppApprGroup;
import com.hs.loan.approve.entity.AppApprGroupGoods;
import com.hs.loan.approve.entity.AppApprStaffGroup;
import com.hs.loan.approve.service.AppApprGroupGoodsService;
import com.hs.loan.approve.service.AppApprGroupService;
import com.hs.loan.approve.service.AppApprStaffGroupService;
import com.hs.utils.RandomUtil;

@Service
@Transactional(readOnly=true)
public class ApprGroupServer implements ApprGroupApi{
	@Autowired
	private AppApprGroupService apprGrpService;
	@Autowired
	private AppApprStaffGroupService staffGrpService;
	@Autowired
	private AppApprGroupGoodsService apprGroupGoodsService;

	@Override
	public Page<ApprGroupDto> queryApprGroupLst(Page<ApprGroupDto> page, UserProfile userProfile)
			throws ServiceException, AppException {
		Page<AppApprGroup> pageObj = apprGrpService.queryApprGrpForPage(page.toPage(AppApprGroup.class), userProfile);
		return pageObj.toPage(ApprGroupDto.class);
	}

	@Override
	@Transactional
	public void saveApprGroup(ApprGroupDto apprGroupDto, UserProfile userProfile)
			throws ServiceException, AppException, IllegalAccessException, InvocationTargetException {
		AppApprGroup parmObj = new AppApprGroup();
		//BeanUtils.copyProperties(parmObj, apprGroupDto);
		parmObj.setGroupName(apprGroupDto.getGroupName());
		parmObj.setGroupNo(apprGroupDto.getGroupNo());
		parmObj.setProdTyp(apprGroupDto.getProdTyp());
		parmObj.setBranchProv(apprGroupDto.getBranchProv());
		parmObj.setBranchCity(apprGroupDto.getBranchCity());
		parmObj.setBranchArea(apprGroupDto.getBranchArea());
		if(StringUtils.isEmpty(apprGroupDto.getGroupNo())){
			parmObj.setGroupNo(RandomUtil.getUUID());
			parmObj.setInstDate(new Date());
			parmObj.setStat(ApproveContant.AVAILABLESTAT_VALID);
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("groupNameVal", apprGroupDto.getGroupName());
			param.put("stat", ApproveContant.AVAILABLESTAT_VALID);
			
			List<AppApprGroup> list = (List<AppApprGroup>)apprGrpService.queryForList(param);
			if(list != null && list.size() > 0){
				throw new ServiceException("审批组["+apprGroupDto.getGroupName()+"]已存在!");
			}
			apprGrpService.insert(parmObj);
			
			
			/**添加商品类型*/
			AppApprGroupGoods appApprGroupGoods=null;
			String[] goodstypes=apprGroupDto.getGoodsTypes().split(",");
			for (int i = 0; i < goodstypes.length; i++) {
				appApprGroupGoods=new AppApprGroupGoods();
				appApprGroupGoods.setId(RandomUtil.getUUID());
				appApprGroupGoods.setInstDate(new Date());
				appApprGroupGoods.setGroupNo(parmObj.getGroupNo());
				appApprGroupGoods.setGoodsType(goodstypes[i]);
				apprGroupGoodsService.insert(appApprGroupGoods);
			}
			
			
		}else{
			parmObj.setUpdtDate(new Date());
			apprGrpService.updateGrpByGrpNo(parmObj);
		}	
	}

	@Override
	@Transactional
	public void removeApprGroup(String groupNo) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		Map param = new HashMap();
		param.put("groupNo", groupNo);
		param.put("stat", ApproveContant.AVAILABLESTAT_VALID);
		List<AppApprStaffGroup> rsList = staffGrpService.queryForList(param);
		if(!rsList.isEmpty()){
			throw new ServiceException("该审批组下还有人员,不能删除!");
		}
		apprGrpService.deleteByPrimaryKey(groupNo);
		
		/**删除商品类型关联表*/
		apprGroupGoodsService.deleteByGroupNo(groupNo);
		
	}

	@Override
	public Page<ApprStaffGroupDto> queryApprGroupStaffLst(Page<ApprGroupDto> page, UserProfile userProfile)
			throws ServiceException, AppException {
		Page<AppApprStaffGroup> pageRsl = staffGrpService.queryApprStaffGrpForPage(page.toPage(AppApprStaffGroup.class), userProfile);
		return pageRsl.toPage(ApprStaffGroupDto.class);
	}

	@Override
	@Transactional
	public void saveApprGroupStaff(List<ApprStaffGroupDto> apprStaffGroupList,String groupNo,UserProfile userProfile)
			throws ServiceException, AppException, IllegalAccessException, InvocationTargetException {
		// TODO Auto-generated method stub
		if(CollectionUtils.isNotEmpty(apprStaffGroupList)){
			for(ApprStaffGroupDto apprStaffGroupDto:apprStaffGroupList){
				AppApprStaffGroup parmObj = new AppApprStaffGroup();
				//BeanUtils.copyProperties(parmObj, apprStaffGroupDto);
				AppApprGroup apprGrp = apprGrpService.getByPrimaryKey(groupNo);
				parmObj.setGroupName(apprGrp.getGroupName());
				parmObj.setApprStat(PubBusinessConstant.SIGNTYPE_WAITING);
				parmObj.setGroupNo(groupNo);
				parmObj.setId(apprStaffGroupDto.getId());
				parmObj.setStaffName(apprStaffGroupDto.getStaffName());
				parmObj.setStaffNo(apprStaffGroupDto.getStaffNo());
				Map<String,Object> staffGrpMap = new HashMap<String,Object>();
				staffGrpMap.put("staffNo", apprStaffGroupDto.getStaffNo());
				staffGrpMap.put("groupNo", groupNo);
				 List<AppApprStaffGroup> existList = staffGrpService.queryForList(staffGrpMap);
				if(!existList.isEmpty()){
					continue;
				} 
				if(StringUtils.isEmpty(apprStaffGroupDto.getId())){
					parmObj.setId(RandomUtil.getUUID());
					parmObj.setInstDate(new Date());
					parmObj.setStat(PubBusinessConstant.SIGNTYPE_OFFLINE);
					staffGrpService.insert(parmObj);
				}else{
					parmObj.setUpdtDate(new Date());
					staffGrpService.updateByApprStaffGrp(parmObj);
				}
			}
		}
	}

	@Override
	@Transactional
	public void removeApprGroupStaff(String groupNo, String staffNo) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		if(StringUtils.isNotEmpty(groupNo) && StringUtils.isNotEmpty(staffNo)){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("groupNo", groupNo);
			param.put("staffNo", staffNo);
			staffGrpService.deleteByCont(param);
		}
	}
	
}