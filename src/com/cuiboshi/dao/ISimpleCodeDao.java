package com.cuiboshi.dao;

import java.util.List;

import com.cuiboshi.common.IBaseDao;
import com.cuiboshi.entity.SimpleCode;

/**
 * 字典表的接口类
 * @author Administrator
 *
 */
public interface ISimpleCodeDao extends IBaseDao<SimpleCode>{

	/**
	 * 查询所有简单代码
	 * @return
	 */
	public List<SimpleCode> queryAll(String hql);
	
}
