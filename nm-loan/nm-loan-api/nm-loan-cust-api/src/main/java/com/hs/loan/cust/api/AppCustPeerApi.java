package com.hs.loan.cust.api;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hs.base.entity.Page;
import com.hs.loan.cust.dto.AppCustPeerDto;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AppCustPeerApi {
    public int insertList(List<AppCustPeerDto> list);
    public AppCustPeerDto getAppCustPeerDto(Map<String, Object> param);
    public int insertOrUpdate(AppCustPeerDto appCustPeerDto);
    public int insert(AppCustPeerDto appCustPeerDto);
    public int updateByPrimaryKeySelective(Map<String, Object> var1);
    public int deleteByPrimaryKey(String id);
    public List<AppCustPeerDto> queryForList(Map<String, Object> param);
    public Page<AppCustPeerDto> queryForPage(Page<AppCustPeerDto> page);
}
