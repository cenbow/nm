/**
 * 
 */
package com.hs.loan.finance.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hs.base.exception.ServiceException;
import com.hs.utils.DateUtils;
import com.hs.utils.ParamUtils;

/**
 * <p>
 * decribing : 银联批量代扣对账工具类
 * </p>
 * <p>
 * copyright : Copyright @ 2012 hansy
 * </p>
 * <p>
 * company : hansy
 * </p>
 * <p>
 * time : 2013-8-1
 * </p>
 *
 * @author hwen
 * @version v1.0
 */
public class EBPPUtil {
	/**
	 * 生成批量代扣文件
	 * 
	 * @param lisObj
	 *            代扣bean
	 * @param template
	 *            模板，按顺序生成
	 * @param join
	 *            分隔符
	 * @param createFile
	 *            生成的文件
	 * @throws ServiceException
	 */
	public static BigDecimal expFileBean(String batchNo, List<?> lisObj, String[] template, String join,
			String createFile) throws ServiceException {
		try {
			// 输出文件
			File f1 = new File(createFile.substring(0, createFile.lastIndexOf("/")));
			if (!f1.exists()) {
				f1.mkdirs();
			}
			FileOutputStream fileOut = new FileOutputStream(createFile);
			List<String> fileLst = new ArrayList<String>();
			BigDecimal totalAmt = new BigDecimal(0);
			DecimalFormat format = new DecimalFormat("0.00");
			for (int i = 0; i < lisObj.size(); i++) {
				// 讲Map转为List
				Object objMap = lisObj.get(i);
				Map<String, String> beanMap = BeanRefUtil.getFieldValueMap(objMap);
				// 将template组装成代扣字符串
				String fileStr = "";
				for (String temp : template) {
					fileStr += temp + join;
				}
				String fileIn = fileStr.substring(0, fileStr.length() - join.length());
				// 替换模板的bean
				for (String temp : template) {
					// 组成16位的序号,规则8位日期，2位小时，6位顺序号
					if (temp.equalsIgnoreCase("#orderNum#")) {
						fileIn = fileIn.replaceAll("#orderNum#", batchNo + EBPPUtil.getOrder(i));
					} else {
						// 替换每月还款
						if (temp.equalsIgnoreCase("#repayAmtYh#")) {
							String repayAmtYhStr = format.format(Double.valueOf(beanMap.get("repayAmtYh")));
							fileIn = fileIn.replaceAll("#repayAmtYh#", repayAmtYhStr);
						}
						// 替换beanMap的内容
						else if (temp != null && !temp.isEmpty() && temp.indexOf("#") >= 0
								&& beanMap.containsKey(temp.substring(1, temp.length() - 1))) {
							fileIn = fileIn.replaceAll(temp, beanMap.get(temp.substring(1, temp.length() - 1)) == null
									? "" : beanMap.get(temp.substring(1, temp.length() - 1)));
						}
					}
				}
				// 计算总金额
				totalAmt = totalAmt.add(BigDecimal.valueOf(Double.valueOf(beanMap.get("repayAmtYh"))));
				// 写入结果文件
				fileLst.add(fileIn);
			}
			// 头文件，商户号15位，批次号01
			// 例700000000000001|01|0||20110401|100.00|10||
			fileOut.write(("EBPP.marNo" + "|" + batchNo + "|0|" + DateUtil.formatDate(new Date(), "yyyyMMdd") + "|"
					+ format.format(totalAmt) + "|" + lisObj.size() + "||").getBytes("gb2312"));
			fileOut.write(0x0D);
			fileOut.write(0x0A);
			// 文件体
			for (String str : fileLst) {
				fileOut.write(str.getBytes("GB18030"));
				// 换行符
				fileOut.write(0x0D);
				fileOut.write(0x0A);
			}
			fileOut.close();
			return totalAmt;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 生成白名单
	 * 
	 * @param lisObj
	 *            代扣bean
	 * @param template
	 *            模板，按顺序生成
	 * @param join
	 *            分隔符
	 * @param createFile
	 *            生成的文件
	 * @throws ServiceException
	 */
	public static void expWhiteFileBean(String batchNo, List<?> lisObj, String[] template, String join,
			String createFile) throws ServiceException {
		try {
			// 输出文件
			File f1 = new File(createFile.substring(0, createFile.lastIndexOf("/")));
			if (!f1.exists()) {
				f1.mkdirs();
			}
			FileOutputStream fileOut = new FileOutputStream(createFile);
			List<String> fileLst = new ArrayList<String>();
			for (int i = 0; i < lisObj.size(); i++) {
				// 讲Map转为List
				Object objMap = lisObj.get(i);
				Map<String, String> beanMap = BeanRefUtil.getFieldValueMap(objMap);
				// 将template组装成代扣字符串
				String fileStr = "";
				for (String temp : template) {
					fileStr += temp + join;
				}
				String fileIn = fileStr.substring(0, fileStr.length() - join.length());
				// 替换模板的bean
				for (String temp : template) {
					// 组成16位的序号,规则8位日期，2位小时，6位顺序号
					if (temp.equalsIgnoreCase("#ebppMarNo#")) {
						fileIn = fileIn.replaceAll("#ebppMarNo#", ParamUtils.getParam("EBPP.marNo"));
					} else {
						// 替换beanMap的内容ebppMarNo
						if (temp != null && !temp.isEmpty() && temp.indexOf("#") >= 0
								&& beanMap.containsKey(temp.substring(1, temp.length() - 1))) {
							fileIn = fileIn.replaceAll(temp, beanMap.get(temp.substring(1, temp.length() - 1)) == null
									? "" : beanMap.get(temp.substring(1, temp.length() - 1)));
						}
					}
				}
				// 写入结果文件
				fileLst.add(fileIn);
			}
			// 文件体
			for (String str : fileLst) {
				fileOut.write(str.getBytes("gb2312"));
				// 换行符
				fileOut.write(0x0D);
				fileOut.write(0x0A);
			}
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	private static String getOrder(int i) {
		// 组成16位的序号,规则8位日期，2位小时，6位顺序号
		// 8位日期
		String today = DateUtil.formatDate(new Date(), "yyyyMMdd");
		// 2位小时
		String hour = DateUtil.getDate().getHours() < 10 ? "0" + DateUtil.getDate().getHours()
				: DateUtil.getDate().getHours() + "";
		String orderNum = today + hour;
		if (i < 10) {
			orderNum += "00000" + i;
		} else if (i < 100) {
			orderNum += "0000" + i;
		} else if (i < 1000) {
			orderNum += "000" + i;
		} else if (i < 10000) {
			orderNum += "00" + i;
		} else if (i < 100000) {
			orderNum += "0" + i;
		} else {
			orderNum += i + "";
		}
		return orderNum;
	}

	/**
	 * 根据新的代扣明细导出代扣文件
	 * 
	 * @param batchNo
	 *            批次
	 * @param lisObj
	 *            数据
	 * @param template
	 *            替换字符
	 * @param join
	 *            分隔符
	 * @param batchNo
	 *            批次号
	 * @return
	 * @throws ServiceException
	 */
	public static Object[] expFileBeanByRepayDk(List<?> lisObj, String[] template, String join, String batchNo)
			throws ServiceException {
		try {
			Object[] obj = new Object[2];
			String downloadPath = ParamUtils.getParam("codeFilePath") + DateUtils.getCurDate() + File.separator;
			// String downloadPath = "D:/data"+File.separator;
			// 文件名与生成文件的内容格式需要根据前台勾选的扣款渠道去匹配

			String fileName = ParamUtils.getParam("EBPP.allInPayMarNo") + "_S02" + DateUtil.getDay("yyyyMMdd") + "_"
					+ batchNo + ".txt";
			String createFile = downloadPath + fileName;
			// 输出文件
			File f1 = new File(createFile.substring(0, createFile.lastIndexOf("/")));
			if (!f1.exists()) {
				f1.mkdirs();
			}
			FileOutputStream fileOut = new FileOutputStream(createFile);
			List<String> fileLst = new ArrayList<String>();
			BigDecimal totalAmt = new BigDecimal(0);
			DecimalFormat format = new DecimalFormat("0.00");
			for (int i = 0; i < lisObj.size(); i++) {
				// 讲Map转为List
				Object objMap = lisObj.get(i);
				Map<String, String> beanMap = BeanRefUtil.getFieldValueMap(objMap);
				// 将template组装成代扣字符串
				String fileStr = "";
				for (String temp : template) {
					fileStr += temp + join;
				}
				String fileIn = fileStr.substring(0, fileStr.length() - join.length());
				// 替换模板的bean
				for (String temp : template) {
					// 替换每月还款
					if (temp.equalsIgnoreCase("#curRcvAmt#")) {
						String repayAmtYhStr = format.format(Double.valueOf(beanMap.get("curRcvAmt")));
						fileIn = fileIn.replaceAll("#curRcvAmt#", repayAmtYhStr.replace(".", ""));
					} else if (temp.equalsIgnoreCase("#serialNum#")) {// 替换序号记录
						fileIn = fileIn.replaceAll("#serialNum#", String.format("%06d", i + 1));
					}
					// 替换beanMap的内容
					else if (temp != null && !temp.isEmpty() && temp.indexOf("#") >= 0
							&& beanMap.containsKey(temp.substring(1, temp.length() - 1))) {
						fileIn = fileIn.replaceAll(temp, beanMap.get(temp.substring(1, temp.length() - 1)) == null ? ""
								: beanMap.get(temp.substring(1, temp.length() - 1)));
					}
				}
				// 计算总金额
				totalAmt = totalAmt.add(BigDecimal.valueOf(Double.valueOf(beanMap.get("curRcvAmt"))));
				// 写入结果文件
				fileLst.add(fileIn);
			}
			// 头文件，商户号15位，批次号01
			// 例700000000000001|01|0||20110401|100.00|10||
			// 例S,012345678901234,20090708,1,1,10600

			fileOut.write(("S" + "," + ParamUtils.getParam("EBPP.allInPayMarNo") + ","
					+ DateUtil.formatDate(new Date(), "yyyyMMdd") + "," + lisObj.size() + ","
					+ format.format(totalAmt).replace(".", "") + ",19900").getBytes("gb2312"));
			fileOut.write(0x0D);
			fileOut.write(0x0A);
			// 文件体
			for (String str : fileLst) {
				fileOut.write(str.getBytes("GB18030"));
				// 换行符
				fileOut.write(0x0D);
				fileOut.write(0x0A);
			}
			fileOut.close();
			obj[0] = fileName;// 下载时需要生成的文件名
			obj[1] = new FileInputStream(createFile);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 根据字段名称，获取object中对应的值
	 * 
	 * @param fieldName
	 * @return
	 */
	private static String getFieldValue(Object obj, String fieldName) {
		String fieldValue = "";
		if (null == obj || StringUtils.isEmpty(fieldName)) {
			return "";
		}
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String getter = "get" + firstLetter + fieldName.substring(1);
		try {
			Method method = obj.getClass().getMethod(getter, new Class[] {});
			if (null != method) {
				Object value = method.invoke(obj, new Object[] {});
				if (null != value) {
					fieldValue = String.valueOf(value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fieldValue;
	}

	/**
	 * 将字符串转化为BigDecimal （返回文件中，金额单位为分，需转化为单位元）
	 * 
	 * @param moneyStr
	 * @return
	 */
	public static BigDecimal moneyTransition(String moneyStr) {
		String returnStr = "0";
		if (!StringUtils.isEmpty(moneyStr)) {
			String head = "";
			String foot = "";
			if (moneyStr.length() > 2) {
				head = moneyStr.substring(0, moneyStr.length() - 2);
				foot = moneyStr.substring(moneyStr.length() - 2);
			} else {
				head = "0";
				foot = String.format("%02d", moneyStr);
			}
			returnStr = head + "." + foot;
		}
		return BigDecimal.valueOf(Double.valueOf(returnStr));
	}

	/**
	 * 生成代扣文件，返回代扣总金额
	 * 
	 * @param batchNo
	 *            批次号
	 * @param repayList
	 *            代扣数据
	 * @param fileName
	 *            代扣文件名
	 * @return 当前批次代扣总金额
	 * @throws ServiceException
	 */
	public static Object[] expFileByRepayDk(String batchNo, List<?> repayList) throws ServiceException {
		if (repayList == null || repayList.isEmpty())
			throw new ServiceException("扣款内容为空");
		try {
			int count = 0;
			BigDecimal totalAmt = new BigDecimal(0);
			DecimalFormat format = new DecimalFormat("0.00");
			// 生成文件体
			String bodyStr = "";
			for (int k = 0; k < repayList.size(); k++) {
				// 讲Map转为List
				Object objMap = repayList.get(k);
				Map<String, String> beanMap = BeanRefUtil.getFieldValueMap(objMap);
				count++;
				// 明细表识，8n，当天不重复
				String bNo = Integer.parseInt(batchNo) + "";
				String dtlFlag = count + "";
				for (int i = 1; i <= (8 - (bNo + count).length()); i++)
					dtlFlag = "0" + dtlFlag;
				dtlFlag = bNo + dtlFlag;
				// 协议编号，32x，MP3+客户的银行账号
				String ptNumber = "MP3" + beanMap.get("acctNo");
				for (int i = 1; i <= (32 - ptNumber.length() + i - 1); i++)
					ptNumber = ptNumber + " ";
				// 交易金额，13位整数位+ “.” + 2位小数位，不足左补0
				String tranAmt = "";
				tranAmt = format.format(Double.valueOf(beanMap.get("curRcvAmt")));
				for (int i = 1; i <= (16 - tranAmt.length() + i - 1); i++)
					tranAmt = "0" + tranAmt;
				String bankType = "00";// 付款行本地行别
				// 付款行行号
				String bankNo = beanMap.get("bankNo");
				for (int i = 1; i <= (14 - bankNo.length() + i - 1); i++)
					bankNo = bankNo + " ";
				// 付款人开户行号
				String khBankNo = bankNo;
				// 付款人账号32位
				String acctNo = beanMap.get("acctNo");
				for (int i = 1; i <= (32 - acctNo.length() + i - 1); i++)
					acctNo = acctNo + " ";
				// 付款人名称，120g
				String accName = beanMap.get("acctName");
				for (int i = 1; i <= (120 - getWordCount(accName) + i - 1); i++)
					accName = accName + " ";
				// 附言,存放贷款编号，
				String remark = beanMap.get("loanNo");
				for (int i = 1; i <= (120 - remark.length() + i - 1); i++)
					remark = remark + " ";
				String bankRetCode = "--------";// 银行返回码
				// 附加信息，100g， 代扣表主键，
				String reverse = beanMap.get("id");
				for (int i = 1; i <= (100 - reverse.length() + i - 1); i++)
					reverse = reverse + " ";
				// 银行返回附言
				String retRemark = "";
				for (int i = 0; i < 120; i++)
					retRemark = retRemark + " ";
				// 替换户名标志
				String replaceNameFlag = " ";
				// 替换后户名
				String repalacedName = "";
				for (int i = 0; i < 120; i++)
					repalacedName = repalacedName + " ";
				totalAmt = totalAmt.add(new BigDecimal(tranAmt));
				// 头文件
				bodyStr = bodyStr + dtlFlag + ptNumber + tranAmt + bankType + bankNo + khBankNo + acctNo + accName
						+ remark + bankRetCode + reverse + retRemark + replaceNameFlag + repalacedName + "\r\n";
			}
			/*
			 * for(SingleRepayBo rdtl : repayList){ if(rdtl == null) continue;
			 * count++; //明细表识，8n，当天不重复 String bNo =
			 * Integer.parseInt(batchNo)+""; String dtlFlag = count+""; for(int
			 * i=1; i<=(8- (bNo+count).length()); i++) dtlFlag= "0"+dtlFlag;
			 * dtlFlag=bNo+dtlFlag; //协议编号，32x，MP3+客户的银行账号 String ptNumber =
			 * "MP3"+rdtl.getAcctNo(); for(int i = 1 ;
			 * i<=(32-ptNumber.length()+i-1); i++) ptNumber = ptNumber + " ";
			 * //交易金额，13位整数位+ “.” + 2位小数位，不足左补0 String tranAmt = ""; tranAmt =
			 * format.format(rdtl.getCurRcvAmt().doubleValue()); for(int i=1;
			 * i<=(16-tranAmt.length()+i-1); i++) tranAmt="0"+tranAmt; String
			 * bankType = "00";//付款行本地行别 //付款行行号 String bankNo =
			 * rdtl.getBankNo(); for(int i = 1; i<=(14-bankNo.length()+i-1);i++)
			 * bankNo = bankNo + " "; //付款人开户行号 String khBankNo = bankNo;
			 * //付款人账号32位 String acctNo = rdtl.getAcctNo(); for(int i=1;
			 * i<=(32-acctNo.length()+i-1); i++ ) acctNo= acctNo +" ";
			 * //付款人名称，120g String accName = rdtl.getAcctName(); for(int i=1;
			 * i<=(120-getWordCount(accName)+i-1); i++) accName = accName + " ";
			 * //附言,存放贷款编号， String remark = rdtl.getLoanNo(); for(int i=1;
			 * i<=(120-remark.length()+i-1); i++) remark=remark+" "; String
			 * bankRetCode = "--------";//银行返回码 //附加信息，100g， 代扣表主键， String
			 * reverse = rdtl.getId(); for(int i=1;
			 * i<=(100-reverse.length()+i-1); i++) reverse = reverse+" ";
			 * //银行返回附言 String retRemark=""; for(int i =0; i<120;i++) retRemark
			 * = retRemark +" "; //替换户名标志 String replaceNameFlag = " "; //替换后户名
			 * String repalacedName=""; for(int i=0;i<120;i++)
			 * repalacedName=repalacedName+" "; totalAmt = totalAmt.add(new
			 * BigDecimal( tranAmt)); //头文件 bodyStr =
			 * bodyStr+dtlFlag+ptNumber+tranAmt+bankType+bankNo+khBankNo+acctNo+
			 * accName+remark
			 * +bankRetCode+reverse+retRemark+replaceNameFlag+repalacedName+
			 * "\r\n"; }
			 */
			bodyStr = bodyStr.replaceAll("(?:\r\n|\n|\r)*$", "");// 去掉末尾回车换行
			// 生成头文件
			String busiType = "302";// 业务类型
			String entCode = "C00000000MP3  ";// 企业代码
			String dlgtDate = DateUtil.getDay("yyyyMMdd");// 委托日期
			String busiCode = "30202";// 业务编码
			String feeCode = "04903";// 费项代码
			String agentBankType = "09";// 代办行别
			String agentBankNo = "308584001016  ";// 代办收款行号
			String recipientBankNo = "308584001016  ";// 收款开户行行号
			// 收款账号32x
			String reciveAcctNo = "";
			for (int i = 0; i < 32; i++)
				reciveAcctNo += " ";
			// 收款名称120g
			String reciveAcctName = "";
			for (int i = 0; i < 120; i++)
				reciveAcctName += " ";
			String cny = "CNY"; // 币种
			// 总笔数8n
			String totlCount = count + "";
			for (int i = 1; i <= (8 - totlCount.length() + i - 1); i++)
				totlCount = "0" + totlCount;
			String totlAmt = totalAmt.toString();// 总金额，13位整数位+ “.” +
													// 2位小数位，不足左补0
			for (int i = 1; i <= (16 - totlAmt.length() + i - 1); i++)
				totlAmt = "0" + totlAmt;
			// 成功笔数8n
			String succCount = "";
			for (int i = 0; i < 8; i++)
				succCount += " ";
			// 成功金额，13位整数位+ “.” + 2位小数位，不足左补0，提出时补空格
			String succAmt = "";
			for (int i = 0; i < 16; i++)
				succAmt += " ";
			// 文件头
			String headStr = busiType + entCode + dlgtDate + batchNo + busiCode + feeCode + agentBankType + agentBankNo
					+ recipientBankNo + reciveAcctNo + reciveAcctName + cny + totlCount + totlAmt + succCount + succAmt
					+ "\r\n";
			// 代扣出盘文件完整内容
			String content = headStr + bodyStr;
			String downloadPath = ParamUtils.getParam("codeFilePath") + DateUtils.getCurDate() + File.separator;
			File f = new File(downloadPath);
			if (!f.exists()) {
				f.mkdirs();
			}
			String libTitle = "30202_C00000000MP3_" + batchNo + "_" + DateUtil.getDay("yyyyMMdd");
			String extName = ".txt";
			String createFile = downloadPath + libTitle + extName;
			FileUtil.writeTxtFile(createFile, content);
			Object[] obj = new Object[2];
			obj[0] = libTitle;
			obj[1] = new FileInputStream(createFile);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("生成出盘文件内容失败:" + e.getMessage());
		}
	}

	/**
	 * 生成快付通代扣文件，返回代扣总金额
	 * 
	 * @param batchNo
	 *            批次号
	 * @param repayList
	 *            代扣数据
	 * @param fileName
	 *            代扣文件名
	 * @return 当前批次代扣总金额
	 * @throws ServiceException
	 */
	public static Object[] expKFTFileByRepayDk(String batchNo, List<?> repayList) throws ServiceException {
		if (repayList == null || repayList.isEmpty())
			throw new ServiceException("扣款内容为空");
		try {
			int count = 0;
			BigDecimal totalAmt = new BigDecimal(0);
			DecimalFormat format = new DecimalFormat("0");
			// 头文件序号
			String totalStrSn = batchNo + "";
			for (int i = 1; i <= (8 - totalStrSn.length() + i - 1); i++) {
				totalStrSn = "0" + totalStrSn;
			}
			// 生成文件体
			String bodyStr = "";
			for (int k = 0; k < repayList.size(); k++) {
				// 讲Map转为List
				Object objMap = repayList.get(k);
				Map<String, String> beanMap = BeanRefUtil.getFieldValueMap(objMap);
				count++;
				String oneStr = count+"";
				for(int i = 1 ; i<=(6-oneStr.length()+i-1); i++) {
					 oneStr = "0"+oneStr;
				}
				String kg18 ="                  ";
				String jymc = "收水费";
				for (int i = 1; i <= (64 - jymc.length() + i - 1); i++)
					jymc = jymc + " ";
				// 协议编号 
				String ptNumber = com.hs.utils.StringUtils.isBlank(beanMap.get("noAgree"))?"":beanMap.get("noAgree");
				for (int i = 1; i <= (32 - ptNumber.length() + i - 1); i++)
					ptNumber = ptNumber + " ";
				// 交易金额，13位整数位+ “.” + 2位小数位，不足左补0
				String tranAmt = "";
				tranAmt = format.format(Double.valueOf(beanMap.get("curRcvAmt"))*100);
				for (int i = 1; i <= (16 - tranAmt.length() + i - 1); i++)
					tranAmt = "0" + tranAmt;
				// 付款行行号
				String bankNo = KftUtil
						.getKftBank(beanMap.get("openBank") == null ? beanMap.get("bankNo") : beanMap.get("openBank"));
				// 付款人账号32位
				String acctNo = beanMap.get("acctNo");
				for (int i = 1; i <= (31 - acctNo.length() + i - 1); i++)
					acctNo = acctNo + " ";
				// 付款人名称，120g
				String accName = beanMap.get("acctName");
				for (int i = 1; i <= (68 - getWordCount(accName) + i - 1); i++)
					accName = accName + " ";
				String cardStr = "0 ";//证件类型
				String cardNo=beanMap.get("certNo");//身份证号码
				for (int i = 1; i <= (31 - cardNo.length() + i - 1); i++)
					cardNo = cardNo + " ";
				// 手机号码
				String phoneStr = beanMap.get("phoneNo");
				for (int i = 0; i < 20; i++)
					phoneStr = phoneStr + " ";
				// 填充1
				String repalace1 = " ";
				// 填充2
				String repalace2 = "";
				for (int i = 0; i < 3; i++){
					repalace1 = repalace1 + " ";
					repalace2 = repalace2 + " ";
				}
				// 附言,存放贷款编号，代扣表主键，
				String remark = beanMap.get("loanNo")+"#"+beanMap.get("id");
				for (int i = 1; i <= (128 - remark.length() + i - 1); i++)
					remark = remark + " ";
				totalAmt = totalAmt.add(new BigDecimal(tranAmt));
				// 头文件
				bodyStr = bodyStr +totalStrSn+ oneStr +kg18+ ptNumber + tranAmt + jymc + bankNo + acctNo + accName
						+ cardStr +cardNo + phoneStr + repalace1 + repalace1+remark + "\r\n";
			}
			bodyStr = bodyStr.replaceAll("(?:\r\n|\n|\r)*$", "");// 去掉末尾回车换行
			// 生成头文件
			String busiType = "20";// 业务类型
			String entCode = "2017032200553161    ";// 企业代码
			String dlgtDate = "2AB00NMJ"+DateUtil.getDay("yyyyMMdd");// 委托日期
			String totlCount = count + "";
			for (int i = 1; i <= (8 - totlCount.length() + i - 1); i++)
				totlCount = "0" + totlCount;
			String totlAmt = totalAmt.toString();// 总金额，13位整数位+ “.” + 2位小数位，不足左补0
			for (int i = 1; i <= (16 - totlAmt.length() + i - 1); i++)
				totlAmt = "0" + totlAmt;
			String titleTwoStr = "";
			for (int i = 0; i < 32; i++)
				titleTwoStr += " ";
			String titleOneStr = "";
			for (int i = 0; i < 24; i++)
				titleOneStr += " ";
			// 文件头
			String headStr = busiType + entCode +totalStrSn+titleOneStr+ dlgtDate + totlCount + totlAmt +titleTwoStr+ "\r\n";
			// 代扣出盘文件完整内容
			String content = headStr + bodyStr;
			String downloadPath = ParamUtils.getParam("codeFilePath") + DateUtils.getCurDate() + File.separator;
			// String downloadPath = "D:/data" + File.separator;
			File f = new File(downloadPath);
			if (!f.exists()) {
				f.mkdirs();
			}
			String libTitle = totalStrSn+ ".txt";
			String createFile = downloadPath + libTitle;
			FileUtil.writeTxtFile(createFile, content);
			Object[] obj = new Object[2];
			obj[0] = libTitle;
			obj[1] = new FileInputStream(createFile);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("生成出盘文件内容失败:" + e.getMessage());
		}
	}

	/**
	 * 生成瑞银代扣文件，返回代扣总金额
	 * 
	 * @param batchNo
	 *            批次号
	 * @param repayList
	 *            代扣数据
	 * @param fileName
	 *            代扣文件名
	 * @return 当前批次代扣总金额
	 * @throws ServiceException
	 */
	public static Object[] expRyFileByRepayDk(String batchNo, List<?> repayList) throws ServiceException {
		if (repayList == null || repayList.isEmpty())
			throw new ServiceException("扣款内容为空");
		try {
			int count = 0;
			BigDecimal totalAmt = new BigDecimal(0);
			DecimalFormat format = new DecimalFormat("0.00");
			List<Map<String, Object>> listExcel = new ArrayList<>();
			Map<String, Object> excelMap = null;
			for (int k = 0; k < repayList.size(); k++) {
				excelMap = new HashMap<>();
				// 讲Map转为List
				Object objMap = repayList.get(k);
				Map<String, String> beanMap = BeanRefUtil.getFieldValueMap(objMap);
				count++;
				// 明细表识，8n，当天不重复
				// String bNo = Integer.parseInt(batchNo) + "";
				// String dtlFlag = getDtlFlag(count, bNo);
				String tranAmt = format.format(Double.valueOf(beanMap.get("curRcvAmt")));
				String bankNo = beanMap.get("openBank") == null ? beanMap.get("bankNo") : beanMap.get("openBank");
				// 贷款编号，
				String remark = beanMap.get("loanNo");
				// 代扣表主键，
				String reverse = beanMap.get("id");
				if (bankNo != null && bankNo.equals("100")) {
					bankNo = "403";
				}
				totalAmt = totalAmt.add(new BigDecimal(tranAmt));
				excelMap.put("A", remark);
				excelMap.put("B", "");
				excelMap.put("C", bankNo);
				excelMap.put("D", "");
				excelMap.put("E", beanMap.get("acctNo"));
				excelMap.put("F", beanMap.get("acctName"));
				excelMap.put("G", "");
				excelMap.put("H", "");
				excelMap.put("I", "");
				excelMap.put("J", "");
				excelMap.put("K", new BigDecimal(beanMap.get("curRcvAmt")).intValue() + "00");// 金额
				excelMap.put("L", "");
				excelMap.put("M", reverse);
				listExcel.add(excelMap);
			}
			// 生成头文件
			String a1 = "S";
			String b1 = "000191400204677";
			String c1 = DateUtil.getDay("yyyyMMdd");// 委托日期
			String f1 = "14901";
			// 总笔数8n
			String totlCount = count + "";
			// String totlAmt = totalAmt.toString();
			String downloadPath = ParamUtils.getParam("codeFilePath") + DateUtils.getCurDate() + File.separator;
			// String downloadPath = "D:/data" + File.separator;
			File f = new File(downloadPath);
			if (!f.exists()) {
				f.mkdirs();
			}
			String txtNameEnd = batchNo + "";
			for (int i = 1; i <= (5 - txtNameEnd.length() + i - 1); i++) {
				txtNameEnd = "0" + txtNameEnd;
			}
			String libTitle = "000191400204677_S03" + DateUtil.getDay("yyyyMMdd") + "_" + txtNameEnd + ".xls";
			String createFile = downloadPath + libTitle;
			List<Map<String, Object>> list = new ArrayList<>();
			excelMap = new HashMap<>();
			excelMap.put("A", a1);
			excelMap.put("B", b1);
			excelMap.put("C", c1);
			excelMap.put("D", totlCount);
			excelMap.put("E", totalAmt.intValue() + "00");
			excelMap.put("F", f1);
			excelMap.put("G", "");
			excelMap.put("H", "");
			excelMap.put("I", "");
			excelMap.put("J", "");
			excelMap.put("K", "");// 金额
			excelMap.put("L", "");
			excelMap.put("M", "");
			list.add(excelMap);
			for (Map<String, Object> map : listExcel) {
				list.add(map);
			}
			String columnNames = "A,B,C,D,E,F,G,H,I,J,K,L,M";
			ExportExcelUtil.writeListToExcel(list, downloadPath, libTitle, "", columnNames, "", true);
			Object[] obj = new Object[2];
			obj[0] = libTitle;
			obj[1] = new FileInputStream(createFile);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("生成出盘文件内容失败:" + e.getMessage());
		}
	}

	private static String getDtlFlag(int count, String bNo) {
		String dtlFlag = "";
		String countStr = count + "";
		String sysDate = System.currentTimeMillis() + "";
		dtlFlag = sysDate.substring(sysDate.length() - (8 - (countStr.length())), sysDate.length()) + count;
		return dtlFlag;
	}

	/**
	 * 获取中文字符串的字节长度
	 * 
	 * @param str
	 * @return
	 */
	public static int getWordCount(String str) {
		int length = 0;
		for (int i = 0; i < str.length(); i++) {
			int ascii = Character.codePointAt(str, i);
			if (ascii >= 0 && ascii <= 255)
				length++;
			else
				length += 2;
		}
		return length;
	}

	public static void main(String[] args) {
		String cardNo= "15" ;//身份证号码
		cardNo = Integer.parseInt(cardNo)+1+"";
		System.out.println(cardNo);
	}

}
