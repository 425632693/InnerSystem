package com.cuiboshi.action;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.cuiboshi.entity.AuthorResources;
import com.cuiboshi.entity.AuthorRole;
import com.cuiboshi.service.IAuthorResourcesService;
import com.cuiboshi.service.IAuthorRoleService;
import com.cuiboshi.utlis.vo.PageVo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 资源树的Action控制器
 * @author Administrator
 *
 */
@Results({
	@Result(name="toList",location="/WEB-INF/jsp/author/role/toList.jsp"),
	@Result(name="toAuther",location="/WEB-INF/jsp/author/role/toAuther.jsp"),
	@Result(name="ftoList",type="redirectAction",params={"namespace","/role","actionName","toList"})
})
@ParentPackage("struts-default")
@Namespace("/role")
@Controller
public class AuthorRoleAction implements ModelDriven<PageVo>{

	//声明PageVo 为全局变量
	private PageVo pageVo;
	
	//声明AuthorRole实体类
	private AuthorRole arole;
	
	private Date time;
	
	@Resource(name="authorRoleServiceImpl")
	private IAuthorRoleService role;

	/**
	 * 角色列表查询
	 * @return
	 */
	@Action("toList")
	public String toList(){
		//获取分页里的所有数据
		System.out.println("进入角色管理列表查询Action里了");
		pageVo = role.toList(pageVo);
		return "toList";
	}
	
	
	
	/**
	 * 角色授权管理里
	 * @return
	 */
	@Action("toAuther")
	public String toAuther(){
		//获取分页里的所有数据
		System.out.println("进入角色授权Action里了");
		return "toAuther";
	}
	
	/**
	 * 保存授权结果
	 * @return
	 */
	@Action("saveAuthorRole")
	public String saveAuthorRole(){
		System.out.println("保存授权结果的Action");
		//获取提交的参数
		String resoucesId = ServletActionContext.getRequest().getParameter("resoucesIds");
		System.out.println("获取页面的资源id是："+resoucesId);
		String roleId = ServletActionContext.getRequest().getParameter("roleId");
		System.out.println("获取页面的角色id是："+roleId);
		//保存角色与资源之间的关联关系
		role.saveAuthorRole(roleId, resoucesId);
		
		return "ftoList";
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


	public AuthorRole getArole() {
		return arole;
	}


	public void setArole(AuthorRole arole) {
		this.arole = arole;
	}
}
