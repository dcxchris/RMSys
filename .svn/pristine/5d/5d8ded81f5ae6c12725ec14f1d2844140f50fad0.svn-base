package com.ctgu.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.ctgu.dao.DAO;
import com.ctgu.dict.model.Dict;


@Component
public class AppListener implements ApplicationListener<ContextRefreshedEvent>{
	
	public static Map<String ,String> map = new HashMap<String , String>();

	@Resource(name = "DAO")
	private DAO dao;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if(event.getApplicationContext().getParent() == null){
			
			StringBuilder script = new StringBuilder();
			
			List<String> dictType = dao.findBySql("select type from db_dict group by type order by type asc  ",null);
			script.append("var dict = {");
			for(String dicttype : dictType){
				script.append("\"").append(dicttype).append("\":{");
				List<Dict> group = dao.find("from Dict where type =  '"+dicttype+"'");
				
				for(Dict dict : group){
					script.append("\"").append(dict.getValue()).append("\":").append("\"").append(StringUtil.toUnicode(dict.getName())).append("\",");
					map.put(dicttype+dict.getValue(), dict.getName());
					map.put(dict.getName(), dict.getValue()+"");
				}
				script.delete(script.length()-1, script.length());
				script.append("},");
			}
			script.delete(script.length()-1, script.length());
			script.append(" };");
			System.out.println(script.toString());
			String path = System.getProperty("user.dir");
			path = path.substring(0, path.length()-3);
			System.out.println(path);
			String dictpath = path+"webapps\\RMSys\\js\\dict.js";
			FileOutputStream os;
			try {
				os = new FileOutputStream(new File(dictpath));
				os.write(script.toString().getBytes());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static String getDict(String type,String value){
		return map.get(type+value);
	}
	

}
