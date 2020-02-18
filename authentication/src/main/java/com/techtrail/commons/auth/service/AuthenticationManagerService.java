package com.techtrail.commons.auth.service;

import java.util.List;

import com.techtrail.commons.auth.dto.UserDto;
import com.techtrail.commons.auth.dto.form.UserForm;
import com.techtrail.commons.auth.model.User;



public interface AuthenticationManagerService {
	

	public UserDto getUser(String email,String password);

	public List<User> getAllUser();

	public void saveUser(UserForm user);
	
	/*public UserDetails loadUserByUsername(String username)*/
}
