package com.rz.sb.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rz.sb.service.UserService;

@Controller
public class MainController
{

	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
    public String index() {
        return "forward:/index.html";
    }
	
	@RequestMapping("/login")
	public String login(String account, String password) {
	    if (account == null || password == null) {
	        return "Please fill in username and password";
	    }
	    Map<String, Object> user = userService.findByAccount(account);
	    if(user == null){
	    	return "User " + account + " not found";
	    }
	    String pwd = user.get("password").toString();
	    if (!password.equals(pwd)) {
	    	return "Invalid login. Please check your name and password";
	    }
	    return Jwts.builder().setSubject(account).claim("roles", "user").setIssuedAt(new Date())
	            .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
    }
}
