package com.hs.loan.approve.server;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.commons.constants.PubBusinessConstant;
import com.hs.loan.approv.dto.LoanFundMatchDto;
import com.hs.loan.approv.dto.ProdFundInfoOutDto;
import com.hs.loan.approve.api.LoanFundMatchApi;
import com.hs.loan.approve.bo.AppLoanFundMatchBo;
import com.hs.loan.approve.contant.ApproveContant;
import com.hs.loan.approve.entity.AppLoanFundMatch;
import com.hs.loan.approve.service.AppLoanFundMatchService;
import com.hs.loan.busi.dto.LoanProdDto;
import com.hs.loan.contract.api.LoanContractSignApi;
import com.hs.loan.finance.api.FundChanInfoApi;
import com.hs.loan.finance.dto.FundChanInfoDto;
import com.hs.loan.prod.api.ProdFundChanApi;
import com.hs.loan.prod.dto.PubProdFundChanDto;
import com.hs.loan.pub.hand.service.AppLoanHandService;
import com.hs.loan.sale.api.LoanAcctApi;
import com.hs.loan.sale.api.LoanViewApi;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * APP_分期资金匹配 服务
 * 
 */
@Service
@Transactional(readOnly=true)
public class LoanFundMatchServer implements LoanFundMatchApi {

	@Autowired
	private AppLoanFundMatchService appLoanFundMatchService;
	@Autowired
	private ProdFundChanApi approveProdFundChanService;
	@Autowired
	private LoanViewApi approveLoanViewService;
	@Autowired
	private FundChanInfoApi approveFundChanInfoService;
	@Autowired
	private LoanAcctApi approveLoanAcctService;
//	@Autowired
//	private ValidateFundChanServer validateFundChanServer;
//	@Autowired
//	private CustInfoApi approveCustInfoApi;
	
	@Autowired
	private AppLoanHandService appLoanHandService;
	
	@Autowired
	private LoanContractSignApi approveLoanContractSignService;
	
	/**
	 * 分页查询 分期资金匹配
	 * 
	 */
	@Override
	public Page<LoanFundMatchDto> queryLoanFundMatch( Page<LoanFundMatchDto> param,UserProfile profile) throws ServiceException, AppException {
		 Page<AppLoanFundMatchBo> page1 = appLoanFundMatchService.queryLoanFundMatch(param.toPage(AppLoanFundMatch.class),profile);
		return page1.toPage(LoanFundMatchDto.class);
	}
	
	/**
	 * 拾取一个任务   进行人工操作
	 * 
	 */
	@Transactional
	@Override
	public LoanFundMatchDto choiceMatch(String matchId, UserProfile userProfile) throws ServiceException, AppException {
		AppLoanFundMatch am = appLoanFundMatchService.choiceMatch(matchId,userProfile);
		LoanFundMatchDto dto = (LoanFundMatchDto) BeanUtils.copyPropertiesNotNull(new LoanFundMatchDto(), am);
		return dto;
	}
	
	
	/**
	 * 查询分期绑定的产品关联的渠道列表
	 * 
	 */
	@Override
	public List<ProdFundInfoOutDto> queryFundLst(String loanNo,UserProfile userProfile) throws ServiceException, AppException {
		if(StringUtils.isEmpty(loanNo)){
			throw new AppException("分期编号不可为空");
		}
		LoanProdDto loanProdDto = approveLoanViewService.queryLoanOtherInfo(loanNo, userProfile).getProd();
		List<PubProdFundChanDto> dtoLst = approveProdFundChanService.queryProdFundChan(loanProdDto.getProdNo());
		List<ProdFundInfoOutDto> pLst = new ArrayList<>();
		for(PubProdFundChanDto dto :dtoLst){
			ProdFundInfoOutDto pDto = new ProdFundInfoOutDto();
			pDto.setChanNo(dto.getChanNo());
			pDto.setChanName(dto.getChanName());
			pLst.add(pDto);
		}
		return pLst;
	}

