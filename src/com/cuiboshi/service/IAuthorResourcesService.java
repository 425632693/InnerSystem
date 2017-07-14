package com.cuiboshi.service;

import java.io.Serializable;

import com.cuiboshi.entity.AuthorResources;
import com.cuiboshi.utlis.vo.PageVo;

/**
 * ��Դ����ҵ���߼��Ľӿ���
 * @author Administrator
 *
 */
public interface IAuthorResourcesService {
	
	/**
	 * ��Դ�б��ѯ
	 * @param vo
	 * @return
	 */
	public PageVo toList(PageVo vo);
	
	/**
	 * ������߸���
	 * @param entity
	 */
	public void saveOrUpdate(AuthorResources entity);

	/**
	 * ���ݸ��ڵ�id���в�ѯ����
	 * @return
	 */
	public AuthorResources get(Serializable qid);

	/**
	 * ɾ���ڵ�
	 * @param did
	 */
	public void delete(Serializable did);
	
	/**
	 * ��ѯ���еĽڵ�
	 * @return
	 */
	public String queryAll();

}
