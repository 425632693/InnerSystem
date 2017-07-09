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
 * 业务逻辑层的接口实现类
 * @author Administrator
 *
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Resource(name="userDaoImpl")
	private IUserDao userDao;

	/**
	 * 查询用户是否登录
	 */
	@Override
	public User login(String uname,String upass) {
		User u = (User) userDao.queryHqlUnique(" from User where uname=? and upass=?", uname,upass);
		return u;
	}

	/**
	 * 分页查询用户
	 * @return 
	 * @return 
	 */
	@Override
	public PageVo toList(PageVo vo) {
		
		if (vo.getPage() == 0 && vo.getPage() == null) {
			vo.setPage(1);
		}
		
		//查询所有用户
		String hql = "from User where 1 = 1";
		//查询用户的总数量
		String hqlCount = "select count(*) from User where 1 = 1";
		//创建一个接收参数的对象
		List<Object> params = new ArrayList<Object>();
		
		//判断PageVo 里的Map 封装不为空
		if(vo.getParams() != null){
			//如果获取页面的uname不为空的话向 hql 语句追加查询条件
			if(vo.getParams().get("uname")!=null && !vo.getParams().get("uname").equals("")){
				System.out.println("获取的页面输入的uname是："+vo.getParams().get("uname"));
				
				hql+= " and uname like ? ";
				hqlCount+= " and uname like ? "; 
				//把获取到的值当做占位符的参数传进去
				params.add("%"+vo.getParams().get("uname")+"%");
			}
			
			if(vo.getParams().get("email")!=null && !vo.getParams().get("email").equals("")){
				System.out.println("获取的页面输入的email是："+vo.getParams().get("email"));
				
				hql+= " and email like ? ";
				hqlCount+= " and email like ? "; 
				//把获取到的值当做占位符的参数传进去
				params.add("%"+vo.getParams().get("email")+"%");
			}
			
			if(vo.getParams().get("phone")!=null && !vo.getParams().get("phone").equals("")){
				System.out.println("获取的页面输入的phone是："+vo.getParams().get("phone"));
				
				hql+= " and phone like ? ";
				hqlCount+= " and phone like ?"; 
				//把获取到的值当做占位符的参数传进去
				params.add("%"+vo.getParams().get("phone")+"%");
				
			}
			
		}
		
		
		//查询分页信息
		List<Object> results = userDao.queryHqlList(hql, vo.getStarIndex(), vo.getPageSize(), params);
		//把查询到的结果集赋值给PageVo 工具类
		vo.setList(results);
		
		//查询总条数
		Integer count = userDao.queryHqlCount(hqlCount, params);
		//把查询到的总条数赋值给PageVo 工具类
		vo.setTotal(count);
		
		return vo;
		
	}
}
