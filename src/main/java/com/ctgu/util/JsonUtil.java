package com.ctgu.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class JsonUtil {

	public static Object json2obj(String jsonstr,Class<?>clazz){
		if(StringUtil.isEmpty(jsonstr))
			return null;
		JSONObject json = JSONObject.fromObject(jsonstr); 
		
		return JSONObject.toBean(json, clazz);
		
	}
	
	public static List<Class<?>> json2list(String jsonStr,Class<?>clazz){
		if(StringUtil.isEmpty(jsonStr))
			return new ArrayList<Class<?>>();
		JSONArray jsonArray = JSONArray.fromObject(jsonStr); 
		List<Class<?>> list = new ArrayList<Class<?>>();
		for(int i = 0; i < jsonArray.size(); i++)
        {
            JSONObject jo = jsonArray.getJSONObject(i);
            Class<?> bean = (Class<?>) JSONObject.toBean(jo, clazz);
            list.add(bean);
        }
		
		return list;
	}
	
	public static String obj2json(Object obj){
	   	JSONObject json = JSONObject.fromObject(obj);
	   	String jsonStr = json.toString();
	   	System.out.println(jsonStr);
	   	return jsonStr;
   	 
	}
	
	public static String List2json(List<?> list){
		JSONArray arr = JSONArray.fromObject(list);
   	 	String jsonArr = arr.toString();
   	 	return jsonArr;
	}
	
	public static void main(String[] args) {}
}
