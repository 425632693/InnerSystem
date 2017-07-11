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
 * 简单代码缓存
 * @author Administrator
 *
 */
@Component
public class SimpleCodeCache {
	
	@Resource(name="simpleCodeServiceImpl")
	private ISimpleCodeService simpleCodeService;

	/**
	 * 使用该注解spring会自动调用该方法来初始化 
	 */
	@PostConstruct
	public void init(){
		//获取查询到的所有结果集
		List<SimpleCode> result = simpleCodeService.queryAll();
		
		//声明一个 List<SimpleCode> 结果集的 Map 集合(根据类别进行分类)
		Map<String, List<SimpleCode>> maps = new HashMap<String, List<SimpleCode>>();
		
		//把结果集存放到缓存中
		for(SimpleCode item : result){
			//通过EhcacheUtils工具类进行存放数据到缓存文件里
			EhcacheUtils.getInstance().put("simpleCode", item.getCodeType(), item);
			System.out.println("存到缓存中的codeType是："+item.getCodeType());
			
			//分析当前简单代码的类别是什么？
			//根据获取的map集合中的得到的item.getCode()获取所有的codeType
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
		//根据类类别进行分类缓存
		//通过map集合的迭代器获取map里面存的key
		Iterator<String> keys = maps.keySet().iterator();
		while(keys.hasNext()){
			String k = keys.next();
			EhcacheUtils.getInstance().put("simpleCode", k, maps.get(k));
		}
		System.out.println("初始化调用我了。。。");
	}
	
}
