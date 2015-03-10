package com.ctgu.record.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ctgu.dao.DAO;
import com.ctgu.file.model.UpLoadFile;
import com.ctgu.group.model.Group;
import com.ctgu.record.model.Record;
import com.ctgu.record.model.RecordSchema;
import com.ctgu.record.service.RecordService;
import com.ctgu.user.model.User;
import com.ctgu.util.DateUtil;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.PropertyUtil;
import com.ctgu.util.RO;
import com.ctgu.util.StringUtil;

@Component("RecordService")
public class RecordServiceImpl implements RecordService {

	@Resource(name = "DAO")
	private DAO dao;
	
	@Override
	public RO addsave(Record record , String addtels , String remark) {
		RO ro = new RO();
		if(record.getStatusFlag().equals("1"))
			record.setCcflag("1");//待查重
		else
			record.setShflag("1");//待审核
		if(StringUtil.isNotEmpty(record.getMediaPath())){
			String fileUploadPath = PropertyUtil.getProperty("uploadPath");
			record.setMediaPath(fileUploadPath+"/"+record.getMediaPath());
		}
		dao.save(record);
		if(StringUtil.isNotEmpty(record.getMediaPath())){//如果附件不为空，就往db_file表插入一条记录
			UpLoadFile file = new UpLoadFile();
			file.setRecordId(record.getId());
			file.setFilePath(record.getMediaPath());
			file.setRemark(remark);
			addFile(file);
		}
		if(StringUtil.isNotEmpty(addtels)){//批量生成记录
			String sql ;
			String[] attr = addtels.split(",");
			for(int i=0 ; i< attr.length; i++){
				if(StringUtil.isNotEmpty(attr[i])){
					sql = "insert into db_record(recordNum,phoneNum,groupId,workType,netType,reason,money,mediaPath,createUserId,createtime,statusFlag,CCflag,SHflag) values('" 
							+ record.getRecordNum()+"','"
							+ attr[i]+"','"
							+ record.getGroupId()+"','" 
							+ record.getWorkType()+"','"
							+ record.getNetType()+"','"
							+ record.getReason()+"','"
							+ record.getMoney()+"'";
							if(StringUtil.isNotEmpty(record.getMediaPath()))
								sql += ",'" + record.getMediaPath()+"'";
							else
								sql += ",null";
							sql +=" ,"+record.getCreateUserId();
							sql +=" ,'"+record.getCreatetime()+"'";
							
							if(check(attr[i]).size()==0)
								sql+=",2,null,1";
							else
								sql+=",1,1,null";
							sql += ")";
					dao.excuteBySql(new String[]{sql});
				}
			}
		}
		ro.setUrl("/record/apply.do");
		return ro;
	}

	@Override
	public List<Group> glist() {
		StringBuilder hql = new StringBuilder("from Group where pid = 1 ");
		return dao.find(hql.toString());
	}

	@Override
	public void addFile(UpLoadFile file) {
		dao.save(file);
		
	}

	@Override
	public List<RecordSchema> check(String tels) {
		if(StringUtil.isEmpty(tels))
			return new ArrayList();
		String sql = "SELECT * from  record_schema where phoneNum in("+tels+") and createtime between  DATE_ADD(now(),INTERVAL -2 MONTH) and now()";
		List<RecordSchema> list = dao.findBySql(sql, RecordSchema.class);
		return list;
	}

	@Override
	public FlipPageInfo<RecordSchema> checkrepeatlist(
			FlipPageInfo<RecordSchema> fpi, RecordSchema recordschema, String time1 , String time2,
			String sortName, String sortOrder) {
		StringBuilder sql = new StringBuilder("select * from record_schema u where statusFlag = 1 and CCflag = 1 ");//查重区
		
		if(recordschema != null){
			if(StringUtil.isNotEmpty(recordschema.getUserName())){
				sql.append(" and u.userName like '%"+recordschema.getUserName()+"%' ");
			}
			
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
			
			if(StringUtil.isNotEmpty(recordschema.getGroupName())){
				sql.append(" and u.groupName = '"+recordschema.getGroupName()+"' ");
			}
		}
		
		if (StringUtil.isNotEmpty(sortName) && StringUtil.isNotEmpty(sortOrder)) {
			sortOrder = "desc".equalsIgnoreCase(sortOrder) ? "desc" : "asc";
			sql.append(" order by u." + sortName + " " + sortOrder);
		} else {
			sql.append(" order by u.id desc");
		}
		return dao.findWithPagingBySql(fpi, sql.toString(), RecordSchema.class);
	}

