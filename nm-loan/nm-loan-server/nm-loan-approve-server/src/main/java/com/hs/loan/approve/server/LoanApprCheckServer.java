package com.hs.loan.approve.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.CommonConstant;
import com.hs.loan.approvcheck.dto.AppApprstaffGroupDto;
import com.hs.loan.approvcheck.dto.AppApprstaffGroupdetalDto;
import com.hs.loan.approvcheck.dto.AppLoanApprCheckDto;
import com.hs.loan.approvcheck.dto.AppLoanApprCheckHDto;
import com.hs.loan.approve.api.LoanApprCheckApi;
import com.hs.loan.approvecheck.bo.AppLoanApprCheckBo;
import com.hs.loan.approvecheck.bo.AppLoanApprCheckHBo;
import com.hs.loan.approvecheck.entity.AppApprstaffGroup;
import com.hs.loan.approvecheck.entity.AppApprstaffGroupH;
import com.hs.loan.approvecheck.entity.AppApprstaffGroupdetal;
import com.hs.loan.approvecheck.service.AppApprstaffGroupHService;
import com.hs.loan.approvecheck.service.AppApprstaffGroupService;
import com.hs.loan.approvecheck.service.AppApprstaffGroupdetalService;
import com.hs.loan.approvecheck.service.AppLoanApprCheckHService;
import com.hs.loan.approvecheck.service.AppLoanApprCheckService;
import com.hs.utils.BeanUtils;

/**
 * 
 * 内部审核
 * @author lenovo
 * 
 */
@Service
@Transactional(readOnly=true)
public class LoanApprCheckServer implements LoanApprCheckApi
{
	@Autowired
	private AppApprstaffGroupService appApprstaffGroupService;
	@Autowired
	private AppApprstaffGroupdetalService appApprstaffGroupdetalService;
	@Autowired
	private AppApprstaffGroupHService appApprstaffGroupHService;
	@Autowired
	private AppLoanApprCheckService appLoanApprCheckService;
	@Autowired
	private AppLoanApprCheckHService appLoanApprCheckHService;
	
	private static final Logger logger = LoggerFactory.getLogger(LoanApprCheckServer.class);
	
	
	/**
	 * 查询审核组
	 * @param page
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 */
	public Page<AppApprstaffGroupDto> queryApprStaffGroupList(Page<AppApprstaffGroupDto> page, UserProfile userProfile)
	{
		if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
	 	Page<AppApprstaffGroup> restpage = appApprstaffGroupService.queryForPage(page.toPage(AppApprstaffGroup.class));
		
		return restpage.toPage(AppApprstaffGroupDto.class);
	}
	
