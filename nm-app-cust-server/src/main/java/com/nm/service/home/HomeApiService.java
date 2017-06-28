package com.nm.service.home;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wangqin
 *
 * @date 2017年5月9日 上午11:39:25
 */
@SuppressWarnings("rawtypes")
public interface HomeApiService {
    List<Map<String,String>> queryBanners();
    void saveCallLogs(List<Map> map,String custNo);
}
