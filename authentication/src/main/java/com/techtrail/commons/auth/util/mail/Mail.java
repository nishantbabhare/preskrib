package com.techtrail.commons.auth.util.mail;

import java.io.UnsupportedEncodingException;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Mail {
	
	    private Mail(){
	     }

	    @Value("${email.from}")
		private String from;
	    
		@Value("${email.host}")
		private String host;
		
		@Value("${email.port}")
		private String port;	
		
		@Value("${email.username}")
		private String username;
		
		@Value("${email.password}")
		private String password;
		
		@Value("${email.smtp.auth}")
		private String auth;
		
		@Value("${email.smtp.starttls.enable}")
		private String starttls;

		private static Mail instance = null;
		public static Mail getInstance() {
			if (instance == null)
				instance = new Mail();
			return instance;
		}
	
	
    public void send(String email,Integer otp) throws UnsupportedEncodingException{
       
        String message1 = "<i>Dear sir ,</i><br>";
        message1 += "<font color=red><b>Your OTP is </b></font>" +(otp);
        
       
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", starttls);
        
      /*  props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");*/
       
        // get Session
        Session session = Session.getInstance(props,
        		new javax.mail.Authenticator() {
        			protected PasswordAuthentication getPasswordAuthentication() {
        				return new PasswordAuthentication(from, password);
        				
        			}
        	}
        );
        MimeMessage message = new MimeMessage(session);
               
        try {
        		String toEmails = email;
        		InternetAddress toAddrs = new InternetAddress(toEmails);
        		/*int i=0;
        		for(String addr : toEmails){
        			toAddrs[i++] = new InternetAddress(addr);
        		}*/
                //message.addRecipients(Message.RecipientType.TO, toAddrs);
        		message.addRecipient(Message.RecipientType.TO, toAddrs);
           }
        catch (MessagingException e) {
                throw new RuntimeException(e);
        	}
		try {
			message.setSubject("OTP for changing Password");
			}
			catch (MessagingException e) {
					e.printStackTrace();
				}
		try {
			message.setContent(message1, "text/html");
			// send message
			Transport.send(message);
			System.out.println("message sent successfully");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    }
    

}
