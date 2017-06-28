package com.hs.loan.cust.service;

import com.hs.base.entity.Page;
import com.hs.loan.cust.entity.AppCustPeer;
import com.hs.loan.cust.entity.AppCustTravel;
import com.hs.loan.cust.mapper.AppCustPeerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly=true)
public class AppCustPeerService {
    @Resource
    private AppCustPeerMapper appCustPeerMapper;
    @Transactional
    public int insertList(Map map){
        return appCustPeerMapper.insertList(map);
    }
    @Transactional
    public int insert(AppCustPeer appCustPeer){
        return appCustPeerMapper.insert(appCustPeer);
    }
    @Transactional
    public int updateByPrimaryKeySelective(Map<String, Object> var1){
        return appCustPeerMapper.updateByPrimaryKeySelective(var1);
    }
    @Transactional
    public int deleteByPrimaryKey(String id){
        return appCustPeerMapper.deleteByPrimaryKey(id);
    }
    public List<AppCustPeer> queryForList(Map<String, Object> param){
        return appCustPeerMapper.queryForList(param);
    }
    public Page<AppCustPeer> queryForPage(Page<AppCustPeer> page){
        appCustPeerMapper.queryForList(page.getPageParams());
        return (Page<AppCustPeer>)page.getPageParams().get(Page.KEY);
    }
}
