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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="courses")
public class Course {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcourse;
    
    @NotNull
    @NotEmpty(message="Name is required!")
    private String name;
    
    @NotNull
    @NotEmpty(message="Set day is required!")
    private String dayoftheweek;
    
    @NotNull
//    @NotEmpty(message="Set price is required!")
    @Range(min=0)
    private double price;
    
    @NotNull
    @NotEmpty(message="A description is required!")
    private String description;
    
    
    private String time;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdat;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedat;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idinstructedby")
    private Instructor instructedby;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "students", 
        joinColumns = @JoinColumn(name = "idcourse"), 
        inverseJoinColumns = @JoinColumn(name = "idinstructor")
    )
    private List<Instructor> instructor;

	public Course() {
	
	}
	public Course(Long idcourse, String name, String dayoftheweek, double price, String description, String time) {
		this.idcourse= idcourse;
		this.name = name;
		this.dayoftheweek = dayoftheweek;
		this.price= price;
		this.description = description;
		this.time = time;
	}
	
	
	
	public Long getIdcourse() {
		return idcourse;
	}
	public void setIdcourse(Long idcourse) {
		this.idcourse = idcourse;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDayoftheweek() {
		return dayoftheweek;
	}
	public void setDayoftheweek(String dayoftheweek) {
		this.dayoftheweek = dayoftheweek;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

	
    public Instructor getInstructedby() {
		return instructedby;
	}
	public void setInstructedby(Instructor instructedby) {
		this.instructedby = instructedby;
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
