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
 * ��Դ����Action������
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

	//����PageVo Ϊȫ�ֱ���
	private PageVo pageVo;
	
	//����AuthorRoleʵ����
	private AuthorRole arole;
	
	private Date time;
	
	@Resource(name="authorRoleServiceImpl")
	private IAuthorRoleService role;

	/**
	 * ��ɫ�б��ѯ
	 * @return
	 */
	@Action("toList")
	public String toList(){
		//��ȡ��ҳ�����������
		System.out.println("�����ɫ�����б��ѯAction����");
		pageVo = role.toList(pageVo);
		return "toList";
	}
	
	
	
	/**
	 * ��ɫ��Ȩ������
	 * @return
	 */
	@Action("toAuther")
	public String toAuther(){
		//��ȡ��ҳ�����������
		System.out.println("�����ɫ��ȨAction����");
		return "toAuther";
	}
	
	/**
	 * ������Ȩ���
	 * @return
	 */
	@Action("saveAuthorRole")
	public String saveAuthorRole(){
		System.out.println("������Ȩ�����Action");
		//��ȡ�ύ�Ĳ���
		String resoucesId = ServletActionContext.getRequest().getParameter("resoucesIds");
		System.out.println("��ȡҳ�����Դid�ǣ�"+resoucesId);
		String roleId = ServletActionContext.getRequest().getParameter("roleId");
		System.out.println("��ȡҳ��Ľ�ɫid�ǣ�"+roleId);
		//�����ɫ����Դ֮��Ĺ�����ϵ
		role.saveAuthorRole(roleId, resoucesId);
		
		return "ftoList";
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


	public AuthorRole getArole() {
		return arole;
	}


	public void setArole(AuthorRole arole) {
		this.arole = arole;
	}
}
