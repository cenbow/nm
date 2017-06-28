package com.hs.loan.approve.mapper;

import java.util.List;
import java.util.Map;

import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.approve.entity.AppApprStaffGroup;

/**
 * APP_审批人员与组关联信息 mapper
 *
 * @author autocreate
 * @create 2015-11-23
 */
@MyBatisRepository
public interface AppApprStaffGroupMapper extends BaseMapper<AppApprStaffGroup> {
    /**
     * 删除审批人员与组关联信状态
     *
     * @param param
     */
    public void deleteByCont(Map<String, Object> param);

    /**
     * 修改审批人员与组关联信状态
     *
     * @param param
     */
    public void updateStaffGrpStat(Map<String, Object> param);

    /**
     * 修改审批人员与组关联信状态
     *
     * @param param
     */
    public void updateByApprStaffGrp(AppApprStaffGroup apprStaffGrp);

    /**
     * 查询审批人员与组关联信状态
     *
     * @param param
     */
    public List<String> queryStaffGrpStat(Map<String, Object> param);

    /**
     * 修改审批人员与组关联信审核状态
     *
     * @param param
     */
    public void updateApprstatByStaffNo(Map<String, Object> param);

    /**
     * 查询审批人员当前的审批状态
     *
     * @param staffNo 人员编号
     * @return
     */
    public int getApprStateByStaffNo(String staffNo);

    /**
     * 根据人员编号更新审批人员状态(审批中)
     *
     * @param staffNo 人员编号
     * @return
     */
    public int updateApprStateByStaffNo(String staffNo);
}