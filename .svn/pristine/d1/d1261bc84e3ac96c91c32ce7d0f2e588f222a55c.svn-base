package com.ctgu.user.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ctgu.group.model.Group;
import com.ctgu.role.model.Auth;
import com.ctgu.role.model.Role;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="db_user" 
     ,catalog="rmsys"
)

public class User  implements UserDetails , java.io.Serializable {


	// Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -5980561976037296114L;
	private Integer id;
     private Role role = new Role();
     private Group group = new Group();
     private String username;
     private String password;
     private String enable;
     private String tel;
     private String location;
     private String flag;//1 开 2 关


    

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="roleId")

    public Role getRole() {
        return this.role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="groupId")

    public Group getGroup() {
        return this.group;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }
    
    @Column(name="userName", length=30)

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="password", length=100)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="enable", length=2)

    public String getEnable() {
        return this.enable;
    }
    
    public void setEnable(String enable) {
        this.enable = enable;
    }

    @Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
    	Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();
		Set<Auth> s = new HashSet();
		if(role != null){
			for(Auth a : role.getAuths() ){
				if(a.getEnable().equals("1"))
					s.add(a);
			}
		}
		authorities.addAll(s);
		return authorities;
	}


    @Transient
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}


    @Transient
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}


    @Transient
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}


    @Transient
	public boolean isEnabled() {
		return this.enable=="0"?true:false;
	}



    @Column(name="tel")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name="location")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name="flag")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
   








}