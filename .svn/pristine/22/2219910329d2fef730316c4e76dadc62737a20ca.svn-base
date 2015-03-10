package com.ctgu.group.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctgu.dict.model.Dict;
import com.ctgu.dict.service.DictService;
import com.ctgu.group.model.Group;
import com.ctgu.group.service.GroupService;
import com.ctgu.util.BaseController;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.JsonUtil;
import com.ctgu.util.RO;


@Controller
@RequestMapping("/group")
public class GroupController extends BaseController{

	private FlipPageInfo<Group> fpi;
	
	@Resource(name = "GroupService")
	private GroupService groupService;
	
	@Resource(name = "DictService")
	private DictService dictService;
	
	@RequestMapping("/manager")
	public String manager(){
		return "group/manager";
	}
	
	@RequestMapping("/group-list")
	public String list(){
		return "group/list";
	}
	
	@RequestMapping("/json-list")
	public void jsonList(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute Group group,
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
			fpi = new FlipPageInfo<Group>(page, pageSize);
		
		
		fpi = groupService.list(fpi, group, sortName, sortOrder);
		
		
		out.println(JsonUtil.obj2json(fpi));
		out.flush();
		out.close();
	}
	
	@RequestMapping("/group-add")
	public String add(HttpServletRequest request){
		List<Group> list = groupService.listAll();
		List<Dict> dlist = dictService.getDictByType("GROUPTYPE");
		request.setAttribute("list", list);
		request.setAttribute("dlist", dlist);
		return "group/add"; 
	}
	
	@RequestMapping("/group-addsave")
	public String addsave(@ModelAttribute Group group,
			HttpServletRequest request){
		List<Group> list = groupService.listAll();
		List<Dict> dlist = dictService.getDictByType("GROUPTYPE");
		groupService.addsave(group);
		request.setAttribute("list", list);
		request.setAttribute("dlist", dlist);
		request.setAttribute("message", "添加成功！");
		return "group/add";
	}
	
	@RequestMapping("/group-edit/{id}")
	public String edit(@PathVariable("id") String id,HttpServletRequest request){
		List<Group> list = groupService.listAll();
		List<Dict> dlist = dictService.getDictByType("GROUPTYPE");
		Group group = groupService.findBYid(id);
		request.setAttribute("group", group);
		request.setAttribute("list", list);
		request.setAttribute("dlist", dlist);
		return "group/edit";
	}
	
	@RequestMapping("/group-editsave")
	public String editsave(@ModelAttribute Group group,
			HttpServletRequest request){
		ro = groupService.editsave(group);
		request.setAttribute("ro", ro);
		return "success";
	}
	
	@RequestMapping("/group-del")
	@ResponseBody
	public RO del(@RequestParam(value="ids" , required=true ) String ids){
		ro = groupService.del(ids);
		return ro;
	} 
	
	
}
