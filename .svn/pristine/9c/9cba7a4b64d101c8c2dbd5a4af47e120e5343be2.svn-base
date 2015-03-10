package com.ctgu.role.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;


/**
 * Auth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="db_auth"
     ,catalog="rmsys"
)

public class Auth  implements GrantedAuthority , java.io.Serializable {


    // Fields    

     private Integer id;
     private String authority;
     private String name;
     private String url;
     private String enable;


   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="authority", length=30)

    public String getAuthority() {
        return this.authority;
    }
    
    public void setAuthority(String authority) {
        this.authority = authority;
    }
    
    @Column(name="name", length=30)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="url", length=50)

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    @Column(name="enable", length=2)

    public String getEnable() {
        return this.enable;
    }
    
    public void setEnable(String enable) {
        this.enable = enable;
    }
   








}