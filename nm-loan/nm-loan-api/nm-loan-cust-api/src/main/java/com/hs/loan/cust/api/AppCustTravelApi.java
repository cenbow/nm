package com.hs.loan.cust.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hs.base.entity.Page;
import com.hs.loan.cust.dto.AppCustTravelDto;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/2/20.
 */
public interface AppCustTravelApi {
    public AppCustTravelDto getAppCustTravelDto(Map<String, Object> param);
    public int insertOrUpdate(AppCustTravelDto appCustTravelDto);
    public int insert(AppCustTravelDto appCustTravelDto);
    public int updateByPrimaryKeySelective(Map<String, Object> var1);
    public int deleteByPrimaryKey(String id);
    public List<AppCustTravelDto> queryForList(Map<String, Object> param);
    public Page<AppCustTravelDto> queryForPage(Page<AppCustTravelDto> page);
}
