package com.hs.loan.busi.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

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
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.author.entity.AppLoanBranchspcailpriBranchH;
import com.hs.loan.author.entity.AppLoanSpcailpriBranch;
import com.hs.loan.author.entity.AppLoanSpcailpriInfo;
import com.hs.loan.author.entity.AppLoanSpcailpriSaler;
import com.hs.loan.author.entity.AppLoanSpcailpriSalerH;
import com.hs.loan.author.service.AppLoanBranchspcailpriBranchHService;
import com.hs.loan.author.service.AppLoanSpcailpriBranchService;
import com.hs.loan.author.service.AppLoanSpcailpriInfoService;
import com.hs.loan.author.service.AppLoanSpcailpriSalerHService;
import com.hs.loan.author.service.AppLoanSpcailpriSalerService;
import com.hs.loan.busi.dto.AppLoanSpcailpriBranchDto;
import com.hs.loan.busi.dto.AppLoanSpcailpriInfoDto;
import com.hs.loan.busi.dto.AppLoanSpcailpriSalerDto;
import com.hs.loan.pub.hand.service.AppLoanHandService;
import com.hs.loan.sale.api.SalerAuthorApi;
import com.hs.loan.sale.bo.LoanListOutBo;
import com.hs.loan.sale.entity.AppLoanAcct;
import com.hs.loan.sale.service.AppLoanAcctService;
import com.hs.system.index.service.PubIndexService;
import com.hs.system.util.OpLogUtil;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;

/**
 * 直通车服务类
 * 
 * @author huangj
 * @create 2016-10-27
 */
@Service
@Transactional(readOnly = true)
public class SalerAuthorServer implements SalerAuthorApi 
{
	@Resource
	private AppLoanBranchspcailpriBranchHService appLoanBranchspcailpriBranchHService;
	@Resource
	private AppLoanSpcailpriBranchService appLoanSpcailpriBranchService;
	@Resource
	private AppLoanSpcailpriInfoService appLoanSpcailpriInfoService;
	@Resource
	private AppLoanSpcailpriSalerHService appLoanSpcailpriSalerHService;
	@Resource
	private AppLoanSpcailpriSalerService appLoanSpcailpriSalerService;
	@Resource
	private AppLoanAcctService appLoanAcctService;
	@Autowired
	private PubIndexService pubIndexService;
	@Autowired
	private AppLoanHandService appLoanHandService;
	
	private static final Logger logger = LoggerFactory.getLogger(SalerAuthorServer.class);
	/**
	 * 商户直通车权限查询
	 * @param param
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public Page<AppLoanSpcailpriBranchDto> queryBranchAuthorList(Page<AppLoanSpcailpriBranchDto> page, UserProfile userProfile) throws ServiceException 
	{
		if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
		Page<AppLoanSpcailpriBranch> restpage = appLoanSpcailpriBranchService.queryForPage(page.toPage(AppLoanSpcailpriBranch.class));
		return restpage.toPage(AppLoanSpcailpriBranchDto.class);
	}

	/**
	 * 商户直通车权限新增
	 * @param inDto
	 * @param profile
	 * @throws ServiceException
	 */
	@Override
	@Transactional
	public void addBranchAuthor(List<AppLoanSpcailpriBranchDto> list, UserProfile userProfile) throws ServiceException 
	{
		if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
		if(list == null || list.isEmpty())
		{
			throw new ServiceException("新增不能为空");
		}
		AppLoanSpcailpriBranchDto appLoanSpcailpriBranchDto = null;
		AppLoanSpcailpriBranch appLoanSpcailpriBranch = null;
		List<AppLoanSpcailpriBranch> appLoanSpcailpriBranchlist = new ArrayList<AppLoanSpcailpriBranch>();
		for(int i = 0; i < list.size(); i++)
		{
			appLoanSpcailpriBranchDto = list.get(i);
			appLoanSpcailpriBranch = new AppLoanSpcailpriBranch();
			appLoanSpcailpriBranch.setBranchNo(appLoanSpcailpriBranchDto.getBranchNo());
			appLoanSpcailpriBranch.setBranchName(appLoanSpcailpriBranchDto.getBranchName());
			appLoanSpcailpriBranch.setHandPerson(userProfile.getStaffNo());
			appLoanSpcailpriBranch.setInstDate(DateUtils.getCurrentDate());
			appLoanSpcailpriBranch.setOrgNo(userProfile.getOrgNo());
			appLoanSpcailpriBranchlist.add(appLoanSpcailpriBranch);
		}
		appLoanSpcailpriBranchService.addbatchInsert(appLoanSpcailpriBranchlist);
	}

