package com.cuiboshi.utlis;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.cuiboshi.utlis.vo.Message;

/**
 * Message提示消息处理工具类
 * @author Administrator
 *
 */
public class MessageUtils {
	
	/**
	 * 增加一条提示消息
	 * @param message
	 */
	public static void addMessage(String message){
		//想获取消息到Request中
		//获取所有的错误信息放到Message 消息的封装类里
		List<Message> errors = (List<Message>) ServletActionContext.getRequest().getAttribute("errors");
		//判断获取的Message消息是否为null，如果为null，创建一个对象，否则的话就向集合中添加提示消息
		if(errors == null){
			errors = new ArrayList<Message>();
		}
		//从Message消息的封装类里的提示消息存放到errors对象中
		errors.add(new Message(message));
		
		//设置Message消息到Request当中
		ServletActionContext.getRequest().setAttribute("errors", errors);
		
	}

}
