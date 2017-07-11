package com.cuiboshi.tags;

import com.cuiboshi.entity.SimpleCode;
import com.cuiboshi.service.ISimpleCodeService;
import com.cuiboshi.utlis.EhcacheUtils;
/**
 * �Զ���EL��ʵ�ַ���
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
		
//		System.out.println("���������"+code);
//		ISimpleCodeService codeService = (ISimpleCodeService) ContextUtils.getBean("simpleCodeServiceImpl");
//		SimpleCode sc = codeService.get(code);
//		System.out.println("���ص��ǣ�"+sc.getCode());
//		return sc.getType();
	}

}
