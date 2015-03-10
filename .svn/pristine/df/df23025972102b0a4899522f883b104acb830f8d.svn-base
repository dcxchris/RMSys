package com.ctgu.role.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * Role entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="db_role"
     ,catalog="rmsys"
)

public class Role  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String roleName;
     private String enable = "1";
     private Integer groupId = 1;
     private Set<Auth> auths = new HashSet<Auth>(0);



   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="roleName", length=30)

    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    @Column(name="enable", length=2)

    public String getEnable() {
        return this.enable;
    }
    
    public void setEnable(String enable) {
        this.enable = enable;
    }
    
    @Column(name="groupId")

    public Integer getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "db_role_auth", joinColumns = { @JoinColumn(name = "roleid") }, inverseJoinColumns = @JoinColumn(name = "authid"))
	public Set<Auth> getAuths() {
		return auths;
	}

	public void setAuths(Set<Auth> auths) {
		this.auths = auths;
	}


}