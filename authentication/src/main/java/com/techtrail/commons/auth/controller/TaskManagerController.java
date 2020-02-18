package com.techtrail.commons.auth.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.techtrail.commons.auth.dto.UserDto;
import com.techtrail.commons.auth.dto.form.UserForm;
import com.techtrail.commons.auth.model.User;
import com.techtrail.commons.auth.service.AuthenticationManagerService;

@RestController
public class TaskManagerController {

	@Autowired
	private AuthenticationManagerService userService;
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getUserInfo(@RequestBody UserDto userData,HttpServletRequest request) {
		String email = userData.getEmail();
		String password = userData.getPassword();
		UserDto user = userService.getUser(email, password);
		System.out.println(user);
		
	 	if (user != null) {
	 		System.out.println(email);
			ModelAndView model = new ModelAndView("home");
			HttpSession session = request.getSession();
			return model;
		} else {
			ModelAndView model = new ModelAndView("invalid");
			return model;
		}
	}
   
    @PostMapping(value = "/users")
	public void saveUser(@RequestBody UserForm userdto){
			/*BindingResult bindingResult) throws ParameterValidationException {
		if (bindingResult != null && bindingResult.hasErrors()) {
			throw new ParameterValidationException(bindingResult);
		}*/
		userService.saveUser(userdto);	
	}

	@GetMapping(value = "/users")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}
}	
