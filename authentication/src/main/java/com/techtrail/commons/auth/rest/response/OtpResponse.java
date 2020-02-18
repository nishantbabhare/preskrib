package com.techtrail.commons.auth.rest.response;

public class OtpResponse {
		
	private boolean otpMatches;
	
	/**
	 * @return the otpMatches
	 */
	public boolean isOtpMatches() {
		return otpMatches;
	}

	/**
	 * @param otpMatches the otpMatches to set
	 */
	public void setOtpMatches(boolean otpMatches) {
		this.otpMatches = otpMatches;
	}
}
