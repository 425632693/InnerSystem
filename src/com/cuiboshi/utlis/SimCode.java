package com.cuiboshi.utlis;

import com.cuiboshi.entity.SimpleCode;
import com.cuiboshi.service.ISimpleCodeService;

public class SimCode {
	
	public static String formatSimpleCode(String code){
		
		System.out.println("���������"+code);
		ISimpleCodeService codeService = (ISimpleCodeService) ContextUtils.getBean("simpleCodeServiceImpl");
		SimpleCode sc = codeService.get(code);
		System.out.println("���ص��ǣ�"+sc.getCode());
		return sc.getType();
	}

}
