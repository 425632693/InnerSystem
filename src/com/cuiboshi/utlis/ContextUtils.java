package com.cuiboshi.utlis;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ContextUtils {

	/**
	 * 获取 Spring 上下文的
	 * 
	 * @return
	 */
	public static ApplicationContext getContext() {

		HttpServletRequest request = 
				((ServletRequestAttributes) 
				RequestContextHolder.getRequestAttributes())
				.getRequest();

		return WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	}

	/**
	 * 获取 Spring 上下文 中的 Bean
	 * 
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getContext().getBean(clazz);
	}

	/**
	 * 获取 Spring 上下文 中的 Bean
	 * 
	 * @return
	 */
	public static Object getBean(String beanName) {
		return getContext().getBean(beanName);
	}

	/**
	 * 获取 Spring 上下文 中的 Bean
	 * 
	 * @return
	 */
	public static <T> T getBean(String beanName, Class<T> clazz) {
		return getContext().getBean(beanName, clazz);
	}

}
