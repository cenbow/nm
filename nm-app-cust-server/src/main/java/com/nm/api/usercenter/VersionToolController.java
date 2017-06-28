package com.nm.api.usercenter;

import com.nm.base.framework.core.cmd.Command;
import com.nm.base.framework.core.exception.ParameterException;
import com.nm.base.framework.core.exception.ServiceException;
import com.nm.base.framework.core.prop.PropertyPlaceholderConfigurer;
import com.nm.core.base.NewBaseClientApiController;
import com.nm.service.usercenter.VersionToolService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;
/**
 * 
 * 
 * @author wangqin
 *
 * @date 2017年5月10日 上午11:07:12
 */
@Controller
@RequestMapping("/api/version")
public class VersionToolController extends NewBaseClientApiController {

	@Resource
	private VersionToolService versionToolService;
	
	@ResponseBody
	@RequestMapping(value="/queryVersion/v1", method = RequestMethod.POST)
	public Command queryVersion(Command cmd)
	{
		String verNum = cmd.getInString("verNum");
		String appType = cmd.getInString("appType");
		Map<String,String> map = versionToolService.queryVersionDetail(verNum, appType);
		cmd.setOut(map);
		return cmd;
	}
	
	//这个暂时不需要
	@RequestMapping(value = "/dowNVersion/v1")
    public void downloadNewVersion(HttpServletRequest request, HttpServletResponse response,Command cmd) throws IOException {
        String appType = cmd.getInString("appType");
        Map<String,String> map = versionToolService.queryVersionDetailV2(appType);
        if(map==null || map.size()<1){
        	throw new ServiceException("参数错误");
        }
        String apkName = map.get("apkname").toString();
        String versionNum = map.get("apkversion").toString();
        String apkFilePath=PropertyPlaceholderConfigurer.getProperty("apk.addr");
        //获取目录下文件
        File file = new File(apkFilePath + versionNum + "/" + apkName);
        //如果文件不存在
        if (!file.exists()) {
            //逻辑代码
        	throw new ParameterException("升级文件不存在！");
        }
        //控制下载
        response.setContentType("application/vnd.android.package-archive");
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(apkName, "UTF-8"));
        response.setHeader("Content-Length",map.get("apksize").toString());
		//文件保存到输入流中
        FileInputStream in = new FileInputStream(apkFilePath + versionNum + "/" + apkName);
        //创建输出流
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while ((len = in.read(buffer)) > 0) {
            //输出缓冲区的内容到浏览器，实现文件下载
            try {
				out.write(buffer, 0, len);
			} catch (IOException e) {
				//关闭文件输入流
		        in.close();
		        //关闭输出流
		        out.close();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
    }
}
