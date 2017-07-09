package com.cuiboshi.service;

import com.cuiboshi.entity.User;
import com.cuiboshi.utlis.vo.PageVo;

/**
 * 业务逻辑层接口类
 * @author Administrator
 *
 */
public interface IUserService {

	//用户登录查询
	public User login(String uname,String upass);
	
	//分页查询
	public PageVo toList(PageVo vo);
	
	
}
