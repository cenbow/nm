package com.hs.loan.certification.server.jiufu.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.macrosky.webservice.biz.TransHead;
import com.hs.base.exception.ServiceException;
import com.hs.utils.ParamUtils;

import microloan.LoanService;

/**
 * 玖富webservice公用组件
 * @author zwr
 *
 */
@org.springframework.stereotype.Service
public class CommonInterFaceService {

	/**
	 * 获取默认的玖富WebService服务
	 * 
	 * @return
	 * @throws ServiceException 
	 */
	public LoanService getLoanService() throws ServiceException{
		//通过配置获取玖富webservice服务地址
		String addr = ParamUtils.getParam("jf.addr");

		URL url = null;
		try {
			url = new URL(addr);
		} catch (MalformedURLException e) {
			throw new ServiceException("玖富WebService服务地址报错："+e.getMessage());
		}

		QName qName = new QName("microloan","loanService");
		Service service = Service.create(url, qName);
		LoanService loanServiceImplPort = service.getPort(LoanService.class);
		return loanServiceImplPort;
	}
	
	/**
	 * 获取properties属性id玖富WebService服务
	 * 
	 * @param keyId properties中的属性id
	 * 
	 * @return
	 * @throws ServiceException 
	 */
	public com.hs.loan.certification.server.jiufu.common.LoanService getLoanService(String keyId) throws ServiceException{
		//通过配置获取玖富webservice服务地址
		String addr = ParamUtils.getParam(keyId);
		
		URL url = null;
		try {
			url = new URL(addr);
		} catch (MalformedURLException e) {
			throw new ServiceException("玖富WebService服务地址报错："+e.getMessage());
		}
		

		QName qName = new QName("microloan","loanService");
		Service service = Service.create(url, qName);
		com.hs.loan.certification.server.jiufu.common.LoanService loanServiceImplPort = service.getPort(com.hs.loan.certification.server.jiufu.common.LoanService.class);
		return loanServiceImplPort;
	}
	
	/**
	 * 生成交易头对象信息
	 * @param sourceClient 交易编码
	 * @param transType 交易类型
	 * @return
	 */
	public TransHead genTransHead(String sourceClient, String transType){
		UUID uuid = UUID.randomUUID();
		TransHead transHead = new TransHead();
		transHead.setSourceClient(sourceClient);// 交易编码
		transHead.setTransType(transType);// 交易类型
		transHead.setTransSerialNo(uuid.toString());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(new Date());
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:MM:ss");
		String startTime = sdf1.format(new Date());
		
		transHead.setTransExeDate(startDate);// 交易日期
		transHead.setTransExeTime(startTime);// 交易时间
		
		return transHead;
	}
	
	/**
	 * 保存玖富接口日志信息
	 * @param logInfo
	 * @throws ServiceException
	 */
	/*public void saveJfLogInfo(TJfLogInfoVO logInfo) throws ServiceException{
		String tableKey = UUIDGenerator.generate(DateTimeUtil.getDate());
		logInfo.setTableKey(tableKey);
		IBaseService baseService = ContextUtil.getBean(IBaseService.class);
		baseService.save(logInfo);
	}*/
}
