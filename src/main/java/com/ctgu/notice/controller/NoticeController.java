package com.ctgu.notice.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctgu.notice.model.Notice;
import com.ctgu.notice.service.NoticeService;
import com.ctgu.util.BaseController;
import com.ctgu.util.DateUtil;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.JsonUtil;
import com.ctgu.util.RO;

@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

	private FlipPageInfo<Notice> fpi;
	
	@Resource(name = "NoticeService")
	private NoticeService noticeService;
	
	@RequestMapping("/manager")
	public String manager(){
		return "notice/manager";
	}
	
	@RequestMapping("/notice-list")
	public String index(){
		return "notice/list";
	}
	
	@RequestMapping("/json-list")
	public void jsonList(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute Notice notice,
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
			fpi = new FlipPageInfo<Notice>(page, pageSize);
		
		fpi = noticeService.list(fpi, notice, sortName, sortOrder);
		
		out.println(JsonUtil.obj2json(fpi));
		out.flush();
		out.close();
	}
	
	@RequestMapping("/notice-add")
	public String add(){
		return "notice/add";
	}
	
	@RequestMapping("/notice-addsave")
	public String addsave(@ModelAttribute Notice notice,HttpServletRequest request){
		notice.setCreateTime(DateUtil.nowString("yyyy-MM-dd HH:mm:ss"));
		ro = noticeService.addsave(notice);
		request.setAttribute("ro", ro);
		return "success";
	}
	
	@RequestMapping("/notice-edit/{id}")
	public String edit(@PathVariable("id") String id,HttpServletRequest request){
		Notice notice = noticeService.findBYid(id);
		request.setAttribute("notice", notice);
		return "notice/edit";
	}
	
	@RequestMapping("/notice-info/{id}_")
	public String info(@PathVariable("id") String id,HttpServletRequest request){
		Notice notice = noticeService.findBYid(id);
		request.setAttribute("notice", notice);
		return "notice/info";
	}
	
	@RequestMapping("/notice-editsave")
	public String editsave(@ModelAttribute Notice notice,HttpServletRequest request){
		ro = noticeService.editsave(notice);
		request.setAttribute("ro", ro);
		return "success";
	}
	
	@RequestMapping("/notice-del")
	@ResponseBody
	public RO del(@RequestParam(value="ids" , required=true ) String ids){
		ro = noticeService.del(ids);
		return ro;
	}
	
}
