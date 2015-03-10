package com.ctgu.util;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class mySecurityFilter implements Filter {
	
	private static final  String  loginURL = "/login.do";
	private static final  String  logoutURL = "/logout.do";
	private static final  String  successURL = "/success.do";
	

	public void init(FilterConfig filterConfig)throws ServletException{
	}
	
	
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws ServletException,IOException{  
         HttpServletRequest req = (HttpServletRequest)request;   
         HttpServletResponse res = (HttpServletResponse)response;   
         HttpSession session= req.getSession();
         String path = req.getContextPath();
         String basePath = req.getScheme()+"://"+req.getServerName()+path;
         //String basePath = req.getScheme()+"://"+req.getServerName()+":8080"+path;
         String  serviceURL = basePath;
         //截取URL
         String url = req.getRequestURL().toString().replaceAll(serviceURL, "");
        
         System.out.println(url);
         //check   attribute 
         if(req.getParameter("password")==null&&(session == null || session.getAttribute("loginUser")==null || session.isNew())){   
             req.getSession().getServletContext().getRequestDispatcher("/login.jsp").forward(req,res);//页面前的'/'一定不能省略 
         }else{
        	 //开始验证URL
        	 List<String> list = (List<String>) session.getAttribute("urllist");
        	 if(check(url,list))
        		 chain.doFilter(request,response);
        	 else
        		 req.getSession().getServletContext().getRequestDispatcher("/common/error/403.jsp").forward(req,res);//页面前的'/'一定不能省略  
         }
        
	} 
	
	//验证的方法
		public boolean check(String url, List<String> list){
			//把url中含有参数的部分去掉
			int firstQuestionMarkIndex = url.indexOf("?");

	        if (firstQuestionMarkIndex != -1) {
	            url = url.substring(0, firstQuestionMarkIndex);
	        }
	        
	       if(url.indexOf("_") != -1)
	    	   return true;
	        
	        String regEx1=".-add.do$";
	        Pattern pat1 = Pattern.compile(regEx1);
	        Matcher mat1 = pat1.matcher(url);  
	        
	        String regEx2=".-edit\\/\\d+\\.do$";
	        Pattern pat2 = Pattern.compile(regEx2);
	        Matcher mat2 = pat2.matcher(url);   
	        
	        String regEx3=".IsExist.do$";
	        Pattern pat3 = Pattern.compile(regEx3);
	        Matcher mat3 = pat3.matcher(url);
	        
	        String regEx4=".json-list.do$";
	        Pattern pat4 = Pattern.compile(regEx4);
	        Matcher mat4 = pat4.matcher(url);
	        
	        if(url.equals(loginURL) || url.equals(logoutURL) || url.equals(successURL) || mat1.find() || mat2.find() || mat3.find() || mat4.find() )
	        	return true;

	        if(list.contains(url))
	        	return true;

	        
	        return false;
		}
	
	public void destroy(){
 
	}   
}