package com.cuiboshi.service;

import com.cuiboshi.utlis.vo.PageVo;

/**
 * 角色树的业务逻辑的接口类
 * @author Administrator
 *
 */
public interface IAuthorRoleService {
	
	/**
	 * 角色列表查询
	 * @param vo
	 * @return
	 */
	public PageVo toList(PageVo vo);
	
	/**
	 * 删除并保存角色与资源的关联关系
	 * @param roleId
	 * @param resoucesIds
	 */
	public void saveAuthorRole(String roleId,String resoucesIds);
	
}
