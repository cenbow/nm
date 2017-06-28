package com.nm.mapper.home;

import com.nm.mybatis.annotation.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wangqin
 *
 * @date 2017年5月9日 上午11:40:01
 */
@MyBatisRepository
public interface HomeApiMapper {
    /*查询广告信息*/
    List<Map<String,String>> queryBarnners();
    /*更新banner表*/
    Integer updateBanners(@Param("imageUrl") String imageUrl,@Param("id") String id);

    Integer saveCallLogs(Map<String,Object> map);
    
    Integer queryCustCallLogs(@Param("custNo") String custNo);
    
    Integer updateCallLogs(Map<String,Object> map);
    
    
}
