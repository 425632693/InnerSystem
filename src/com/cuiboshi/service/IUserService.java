package com.cuiboshi.service;

import com.cuiboshi.entity.User;
import com.cuiboshi.utlis.vo.PageVo;

/**
 * ҵ���߼���ӿ���
 * @author Administrator
 *
 */
public interface IUserService {

	//�û���¼��ѯ
	public User login(String uname,String upass);
	
	//��ҳ��ѯ
	public PageVo toList(PageVo vo);
	
	
}
