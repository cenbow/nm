package com.hs.loan.approvecheck.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.approvecheck.bo.AppLoanApprCheckBo;
import com.hs.loan.approvecheck.entity.AppLoanApprCheck;

/**
 * APP_分期案件复核 mapper
 * @author autocreate
 * @create 2016-11-24
 */
@MyBatisRepository
public interface AppLoanApprCheckMapper extends BaseMapper<AppLoanApprCheck>
{
	/**
     * 根据审批编号查询主管编号
     *
     * @param staffNo 人员编号
     * @return
     */
    public Map<String,String> getManagerNoByStaffNo(String staffNo);
    
	/**
     * 根据主管编号查询是否在线(上线、下线)状态
     *
     * @param staffNo 人员编号
     * @return
     */
    public String getGroupTypeByStaffNo(String staffNo);
    
    /**
     * 根据主管编号查询主管的复核单子或抢单的总数
     * @param staffNo 人员编号
     * @return
     */
    public Integer getCountByManagerNo(String staffNo);
    
    /**
     * 根据非主管编号查询审批中单子数
     * @param staffNo
     * @return
     */
    public Integer getCountByStaffNo(String staffNo);
    
    /**
     * 根据ID查询出复核次数
     * @param apprId
     * @return
     */
    public Integer getCheckCntByApprId(String apprId);
    
    /**
     * 根据ID查询出复核记录
     * @param apprId
     * @return
     */
    public int deleteByApprId(String apprId);
    
    /**
	 * 查询 APP_分期案件复核 列表
	 * @param param	查询参数map
	 * @return List<T>列表
	 */
    public List<AppLoanApprCheckBo> queryForListTwo(Map<String, Object> map);
    
    /**
     * 根据非主管编号查询审批中单子数
     * @param staffNo
     * @return
     */
    public Integer getCountByStaffNoTwo(String staffNo);
    
    /**
     * 更新app_loan_acct
     * @param map
     * @return
     */
    public int updateAppLoanAcctByLoanNo(Map<String,Object> map);
}