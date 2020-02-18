package com.techtrail.commons.auth.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;


import com.techtrail.commons.auth.dto.UserDto;
import com.techtrail.commons.auth.dto.UserEntityDto;
import com.techtrail.commons.auth.model.User;
import com.techtrail.commons.db.dao.Dao;



public interface AuthenticationManagerDao extends Dao {
	
	public UserDto findUserById(String email,String pswd);
	
	List<User> findAllUser();
	
	User findUserByEmailId(String email);
	
	public void updateUser(String email,String pswd);
	
	public User getOtpForUser(String email);
	
	public String verifyOtpForUserId(String otp, String email);
	
	public void setNewOTP(String email, String otp);
	
	UserEntityDto findUserEntityDetailsById(Integer userId);
	
	/////////////
	
	public User findUserByMobile(String mobileNo);
	
	public void updateOtpForUser(User employee, int otp);
	
	public Integer getOtpForEmployee(User employee);
	
}
