package com.ctgu.search.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ctgu.record.model.RecordSchema;
import com.ctgu.util.FlipPageInfo;


public interface SearchService {

	public FlipPageInfo<RecordSchema> list(HttpServletRequest request , FlipPageInfo<RecordSchema>fpi , RecordSchema recordschema ,String time1, String time2,  String sortName , String sortOrder);

	public void export(HttpServletRequest request , HttpServletResponse response);
	
}
