package com.hs.system.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.hs.base.entity.UserProfile;
import com.hs.commons.auth.service.ISessionUserService;


/**
 * 用户信息获取实例类.
 * @author qiuji
 */
@Service(ISessionUserService.BEAN_Id)
public class SessionUserService implements ISessionUserService {
    
    /**
     * 得到用户的信息.
     * @return  SessionUserHolder 用户信息.
     */
    @Override
    public UserProfile getUser() {
    	Subject subject = SecurityUtils.getSubject();
    	if(!subject.isAuthenticated()){
    		return null;
    	}
    	return (UserProfile) subject.getPrincipal();
    }

}
