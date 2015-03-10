package com.ctgu.job.model;
// default package


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * JobSchema entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="job_schema"
    ,catalog="rmsys"
)

public class JobSchema  implements java.io.Serializable {


	// Fields    

	private Long id;
    private String checkFlag = "0" ;//是否已经处理;
    private String createtime; 
    private String jobType ;//1待查重  2待审核  3待确认
    private String recordNum; //工单号
    private String phoneNum; // 电话号
    private Integer groupId; 
    private String workType; //业务类别
    private String netType; //网别
    private String reason;  //退费原因
    private Double money; //退费金额
    private String mediaPath;  // 附近
    private String fromUser;




  
   // Property accessors
    @Id
    @Column(name="jid")
    
    public Long getId() {
    	return this.id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }

   @Column(name="checkFlag", length=2)

   public String getCheckFlag() {
       return this.checkFlag;
   }
   
   public void setCheckFlag(String checkFlag) {
       this.checkFlag = checkFlag;
   }


   @Column(name="jobType", length=2)

   public String getJobType() {
       return this.jobType;
   }
   
   public void setJobType(String jobType) {
       this.jobType = jobType;
   }


   @Column(name="recordNum", length=20)

   public String getRecordNum() {
       return this.recordNum;
   }
   
   public void setRecordNum(String recordNum) {
       this.recordNum = recordNum;
   }

   @Column(name="phoneNum", length=11)

   public String getPhoneNum() {
       return this.phoneNum;
   }
   
   public void setPhoneNum(String phoneNum) {
       this.phoneNum = phoneNum;
   }

   @Column(name="groupId")

   public Integer getGroupId() {
       return this.groupId;
   }
   
   public void setGroupId(Integer groupId) {
       this.groupId = groupId;
   }

   @Column(name="workType", length=2)

   public String getWorkType() {
       return this.workType;
   }
   
   public void setWorkType(String workType) {
       this.workType = workType;
   }

   @Column(name="netType", length=2)

   public String getNetType() {
       return this.netType;
   }
   
   public void setNetType(String netType) {
       this.netType = netType;
   }

   @Column(name="reason", length=65535)

   public String getReason() {
       return this.reason;
   }
   
   public void setReason(String reason) {
       this.reason = reason;
   }

   @Column(name="money", precision=10)

   public Double getMoney() {
       return this.money;
   }
   
   public void setMoney(Double money) {
       this.money = money;
   }

   @Column(name="mediaPath")

   public String getMediaPath() {
       return this.mediaPath;
   }
   
   public void setMediaPath(String mediaPath) {
       this.mediaPath = mediaPath;
   }

   

   @Column(name="jobcreatetime", length=20)

   public String getCreatetime() {
       return this.createtime;
   }
   
   public void setCreatetime(String createtime) {
       this.createtime = createtime;
   }

   @Column(name="userName", length=20)
	public String getFromUser() {
		return fromUser;
	}
	
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

   
   



  
}