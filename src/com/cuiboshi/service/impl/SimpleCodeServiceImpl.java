package com.cuiboshi.service.impl;

import java.util.List;

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
	
	/**
	 * ����codeType��ѯ����
	 */
	@Override
	public SimpleCode get(String codeType) {
		System.out.println("���������kk"+codeType);
		SimpleCode simpleCodes = (SimpleCode) codeDao.queryHqlUnique(" from SimpleCode where codeType = ? ", codeType);
		return simpleCodes;
		
	}

	/**
	 * ��ѯ���еļ򵥴���
	 */
	@Override
	public List<SimpleCode> queryAll() {
		List<SimpleCode> lists = codeDao.queryAll(" from SimpleCode ");
		return lists;
	}

}
