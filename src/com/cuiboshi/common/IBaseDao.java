package com.cuiboshi.common;

import java.io.Serializable;
import java.util.List;

/**
 * ���õ����ݷ��ʲ�
 * @author Administrator
 *
 */
public interface IBaseDao<T> {
	
	/**
	 * ���ñ��淽��
	 * @param entity
	 */
	public Integer save(Object entity);
	
	
	/**
	 * ���ø��·���
	 * @param entity
	 */
	public void update(Object entity);
	
	
	/**
	 * ���ñ�����߸��·���
	 * @param entity
	 */
	public void saveOrUpdate(Object entity);
	
	/**
	 * ���õĲ�ѯ����
	 * @param qid
	 * @return
	 */
	public T get(Serializable qid);
	
	
	
	/**
	 * ����ɾ������
	 * @param entity
	 */
	public void delete(Serializable did);
	
	
	/**
	 * ����ɾ������ķ���
	 * @param entity
	 */
	public void delete(Serializable... dids);
	
	
	/**
	 * HQL ��ҳ��ѯ
	 * @param hql
	 * @param startIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<Object> queryHqlList(String hql,Integer startIndex,Integer pageSize,List<Object> params);
	
	
	/**
	 * HQL ��������ѯ
	 * @param hql
	 * @param startIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public Integer queryHqlCount(String hql,List<Object> params);
	
	
	/**
	 * ��ѯ��һ�Ľ��
	 * @param hql
	 * @param args
	 * @return
	 */
	public Object queryHqlUnique(String hql,Serializable... args);
	
	/**
	 * HQL ���õ��޸ķ���
	 * @param hql
	 * @param args
	 * @return
	 */
	public Integer updateHql(String hql,Serializable... args);
	
	/**
	 * ��ѯҪɾ�����ӽڵ�����������ӽڵ�
	 * @param hql
	 * @param startIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<Object> queryHqlList(String hql,Serializable... params);
	
	/**
	 * ������Դ���ɫ֮��Ĺ�����ϵ
	 * @param sql
	 * @param args
	 * @return
	 */
	public Integer updateSql(String sql,Serializable... args);

}
