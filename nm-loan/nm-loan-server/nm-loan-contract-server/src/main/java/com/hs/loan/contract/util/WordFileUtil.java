package com.hs.loan.contract.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aliyun.oss.ServiceException;
import com.hs.utils.ParamUtils;

/**
 * 生成world文档工具类
 * @author zwr
 *
 */
public class WordFileUtil {

	private static Log log = LogFactory.getLog(WordFileUtil.class);
	
	/** 
	 * 替换模板 
	 * @param map 标识与替换内容 
	 * @param sourceFilePath 模板路径 
	 * @param targetFilePath 生成Word文档路径 
	 *//*  
	public static FileResult rgModel(Map<String, String> map,String fileName , String sourceFilePath, String targetFilePath) {  

		 字节形式读取模板文件内容,将结果转为字符串   
		String sourcecontent = "";  
		BufferedInputStream ins = null;  
		try {  
			File file = new File(sourceFilePath);
			if (!file.exists()) {  
				logger.debug(WordFileUtil.class.getName()+":源模板文件不存在");
				return null;
			}  
			ins = new BufferedInputStream(new FileInputStream(file) );  
			byte[] b = new byte[1024];  
			int bytesRead = 0;  
			while (true) {  
				bytesRead = ins.read(b, 0, 1024); // return final read bytes  
				// counts  
				if (bytesRead == -1) {// end of InputStream  
					logger.debug(WordFileUtil.class.getName()+":读取模板文件结束");  
					break;  
				}  
				// convert to string using bytes  
				sourcecontent += new String(b, 0, bytesRead);  
			}
			ins.close();
			ins =null;
			 修改变化部分   
			String targetcontent = "";  
			int i = 0;  
			for (String key : map.keySet()) {  
				String value = map.get(key);  
				if (i == 0) {  
					targetcontent = replaceRTF(sourcecontent, key, value);  
				} else {  
					targetcontent = replaceRTF(targetcontent, key, value);  
				}  
				i++;  
			}
			
			 结果输出上传到文件服务器   
			byte[] data = null;
			if (targetcontent.equals("") || targetcontent == "") {  
				data = sourcecontent.getBytes(); 
			} else {  
				data = targetcontent.getBytes();
			}  
			if(data!=null){
				ins = new BufferedInputStream(new ByteArrayInputStream(data));
				String savePath = targetFilePath;
				String saveName = fileName.substring(0,fileName.lastIndexOf("."));
				String extName = fileName.substring(fileName.lastIndexOf("."));
				FileResult fr =FileTransfer.postFileToFileServer(ins, savePath, saveName, extName, "");
				if(fr!=null && fr.getCode().equals(QhxgConstants.FILE_RESULT_SUCCESS_CODE)){
					logger.debug(WordFileUtil.class.getName()+":生成文件 " + targetFilePath + fileName + " 成功"); 
					return fr;
				}else{
					logger.debug(WordFileUtil.class.getName()+":生成文件 " + targetFilePath + fileName + " 失败");
					return null;
				}
			}
		} catch (Exception e) {  
			e.printStackTrace();
			log.error(e);
		}finally{
			try {
				if(ins!=null){
					ins.close();
					ins = null;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}*/
	/** 
	 * 替换模板 
	 * @param map 标识与替换内容 
	 * @param sourceFilePath 模板路径 
	 * @param targetFilePath 生成Word文档路径 
	 */  
	public static void buildFile(Map<String, String> map,String fileName , String sourceFilePath, String targetFilePath) {  
		log.info("*****************************************************************写"+sourceFilePath);
		/* 字节形式读取模板文件内容,将结果转为字符串 */  
		String sourcecontent = "";  
		BufferedInputStream ins = null;  
		try {  
			File file = new File(sourceFilePath);
			if (!file.exists()) {  
				log.info(WordFileUtil.class.getName()+":源模板文件不存在");
				throw new ServiceException("合同模板不存在,请联系管理员");
			}  
			ins = new BufferedInputStream(new FileInputStream(file) );  
			byte[] b = new byte[1024];  
			int bytesRead = 0;  
			while (true) {  
				bytesRead = ins.read(b, 0, 1024); // return final read bytes  
				// counts  
				if (bytesRead == -1) {// end of InputStream  
					log.info(WordFileUtil.class.getName()+":读取模板文件结束");  
					break;  
				}  
				// convert to string using bytes  
				sourcecontent += new String(b, 0, bytesRead);  
			}
			ins.close();
			ins =null;
			/* 修改变化部分 */  
			String targetcontent = "";  
			int i = 0;  
			for (String key : map.keySet()) {  
				String value = (String) map.get(key);  
				if (i == 0) {  
					targetcontent = replaceRTF(sourcecontent, key, value);  
				} else {  
					targetcontent = replaceRTF(targetcontent, key, value);  
				}  
				i++;  
			}
			// 结果输出上传到文件服务器 
			byte[] data = null;
			if (targetcontent.equals("") || targetcontent == "") {  
				data = sourcecontent.getBytes(); 
			} else {  
				data = targetcontent.getBytes();
			}  
			if(data!=null){
				ins = new BufferedInputStream(new ByteArrayInputStream(data));
				File tagefiledir = new File(targetFilePath);
				if(!tagefiledir.exists()){
					tagefiledir.mkdirs();
				}
				File tagefile = new File(targetFilePath+fileName);
				
				FileOutputStream fOut = new FileOutputStream(tagefile);
				byte[] buffer = new byte[1024];
				int len = 0;
				while((len= ins.read(buffer))>0){
					fOut.write(buffer, 0, len);
				}
				fOut.flush();
				fOut.close();
				ins.close();
			}  
		} catch (Exception e) {  
			e.printStackTrace();
			log.error(e);
		}finally{
			try {
				if(ins!=null){
					ins.close();
					ins = null;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/** 
	 * 替换文档的可变部分 
	 *  
	 * @param content 文档内容 
	 * @param key 标识 
	 * @param replacecontent 替换内容 
	 * @return 
	 */  
	private static String replaceRTF(String content, String key, String replacecontent) {  
		String target = "";
		String rc = strToRtf(replacecontent);  
		//如果是图片特殊格式 不转换为rtf编码 原文替换
	    if(key.equals("image")){
	    	target = content.replace(key,replacecontent);
	    }else{
	    	target = content.replace(key, rc);  
	    }
		 
		return target;  
	}  
	
	/** 
	* 字符串转换为rtf编码 
	*  
	* @param content 
	* @return 
	*/  
	private static String strToRtf(String content) {  
		StringBuffer sb = new StringBuffer("");  
		try {  
			char[] digital = "0123456789ABCDEF".toCharArray();  
			byte[] bs = null;
			if (content == null) {
				return sb.toString();
			}
			bs = content.getBytes("GBK");  
			int bit;  
			for (int i = 0; i < bs.length; i++) {  
				bit = (bs[i] & 0x0f0) >> 4;  
				sb.append("\\'");  
				sb.append(digital[bit]);  
				bit = bs[i] & 0x0f;  
				sb.append(digital[bit]);  
			}  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
		return sb.toString();  
	}  
	
}
