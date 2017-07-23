package com.bms;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
