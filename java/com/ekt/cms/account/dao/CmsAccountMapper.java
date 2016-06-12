package com.ekt.cms.account.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ekt.cms.account.entity.CmsAccount;
/**
 * notice： 这是一个接口文件，mybatis3 只需要定义dao接口，不需要实现类， 会自动匹配，名字和mapper文件夹下面的 xml文件名字要一致
 **/
public interface CmsAccountMapper {
	
	// 按主键删除    	
    int deleteByPrimaryKey(Integer id);
    
    // 插入 返回新插入数据的ID    
    int insert(CmsAccount cmsAccount);
    
    // 按主键查询   
    CmsAccount selectByPrimaryKey(Integer id);
    
    // 更新   
    int updateByPrimaryKey(@Param("cmsAccount")CmsAccount cmsAccount);
    /**
     * 按条件查询
     */
    List<CmsAccount> listPage(@Param("cmsAccount")CmsAccount cmsAccount);
	
    
    List<CmsAccount> queryByCondition(@Param("cmsAccount") CmsAccount cmsAccount);
    /**
     * 根据用户名查询
     * @param userName
     * @return 用户entity
     */
    CmsAccount queryByUserName(@Param("userName")String userName);
    
    /**
     * 根据用户id停用或启用用户
     * @param cmsAccount
     * @return
     */
    int confine(@Param("cmsAccount")CmsAccount cmsAccount);
    
    /**
     * 重置用户密码
     * @param cmsAccount
     * @return
     */
    int setPwd(@Param("cmsAccount")CmsAccount cmsAccount);
    /**
     * 修改用户信息
     * @param cmsAccount
     * @return
     */
    int updateAccount(@Param("cmsAccount")CmsAccount cmsAccount);
    /**
     * 获取某角色的所有用户
     * @param String
     * @return CmsAccount
     */
    List<CmsAccount> listAccountByRole(@Param("role")String  role);
}
 
