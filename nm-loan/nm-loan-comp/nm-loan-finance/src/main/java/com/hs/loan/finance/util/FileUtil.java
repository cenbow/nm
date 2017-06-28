package com.hs.loan.finance.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import net.sf.json.JSONArray;

/**
 * <p>
 * decribing : 文件工具类
 * </p>
 * <p>
 * copyright : Copyright @ 2013 hansy
 * </p>
 * <p>
 * company : hansy
 * </p>
 * <p>
 * time : 2013-10-31
 * </p>
 *
 * @author hwen
 * @version v1.0
 */
public class FileUtil {

	/**
	 * 批量压缩文件
	 * 
	 * @param files
	 *            文件列表
	 * @param zipfile
	 *            压缩文件路径
	 * @return
	 * @throws Exception
	 */
	public static boolean zipFiles(List<String> files, String zipfile) throws Exception {
		boolean bf = true;

		// 根据文件路径构造一个文件实例
		File ff = new File(zipfile);
		// 判断目前文件是否存在,如果不存在,则新建一个
		if (!ff.exists()) {
			ff.createNewFile();
		}
		// 根据文件路径构造一个文件输出流
		FileOutputStream out = new FileOutputStream(zipfile);
		// 传入文件输出流对象,创建ZIP数据输出流对象
		ZipOutputStream zipOut = new ZipOutputStream(out);

		// 循环待压缩的文件列表
		for (int i = 0; i < files.size(); i++) {
			File f = new File(files.get(i));
			if (!f.exists()) {
				bf = false;
			}
			try {
				// 创建文件输入流对象
				FileInputStream in = new FileInputStream(files.get(i));
				// 得到当前文件的文件名称
				String fileName = files.get(i).substring(files.get(i).lastIndexOf('/') + 1, files.get(i).length());
				// 创建指向压缩原始文件的入口
				ZipEntry entry = new ZipEntry(fileName);
				zipOut.putNextEntry(entry);
				// 向压缩文件中输出数据
				int nNumber = 0;
				byte[] buffer = new byte[512];
				while ((nNumber = in.read(buffer)) != -1) {
					zipOut.write(buffer, 0, nNumber);
				}
				// 关闭创建的流对象
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
				bf = false;
			}
		}
		zipOut.close();
		out.close();
		return bf;
	}

	/**
	 * @param zipFilePath
	 *            文件位置
	 * @param unzPath
	 *            解压后存放位置
	 * @return 返回解压后的文件路径集合
	 */
	public static List<String> unZipFile(String zipFilePath, String unzPath) {
		List<String> lst = new ArrayList<String>();
		try {
			File file = new File(zipFilePath);// 压缩文件
			ZipFile zipFile = new ZipFile(file);// 实例化ZipFile，每一个zip压缩文件都可以表示为一个ZipFile
			// 实例化一个Zip压缩文件的ZipInputStream对象，可以利用该类的getNextEntry()方法依次拿到每一个ZipEntry对象
			ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
			ZipEntry zipEntry = null;
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				String fileName = zipEntry.getName();
				lst.add(unzPath + fileName);
				File temp = new File(unzPath + fileName);
				if (!temp.getParentFile().exists())
					temp.getParentFile().mkdirs();
				OutputStream os = new FileOutputStream(temp);
				// 通过ZipFile的getInputStream方法拿到具体的ZipEntry的输入流
				InputStream is = zipFile.getInputStream(zipEntry);
				int len = 0;
				while ((len = is.read()) != -1)
					os.write(len);
				os.close();
				is.close();
			}
			zipInputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lst;
	}

