package com.ctgu.util;

import java.util.HashMap;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ctgu.user.model.User;

public class MySessionListener implements HttpSessionListener,HttpSessionAttributeListener{

	public static HashMap<String , User> SessionMap = new HashMap<String , User>();//保存sessionID和username的映射
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		User u = SessionMap.get(arg0.getSession().getId());
		System.out.println(u.getUsername()+" session destroy ");
		SessionMap.remove(arg0.getSession().getId());
		
	}

	
}
