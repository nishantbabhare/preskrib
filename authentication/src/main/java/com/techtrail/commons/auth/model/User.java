package com.techtrail.commons.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.techtrail.commons.auth.security.SpringSecurityUser;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User extends SpringSecurityUser implements Serializable {


	private static final long serialVersionUID = 1L;

	public static final String STATUS_ACTIVE = "Active";
	public static final String STATUS_DEACTIVE = "Deactive";
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid", updatable = false, nullable = false, unique = true)
	private Integer userId;
	
	@Column(name = "firstname")
	@Size(min=0, max= 100)
	private String firstName;
	
	@Column(name = "lastname")
	@Size(min=0, max= 100)
	private String lastName;
	
	@Size(min=0, max= 20)
	private String mobile;
	
	@Size(min=0, max= 100)
	private String email;
	
	//@Size(min=0, max= 20)
	private Integer otp;
	
	@Size(min=0, max= 500)
	private String password;
	
	@Size(min=0, max= 20)
	private String status;
	
	@Column(name = "usertype")
	@Size(min=0, max= 20)
	private String userType;
	
	@Column(name = "entityid")
	private Integer entityId;
	
}
