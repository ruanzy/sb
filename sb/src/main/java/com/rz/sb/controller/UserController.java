package com.rz.sb.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rz.sb.service.UserService;

@RequestMapping("user")
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/list")
	public Object list() {
		return userService.list();
	}
	
	@RequestMapping("/list2")
	public Object list2() {
		return userService.list2();
	}
	
	@RequestMapping("/list22")
	public Object list22() {
		return userService.list22();
	}
	
	@RequestMapping("/projects")
	public Object projects() {
		return userService.projects();
	}
	
	@RequestMapping("/login")
	public Object login(String account, String password) {
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
	    String token = Jwts.builder().setSubject(account).claim("roles", "user").setIssuedAt(new Date())
        .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
	    Map<String, Object> m = new HashMap<String, Object>();
	    m.put("token", token);
	    
	    return m;
    }
}
