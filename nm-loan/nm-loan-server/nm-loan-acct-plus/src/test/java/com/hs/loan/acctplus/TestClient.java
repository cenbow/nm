package com.hs.loan.acctplus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hs.loan.acct.api.AcctPlusService;
import com.hs.loan.acct.dto.AppLoanAcctDto;


public class TestClient {

	public static void main(String[] args) throws SQLException {
		
		
		System.setProperty("dubbo.application.name","nm-acct-plus-test");
		System.setProperty("dubbo.consumer.timeout","60000");
		System.setProperty("dubbo.consumer.retries","0");
		System.setProperty("dubbo.registry.address","zookeeper://192.168.3.208:2181");
		System.setProperty("dubbo.protocol.port","30881");
		
		ApplicationContext factory=new ClassPathXmlApplicationContext("classpath*:app-client.xml"); 
		
		System.out.println(">>>>>>>client");
		try {
			
			AcctPlusService acctPlusService = factory.getBean(AcctPlusService.class);
			AppLoanAcctDto dto = new AppLoanAcctDto();
			
//			dto.setLoanNo("880201000011071412126370967");
//			dto.setStat("30201002");
//			dto.setFileNo("5");
//			
//			dto.setCustNo("custno");
//			dto.setCustName("custname");
//			dto.setStaffNo("staffNo");
//			dto.setStaffName("staffName");
//			dto.setOrgNo("11000001");
//			dto.setBranchNo("cq-jb-0003-01");
////			dto.setBranchNo("sc-cd-0057-01");//黑名单
//			dto.setApplyDate(new Date());
			acctPlusService.sendAcctHandle(dto);
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
}
