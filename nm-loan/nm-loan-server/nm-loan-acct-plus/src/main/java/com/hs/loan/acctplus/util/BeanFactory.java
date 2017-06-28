package com.hs.loan.acctplus.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/** 
 * <li>ClassName:BeanFactory <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年9月20日 <br/> 
 * <li>@author   zzy       
 */
@Component
public class BeanFactory implements ApplicationContextAware{

	private static ApplicationContext context;
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}
	
	public static <T> T getBean(Class<T> requiredType){
		if(context!=null){
			return context.getBean(requiredType);
		}
		return null;
	}
}
