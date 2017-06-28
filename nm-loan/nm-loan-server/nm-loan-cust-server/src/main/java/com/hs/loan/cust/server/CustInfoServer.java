package com.hs.loan.cust.server;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.cust.api.CustInfoApi;
import com.hs.loan.cust.bo.CustInfoBo;
import com.hs.loan.cust.dto.AppCustSourceInfoDto;
import com.hs.loan.cust.dto.CustBaseInfoDto;
import com.hs.loan.cust.dto.CustInfoBoDto;
import com.hs.loan.cust.dto.CustInfoDto;
import com.hs.loan.cust.entity.AppCustInfo;
import com.hs.loan.cust.entity.AppCustSourceInfo;
import com.hs.loan.cust.service.AppCustInfoService;
import com.hs.loan.cust.service.AppCustSourceInfoService;
import com.hs.loan.pub.hand.service.AppLoanHandService;
import com.hs.loan.sale.service.AppLoanAcctService;
import com.hs.system.area.PubSysRegionalBelongService;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;
import com.hs.utils.StringUtils;

/**
 * 客户信息 服务
 * @author zwr
 *
 */
@Service
@Transactional(readOnly=true)
public class CustInfoServer implements CustInfoApi {
	@Autowired
	private AppLoanHandService appLoanHandService;
	@Autowired
	private AppCustInfoService appCustInfoService;
	@Autowired
	private CustLiveInfoServer custLiveInfoServer;
	@Autowired
	private PubSysRegionalBelongService regionalBelongService;	//区县
	@Autowired
	private AppLoanAcctService appLoanAcctService;
	@Autowired
	private AppCustSourceInfoService appCustSourceInfoService;
	/**
	 * 分页查询 客户基本信息
	 * @param page
	 * @return
	 */
	public Page<CustInfoBoDto> queryCustInfo(Page<CustInfoBoDto> page){
		/*List<String> custNos = new ArrayList<>();//调用分期服务通过分期时间查询出custno
		if(custNos!=null && custNos.size()>0){
			page.getParams().put("custNos", custNos);
		}*/
		return appCustInfoService.queryCustInfo(page.toPage(CustInfoBo.class)).toPage(CustInfoBoDto.class);
	}

	/**
	 * 通过客户号，获取客户基本信息（包含户籍信息）
	 * @param custNo
	 * @return
	 */
	public CustInfoDto getByNo(String custNo) {
		CustInfoDto dto = null;
		AppCustInfo appCustInfo = appCustInfoService.getByNo(custNo);
		if(appCustInfo!=null){
			dto = new CustInfoDto();
			BeanUtils.copyProperties(appCustInfo, dto);
			setRegAddr(dto);
		}
		dto.setAppCustSourceInfoDto(this.findShourceInfo(custNo));
		return dto;
	}
	
	/**
	 * 保存或者更新 客户基本信息
	 * 返回客户编号
	 * @param custInfoDto
	 */
	@Transactional
	public String save(CustInfoDto custInfoDto){
		AppCustInfo appCustInfo = new AppCustInfo();
		BeanUtils.copyProperties(custInfoDto, appCustInfo);
		if(StringUtils.isNotBlank(appCustInfo.getCertNo()))
			appCustInfo.setCertNo(appCustInfo.getCertNo().trim());
		if(StringUtils.isNotBlank(appCustInfo.getCustName()))
			appCustInfo.setCustName(appCustInfo.getCustName().trim());
		return appCustInfoService.save(appCustInfo);
	}
 
	/**
	 * 通过客户号 删除 客户基本信息
	 */
	@Transactional
	public void deleteByNo(String custNo){
		appCustInfoService.deleteByNo(custNo);
	}
	
	/**
	 * 更新客户最后一次申请时间为当前时间
	 * @param custNo
	 */
	@Transactional
	public void updateLastApplyDate(String custNo){
		appCustInfoService.updateLastApplyDate(custNo);
	}
	
	/**
	 * 通过身份证号获取客户身份信息
	 * 
	 * @param certNo
	 */
	public CustInfoDto getByCertNo(String certNo){
		CustInfoDto dto = null;
		AppCustInfo appCustInfo = appCustInfoService.getByCertNo(certNo);
		if(appCustInfo!=null){
			dto = new CustInfoDto();;
			BeanUtils.copyProperties(appCustInfo, dto);
			setRegAddr(dto);
		}
		return dto;
	}
	
