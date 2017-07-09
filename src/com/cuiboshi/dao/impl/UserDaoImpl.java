package com.cuiboshi.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cuiboshi.common.dao.BaseDaoImpl;
import com.cuiboshi.dao.IUserDao;
import com.cuiboshi.entity.User;
/**
 * 数据访问层的实现接口类
 * @author Administrator
 *
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
	
	private HibernateTemplate template;

	//获取操作的实体类
	@Override
	public Class<User> getClazz() {
		return User.class;
	}

	//注入HibernateTemplate的操作模板类
	@Override
	@Resource(name="hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
}
