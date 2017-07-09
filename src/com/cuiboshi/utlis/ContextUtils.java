package com.cuiboshi.utlis;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ContextUtils {

	/**
	 * ��ȡ Spring �����ĵ�
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
	 * ��ȡ Spring ������ �е� Bean
	 * 
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getContext().getBean(clazz);
	}

	/**
	 * ��ȡ Spring ������ �е� Bean
	 * 
	 * @return
	 */
	public static Object getBean(String beanName) {
		return getContext().getBean(beanName);
	}

	/**
	 * ��ȡ Spring ������ �е� Bean
	 * 
	 * @return
	 */
	public static <T> T getBean(String beanName, Class<T> clazz) {
		return getContext().getBean(beanName, clazz);
	}

}
