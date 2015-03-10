package com.ctgu.role.service;

import java.util.List;
import java.util.Map;

import com.ctgu.role.model.Auth;
import com.ctgu.role.model.Role;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.RO;

public interface RoleService {

	public List<Role> listAll();
	
	public FlipPageInfo<Role> list(FlipPageInfo<Role> fpi, Role role,String sortName, String sortOrder);
	 
	public RO addsave(Role role);
	
	public RO editsave(Role role,String ids);
	
	public RO del(String ids);
	
	public Role findBYid(String id); 
	
	public Map<String ,List<Auth>> authgroups();
}
