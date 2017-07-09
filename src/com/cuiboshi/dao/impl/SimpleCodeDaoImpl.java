package com.cuiboshi.dao.impl;

import javax.annotation.Resource;

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
	

	

}
