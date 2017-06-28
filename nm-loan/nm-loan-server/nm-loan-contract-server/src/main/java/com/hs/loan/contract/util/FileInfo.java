package com.hs.loan.contract.util;

import java.io.Serializable;

/**
 *SYS 2014-04-08 存储的文件的相关信息
 * @author hejian
 * @version 1.0.0
 */
public class FileInfo implements Serializable{
	private static final long serialVersionUID = -315031590230735772L;
	/**
	 * 上传的原文件名称
	 */
	private String originFileName;
	/**
	 * 存储过后的新的文件名
	 */
	private String fileName;
	/**
	 * 文件后缀名
	 */
	private String extName;
	/**
	 * 文件存储路径
	 */
	private String savePath;
	/**
	 * 存储文件服务器的本机ip地址
	 */
	private String fileServerIP;
	
	public FileInfo(){}
	
	public FileInfo(String originFileName, String fileName, 
			String extName, String savePath, String fileServerIP) {
		
		this.originFileName = originFileName;
		this.fileName = fileName;
		this.extName = extName;
		this.savePath = savePath;
		this.fileServerIP = fileServerIP;
	}
	
	public String getOriginFileName() {
		return originFileName;
	}
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getExtName() {
		return extName;
	}
	public void setExtName(String extName) {
		this.extName = extName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getFileServerIP() {
		return fileServerIP;
	}
	public void setFileServerIP(String fileServerIP) {
		this.fileServerIP = fileServerIP;
	}
	
}
