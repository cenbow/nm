package com.hs.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.system.index.bo.ApprTjBo;
import com.hs.system.index.bo.CollectTjBo;
import com.hs.system.index.bo.ValBo;

/**
 * 首页mapper
 * @author zym
 * @create 
 */
@MyBatisRepository
public interface  PubIndexMapper {

	/**
	 * 角色查询默认首页
	 * @param param
	 * @return List<SysCodInfo>
	 */
	public String queryIndexByRole(String role);

	/**
	 * 查询列表
	 * @param param
	 * @return List<SysCodInfo>
	 */
	public List<com.hs.system.index.bo.ValBo> querySaleIndexOfStat(@Param("stats")List<String> ids,@Param("staffNo") String staffNo,@Param("applyDt") String applyDt);
	
	
	public List<Map<String,String>> queryApprStaffGroup(String staffNo);

	public List<Map<String,String>> queryCollectionStaffGroup(String staffNo);

	/**
	 * 
	 * @param list
	 * @param staffNo
	 * @param curDate
	 * @return
	 */
	public List<ValBo> queryApprIndexOfStat(@Param("stats")List<String> ids,@Param("staffNo") String staffNo,@Param("applyDt") String applyDt);
	/**
	 * 
	 * @param list
	 * @param staffNo
	 * @param curDate
	 * @return
	 */
	public Map<String,Object> queryApprIndexOfStatByMuth(@Param("staffNo") String staffNo,@Param("applyDt") String applyDt);
	/**
	 * 查询违约率
	 * @param param
	 * @return
	 */
	public Double queryRiskRate(Map<String,Object> param);
	/**
	 * 查询审批信息
	 * @param param
	 * @return
	 */
 	public List<ApprTjBo> queryApprIndex(Map<String,Object> param);
	/**
	 * 查询催收信息
	 * @param param
	 * @return
	 */
 	public List<CollectTjBo> queryCollectIndex(Map<String,Object> param);
 	/**
 	 * 保存大数据请求流水
 	 * @param param
 	 * @return
 	 */
 	public void saveDmOrder(Map<String,String> param);
 	/**
 	 * 根据分期编号获取大数据请求流水编号
 	 * @param param
 	 * @return
 	 */
 	public String getDmOrderId(String loanNo);
 	/**
 	 * 根据分期编号更新大数据请求流水编号
 	 * @param param
 	 * @return
 	 */
	public int updateDmOrderId(Map<String,String> map);
	/**
	 * 根据分期编号获取大数据审批结果
	 * @param param
	 * @return
	 */
	public String getDmResult(String loanNo);

	public Map<String, String> getDmOrderIdAndAppkey(String loanNo);

	public void saveDmOrderAndAppkey(Map<String, String> map);

}
