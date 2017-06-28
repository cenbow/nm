package com.hs.system.index.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.ServiceException;
import com.hs.system.index.bo.ApprTjBo;
import com.hs.system.index.bo.CollectTjBo;
import com.hs.system.index.bo.IndexOfApprBo;
import com.hs.system.index.bo.IndexOfCollectionBo;
import com.hs.system.index.bo.IndexOfSaleBo;
import com.hs.system.index.bo.ValBo;
import com.hs.system.mapper.PubIndexMapper;
import com.hs.utils.DateUtils;
import com.hs.utils.RandomUtil;

@Service
@Transactional(readOnly=true)
public class PubIndexService {
	@Autowired
	private PubIndexMapper pubIndexMapper;
	
	/**
	 * 销售首页
	 * @param loanStats
	 * @return
	 */
	public IndexOfSaleBo querySaleindxInfo(Set<String> loanStats,UserProfile userProfile,Map<String,Object> map){
		if(userProfile == null){
			throw new ServiceException("获取登录用户失败，请重新登录");
		}
		IndexOfSaleBo bo = new IndexOfSaleBo();
		List<String> list= new ArrayList<>();
		list.addAll(loanStats);
		List<ValBo> retlist = pubIndexMapper.querySaleIndexOfStat(list,userProfile.getStaffNo(),DateUtils.getCurDate());
		bo.setRetLst(retlist);
		return bo;
	}
	/**
     * 获取当前时间倒推半年的时间
     * @return
     */
    public Date getBefHalfYearTime(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -6);
        return c.getTime();
    }
	/**
	 * 审批首页 --
	 * @param loanStats
	 * @return
	 */
	public IndexOfApprBo queryApprindxInfo(Set<String> loanStats,UserProfile user){
		if(user == null){
			throw new ServiceException("获取登录用户失败，请重新登录");
		}
		IndexOfApprBo bo = new IndexOfApprBo();
		List<String> list= new ArrayList<>();
		list.addAll(loanStats);
		 Map<String,Object> retMap = pubIndexMapper.queryApprIndexOfStatByMuth(user.getStaffNo(),DateUtils.getCurDate().substring(0, 6));
		 if(retMap == null ||retMap.size() == 0){
			 retMap = new HashMap<>();
			 retMap.put("all", 0);
			 retMap.put("pass", 0);
			 retMap.put("unpass", 0);
			 retMap.put("staffNo", user.getStaffNo());
			 bo.setCntBymuth(retMap);
		 }else{
			 bo.setCntBymuth(retMap);
		 }
		bo.setStaffName(user.getStaffName());
		bo.setGroupName(pubIndexMapper.queryApprStaffGroup(user.getStaffNo()));
		Map<String,Object> param = new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		param.put("statDate", sdf.format(new Date()));
		param.put("statDateSt", sdf.format(getBefHalfYearTime()));
		param.put("statDateEd", sdf.format(new Date()));
		Double riskRt = pubIndexMapper.queryRiskRate(param);
		bo.setRisk(riskRt);
		List<ApprTjBo> apprBoList = pubIndexMapper.queryApprIndex(param);
		List<String> total = new ArrayList<String>();
		List<String> pass = new ArrayList<>();
		List<String> unpass = new ArrayList<>();
		List<String> date = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(apprBoList)){
			for(ApprTjBo apprBo:apprBoList){
				total.add(apprBo.getTotal()==null?"0":apprBo.getTotal().toString());
				pass.add(apprBo.getPass()==null?"0":apprBo.getPass().toString());
				unpass.add(apprBo.getUnpass()==null?"0":apprBo.getUnpass().toString());
				date.add(apprBo.getApprMth());
			}
		}
		/*total.add("100");
		total.add("400");
		total.add("300");
		total.add("500");
		total.add("600")*/;
		bo.setTotal(total);
		
//		List<String> pass = new ArrayList<>();
//		pass.add("80");
//		pass.add("280");
//		pass.add("210");
//		pass.add("390");
//		pass.add("550");
		bo.setPass(pass);
