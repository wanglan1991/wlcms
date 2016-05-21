package com.ekt.cms.account.service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.stereotype.Service;
import com.ekt.cms.account.dao.CmsAccountMapper;
import com.ekt.cms.account.entity.CmsAccount;
import com.ekt.cms.permission.entity.CmsPermission;
import com.ekt.cms.permission.service.IPermissionService;
import com.ekt.cms.utils.Md5Utils;
import com.ekt.cms.utils.pageHelper.PageBean;

@Service("cmsAccountService")
public class CmsAccountService implements ICmsAccountService {
	@Resource
	private CmsAccountMapper cmsAccountMapper;
	@Resource
	private IPermissionService permissionService;
	
	@Override
	public PageBean<CmsAccount> listPage(CmsAccount cmsAccount) {
		return new PageBean<CmsAccount>(cmsAccountMapper.listPage(cmsAccount));
	}

	@Override
	// 根据用户ID查询所有权限
	public SimpleAuthorizationInfo getAccountRolePermission(int id) {
		SimpleAuthorizationInfo AuthorizationInfo = new SimpleAuthorizationInfo();
		// 根据userID查询到roleID
		CmsAccount curUser = cmsAccountMapper.selectByPrimaryKey(id);
		int roleId = curUser.getRole();
		// 根据roleID查询所有的权限和角色
		List<CmsPermission> cmsPermissions =permissionService.queryPermissionByRoleId(roleId);
		curUser.setCmsPermissions(cmsPermissions);
		Set<String> permission = new HashSet<>();
		Set<String> roleEncoding = new HashSet<String>();
		if (cmsPermissions != null && cmsPermissions.size() > 0) {
			for (CmsPermission cmsPermission : cmsPermissions) {
				permission.add(cmsPermission.getKey());
				roleEncoding.add(cmsPermission.getEncoding());
			}
		}
		// 将权限信息和角色注入到SimpleAuthorizationInfo中
		AuthorizationInfo.addStringPermissions(permission);
		AuthorizationInfo.addRoles(roleEncoding);
		return AuthorizationInfo;
	}
	
	@Override
	public List<CmsAccount> queryByCondition(CmsAccount cmsAccount) {
		return cmsAccountMapper.queryByCondition(cmsAccount);
	}
	
	@Override
	public int update(CmsAccount cmsAccount) {
		return cmsAccountMapper.updateByPrimaryKey(cmsAccount);
	}

	@Override
	public int delete(int id) {
		return cmsAccountMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int addAccount(CmsAccount cmsAccount) {
		return cmsAccountMapper.insert(cmsAccount);
	}

	@Override
	public CmsAccount queryByUserName(String userName) {
		return cmsAccountMapper.queryByUserName(userName);
	}

	@Override
	public int confine(CmsAccount cmsAccount) {
		return cmsAccountMapper.confine(cmsAccount);
	}

	@Override
	public int setPwd(CmsAccount cmsAccount) {
		return cmsAccountMapper.setPwd(cmsAccount);
	}
	@Override
	public int updateAccount(CmsAccount cmsAccount){
		return cmsAccountMapper.updateAccount(cmsAccount);
	}
	




	// 根据主键查询
	@Override
	public CmsAccount selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return cmsAccountMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CmsAccount> listAccountByRole(String role) {
		// TODO Auto-generated method stub
		return cmsAccountMapper.listAccountByRole(role);
	}

}