	/**
	 * 新增审核组
	 * @param appApprstaffGroupDto
	 * @param userProfile
	 * @throws ServiceException
	 */
	@Transactional
	public void addApprStaffGroup(AppApprstaffGroupDto appApprstaffGroupDto, UserProfile userProfile)
	{
		if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
		if(StringUtils.isBlank(appApprstaffGroupDto.getGroupName()))
		{
			throw new ServiceException("审批组名称不能为空");
		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("groupName", appApprstaffGroupDto.getGroupName());
		List<AppApprstaffGroup> apprstaffGroupList = appApprstaffGroupService.queryForList(param);
		if(!apprstaffGroupList.isEmpty())
		{
			throw new ServiceException("审批组名称已经存在");
		}
		AppApprstaffGroup appApprstaffGroup = new AppApprstaffGroup();
		appApprstaffGroup.setGroupName(appApprstaffGroupDto.getGroupName());
		appApprstaffGroup.setGroupId(com.hs.utils.RandomUtil.getUUID());
		appApprstaffGroup.setOperateNo(userProfile.getStaffNo());
		appApprstaffGroup.setOperateDate(new java.util.Date());
		if(StringUtils.isBlank(appApprstaffGroupDto.getStat()))
		{
			appApprstaffGroup.setStat(com.hs.commons.constants.CommonConstant.STAT_ENABLE);
		}
		else
		{
			appApprstaffGroup.setStat(appApprstaffGroupDto.getStat());
		}
		appApprstaffGroupService.insert(appApprstaffGroup);
	}
	
	/**
	 * 批量删除审核组
	 * @param appApprstaffGroupDto
	 * @param userProfile
	 * @throws ServiceException
	 */
	@Transactional
	public void batchDelApprStaffGroupByLstId(List<String> list, UserProfile userProfile)
	{
		if(userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
		if(list == null || list.isEmpty())
		{
			throw new ServiceException("删除审核组GroupId不能为空");
		}
		appApprstaffGroupService.batchDeleteByPrimaryKey(list);
		appApprstaffGroupdetalService.batchDeleteByGroupId(list);
	}
	
	/**
	 * 修改审核组
	 * @param appApprstaffGroupDto
	 * @param userProfile
	 */
	@Transactional
	public void modifyApprStaffGroupById(AppApprstaffGroupDto appApprstaffGroupDto, UserProfile userProfile)
	{
		if(userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
		if(StringUtils.isBlank(appApprstaffGroupDto.getGroupId()))
		{
			throw new ServiceException("审批组groupId不能为空");
		}
		AppApprstaffGroup appApprstaffGroup = new AppApprstaffGroup();
		com.hs.utils.BeanUtils.copyPropertiesNotForce(appApprstaffGroup, appApprstaffGroupDto);
		appApprstaffGroup.setOperateNo(userProfile.getStaffNo());
		Map<String,Object> param = BeanUtils.bean2map(appApprstaffGroup);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		param.put("operateDate", sdf.format(new java.util.Date()));
		appApprstaffGroupService.updateByPrimaryKeySelective(param);
	}
	
	/**
	 * 查询审批内部组人员
	 * @param page
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 */
	public Page<AppApprstaffGroupdetalDto> queryApprstaffGroupdetalList(Page<AppApprstaffGroupdetalDto> page, UserProfile userProfile)
	{
		if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
	 	Page<AppApprstaffGroupdetal> restpage = appApprstaffGroupdetalService.queryForPage(page.toPage(AppApprstaffGroupdetal.class));
		
		return  restpage.toPage(AppApprstaffGroupdetalDto.class);
	}
	
	/**
	 * 批量新增审批内部组人员
	 * @param apprstaffGroupDto
	 * @param apprstaffGroupdetalDtoList
	 * @param userProfile
	 */
	@Transactional
	public void batchAddApprstaffGroupdetal(AppApprstaffGroupDto apprstaffGroupDto, List<AppApprstaffGroupdetalDto> apprstaffGroupdetalDtoList, UserProfile userProfile)
	{
		if(userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
		if(StringUtils.isBlank(apprstaffGroupDto.getGroupId()))
		{
			throw new ServiceException("审批组号不能为空");
		}
		if(StringUtils.isBlank(apprstaffGroupDto.getGroupName()))
		{
			throw new ServiceException("审批组名称不能为空");
		}
		if(apprstaffGroupdetalDtoList == null || apprstaffGroupdetalDtoList.isEmpty())
		{
			throw new ServiceException("新增审批内部组人员不能为空");
		}
		
		AppApprstaffGroupdetalDto apprstaffGroupdetalDto = null;
		for(int i = 0;i < apprstaffGroupdetalDtoList.size();i++)
		{
			apprstaffGroupdetalDto = apprstaffGroupdetalDtoList.get(i);
			if(StringUtils.isBlank(apprstaffGroupdetalDto.getStaffNo()))
			{
				throw new ServiceException("新增审批内部组人员编号不能为空");
			}
			if(StringUtils.isBlank(apprstaffGroupdetalDto.getStaffName()))
			{
				throw new ServiceException("新增审批内部组人员名称不能为空");
			}
			if(StringUtils.isBlank(apprstaffGroupdetalDto.getStaffLevl()))
			{
				throw new ServiceException("新增审批内部组人员等级不能为空");
			}
			if("40201004".equals(apprstaffGroupdetalDto.getStaffLevl()))
			{
				if(apprstaffGroupdetalDtoList.size() > 1)
				{
					throw new ServiceException("新增审批内部组主管只能是添加一个");
				}
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("groupId", apprstaffGroupDto.getGroupId());
				param.put("staffLevl", apprstaffGroupdetalDto.getStaffLevl());
				List<AppApprstaffGroupdetal> apprstaffGroupdetalList = appApprstaffGroupdetalService.queryForList(param);
				if(apprstaffGroupdetalList.size() > 0)
				{
					throw new ServiceException("新增审批内部组主管只能是添加一个");
				}
			}
		}
		AppApprstaffGroup apprstaffGroup = appApprstaffGroupService.getByPrimaryKey(apprstaffGroupDto.getGroupId());
		if(apprstaffGroup != null && CommonConstant.STAT_DISABLE.equals(apprstaffGroup.getStat()))
		{
			throw new ServiceException("审批组已经失效");
		}
		List<AppApprstaffGroupdetal> apprstaffGroupdetalList = new ArrayList<AppApprstaffGroupdetal>();
		List<AppApprstaffGroupH> apprstaffGroupHList = new ArrayList<AppApprstaffGroupH>();
		AppApprstaffGroupdetal apprstaffGroupdetal = null;
		AppApprstaffGroupH apprstaffGroupH = null;
		for(int i = 0; i < apprstaffGroupdetalDtoList.size(); i++)
		{
			apprstaffGroupdetalDto = apprstaffGroupdetalDtoList.get(i);
			apprstaffGroupdetal = new AppApprstaffGroupdetal();
			apprstaffGroupH = new AppApprstaffGroupH();
			apprstaffGroupdetal.setId(com.hs.utils.RandomUtil.getUUID());
			apprstaffGroupdetal.setGroupId(apprstaffGroupDto.getGroupId());
			apprstaffGroupdetal.setGroupName(apprstaffGroupDto.getGroupName());
			apprstaffGroupdetal.setStaffNo(apprstaffGroupdetalDto.getStaffNo());
			apprstaffGroupdetal.setStaffName(apprstaffGroupdetalDto.getStaffName());
			apprstaffGroupdetal.setStaffLevl(apprstaffGroupdetalDto.getStaffLevl());
			apprstaffGroupdetal.setOperateNo(userProfile.getStaffName());
			apprstaffGroupdetal.setOperateDate(new java.util.Date());
			BeanUtils.copyPropertiesNotForce(apprstaffGroupH, apprstaffGroupdetal);
			
			apprstaffGroupH.setBeginDt(new java.util.Date());
			apprstaffGroupH.setStat(com.hs.commons.constants.CommonConstant.STAT_ENABLE);
			
			apprstaffGroupdetalList.add(apprstaffGroupdetal);
			apprstaffGroupHList.add(apprstaffGroupH);
		}
		appApprstaffGroupdetalService.batchInsert(apprstaffGroupdetalList);
		appApprstaffGroupHService.batchInsert(apprstaffGroupHList);
	}
	
	/**
	 * 批量删除审批内部组人员
	 * @param list
	 * @param userProfile
	 * @throws ServiceException
	 */
	@Transactional
	public void batchDelApprstaffGroupdetalByLstId(List<String> list, UserProfile userProfile)
	{
		if(userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
		if(list == null || list.isEmpty())
		{
			throw new ServiceException("审批内部组人员不能为空");
		}
		appApprstaffGroupdetalService.batchDeleteByPrimaryKey(list);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("stat", com.hs.commons.constants.CommonConstant.STAT_DISABLE);
		params.put("endDt", sdf.format(new java.util.Date()));
		params.put("list", list);
		appApprstaffGroupHService.batchModifyByPrimaryKeySelective(params);
	}
	
	/**
	 * 查询不在审批内部组人员
	 * @param page
	 * @param userProfile
	 * @return AppApprstaffGroupdetalDto 返回 staffNo、staffName 参数 
	 */
	public Page<AppApprstaffGroupdetalDto> queryNotApprstaffGroupdetalForPage(Page<AppApprstaffGroupdetalDto> page, UserProfile userProfile)
	{
    	if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
    	page.getPageParams().put("status",CommonConstant.STAT_ENABLE);
    	Page<AppApprstaffGroupdetal> restpage = appApprstaffGroupdetalService.queryNotApprstaffGroupdetalList(page.toPage(AppApprstaffGroupdetal.class));
    	return restpage.toPage(AppApprstaffGroupdetalDto.class);
	}
	
	/**
	 * 查询所有的复核历史
	 * @param page
	 * @param userProfile
	 * @return
	 * @throws ServiceException
	 */
	public Page<AppLoanApprCheckHDto> queryApprCheckHList(Page<AppLoanApprCheckHDto> page, UserProfile userProfile)
	{
		if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
	 	Page<AppLoanApprCheckHBo> restpage = appLoanApprCheckHService.queryForPageTwo(page.toPage(AppLoanApprCheckHBo.class));
		
		return restpage.toPage(AppLoanApprCheckHDto.class);
	}
	
	/**
	 * 查询所有的复核记录
	 * @param page
	 * @param userProfile
	 * @return
	 */
	public Page<AppLoanApprCheckDto> queryApprCheckList(Page<AppLoanApprCheckDto> page, UserProfile userProfile)
	{
		if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
		Page<AppLoanApprCheckBo> restpage = appLoanApprCheckService.queryForPageTwo(page.toPage(AppLoanApprCheckBo.class));
		return restpage.toPage(AppLoanApprCheckDto.class);
	}
	
	/**
	 * 查询主管是否在线
	 * 
	 */
	public String queryManagerStateByStaffNo(UserProfile userProfile)
	{
		if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
		Map<String,String> manageInfo = appLoanApprCheckService.getManagerNoByStaffNo(userProfile.getStaffNo());
		if(manageInfo == null)
		{
			throw new ServiceException("该员工组无主管，请先添加");
		}
		String status = appLoanApprCheckService.getGroupTypeByStaffNo(manageInfo.get("staffNo"));
		
		return status;
	}
	
}
