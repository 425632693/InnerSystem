package com.cuiboshi.utlis;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


/**
 * ����Ĺ�����
 * ����ģʽ
 * @author Administrator
 *
 */
public class EhcacheUtils {
	//���������
	private CacheManager cm;  
	//���湤����
    private static EhcacheUtils ehCache;  
    //���캯��
	private EhcacheUtils(){
		//���ػ��������ļ���Ehcache����
		cm = CacheManager.create(
				EhcacheUtils.class.getClassLoader().getResource("ehcache.xml"));
	}
	
	//������ǰ�����Ķ���
	public static EhcacheUtils getInstance() {  
        if (ehCache== null) {  
            ehCache= new EhcacheUtils();  
        }  
        return ehCache;  
    }  
  
	/**
	 * ���һ����������
	 * @param cacheName ��������
	 * @param key	����ļ�
	 * @param value	�����ֵ
	 */
    public void put(String cacheName, String key, Object value) {  
        Cache cache = cm.getCache(cacheName);  
        Element element = new Element(key, value);  
        cache.put(element);  
    }  
  
    /**
     * ��ȡһ����������
     * @param cacheName ���������
     * @param key	����ļ�
     * @return
     */
    public Object get(String cacheName, String key) {  
        Cache cache = cm.getCache(cacheName);  
        Element element = cache.get(key);  
        return element == null ? null : element.getObjectValue();  
    }  
  
    /**
     * ��ȡһ������
     * @param cacheName
     * @return
     */
    public Cache get(String cacheName) {  
        return cm.getCache(cacheName);  
    }  
  
    /**
     * ɾ��һ�������ֵ
     * @param cacheName
     * @param key
     */
    public void remove(String cacheName, String key) {  
        Cache cache = cm.getCache(cacheName);  
        cache.remove(key);  
    }  
    
    /**
     * ��յ�ǰ����
     * @param cacheName
     */
    public void clear(String cacheName) {  
        Cache cache = cm.getCache(cacheName);  
        cache.removeAll(); 
    }  

}
