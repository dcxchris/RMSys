package com.ctgu.dict.service;

import java.util.List;

import com.ctgu.dict.model.Dict;


public interface DictService {
	
	public String getDict(String code,String value);
	
	public List<Dict> getDictByType(String type);

}
