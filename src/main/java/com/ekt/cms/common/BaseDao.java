package com.ekt.cms.common;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class BaseDao extends SqlSessionDaoSupport {
	
	/**
	 * 批量插入操作
	 * @param method 插入操作的方法名
	 * @param entity 查询参数或实体类
	 * @return 返回影响的行数
	 */
	public int batchInsert(String method,Object entity){  
        return this.getSqlSession().insert(method, entity);  
    }
}
