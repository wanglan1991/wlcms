package com.ekt.cms.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ekt.cms.common.entity.CmsDict;

public interface CmsDictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsDict record);

    int insertSelective(CmsDict record);

    CmsDict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsDict record);

    int updateByPrimaryKey(CmsDict record);
    
    public List<CmsDict> queryByCondition(@Param("dict")CmsDict dict);
    
    public List<CmsDict> listDictPage(@Param("dict")CmsDict dict);
    
}