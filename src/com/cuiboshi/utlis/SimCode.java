package com.cuiboshi.utlis;

import com.cuiboshi.entity.SimpleCode;
import com.cuiboshi.service.ISimpleCodeService;

public class SimCode {
	
	public static String formatSimpleCode(String code){
		
		System.out.println("传入的数据"+code);
		ISimpleCodeService codeService = (ISimpleCodeService) ContextUtils.getBean("simpleCodeServiceImpl");
		SimpleCode sc = codeService.get(code);
		System.out.println("返回的是："+sc.getCode());
		return sc.getType();
	}

}
