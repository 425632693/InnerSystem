package com.cuiboshi.utlis;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


/**
 * 缓存的工具类
 * 单例模式
 * @author Administrator
 *
 */
public class EhcacheUtils {
	//缓存管理器
	private CacheManager cm;  
	//缓存工具类
    private static EhcacheUtils ehCache;  
    //构造函数
	private EhcacheUtils(){
		//加载缓存配置文件（Ehcache管理）
		cm = CacheManager.create(
				EhcacheUtils.class.getClassLoader().getResource("ehcache.xml"));
	}
	
	//创建当前单例的对象
	public static EhcacheUtils getInstance() {  
        if (ehCache== null) {  
            ehCache= new EhcacheUtils();  
        }  
        return ehCache;  
    }  
  
	/**
	 * 存放一个缓存数据
	 * @param cacheName 缓存名称
	 * @param key	缓存的键
	 * @param value	缓存的值
	 */
    public void put(String cacheName, String key, Object value) {  
        Cache cache = cm.getCache(cacheName);  
        Element element = new Element(key, value);  
        cache.put(element);  
    }  
  
    /**
     * 获取一个缓存数据
     * @param cacheName 缓存的名称
     * @param key	缓存的键
     * @return
     */
    public Object get(String cacheName, String key) {  
        Cache cache = cm.getCache(cacheName);  
        Element element = cache.get(key);  
        return element == null ? null : element.getObjectValue();  
    }  
  
    /**
     * 获取一个缓存
     * @param cacheName
     * @return
     */
    public Cache get(String cacheName) {  
        return cm.getCache(cacheName);  
    }  
  
    /**
     * 删除一个缓存的值
     * @param cacheName
     * @param key
     */
    public void remove(String cacheName, String key) {  
        Cache cache = cm.getCache(cacheName);  
        cache.remove(key);  
    }  
    
    /**
     * 清空当前缓存
     * @param cacheName
     */
    public void clear(String cacheName) {  
        Cache cache = cm.getCache(cacheName);  
        cache.removeAll(); 
    }  

}
