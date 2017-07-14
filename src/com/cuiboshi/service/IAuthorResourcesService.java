package com.cuiboshi.service;

import java.io.Serializable;

import com.cuiboshi.entity.AuthorResources;
import com.cuiboshi.utlis.vo.PageVo;

/**
 * 资源树的业务逻辑的接口类
 * @author Administrator
 *
 */
public interface IAuthorResourcesService {
	
	/**
	 * 资源列表查询
	 * @param vo
	 * @return
	 */
	public PageVo toList(PageVo vo);
	
	/**
	 * 保存或者更新
	 * @param entity
	 */
	public void saveOrUpdate(AuthorResources entity);

	/**
	 * 根据根节点id进行查询数据
	 * @return
	 */
	public AuthorResources get(Serializable qid);

	/**
	 * 删除节点
	 * @param did
	 */
	public void delete(Serializable did);
	
	/**
	 * 查询所有的节点
	 * @return
	 */
	public String queryAll();

}
