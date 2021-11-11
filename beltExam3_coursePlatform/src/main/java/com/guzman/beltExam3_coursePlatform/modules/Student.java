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

import com.guzman.beltExam3_coursePlatform.modules.*;


@Entity
@Table(name="students")
public class Student {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idstudent;
	
    @NotNull
    @NotEmpty(message="Name is required!")
    @Size(min=2, max=10, message="Name must be between 2 and 10 characters")
    private String name;
    
    @NotNull
    @NotEmpty(message="Email is required!")
    @Email(message="Please enter a valid email")
    private String email;
    
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdat;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedat;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idinstructor")
    private Instructor instructor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idcourse")
    private Course course;
    
    
    public Student() {
        
    }
    public Student(String name, String email) {
    	this.name = name;
    	this.email = email;
    }
    
    
	public Long getIdstudent() {
		return idstudent;
	}
	public void setIdstudent(Long idstudent) {
		this.idstudent = idstudent;
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
