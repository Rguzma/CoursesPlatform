package com.guzman.beltExam3_coursePlatform.modules;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="instructors")
public class Instructor {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idinstructor;
	
    @NotNull
    @NotEmpty(message="Name is required!")
    @Size(min=2, max=10, message="Name must be between 2 and 10 characters")
    private String name;
    
    @NotNull
    @NotEmpty(message="Email is required!")
    @Email(message="Please enter a valid email")
    private String email;
    
    @NotNull
    @NotEmpty(message="Password is required!")
    @Size(min=7, max=12, message="Password must be between 8 and 12 characters")
    private String password;
    
    @Transient
    @NotEmpty(message="Confirm Password is required!")
    private String confirmpassword;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdat;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedat;
    
    @OneToMany(mappedBy="instructedby",fetch = FetchType.LAZY)
    private List<Course> teach;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "students", 
        joinColumns = @JoinColumn(name = "idinstructor"), 
        inverseJoinColumns = @JoinColumn(name = "idcourse")
    )
    private List<Course> courses;

	public Instructor() {
	
	}
	public Instructor(String name, String email, String password) {
		super();
	    this.name = name;
	    this.email = email;
	    this.password = password;
	}
	public Long getIdinstructor() {
		return idinstructor;
	}
	public void setIdinstructor(Long idinstructor) {
		this.idinstructor = idinstructor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public Date getCreatedat() {
		return createdat;
	}
	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}
	public Date getUpdatedat() {
		return updatedat;
	}
	public void setUpdatedat(Date updatedat) {
		this.updatedat = updatedat;
	}
	
    @PrePersist
    protected void onCreate(){
        this.createdat = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedat = new Date();
    }

}
