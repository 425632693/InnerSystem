package com.cuiboshi.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cuiboshi.common.dao.BaseDaoImpl;
import com.cuiboshi.dao.ISimpleCodeDao;
import com.cuiboshi.entity.SimpleCode;

/**
 * 字典表的实现类
 * @author Administrator
 *
 */
@Repository
public class SimpleCodeDaoImpl extends BaseDaoImpl<SimpleCode> implements ISimpleCodeDao{
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate template;

	
	@Override
	public Class<SimpleCode> getClazz() {
		return SimpleCode.class;
	}

	@Override
	@Resource(name="hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template=template;
		
	}

	@Override
	public List<SimpleCode> queryAll(String hql) {
		Session session = template.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	

	

}
