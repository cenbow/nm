package com.hs.loan.approve.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.parboiled.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.Page;
import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.loan.approve.bo.AppLoanFundMatchBo;
import com.hs.loan.approve.contant.ApproveContant;
import com.hs.loan.approve.entity.AppLoanFundMatch;
import com.hs.loan.approve.entity.AppLoanFundMatchLog;
import com.hs.loan.approve.mapper.AppLoanFundMatchMapper;
import com.hs.utils.BeanUtils;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;

/**
 * APP_分期资金匹配 业务处理
 * @author autocreate
 * @create 2015-11-23
 */
@Service
@Transactional(readOnly=true)
public class  AppLoanFundMatchService{
	@Autowired
	private AppLoanFundMatchMapper appLoanFundMatchMapper;
	@Autowired
	private AppLoanFundMatchLogService appLoanFundMatchLogService;
	
	
	
	/**
	 * 新增 APP_分期资金匹配
	 * @param appLoanFundMatch 新增对象
	 */
	@Transactional
	public void insert(AppLoanFundMatch appLoanFundMatch){
		appLoanFundMatchMapper.insert(appLoanFundMatch);
	}

	/**
	 * 通过主键修改 APP_分期资金匹配
	 * @param map 修改参数Map
	 */
	@Transactional
	public void updateByPrimaryKeySelective(Map<String, Object> map){
		appLoanFundMatchMapper.updateByPrimaryKeySelective(map);
	}

	/**
	 * 通过主键删除 APP_分期资金匹配
	 * @param primaryKey 主键
	 */
	@Transactional
	public void deleteByPrimaryKey(String primaryKey){
		appLoanFundMatchMapper.deleteByPrimaryKey(primaryKey);
	}

	

	/**
	 * 通过主键取得 APP_分期资金匹配 对象
	 * @param primaryKey 主键
	 * @return APP_分期资金匹配对象
	 */
	public AppLoanFundMatch getByPrimaryKey(String primaryKey){
		return appLoanFundMatchMapper.getByPrimaryKey(primaryKey);
	}

	/**
	 * 查询 APP_分期资金匹配 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
	public List<AppLoanFundMatch> queryForList(Map<String, Object> param){
		return appLoanFundMatchMapper.queryForList(param);
	}
	
	/**
	 * 查询 APP_分期资金匹配 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
	public Page<AppLoanFundMatch> queryForPage(Page<AppLoanFundMatch> page){
		appLoanFundMatchMapper.queryForList(page.getPageParams());
		return (Page<AppLoanFundMatch>)page.getPageParams().get(Page.KEY);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	 
	/**
	 * 分页查询分期资金匹配
	 * 
	 * @param page
	 * @return
	 */
	public Page<AppLoanFundMatchBo> queryLoanFundMatch(Page<AppLoanFundMatch> page,UserProfile profile){
		//page.getParams().put("staffNo", profile.getStaffNo());//值查询当前登录人和没有被匹配过的记录
		//page.getParams().put("matchTyp", ApproveContant.FUNDMATCHTYPE_RGPP);//人工匹配
		appLoanFundMatchMapper.queryLoanFundMatch(page.getPageParams());
		return (Page<AppLoanFundMatchBo>) page.getPageParams().get(Page.KEY);
	}
	
	
	/**
	 * 选择一个分期资金匹配    进行人工操作
	 * 
	 * @param matchId
	 * @param userProfile
	 * @return
	 */
	@Transactional
	public AppLoanFundMatch choiceMatch(String matchId, UserProfile userProfile) {
		if(StringUtils.isEmpty(matchId)){
			throw new AppException("匹配id不可为空");
		}
		AppLoanFundMatch am = getByPrimaryKey(matchId);
		
		//没被拾取过的，人工匹配类型的，待匹配的
		if(StringUtils.isEmpty(am.getMatchPsn())
				&& ApproveContant.FUNDMATCHTYPE_RGPP.equals(am.getMatchTyp())
				&& ApproveContant.FUNDMATCHSTAT_DPP.equals(am.getStat())){
			
			am.setMatchPsn(userProfile.getStaffNo());
			am.setMatchName(userProfile.getStaffName());
			am.setStat(ApproveContant.FUNDMATCHSTAT_PPZ);//处理中,匹配中
			
			Map<String,Object> param = new HashMap<>();
			param.put("matchId", matchId);
			param.put("matchPsn", userProfile.getStaffNo());
			param.put("matchName", userProfile.getStaffName());
			param.put("stat", ApproveContant.FUNDMATCHSTAT_PPZ);
			
			updateByPrimaryKeySelective(param);
			
			//向历史记录表中插入一条处理中状态的的数据
			AppLoanFundMatchLog aLog = new AppLoanFundMatchLog();
			aLog.setId(RandomUtil.getUUID());
			aLog.setMatchId(matchId);
			aLog.setLoanNo(am.getLoanNo());
			aLog.setInstDate(am.getInstDate());
			aLog.setMatchTyp(am.getMatchTyp());
			aLog.setMatchPsn(am.getMatchPsn());
			aLog.setMatchName(am.getMatchName());
			aLog.setMatchDate(am.getMatchDate());
			aLog.setFundNo(am.getFundNo());
			aLog.setStat(am.getStat());
			
			aLog.setMatchResult(am.getMatchResult());
			aLog.setChanNo(am.getChanNo());
			appLoanFundMatchLogService.insert(aLog);
			
		//已经被自己拾取过的，状态是匹配中的。
		}else if(am.getMatchPsn().equals(userProfile.getStaffNo())
				&& am.getStat().equals(ApproveContant.FUNDMATCHSTAT_PPZ)){
			return am;
		}else{
			throw new AppException("该状态下不可人工匹配");
		}
		
		return am;
	}
	
