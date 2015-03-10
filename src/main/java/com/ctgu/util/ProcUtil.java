package com.ctgu.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.jdbc.Work;
/**
 * 存储过程工具类
 * @author Administrator
 *
 */
public class ProcUtil implements Work{
	
	private String sql = "";
	
	private List<ProcParameter> param = null;
	
	private ResultSet rs = null;
	
	public ProcUtil(String sql,List<ProcParameter> param){
		this.sql = sql;
		this.param = param;
	}
	

	@Override
	public void execute(Connection conn) throws SQLException {
		CallableStatement call = conn.prepareCall(sql);
		if(param != null){
			for(int i=0 ; i<param.size() ; i++){
				if(param.get(i).getIOtype().equals("in"))
					call.setString(i+1, param.get(i).getValue()+"");
				else
					call.registerOutParameter(i+1,param.get(i).getType());
			}
		}
		
    	rs = call.executeQuery();   
		
	}
	
	public ResultSet getRs(){
		return rs;
	}

}
