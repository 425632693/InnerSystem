package com.cuiboshi.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cuiboshi.dao.IUserDao;
import com.cuiboshi.dao.impl.UserDaoImpl;
import com.cuiboshi.entity.User;
import com.cuiboshi.service.IUserService;
import com.cuiboshi.utlis.vo.PageVo;
/**
 * ҵ���߼���Ľӿ�ʵ����
 * @author Administrator
 *
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Resource(name="userDaoImpl")
	private IUserDao userDao;

	/**
	 * ��ѯ�û��Ƿ��¼
	 */
	@Override
	public User login(String uname,String upass) {
		User u = (User) userDao.queryHqlUnique(" from User where uname=? and upass=?", uname,upass);
		return u;
	}

	/**
	 * ��ҳ��ѯ�û�
	 * @return 
	 * @return 
	 */
	@Override
	public PageVo toList(PageVo vo) {
		
		if (vo.getPage() == 0 && vo.getPage() == null) {
			vo.setPage(1);
		}
		
		//��ѯ�����û�
		String hql = "from User where 1 = 1";
		//��ѯ�û���������
		String hqlCount = "select count(*) from User where 1 = 1";
		//����һ�����ղ����Ķ���
		List<Object> params = new ArrayList<Object>();
		
		//�ж�PageVo ���Map ��װ��Ϊ��
		if(vo.getParams() != null){
			//�����ȡҳ���uname��Ϊ�յĻ��� hql ���׷�Ӳ�ѯ����
			if(vo.getParams().get("uname")!=null && !vo.getParams().get("uname").equals("")){
				System.out.println("��ȡ��ҳ�������uname�ǣ�"+vo.getParams().get("uname"));
				
				hql+= " and uname like ? ";
				hqlCount+= " and uname like ? "; 
				//�ѻ�ȡ����ֵ����ռλ���Ĳ�������ȥ
				params.add("%"+vo.getParams().get("uname")+"%");
			}
			
			if(vo.getParams().get("email")!=null && !vo.getParams().get("email").equals("")){
				System.out.println("��ȡ��ҳ�������email�ǣ�"+vo.getParams().get("email"));
				
				hql+= " and email like ? ";
				hqlCount+= " and email like ? "; 
				//�ѻ�ȡ����ֵ����ռλ���Ĳ�������ȥ
				params.add("%"+vo.getParams().get("email")+"%");
			}
			
			if(vo.getParams().get("phone")!=null && !vo.getParams().get("phone").equals("")){
				System.out.println("��ȡ��ҳ�������phone�ǣ�"+vo.getParams().get("phone"));
				
				hql+= " and phone like ? ";
				hqlCount+= " and phone like ?"; 
				//�ѻ�ȡ����ֵ����ռλ���Ĳ�������ȥ
				params.add("%"+vo.getParams().get("phone")+"%");
				
			}
			
		}
		
		
		//��ѯ��ҳ��Ϣ
		List<Object> results = userDao.queryHqlList(hql, vo.getStarIndex(), vo.getPageSize(), params);
		//�Ѳ�ѯ���Ľ������ֵ��PageVo ������
		vo.setList(results);
		
		//��ѯ������
		Integer count = userDao.queryHqlCount(hqlCount, params);
		//�Ѳ�ѯ������������ֵ��PageVo ������
		vo.setTotal(count);
		
		return vo;
		
	}
}
