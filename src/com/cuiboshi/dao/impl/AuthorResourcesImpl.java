package com.cuiboshi.dao.impl;


import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cuiboshi.common.dao.BaseDaoImpl;
import com.cuiboshi.dao.IAuthorResources;
import com.cuiboshi.entity.AuthorResources;
/**
 * ��Դ��ʵ���ʵ����
 * @author Administrator
 *
 */
@Repository
public class AuthorResourcesImpl extends BaseDaoImpl<AuthorResources> 
		implements IAuthorResources{

	private HibernateTemplate template;
	
	@Override
	public Class<AuthorResources> getClazz() {
		return AuthorResources.class;
	}

	//ע��HibernateTemplate�Ĳ���ģ����
	@Override
	@Resource(name="hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

}