	/**
	 * 保存匹配的渠道信息
	 * 
	 */
	@Transactional
	@Override
	public void saveLoanFundMatch(LoanFundMatchDto matchDto, UserProfile userProfile) throws ServiceException, AppException {
		if(matchDto==null||StringUtils.isEmpty(matchDto.getMatchId())){
			throw new AppException("matchId 不可为空");
		}
		AppLoanFundMatch appLoanFundMatch = appLoanFundMatchService.getByPrimaryKey(matchDto.getMatchId());
		if(ApproveContant.FUNDMATCHRST_PPCG.equals(appLoanFundMatch.getMatchResult())){
			throw new ServiceException("该笔分期已经匹配成功，匹配结果为："+appLoanFundMatch.getChanName());
		}
		String chanNo = matchDto.getChanNo();
		
		//检查该渠道是否可用
		FundChanInfoDto fundDto = approveFundChanInfoService.getByNo(chanNo);
		String checkFlag = fundDto.getCheckFlag();
		appLoanFundMatch.setMatchResult(ApproveContant.FUNDMATCHRST_PPCG);
		appLoanFundMatch.setFundNo(matchDto.getFundNo());
		appLoanFundMatch.setChanName(matchDto.getChanName());
		appLoanFundMatch.setChanNo(matchDto.getChanNo());
		appLoanFundMatch.setMatchDate(DateUtils.getCurrentTimestamp());
		appLoanFundMatch.setStat(ApproveContant.FUNDMATCHSTAT_PPWC);
		/* 渠道校验，占时屏蔽
		if(checkFlag!=null && checkFlag.equals(CommonConstant.COMMON_YES)){
			CustInfoDto custDto = approveCustInfoApi.getByNo(matchDto.getCustNo());
			String loanNo = matchDto.getLoanNo();
			if(!validateFundChanServer.checkChanIzAvailable(loanNo, custDto, fundDto)){//不成功则匹配失败
				matchDto.setMatchResult(ApproveContant.FUNDMATCHRST_PPSB);
			}
		}
		
		if(ApproveContant.FUNDMATCHRST_PPSB.equals(appLoanFundMatch.getMatchResult())){
			saveLoanFundMatchLog(appLoanFundMatch);
			throw new ServiceException("该资金渠道不可用，请更换渠道！");
		}*/
		//该渠道可用，更新分期资金匹配表并写入分期资金匹配记录
		appLoanFundMatch.setMatchDate(DateUtils.getCurrentTimestamp());
		appLoanFundMatchService.updateLoanFundMatch(appLoanFundMatch);
		appLoanFundMatchService.saveLoanFundMatchLog(appLoanFundMatch);
		//写分期与渠道的关系表
		approveLoanAcctService.saveLoanFundChanl(matchDto.getLoanNo(), matchDto.getFundNo(),matchDto.getChanName());
		//生成合同
		try{
			approveLoanContractSignService.buidContant(matchDto.getLoanNo(), userProfile,"pc");
		}catch(Exception e){
			e.printStackTrace();
			/*retMsg="02"; //审批通过资金匹配成功合同生成失败
			return retMsg;*/
			throw new ServiceException("生成合同失败,请重匹配!");
		}		
		//更新分期基本信息
		Map<String,Object> map = new HashMap<>();
		map.put("loanNo",matchDto.getLoanNo());
		map.put("aprvDate", DateUtils.getCurDateTime());
		map.put("stat",PubBusinessConstant.LOANSTAT_PASS);
		approveLoanAcctService.updateLoanByLoanNo(map);
		appLoanHandService.saveAppLoanHand(matchDto.getLoanNo(),appLoanFundMatch.getCustNo(),appLoanFundMatch.getCustName(),
				PubBusinessConstant.LOANHANDTYPE_FUNMATCH, PubBusinessConstant.LOANHANDMODEL_SYS, 
				userProfile.getStaffNo(), userProfile.getStaffName(),DateUtils.getCurrentTimestamp(), "分期资金匹配/生成合同-系统写入",PubBusinessConstant.CUST_ZC);
		
	}
	
	/**
	 * 新开一个事物 写入分期资金匹配记录，在渠道不可用的时候调用，防止写入记录被回滚
	 * 
	 * @param matchDto
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void saveLoanFundMatchLog(AppLoanFundMatch appLoanFundMatch){
		AppLoanFundMatch aMatch = (AppLoanFundMatch)BeanUtils.copyPropertiesNotNull(new AppLoanFundMatch(),appLoanFundMatch);
		appLoanFundMatchService.saveLoanFundMatchLog(aMatch);
	}

	@Override
	public LoanFundMatchDto queryLoanFund(String loanNo) throws ServiceException, AppException {
		LoanFundMatchDto dto = new LoanFundMatchDto();
		AppLoanFundMatch  appLoanFundMatch = appLoanFundMatchService.queryLoanFund(loanNo);
		BeanUtils.copyPropertiesNotForce(dto, appLoanFundMatch);
		return dto;
	}
	
	/**
	 * 资金匹配成功但合同生成失败 手工生成
	 * @param loanNo
	 * @param profile
	 */
	public String handCreateContract(String loanNo,UserProfile profile){
		LoanFundMatchDto dto = this.queryLoanFund(loanNo);
		if(dto == null || StringUtils.isEmpty(dto.getChanNo()) || !(ApproveContant.FUNDMATCHRST_PPCG.equals(dto.getMatchResult()))){
			throw new ServiceException("生成合同失败,请先匹配资金渠道!"); 
		}
		//生成合同
		try{
			 approveLoanContractSignService.buidContant(loanNo, profile,"pc");
		}catch(Exception e){
			e.printStackTrace();
			/*retMsg="02"; //审批通过资金匹配成功合同生成失败
			return retMsg;*/
			throw new ServiceException("生成合同失败,请重生成或联系管理员!");
		}		
		//更新分期基本信息
		Map<String,Object> map = new HashMap<>();
		map.put("loanNo",loanNo);
		map.put("aprvDate", DateUtils.getCurDateTime());
		map.put("stat",PubBusinessConstant.LOANSTAT_PASS);
		approveLoanAcctService.updateLoanByLoanNo(map);
		return "";
	}
	

}
