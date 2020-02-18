package com.techtrail.commons.auth.dto.form;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserForm {
	
	private Integer userId;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String password;
	private Integer otp;
	private String userType;
    private String status;
    private String empId;
    
}
