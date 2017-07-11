package com.cuiboshi.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cuiboshi.dao.ISimpleCodeDao;
import com.cuiboshi.entity.SimpleCode;
import com.cuiboshi.service.ISimpleCodeService;

/**
 * 字典表的业务逻辑层的实现类
 * @author Administrator
 *
 */
@Service
@Transactional
public class SimpleCodeServiceImpl implements ISimpleCodeService{

	@Resource(name="simpleCodeDaoImpl")
	private ISimpleCodeDao codeDao;
	
	/**
	 * 根据codeType查询数据
	 */
	@Override
	public SimpleCode get(String codeType) {
		System.out.println("传入的数据kk"+codeType);
		SimpleCode simpleCodes = (SimpleCode) codeDao.queryHqlUnique(" from SimpleCode where codeType = ? ", codeType);
		return simpleCodes;
		
	}

	/**
	 * 查询所有的简单代码
	 */
	@Override
	public List<SimpleCode> queryAll() {
		List<SimpleCode> lists = codeDao.queryAll(" from SimpleCode ");
		return lists;
	}

}
