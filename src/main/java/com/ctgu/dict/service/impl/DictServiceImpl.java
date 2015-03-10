package com.ctgu.dict.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ctgu.dao.DAO;
import com.ctgu.dict.model.Dict;
import com.ctgu.dict.service.DictService;
import com.ctgu.util.StringUtil;


@Component("DictService")
public class DictServiceImpl implements DictService {

	@Resource(name = "DAO")
	private DAO dao;
	
	@Override
	public String getDict(String type, String value) {
		if(StringUtil.isEmpty(value))
			return "";
		return dao.findFirst("select name from Dict where type = '"+type+"' and value="+value);
	}

	@Override
	public List<Dict> getDictByType(String type) {
		
		return dao.find("from Dict where type = '"+type+"' ");
	}

}
