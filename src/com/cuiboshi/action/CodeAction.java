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
 * 登录图片认证码
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
	 * 登录图片认证码
	 * @return
	 */
	@Action("/vlogin")
	public void login(){
		
		HttpServletResponse response=ServletActionContext.getResponse();
		//设置页面不缓存  
	    response.setHeader("Pragma","no-cache");  
	    response.setHeader("Cache-Control","no-catch");  
	    response.setDateHeader("Expires",0);
	    //设置响应格式为图片
	    response.setContentType("image/jpeg");
		//获取认证码图片
	    BufferedImage vcodeImage=ImageUtils.createCode(ServletActionContext.getRequest().getSession()
	    		,"loginCode");
	    try {
	    	//输出图片到前台界面
			ImageIO.write(vcodeImage, "jpeg", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
