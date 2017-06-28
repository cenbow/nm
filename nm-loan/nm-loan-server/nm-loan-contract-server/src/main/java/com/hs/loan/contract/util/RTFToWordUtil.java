package com.hs.loan.contract.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hs.base.exception.ServiceException;

 /**
 * 将RTF模板转换成Word
 *
 */ 
public class RTFToWordUtil {  
	private static Log log = LogFactory.getLog(RTFToWordUtil.class);
     
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
		if(key.equals("image")||key.equals("twoIMG")||key.equals("ewm")){
	    	target = content.replace(key,replacecontent);
	    }else{
	    	target = content.replace(key, rc);  
	    }
		 
		return target;  
	}  

	 /** 
	 * 半角转为全角 
	 */   
	private String ToSBC(String input) {  
		char[] c = input.toCharArray();  
		for (int i = 0; i < c.length; i++) {  
			if (c[i] == 32) {  
				c[i] = (char) 12288;  
				continue;  
			}  
			if (c[i] < 127) {  
				c[i] = (char) (c[i] + 65248);  
			}  
		}  
		return new String(c);  
	}  

	 /** 
	 * 替换模板 
	 * @param map 标识与替换内容 
	 * @param sourceFilePath 模板路径 
	 * @param targetFilePath 生成Word文档路径 
	 */  
	public static void rgModel(Map<String, String> map,String fileName , String sourceFilePath, String targetFilePath) {  

		// 字节形式读取模板文件内容,将结果转为字符串   
		String sourcecontent = "";  
		InputStream ins = null;  
		try {  
			ins = new FileInputStream(sourceFilePath);  
			byte[] b = new byte[1024];  
			if (ins == null) {  
				System.out.println(RTFToWordUtil.class.getName()+":源模板文件不存在");  
			}  
			int bytesRead = 0;  
			while (true) {  
				bytesRead = ins.read(b, 0, 1024); // return final read bytes  
				// counts  
				if (bytesRead == -1) {// end of InputStream  
					System.out.println(RTFToWordUtil.class.getName()+":读取模板文件结束");  
					break;  
				}  
				// convert to string using bytes  
				sourcecontent += new String(b, 0, bytesRead);  
			}  
		} catch (Exception e) {  
			e.printStackTrace();
			try {
				ins.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			log.error(e);
			throw new ServiceException(e.getMessage());
		}finally {
			try {
				ins.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 

		// 修改变化部分   
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
		FileWriter fw = null;
		PrintWriter out = null;
		// 结果输出保存到文件   
		try {  
			File f1 = new File(targetFilePath);
			if (!f1.exists()) {
				f1.mkdirs();
			}
			 fw = new FileWriter(targetFilePath + fileName, true);  
			  out = new PrintWriter(fw);  

			if (targetcontent.equals("") || targetcontent == "") {  
				out.println(sourcecontent);  
			} else {  
				out.println(targetcontent);  
			}  
			out.close();  
			fw.close();  
			System.out.println(RTFToWordUtil.class.getName()+":生成文件 " + targetFilePath + fileName + " 成功");  
		} catch (IOException e) {  
			e.printStackTrace();  
			log.error(e);
			throw new ServiceException(e.getMessage());
		}finally{
			try {
				fw.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	} 
	
	/**
	 * 将图片转换成rtf格式字符串
	 * @param imgPath 图片路径 包含名称
	 * @param type null代表条码 其他代表头像
	 * @return 字符串
	 */
	public static StringBuffer replaceStringToImage(String savePath , String fileName,Object type,String fileServerAddr){
		StringBuffer result = new StringBuffer();
		StringBuffer restr= new StringBuffer();
		try{
			
			if(type == null){
				InputStream is = new FileInputStream(new File(savePath,fileName));
				int i = 0;
				while ((i = is.read()) != -1) {
				    String str = i < 16 ? "0" + Integer.toHexString(i) : Integer.toHexString(i);
				    restr.append(str);
				}
				result.append("{\\*\\shppict{\\pict{\\*\\picprop\\shplid1025{\\sp{\\sn shapeType}{\\sv 75}}{\\sp{\\sn fFlipH}{\\sv 0}}"+
				"{\\sp{\\sn fFlipV}{\\sv 0}}{\\sp{\\sn fRotateText}{\\sv 1}}{\\sp{\\sn pictureGray}{\\sv 0}}{\\sp{\\sn pictureBiLevel}{\\sv 0}}{\\sp{\\sn fFilled}{\\sv 0}}{\\sp{\\sn fNoFillHitTest}{\\sv 0}}"+
				"{\\sp{\\sn fLine}{\\sv 0}}{\\sp{\\sn wzName}{\\sv \\'cd\\'bc\\'c6\\'ac 1}}{\\sp{\\sn wzDescription}{\\sv C:\\'5ctest\\'5c2.jpeg}}{\\sp{\\sn fHidden}{\\sv 0}}{\\sp{\\sn fLayoutInCell}{\\sv 1}}}\\picscalex60\\picscaley54\\piccropl0\\piccropr0\\piccropt0\\piccropb0"+
				"\\picw6297\\pich1588\\picwgoal3570\\pichgoal900\\jpegblip\\bliptag554110001{\\*\\blipuid 21070c31dd070a370466a36b205443b0}");
				
			}else{
				//头像格式 代码
				restr.append(XianChangZhaoHelper.getXianChangZhaoFromFileServer(fileServerAddr));
				result.append("{\\*\\shppict"+
					"{\\pict{\\*\\picprop\\shplid1026{\\sp{\\sn shapeType}{\\sv 75}}{\\sp{\\sn fFlipH}{\\sv 0}}{\\sp{\\sn fFlipV}{\\sv 0}}{\\sp{\\sn fLockAspectRatio}{\\sv 1}}{\\sp{\\sn fLockPosition}{\\sv 0}}{\\sp{\\sn fLockAgainstSelect}{\\sv 0}}{\\sp{\\sn fLockAgainstGrouping}{\\sv 0}}"+
					"{\\sp{\\sn pictureGray}{\\sv 0}}{\\sp{\\sn pictureBiLevel}{\\sv 0}}{\\sp{\\sn fFilled}{\\sv 0}}{\\sp{\\sn fNoFillHitTest}{\\sv 0}}{\\sp{\\sn fLine}{\\sv 0}}{\\sp{\\sn wzName}{\\sv \\'cd\\'bc\\'c6\\'ac 1}}{\\sp{\\sn wzDescription}{\\sv \\'cb\\'b5\\'c3\\'f7:"+
					" \\'cb\\'b5\\'c3\\'f7: \\'cb\\'b5\\'c3\\'f7: \\'cb\\'b5\\'c3\\'f7: \\'cb\\'b5\\'c3\\'f7: \\'cb\\'b5\\'c3\\'f7: \\'cb\\'b5\\'c3\\'f7: \\'cb\\'b5\\'c3\\'f7: \\'cb\\'b5\\'c3\\'f7: \\'cb\\'b5\\'c3\\'f7: \\'cb\\'b5\\'c3\\'f7:\\'cb\\'b5\\'c3\\'f7: C:\\'5cUsers\\'5cAdministrato"+
					"r\\'5cAppData\\'5cRoaming\\'5cTencent\\'5cUsers\\'5c396183689\\'5cQQ\\'5cWinTemp\\'5cRichOle\\'5cEA][~IPK3E%UH7TBUW5J\\'7b\\'7b1.jpg}}{\\sp{\\sn dhgt}{\\sv 251658240}}{\\sp{\\sn fHidden}{\\sv 0}}{\\sp{\\sn fLayoutInCell}{\\sv 1}}}"+
					"\\picscalex33\\picscaley60\\piccropl0\\piccropr0\\piccropt0\\piccropb0\\picw8652\\pich3440\\picwgoal4905\\pichgoal1950\\jpegblip\\bliptag1776870445{\\*\\blipuid 69e8e42d1ad2f5ffa23dd0d3d45d8de6}");
			}
			result.append(restr);
			result.append("}}");
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	    
	    return result;
	   
	}
	
}  
 