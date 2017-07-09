package com.cuiboshi.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cuiboshi.dao.ISimpleCodeDao;
import com.cuiboshi.entity.SimpleCode;
import com.cuiboshi.service.ISimpleCodeService;

/**
 * �ֵ���ҵ���߼����ʵ����
 * @author Administrator
 *
 */
@Service
@Transactional
public class SimpleCodeServiceImpl implements ISimpleCodeService{

	@Resource(name="simpleCodeDaoImpl")
	private ISimpleCodeDao codeDao;
	
	@Override
	public SimpleCode get(String code) {
		System.out.println("���������kk"+code);
		SimpleCode simpleCodes = (SimpleCode) codeDao.queryHqlUnique(" from SimpleCode where code = ? ", code);
		return simpleCodes;
		
	}

}
