package com.techtrail.commons.auth.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserEntityDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userid;
	private String usertype;
	private Integer entityid;
	private String entitytype;

}
