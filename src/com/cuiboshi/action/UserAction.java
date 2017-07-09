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
 * �û�����
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
	//����PageVo Ϊȫ�ֱ���
	private PageVo pageVo;
	
	@Resource(name="userServiceImpl")
	private IUserService userService;

	/**
	 * �û��б��ѯ
	 * @return
	 */
	@Action("toList")
	public String toList(){
		//��ȡ��ҳ�����������
		System.out.println("toList Action");
		pageVo = userService.toList(pageVo);
		
		return "toList";
	}
	
	
	

	/**
	 * ��ֹ��һ�η��ʵ�ʱ��PageVo �Ķ���Ϊnull
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
