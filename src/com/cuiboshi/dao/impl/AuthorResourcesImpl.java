package com.cuiboshi.dao.impl;


import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cuiboshi.common.dao.BaseDaoImpl;
import com.cuiboshi.dao.IAuthorResources;
import com.cuiboshi.entity.AuthorResources;
/**
 * 资源树实体的实现类
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

	//注入HibernateTemplate的操作模板类
	@Override
	@Resource(name="hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

}
