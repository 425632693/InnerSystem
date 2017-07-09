package com.cuiboshi.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cuiboshi.dao.IAuthorResources;
import com.cuiboshi.entity.AuthorResources;
import com.cuiboshi.service.IAuthorResourcesService;
import com.cuiboshi.utlis.vo.PageVo;
/**
 * ��Դ����ҵ���߼����ʵ����
 * @author Administrator
 *
 */
@Service
@Transactional
public class AuthorResourcesServiceImpl implements IAuthorResourcesService {

	@Resource(name="authorResourcesImpl")
	private IAuthorResources resDao;

	/**
	 * ��Դ�б��ѯ
	 */
	@Override
	public PageVo toList(PageVo vo) {
		if (vo.getPage() == 0 && vo.getPage() == null) {
			vo.setPage(1);
		}
		
		//��ѯ�����û�
		String hql = "from AuthorResources where 1 = 1";
		//��ѯ�û���������
		String hqlCount = "select count(*) from AuthorResources where 1 = 1";
		
		//����һ�����ղ����Ķ���
		List<Object> params = new ArrayList<Object>();
		
		
		if(vo.getParams() != null){
			System.out.println("��ȡ���ܲ���ֵ======="+vo.getParams());
			if(vo.getParams().get("parentId") != null && !vo.getParams().get("parentId").equals("")){
				System.out.println("��ȡ�ĵ�ǰ���ڵ��id�ǣ�"+vo.getParams().get("parentId"));
				
				hql+= "and parentId = ?";
				hqlCount+= "and parentId = ?";
				params.add(Integer.valueOf(vo.getParams().get("parentId")));
			}
			
		}
		
		//��ѯ��ҳ��Ϣ
		List<Object> results = resDao.queryHqlList(hql, vo.getStarIndex(), vo.getPageSize(), params);
		//�Ѳ�ѯ���Ľ������ֵ��PageVo ������
		vo.setList(results);
		
		//��ѯ������
		Integer count = resDao.queryHqlCount(hqlCount, params);
		System.out.println("�������ǣ�"+count);
		//�Ѳ�ѯ������������ֵ��PageVo ������
		vo.setTotal(count);
		
		return vo;
		
	}

	/**
	 * ������߸���
	 */
	@Override
	public void saveOrUpdate(AuthorResources entity) {
		resDao.saveOrUpdate(entity);
	}

	/**
	 * ���ݸ��ڵ�id��ѯ����
	 */
	@Override
	public AuthorResources get(Serializable qid) {
		return resDao.get(qid);
	}

	/**
	 * ɾ���ڵ�
	 */
	@Override
	public void delete(Serializable did) {
		List<Object> childs = resDao.queryHqlList(" from AuthorResources where parentId = ?", did);
		
		if(childs.size() > 0){
			for (Object item : childs) {
				AuthorResources arItem = (AuthorResources) item;
				toDelete(arItem.getResId());
			}
		}
		else{
			resDao.delete(did);
		}
		
	}
	
	/**
	 * Ҫ��ɾ���Լ��������ж��Ƿ����ӽڵ㣬������ӽڵ���Ҫ�Ƚ��ӽڵ�ɾ��
	 * @param did
	 */
	public void toDelete(Serializable did){
		
		
	}
	
	
	
	
	
}
