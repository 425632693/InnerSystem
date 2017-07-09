package com.cuiboshi.action;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.cuiboshi.entity.User;
import com.cuiboshi.utlis.ImageUtils;

/**
 * ��¼ͼƬ��֤��
 * @author Administrator
 *
 */
@Results({
	@Result(name="success",location="/WEB-INF/jsp/admin/main.jsp")
})
@ParentPackage("struts-default")
@Namespace("/code")
@Controller
public class CodeAction {

	/**
	 * ��¼ͼƬ��֤��
	 * @return
	 */
	@Action("/vlogin")
	public void login(){
		
		HttpServletResponse response=ServletActionContext.getResponse();
		//����ҳ�治����  
	    response.setHeader("Pragma","no-cache");  
	    response.setHeader("Cache-Control","no-catch");  
	    response.setDateHeader("Expires",0);
	    //������Ӧ��ʽΪͼƬ
	    response.setContentType("image/jpeg");
		//��ȡ��֤��ͼƬ
	    BufferedImage vcodeImage=ImageUtils.createCode(ServletActionContext.getRequest().getSession()
	    		,"loginCode");
	    try {
	    	//���ͼƬ��ǰ̨����
			ImageIO.write(vcodeImage, "jpeg", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}