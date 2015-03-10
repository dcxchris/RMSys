package com.ctgu.job.model;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Job entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="db_job"
    ,catalog="rmsys"
)

public class Job  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Long recordId;
     private String createTime;
     private Integer fromUserId;
     private String checkFlag;
     private String jobType;


    

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="recordId")

    public Long getRecordId() {
        return this.recordId;
    }
    
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }
    
    @Column(name="createTime", length=30)

    public String getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="FromUserId")

    public Integer getFromUserId() {
        return this.fromUserId;
    }
    
    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
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
   








}