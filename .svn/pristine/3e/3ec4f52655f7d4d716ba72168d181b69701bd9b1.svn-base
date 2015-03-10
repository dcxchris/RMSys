package com.ctgu.group.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ctgu.dao.DAO;
import com.ctgu.group.model.Group;
import com.ctgu.group.service.GroupService;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.RO;
import com.ctgu.util.StringUtil;

@Component("GroupService")
public class GroupServiceImpl implements GroupService{

	@Resource(name = "DAO")
	private DAO dao;
	
	@Override
	public List<Group> listAll() {
		StringBuilder hql = new StringBuilder("from Group ");
		
		return dao.find(hql.toString());
	}

	@Override
	public FlipPageInfo<Group> list(FlipPageInfo<Group> fpi, Group group,
			String sortName, String sortOrder) {
		StringBuilder hql = new StringBuilder("from Group u where 1=1 ");
		
		if (StringUtil.isNotEmpty(sortName) && StringUtil.isNotEmpty(sortOrder)) {
			sortOrder = "desc".equalsIgnoreCase(sortOrder) ? "desc" : "asc";
			hql.append(" order by u." + sortName + " " + sortOrder);
		} else {
			hql.append(" order by u.id asc");
		}
		
		return dao.findWithPaging(fpi, hql.toString());
	}

	@Override
	public RO addsave(Group group) {
		RO ro = new RO();
		dao.save(group);
		return ro;
	}

	@Override
	public RO editsave(Group group) {
		RO ro = new RO();
		dao.merge(group);
		ro.setUrl("/group/group-list.do");
		return ro;
	}

	@Override
	public RO del(String ids) {
		RO ro = new RO();
		String arr[] = ids.split(",");
		for(String id :arr){
			dao.excuteBySql(new String[]{"delete from db_group where id = "+id});
		}
		ro.setInfo("删除成功!");
		return ro;
	}

	@Override
	public Group findBYid(String id) {
		StringBuilder hql = new StringBuilder(" from Group where id =  "+id);
		return dao.findFirst(hql.toString()); 
	}

}
