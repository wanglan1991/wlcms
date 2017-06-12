package com.ekt.cms.shiro;
import javax.annotation.Resource;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.account.service.ICmsAccountService;
import com.ekt.cms.utils.EncryptUtil;
import net.sf.ehcache.CacheManager;

/**
*
*/

public class UserRealm extends AuthorizingRealm {

	@Resource
	private ICmsAccountService cmsAccountService;


	@Autowired
	private CacheManager cacheManager;
	
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		int userId = Integer.parseInt(principals.getPrimaryPrincipal().toString());
		SimpleAuthorizationInfo authorizationInfo = cmsAccountService.getAccountRolePermission(userId);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		CmsAccount account=cmsAccountService.queryByUserName(username);
		if (account == null||account.getStatus()==0) {
			throw new UnknownAccountException();
		}
		// 判断输入密码是否和用户密码一致 并且状态必须为1
		if (!EncryptUtil.checkPassword(account.getPassword(), password)) {
			throw new IncorrectCredentialsException();
		}
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(account.getId(),
				password, getName());
		return authenticationInfo;
	}
	
	
	
	 /** 重写退出时缓存处理方法 */  
    protected void doClearCache(PrincipalCollection principalcollection) {  
    	  Object principal = principalcollection.getPrimaryPrincipal();  
          cacheManager.removeCache(principal.toString());   
    }  
}