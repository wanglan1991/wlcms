package com.ekt.cms.account.dao;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.ekt.cms.account.entity.CmsAccount;
/**
 * Cms用户Dao
 * @author wanglan
 *
 */
public interface CmsAccountMapper {
	/**
	 * 查询用户
	 * @return 用户 
	 */
	CmsAccount findAccount(@Param("cmsAccount")CmsAccount cmsAccount);
	/**
	 * 删除用户
	 * @return 是否操作成功
	 */
	Integer deleteById(@Param("id")Integer id);
	/**
	 * 修改用户
	 * @return 是否操作成功
	 */
	Integer updateById(@Param("cmsAccount")CmsAccount cmsAccount);
	/**
	 * 查询用户
	 * @return 用户List
	 */
	List<Map<String,CmsAccount>> getAccountListByAccount(@Param("cmsAccount")CmsAccount cmsAccount);
	/**
	 * 添加用户
	 */
	Integer insertAccount(@Param("cmsAccount")CmsAccount cmsAccount);


}
