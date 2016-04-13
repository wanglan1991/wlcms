package com.ekt.cms.shiro.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import com.ekt.cms.permission.entity.CmsPermission;
import com.ekt.cms.permission.dao.CmsPermissionMapper;

/**
 * 借助spring {@link FactoryBean} 对apache shiro的premission进行动态创建 动态的从数据库中读取权限信息
 * 
 */
@Component
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {

	// shiro默认的链接定义 写在xml上的。
	private String filterChainDefinitions;
	@Resource
	private CmsPermissionMapper CmsPermissionMapper;

	@Override
	public Section getObject() throws Exception {
		Ini ini = new Ini();
		// 加载默认的url
		ini.load(filterChainDefinitions);
		System.out.println(filterChainDefinitions);
		
		/**	加载数据库cms_permission 的 value(路径) 和 key(权限)组成类似/login.do = authc的格式 ，
		若要这样使用， key 需要--->  perms[permission]
		 */
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		//查询数据库中所有的  路径对应需要的权限.
		List<CmsPermission> lst = CmsPermissionMapper.selectAll();
		for(CmsPermission per : lst){
		//访问某一路径，需要对应的权限
		if(StringUtils.isNotEmpty(per.getValue())&&StringUtils.isNotEmpty(per.getKey()))
		section.put(per.getValue(), "p["+per.getKey()+"]");
		}
		section.put("/**", "o");
		for(String s : section.keySet()){
		System.out.println(s + "----"+ section.get(s)+"-----------section");
		}
		return section;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Section.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getFilterChainDefinitions() {
		return filterChainDefinitions;
	}

	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

}
