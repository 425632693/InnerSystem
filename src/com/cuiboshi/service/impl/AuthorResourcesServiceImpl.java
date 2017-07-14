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
 * 资源树的业务逻辑层的实现类
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
	 * 资源列表查询
	 */
	@Override
	public PageVo toList(PageVo vo) {
		if (vo.getPage() == 0 && vo.getPage() == null) {
			vo.setPage(1);
		}

		// 查询所有用户
		String hql = "from AuthorResources where 1 = 1";
		// 查询用户的总数量
		String hqlCount = "select count(*) from AuthorResources where 1 = 1";

		// 创建一个接收参数的对象
		List<Object> params = new ArrayList<Object>();

		if (vo.getParams() != null) {
			System.out.println("获取的总参数值=======" + vo.getParams());
			if (vo.getParams().get("parentId") != null
					&& !vo.getParams().get("parentId").equals("")) {
				System.out.println("获取的当前父节点的id是："
						+ vo.getParams().get("parentId"));

				hql += "and parentId = ?";
				hqlCount += "and parentId = ?";
				params.add(Integer.valueOf(vo.getParams().get("parentId")));
			}

		}

		// 查询分页信息
		List<Object> results = resDao.queryHqlList(hql, vo.getStarIndex(),
				vo.getPageSize(), params);
		// 把查询到的结果集赋值给PageVo 工具类
		vo.setList(results);

		// 查询总条数
		Integer count = resDao.queryHqlCount(hqlCount, params);
		System.out.println("总条数是：" + count);
		// 把查询到的总条数赋值给PageVo 工具类
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
	 * 要想删除自己，必须判断是否有子节点，如果有子节点需要先将子节点删除
	 * 
	 * @param did
	 */
	public void toDelete(Serializable did) {

	}

	@Override
	public String queryAll() {
		//创建一个JSONArray数组来接收总的JSON结果集
		JSONArray array = new JSONArray();
		//调用查询树结构的方法(需要的两个参数分别是：1.当前节点的id，由于当前节点就是父节点，所以传null。
		//									2.接收总的JSON结果集(父节点的JSONArray数组))
		buildTree(null, array);
		return array.toString();
	}

	/**
	 * 构建树
	 * 
	 * @param parentId
	 *            当前节点的父节点Id
	 * @param array
	 *            构建父节点的JSONArray的对象
	 */
	public void buildTree(Integer parentId, JSONArray array) {
		// 查询节点(声明一个变量)
		List<? extends Object> items;
		//根据parentId是否为null来判断当前节点是否是父节点
		if (parentId == null) {
			//查询当前节点是null为父节点
			items = resDao.queryHqlList("from AuthorResources where parentId is null  ");
		} else {
			//如果parentId不为null的话根据parentId的值来查询当前父节点下面的所有子节点
			items = resDao.queryHqlList("from AuthorResources where parentId=? ", parentId);
		}
		
		//循环解析查询的当前节点是否有子节点
		for (Object item : items) {
			// 查询当前节点
			AuthorResources ares = (AuthorResources) item;
			// 查询当前节点是否有子节点
			List<? extends Object> citems = resDao.queryHqlList("from AuthorResources where parentId=? ", ares.getResId());
			// 判断当前节点是否有子节点
			if (citems.size() > 0) {
				// 有子节点
				JSONArray childArray = new JSONArray();
				//调用自己并带着参数重新查询当前子节点是否还有子节点，如果还有子节点的话会重新回去调用自己本方法，
				//childArray是当前子节点的Json数组集合
				buildTree(ares.getResId(), childArray);
				/*----------------上面的代码是在树结构构造前，下面的代码是在树结构的构造后(所有代码执行完毕以后)-------------------*/
				//把当前节点的对象转换成JSONObject对象
				JSONObject itemObj = JSONObject.fromObject(ares);
				//element (String key, Object value) 将键/值对放到这个JSONObject对象里面。如果当前value为空(null)，那么如果这个key存在的话，这个key就会移除掉。如果这
				//个key之前有value值，那么此方法会调用accumulate()方法
				itemObj.element("children", childArray);
				//把父节点下面所有子节点查询出来的节点全部装进父节点的JSON数组集合里
				array.add(itemObj);
			} else {
				// 没有子节点
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
