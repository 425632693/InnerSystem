package com.cuiboshi.common;

import java.io.Serializable;
import java.util.List;

/**
 * 公用的数据访问层
 * @author Administrator
 *
 */
public interface IBaseDao<T> {
	
	/**
	 * 公用保存方法
	 * @param entity
	 */
	public Integer save(Object entity);
	
	
	/**
	 * 公用更新方法
	 * @param entity
	 */
	public void update(Object entity);
	
	
	/**
	 * 公用保存或者更新方法
	 * @param entity
	 */
	public void saveOrUpdate(Object entity);
	
	/**
	 * 公用的查询方法
	 * @param qid
	 * @return
	 */
	public T get(Serializable qid);
	
	
	
	/**
	 * 公用删除方法
	 * @param entity
	 */
	public void delete(Serializable did);
	
	
	/**
	 * 公用删除多个的方法
	 * @param entity
	 */
	public void delete(Serializable... dids);
	
	
	/**
	 * HQL 分页查询
	 * @param hql
	 * @param startIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<Object> queryHqlList(String hql,Integer startIndex,Integer pageSize,List<Object> params);
	
	
	/**
	 * HQL 总条数查询
	 * @param hql
	 * @param startIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public Integer queryHqlCount(String hql,List<Object> params);
	
	
	/**
	 * 查询单一的结果
	 * @param hql
	 * @param args
	 * @return
	 */
	public Object queryHqlUnique(String hql,Serializable... args);
	
	/**
	 * HQL 公用的修改方法
	 * @param hql
	 * @param args
	 * @return
	 */
	public Integer updateHql(String hql,Serializable... args);
	
	/**
	 * 查询要删除的子节点下面的所有子节点
	 * @param hql
	 * @param startIndex
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public List<Object> queryHqlList(String hql,Serializable... params);
	
	/**
	 * 保存资源与角色之间的关联关系
	 * @param sql
	 * @param args
	 * @return
	 */
	public Integer updateSql(String sql,Serializable... args);

}
