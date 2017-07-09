package com.cuiboshi.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.cuiboshi.common.IBaseDao;
import com.cuiboshi.service.IUserService;
import com.cuiboshi.utlis.vo.PageVo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户管理
 * @author Administrator
 *
 */
@Results({
	@Result(name="toList",location="/WEB-INF/jsp/user/toList.jsp")
})
@ParentPackage("struts-default")
@Namespace("/user")
@Controller
public class UserAction implements ModelDriven<PageVo>{
	//声明PageVo 为全局变量
	private PageVo pageVo;
	
	@Resource(name="userServiceImpl")
	private IUserService userService;

	/**
	 * 用户列表查询
	 * @return
	 */
	@Action("toList")
	public String toList(){
		//获取分页里的所有数据
		System.out.println("toList Action");
		pageVo = userService.toList(pageVo);
		
		return "toList";
	}
	
	
	

	/**
	 * 防止第一次访问的时候PageVo 的对象为null
	 */
	@Override
	public PageVo getModel() {
		if(pageVo == null){
			pageVo = new PageVo();
		}
		return pageVo;
	}




	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
