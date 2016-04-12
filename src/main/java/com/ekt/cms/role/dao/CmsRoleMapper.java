package com.ekt.cms.role.dao;

import com.ekt.cms.role.entity.CmsRole;

public interface CmsRoleMapper {
	CmsRole selectByPrimaryKey(Integer id);
	
    int deleteByPrimaryKey(Integer id);

    int insert(CmsRole record);

    int insertSelective(CmsRole record);

    int updateByPrimaryKey(CmsRole record);
}