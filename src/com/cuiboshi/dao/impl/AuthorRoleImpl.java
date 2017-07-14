package com.cuiboshi.dao.impl;


import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cuiboshi.common.dao.BaseDaoImpl;
import com.cuiboshi.dao.IAuthorResources;
import com.cuiboshi.dao.IAuthorRole;
import com.cuiboshi.entity.AuthorResources;
import com.cuiboshi.entity.AuthorRole;
/**
 * 角色树实体的实现类
 * @author Administrator
 *
 */
@Repository
public class AuthorRoleImpl extends BaseDaoImpl<AuthorRole> 
		implements IAuthorRole{

	private HibernateTemplate template;
	
	@Override
	public Class<AuthorRole> getClazz() {
		return AuthorRole.class;
	}

	//注入HibernateTemplate的操作模板类
	@Override
	@Resource(name="hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	@Override
	public void saveAuthorRole(String roleId, String resoucesIds) {
		System.out.println("保存授权结果的Dao");
		//先删除已经保存的关联关系（与roleId有关联关系的 ）
		System.out.println("==================>"+roleId);
		updateSql("delete from sys_author_resources_role where roleId=?", roleId);
		//拆分多个资源的 ID(根据每个id中间的逗号进行拆分)
		String[] resoucesId = resoucesIds.split(",");
		
		System.out.println("--------------保存授权结果的Dao");
		
		//循环资源ID，保存与角色的关联关系
		for(String resouceId : resoucesId){
			System.out.println("++++++++++++++>"+resouceId);
			System.out.println("------------->"+roleId);
			updateSql("insert into sys_author_resources_role (resId,roleId) values (?,?)", 
					resouceId,roleId
//					Integer.valueOf(resouceId),Integer.valueOf(roleId),
//					Integer.parseInt(resouceId.trim()),Integer.parseInt(roleId.trim())
					);
		}
		
		
	}


}