	@Override
	public RO confirmMoney(String ids, String confirmMoney, String remark1 , Integer userid) {
		RO ro = new RO();
		String sql;
		if("0".equals(confirmMoney))//不同意
			sql = "update db_record set CCflag = 2, CCUserId="+userid+",CCTime='"+DateUtil.nowString("YYYY-MM-dd HH:mm:ss")+"', statusFlag = 3, confirmMoney = 0 , remark1 = '"+remark1+"' where id in("+ids+")";
		else
			sql = "update db_record set CCflag = 2, CCUserId="+userid+",CCTime='"+DateUtil.nowString("YYYY-MM-dd HH:mm:ss")+"', SHflag = 1, statusFlag = 2, confirmMoney = "+confirmMoney+" , remark1 = '"+remark1+"' where id in ("+ids+") ";
		dao.excuteBySql(new String[]{sql});
		ro.setInfo("稽核成功!");
		return ro;
	}

	@Override
	public RO confirmMoneyBatch(String ids, Integer userid, String flag) {
		RO ro = new RO();
		String sql ;
		if(flag.equals("1")){//同意
			sql = "update db_record set CCflag = 2, CCUserId="+userid+",CCTime='"+DateUtil.nowString("YYYY-MM-dd HH:mm:ss")+"', SHflag = 1, statusFlag = 2, confirmMoney = money , remark1 = '同意' where id in ("+ids+") ";
		}else{
			sql = "update db_record set CCflag = 2, CCUserId="+userid+",CCTime='"+DateUtil.nowString("YYYY-MM-dd HH:mm:ss")+"',  statusFlag = 3, confirmMoney = 0 , remark1 = '不同意' where id in ("+ids+") ";
		}
		dao.excuteBySql(new String[]{sql});
		ro.setInfo("");
		return ro;
	}

	@Override
	public FlipPageInfo<RecordSchema> shenhelist(
			FlipPageInfo<RecordSchema> fpi, RecordSchema recordschema,
			String time1, String time2, String sortName, String sortOrder) {
		
		StringBuilder sql = new StringBuilder("select * from record_schema u where statusFlag = 2 and SHflag = 1 ");//审核
		
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
			
			if(recordschema.getGroupId()!=1 && recordschema.getGroupId()!=null){
				sql.append(" and u.groupId = "+recordschema.getGroupId());
			}
		}
		
