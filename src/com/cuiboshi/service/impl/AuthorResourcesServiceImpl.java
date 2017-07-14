package com.cuiboshi.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.cuiboshi.dao.IAuthorResources;
import com.cuiboshi.entity.AuthorResources;
import com.cuiboshi.service.IAuthorResourcesService;
import com.cuiboshi.utlis.vo.PageVo;

/**
 * ��Դ����ҵ���߼����ʵ����
 * 
 * @author Administrator
 *
 */
@Service
@Transactional
public class AuthorResourcesServiceImpl implements IAuthorResourcesService {

	@Resource(name = "authorResourcesImpl")
	private IAuthorResources resDao;

	/**
	 * ��Դ�б��ѯ
	 */
	@Override
	public PageVo toList(PageVo vo) {
		if (vo.getPage() == 0 && vo.getPage() == null) {
			vo.setPage(1);
		}

		// ��ѯ�����û�
		String hql = "from AuthorResources where 1 = 1";
		// ��ѯ�û���������
		String hqlCount = "select count(*) from AuthorResources where 1 = 1";

		// ����һ�����ղ����Ķ���
		List<Object> params = new ArrayList<Object>();

		if (vo.getParams() != null) {
			System.out.println("��ȡ���ܲ���ֵ=======" + vo.getParams());
			if (vo.getParams().get("parentId") != null
					&& !vo.getParams().get("parentId").equals("")) {
				System.out.println("��ȡ�ĵ�ǰ���ڵ��id�ǣ�"
						+ vo.getParams().get("parentId"));

				hql += "and parentId = ?";
				hqlCount += "and parentId = ?";
				params.add(Integer.valueOf(vo.getParams().get("parentId")));
			}

		}

		// ��ѯ��ҳ��Ϣ
		List<Object> results = resDao.queryHqlList(hql, vo.getStarIndex(),
				vo.getPageSize(), params);
		// �Ѳ�ѯ���Ľ������ֵ��PageVo ������
		vo.setList(results);

		// ��ѯ������
		Integer count = resDao.queryHqlCount(hqlCount, params);
		System.out.println("�������ǣ�" + count);
		// �Ѳ�ѯ������������ֵ��PageVo ������
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
		List<Object> childs = resDao.queryHqlList(
				" from AuthorResources where parentId = ?", did);

		if (childs.size() > 0) {
			for (Object item : childs) {
				AuthorResources arItem = (AuthorResources) item;
				toDelete(arItem.getResId());
			}
		} else {
			resDao.delete(did);
		}

	}

	/**
	 * Ҫ��ɾ���Լ��������ж��Ƿ����ӽڵ㣬������ӽڵ���Ҫ�Ƚ��ӽڵ�ɾ��
	 * 
	 * @param did
	 */
	public void toDelete(Serializable did) {

	}

	@Override
	public String queryAll() {
		//����һ��JSONArray�����������ܵ�JSON�����
		JSONArray array = new JSONArray();
		//���ò�ѯ���ṹ�ķ���(��Ҫ�����������ֱ��ǣ�1.��ǰ�ڵ��id�����ڵ�ǰ�ڵ���Ǹ��ڵ㣬���Դ�null��
		//									2.�����ܵ�JSON�����(���ڵ��JSONArray����))
		buildTree(null, array);
		return array.toString();
	}

	/**
	 * ������
	 * 
	 * @param parentId
	 *            ��ǰ�ڵ�ĸ��ڵ�Id
	 * @param array
	 *            �������ڵ��JSONArray�Ķ���
	 */
	public void buildTree(Integer parentId, JSONArray array) {
		// ��ѯ�ڵ�(����һ������)
		List<? extends Object> items;
		//����parentId�Ƿ�Ϊnull���жϵ�ǰ�ڵ��Ƿ��Ǹ��ڵ�
		if (parentId == null) {
			//��ѯ��ǰ�ڵ���nullΪ���ڵ�
			items = resDao.queryHqlList("from AuthorResources where parentId is null  ");
		} else {
			//���parentId��Ϊnull�Ļ�����parentId��ֵ����ѯ��ǰ���ڵ�����������ӽڵ�
			items = resDao.queryHqlList("from AuthorResources where parentId=? ", parentId);
		}
		
		//ѭ��������ѯ�ĵ�ǰ�ڵ��Ƿ����ӽڵ�
		for (Object item : items) {
			// ��ѯ��ǰ�ڵ�
			AuthorResources ares = (AuthorResources) item;
			// ��ѯ��ǰ�ڵ��Ƿ����ӽڵ�
			List<? extends Object> citems = resDao.queryHqlList("from AuthorResources where parentId=? ", ares.getResId());
			// �жϵ�ǰ�ڵ��Ƿ����ӽڵ�
			if (citems.size() > 0) {
				// ���ӽڵ�
				JSONArray childArray = new JSONArray();
				//�����Լ������Ų������²�ѯ��ǰ�ӽڵ��Ƿ����ӽڵ㣬��������ӽڵ�Ļ������»�ȥ�����Լ���������
				//childArray�ǵ�ǰ�ӽڵ��Json���鼯��
				buildTree(ares.getResId(), childArray);
				/*----------------����Ĵ����������ṹ����ǰ������Ĵ����������ṹ�Ĺ����(���д���ִ������Ժ�)-------------------*/
				//�ѵ�ǰ�ڵ�Ķ���ת����JSONObject����
				JSONObject itemObj = JSONObject.fromObject(ares);
				//element (String key, Object value) ����/ֵ�Էŵ����JSONObject�������档�����ǰvalueΪ��(null)����ô������key���ڵĻ������key�ͻ��Ƴ����������
				//��key֮ǰ��valueֵ����ô�˷��������accumulate()����
				itemObj.element("children", childArray);
				//�Ѹ��ڵ����������ӽڵ��ѯ�����Ľڵ�ȫ��װ�����ڵ��JSON���鼯����
				array.add(itemObj);
			} else {
				// û���ӽڵ�
				array.add(JSONObject.fromObject(ares));
			}

		}
	}
	
	
	

	// public static void main(String[] args){
	// JSONObject object = JSONObject.fromObject(new AuthorResources());
	// System.out.println(object.toString());
	//
	// JSONArray array = new JSONArray();
	// array.add(object);
	// System.out.println(array.toString());
	// }

}
