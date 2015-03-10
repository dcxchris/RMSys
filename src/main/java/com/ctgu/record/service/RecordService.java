package com.ctgu.record.service;

import java.util.List;

import com.ctgu.file.model.UpLoadFile;
import com.ctgu.group.model.Group;
import com.ctgu.record.model.Record;
import com.ctgu.record.model.RecordSchema;
import com.ctgu.user.model.User;
import com.ctgu.util.FlipPageInfo;
import com.ctgu.util.RO;

public interface RecordService {
	
	public FlipPageInfo<RecordSchema> checkrepeatlist(FlipPageInfo<RecordSchema> fpi , RecordSchema recordschema , String time1 , String time2,String sortName , String sortOrder );
	
	public FlipPageInfo<RecordSchema> shenhelist(FlipPageInfo<RecordSchema> fpi , RecordSchema recordschema , String time1 , String time2,String sortName , String sortOrder );

	public FlipPageInfo<RecordSchema> waitlist(FlipPageInfo<RecordSchema> fpi , RecordSchema recordschema , String time1 , String time2,String sortName , String sortOrder );
	
	public RO addsave(Record record , String addtels , String remark);
	
	public RO editsave(Record record);
	
	public RO turnBackRecord(String ids);
	
	public RO zhuanfaRecord(String ids,String groupid);
	
	public RO confirmwait(String ids);
	
	public RO del(String ids);
	
	public List<Group> glist();
	
	public void addFile(UpLoadFile file);
	
	public List<RecordSchema> check(String tels);
	
	public RO confirmMoney(String ids , String confirmMoney , String remark , Integer userid );
	
	public RO confirmMoneyBatch(String ids ,Integer userid,String flag);
	
	public RO confirmshenheMoney(String ids , String confirmMoney , String remark , Integer userid );
	
	public RO confirmshenheMoneyBatch(String ids ,Integer userid,String flag);
	
	public List<Record> task(User user , int num);
	
}