		if (StringUtil.isNotEmpty(sortName) && StringUtil.isNotEmpty(sortOrder)) {
			sortOrder = "desc".equalsIgnoreCase(sortOrder) ? "desc" : "asc";
			sql.append(" order by u." + sortName + " " + sortOrder);
		} else {
			sql.append(" order by u.id desc");
		}
		return dao.findWithPagingBySql(fpi, sql.toString(), RecordSchema.class);
	}
	
	@Override
	public RO confirmshenheMoney(String ids, String confirmMoney, String remark2 , Integer userid) {
		RO ro = new RO();
		String sql;
		if("0".equals(confirmMoney))//不同意
			sql = "update db_record set SHflag = 2, SHUserId="+userid+",SHTime='"+DateUtil.nowString("YYYY-MM-dd HH:mm:ss")+"', statusFlag = 3, confirmMoney = 0 , remark2 = '"+remark2+"' where id in("+ids+")";
		else
			sql = "update db_record set SHflag = 2, SHUserId="+userid+",SHTime='"+DateUtil.nowString("YYYY-MM-dd HH:mm:ss")+"', statusFlag = 3, confirmMoney = "+confirmMoney+" , remark2 = '"+remark2+"' where id in ("+ids+") ";
		dao.excuteBySql(new String[]{sql});
		ro.setInfo("审核成功!");
		return ro;
	}

	@Override
	public RO confirmshenheMoneyBatch(String ids, Integer userid, String flag) {
		RO ro = new RO();
		String sql ;
		if(flag.equals("1")){//同意
			sql = "update db_record set SHflag = 2, SHUserId="+userid+",SHTime='"+DateUtil.nowString("YYYY-MM-dd HH:mm:ss")+"' , statusFlag = 3, confirmMoney = money , remark2 = '同意' where id in ("+ids+") ";
		}else{
			sql = "update db_record set SHflag = 2, SHUserId="+userid+",SHTime='"+DateUtil.nowString("YYYY-MM-dd HH:mm:ss")+"',  statusFlag = 3, confirmMoney = 0 , remark2 = '不同意' where id in ("+ids+") ";
		}
		dao.excuteBySql(new String[]{sql});
		ro.setInfo("");
		return ro;
	}

	@Override
	public RO editsave(Record record) {
		RO ro = new RO();
		dao.excuteBySql(new String[]{"update db_record set netType= "+record.getNetType()+",workType ="+record.getWorkType()+" , confirmMoney = "+record.getConfirmMoney()+"  where id =  "+record.getId()});
		ro.setInfo("保存成功!");
		return ro;
	}
	
	@Override
	public RO turnBackRecord(String ids){
		RO ro = new RO();
		dao.excuteBySql(new String[]{"update db_record set CCflag = 1 , CCUserId = null,SHflag = null , statusFlag = 1 where id in ("+ids+") "});
		ro.setInfo("转发成功!");
		return ro;
	}
	
	@Override
	public RO zhuanfaRecord(String ids , String groupid){
		RO ro = new RO();
		dao.excuteBySql(new String[]{"update db_record set groupId = "+groupid+" where id in ("+ids+") "});
		ro.setInfo("转发成功!");
		return ro;
	}
	
	@Override
	public FlipPageInfo<RecordSchema> waitlist(
			FlipPageInfo<RecordSchema> fpi, RecordSchema recordschema,
			String time1, String time2, String sortName, String sortOrder) {
		
		StringBuilder sql = new StringBuilder("select * from record_schema u where statusFlag >= 3 ");//待处理
		
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
			
			
			if(recordschema.getCreateUserId()!=1 && recordschema.getCreateUserId()!=null){
				sql.append(" and u.CreateUserId = "+recordschema.getCreateUserId());
			}
		}
		
		if (StringUtil.isNotEmpty(sortName) && StringUtil.isNotEmpty(sortOrder)) {
			sortOrder = "desc".equalsIgnoreCase(sortOrder) ? "desc" : "asc";
			sql.append(" order by u." + sortName + " " + sortOrder);
		} else {
			sql.append(" order by u.id desc");
		}
		return dao.findWithPagingBySql(fpi, sql.toString(), RecordSchema.class);
	}

	@Override
	public RO confirmwait(String ids) {
		RO ro = new RO();
		dao.excuteBySql(new String[]{"update db_record set statusFlag = 4 where id in("+ids+") "});
		ro.setInfo("确认成功!");
		
		return ro;
	}

	@Override
	public RO del(String ids) {
		RO ro = new RO();
		dao.excuteBySql(new String[]{"delete from db_record where id in("+ids+") "});
		ro.setInfo("删除成功!");
		return ro;
	}

	@Override
	public List<Record> task(User user, int num) {
		int roleid = user.getRole().getId();
		int groupid = user.getGroup().getId();
		String hql = "";
		if(roleid == 2){//退费员
			hql = " from Record where statusFlag = 3 and createUserId= "+user.getId();
		}else if(roleid == 3){//审核员
			hql = "from Record where statusFlag = 2 and shflag = 1 and groupId ="+groupid;
		}else if(roleid == 4){//稽核员
			hql = "from Record where statusFlag = 1 and ccflag = 1  ";
		
		}else{
			hql = "from Record where 1=1 ";
		}
		
			hql += " order by id desc ";
			return dao.find(hql, num);
	}
	


}
