package com.techtrail.commons.auth.serviceImpl;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpSession;
import org.apache.http.HttpException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.techtrail.commons.auth.dao.AuthenticationManagerDao;
import com.techtrail.commons.auth.exception.EmailDoesntExistException;
import com.techtrail.commons.auth.model.User;
import com.techtrail.commons.auth.rest.response.OtpResponse;
import com.techtrail.commons.auth.service.ForgotPwdService;
import com.techtrail.commons.auth.util.IntegerOtpGenerator;
import com.techtrail.commons.auth.util.mail.Mail;




@Service
public class ForgotPwdServiceImpl implements ForgotPwdService {
	
	//@Autowired
	//private SMSSender smsSender;
	
	@Autowired
	private AuthenticationManagerDao authenticationManagerDao; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Mail mailSender;

	@Override
	public void sendForgotPwdOtp(String email, HttpSession session) throws EmailDoesntExistException {
		
		User user = authenticationManagerDao.findUserByEmailId(email);
		
		if(user == null)
			throw new EmailDoesntExistException("Email Id "+email+" does not exists. Kindly check the Email Id.");
		//String[] smsTo = { mobileNo };

		//String to = String.join(",", smsTo);
		
		int otp = IntegerOtpGenerator.generateOtp();
		
		session.setAttribute("user", user);
		
		authenticationManagerDao.updateOtpForUser(user, otp);
		
		try {
			mailSender.send(user.getEmail(), otp);
		    }   catch (UnsupportedEncodingException e) {
				e.printStackTrace();
		   }
		
		//smsSender.send(to, "Kindly use the OTP - "+ otp +" to reset your password.");
		System.out.println("OTP :: "+ otp);
	}

	@Override
	public OtpResponse verifyForgotPwdOtp(int otp, String email, HttpSession session) {
		OtpResponse response = new OtpResponse();
		
		User user = authenticationManagerDao.findUserByEmailId(email);
		if(user == null){
			response.setOtpMatches(false);
			return response;
		}
		
		Integer savedOtp = authenticationManagerDao.getOtpForEmployee(user);
		response.setOtpMatches(otp == savedOtp);
		return response;
	}
	
	@Override
	public void saveNewPassword(String password, String cpassword, HttpSession session){
		
		if(session == null){
			throw new RuntimeException(new HttpException("Invalid session. Please try again"));
		}
		if(password == null || cpassword == null)
			throw new NullPointerException("Password cannot be null or empty");
		
		if(!password.equals(cpassword))
			throw new RuntimeException("The passwords are not matching");
		
		User emp = (User) session.getAttribute("user");
		emp.setPassword(passwordEncoder.encode(password));
		authenticationManagerDao.update(emp);
	}
}
