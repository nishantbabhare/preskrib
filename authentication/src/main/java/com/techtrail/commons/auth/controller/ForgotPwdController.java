package com.techtrail.commons.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techtrail.commons.auth.exception.EmailDoesntExistException;
import com.techtrail.commons.auth.rest.response.OtpResponse;
import com.techtrail.commons.auth.service.ForgotPwdService;


@RestController
@RequestMapping("forgotpassword")
public class ForgotPwdController {
	
	@Autowired
	private ForgotPwdService forgotPwdService;
	
	private HttpSession session;
	
	@RequestMapping(method = RequestMethod.POST)
	public void signup(HttpServletRequest request, @RequestParam("email") String email) throws EmailDoesntExistException {
		session = request.getSession();
		forgotPwdService.sendForgotPwdOtp(email, session);
	}
	
	@RequestMapping( value = "/verify", method = RequestMethod.POST)
	public OtpResponse verifyOtp(@RequestParam("otp") int otp, @RequestParam("email") String email) {				
		return forgotPwdService.verifyForgotPwdOtp(otp, email, session);
	}
	
	@RequestMapping( value = "/resetpwd", method = RequestMethod.POST)
	public void resetPassword(@RequestParam("password") String pwd, @RequestParam("cpassword") String cpwd) {				
		forgotPwdService.saveNewPassword(pwd, cpwd, session);
	}
	
}