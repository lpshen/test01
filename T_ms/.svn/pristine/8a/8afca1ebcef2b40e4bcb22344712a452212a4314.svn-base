package com.bms.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @Project：mybatisForSpring3   
 * @ClassName：ApplicationContextFactory   
 * @Description：TODO  
 * @author：lixiu   
 * @CreateTime：Jul 30, 2011 1:55:32 PM   
 * @Modifier：lixiu  
 * @ChangeTime：Jul 30, 2011 1:55:32 PM   
 * @Remark：TODO 
 * @version 1.0
 */
public class ApplicationContextFactory 
{
	private static ApplicationContext context = null;
	
	
	private ApplicationContextFactory()
	{
		super();
	}
	
	public static void init()
	{   
		String [] contextFileNames = new String [1];
		contextFileNames[0] =  "applicationContext-common.xml";
		context = new ClassPathXmlApplicationContext(contextFileNames);
	}
	
	public static ApplicationContext getInstance()
	{
		if (null == context)
		{
			init();
		}
		return context;
	}
	
	
}
