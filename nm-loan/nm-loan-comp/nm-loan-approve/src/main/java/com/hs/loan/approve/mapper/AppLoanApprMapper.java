package com.hs.loan.approve.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.approve.bo.AppLoanApprBo;
import com.hs.loan.approve.entity.AppLoanAppr;

/**
 * APP_分期审批信息 mapper
 *
 * @author autocreate
 * @create 2015-11-23
 */
@MyBatisRepository
public interface AppLoanApprMapper extends BaseMapper<AppLoanAppr> {
    public List<AppLoanAppr> getAppLoanAppr(Map map);
    /**
     * 更新根据进入时间的第一条未被未被分配的任务
     *
     * @param map (人工审批开始时间 MANU_START_DATE,审批人员编号 APPR_NO,审批人员姓名 apprName)
     * @return
     */
    public int updateFirstOrderByInstDate(HashMap<String, Object> map);

    /**
     * 查询是否存在带分配的贷款审批案件
     *
     * @return int
     */
    public int selectWaitAllot(HashMap<String,Object> map);
    /**
     * 查询待分配的贷款审批案件
     *
     * @return int
     */
    public int queryForListcnt(Map<String, Object> param);
    
    /**
	 * 查询 APP_分期审批信息 分页列表
	 * @param page	查询参数page
	 * @return List<T>列表
	 */
    public List<AppLoanApprBo> queryForListTwo(Map<String,Object> map);
	public int updateGoodsPric(Map<String, Object> paramap);
	public Map<String, Object> getGoodsType(Map<String, Object> param);
	public Map<String, Object> querySendPhoneNo(Map<String, Object> param);
}