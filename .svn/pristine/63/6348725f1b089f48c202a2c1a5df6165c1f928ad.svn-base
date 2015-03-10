package com.ctgu.notice.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ctgu.dao.DAO;
import com.ctgu.notice.model.Notice;
import com.ctgu.notice.service.NoticeService;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.RO;
import com.ctgu.util.StringUtil;

@Component("NoticeService")
public class NoticeServiceImpl implements NoticeService {

	@Resource(name = "DAO")
	private DAO dao;
	
	@Override
	public FlipPageInfo<Notice> list(FlipPageInfo<Notice> fpi, Notice notice,
			String sortName, String sortOrder) {
		StringBuilder hql =new StringBuilder(" from Notice u where 1=1 ");
		
		if(notice!=null){
			if(StringUtil.isNotEmpty(notice.getTitle())){
				hql.append(" and u.title like '%"+notice.getTitle()+"%'  ");
			}
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
	public RO addsave(Notice notice) {
		RO ro = new RO();
		dao.save(notice);
		ro.setUrl("/notice/notice-list.do");
		return ro;
	}

	@Override
	public RO editsave(Notice notice) {
		RO ro = new RO();
		dao.merge(notice);
		ro.setUrl("/notice/notice-list.do");
		return ro;
	}

	@Override
	public RO del(String ids) {
		RO ro = new RO();
		dao.excuteBySql(new String[]{"delete from db_notice where id in ("+ids+")  "});
		ro.setInfo("删除成功!");
		return ro;
	}

	@Override
	public Notice findBYid(String id) {
		return dao.findFirst("from Notice where id = "+id);
	}


	@Override
	public List<Notice> list(int num) {
		
		return dao.find("from Notice order by id desc ", num);
	}

}
