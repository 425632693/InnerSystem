package com.cuiboshi.dao;

import java.util.List;

import com.cuiboshi.common.IBaseDao;
import com.cuiboshi.entity.SimpleCode;

/**
 * �ֵ��Ľӿ���
 * @author Administrator
 *
 */
public interface ISimpleCodeDao extends IBaseDao<SimpleCode>{

	/**
	 * ��ѯ���м򵥴���
	 * @return
	 */
	public List<SimpleCode> queryAll(String hql);
	
}
