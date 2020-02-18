package com.techtrail.commons.auth.serviceImpl;

import java.util.List;
import java.util.Random;

import javax.security.auth.login.CredentialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techtrail.commons.auth.dao.AuthenticationManagerDao;
import com.techtrail.commons.auth.dto.UserDto;
import com.techtrail.commons.auth.dto.form.UserForm;
import com.techtrail.commons.auth.model.User;
import com.techtrail.commons.auth.service.AuthenticationManagerService;



@Service
public class AuthenticationManagerServiceImpl implements AuthenticationManagerService{

	@Autowired
	private AuthenticationManagerDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> getAllUser() {
		return userDao.findAllUser();
	}

	@Override
	public void saveUser(UserForm user) {
		
		Integer otp = Integer.parseInt(getNewOTP());
		User userdata = new User();
		
		userdata.setUserId(user.getUserId());
		userdata.setFirstName(user.getFirstName());
		userdata.setLastName(user.getLastName());
		userdata.setEmail(user.getEmail());
		userdata.setMobile(user.getMobile());
		userdata.setStatus(user.getStatus());
		userdata.setOtp(otp);
		//userdata.setEmpId(user.getEmpId());
		userdata.setUserType(user.getUserType());
		userDao.saveOrUpdate(userdata);
	}

	@Override
	public UserDto getUser(String email, String password) {
		
		String pswd = passwordEncoder.encode(password);
		UserDto usr = userDao.findUserById(email, pswd);
		System.out.println(usr);
		if( passwordEncoder.matches(password, usr.getPassword())){
			return usr;
		} else {
			throw new RuntimeException(new CredentialException("Invalid user-id or password"));
		}
    }
	
	private String getNewOTP(){
		Random random = new Random();
		return String.format("%04d", random.nextInt(10000));
	}
}
