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
 * 资源树的业务逻辑层的实现类
 * @author Administrator
 *
 */
@Service
@Transactional
public class AuthorResourcesServiceImpl implements IAuthorResourcesService {

	@Resource(name="authorResourcesImpl")
	private IAuthorResources resDao;

	/**
	 * 资源列表查询
	 */
	@Override
	public PageVo toList(PageVo vo) {
		if (vo.getPage() == 0 && vo.getPage() == null) {
			vo.setPage(1);
		}
		
		//查询所有用户
		String hql = "from AuthorResources where 1 = 1";
		//查询用户的总数量
		String hqlCount = "select count(*) from AuthorResources where 1 = 1";
		
		//创建一个接收参数的对象
		List<Object> params = new ArrayList<Object>();
		
		
		if(vo.getParams() != null){
			System.out.println("获取的总参数值======="+vo.getParams());
			if(vo.getParams().get("parentId") != null && !vo.getParams().get("parentId").equals("")){
				System.out.println("获取的当前父节点的id是："+vo.getParams().get("parentId"));
				
				hql+= "and parentId = ?";
				hqlCount+= "and parentId = ?";
				params.add(Integer.valueOf(vo.getParams().get("parentId")));
			}
			
		}
		
		//查询分页信息
		List<Object> results = resDao.queryHqlList(hql, vo.getStarIndex(), vo.getPageSize(), params);
		//把查询到的结果集赋值给PageVo 工具类
		vo.setList(results);
		
		//查询总条数
		Integer count = resDao.queryHqlCount(hqlCount, params);
		System.out.println("总条数是："+count);
		//把查询到的总条数赋值给PageVo 工具类
		vo.setTotal(count);
		
		return vo;
		
	}

	/**
	 * 保存或者更新
	 */
	@Override
	public void saveOrUpdate(AuthorResources entity) {
		resDao.saveOrUpdate(entity);
	}

	/**
	 * 根据根节点id查询数据
	 */
	@Override
	public AuthorResources get(Serializable qid) {
		return resDao.get(qid);
	}

	/**
	 * 删除节点
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
	 * 要想删除自己，必须判断是否有子节点，如果有子节点需要先将子节点删除
	 * @param did
	 */
	public void toDelete(Serializable did){
		
		
	}
	
	
	
	
	
}
