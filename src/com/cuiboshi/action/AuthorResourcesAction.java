package com.cuiboshi.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.cuiboshi.entity.AuthorResources;
import com.cuiboshi.service.IAuthorResourcesService;
import com.cuiboshi.utlis.vo.PageVo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 资源树的Action控制器
 * @author Administrator
 *
 */
@Results({
	@Result(name="toList",location="/WEB-INF/jsp/author/res/resources.jsp"),
	@Result(name="toAdd",location="/WEB-INF/jsp/author/res/add.jsp"),
	@Result(name="toList1",type="redirectAction",params={"namespace","/res","actionName","toList"})
})
@ParentPackage("struts-default")
@Namespace("/res")
@Controller
public class AuthorResourcesAction implements ModelDriven<PageVo>{

	//声明PageVo 为全局变量
	private PageVo pageVo;
	
	//声明AuthorResources实体类
	private AuthorResources ares;
	
	private Date time;
	
	@Resource(name="authorResourcesServiceImpl")
	private IAuthorResourcesService ars;

	/**
	 * 资源列表查询
	 * @return
	 */
	@Action("toList")
	public String toList(){
		//获取分页里的所有数据
		System.out.println("进入资源管理列表查询Action里了");
		pageVo = ars.toList(pageVo);
		return "toList";
	}
	
	/**
	 * 控制新增根节点的跳转
	 * @return
	 */
	@Action("toAdd")
	public String toAdd(){
		if(ares != null && ares.getResId() != null) {
			//根据根节点资源id查询，如果不等于空的话就查询数据
			ares = ars.get(ares.getResId());
		}
		return "toAdd";
	}
	
	/**
	 * 添加根节点
	 * @return
	 */
	@Action("add")
	public String add(){
		time= new Date();

//		转换日期格式
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		String times = format.format(time);
		
		ares.setCreateTs(time);
		ars.saveOrUpdate(ares);
		return "toAdd";
	}
	
	/**
	 * 删除节点要把自己的所有节点删除干净（包含自己）
	 * @return
	 */
	@Action("delete")
	public String delete(){
		System.out.println("进入删除子节点的Action中"+ares.getResId());
		ars.delete(ares.getResId());
		return "toList1";
	}
	
	
	/**
	 * 查询所有的节点
	 *  
	 */
	@Action("all")
	public void all() throws IOException{
		String results = ars.queryAll();
		try {
			//获取输出对象
			HttpServletResponse response = ServletActionContext.getResponse();
			//指定输出的内容格式
			response.setContentType("text/html;charset=utf-8");
			//创建输出流
			PrintWriter writer = response.getWriter();
			//向页面输出结果
			writer.write(results);
			//清空输出流
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("获取的树结构是："+results);
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

	public AuthorResources getAres() {
		return ares;
	}

	public void setAres(AuthorResources ares) {
		this.ares = ares;
	}
	
	
	
	
}
