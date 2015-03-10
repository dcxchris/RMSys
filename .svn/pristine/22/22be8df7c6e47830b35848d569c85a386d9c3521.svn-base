package com.ctgu.user.service;

import com.ctgu.user.model.User;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.RO;

public interface UserService {

	public FlipPageInfo<User> list(FlipPageInfo<User> fpi, User user,String sortName, String sortOrder);
 
	public RO addsave(User user);
	
	public RO editsave(User user);
	
	public RO editsavepw(User user , String pw);
	
	public RO del(String ids);
	
	public User findBYid(String id);
}
