package com.cuiboshi.utlis.vo;
/**
 * Message 消息的封装类
 * @author Administrator
 *
 */
public class Message {
	//提示消息的内容
	private String context;

	public Message() {}


	public Message(String context) {
		super();
		this.context = context;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	
	
}
