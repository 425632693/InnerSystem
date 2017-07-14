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
 * 资源树的业务逻辑层的实现类
 * @author Administrator
 *
 */
@Service
@Transactional
public class AuthorRoleServiceImpl implements IAuthorRoleService {

	@Resource(name="authorRoleImpl")
	private IAuthorRole roleDao;

	/**
	 * 角色列表查询
	 */
	@Override
	public PageVo toList(PageVo vo) {
		if (vo.getPage() == 0 && vo.getPage() == null) {
			vo.setPage(1);
		}
		
		//查询所有用户
		String hql = "from AuthorRole where 1 = 1";
		//查询用户的总数量
		String hqlCount = "select count(*) from AuthorRole where 1 = 1";
		
		//创建一个接收参数的对象
		List<Object> params = new ArrayList<Object>();
		
		
		if(vo.getParams() != null){
			
		}
		
		//查询分页信息
		List<Object> results = roleDao.queryHqlList(hql, vo.getStarIndex(), vo.getPageSize(), params);
		//把查询到的结果集赋值给PageVo 工具类
		vo.setList(results);
		
		//查询总条数
		Integer count = roleDao.queryHqlCount(hqlCount, params);
		System.out.println("总条数是："+count);
		//把查询到的总条数赋值给PageVo 工具类
		vo.setTotal(count);
		
		return vo;
		
	}

	/**
	 * 保存角色与资源的关联关系
	 */
	@Override
	public void saveAuthorRole(String roleId, String resoucesIds) {
		System.out.println("保存授权结果的Service");
		
		roleDao.saveAuthorRole(roleId, resoucesIds);
	}

}
