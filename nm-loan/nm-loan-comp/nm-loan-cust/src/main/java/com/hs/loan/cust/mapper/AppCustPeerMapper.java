package com.hs.loan.cust.mapper;


import com.hs.base.mapper.BaseMapper;
import com.hs.base.support.mybatis.annotation.MyBatisRepository;
import com.hs.loan.cust.entity.AppCustOtherInfo;
import com.hs.loan.cust.entity.AppCustPeer;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface AppCustPeerMapper extends BaseMapper<AppCustPeer> {
    public int insertList(Map map);
}