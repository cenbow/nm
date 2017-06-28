//package com.hs.loan.produce;
//
//import java.util.Scanner;
//
//import com.hs.loan.produce.server.AppLoanApprServer;
//import com.hs.loan.produce.server.AppMqStatServer;
//import com.hs.loan.produce.util.BeanFactory;
//
///** 
// * <li>ClassName:ProcessCmd <br/> 
// * <li>@Description: TODO(类描述)
// * <li>@Date:     2016年12月5日 <br/> 
// * <li>@author   zzy       
// */
//public class ProcessCmd extends Thread{
//	@SuppressWarnings("resource")
//	@Override
//	public void run() {
//		
//		String cmd = null;
//		Scanner sc = null;
//		while (true) {
//			System.out.print("cmd>");
//			try {
//				sc = new Scanner(System.in);
//				cmd = sc.nextLine();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			try {
//				switch(cmd){
//					case "getappr":{
//						AppLoanApprServer server = BeanFactory.getBean(AppLoanApprServer.class);
//						server.getAndSendAppLoanAppr();
//						break;
//					}
//					case "getacct":{
//						AppLoanApprServer server = BeanFactory.getBean(AppLoanApprServer.class);
//						server.getAndSendAppLoanAcct();
//						break;
//					}
//					case "clean":{
//						AppMqStatServer server = BeanFactory.getBean(AppMqStatServer.class);
//						server.cleanHistoryData();
//						break;
//					}
//					case "getacccap":{
//						AppLoanApprServer server = BeanFactory.getBean(AppLoanApprServer.class);
//						server.getAndSendAccCapWith();
//						break;
//					}
//					default:{
//						System.out.println("not find cmd="+cmd);
//					}
//				}
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("cmd error ....");
//			}
//		}
//	}
//}
