package com.cuiboshi.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cuiboshi.dao.IAuthorResources;
import com.cuiboshi.dao.IAuthorRole;
import com.cuiboshi.entity.AuthorResources;
import com.cuiboshi.entity.AuthorRole;
import com.cuiboshi.service.IAuthorResourcesService;
import com.cuiboshi.service.IAuthorRoleService;
import com.cuiboshi.utlis.vo.PageVo;
/**
 * ��Դ����ҵ���߼����ʵ����
 * @author Administrator
 *
 */
@Service
@Transactional
public class AuthorRoleServiceImpl implements IAuthorRoleService {

	@Resource(name="authorRoleImpl")
	private IAuthorRole roleDao;

	/**
	 * ��ɫ�б��ѯ
	 */
	@Override
	public PageVo toList(PageVo vo) {
		if (vo.getPage() == 0 && vo.getPage() == null) {
			vo.setPage(1);
		}
		
		//��ѯ�����û�
		String hql = "from AuthorRole where 1 = 1";
		//��ѯ�û���������
		String hqlCount = "select count(*) from AuthorRole where 1 = 1";
		
		//����һ�����ղ����Ķ���
		List<Object> params = new ArrayList<Object>();
		
		
		if(vo.getParams() != null){
			
		}
		
		//��ѯ��ҳ��Ϣ
		List<Object> results = roleDao.queryHqlList(hql, vo.getStarIndex(), vo.getPageSize(), params);
		//�Ѳ�ѯ���Ľ������ֵ��PageVo ������
		vo.setList(results);
		
		//��ѯ������
		Integer count = roleDao.queryHqlCount(hqlCount, params);
		System.out.println("�������ǣ�"+count);
		//�Ѳ�ѯ������������ֵ��PageVo ������
		vo.setTotal(count);
		
		return vo;
		
	}

	/**
	 * �����ɫ����Դ�Ĺ�����ϵ
	 */
	@Override
	public void saveAuthorRole(String roleId, String resoucesIds) {
		System.out.println("������Ȩ�����Service");
		
		roleDao.saveAuthorRole(roleId, resoucesIds);
	}

}
