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
 * ��ɫ��ʵ���ʵ����
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

	//ע��HibernateTemplate�Ĳ���ģ����
	@Override
	@Resource(name="hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	@Override
	public void saveAuthorRole(String roleId, String resoucesIds) {
		System.out.println("������Ȩ�����Dao");
		//��ɾ���Ѿ�����Ĺ�����ϵ����roleId�й�����ϵ�� ��
		System.out.println("==================>"+roleId);
		updateSql("delete from sys_author_resources_role where roleId=?", roleId);
		//��ֶ����Դ�� ID(����ÿ��id�м�Ķ��Ž��в��)
		String[] resoucesId = resoucesIds.split(",");
		
		System.out.println("--------------������Ȩ�����Dao");
		
		//ѭ����ԴID���������ɫ�Ĺ�����ϵ
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
