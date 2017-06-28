package com.hs.system.shiro;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hs.base.entity.UserProfile;
import com.hs.base.exception.AppException;
import com.hs.base.exception.ServiceException;
import com.hs.system.entity.SysStaff;

/**
 * 重新shiro CasRealm
 * @author jqiu
 *
 */
public class MyCasRealm extends CasRealm{
	private static final Logger logger = LoggerFactory.getLogger(MyCasRealm.class);
	
	@Autowired
	IShiroService userService;
	
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	UserProfile profile = (UserProfile)principals.getPrimaryPrincipal();
        logger.debug(profile.getLoginNo());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.findRoles(profile.getStaffNo()));
        authorizationInfo.setStringPermissions(userService.findPermissions(profile.getStaffNo()));
        return authorizationInfo;
    }
    
    @Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
    	CasToken casToken = (CasToken) token;
        if (token == null) {
            return null;
        }
        
        String ticket = (String)casToken.getCredentials();
        if (!StringUtils.hasText(ticket)) {
            return null;
        }
        
        TicketValidator ticketValidator = ensureTicketValidator();

        try {
            // contact CAS server to validate service ticket
            Assertion casAssertion = ticketValidator.validate(ticket, getCasService());
            // get principal, user id and attributes
            AttributePrincipal casPrincipal = casAssertion.getPrincipal();
            String loginNo = casPrincipal.getName();
            logger.debug("Validate ticket : {} in CAS server : {} to retrieve user : {}", new Object[]{
                    ticket, getCasServerUrlPrefix(), loginNo
            });

            Map<String, Object> attributes = casPrincipal.getAttributes();
            // refresh authentication token (user id + remember me)
            casToken.setUserId(loginNo);
            String rememberMeAttributeName = getRememberMeAttributeName();
            String rememberMeStringValue = (String)attributes.get(rememberMeAttributeName);
            boolean isRemembered = rememberMeStringValue != null && Boolean.parseBoolean(rememberMeStringValue);
            if (isRemembered) {
                casToken.setRememberMe(true);
            }

            String loginNoByStaffNo = userService.getLoginNoByStaffNo(loginNo);
            if(! com.hs.utils.StringUtils.isEmpty(loginNoByStaffNo)){
            	loginNo = loginNoByStaffNo;
            }
            UserProfile userHolder = null;
            try{
            	userHolder = userService.getUserProfile(loginNo);
            }catch(ServiceException e){
            	logger.error("登陆出错了",e);
            	throw e;
            }catch(AppException e){
            	throw e;
            }catch(Exception e){
            	logger.error("登陆出错了",e);
            	throw new ServiceException("登陆出错了！");
            }
            if(userHolder==null){
            	throw new AppException("该用户不存在！");
            }
            userHolder.getOtherParams().putAll(attributes);
            
            // create simple authentication info
//            List<Object> principals = CollectionUtils.asList(userId, attributes);
            
            PrincipalCollection principalCollection = new SimplePrincipalCollection(userHolder, getName());
            return new SimpleAuthenticationInfo(principalCollection, ticket);
        } catch (TicketValidationException e) { 
            throw new CasAuthenticationException("Unable to validate ticket [" + ticket + "]", e);
        }
	}
    
    
    /**
     * 授权用户信息.
     */
    public static class Principal implements Serializable {
        /**
         * 序列号.
         */
        private static final long serialVersionUID = 1L;
        /**
         * 用户id.
         */
        private String loginNo;
        /**
         * 用户名称.
         */
        private String staffNo;
        /**
         * 
         */
        private String staffName;
        /**
         * ip地址.
         */
        private String ipAddr;
        /**
         * 角色名.
         */
        private String roleNames;
        /**
         * 机构名.
         */
        private String orgName;
        /**
         * 部门名.
         */
        private String depName;
        /**
         * 用户session.
         */
        private UserProfile user; 
        /**
         * 功能权限
         */
        private Set<String> permissionCodeSet; 
        
        /**
         * 用户holder.
         * @param userHolder.
         * @return  Principal 用户holder.
         */
        public Principal(UserProfile userHolder) {
            this.loginNo = userHolder.getLoginNo();
            this.staffNo = userHolder.getStaffNo();
            this.staffName = userHolder.getStaffName();
            this.orgName = userHolder.getOrgName();
            this.depName = userHolder.getDepName();
            this.ipAddr = getIpAddr();
            this.roleNames = userHolder.getRoleNames();
            this.permissionCodeSet = userHolder.getPermissionCodeSet();
            userHolder.setLoginIp(ipAddr);
            user = userHolder;
        }

        
        
        
        /**
         * 用户user.
         * @return
         */
        public UserProfile getUser() {
            return user;
        }
        /**
         * 得到本机的ip地址
         * @return
         */
        public String getIpAddr(){
            String ip="";
            try{
                // 获取IP地址
            	ip = InetAddress.getLocalHost().getHostAddress();
            }catch (UnknownHostException e){
                e.printStackTrace();
            }
            return ip;
        }
            
    
        /**
         * 获取用户权限编码
         * @return
         */
        public Set<String> getPermissionCodeSet() {
            return permissionCodeSet;
        }



        /**
         * 用户登陆号
         * @return
         */
		public String getLoginNo() {
			return loginNo;
		}



		/**
		 * 用户编号
		 * @return
		 */
		public String getStaffNo() {
			return staffNo;
		}



		/**
		 * 姓名
		 * @return
		 */
		public String getStaffName() {
			return staffName;
		}

		

		/**
		 * 角色名(多个以逗号分隔)
		 * @return
		 */
		public String getRoleNames() {
			return roleNames;
		}



		/**
		 * 部门名
		 * @return
		 */
		public String getDepName() {
			return depName;
		}
		


		/**
		 * 机构名
		 * @return
		 */
		public String getOrgName() {
			return orgName;
		}
        
    }
}