	/**
	 * 商户直通车权限删除
	 * @param id
	 * @param profile
	 * @throws ServiceException
	 */
	@Override
	@Transactional
	public void delBranchAuthor(String branchNo, UserProfile userProfile) throws ServiceException 
	{
		if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
		if(StringUtils.isEmpty(branchNo))
		{
			throw new ServiceException("删除的商户编号为空");
		}
		AppLoanSpcailpriBranch appLoanSpcailpriBranch = appLoanSpcailpriBranchService.getByPrimaryKey(branchNo);
		if(appLoanSpcailpriBranch != null)
		{
			AppLoanBranchspcailpriBranchH appLoanBranchspcailpriBranchH = new AppLoanBranchspcailpriBranchH();
			BeanUtils.copyPropertiesNotForce(appLoanBranchspcailpriBranchH,appLoanSpcailpriBranch);
			appLoanBranchspcailpriBranchH.setId(RandomUtil.getUUID());
			appLoanBranchspcailpriBranchH.setEndDate(DateUtils.getCurrentDate());
			appLoanBranchspcailpriBranchH.setHandPerson(appLoanSpcailpriBranch.getHandPerson() + "|" + userProfile.getStaffNo());
			appLoanBranchspcailpriBranchH.setOrgNo(appLoanSpcailpriBranch.getOrgNo() + "|" + userProfile.getOrgNo());
			appLoanBranchspcailpriBranchHService.insert(appLoanBranchspcailpriBranchH);
			appLoanSpcailpriBranchService.deleteByPrimaryKey(branchNo);
		}
	}

