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
 * ���ݷ��ʲ��ʵ�ֽӿ���
 * @author Administrator
 *
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
	
	private HibernateTemplate template;

	//��ȡ������ʵ����
	@Override
	public Class<User> getClazz() {
		return User.class;
	}

	//ע��HibernateTemplate�Ĳ���ģ����
	@Override
	@Resource(name="hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
}
