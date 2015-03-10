package com.ctgu.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ctgu.user.model.User;
import com.ctgu.user.service.UserService;
import com.ctgu.util.EncryptUtil;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.PropertyUtil;
import com.ctgu.util.RO;
import com.ctgu.util.StringUtil;
import com.ctgu.dao.DAO;
@Component("UserService")
public class UserServiceImpl implements UserService{

	@Resource(name = "DAO")
	private DAO dao;
	
	@Override
	public FlipPageInfo<User> list(FlipPageInfo<User> fpi, User user,
			String sortName, String sortOrder) {
		StringBuilder hql = new StringBuilder("from User u where 1=1 and u.id != 1");
		List<Object> param = new ArrayList<Object>();
		
		if(user != null ){
 			if(StringUtil.isNotEmpty(user.getUsername()))
 			{
 				hql.append(" and u.username like ?");
 				param.add("%"+user.getUsername()+"%");
 			}
 		}
		
		
		if (StringUtil.isNotEmpty(sortName) && StringUtil.isNotEmpty(sortOrder)) {
			sortOrder = "desc".equalsIgnoreCase(sortOrder) ? "desc" : "asc";
			hql.append(" order by u." + sortName + " " + sortOrder);
		} else {
			hql.append(" order by u.id desc");
		}
		
		FlipPageInfo<User> value = dao.findWithPaging(fpi, hql.toString(), param);

		return value;
	}

	@Override
	public RO addsave(User user) {
		RO ro = new RO();
		String key = PropertyUtil.getProperty("key");
		user.setPassword(EncryptUtil.byte2hex(EncryptUtil.desEncrypt(user.getPassword(),key)));//密码要经过加密
		dao.save(user);
		ro.setUrl("/user/user-list.do");
		return ro;
	}

	@Override
	public RO editsave(User user) {
		RO ro = new RO();
		String key = PropertyUtil.getProperty("key");
		user.setPassword(EncryptUtil.byte2hex(EncryptUtil.desEncrypt(user.getPassword(),key)));//密码要经过加密
		dao.merge(user);
		ro.setUrl("/user/user-list.do");
		return ro;
	}

	@Override
	public RO editsavepw(User user, String pw) {
		String key = PropertyUtil.getProperty("key");
		dao.excuteBySql(new String[]{"update db_user set password = '"+EncryptUtil.byte2hex(EncryptUtil.desEncrypt(user.getPassword(),key))+"' "});
		return null;
	}

	@Override
	public RO del(String ids) {
		RO ro = new RO();
		String arr[] = ids.split(",");
		for(String id :arr){
			dao.excuteBySql(new String[]{"delete from db_user where id = "+id});
		}
		ro.setInfo("删除成功!");
		return ro;
	}

	@Override
	public User findBYid(String id) {
		StringBuilder hql = new StringBuilder("from User where id = "+id);
		return dao.findFirst(hql.toString());
	}

}
