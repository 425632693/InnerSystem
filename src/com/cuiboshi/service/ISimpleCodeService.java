package com.cuiboshi.service;

import java.util.List;

import com.cuiboshi.entity.SimpleCode;

/**
 * �ֵ��Ľӿ���
 * @author Administrator
 *
 */
public interface ISimpleCodeService {

	/**
	 * ����codeType��ѯ����
	 * @param code
	 * @return
	 */
	public SimpleCode get(String codeType);
	
	/**
	 * ��ѯ���еļ򵥴���
	 * @return
	 */
	public List<SimpleCode> queryAll();

	
}
