package com.hs.loan.cust.service;

import com.hs.base.entity.Page;
import com.hs.loan.cust.entity.AppCustTravel;
import com.hs.loan.cust.entity.AppCustWork;
import com.hs.loan.cust.mapper.AppCustPeerMapper;
import com.hs.loan.cust.mapper.AppCustTravelMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly=true)
public class AppCustTravelService {
    @Resource
    private AppCustTravelMapper appCustTravelMapper;
    @Transactional
    public int insert(AppCustTravel appCustTravel){
        return appCustTravelMapper.insert(appCustTravel);
    }
    @Transactional
    public int updateByPrimaryKeySelective(Map<String, Object> var1){
        return appCustTravelMapper.updateByPrimaryKeySelective(var1);
    }
    @Transactional
    public int deleteByPrimaryKey(String id){
        return appCustTravelMapper.deleteByPrimaryKey(id);
    }
    public List<AppCustTravel> queryForList(Map<String, Object> param){
        return appCustTravelMapper.queryForList(param);
    }
    public Page<AppCustTravel> queryForPage(Page<AppCustTravel> page){
        appCustTravelMapper.queryForList(page.getPageParams());
        return (Page<AppCustTravel>)page.getPageParams().get(Page.KEY);
    }
}
