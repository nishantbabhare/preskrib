package com.techtrail.commons.auth.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.techtrail.commons.auth.dao.AuthenticationManagerDao;
import com.techtrail.commons.auth.dto.UserEntityDto;
import com.techtrail.commons.auth.model.User;

public class CustomTokenEnhancer implements TokenEnhancer {
	@Autowired
	AuthenticationManagerDao authenticationManagerDao;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User user = (User) authentication.getPrincipal(); 
        
        UserEntityDto entityDetails =  authenticationManagerDao.findUserEntityDetailsById(user.getUserId());
         
        final Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("userId",  user.getUserId());
        additionalInfo.put("firstName", user.getFirstName());
        additionalInfo.put("lastName", user.getLastName());
        additionalInfo.put("authorities", user.getAuthorities());
        additionalInfo.put("userEntityId", user.getEntityId());
        additionalInfo.put("userType", user.getUserType());
        additionalInfo.put("entityType", entityDetails.getEntitytype()); 
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }

}