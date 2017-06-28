package com.hs.loan.approv;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.base.test.BaseTest;
import com.hs.loan.approv.dto.ApprGroupDto;
import com.hs.loan.approve.server.ApprGroupServer;

public class AppApprGroupServerTest extends BaseTest{

	@Autowired	private ApprGroupServer apprServer;
	
	@Test
	public void test() {
		UserProfile u = new UserProfile();
		ApprGroupDto a = new ApprGroupDto();
		a.setGroupName("测试2");
		//a.setGroupNo("12321321");
		a.setProdTyp("1");
		try {
			apprServer.saveApprGroup(a,u);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
