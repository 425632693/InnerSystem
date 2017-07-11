package com.cuiboshi.service;

import java.util.List;

import com.cuiboshi.entity.SimpleCode;

/**
 * 字典表的接口类
 * @author Administrator
 *
 */
public interface ISimpleCodeService {

	/**
	 * 根据codeType查询数据
	 * @param code
	 * @return
	 */
	public SimpleCode get(String codeType);
	
	/**
	 * 查询所有的简单代码
	 * @return
	 */
	public List<SimpleCode> queryAll();

	
}
