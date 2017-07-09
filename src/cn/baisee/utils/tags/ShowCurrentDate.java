package cn.baisee.utils.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.PageContext;


public class ShowCurrentDate extends SimpleTagSupport {

	private String formatType;//���ڸ�ʽ��ʽ��
	private Date date;
	
	@Override
	public void doTag() throws JspException, IOException {
		if(date==null){
			//��ǰʱ��
			date = new Date();
		}
		if(formatType == null){
			formatType = "yyyy-MM-dd hh:mm:ss SSS";
		}
		//ת�����ڸ�ʽ
		SimpleDateFormat format = new SimpleDateFormat(formatType);
		String result = format.format(date);
		PageContext pc = (PageContext) getJspContext();
		JspWriter out = pc.getOut();
		out.write(result.toString());
	}

	public String getFormatType() {
		return formatType;
	}
	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
