package com.cuiboshi.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cuiboshi.entity.SimpleCode;
import com.cuiboshi.service.ISimpleCodeService;
import com.cuiboshi.utlis.EhcacheUtils;

/**
 * �򵥴��뻺��
 * @author Administrator
 *
 */
@Component
public class SimpleCodeCache {
	
	@Resource(name="simpleCodeServiceImpl")
	private ISimpleCodeService simpleCodeService;

	/**
	 * ʹ�ø�ע��spring���Զ����ø÷�������ʼ�� 
	 */
	@PostConstruct
	public void init(){
		//��ȡ��ѯ�������н����
		List<SimpleCode> result = simpleCodeService.queryAll();
		
		//����һ�� List<SimpleCode> ������� Map ����(���������з���)
		Map<String, List<SimpleCode>> maps = new HashMap<String, List<SimpleCode>>();
		
		//�ѽ������ŵ�������
		for(SimpleCode item : result){
			//ͨ��EhcacheUtils��������д�����ݵ������ļ���
			EhcacheUtils.getInstance().put("simpleCode", item.getCodeType(), item);
			System.out.println("�浽�����е�codeType�ǣ�"+item.getCodeType());
			
			//������ǰ�򵥴���������ʲô��
			//���ݻ�ȡ��map�����еĵõ���item.getCode()��ȡ���е�codeType
			List<SimpleCode> lists = maps.get(item.getCode());
			if(lists == null){
				lists = new ArrayList<SimpleCode>();
				lists.add(item);
				maps.put(item.getCode(), lists);
			}
			else{
				lists.add(item);
				maps.put(item.getCode(), lists);
			}
		}
		//�����������з��໺��
		//ͨ��map���ϵĵ�������ȡmap������key
		Iterator<String> keys = maps.keySet().iterator();
		while(keys.hasNext()){
			String k = keys.next();
			EhcacheUtils.getInstance().put("simpleCode", k, maps.get(k));
		}
		System.out.println("��ʼ���������ˡ�����");
	}
	
}
