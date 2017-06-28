package com.hs.loan.acctplus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** 
 * <li>ClassName:Config <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2017年1月5日 <br/> 
 * <li>@author   zzy       
 */
@Component
public class Config {
	
	public static String big_data_url;

	public static String getBig_data_url() {
		return big_data_url;
	}
	
	@Value("${big_data_url}")
	public void setBig_data_url(String big_data_url) {
		Config.big_data_url = big_data_url;
	}
	
}
