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
 * ��Դ����Action������
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

	//����PageVo Ϊȫ�ֱ���
	private PageVo pageVo;
	
	//����AuthorResourcesʵ����
	private AuthorResources ares;
	
	private Date time;
	
	@Resource(name="authorResourcesServiceImpl")
	private IAuthorResourcesService ars;

	/**
	 * ��Դ�б��ѯ
	 * @return
	 */
	@Action("toList")
	public String toList(){
		//��ȡ��ҳ�����������
		System.out.println("������Դ�����б��ѯAction����");
		pageVo = ars.toList(pageVo);
		return "toList";
	}
	
	/**
	 * �����������ڵ����ת
	 * @return
	 */
	@Action("toAdd")
	public String toAdd(){
		if(ares != null && ares.getResId() != null) {
			//���ݸ��ڵ���Դid��ѯ����������ڿյĻ��Ͳ�ѯ����
			ares = ars.get(ares.getResId());
		}
		return "toAdd";
	}
	
	/**
	 * ��Ӹ��ڵ�
	 * @return
	 */
	@Action("add")
	public String add(){
		time= new Date();

//		ת�����ڸ�ʽ
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		String times = format.format(time);
		
		ares.setCreateTs(time);
		ars.saveOrUpdate(ares);
		return "toAdd";
	}
	
	/**
	 * ɾ���ڵ�Ҫ���Լ������нڵ�ɾ���ɾ��������Լ���
	 * @return
	 */
	@Action("delete")
	public String delete(){
		System.out.println("����ɾ���ӽڵ��Action��"+ares.getResId());
		ars.delete(ares.getResId());
		return "toList1";
	}
	
	
	/**
	 * ��ѯ���еĽڵ�
	 *  
	 */
	@Action("all")
	public void all() throws IOException{
		String results = ars.queryAll();
		try {
			//��ȡ�������
			HttpServletResponse response = ServletActionContext.getResponse();
			//ָ����������ݸ�ʽ
			response.setContentType("text/html;charset=utf-8");
			//���������
			PrintWriter writer = response.getWriter();
			//��ҳ��������
			writer.write(results);
			//��������
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("��ȡ�����ṹ�ǣ�"+results);
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

	public AuthorResources getAres() {
		return ares;
	}

	public void setAres(AuthorResources ares) {
		this.ares = ares;
	}
	
	
	
	
}
