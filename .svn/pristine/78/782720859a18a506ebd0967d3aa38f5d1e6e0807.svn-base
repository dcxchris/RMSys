package com.ctgu.search.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ctgu.dict.model.Dict;
import com.ctgu.dict.service.DictService;
import com.ctgu.group.model.Group;
import com.ctgu.record.model.RecordSchema;
import com.ctgu.record.service.RecordService;
import com.ctgu.search.service.SearchService;
import com.ctgu.user.model.User;
import com.ctgu.util.BaseController;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.JsonUtil;

@Controller
@RequestMapping("/search")
public class SearchController extends BaseController{
	
	private FlipPageInfo<RecordSchema> fpi;
	
	@Resource(name = "SearchService")
	private SearchService searchService;
	
	@Resource(name = "RecordService")
	private RecordService recordService;
	
	@Resource(name = "DictService")
	private DictService dictService;
	
	
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		List<Group> glist = recordService.glist();
		List<Dict> nlist = dictService.getDictByType("NETTYPE");
		List<Dict> wlist = dictService.getDictByType("WORKTYPE");
		List<Dict> slist = dictService.getDictByType("STATUSFLAG");
		request.setAttribute("nlist", nlist);
		request.setAttribute("glist", glist);
		request.setAttribute("wlist", wlist);
		request.setAttribute("slist", slist);
		return "search/index";
	}
	
	@RequestMapping("/list_")
	public void list(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute RecordSchema recordschema,
			@RequestParam(value="time1", required=false) String time1,
			@RequestParam(value="time2", required=false) String time2,
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
			fpi = new FlipPageInfo<RecordSchema>(page, pageSize);
		
		fpi = searchService.list(request , fpi, recordschema, time1 , time2 , sortName, sortOrder);
		
		
		out.println(JsonUtil.obj2json(fpi));
		out.flush();
		out.close();
	}
	
	@RequestMapping("/export")
	public void export(HttpServletRequest request , HttpServletResponse response){
		searchService.export(request, response);
	}
	
	

}
