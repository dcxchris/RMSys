package com.ctgu.search.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.springframework.stereotype.Component;

import com.ctgu.dao.DAO;
import com.ctgu.record.model.RecordSchema;
import com.ctgu.search.service.SearchService;
import com.ctgu.user.model.User;
import com.ctgu.util.AppListener;
import com.ctgu.util.DateUtil;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.StringUtil;


@Component("SearchService")
public class SearchServiceImpl implements SearchService{

	@Resource(name = "DAO")
	private DAO dao;
	
	@Override
	public FlipPageInfo<RecordSchema> list(HttpServletRequest request , FlipPageInfo<RecordSchema> fpi,
			RecordSchema recordschema,String time1, String time2,  String sortName, String sortOrder) {
		
		StringBuilder sql = new StringBuilder("select * from record_schema u where 1=1 ");
		
		if(recordschema != null){
			
			
			if(StringUtil.isNotEmpty(time1)){
				sql.append(" and left(u.createtime,10) >= '"+time1+"'");
			}
			
			if(StringUtil.isNotEmpty(time2)){
				sql.append(" and left(u.createtime,10) <= '"+time2+"' ");
			}
			
			if(StringUtil.isNotEmpty(recordschema.getWorkType())){
				sql.append(" and u.workType = "+recordschema.getWorkType());
			}
			
			if(StringUtil.isNotEmpty(recordschema.getNetType())){
				sql.append(" and u.netType =  "+recordschema.getNetType());
			}
			
			if(StringUtil.isNotEmpty(recordschema.getCCflag())){
				sql.append(" and u.CCflag = "+recordschema.getCCflag());
			}
			
			if(StringUtil.isNotEmpty(recordschema.getSHflag())){
				sql.append(" and u.SHflag = "+recordschema.getSHflag());
			}
			
			if(StringUtil.isNotEmpty(recordschema.getStatusFlag())){
				sql.append(" and u.statusFlag = "+recordschema.getStatusFlag());
			}
			
			User user = (User) request.getSession().getAttribute("loginUser");
			int roleid = user.getRole().getId();
			int groupid = user.getGroup().getId();
			
			if(roleid == 2){//退费员
				sql.append(" and u.createUserId= "+user.getId());
			}else if(roleid == 3){//审核员
				sql.append(" and  u.groupId ="+groupid);
			}else if(roleid == 4){//稽核员
				sql.append(" and (u.CCflag = 1 or u.CCflag =2)");
			
			}
		}
		
		if (StringUtil.isNotEmpty(sortName) && StringUtil.isNotEmpty(sortOrder)) {
			sortOrder = "desc".equalsIgnoreCase(sortOrder) ? "desc" : "asc";
			sql.append(" order by u." + sortName + " " + sortOrder);
		} else {
			sql.append(" order by u.id desc");
		}
		request.getSession().setAttribute("downsql", sql.toString());
		return dao.findWithPagingBySql(fpi, sql.toString(), RecordSchema.class);
	}

