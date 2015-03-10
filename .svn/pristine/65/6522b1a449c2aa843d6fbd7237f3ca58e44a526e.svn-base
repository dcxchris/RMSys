package com.ctgu.record.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ctgu.dict.model.Dict;
import com.ctgu.dict.service.DictService;
import com.ctgu.group.model.Group;
import com.ctgu.record.model.Record;
import com.ctgu.record.model.RecordSchema;
import com.ctgu.record.service.RecordService;
import com.ctgu.user.model.User;
import com.ctgu.util.BaseController;
import com.ctgu.util.DateUtil;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.JsonUtil;
import com.ctgu.util.PropertyUtil;
import com.ctgu.util.RO;

@Controller
@RequestMapping("/record")
public class RecordController extends BaseController{
	
	private FlipPageInfo<RecordSchema> fpi;
	
	@Resource(name = "RecordService")
	private RecordService recordService;
	
	@Resource(name = "DictService")
	private DictService dictService;
	
	
	@RequestMapping("/manager")
	public String manager(@RequestParam(value = "tab",required=false) String tab,HttpServletRequest request ){
		request.setAttribute("tab", tab);
		return "record/manager";
	}
	
	/*********** 退费申请  **********/
	@RequestMapping("/apply")
	public String apply(HttpServletRequest request){
		List<Group> glist = recordService.glist();
		List<Dict> wlist = dictService.getDictByType("WORKTYPE");
		request.setAttribute("recordNum", DateUtil.nowString("yyyyMMddHHmmss"));
		request.setAttribute("glist", glist);
		request.setAttribute("wlist", wlist);
		return "record/apply";
	}
	
	
	/*********** 退费申请保存  **********/
	@RequestMapping("/apply-addsave_")
	public String applyAddSave(@ModelAttribute Record record,
			@RequestParam(value="addtels") String addtels,
			@RequestParam(value="remark") String remark,
			HttpServletRequest request
			){
		User user = (User) request.getSession().getAttribute("loginUser");
		record.setCreatetime(DateUtil.nowString("yyyy-MM-dd HH:mm:ss"));
		record.setCreateUserId(user.getId());
		ro = recordService.addsave(record , addtels , remark);
		request.setAttribute("ro", ro);
		return "success";
	}
	
	
	/*********** 退费申请附件上传  **********/
	@ResponseBody
	@RequestMapping("/fileUpLoad_")
	public RO fileUpLoad(@RequestParam(value = "file") MultipartFile file , HttpServletRequest request){
		String fileUploadPath = PropertyUtil.getProperty("uploadPath");
		
    	
    	String fileName = DateUtil.nowString("yyyyMMddHHmmss_")+file.getOriginalFilename();  
    	
    	System.out.println(fileName);
    	
    	
    	File targetFile = new File(request.getRealPath(fileUploadPath), fileName);
    	if(!targetFile.exists()) {  
            targetFile.mkdirs();  
        }
    	// 保存
    	try {
			file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	ro = new RO();
    	ro.setInfo(fileName);
    	return ro;
	}
	
	/*********** 退费申请查重  **********/
	@ResponseBody
	@RequestMapping("/checkPhoneNum_")
	public RO checkPhoneNum(@RequestParam(value = "phoneNum") String phoneNum , HttpServletRequest request){
		ro = new RO<List<RecordSchema>>();
		List<RecordSchema> list = recordService.check(phoneNum);
		if(list.size()!=0){
			ro.setCode("1");//进入查重区
			ro.setInfo("该电话号码近两个月内有重复提交！");
			ro.setObj(list);
		}
		else
			ro.setCode("0");//待审核
		return ro;
	}
		
	/*********** 查重区  **********/
	@RequestMapping("/checkrepeat-list")
	public String checkrepeatlist(HttpServletRequest request){
		List<Group> glist = recordService.glist();
		List<Dict> nlist = dictService.getDictByType("NETTYPE");
		List<Dict> wlist = dictService.getDictByType("WORKTYPE");
		request.setAttribute("nlist", nlist);
		request.setAttribute("glist", glist);
		request.setAttribute("wlist", wlist);
		return "record/checkrepeat-list";
	}
	
	@RequestMapping("/checkrepeat-list-json_")
	public void checkrepeatlistjson(HttpServletRequest request,HttpServletResponse response,
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
		
		
		fpi = recordService.checkrepeatlist(fpi, recordschema, time1 , time2 , sortName, sortOrder);
		
		
		out.println(JsonUtil.obj2json(fpi));
		out.flush();
		out.close();
	}
	
	
	/*********** 稽核  **********/
	@ResponseBody
	@RequestMapping("/confirm-money")
	public RO confirmMoney(@RequestParam(value="ids", required=false) String ids,
			@RequestParam(value="confirmMoney", required=false) String confirmMoney,
			@RequestParam(value="remark1", required=false) String remark1,
			HttpServletRequest request){
		User u = (User) request.getSession().getAttribute("loginUser");
		ro = recordService.confirmMoney(ids, confirmMoney, remark1 , u.getId());
		return ro;
	}
	
	
	/*********** 批量稽核  **********/
	@ResponseBody
	@RequestMapping("/confirm-money-batch_")
	public RO confirmMoneyBatch(@RequestParam(value="ids", required=false) String ids,
			@RequestParam(value="flag", required=false) String flag,
			HttpServletRequest request){
		User u = (User) request.getSession().getAttribute("loginUser");
		ro = recordService.confirmMoneyBatch(ids, u.getId(),flag);
		return ro;
	}
	
	
	/*********** 审核 **********/
	@RequestMapping("/shenhe-list")
	public String shenhelist(HttpServletRequest request){
		List<Group> glist = recordService.glist();
		List<Dict> nlist = dictService.getDictByType("NETTYPE");
		List<Dict> wlist = dictService.getDictByType("WORKTYPE");
		request.setAttribute("nlist", nlist);
		request.setAttribute("glist", glist);
		request.setAttribute("wlist", wlist);
		return "record/shenhe-list";
	}
	
	@RequestMapping("/shenhe-list-json_")
	public void shenhelistjson(HttpServletRequest request,HttpServletResponse response,
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
		
		User u = (User) request.getSession().getAttribute("loginUser");
		recordschema.setGroupId(u.getGroup().getId());
		fpi = recordService.shenhelist(fpi, recordschema, time1 , time2 , sortName, sortOrder);
		
		
		out.println(JsonUtil.obj2json(fpi));
		out.flush();
		out.close();
	}
	
	/*********** 审核  **********/
	@ResponseBody
	@RequestMapping("/confirm-shenhe-money")
	public RO confirmshenheMoney(@RequestParam(value="ids", required=false) String ids,
			@RequestParam(value="confirmMoney", required=false) String confirmMoney,
			@RequestParam(value="remark2", required=false) String remark2,
			HttpServletRequest request){
		User u = (User) request.getSession().getAttribute("loginUser");
		ro = recordService.confirmshenheMoney(ids, confirmMoney, remark2 , u.getId());
		return ro;
	}
	
	
	/*********** 批量审核 **********/
	@ResponseBody
	@RequestMapping("/confirm-shenhe-money-batch_")
	public RO confirmshenheMoneyBatch(@RequestParam(value="ids", required=false) String ids,
			@RequestParam(value="flag", required=false) String flag,
			HttpServletRequest request){
		User u = (User) request.getSession().getAttribute("loginUser");
		ro = recordService.confirmshenheMoneyBatch(ids, u.getId(),flag);
		return ro;
	}
	
	
	/*********** 保存修改 **********/
	@ResponseBody
	@RequestMapping("/editsave_")
	public RO editsave(@ModelAttribute Record record ){
		ro = recordService.editsave(record);
		return ro;
	}
	
	
	/*********** 转发稽核 **********/
	@ResponseBody
	@RequestMapping("/turnback-record_")
	public RO turnBackRecord(@RequestParam(value="ids", required=false) String ids){
		ro = recordService.turnBackRecord(ids);
		return ro;
	}
	
	
	/*********** 转发部门 **********/
	@ResponseBody
	@RequestMapping("/zhuanfa-record_")
	public RO zhuanfa(@RequestParam(value="ids", required=false) String ids,
			@RequestParam(value="group", required=false) String groupid){
		ro = recordService.zhuanfaRecord(ids,groupid);
		return ro;
	}
	
	
	/*********** 待处理 **********/
	@RequestMapping("/confirm-wait-list")
	public String waitlist(HttpServletRequest request){
		List<Group> glist = recordService.glist();
		List<Dict> nlist = dictService.getDictByType("NETTYPE");
		List<Dict> wlist = dictService.getDictByType("WORKTYPE");
		request.setAttribute("nlist", nlist);
		request.setAttribute("glist", glist);
		request.setAttribute("wlist", wlist);
		return "record/wait-list";
	}
	
	@RequestMapping("/confirm-wait-list-json_")
	public void waitlistjson(HttpServletRequest request,HttpServletResponse response,
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
		
		User u = (User) request.getSession().getAttribute("loginUser");
		recordschema.setCreateUserId(u.getId());
		fpi = recordService.waitlist(fpi, recordschema, time1 , time2 , sortName, sortOrder);
		
		
		out.println(JsonUtil.obj2json(fpi));
		out.flush();
		out.close();
	}
	
	/*********** 确认 **********/
	@ResponseBody
	@RequestMapping("/confirm-wait_")
	public RO confirmwait(@RequestParam(value="ids", required=false) String ids ){
		ro = recordService.confirmwait(ids);
		return ro;
	}
	
	
	/*********** 删除 **********/
	@ResponseBody
	@RequestMapping("/del-record")
	public RO del(@RequestParam(value="ids", required=false) String ids ){
		ro = recordService.del(ids);
		return ro;
	}
	
}
