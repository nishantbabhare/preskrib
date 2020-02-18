package com.techtrail.commons.auth.util;

public class IntegerOtpGenerator {
	
	public static int generateOtp() 
	{   
		int otpNo = ((int)(Math.random()*9000)+1000);	    
	    return otpNo;
	}

}
