package com.ctgu.notice.service;

import java.util.List;

import com.ctgu.notice.model.Notice;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.RO;

public interface NoticeService {

	public FlipPageInfo<Notice> list(FlipPageInfo<Notice> fpi, Notice notice , String sortName , String sortOrder);
	
	public RO addsave(Notice notice);
	
	public RO editsave(Notice notice);
	
	public RO del(String ids);
	
	public Notice findBYid(String id);
	
	public List<Notice> list(int num);
}
