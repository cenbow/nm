package org.nm.loan.produceMq.mq;

/** 
 * <li>ClassName:MQState <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年11月2日 <br/> 
 * <li>@author   zzy       
 */
public class Action {
	public final static int ACCEPT = 1;  // 处理成功
	public final static int RETRY = 2;  // 可以重试的错误
	public final static int REJECT = 3;  // 无需重试的错误
	public static void main(String[] args) {
		System.out.println(10001%10000==0);
	}
}
