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

import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.account.service.ICmsAccountService;

/**
*
*/

public class UserRealm extends AuthorizingRealm {

	@Resource
	private ICmsAccountService cmsAccountService;

	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		int userId = Integer.parseInt(principals.getPrimaryPrincipal().toString());// 为什么这里获取到的是用户ID
		System.out.println(userId);
		SimpleAuthorizationInfo authorizationInfo = cmsAccountService.getAccountRolePermission(userId);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		// 根据用户名查找用户是否存在
		CmsAccount cmsAccount = new CmsAccount();
		cmsAccount.setUserName(username);
		cmsAccount.setStatus(1);
		List<CmsAccount> account = cmsAccountService.queryByCondition(cmsAccount);
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