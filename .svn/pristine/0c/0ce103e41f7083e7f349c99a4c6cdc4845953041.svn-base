package com.ctgu.role.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ctgu.dao.DAO;
import com.ctgu.role.model.Auth;
import com.ctgu.role.model.Role;
import com.ctgu.role.service.RoleService;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.RO;
import com.ctgu.util.StringUtil;

@Component("RoleService")
public class RoleServiceImpl implements RoleService{
	
	@Resource(name = "DAO")
	private DAO dao;

	@Override
	public List<Role> listAll() {
		StringBuilder hql = new StringBuilder("from Role ");
		return dao.find(hql.toString());
	}

	@Override
	public FlipPageInfo<Role> list(FlipPageInfo<Role> fpi, Role role,
			String sortName, String sortOrder) {
		StringBuilder hql = new StringBuilder("from Role u where 1=1 ");
		
		if(StringUtil.isNotEmpty(role.getRoleName())){
			hql.append(" and u.roleName like '%"+role.getRoleName()+"%' ");
		}
		
		if (StringUtil.isNotEmpty(sortName) && StringUtil.isNotEmpty(sortOrder)) {
			sortOrder = "desc".equalsIgnoreCase(sortOrder) ? "desc" : "asc";
			hql.append(" order by u." + sortName + " " + sortOrder);
		} else {
			hql.append(" order by u.id desc");
		}
		
		return dao.findWithPaging(fpi, hql.toString());
	}

	@Override
	public RO addsave(Role role) {
		RO ro = new RO();
		dao.save(role);
		ro.setInfo("创建成功！");
		return ro;
	}

	@Override
	public RO editsave(Role role , String ids) {
		RO ro = new RO();
		if(StringUtil.isNotEmpty(ids))
			ids = ids.substring(0, ids.length()-1);
		String arr[] = ids.split(",");
		dao.excuteBySql(new String[]{"delete from db_role_auth where roleid = "+role.getId()});
		dao.excuteBySql(new String[]{" update db_role set roleName = '"+role.getRoleName()+"' where id =  "+role.getId()});
		for(String id :arr){
			dao.excuteBySql(new String[]{"insert into db_role_auth (roleid,authid) values("+role.getId()+","+id+")"});
		}
		ro.setUrl("/role/role-list.do");
		return ro;
	}

	@Override
	public RO del(String ids) {
		RO ro = new RO();
		String arr[] = ids.split(",");
		for(String id :arr){
			dao.excuteBySql(new String[]{"update db_user set roleid = null where roleid = "+id});
			dao.excuteBySql(new String[]{"delete from db_role_auth where roleid = "+id});
			dao.excuteBySql(new String[]{"delete from db_role where id = "+id});
		}
		ro.setInfo("删除成功!");
		return ro;
	}

	@Override
	public Role findBYid(String id) {
		StringBuilder hql = new StringBuilder("from Role where id = "+id);
		return dao.findFirst(hql.toString());
	}
	
	@Override
	public Map<String, List<Auth>> authgroups() {
		
		Map<String, List<Auth>> group = new TreeMap<String, List<Auth>>();
		List<Object[]> alist = dao.find("select authority,name from Auth  where LENGTH(authority)=7 order by authority asc ");
		
		for(Object[] a : alist){
			List<Auth> list = dao.find("from Auth where authority like '"+a[0]+"%' ");
			group.put((String)a[1], list);
		}
		System.out.println(group.keySet().size()+"######");
		return group;
	}

}
