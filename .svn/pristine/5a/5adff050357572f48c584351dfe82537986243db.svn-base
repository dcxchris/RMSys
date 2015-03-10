package com.ctgu.util;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ctgu.user.model.User;


public class MyUserDetailService  implements UserDetailsService{
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+username);
		Session session = sessionFactory.getCurrentSession();
		String hql = "from User where uname = '"+username+"'";
		Query q = session.createQuery(hql);
		List l = q.list();
		if(l.size() == 0)
			throw new UsernameNotFoundException("username not found...");
		User u = (User) l.get(0);
		return (UserDetails)u;
	}

}
