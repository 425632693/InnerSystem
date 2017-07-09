package com.cuiboshi.common.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.cuiboshi.common.IBaseDao;
/**
 * �������ݷ��ʲ��ʵ����
 * @author Administrator
 *
 */
public abstract class BaseDaoImpl<T> implements IBaseDao<T>{

	@Resource(name="hibernateTemplate")
	protected HibernateTemplate template;
	
	/**
	 * ���ñ��淽��
	 * @param entity
	 */
	public Integer save(Object entity){
		return (Integer) template.save(entity);
	}
	
	
	/**
	 * ���ø��·���
	 * @param entity
	 */
	public void update(Object entity){
		template.update(entity);
	}
	
	
	/**
	 * ���ñ�����߸��·���
	 * @param entity
	 */
	public void saveOrUpdate(Object entity){
		template.saveOrUpdate(entity);
	}
	
	/**
	 * ���õĲ�ѯ����
	 * @param qid
	 * @return
	 */
	public T get(Serializable qid){
		return template.get(getClazz(), qid);
	}
	
	
	
	/**
	 * ����ɾ������
	 * @param entity
	 */
	public void delete(Serializable did){
		template.delete(get(did));
	}
	
	
	/**
	 * ����ɾ������ķ���
	 * @param entity
	 */
	public void delete(Serializable... dids){
		if(dids != null){
			for (int i = 0; i < dids.length; i++) {
				template.delete(get(dids[i]));
			}
		}
	}
	
	
	/**
	 * HQL ��ҳ��ѯ
	 * @param hql
	 * @param startIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<Object> queryHqlList(String hql,Integer startIndex,Integer pageSize,List<Object> params){
		//��ȡ���ڵ�ǰ�߳��ϵ�Session
		System.out.println("====kkk=====");
		
		Session session = template.getSessionFactory().getCurrentSession();
		
		System.out.println("========="+session);
		//������ѯ���
		Query query = session.createQuery(hql);
		//���ò���
		if(params != null){
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		//���÷�ҳ����
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		
		//�õ���ѯ���
		return query.list();
	}
	
	
	/**
	 * HQL ��������ѯ
	 * @param hql
	 * @param startIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public Integer queryHqlCount(String hql,List<Object> params){
		//��ȡ���ڵ�ǰ�߳��ϵ�Session
		Session session = template.getSessionFactory().getCurrentSession();
		//������ѯ���
		Query query = session.createQuery(hql);
		//���ò���
		if(params != null){
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		//�õ���ѯ���
		Object count = query.uniqueResult();
		return Integer.valueOf(String.valueOf(count));
	}
	
	
	/**
	 * ��ѯ��һ�Ľ��
	 * @param hql
	 * @param args
	 * @return
	 */
	public Object queryHqlUnique(String hql,Serializable... args){
		//��ȡ��ǰ�߳��ϰ󶨵�session
		Session session = template.getSessionFactory().getCurrentSession();
		//������ѯ����
		Query query = session.createQuery(hql);
		//���ò�ѯ����
		if(args != null){
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		//��ȡ��ѯ���ص�Ψһ�����
		Object object = query.uniqueResult();
		return object;
	}
	
	/**
	 * HQL ���õ��޸ķ���
	 * @param hql
	 * @param args
	 * @return
	 */
	public Integer updateHql(String hql,Serializable... args){
		Session session = template.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		if(args != null){
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		
		Integer row = query.executeUpdate();
		return row;
	}
	
	
	
	/**
	 * ��ѯҪɾ�����ӽڵ�����������ӽڵ�
	 * @param hql
	 * @param startIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<Object> queryHqlList(String hql,Serializable... args){
		
		Session session = template.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		if(args != null){
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		
		return query.list();
	}

	
	
	


	/**
	 * ���󷽷����ڱ������ñ����ʱ�򣬱�����д���������������г��󷽷�
	 * @return
	 */
	//������һ�����󷽷���������ȡ���ñ�����Ǹ����Class
	public abstract Class<T> getClazz();
	
	//�ĸ�����ñ��������дHibernateTemplate������HibernateTemplateע�뵽�������������ɾ�Ĳ�
	public abstract void setTemplate(HibernateTemplate template);
}