	/**
	 * 读取加密TXT文件内容并解密
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readTxtFile(String filePath) throws Exception {
		File file = new File(filePath);
		String result = "";
		if (!file.exists()) {
			System.err.println(filePath + "不存在");
		} else {
			BufferedReader bufferedReader = null;
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				try {
					String read = "";
					while ((read = bufferedReader.readLine()) != null) {
						result += read + "\r\n";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			}
		}
		System.out.println("读取出来的加密文件内容是：" + "\r\n" + result);
		String outputStr = new String(result);
		System.err.println("解密后:\t" + outputStr);
		// 解密
		return outputStr;
	}

	/**
	 * 将文本写入文件并加密
	 * 
	 * @param content
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static boolean writeTxtFile(String filePath, String content) throws Exception {
		File file = new File(filePath);
		boolean flag = false;
		OutputStreamWriter o = null;
		try {
			o = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			o.write(content);
			o.close();
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 追加内容至原文件末尾
	 * 
	 * @param filePath
	 * @param content
	 */
	public static void contentToTxt(String filePath, String content) {
		try {
			// 读取新解密出来的文件
			File f = new File(filePath);
			if (!f.exists()) {
				f.createNewFile();// 不存在则创建
				System.out.print(filePath + "不存在");
			}
			// 追加内容
			BufferedWriter output = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(filePath, true), "UTF-8"));
			output.write((content + "\r\n"));
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读TXT文件内容封装对象
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readTxtFileToBean(String filePath) throws Exception {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		isFolder(filePath);
		// 原有文件
		File fileOld = new File(filePath);
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new InputStreamReader(new FileInputStream(fileOld), "UTF-8"));
			String str = "";
			String read = "";
			while ((read = bf.readLine()) != null) {
				str = str + read;
			}
			System.err.println("解密前:\r\n" + str);
			// 解密原文件
			String outputStr = new String(str.getBytes(), "UTF-8");
			System.err.println("解密后:\r\n" + outputStr);
			String[] datas = outputStr.split("\r\n");
			for (int i = 0; i < datas.length; i++) {
				Map<String, String> map = new HashMap<String, String>();
				String[] data = datas[i].split("\\|");
				for (int j = 0; j < data.length; j++) {
					String[] fileds = data[j].split(":");
					map.put(fileds[0], fileds[1]);
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bf != null) {
				bf.close();
			}
		}
		// 转Json字符串
		JSONArray array = JSONArray.fromObject(list);
		return array.toString();
	}

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            String 如 c:/fqf
	 * @return boolean
	 */
	public static void newFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
		} catch (Exception e) {
			System.out.println("新建目录操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String 文件内容
	 * @return boolean
	 * @throws IOException
	 */
	public static void newFile(String filePathAndName, String fileContent) throws IOException {
		String filePath = filePathAndName;
		filePath = filePath.toString();
		File myFilePath = new File(filePath);
		if (!myFilePath.exists()) {
			myFilePath.createNewFile();
		}
		FileWriter resultFile = new FileWriter(myFilePath);
		PrintWriter myFile = new PrintWriter(resultFile);
		String strContent = fileContent;
		myFile.println(strContent);
		resultFile.close();
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 * @throws IOException
	 */
	public static void copyFile(String oldPath, String newPath) throws IOException {
		int bytesum = 0;
		int byteread = 0;

		File oldfile = new File(oldPath);
		if (oldfile.exists()) { // 文件存在时
			isFolder(newPath);
			FileInputStream inStream = new FileInputStream(oldPath); // 读入原文件
			FileOutputStream fs = new FileOutputStream(newPath);
			byte[] buffer = new byte[1444];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread; // 字节数 文件大小
				System.out.println(bytesum);
				fs.write(buffer, 0, byteread);
			}
			inStream.close();
		}

	}

	/**
	 * 判断文件细致是否存在，如果不存在则创建文件夹
	 * 
	 * @param newPath
	 *            包含文件在内的路径 c:/fqf.txt
	 */
	public static void isFolder(String newPath) {
		int index = newPath.lastIndexOf('\\');
		String path = newPath.substring(0, index);
		File myFilePath = new File(path);
		if (!myFilePath.exists()) {
			myFilePath.mkdir();
		}
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		String filePath = filePathAndName;
		filePath = filePath.toString();
		File myDelFile = new File(filePath);
		myDelFile.delete();
	}

	/**
	 * 删除文件夹
	 * 
	 * @param filePathAndName
	 *            String 文件夹路径及名称 如c:/fqf
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFolder(String folderPath) {
		delAllFile(folderPath); // 删除完里面所有内容
		String filePath = folderPath;
		filePath = filePath.toString();
		File myFilePath = new File(filePath);
		myFilePath.delete(); // 删除空文件夹
	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 *            String 文件夹路径 如 c:/fqf
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 * @throws IOException
	 */
	public static void moveFile(String oldPath, String newPath) throws IOException {
		copyFile(oldPath, newPath);
		delFile(oldPath);
	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
	}

	/**
	 * 判断字符串的编码
	 *
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return encode;
	}

}
