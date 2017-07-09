package com.cuiboshi.utlis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.servlet.http.HttpSession;

public class ImageUtils {

	/**
	 * 创建动态认证码
	 * @param session 存放session对象
	 * @param sessionKey 存放session对象key
	 * @return 返回图片对象
	 */
	public static BufferedImage createCode(HttpSession session,String sessionKey){
		//在内存中创建图象  
	    int width = 60;  
	    int height = 20;  
	    BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);  
	    //创建图象  
	    Graphics g = image.getGraphics();  
	    //生成随机对象  
	    Random random = new Random();  
	    //设置背景色  
	    g.setColor(Color.GRAY);  
	    g.fillRect(0,0,width,height);  
	    //设置字体  
	    g.setFont(new Font("宋体",Font.PLAIN,18));  
	    //随机产生认证码,4位数字  
	    String sRand = "";  
	    for(int i = 0; i < 4; i++){  
	        String rand = String.valueOf(random.nextInt(10));  
	        sRand  += rand;  
	        //将认证码显示到图象中  
	        g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));  
	        g.drawString(rand,13*i+6,16);  
	    }  
	    session.setAttribute(sessionKey,sRand);  
	    //图像生效  
	    g.dispose();
	    return image;
	}
	
}
