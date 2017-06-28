package com.nm.api.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nm.base.framework.core.cmd.Command;
import com.nm.core.base.NewBaseClientApiController;
import com.nm.service.home.HomeApiService;

/**
 * 
 * 
 * @author wangqin
 *
 * @date 2017年5月9日 上午11:37:23
 */
@Controller
@RequestMapping("/api/home")
public class HomeApiController extends NewBaseClientApiController {

    @Resource
    private HomeApiService homeApiService;

    private static Map<String, String> imageContentType = new HashMap<>();


    static {
        imageContentType.put("jpg", "image/jpeg");
        imageContentType.put("jpeg", "image/jpeg");
        imageContentType.put("png", "image/png");
        imageContentType.put("tif", "image/tiff");
        imageContentType.put("tiff", "image/tiff");
        imageContentType.put("ico", "image/x-icon");
        imageContentType.put("bmp", "image/bmp");
        imageContentType.put("gif", "image/gif");
    }


    /**
     * 查询广告信息
     *
     * @param cmd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryBanners/v1", method = RequestMethod.POST)
    public Command queryBrannersV1(Command cmd) {
        List<Map<String, String>> map = homeApiService.queryBanners();
        cmd.setOut(map);
        return cmd;
    }

    /**
     *保存安卓端抓取通话记录
     * @param cmd
     * @return
     */
   @ResponseBody
    @RequestMapping(value = "/saveCallLogs/v1", method = RequestMethod.POST)
   @SuppressWarnings("rawtypes")
    public Command saveCallLogs(Command cmd) {
	List<Map> rqList=cmd.getInArray("rqArray",Map.class);
       String custNo= cmd.getInString("custNo");
       homeApiService.saveCallLogs(rqList,custNo);
       return cmd;
    }



}
