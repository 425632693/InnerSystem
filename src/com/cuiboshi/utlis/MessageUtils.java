package com.cuiboshi.utlis;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.cuiboshi.utlis.vo.Message;

/**
 * Message��ʾ��Ϣ��������
 * @author Administrator
 *
 */
public class MessageUtils {
	
	/**
	 * ����һ����ʾ��Ϣ
	 * @param message
	 */
	public static void addMessage(String message){
		//���ȡ��Ϣ��Request��
		//��ȡ���еĴ�����Ϣ�ŵ�Message ��Ϣ�ķ�װ����
		List<Message> errors = (List<Message>) ServletActionContext.getRequest().getAttribute("errors");
		//�жϻ�ȡ��Message��Ϣ�Ƿ�Ϊnull�����Ϊnull������һ�����󣬷���Ļ����򼯺��������ʾ��Ϣ
		if(errors == null){
			errors = new ArrayList<Message>();
		}
		//��Message��Ϣ�ķ�װ�������ʾ��Ϣ��ŵ�errors������
		errors.add(new Message(message));
		
		//����Message��Ϣ��Request����
		ServletActionContext.getRequest().setAttribute("errors", errors);
		
	}

}
