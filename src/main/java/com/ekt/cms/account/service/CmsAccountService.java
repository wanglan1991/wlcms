package com.ekt.cms.account.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.ekt.cms.account.dao.CmsAccountMapper;
import com.ekt.cms.account.entity.CmsAccount;
/**
 * 
 * @author wanglan
 *
 */
@Service("iCmsAccountsService")
public class CmsAccountService implements ICmsAccountsService {

	@Resource
	CmsAccountMapper cmsAccountMapper;
	
	@Override
	public CmsAccount findAccount(CmsAccount cmsAccount) {
		
		return cmsAccountMapper.findAccount(cmsAccount);
	}

	@Override
	public Integer deleteById(Integer id) {

		return cmsAccountMapper.deleteById(id);
	}

	@Override
	public Integer updateById(CmsAccount cmsAccount) {

		return cmsAccountMapper.updateById(cmsAccount);
	}

	@Override
	public List<Map<String, CmsAccount>> getAccountListByAccount(CmsAccount cmsAccount) {

		return cmsAccountMapper.getAccountListByAccount(cmsAccount);
	}

	@Override
	public List<Map<String, CmsAccount>> getAccountListByAccount() {
		CmsAccount cmsAccount =new CmsAccount();
		return cmsAccountMapper.getAccountListByAccount(cmsAccount);
	}

	@Override
	public Integer addAccount(CmsAccount cmsAccount) {
	
		return cmsAccountMapper.insertAccount(cmsAccount);
	}
	


	
	
}