	/**
	 * 通过客户名和身份证号验证客户
	 * 验证成功返回该客户基本信息，和现联系信息 否则返回null
	 * 
	 * @param custName
	 * @param certNo
	 * @return
	 */
	public CustBaseInfoDto validCust(String custName,String certNo){
		CustBaseInfoDto dto = null;
		AppCustInfo appCustInfo = appCustInfoService.validCust(custName,certNo);
		if(appCustInfo==null){
			return null;
		}
		dto = new CustBaseInfoDto();
		CustInfoDto info = new CustInfoDto();
		BeanUtils.copyProperties(appCustInfo, info);
		setRegAddr(info);
		dto.setCustInfoDto(info);
		//获取现联系信息
		/*List<CustContctInfoDto> lst = custContctInfoServer.getLatestAvailableContctInfoLst(info.getCustNo());
		if(lst==null || lst.size()==0){
			return dto;
		}
		CustContctInfoDto contct = lst.get(0);
		dto.setCustContctInfoDto(contct);*/
		return dto;
	}
	
	/**
	 * 保存或更新 客户的基本信息,联系信息，居住信息
	 * 若custNo不为空的时候，custInfoDto,custLiveInfoDto中的custNo以传入的custNo参数为准
	 * 
	 * 返回保存后的custNo
	 * 
	 * @param custInfoDto
	 * @param custLiveInfoDto
	 * @return
	 */
	@Transactional
	public String saveCustBaseInfo(CustBaseInfoDto dto){
		String custNo = dto.getCustInfoDto().getCustNo();
		if(!StringUtils.isEmpty(custNo)){
			if(!custNo.equals(dto.getCustLiveInfoDto().getCustNo())){
				throw new AppException("客户号不统一");
			}
		}
		//验证证件有效期
		Date curdate = DateUtils.getCurrentDate();
		String strCertValidDate = dto.getCustInfoDto().getCertValidDate();
		if(StringUtils.isEmpty(strCertValidDate)){
			throw new ServiceException("证件有效期不能为空");
		}
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			Date certValidDate = fmt.parse(strCertValidDate);
			if(curdate.after(certValidDate)){
				throw new AppException("证件有效期不能为过去时间");
			}
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ServiceException("有效日期格式化错误");
		}
		