	@Override
	public void export(HttpServletRequest request,
			HttpServletResponse response) {
		
		String sql = (String) request.getSession().getAttribute("downsql") ;
		List<RecordSchema> list = dao.findBySql(sql,RecordSchema.class);
		
		//打印excel
		response.reset();
		response.setContentType("applicationnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment;filename=record.xls");
		try {
			OutputStream out = response.getOutputStream();
			WritableWorkbook book = Workbook.createWorkbook(out);
			WritableSheet sheet = book.createSheet("sheet", 0);
			
			WritableFont wf_title = new WritableFont(WritableFont.createFont("宋体"), 16, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		               jxl.format.Colour.BLACK); 
			WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // 单元格定义   
		    wcf_title.setAlignment(Alignment.CENTRE);
			wcf_title.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			
			WritableFont wf_title2 = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		               jxl.format.Colour.BLACK); 
			WritableCellFormat wcf_title2 = new WritableCellFormat(wf_title2); // 单元格定义   
			wcf_title2.setAlignment(Alignment.LEFT);
			wcf_title2.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			WritableFont wf_title3 = new WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		               jxl.format.Colour.BLACK); 
			WritableCellFormat wcf_title3 = new WritableCellFormat(wf_title3); // 单元格定义   
			wcf_title3.setAlignment(Alignment.CENTRE);
			wcf_title3.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf_title3.setBorder(jxl.format.Border.TOP, BorderLineStyle.THIN);
			wcf_title3.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);	
			wcf_title3.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
			wcf_title3.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.THIN);
			
			sheet.mergeCells(0, 0, 18, 0);//合并列
			sheet.addCell(new Label(0,0,"退费记录",wcf_title));
			
			sheet.setRowView(1, 500);
			sheet.addCell(new Label(0,1,"序号",wcf_title3));
			sheet.addCell(new Label(1,1,"电话",wcf_title3));
			sheet.addCell(new Label(2,1,"审核部门",wcf_title3));
			sheet.addCell(new Label(3,1,"业务类型",wcf_title3));
			sheet.addCell(new Label(4,1,"网别",wcf_title3));
			sheet.addCell(new Label(5,1,"金额",wcf_title3));
			sheet.addCell(new Label(6,1,"退费原因",wcf_title3));
			sheet.addCell(new Label(7,1,"确认金额",wcf_title3));
			sheet.addCell(new Label(8,1,"查重状态",wcf_title3));
			sheet.addCell(new Label(9,1,"操作员",wcf_title3));
			sheet.addCell(new Label(10,1,"查重时间",wcf_title3));
			sheet.addCell(new Label(11,1,"留言",wcf_title3));
			sheet.addCell(new Label(12,1,"审核状态",wcf_title3));
			sheet.addCell(new Label(13,1,"操作员",wcf_title3));
			sheet.addCell(new Label(14,1,"审核时间",wcf_title3));
			sheet.addCell(new Label(15,1,"留言",wcf_title3));
			sheet.addCell(new Label(16,1,"退费员",wcf_title3));
			sheet.addCell(new Label(17,1,"退费时间",wcf_title3));
			sheet.addCell(new Label(18,1,"状态",wcf_title3));
			
			for(int i = 0 ; i < list.size() ; i++ ){
				sheet.addCell(new Label(0,i+2,(i+1)+"",wcf_title3));
				sheet.addCell(new Label(1,i+2,list.get(i).getPhoneNum(),wcf_title3));
				sheet.addCell(new Label(2,i+2,list.get(i).getGroupName(),wcf_title3));
				sheet.addCell(new Label(3,i+2,AppListener.getDict("WORKTYPE", list.get(i).getWorkType()),wcf_title3));
				sheet.addCell(new Label(4,i+2,AppListener.getDict("NETTYPE",list.get(i).getNetType()),wcf_title3));
				sheet.addCell(new Label(5,i+2,list.get(i).getMoney()+"",wcf_title3));
				sheet.addCell(new Label(6,i+2,list.get(i).getReason(),wcf_title3));
				sheet.addCell(new Label(7,i+2,list.get(i).getConfirmMoney()+"",wcf_title3));
				sheet.addCell(new Label(8,i+2,AppListener.getDict("CCFLAG",list.get(i).getCCflag()),wcf_title3));
				sheet.addCell(new Label(9,i+2,list.get(i).getCCUserName(),wcf_title3));
				sheet.addCell(new Label(10,i+2,list.get(i).getCCTime(),wcf_title3));
				sheet.addCell(new Label(11,i+2,list.get(i).getRemark1(),wcf_title3));
				sheet.addCell(new Label(12,i+2,AppListener.getDict("SHFLAG",list.get(i).getSHflag()),wcf_title3));
				sheet.addCell(new Label(13,i+2,list.get(i).getSHUserName(),wcf_title3));
				sheet.addCell(new Label(14,i+2,list.get(i).getSHTime(),wcf_title3));
				sheet.addCell(new Label(15,i+2,list.get(i).getRemark1(),wcf_title3));

				sheet.addCell(new Label(16,i+2,list.get(i).getUserName(),wcf_title3));
				sheet.addCell(new Label(17,i+2,list.get(i).getCreatetime(),wcf_title3));
				sheet.addCell(new Label(18,i+2,AppListener.getDict("STATUSFLAG",list.get(i).getStatusFlag()),wcf_title3));
				
				sheet.setRowView(i+2, 500);
				
			}
			sheet.setRowView(list.size()+2, 500);
			sheet.setColumnView(0, 5);	
			sheet.setColumnView(1, 15);	
			sheet.setColumnView(2, 10);
			sheet.setColumnView(3, 10);
			sheet.setColumnView(4, 10);
			sheet.setColumnView(5, 10);
			sheet.setColumnView(6, 15);
			sheet.setColumnView(7, 10);
			sheet.setColumnView(8, 10);
			sheet.setColumnView(9, 10);	
			sheet.setColumnView(10, 10);	
			sheet.setColumnView(11, 15);
			sheet.setColumnView(12, 10);
			sheet.setColumnView(13, 10);
			sheet.setColumnView(14, 10);
			sheet.setColumnView(15, 15);
			sheet.setColumnView(16, 10);
			sheet.setColumnView(17, 10);
			sheet.setColumnView(17, 10);
			
	        
			/*sheet.getSettings().setHorizontalCentre(true);
			sheet.getSettings().setOrientation(PageOrientation.PORTRAIT) ;    
			sheet.getSettings().setPaperSize(PaperSize.A4) ;  */
			
			book.write();
			book.close();
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//
		catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
