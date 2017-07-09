package com.cuiboshi.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.cuiboshi.entity.User;
import com.cuiboshi.service.IUserService;
import com.cuiboshi.utlis.MessageUtils;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.ModelDrivenInterceptor;

/**
 * 用户登录控制器
 * @author Administrator
 *
 */
@Results({
	@Result(name="success",location="/WEB-INF/jsp/admin/main.jsp"),
	@Result(name="error",location="/login.jsp")
})
@ParentPackage("struts-default")
@Namespace("/")
@Controller
public class LoginAction implements ModelDriven<User>{
	
	@Resource(name="userServiceImpl")
	private IUserService userService;
	
	private User user;
	
	@Action("login")
	public String login(){
		System.out.println("访问到Action中了");
		User u = userService.login(user.getUname(),user.getUpass());
		if(u == null){
			MessageUtils.addMessage("用户名或者密码错误！");
			System.out.println("输入的用户不存在");
			return "error";
		}
		System.out.println(u);
		return "success";
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public User getModel() {
		if(user==null){
			user = new User();
		}
		return user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
