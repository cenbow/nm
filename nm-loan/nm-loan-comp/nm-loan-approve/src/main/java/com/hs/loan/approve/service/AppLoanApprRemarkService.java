package com.hs.loan.approve.service;

import com.hs.loan.approve.entity.AppLoanApprRemark;
import com.hs.loan.approve.mapper.AppLoanApprRemakMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lenovo on 2016/5/24.
 */
@Service
@Transactional(readOnly = true)
public class AppLoanApprRemarkService {
    @Resource
    private AppLoanApprRemakMapper appLoanApprRemakMapper;
    @Transactional
    public int insert(AppLoanApprRemark t){
       return appLoanApprRemakMapper.insert(t);
    }
    @Transactional
    public int update(java.util.Map map){
        return appLoanApprRemakMapper.updateByPrimaryKeySelective(map);
    }
    
    @Transactional
    public void deleteByblockId(String blockId, String loanNo){
          appLoanApprRemakMapper.deleteByBlockId(blockId,loanNo);
    }
    public List<AppLoanApprRemark> queryForList(java.util.Map map){
       return appLoanApprRemakMapper.queryForList(map);
    }
}
