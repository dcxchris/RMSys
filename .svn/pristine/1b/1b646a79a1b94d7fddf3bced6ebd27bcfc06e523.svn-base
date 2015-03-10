package com.ctgu.group.service;

import java.util.List;

import com.ctgu.group.model.Group;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.RO;

public interface GroupService {
	
	public List<Group> listAll();
	
	public FlipPageInfo<Group> list(FlipPageInfo<Group> fpi, Group group,String sortName, String sortOrder);
	 
	public RO addsave(Group group);
	
	public RO editsave(Group group);
	
	public RO del(String ids);
	
	public Group findBYid(String id);

}