//		List<String> unpass = new ArrayList<>();
//		unpass.add("20");
//		unpass.add("120");
//		unpass.add("90");
//		unpass.add("110");
//		unpass.add("50");
		bo.setUnpass(unpass);
		
//		List<String> date = new ArrayList<>();
//		date.add("201501");
//		date.add("201502");
//		date.add("201503");
//		date.add("201504");
//		date.add("201505");
		bo.setDates(date);
		return bo;
	}
	/**
	 * 催收首页
	 * @param loanStats
	 * @return
	 */
	public IndexOfCollectionBo queryCollectionindxInfo(UserProfile user){
		IndexOfCollectionBo bo = new IndexOfCollectionBo();
		bo.setStaffName(user.getStaffName());
		bo.setGroupName(pubIndexMapper.queryCollectionStaffGroup(user.getStaffNo()));
		Map<String,Object> param = new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		param.put("statMthSt", sdf.format(getBefHalfYearTime()));
		param.put("statMthEd", sdf.format(new Date()));
		List<CollectTjBo> collectBoList = pubIndexMapper.queryCollectIndex(param);
		List<String> total = new ArrayList<>();
		List<String> pass = new ArrayList<>();
		List<String> date = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(collectBoList)){
			for(CollectTjBo coltBo:collectBoList){
				total.add(coltBo.getTotal()==null?"0":coltBo.getTotal().toString());
				pass.add(coltBo.getPass()==null?"0":coltBo.getPass().toString());
				date.add(coltBo.getCollectMth());
			}
		}
//		List<String> total = new ArrayList<>();
//		total.add("100");
//		total.add("400");
//		total.add("300");
//		total.add("500");
//		total.add("600");
		bo.setTotal(total);
//		List<String> pass = new ArrayList<>();
//		pass.add("80");
//		pass.add("280");
//		pass.add("210");
//		pass.add("390");
//		pass.add("550");
		bo.setPass(pass);
//		List<String> date = new ArrayList<>();
//		date.add("201501");
//		date.add("201502");
//		date.add("201503");
//		date.add("201504");
//		date.add("201505");
		bo.setDates(date);
		return bo;
	}
	
	/**
	 * 保存流水
	 * @param orderId
	 * @param loanNo
	 * @return
	 */
	@Transactional
	public String saveDmOrderInfo(String orderId,String loanNo){
		Map<String,String> map = new HashMap<>();
		map.put("orderId", orderId);
		map.put("loanNo", loanNo);
		pubIndexMapper.saveDmOrder(map);
		return this.getDmOrderId(loanNo);
	}
	/**
	 * 保存流水
	 * @param orderId
	 * @param loanNo
	 * @return
	 */
	@Transactional
	public String saveDmOrderAndAppkey(String orderId,String loanNo,String appkey){
		Map<String,String> map = new HashMap<>();
		map.put("orderId", orderId);
		map.put("loanNo", loanNo);
		map.put("appKey", appkey);
		pubIndexMapper.saveDmOrderAndAppkey(map);
		return this.getDmOrderId(loanNo);
	}
	/**
	 * 获取流水
	 * @param orderId
	 * @param loanNo
	 * @return
	 */
	@Transactional
	public String updateDmOrderId(String loanNo){
		String orderId =  RandomUtil.getUUID();
		 Map<String,String> map = new HashMap<>();
		 map.put("orderId",orderId);
		 map.put("loanNo", loanNo);
		 pubIndexMapper.updateDmOrderId(map);
		return orderId;
	}
	 
	/**
	 * 获取流水
	 * @param orderId
	 * @param loanNo
	 * @return
	 */
	public String getDmOrderId(String loanNo){
		return pubIndexMapper.getDmOrderId(loanNo);
	}
	/**
	 * 获取流水
	 * @param orderId
	 * @param loanNo
	 * @return
	 */
	public Map<String,String> getDmOrderIdAndAppkey(String loanNo){
		return pubIndexMapper.getDmOrderIdAndAppkey(loanNo);
	}
	/**
	 * 获取大数据审批结果
	 * @param orderId
	 * @param loanNo
	 * @return
	 */
	public String getDmResult(String loanNo){
		return pubIndexMapper.getDmResult(loanNo);
	}
}
