package com.cuiboshi.common.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.cuiboshi.common.IBaseDao;
/**
 * 公用数据访问层的实现类
 * @author Administrator
 *
 */
public abstract class BaseDaoImpl<T> implements IBaseDao<T>{

	@Resource(name="hibernateTemplate")
	protected HibernateTemplate template;
	
	/**
	 * 公用保存方法
	 * @param entity
	 */
	public Integer save(Object entity){
		return (Integer) template.save(entity);
	}
	
	
	/**
	 * 公用更新方法
	 * @param entity
	 */
	public void update(Object entity){
		template.update(entity);
	}
	
	
	/**
	 * 公用保存或者更新方法
	 * @param entity
	 */
	public void saveOrUpdate(Object entity){
		template.saveOrUpdate(entity);
	}
	
	/**
	 * 公用的查询方法
	 * @param qid
	 * @return
	 */
	public T get(Serializable qid){
		return template.get(getClazz(), qid);
	}
	
	
	
	/**
	 * 公用删除方法
	 * @param entity
	 */
	public void delete(Serializable did){
		template.delete(get(did));
	}
	
	
	/**
	 * 公用删除多个的方法
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
	 * HQL 分页查询
	 * @param hql
	 * @param startIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<Object> queryHqlList(String hql,Integer startIndex,Integer pageSize,List<Object> params){
		//获取绑定在当前线程上的Session
		System.out.println("====kkk=====");
		
		Session session = template.getSessionFactory().getCurrentSession();
		
		System.out.println("========="+session);
		//创建查询语句
		Query query = session.createQuery(hql);
		//设置参数
		if(params != null){
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		//设置分页参数
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		
		//得到查询结果
		return query.list();
	}
	
	
	/**
	 * HQL 总条数查询
	 * @param hql
	 * @param startIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public Integer queryHqlCount(String hql,List<Object> params){
		//获取绑定在当前线程上的Session
		Session session = template.getSessionFactory().getCurrentSession();
		//创建查询语句
		Query query = session.createQuery(hql);
		//设置参数
		if(params != null){
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		//得到查询结果
		Object count = query.uniqueResult();
		return Integer.valueOf(String.valueOf(count));
	}
	
	
	/**
	 * 查询单一的结果
	 * @param hql
	 * @param args
	 * @return
	 */
	public Object queryHqlUnique(String hql,Serializable... args){
		//获取当前线程上绑定的session
		Session session = template.getSessionFactory().getCurrentSession();
		//创建查询对象
		Query query = session.createQuery(hql);
		//设置查询参数
		if(args != null){
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		//获取查询返回的唯一结果集
		Object object = query.uniqueResult();
		return object;
	}
	
	/**
	 * HQL 公用的修改方法
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
	 * 查询要删除的子节点下面的所有子节点
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
	 * 抽象方法是在别的类调用本类的时候，必须重写这个本类里面的所有抽象方法
	 * @return
	 */
	//声明的一个抽象方法，用来获取调用本类的那个类的Class
	public abstract Class<T> getClazz();
	
	//哪个类调用本类必须重写HibernateTemplate方法把HibernateTemplate注入到类里，用来操作增删改查
	public abstract void setTemplate(HibernateTemplate template);
}
