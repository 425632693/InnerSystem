package com.cuiboshi.dao;

import com.cuiboshi.common.IBaseDao;
import com.cuiboshi.entity.AuthorResources;
import com.cuiboshi.entity.AuthorRole;
/**
 * 角色树实体的接口类
 * @author Administrator
 *
 */
public interface IAuthorRole extends IBaseDao<AuthorRole> {

	public void saveAuthorRole(String roleId,String resoucesIds);
	
	
}
