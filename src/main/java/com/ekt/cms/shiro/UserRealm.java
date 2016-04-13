package com.ekt.cms.shiro;
//
//import com.larva.model.Account;
//import com.larva.service.IAccountService;
//
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
//
///** */
public class UserRealm extends AuthorizingRealm {
	
	//权限验证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//登录验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}
//    private IAccountService accountService;
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        int userId = Integer.parseInt(principals.getPrimaryPrincipal().toString());
//        SimpleAuthorizationInfo authorizationInfo = accountService.getAccountRolePermission(userId);
//        return authorizationInfo;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String username = (String) token.getPrincipal();
//        String password = new String((char[]) token.getCredentials());
//        Account account = accountService.getAccountByAccount(username);
//        if (account == null) {
//            throw new UnknownAccountException();
//        }
//        String accountPassword = account.getPassword();
//        if (!password.equals(accountPassword)) {
//            throw new IncorrectCredentialsException();
//        }
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(account.getId(), password, getName());
//        return authenticationInfo;
//    }
//
//    public IAccountService getAccountService() {
//        return accountService;
//    }
//
//    public void setAccountService(IAccountService accountService) {
//        this.accountService = accountService;
//    }
}