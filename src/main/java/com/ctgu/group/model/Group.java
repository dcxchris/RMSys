package com.ctgu.group.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Group entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="db_group"
     ,catalog="rmsys"
)

public class Group  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String groupName;
     private Integer pid;
     private String type;



   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="groupName", length=100)

    public String getGroupName() {
        return this.groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    @Column(name="pid")

    public Integer getPid() {
        return this.pid;
    }
    
    public void setPid(Integer pid) {
        this.pid = pid;
    }
    
    @Column(name="type", length=2)

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }




}