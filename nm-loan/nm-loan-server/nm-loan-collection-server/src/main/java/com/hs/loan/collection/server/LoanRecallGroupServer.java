package com.hs.loan.collection.server;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.collection.api.RecallGroupApi;
import com.hs.loan.collection.dto.AppRecallGroupDto;
import com.hs.loan.collection.dto.RecallStaffGroupDto;
import com.hs.loan.collection.entity.AppRecallGroup;
import com.hs.loan.collection.entity.PlRecallStaffGroup;
import com.hs.loan.collection.service.AppRecallGroupService;
import com.hs.loan.collection.service.PlRecallStaffGroupService;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;

/**
 * 催收分组server
 * @author IT-009
 *
 */
@Service
@Transactional(readOnly=true)
public  class LoanRecallGroupServer implements RecallGroupApi{
	@Autowired
	private AppRecallGroupService recallGrpService;
	@Autowired
	private PlRecallStaffGroupService recallStaffGrpService;

	/**
	 * 分页查询催收组对象
	 * @param page
	 * @param userProfile
	 * @return
	 * @throws com.hs.base.exception.ServiceException
	 * @throws AppException
	 */
	@Override
	public Page<AppRecallGroupDto> queryRecallGroupLst(Page<AppRecallGroupDto> page, UserProfile userProfile)
			throws com.hs.base.exception.ServiceException, AppException {
		Page<AppRecallGroup> pageRsl = recallGrpService.queryForPermissionPage(page.toPage(AppRecallGroup.class),userProfile);
		return pageRsl.toPage(AppRecallGroupDto.class);
	}

	/**
	 * 保存或更改催收组信息
	 * @param recallGroupDto
	 * @param userProfile
	 * @throws com.hs.base.exception.ServiceException
	 * @throws AppException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Override
	@Transactional
	public void saveRecallGroup(AppRecallGroupDto recallGroupDto, UserProfile userProfile)
			throws com.hs.base.exception.ServiceException, AppException, IllegalAccessException,
			InvocationTargetException {
		if(StringUtils.isEmpty(recallGroupDto.getGroupNo())){
			//新增催收组查询是否存在相同名字的催收组
			Integer selectExistsByName = recallGrpService.selectExistsByName(recallGroupDto.getGroupName().trim());
			if(selectExistsByName>0){
				throw new ServiceException("该催收组已经存在");
			}
			//组编号 uuid
			String groupNo = RandomUtil.getUUID();
			//催收组实体
			AppRecallGroup recallGrp = new AppRecallGroup();
			//设置催收组编号
			recallGrp.setGroupNo(groupNo);
			//催收组状态
			recallGrp.setStat(recallGroupDto.getStat());
			//设置新增催收组时间
			recallGrp.setInstDate(DateUtils.getCurrentDate());
			//设置催收组名称
			recallGrp.setGroupName(recallGroupDto.getGroupName());
			recallGrpService.insert(recallGrp);
		}else{
			AppRecallGroup recallGrp = new AppRecallGroup();
			recallGrp.setGroupNo(recallGroupDto.getGroupNo());
			recallGrp.setGroupName(recallGroupDto.getGroupName());
			recallGrp.setStat(recallGroupDto.getStat());
			recallGrp.setUpdtDate(DateUtils.getCurrentDate());
			recallGrpService.updateByPrimaryKeySelective(BeanUtils.bean2map(recallGrp));
		}
	}

	/**
	 * 根据催收组编号删除催收组
	 * @param groupNo 催收组号
	 * @throws com.hs.base.exception.ServiceException
	 * @throws AppException 该组下有催收人员,不能删除
	 */
	@Override
	@Transactional
	public void removeRecallGroup(String groupNo) throws com.hs.base.exception.ServiceException, AppException {
		//query grp man
		Map<String,Object> param = new HashMap<String,Object>();
		//催收组号
		param.put("groupNo", groupNo);
		//查询该催收组下是否有催收组成员，如果有则不能删除
		List<PlRecallStaffGroup> jgList = recallStaffGrpService.queryForList(param);
		if(CollectionUtils.isNotEmpty(jgList)){
			throw new ServiceException("该组下有催收人员,不能删除");
		}
		recallGrpService.deleteByPrimaryKey(groupNo);
	}

	/**
	 * 查询 PL_催收人员与组关联信息 分页列表
	 * @param page
	 * @param userProfile
	 * @return
	 * @throws com.hs.base.exception.ServiceException
	 * @throws AppException
	 */
	@Override
	public Page<RecallStaffGroupDto> queryRecallGroupStaffLst(Page<RecallStaffGroupDto> page, UserProfile userProfile)
			throws com.hs.base.exception.ServiceException, AppException {
		Page<PlRecallStaffGroup> pageRsl = recallStaffGrpService.queryForPermissionPage(page.toPage(PlRecallStaffGroup.class), userProfile);
		return pageRsl.toPage(RecallStaffGroupDto.class);
	}

