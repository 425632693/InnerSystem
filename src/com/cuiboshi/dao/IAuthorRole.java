package com.cuiboshi.dao;

import com.cuiboshi.common.IBaseDao;
import com.cuiboshi.entity.AuthorResources;
import com.cuiboshi.entity.AuthorRole;
/**
 * ��ɫ��ʵ��Ľӿ���
 * @author Administrator
 *
 */
public interface IAuthorRole extends IBaseDao<AuthorRole> {

	public void saveAuthorRole(String roleId,String resoucesIds);
	
	
}
