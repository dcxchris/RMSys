package com.ctgu.record.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ctgu.util.StringUtil;


/**
 * Record entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="db_record"
    ,catalog="rmsys"
)

public class Record  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String recordNum;//工单号
     private String phoneNum;//电话号
     private Integer groupId;//审核部门
     private String workType;//业务类型
     private String netType;//网别
     private String reason;//退费原因
     private Double money;//申请金额
     private Double confirmMoney;//确认金额
     private String remark1;//留言
     private String remark2;
     private String mediaPath;//附件
     private Integer createUserId;//申请操作人
     private String createtime;//申请时间
     private String statusFlag = "2";//申请状态  1待查重  2待审核  3待确认 4结束
     private String ccflag;//查重状态 1待查重 2已稽核
     private Integer ccuserId;//查重员
     private String cctime;//查重时间
     private String shflag;//审核状态 1待审核 2已审核
     private Integer shuserId;//审核员
     private String shtime;//审核时间



   

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    	if(StringUtil.isEmpty(this.mediaPath))
    		return null;
    	else
    		return this.mediaPath;
    }
    
    public void setMediaPath(String mediaPath) {
    	if(StringUtil.isNotEmpty(mediaPath))
    		this.mediaPath = mediaPath;
    	else
    		this.mediaPath = null;
    }
    
    @Column(name="createUserId")

    public Integer getCreateUserId() {
        return this.createUserId;
    }
    
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }
    
    @Column(name="createtime", length=20)

    public String getCreatetime() {
        return this.createtime;
    }
    
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    
    @Column(name="statusFlag", length=2)

    public String getStatusFlag() {
        return this.statusFlag;
    }
    
    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
    }
    
    
    @Column(name="CCUserId")

    public Integer getCcuserId() {
        return this.ccuserId;
    }
    
    public void setCcuserId(Integer ccuserId) {
        this.ccuserId = ccuserId;
    }
    
    @Column(name="CCTime", length=20)

    public String getCctime() {
        return this.cctime;
    }
    
    public void setCctime(String cctime) {
        this.cctime = cctime;
    }
    
    @Column(name="SHUserId")

    public Integer getShuserId() {
        return this.shuserId;
    }
    
    public void setShuserId(Integer shuserId) {
        this.shuserId = shuserId;
    }
    
    @Column(name="SHTime", length=20)

    public String getShtime() {
        return this.shtime;
    }
    
    public void setShtime(String shtime) {
        this.shtime = shtime;
    }

    @Column(name="confirmMoney", length=20)
	public Double getConfirmMoney() {
		return confirmMoney;
	}

	public void setConfirmMoney(Double confirmMoney) {
		this.confirmMoney = confirmMoney;
	}

	@Column(name="remark1", length=20)
	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	
	@Column(name="remark2", length=20)
	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	@Column(name="CCflag", length=20)
	public String getCcflag() {
		return ccflag;
	}

	public void setCcflag(String ccflag) {
		this.ccflag = ccflag;
	}
	@Column(name="SHflag", length=20)
	public String getShflag() {
		return shflag;
	}

	public void setShflag(String shflag) {
		this.shflag = shflag;
	}
   








}