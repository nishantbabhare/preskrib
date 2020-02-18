package com.techtrail.commons.auth.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity@Data
@Table(name = "userdetails")
public class UserDetails {
	
	@Id@Column(name = "userid")
	private Integer userId;
	
	@Column(name = "birthdate")
	private Date birthDate;
	
	@Size(min=0, max= 50)
	private String designation; 
	
	@Column(name = "empid")
	@Size(min=0, max= 50)
	private String empId;
	
	@Size(min=0, max= 255)
	private String address;
	
	@Size(min=0, max= 100)
	private String qualification;
	
	@Column(name = "joiningdate")
	private Date joiningDate; 
	
	@Column(name = "relievingdate")
	private Date relievingDate; 
}
