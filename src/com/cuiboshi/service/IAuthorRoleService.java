package com.cuiboshi.service;

import com.cuiboshi.utlis.vo.PageVo;

/**
 * ��ɫ����ҵ���߼��Ľӿ���
 * @author Administrator
 *
 */
public interface IAuthorRoleService {
	
	/**
	 * ��ɫ�б��ѯ
	 * @param vo
	 * @return
	 */
	public PageVo toList(PageVo vo);
	
	/**
	 * ɾ���������ɫ����Դ�Ĺ�����ϵ
	 * @param roleId
	 * @param resoucesIds
	 */
	public void saveAuthorRole(String roleId,String resoucesIds);
	
}