		//保存基本信息
		custNo = save(dto.getCustInfoDto());
		//保存联系信息
		//custContctInfoServer.save(custNo, dto.getCustContctInfoDto());
		//保存现居住地址
		dto.getCustLiveInfoDto().setCustNo(custNo);
		custLiveInfoServer.save(custNo,dto.getCustLiveInfoDto());
		return custNo;
	}
	  
	/**
	 * 设置户籍地址
	 * @param dto
	 */
	private void setRegAddr(CustInfoDto dto){
		if(dto==null) return;
		dto.setRegProvName(regionalBelongService.getProvName(dto.getRegProv()));
		dto.setRegCityName(regionalBelongService.getCityName(dto.getRegCity()));
		dto.setRegAreaName(regionalBelongService.getCountName(dto.getRegArea()));
		dto.setContactProvName(regionalBelongService.getProvName(dto.getContactProv()));
		dto.setContactCityName(regionalBelongService.getCityName(dto.getContactCity()));
		dto.setContactAreaName(regionalBelongService.getCountName(dto.getContactArea()));
	}

	/**
	 * 保存或更新 客户的基本信息,联系信息，居住信息
	 * 若custNo不为空的时候，custInfoDto,custLiveInfoDto中的custNo以传入的custNo参数为准
	 * 
	 * 返回保存后的custNo
	 * 
	 * @param custInfoDto
	 * @param custLiveInfoDto
	 * @return
	 */
	@Transactional
	public String update(String loanNo,CustInfoDto custInfoDto,UserProfile profile) throws ServiceException,
			AppException {
		AppCustInfo appCustInfo = new AppCustInfo();
		BeanUtils.copyProperties(custInfoDto, appCustInfo);
		
		/* 添加经办信息 */
		appLoanHandService.saveAppLoanHand(loanNo, custInfoDto.getCustNo(),
				custInfoDto.getCustName(),
				PubBusinessConstant.LOANHANDTYPE_UPDATECUSTINFO,
				PubBusinessConstant.LOANHANDMODEL_HAND, profile.getStaffNo(),
				profile.getStaffName(), DateUtils.getCurrentTimestamp(),
				"修改客户信息",PubBusinessConstant.CUST_ZC);
		
		/*修改贷款基本表客户名称*/
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("custName", custInfoDto.getCustName());
		map.put("loanNo", loanNo);
		appLoanAcctService.updateByLoanNoSelective(map);
		
		return appCustInfoService.save(appCustInfo);
		
		
	}

	@Override
	public int queryCustScore(String custNo) throws ServiceException,
			AppException {
		List<Integer> scores=appCustInfoService.queryCustScore(custNo);
		if (scores != null && scores.size() > 0) {
			int totalScore = 0;
			for (int i = 0; i < scores.size(); i++) {
				totalScore += scores.get(i);
			}
			return totalScore / scores.size();

		}
		return 0;
	}
 
	/***
	 * 新增客户来源
	 */
	@Transactional
	private void addCustSourceInfo(AppCustSourceInfoDto custInfoDto)  throws ServiceException, AppException{
		
		AppCustSourceInfo appCustSourceInfo = appCustSourceInfoService.getByCustNo(custInfoDto.getCustNo());
		if (appCustSourceInfo != null) {
			appCustSourceInfo.setSource(custInfoDto.getSource());
			appCustSourceInfo.setCustSource(custInfoDto.getCustSource());
			appCustSourceInfo.setRemark(custInfoDto.getRemark());
			appCustSourceInfo.setInstDate(new Date());
			Map<String,Object> map = BeanUtils.bean2map(appCustSourceInfo);
			map.put("instDate", new Date());
			appCustSourceInfoService.updateByPrimaryKeySelective(map);
		} else {
			AppCustSourceInfo info = new AppCustSourceInfo();
			BeanUtils.copyPropertiesNotForce(info, custInfoDto);
			info.setId(RandomUtil.getUUID());
			info.setInstDate(new Date());
			info.setIsWilling("10001001");
			appCustSourceInfoService.insert(info);
		}
		
//		if (StringUtils.isBlank(custInfoDto.getId())) {
//			Map<String, Object> map = new HashMap<>();
//			if (custInfoDto.getCustSource() != null) {
//				map.put("custSource", custInfoDto.getCustSource());
//			}
//			if (custInfoDto.getRemark() != null) {
//				map.put("remark", custInfoDto.getRemark());
//			}
//			map.put("id", custInfoDto.getId());
//			appCustSourceInfoService.updateByPrimaryKeySelective(map);
//		} else {
//			info.setId(RandomUtil.getUUID());
//			info.setInstDate(new Date());
//			info.setIsWilling("10001001");
//			if (appCustSourceInfoService.getByCustNo(custInfoDto.getCustNo()) != null) {
//				throw new ServiceException("已存在客户来源信息");
//			}
//			appCustSourceInfoService.insert(info);
//		}
	}

	/**
	 * 查询客户来源信息
	 * 
	 * @param custNo
	 */
	@Override
	public AppCustSourceInfoDto findShourceInfo(String custNo) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		AppCustSourceInfo info = appCustSourceInfoService.getByCustNo(custNo);
		AppCustSourceInfoDto infoDto = new AppCustSourceInfoDto();
		if (info!= null) {
			BeanUtils.copyPropertiesNotForce(infoDto, info);
		}
		return infoDto;
	}
	
	/**
	 * 保存或更新 客户的基本信息,联系信息，居住信息
	 * 若custNo不为空的时候，custInfoDto,custLiveInfoDto中的custNo以传入的custNo参数为准
	 * 
	 * 返回保存后的custNo
	 * 
	 * @param custInfoDto
	 * @param custLiveInfoDto
	 * @return
	 */
	@Transactional
	public String saveCustBaseInfoNew(CustBaseInfoDto dto) throws ServiceException, AppException {
		String custNo = this.saveCustBaseInfo(dto);
		if (dto.getAppCustSourceInfoDto()!=null) {
			dto.getAppCustSourceInfoDto().setCustNo(custNo);
			this.addCustSourceInfo(dto.getAppCustSourceInfoDto());
		}
		return custNo;
	}
	
	/**
	 * 查询客户结清金额
	 * @param loanNo
	 * @param tranDate
	 * @return
	 * @throws ServiceException
	 * @throws AppException
	 */
	@Transactional
	public Map<String, Object> findCustSettleAmt(String loanNo, String tranDate) throws ServiceException, AppException {
		// TODO Auto-generated method stub
		 Map<String, Object> res =null;
		try {
			res = appLoanAcctService.findCustSettleAmt(loanNo, tranDate);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return res;
	}
}
