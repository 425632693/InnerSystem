package com.cuiboshi.tags;

import com.cuiboshi.entity.SimpleCode;
import com.cuiboshi.service.ISimpleCodeService;
import com.cuiboshi.utlis.EhcacheUtils;
/**
 * 自定义EL的实现方法
 * @author Administrator
 *
 */
public class SimCode {
	
	public static String formatSimpleCode(String codeType){
		
		SimpleCode sc = (SimpleCode) EhcacheUtils.getInstance().get("simpleCode", codeType);
		
		if(sc != null){
			return sc.getType();
		}
		
		return "";
		
//		System.out.println("传入的数据"+code);
//		ISimpleCodeService codeService = (ISimpleCodeService) ContextUtils.getBean("simpleCodeServiceImpl");
//		SimpleCode sc = codeService.get(code);
//		System.out.println("返回的是："+sc.getCode());
//		return sc.getType();
	}

}
