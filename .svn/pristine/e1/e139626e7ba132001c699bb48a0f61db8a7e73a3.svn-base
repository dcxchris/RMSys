package com.ctgu.role.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctgu.role.model.Auth;
import com.ctgu.role.model.Role;
import com.ctgu.role.service.RoleService;
import com.ctgu.user.model.User;
import com.ctgu.util.BaseController;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.JsonUtil;
import com.ctgu.util.RO;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	private FlipPageInfo<Role> fpi;
	
	@Resource(name = "RoleService")
	private RoleService roleService;
	
	
	@RequestMapping("/role-list")
	public String list(){
		return "role/list";
	}
	
	@RequestMapping("/json-list")
	public void jsonList(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute Role role,
			@RequestParam(value="sortName", required=false) String sortName,
			@RequestParam(value="sortOrder", required=false) String sortOrder,
			@RequestParam(value="page", required=false) Integer page,
		    @RequestParam(value="pageSize", required=false) Integer pageSize
			){
		
		response.setContentType("application/json"); 
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(page!=null && pageSize != null)
			fpi = new FlipPageInfo<Role>(page, pageSize);
		
		
		fpi = roleService.list(fpi, role, sortName, sortOrder);
		
		
		out.println(JsonUtil.obj2json(fpi));
		out.flush();
		out.close();
	}
	
	@RequestMapping("/role-addsave")
	@ResponseBody
	public RO addsave(@ModelAttribute Role role){
		ro = roleService.addsave(role);
		return ro;
	}
	
	@RequestMapping("/role-del")
	@ResponseBody
	public RO del(@RequestParam(value="ids" , required=true ) String ids){
		ro = roleService.del(ids);
		return ro;
	}
	
	@RequestMapping("/role-edit/{id}")
	public String edit(@PathVariable("id") String id,HttpServletRequest request){
		Role role = roleService.findBYid(id);
		List<String> list = new ArrayList();
		for(Auth a :role.getAuths()){
			list.add(a.getAuthority());
		}
		Map<String, List<Auth>> group = roleService.authgroups();
		request.setAttribute("group", group);
		request.setAttribute("alist", list);
		request.setAttribute("role", role);
		return "role/edit";
	}
	
	@RequestMapping("/role-editsave")
	public String editsave(@ModelAttribute Role role,
			@RequestParam(value="ids",required=true) String ids ,
			HttpServletRequest request){
		ro = roleService.editsave(role , ids);
		request.setAttribute("ro", ro);
		return "success";
	}
	
	
}
