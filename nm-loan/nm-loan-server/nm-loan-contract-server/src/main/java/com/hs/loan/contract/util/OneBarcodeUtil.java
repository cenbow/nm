package com.hs.loan.contract.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code93Encoder;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;

/**
 * <p>decribing : 条形码生成代码 使用jbarcode-0.2.8.jar 支持EAN13, EAN8, UPCA, UPCE, Code 3 of 9, Codabar, Code 11, Code 93, Code 128, MSI/Plessey, Interleaved 2 of 5, PostNet等</p>
 * <p>copyright : Copyright @ 2012 hansy</p>
 * <p>company   : hansy</p>
 * <p>date      : 2014-1-17</p>
 *
 * @author hwen
 * @version v1.0
 */
public class OneBarcodeUtil {  
	  
    public static void main(String[] paramArrayOfString){  
	    try {  
//	    	JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(), WidthCodedPainter.getInstance(), EAN13TextPainter.getInstance());  
//	    	//生成. 欧洲商品条码(=European Article Number)  
//	    	//这里我们用作图书条码  
//	    	String str = "201309210067";  
//	    	BufferedImage localBufferedImage = localJBarcode.createBarcode(str);  
//	    	saveToGIF(localBufferedImage, "EAN13.gif");  
//	    	
//	    	//条形码类型
//	    	localJBarcode.setEncoder(Code39Encoder.getInstance());  
//	    	localJBarcode.setPainter(WideRatioCodedPainter.getInstance());  
//	    	localJBarcode.setTextPainter(BaseLineTextPainter.getInstance());  
//	    	localJBarcode.setShowCheckDigit(false);  
//	    	//xx  
//	    	str = "201309210067";  
//	    	localBufferedImage = localJBarcode.createBarcode(str);  
//	    	saveToPNG(localBufferedImage, "Code39.png");  
	  
	    	
//	    	saveToJPEG(localBufferedImage,"localBufferedImage.doc");
	    	String [] str = {"258020134529020000515"
	    			,"20130529000018"
	    			,"20130529000020"
	    			,"20130529000021"
	    			,"20130529000022"
	    			,"20130529000024"
	    			,"20130529000025"
	    			,"20130529000026"
	    			,"20130529000027"
	    			,"20130529000029"
	    			,"20130529000031"
	    			,"20130529000032"
	    			,"20130529000034"
	    			,"20130529000035"
	    			,"20130529000037"
	    			,"20130529000038"
	    			,"20130529000040"
	    			,"20130529000041"
	    			,"20130529000045"
	    			,"20130529000046"
	    			,"20130529000051"
	    			,"20130529000052"
	    			,"20130529000056"
	    			,"20130529000058"
	    			,"20130529000059"
	    			,"20130529000060"
	    			,"20130529000061"
	    			,"20130529000063"
	    			,"20130529000066"};  
	    	for (String string : str) {
	    		createCodeAndSaveToFile("c:/test/", string+".jpeg", "jpeg", string);
	    		return;
			}
	    	
	    	
	    } catch (Exception localException){  
	    	localException.printStackTrace();  
	    }  
    }  
  
    static void saveToJPEG(BufferedImage paramBufferedImage, String paramString){  
    	saveToFile(paramBufferedImage, paramString, "jpeg");  
    }  
  
    static void saveToPNG(BufferedImage paramBufferedImage, String paramString){  
    	saveToFile(paramBufferedImage, paramString, "png");  
    }  
  
    static void saveToGIF(BufferedImage paramBufferedImage, String paramString){  
    	saveToFile(paramBufferedImage, paramString, "gif");  
    }  
  
    static void saveToFile(BufferedImage paramBufferedImage, String paramString1, String paramString2){  
	    try{  
	    	FileOutputStream localFileOutputStream = new FileOutputStream("d:\\" + paramString1);  
	    	ImageUtil.encodeAndWrite(paramBufferedImage, paramString2, localFileOutputStream, 1360, 1360);  
	    	localFileOutputStream.close();  
	    } catch (Exception localException){  
	    	localException.printStackTrace();  
	    }  
    }  
    
    
    /**
     * 根据分期编号生成条码保存到文件
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @param paramString2 文件格式
     * @param str 条码内容
     */
    public static void createCodeAndSaveToFile(String filePath,String fileName, String fileType,String strContext){
    	try{  
    		JBarcode localJBarcode = new JBarcode(Code93Encoder.getInstance(), WidthCodedPainter.getInstance(), EAN13TextPainter.getInstance());  
    		BufferedImage localBufferedImage = localJBarcode.createBarcode(strContext);  
    		//条形码类型
//	    	localJBarcode.setEncoder(Code39Encoder.getInstance());  
//	    	localJBarcode.setPainter(WideRatioCodedPainter.getInstance());  
//	    	localJBarcode.setTextPainter(BaseLineTextPainter.getInstance());  
//	    	localJBarcode.setShowCheckDigit(false);  
    		File file = new File(filePath);
    		if(!file.exists()){
    			file.mkdirs();
    		}
	    	FileOutputStream localFileOutputStream = new FileOutputStream(filePath + fileName);  
	    	ImageUtil.encodeAndWrite(localBufferedImage, fileType, localFileOutputStream, 24, 24);  
	    	localFileOutputStream.close();  
	    } catch (Exception localException){  
	    	localException.printStackTrace();  
	    }  
    }
  
}  