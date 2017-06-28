package com.hs.loan.cust.server;

import com.alibaba.dubbo.common.json.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hs.base.entity.Page;
import com.hs.base.exception.ServiceException;
import com.hs.loan.cust.api.AppCustPeerApi;
import com.hs.loan.cust.dto.AppCustPeerDto;
import com.hs.loan.cust.dto.AppCustTravelDto;
import com.hs.loan.cust.entity.AppCustPeer;
import com.hs.loan.cust.entity.AppCustTravel;
import com.hs.loan.cust.service.AppCustPeerService;
import com.hs.utils.RandomUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly=true)
public class AppCustPeerServer implements AppCustPeerApi{
    @Resource
    private AppCustPeerService appCustPeerService;
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
    private String threeNull(Object obj) {
        return (null == obj || "".equalsIgnoreCase(obj.toString().trim())) ? null : obj.toString().trim();
    }
    @Transactional
    public int insertList(List<AppCustPeerDto> list){
        if(null==list||0>=list.size())return 0;
        List<AppCustPeer> peerList = (List<AppCustPeer>)reference(list, new TypeReference<List<AppCustPeer>>(){});
        if(null!=peerList&&0<peerList.size()){
            for (AppCustPeer peer : peerList) {
                if(null==threeNull(peer.getId()))peer.setId(RandomUtil.getUUID());
                if(null==peer.getInstDate())peer.setInstDate(new Date());
                if(null==peer.getUpdtDate())peer.setUpdtDate(new Date());
                if(null==threeNull(peer.getStatus()))peer.setStatus("10002001");
                if(null==threeNull(peer.getRemark()))peer.setRemark("");
            }
        }
        Map map=new HashMap();
        map.put("list",peerList);
        return appCustPeerService.insertList(map);
    }
    @Transactional
    public int insertOrUpdate(AppCustPeerDto appCustPeerDto){
        Map updateMap=(Map)reference(appCustPeerDto, new TypeReference<Map>() {});
        if(null!=appCustPeerDto.getId())return updateByPrimaryKeySelective(updateMap);
        if(null==appCustPeerDto.getId())return insert(appCustPeerDto);
        return 0;
    }
    @Transactional
    public int insert(AppCustPeerDto appCustPeerDto){
        if(null==appCustPeerDto.getId())appCustPeerDto.setId(RandomUtil.getUUID());
        if(null==appCustPeerDto.getInstDate())appCustPeerDto.setInstDate(new Date());
        AppCustPeer appCustPeer=(AppCustPeer)reference(appCustPeerDto, new TypeReference<AppCustPeer>() {
        });
        return appCustPeerService.insert(appCustPeer);
    }
    @Transactional
    public int updateByPrimaryKeySelective(Map<String, Object> var1){
        return appCustPeerService.updateByPrimaryKeySelective(var1);
    }
    @Transactional
    public int deleteByPrimaryKey(String id){
        return appCustPeerService.deleteByPrimaryKey(id);
    }
    public AppCustPeerDto getAppCustPeerDto(Map<String, Object> param){
        return queryForList(param).get(0);
    }
    public List<AppCustPeerDto> queryForList(Map<String, Object> param){
        List<AppCustPeerDto> list=(List<AppCustPeerDto>)reference(appCustPeerService.queryForList(param), new TypeReference<List<AppCustPeerDto>>() {});
        return list;
    }
    public Page<AppCustPeerDto> queryForPage(Page<AppCustPeerDto> page){
       return appCustPeerService.queryForPage(page.toPage(AppCustPeer.class)).toPage(AppCustPeerDto.class);
    }
}