	/**
	 * 销售直通车权限查询
	 * @param param
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public Page<AppLoanSpcailpriSalerDto> querySaleAuthorLst(Page<AppLoanSpcailpriSalerDto> param, UserProfile userProfile)	throws ServiceException 
	{
		if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
		Page<AppLoanSpcailpriSaler> restpage = appLoanSpcailpriSalerService.queryForPage(param.toPage(AppLoanSpcailpriSaler.class));
		return restpage.toPage(AppLoanSpcailpriSalerDto.class);
	}

	/**
	 * 销售直通车权限删除
	 * @param id
	 * @param profile
	 * @throws ServiceException
	 */
	@Override
	@Transactional
	public void delSaleAuthor(String staffNo, UserProfile userProfile) throws ServiceException 
	{
		if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
		if(StringUtils.isEmpty(staffNo))
		{
			throw new ServiceException("删除的销售编号为空");
		}
		AppLoanSpcailpriSaler appLoanSpcailpriSaler = appLoanSpcailpriSalerService.getByPrimaryKey(staffNo);
		AppLoanSpcailpriSalerH appLoanSpcailpriSalerH = new AppLoanSpcailpriSalerH();
		BeanUtils.copyPropertiesNotForce(appLoanSpcailpriSalerH, appLoanSpcailpriSaler);
		appLoanSpcailpriSalerH.setId(RandomUtil.getUUID());
		appLoanSpcailpriSalerH.setEndDate(DateUtils.getCurrentDate());
		appLoanSpcailpriSalerH.setHandPerson(appLoanSpcailpriSaler.getHandPerson() + "|" + userProfile.getStaffNo());
		appLoanSpcailpriSalerH.setOrgNo(appLoanSpcailpriSaler.getOrgNo() + "|" + userProfile.getOrgNo());
		appLoanSpcailpriSalerHService.insert(appLoanSpcailpriSalerH);
		appLoanSpcailpriSalerService.deleteByPrimaryKey(staffNo);
	}

	
	/**
	 * 
	 * 查询不在商户直通车的网点
	 * 
	 */
    public Page<AppLoanSpcailpriBranchDto> queryNotSpcailpriBranchForPage(Page<AppLoanSpcailpriBranchDto> page, UserProfile userProfile)
    {
		if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
    	page.getPageParams().put("status",CommonConstant.STAT_ENABLE);
		Page<AppLoanSpcailpriBranch> restpage = appLoanSpcailpriBranchService.queryNotSpcailpriBranchForPage(page.toPage(AppLoanSpcailpriBranch.class));
        String staffName = userProfile.getStaffName();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_OPRATE+"10001",CommonConstant.OP_TYPE_SEARCH,staffName+"不在商户直通车网点查询",CommonConstant.SALECHANL_XYD);
		return restpage.toPage(AppLoanSpcailpriBranchDto.class);
    }
	
    /**
	 * 商户直通车权限销售新增
	 * @param inDto
	 * @param profile
	 * @throws ServiceException
	 */
	@Override
	@Transactional
	public void addSaleAuthor(List<AppLoanSpcailpriSalerDto> list, UserProfile userProfile) throws ServiceException 
	{
		if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
		if(list == null || list.isEmpty())
		{
			throw new ServiceException("删除不能为空");
		}
		AppLoanSpcailpriSalerDto appLoanSpcailpriSalerDto = null;
		AppLoanSpcailpriSaler appLoanSpcailpriSaler = null;
		List<AppLoanSpcailpriSaler> appLoanSpcailpriSalerlist = new ArrayList<AppLoanSpcailpriSaler>();
		for(int i = 0; i < list.size(); i++)
		{
			appLoanSpcailpriSalerDto = list.get(i);
			appLoanSpcailpriSaler = new AppLoanSpcailpriSaler();
			appLoanSpcailpriSaler.setStaffNo(appLoanSpcailpriSalerDto.getStaffNo());
			appLoanSpcailpriSaler.setStaffName(appLoanSpcailpriSalerDto.getStaffName());
			appLoanSpcailpriSaler.setHandPerson(userProfile.getStaffNo());
			appLoanSpcailpriSaler.setInstDate(DateUtils.getCurrentDate());
			appLoanSpcailpriSaler.setOrgNo(userProfile.getOrgNo());
			appLoanSpcailpriSalerlist.add(appLoanSpcailpriSaler);
		}
		appLoanSpcailpriSalerService.addbatchInsert(appLoanSpcailpriSalerlist);
	}
	/**
	 * 
	 * 查询不在商户直通车的销售
	 * 
	 */
    public Page<AppLoanSpcailpriSalerDto> queryNotSpcailpriSalerForPage(Page<AppLoanSpcailpriSalerDto> page, UserProfile userProfile)
    {
    	if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
    	page.getPageParams().put("status",CommonConstant.STAT_ENABLE);
		Page<AppLoanSpcailpriSaler> restpage = appLoanSpcailpriSalerService.queryNotSpcailpriSalerForPage(page.toPage(AppLoanSpcailpriSaler.class));
        String staffName = userProfile.getStaffName();
    	OpLogUtil.saveOperaterLog(CommonConstant.SUBSYS_OPRATE+"10001",CommonConstant.OP_TYPE_SEARCH,staffName+"不在商户直通车销售查询",CommonConstant.SALECHANL_XYD);
		return restpage.toPage(AppLoanSpcailpriSalerDto.class);
    }
    
	/**
	 * 
	 * 查看商户直通车交易流水
	 * 
	 */
	@Override
	public Page<AppLoanSpcailpriInfoDto> queryLoanSpcailpriInfoLst(Page<AppLoanSpcailpriInfoDto> page, UserProfile userProfile) throws ServiceException 
	{

		if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
		Map<String, Object> map = page.getPageParams();
		Set<String> roleNoSet = userProfile.getRoleNoSet();
		// 将set转为list
		List<String> list = new ArrayList<>(roleNoSet);
		// 将销售经理排在第一位
		for (String str : list) 
		{
			if (PubBusinessConstant.ROLE_R_SALE_MGR.equals(str)) 
			{
				String s = str;
				list.remove(str);
				list.add(0, s);
			}
		}
		for (String role : list) 
		{
			if (PubBusinessConstant.ROLE_R_SALE_MGR.equals(role)) 
			{
				map.put("authority", "_SELF_AND_SUB");
				map.put("orgCodPath", userProfile.getOrgNo());
				break;
			} 
			else if (PubBusinessConstant.ROLE_R_SALE_MGR_REGION.equals(role)) 
			{
				map.put("authority", "_SELF_AND_SUB");
				map.put("orgCodPath", userProfile.getOrgNo());
				break;
			} 
			else if (PubBusinessConstant.ROLE_R_SALE_MGR_AREA.equals(role)) 
			{
				map.put("authority", "_SELF_AND_SUB");
				map.put("orgCodPath", userProfile.getOrgNo());
				break;
			} 
			else if (PubBusinessConstant.ROLE_R_SALE_STAFF.equals(role)) 
			{
				map.put("authority", "_SELF");
				map.put("staffNo", userProfile.getStaffNo());
				break;
			} 
			else 
			{
				map.put("authority", "_ALL");
				break;
			}
		}

		Page<AppLoanSpcailpriInfo> restpage = appLoanSpcailpriInfoService.queryForPage(page.toPage(AppLoanSpcailpriInfo.class));
		return restpage.toPage(AppLoanSpcailpriInfoDto.class);
	}

	/**
	 * 
	 * 销售直通车办单
	 * 
	 */
	@Override
	@Transactional
	public void addLoanSpcailpriInfo(AppLoanSpcailpriInfoDto inDto, UserProfile userProfile) throws ServiceException 
	{
		if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
		String loanNo = inDto.getLoanNo();
		if(StringUtils.isBlank(inDto.getLoanNo()))
		{
			throw new ServiceException("贷款编号不能为空");
		}
		AppLoanSpcailpriInfo appLoanSpcailpriInfo = appLoanSpcailpriInfoService.getByPrimaryKey(loanNo);
		if(appLoanSpcailpriInfo != null)
		{
			throw new ServiceException("根据贷款编号，该已经申请过商户直通车过了，不能继续申请！");
		}
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("loanNo", loanNo);
		List<LoanListOutBo> list = appLoanAcctService.queryLoanByCondition(param);
		if(list == null || list.isEmpty())
		{
			throw new ServiceException("没有找到相应的分期信息");
		}
		LoanListOutBo loanListOutBo = list.get(0);
		if(!(userProfile.getStaffNo().equals(loanListOutBo.getStaffNo())))
		{
			throw new ServiceException("操作用户信息不一致,无权限访问此分期申请");
		}
		appLoanSpcailpriInfo = new AppLoanSpcailpriInfo();
		appLoanSpcailpriInfo.setId(RandomUtil.getUUID());
		appLoanSpcailpriInfo.setLoanNo(loanNo);
		appLoanSpcailpriInfo.setCustName(loanListOutBo.getCustName());
		appLoanSpcailpriInfo.setOrgNo(userProfile.getOrgNo());
		appLoanSpcailpriInfo.setOrgName(userProfile.getOrgName());
		appLoanSpcailpriInfo.setStaffNo(userProfile.getStaffNo());
		appLoanSpcailpriInfo.setStaffName(userProfile.getStaffName());
		appLoanSpcailpriInfo.setApplyDate(DateUtils.getCurrentDate());
		appLoanSpcailpriInfo.setApprResult(PubBusinessConstant.SPP_APPLY);
		appLoanSpcailpriInfoService.insert(appLoanSpcailpriInfo);
		
	}

	/**
	 * 
	 * 销售直通车审批
	 * 
	 */
	@Override
	@Transactional
	public void updateLoanSpcailpriInfo(AppLoanSpcailpriInfoDto inDto, UserProfile userProfile) throws ServiceException 
	{
		if (userProfile == null) 
		{
			throw new ServiceException("登录信息为空，请重新登录");
		}
		if(StringUtils.isEmpty(inDto.getId()))
		{
			throw new ServiceException("商户直通车ID不能为空");
		}
		if(inDto == null || StringUtils.isBlank(inDto.getApprResult()))
		{
			throw new ServiceException("审核结果不能为空,请选择");
		}
		AppLoanSpcailpriInfo appLoanSpcailpriInfo = appLoanSpcailpriInfoService.getByPrimaryKey(inDto.getId());
		if(appLoanSpcailpriInfo == null)
		{
			throw new ServiceException("没有找到相应的直通车申请信息");
		}
		String loanNo = appLoanSpcailpriInfo.getLoanNo();
		String dmReslt = pubIndexService.getDmResult(loanNo);
		if("2".equals(dmReslt))
		{
			throw new ServiceException("此状态不能操作");
		}
		AppLoanAcct appLoanAcct = appLoanAcctService.getByLoanNo(loanNo);
		if(appLoanAcct == null) 
		{
			throw new ServiceException("没有找到相应的分期信息");
		}
		if(!PubBusinessConstant.LOANSTAT_REFUSED.equals(appLoanAcct.getStat()))
		{	
			throw new ServiceException("只能对审批拒绝的分期做操作");
		}
		appLoanSpcailpriInfo.setApprNo(userProfile.getStaffNo());
		appLoanSpcailpriInfo.setApprName(userProfile.getStaffName());
		appLoanSpcailpriInfo.setApprDate(DateUtils.getCurrentDate());
		appLoanSpcailpriInfo.setApprResult(inDto.getApprResult());
		appLoanSpcailpriInfo.setApprRemark(inDto.getApprRemark());
		Map<String,Object> param = BeanUtils.bean2map(appLoanSpcailpriInfo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		param.put("applyDate", sdf.format(appLoanSpcailpriInfo.getApplyDate()));
		param.put("apprDate", sdf.format(appLoanSpcailpriInfo.getApprDate()));
		
 	    logger.info("****************************** {}对直通车分期贷款编号{}，{}审核 ****Begin******************************",new Object[]{userProfile.getStaffNo(),inDto.getApprResult(),loanNo});
		
 	    appLoanSpcailpriInfoService.updateByPrimaryKeySelective(param);
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("loanNo", loanNo);
		/** 通过 */
		if(PubBusinessConstant.SPP_PASS.equals(inDto.getApprResult()))
		{
			map.put("stat", PubBusinessConstant.LOANSTAT_PASS);
			appLoanHandService.saveAppLoanHand(loanNo, appLoanAcct.getCustNo(), appLoanAcct.getCustName(), PubBusinessConstant.LOANHANDTYPE_CHANGESTAT, PubBusinessConstant.LOANHANDMODEL_SYS,userProfile.getStaffNo(), userProfile.getStaffName(), DateUtils.getCurrentTimestamp(), "分期直通车贷款状态修改为通过-销售操作写入",PubBusinessConstant.CUST_ZC);
			
			String matchStat = appLoanAcctService.loanFundMatch(loanNo);
			logger.info("{}对直通车对分期贷款审核编号{}匹配状态{}",new Object[]{userProfile.getStaffNo(),loanNo,matchStat});
			if("00".equalsIgnoreCase(matchStat))
			{
				appLoanHandService.saveAppLoanHand(loanNo,appLoanAcct.getCustNo(),appLoanAcct.getCustName(),PubBusinessConstant.LOANHANDTYPE_APPOV, PubBusinessConstant.LOANHANDMODEL_SYS, userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(), "分期资金匹配-(直通车)系统写入",PubBusinessConstant.CUST_ZC);
				int cn= appLoanAcctService.updateByLoanNoSelective(map);
				logger.info("{}对直通车对分期贷款审核编号{}修改{}",new Object[]{userProfile.getStaffNo(),loanNo,cn});
				if(cn !=1)
				{
					throw new ServiceException("分期直通车更新分期状态失败");
				}
			}
			else
			{
				throw new ServiceException("分期直通车更新分期状态,资金匹配失败");
			}
		}
		logger.info("****************************** {}对直通车分期贷款编号{}，{}审核 ****End******************************",new Object[]{userProfile.getStaffNo(),inDto.getApprResult(),loanNo});
	}
}
