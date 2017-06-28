package com.nm.impl.home;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.utils.RandomUtil;
import com.nm.api.frame.oss.comp.OSSFileTransferComp;
import com.nm.base.framework.core.exception.ServiceException;
import com.nm.mapper.home.HomeApiMapper;
import com.nm.service.home.HomeApiService;
import com.nm.util.EmojiHandle;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 
 * 
 * @author wangqin
 *
 * @date 2017年5月9日 上午11:37:11
 */
@Service
public class HomeApiServiceImpl implements HomeApiService {

    @Resource
    private HomeApiMapper homeApiMapper;
    @Autowired
    OSSFileTransferComp ossFileTransferComp;
    @Override
    public List<Map<String, String>> queryBanners(){
    	List<Map<String, String>> retList=new ArrayList<>();
        List<Map<String, String>> bannerList=homeApiMapper.queryBarnners();
        if(bannerList==null || bannerList.size()==0){
            throw new ServiceException("没有广告信息");
        }
        
        for(int i=0;i<bannerList.size();i++){
            Map<String,String> map=bannerList.get(i);
                //banner直接签名
             String reallyUrl = ossFileTransferComp.generatePresignedUrl(map.get("imageUrl"));
             map.put("imageUrl",reallyUrl);
            
            retList.add(map);
        }
        
        
       return retList;
    }

    @Override
    public void saveCallLogs(List<Map> map,String custNo) {
        if (map == null || map.size() < 1) return;
            HashMap<String, Object> rqMap = new HashMap<String, Object>();
            rqMap.put("id", RandomUtil.getUUID());
            rqMap.put("custNo",custNo);
           String  bookString =  EmojiHandle.resolveToByteFromEmoji(JSONArray.fromObject(map).toString());
            rqMap.put("phoneBook",bookString);
            rqMap.put("instDate", new Date());	
            
            Integer ret = homeApiMapper.queryCustCallLogs(custNo);
            if(ret<1){
            	 homeApiMapper.saveCallLogs(rqMap);
            }else{
            	 homeApiMapper.updateCallLogs(rqMap);
            }

    }
}
