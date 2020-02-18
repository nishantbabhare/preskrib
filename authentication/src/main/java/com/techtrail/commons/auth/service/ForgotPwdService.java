package com.techtrail.commons.auth.service;

import javax.servlet.http.HttpSession;

import com.techtrail.commons.auth.exception.EmailDoesntExistException;
import com.techtrail.commons.auth.rest.response.OtpResponse;




public interface ForgotPwdService {
	

	
	public void sendForgotPwdOtp(String email, HttpSession session) throws EmailDoesntExistException;
	
	public OtpResponse verifyForgotPwdOtp(int otp, String email, HttpSession session);
	
	public void saveNewPassword(String password, String cpassword, HttpSession session);
	
}
