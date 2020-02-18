package com.techtrail.commons.auth.security;


import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.techtrail.commons.auth.dao.AuthenticationManagerDao;
import com.techtrail.commons.auth.model.User;



@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private AuthenticationManagerDao userDao;
    
    @Autowired
    private HttpServletRequest request;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String loginUser) {

        log.debug("Authenticating {}", loginUser);
        //String lowercaseLogin = login.toLowerCase();

        User userFromDatabase = userDao.findUserByEmailId(loginUser); 
        
    	

        if (userFromDatabase == null) {
            throw new UsernameNotFoundException("User " + loginUser + " was not found in the database");
        } else if (!userFromDatabase.getStatus().equals(User.STATUS_ACTIVE)) {
				throw new UserNotActivatedException("User " + loginUser + " is deactivated.");				
			
        }
        
        
      //  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	//User usr = (User) auth.getPrincipal();
    	//request.getSession().setAttribute("loginUser", userFromDatabase);
        
        /*----Role Mapping----*/
        //EmpRoleMapping roleMap = userFromDatabase.getEmpRoleMapping();
        String role =  userFromDatabase.getUserType();
        
        
        
        /*Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
        grantedAuthorities.add(grantedAuthority);*/
        
       /* Authorities usrRole = Authorities.valueOf(role);
        
        switch (usrRole) {
		case ROLE_TECH:
			throw new UserNotAuthorized("**YOU ARE NOT AUTHORIZED TO ACCESS THIS SYSTEM**");
		case ROLE_COORDI:
			throw new UserNotAuthorized("**YOU ARE NOT AUTHORIZED TO ACCESS THIS SYSTEM**");
		default:
			break;
		}*/
        
       Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        
        boolean accountISEnable = userFromDatabase.getStatus().equals(User.STATUS_ACTIVE) ? true : false;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
        /*UserDetails userDetails = new org.springframework.security.core.userdetails.User(
        		login, userFromDatabase.getPassword(), accountISEnable,
				accountNonExpired, credentialsNonExpired, accountNonLocked,
				grantedAuthorities);*/
        userFromDatabase.setAccountNonExpired(accountNonExpired);
        userFromDatabase.setAccountNonLocked(accountNonLocked);
        userFromDatabase.setCredentialsNonExpired(credentialsNonExpired);
        userFromDatabase.setEnabled(accountISEnable);
        userFromDatabase.setAuthorities(authorities);
        userFromDatabase.setUsername(loginUser);
		
        	return userFromDatabase;
        
        //return new org.springframework.security.core.userdetails.User(userFromDatabase.getMobileNo(), userFromDatabase.getPassword(), grantedAuthorities);

    }

}