	/**
	 * 向催收组添加催收人员
	 * @param RecallStaffGroupList  PL_催收人员与组关联信息 对象集合
	 * @param groupNo 催收组编号
	 * @param userProfile 用户信息
	 * @throws com.hs.base.exception.ServiceException
	 * @throws AppException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Override
	@Transactional
	public void saveRecallGroupStaff(List<RecallStaffGroupDto> RecallStaffGroupList, String groupNo,
			UserProfile userProfile) throws com.hs.base.exception.ServiceException, AppException,
					IllegalAccessException, InvocationTargetException {
		//通过主键查询该催收组
		AppRecallGroup recallGrp = recallGrpService.getByPrimaryKey(groupNo);
		//验证该催收组是否已经失效
		if(recallGrp!=null && CommonConstant.STAT_DISABLE.equals(recallGrp.getStat())){
			throw new ServiceException("该群已失效，不能向该群添加催收人员!");
		}
		//判断人员与组关联信息对象集合是否为空
        if(RecallStaffGroupList.isEmpty()){
			throw new ServiceException("请添加催收人员");
		}
		//查询该催收组下所有的催收人员
		List<PlRecallStaffGroup> plRecallStaffGroups = recallStaffGrpService.queryListBygroupNo(groupNo);
		//判断是否为空
		if(null!=plRecallStaffGroups&&0<plRecallStaffGroups.size()){
			for (int i = 0; i < plRecallStaffGroups.size(); i++) {
				for (int j = 0; j < RecallStaffGroupList.size(); j++) {
					if(StringUtils.equals(RecallStaffGroupList.get(j).getStaffNo(),plRecallStaffGroups.get(i).getStaffNo())){
						RecallStaffGroupList.remove(j);
					}
				}
			}
			if(null!=RecallStaffGroupList){
				//催收组名称
				String grpName = recallGrp.getGroupName();
				for(RecallStaffGroupDto stfGrpDto:RecallStaffGroupList){
						//PL_催收人员与组关联信息 对象
						PlRecallStaffGroup stfGrp = new PlRecallStaffGroup();
						//主键
						stfGrp.setId(RandomUtil.getUUID());
						//催收组编号
						stfGrp.setGroupNo(groupNo);
						//催收组名称
						stfGrp.setGroupName(grpName);
						//人员编号
						stfGrp.setStaffName(stfGrpDto.getStaffName());
						//人员名称
						stfGrp.setStaffNo(stfGrpDto.getStaffNo());
						//加入时间
						stfGrp.setInstDate(DateUtils.getCurrentDate());
						//状态
						stfGrp.setStat(CommonConstant.STAT_ENABLE);
						recallStaffGrpService.insert(stfGrp);
				}
			}
		}else{
			//催收组名称
			String grpName = recallGrp.getGroupName();
			for(RecallStaffGroupDto stfGrpDto:RecallStaffGroupList){
				if(StringUtils.isEmpty(stfGrpDto.getId())){
					//PL_催收人员与组关联信息 对象
					PlRecallStaffGroup stfGrp = new PlRecallStaffGroup();
					//主键
					stfGrp.setId(RandomUtil.getUUID());
					//催收组编号
					stfGrp.setGroupNo(groupNo);
					//催收组名称
					stfGrp.setGroupName(grpName);
					//人员编号
					stfGrp.setStaffName(stfGrpDto.getStaffName());
					//人员名称
					stfGrp.setStaffNo(stfGrpDto.getStaffNo());
					//加入时间
					stfGrp.setInstDate(DateUtils.getCurrentDate());
					//状态
					stfGrp.setStat(CommonConstant.STAT_ENABLE);
					recallStaffGrpService.insert(stfGrp);
				}else{
					PlRecallStaffGroup stfGrp = new PlRecallStaffGroup();
					stfGrp.setId(stfGrpDto.getId());
					stfGrp.setGroupNo(groupNo);
					stfGrp.setGroupName(stfGrpDto.getGroupName());
					stfGrp.setStaffName(stfGrpDto.getStaffName());
					stfGrp.setStaffNo(stfGrpDto.getStaffNo());
					stfGrp.setUpdtDate(DateUtils.getCurrentDate());
					stfGrp.setStat(CommonConstant.STAT_ENABLE);
					recallStaffGrpService.updateByPrimaryKeySelective(BeanUtils.bean2map(stfGrp));
				}
			}
		}
	}

	/**
	 * 通过主键删除 PL_催收人员与组关联信息
	 * @param groupNo 催收组编号
	 * @param staffNo 人员编号
	 * @throws com.hs.base.exception.ServiceException
	 * @throws AppException
	 */
	@Override
	@Transactional
	public void removeRecallGroupStaff(String groupNo, String staffNo)
			throws com.hs.base.exception.ServiceException, AppException {
		recallStaffGrpService.deleteByCont(staffNo, groupNo);
	}

	 
}
