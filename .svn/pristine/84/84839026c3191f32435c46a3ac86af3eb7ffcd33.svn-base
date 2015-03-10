package com.ctgu.login.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ctgu.login.service.LoginService;
import com.ctgu.notice.model.Notice;
import com.ctgu.notice.service.NoticeService;
import com.ctgu.record.model.Record;
import com.ctgu.record.service.RecordService;
import com.ctgu.role.model.Auth;
import com.ctgu.user.model.User;
import com.ctgu.util.EncryptUtil;
import com.ctgu.util.MySessionListener;
import com.ctgu.util.PropertyUtil;
import com.ctgu.util.StringUtil;

@Controller
public class LoginController {

	
	@Resource(name = "LoginService")
	private LoginService loginService;
	
	@Resource(name="NoticeService")
	private NoticeService noticeService;
	
	@Resource(name = "RecordService")
	private RecordService recordService;
	
	
	@RequestMapping("/login")
	public String login(@ModelAttribute User user,ModelMap modelMap, HttpServletRequest request){
		String info = null;
		User u = new User();
		String key = PropertyUtil.getProperty("key");
		if(user==null||StringUtil.isEmpty(user.getUsername())){
			if(request.getSession()==null||request.getSession().getAttribute("loginUser")==null){
				return "forward:/login.jsp";
			}
			else{
				user = (User) request.getSession().getAttribute("loginUser");
				
			}
			
		}else
			user.setPassword(EncryptUtil.byte2hex(EncryptUtil.desEncrypt(user.getPassword(),key)));
		
		u.setUsername(user.getUsername());
		u = loginService.findByUsernamePw(u);
		if(u == null){
			info ="1";
			modelMap.put("info", info);
			return "forward:/login.jsp";
		}else if(!u.getPassword().equals(user.getPassword())){
			info = "2";
			modelMap.put("info", info);
			return "forward:/login.jsp";
		}
		
		
		UserDetails userDetails = loginService.getUserDetails(user.getUsername());
		Authentication authentication = new UsernamePasswordAuthenticationToken(    
		        userDetails, userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContext securityContext = SecurityContextHolder.getContext();  
		securityContext.setAuthentication(authentication);  
		
		List<String> ulist = new ArrayList<String>();
		
		if(u.getRole()!=null){
			for(Auth a : u.getRole().getAuths()){
				ulist.add(a.getUrl());
			}
		}
		
		request.getSession().setAttribute("urllist", ulist);
		request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		request.getSession().setAttribute("loginUser", u);
		MySessionListener.SessionMap.put(request.getSession().getId(), u);
		return "main";
				
	
	}
	
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/login.jsp";
	}
	
	@RequestMapping("/portlet_")
	public String portlet(HttpServletRequest request){
		User u = (User) request.getSession().getAttribute("loginUser");
		List<Record> rlist = recordService.task(u, 5);
		List<Notice> list = noticeService.list(3);
		request.setAttribute("rlist", rlist);
		request.setAttribute("list", list);
		request.setAttribute("alluser", MySessionListener.SessionMap);
		
		return "portlet";
	}
	
	
}
