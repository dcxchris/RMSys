package com.ctgu.mossle.workcal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ctgu.dao.DAO;
import com.ctgu.mossle.workcal.domain.WorkcalRule;
import com.ctgu.mossle.workcal.service.WorkcalRuleService;

@Component("WorkcalRuleService")
public class WorkcalRuleServiceImpl implements WorkcalRuleService{

	@Resource(name = "DAO")
	private DAO dao;
	
	@Override
	public List<WorkcalRule> find(String hql) {
		
		return dao.find(hql);
	}

}