	/**
	 * 保存人工操作
	 * 
	 * @param match
	 */
	@Transactional
	public void updateLoanFundMatch(AppLoanFundMatch match){
		Map<String,Object> param = BeanUtils.bean2mapExclude(match, "matchDate,instDate");
		param.put("matchDate", DateUtils.getCurDateTime());
		param.put("instDate", match.getInstDate());
		updateByPrimaryKeySelective(param);
	}

	/**
	 * 保存匹配记录
	 * @param logDto
	 */
	@Transactional
	public void saveLoanFundMatchLog(AppLoanFundMatch match) {
		AppLoanFundMatchLog matchLog = new AppLoanFundMatchLog();
		matchLog.setId(RandomUtil.getUUID());
		matchLog.setMatchId(match.getMatchId());
		matchLog.setLoanNo(match.getLoanNo());
		matchLog.setInstDate(match.getInstDate());
		matchLog.setMatchResult(match.getMatchResult());
		matchLog.setMatchTyp(match.getMatchTyp());
		matchLog.setMatchPsn(match.getMatchPsn());
		matchLog.setMatchName(match.getMatchName());
		matchLog.setMatchDate(new Date());
		matchLog.setFundNo(match.getFundNo());
		matchLog.setStat(match.getStat());
		
		matchLog.setChanNo(match.getChanNo());
		appLoanFundMatchLogService.insert(matchLog);
	}
	
	/**
	 * 资金匹配
	 * @param loanNo
	 * @return
	 */
	@Transactional
	public String  loanFundMatch(String loanNo){
		String retStat = "00";
		Map<String,Object> map = new HashMap<>();
		map.put("I_LOAN_NO", loanNo);
		try{
			appLoanFundMatchMapper.loanFundMatch(map);
		}catch(Exception e){
			e.printStackTrace();
			return "01";
		}
		String ret = (String) map.get("O_STATUS");
		if(!"00".equals(ret)){
			return "01";
		}
		return retStat;
	}
	
	/**
	 * 获取分期资金渠道信息
	 * @param loanNo
	 * @return
	 */
	public AppLoanFundMatch queryLoanFund(String loanNo){
		if(StringUtils.isEmpty(loanNo)){
			return null;
		}
		Map<String,Object> matchMap = new HashMap<String,Object>();
		matchMap.put("loanNo", loanNo);
		matchMap.put("matchResult", ApproveContant.FUNDMATCHRST_PPCG);
		List<AppLoanFundMatch> matchList = this.queryForList(matchMap);
		if(matchList == null || matchList.size() ==0){
			return null;
		}
		 return matchList.get(0);
	}
	
}