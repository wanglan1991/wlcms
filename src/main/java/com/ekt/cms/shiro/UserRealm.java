package com.ekt.cms.shiro;

import java.util.List;

import javax.annotation.Resource;

//
//import com.larva.model.Account;
//import com.larva.service.IAccountService;
//
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import com.ekt.cms.user.entity.CmsUser;
import com.ekt.cms.user.service.ICmsUserService;

/**
*
*/

public class UserRealm extends AuthorizingRealm {

	@Resource
	private ICmsUserService cmsUserService;

	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("before");
		int userId = Integer.parseInt(principals.getPrimaryPrincipal().toString());// 为什么这里获取到的是用户ID
		System.out.println(userId);
		SimpleAuthorizationInfo authorizationInfo = cmsUserService.getAccountRolePermission(userId);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		// 根据用户名查找用户是否存在
		CmsUser cmsUser = new CmsUser();
		cmsUser.setUserName(username);
		cmsUser.setStatus(1);
		List<CmsUser> account = cmsUserService.queryByCondition(cmsUser);
		if (account == null) {
			throw new UnknownAccountException();
		}
		String accountPassword = account.get(0).getPassword();
		// 判断输入密码是否和用户密码一致
		if (!password.equals(accountPassword)) {
			throw new IncorrectCredentialsException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(account.get(0).getId(),
				password, getName());
		return authenticationInfo;
	}

}