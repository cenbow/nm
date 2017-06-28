package com.hs.loan.cust.server;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.api.AppCustTravelApi;
import com.hs.loan.cust.dto.AppCustPeerDto;
import com.hs.loan.cust.dto.AppCustTravelDto;
import com.hs.loan.cust.dto.CustCallRegisterDto;
import com.hs.loan.cust.entity.AppCustCallRegister;
import com.hs.loan.cust.entity.AppCustPeer;
import com.hs.loan.cust.entity.AppCustTravel;
import com.hs.loan.cust.service.AppCustTravelService;
import com.hs.utils.RandomUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly=true)
public class AppCustTravelServer implements AppCustTravelApi{
    @Resource
    private AppCustTravelService appCustTravelService;
    public <T> T reference(Object obj,TypeReference<T> typeRef){
        if(null==obj)return null;
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            T t = (T)objectMapper.readValue(objectMapper.writeValueAsString(obj),typeRef);
            return t;
        }catch (Exception e){
            throw new ServiceException("转换异常");
        }
    }
    @Transactional
    public int insertOrUpdate(AppCustTravelDto appCustTravelDto){
        Map updateMap=(Map)reference(appCustTravelDto, new TypeReference<Map>() {
        });
        if(null!=appCustTravelDto.getId())updateByPrimaryKeySelective(updateMap);
        if(null==appCustTravelDto.getId())insert(appCustTravelDto);
        return 0;
    }
    @Transactional
    public int insert(AppCustTravelDto appCustTravelDto){
        if(null==appCustTravelDto.getCustNo())throw new ServiceException("客户编号不能为空！");
        if(null==appCustTravelDto.getId())appCustTravelDto.setId(RandomUtil.getUUID());
        if(null==appCustTravelDto.getInstDate())appCustTravelDto.setInstDate(new Date());
        AppCustTravel AppCustTravel=(AppCustTravel)reference(appCustTravelDto, new TypeReference<AppCustTravel>() {});
        return appCustTravelService.insert(AppCustTravel);
    }
    @Transactional
    public int updateByPrimaryKeySelective(Map<String, Object> var1){
        return appCustTravelService.updateByPrimaryKeySelective(var1);
    }
    @Transactional
    public int deleteByPrimaryKey(String id){
        return appCustTravelService.deleteByPrimaryKey(id);
    }
    public AppCustTravelDto getAppCustTravelDto(Map<String, Object> param){
        AppCustTravelDto appCustTravelDto = queryForList(param).get(0);
        return appCustTravelDto;
    }
    public List<AppCustTravelDto> queryForList(Map<String, Object> param) {
        List<AppCustTravel> appCustTravels = appCustTravelService.queryForList(param);
        return (List<AppCustTravelDto>) reference(appCustTravels, new TypeReference<List<AppCustTravelDto>>() {});
    }
    public Page<AppCustTravelDto> queryForPage(Page<AppCustTravelDto> page){
        return appCustTravelService.queryForPage(page.toPage(AppCustTravel.class)).toPage(AppCustTravelDto.class);
    }
}
