package com.ctgu.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctgu.group.model.Group;
import com.ctgu.group.service.GroupService;
import com.ctgu.role.model.Role;
import com.ctgu.role.service.RoleService;
import com.ctgu.user.model.User;
import com.ctgu.user.service.UserService;
import com.ctgu.util.BaseController;
import com.ctgu.util.EncryptUtil;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.JsonUtil;
import com.ctgu.util.PropertyUtil;
import com.ctgu.util.RO;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	private FlipPageInfo<User> fpi;
	
	@Resource(name = "UserService")
	private UserService userService; 
	
	@Resource(name = "GroupService")
	private GroupService gruopService;
	
	@Resource(name = "RoleService")
	private RoleService roleService;

	
	@RequestMapping("/manager")
	public String manager(){
		return "user/manager";
	}
	
	@RequestMapping("/user-list")
	public String list(){
		return "user/list";
	}
	
	@RequestMapping("/json-list")
	public void jsonList(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute User user,
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
			fpi = new FlipPageInfo<User>(page, pageSize);
		
		
		fpi = userService.list(fpi, user, sortName, sortOrder);
		
		
		out.println(JsonUtil.obj2json(fpi));
		out.flush();
		out.close();
	}
	
	@RequestMapping("/user-add")
	public String add(HttpServletRequest request){
		List<Group> glist = gruopService.listAll(); 
		List<Role> rlist = roleService.listAll();
		request.setAttribute("glist", glist);
		request.setAttribute("rlist", rlist);
		return "user/add";
	}
	
	@RequestMapping("/user-addsave")
	  
	public String addsave(@ModelAttribute User user,HttpServletRequest request){
		ro = userService.addsave(user);
		request.setAttribute("ro", ro);
		return "success";
	}
	
	@RequestMapping("/user-edit/{id}")
	public String edit(@PathVariable("id") String id,HttpServletRequest request){
		User user = userService.findBYid(id);
		String key = PropertyUtil.getProperty("key");
		String pw = (EncryptUtil.desDecrypt(user.getPassword(),key));
		List<Group> glist = gruopService.listAll(); 
		List<Role> rlist = roleService.listAll();
		request.setAttribute("user", user);
		request.setAttribute("glist", glist);
		request.setAttribute("rlist", rlist);
		request.setAttribute("pw", pw);
		return "user/edit";
	}
	
	@RequestMapping("/user-editsave")
	public String editsave(@ModelAttribute User user,HttpServletRequest request){
		ro = userService.editsave(user);
		request.setAttribute("ro", ro);
		return "success";
	}
	
	@RequestMapping("/user-del")
	@ResponseBody
	public RO del(@RequestParam(value="ids" , required=true ) String ids){
		ro = userService.del(ids);
		return ro;
	}
	
	@RequestMapping("/editsavepw_")
	public String editsavePW(HttpSession session , HttpServletRequest request){
		User user = (User) session.getAttribute("loginUser");
		String pw = request.getParameter("password");
		userService.editsavepw(user, pw);
		return "success";
	}
	
	@RequestMapping("/editpw_")
	public String editPW(){
		return "user/editpw";
	}
	
}
