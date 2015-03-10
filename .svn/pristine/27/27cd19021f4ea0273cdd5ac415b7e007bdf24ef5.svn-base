package com.ctgu.login.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ctgu.login.service.LoginService;
import com.ctgu.user.model.User;
import com.ctgu.util.StringUtil;
import com.ctgu.dao.DAO;

@Component("LoginService")
public class LoginServiceImpl implements LoginService , UserDetailsService{

	@Resource(name = "DAO")
	private DAO dao;
	
	@Override
	public User findByUsernamePw(User user) {
		StringBuilder hql = new StringBuilder("from User u where 1=1 ");
		List<Object> param = new ArrayList<Object>();
		if(StringUtil.isNotEmpty(user.getUsername())){
 			hql.append(" and u.username = ?");
 			param.add(user.getUsername());
 		}
		
		if(StringUtil.isNotEmpty(user.getPassword())){
 			hql.append(" and u.password = ?");
 			param.add(user.getPassword());
 		}
 		
		
		
		return dao.findFirst(hql.toString(), param);
	}

	@Override
	public UserDetails getUserDetails(String username) {
		return loadUserByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		String hql = "from User where username = '" + username + "'";
        User user = dao.findFirst(hql);
        return (UserDetails) user;
	}

}
