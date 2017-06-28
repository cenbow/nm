package com.hs.loan.approve.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.approve.entity.AppApprStaffGroup;
import com.hs.loan.approve.entity.AppApprStaffSign;
import com.hs.loan.approve.mapper.AppApprStaffGroupMapper;
import com.hs.loan.approve.mapper.AppApprStaffSignMapper;
import com.hs.loan.approvecheck.mapper.AppLoanApprCheckMapper;
import com.hs.utils.RandomUtil;

/**
 * APP_审批人员签到明细 业务处理
 * @author autocreate
 * @create 2015-11-23
 */
@Service
@Transactional(readOnly=true)
public class  AppApprStaffSignService{
	@Autowired
	private AppApprStaffSignMapper appApprStaffSignMapper;
	@Autowired
	private AppApprStaffGroupMapper apprStaffGroupMapper;
	@Autowired
	private AppLoanApprCheckMapper appLoanApprCheckMapper;
	/**
	 * 根据人员编号更新审批人员状态(审批中)
	 * @param staffNo 人员编号
	 * @return
	 */
	@Transactional
	public int updateApprStateByStaffNo(String staffNo){
		return  apprStaffGroupMapper.updateApprStateByStaffNo(staffNo);
	}
	/**
	 * 查询审批人员当前的审批状态
	 * @param staffNo 人员编号
	 * @return
	 */
	public Integer getApprStateByStaffNo(String staffNo){
		return apprStaffGroupMapper.getApprStateByStaffNo(staffNo);
	}
	/**
	 * 新增 APP_审批人员签到明细
	 * @param appApprStaffSign 新增对象
	 */
	@Transactional
	private void insert(AppApprStaffSign appApprStaffSign){
		appApprStaffSignMapper.insert(appApprStaffSign);
	}

	/**
	 * 通过主键修改 APP_审批人员签到明细
	 * @param map 修改参数Map
	 */
	@Transactional
	private void updateByPrimaryKeySelective(Map<String, Object> map){
		appApprStaffSignMapper.updateByPrimaryKeySelective(map);
	}


	@Transactional
	public void updateStaffOnLine(UserProfile profile){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("staffNo",profile.getStaffNo());
		//param.put("roleNos", profile.getRoleNoSet());
		//param.put("orgNo", profile.getOrgNo());
		List<AppApprStaffGroup> staffGrpList = apprStaffGroupMapper.queryForList(param);//查询当前人员与审批组信息
		//param.put("stat", PubBusinessConstant.SIGNTYPE_ONLINE);//在离线状态
		//apprStaffGroupMapper.updateStaffGrpStat(param);
		if(CollectionUtils.isNotEmpty(staffGrpList)){
			for(AppApprStaffGroup staffGrp:staffGrpList){
				AppApprStaffSign staffSign = new AppApprStaffSign();
				staffSign.setGroupNo(staffGrp.getGroupNo());
				staffSign.setStaffNo(profile.getStaffNo());
				staffSign.setSignTyp(PubBusinessConstant.SIGNTYPE_ONLINE);
				staffSign.setId(RandomUtil.getUUID());
				staffSign.setInstDate(new Date());
				staffSign.setSignDate(new Date());
				this.insert(staffSign);
			}
		}
	}
	
	@Transactional
	public void updateStaffOffLine(UserProfile profile){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("staffNo",profile.getStaffNo());
		/*param.put("roleNos", profile.getRoleNoSet());
		param.put("orgNo", profile.getOrgNo());*/
		List<AppApprStaffGroup> staffGrpList = apprStaffGroupMapper.queryForList(param);//查询当前人员与审批组信息
		
		/** modify by huangjian 20161123 begin */
		Map<String,String> manageInfo = appLoanApprCheckMapper.getManagerNoByStaffNo(profile.getStaffNo());
		if(manageInfo == null)
		{
			throw new ServiceException("该员工组无主管，请先添加");
		}
		String staffNo = manageInfo.get("staffNo");
		//非主管
		if(profile.getStaffNo() != null && !profile.getStaffNo().equals(staffNo))
		{
			Integer num = appLoanApprCheckMapper.getCountByStaffNoTwo(profile.getStaffNo());
			if(num > 0)
			{
				throw new ServiceException("还有任务未审核,不能下线");
			}
		}
		//param.put("stat", PubBusinessConstant.SIGNTYPE_OFFLINE);//在离线状态
		//apprStaffGroupMapper.updateStaffGrpStat(param);
		if(CollectionUtils.isNotEmpty(staffGrpList)){
			for(AppApprStaffGroup staffGrp:staffGrpList){
				//if(PubBusinessConstant.SIGNTYPE_APPROVING.equals(staffGrp.getApprStat())){
					//throw new ServiceException("该任务在审批中,不能下线");
				//}
				AppApprStaffSign staffSign = new AppApprStaffSign();
				staffSign.setGroupNo(staffGrp.getGroupNo());
				staffSign.setStaffNo(profile.getStaffNo());
				staffSign.setSignTyp(PubBusinessConstant.SIGNTYPE_OFFLINE);
				staffSign.setId(RandomUtil.getUUID());
				staffSign.setInstDate(new Date());
				staffSign.setSignDate(new Date());
				this.insert(staffSign);
			}
		}
		/** modify by huangjian 20161123 end */
	}
	
	/**
	 * 通过主键删除 APP_审批人员签到明细
	 * @param primaryKey 主键
	 */
	@Transactional
	private void deleteByPrimaryKey(String primaryKey){
		appApprStaffSignMapper.deleteByPrimaryKey(primaryKey);
	}

	/**
	 * 获取审批人状态
	 * @param staffNo
	 * @return
	 */
	public String getStaffStat(String staffNo){
		return appApprStaffSignMapper.getStaffStat(staffNo);
	}

	/**
	 * 通过主键取得 APP_审批人员签到明细 对象
	 * @param primaryKey 主键
	 * @return APP_审批人员签到明细对象
	 */
	private AppApprStaffSign getByPrimaryKey(String primaryKey){
		return appApprStaffSignMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_审批人员签到明细 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	private List<AppApprStaffSign> queryForList(Map<String, Object> param){
		return appApprStaffSignMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_审批人员签到明细 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	private Page<AppApprStaffSign> queryForPage(Page<AppApprStaffSign> page){
		appApprStaffSignMapper.queryForList(page.getPageParams());
		return (Page<AppApprStaffSign>)page.getPageParams().get(Page.KEY);
	}
}