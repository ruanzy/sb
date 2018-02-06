package com.rz.sb.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rz.sb.service.UserService;
import com.rz.sb.util.Result;

@RequestMapping("user")
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@ApiOperation(value="用户列表",notes = "查询用户列表", code = 200, produces = "application/json")
	@PostMapping("/list")
	public Object list() {
		return userService.list();
	}
	
	@ApiOperation(value="增加用户",notes = "增加用户", code = 200, produces = "application/json")
	@PostMapping("/add")
	public Object add(@RequestParam Map<String, Object> param) {
		userService.add(param);
		return new Result(true, "add");
	}
	
	@PostMapping("/exist")
	public boolean exist(String name)
	{
		return userService.exist(name);
	}
	
	@PostMapping("/del")
	public Object del(String id) {
		userService.del(id);
		return new Result(true, "del");
	}
	
	@PostMapping("/getRoles")
	public Object getRoles(String id) {
		return userService.getRoles(id);
	}
	
	@PostMapping("/setRoles")
	public Object setRoles(String id, String roles) {
		userService.setRoles(id, roles);
		return new Result(true, "setRole");
	}
	
	@PostMapping("/projects")
	public Object projects() {
		return userService.projects();
	}
	
	@PostMapping("/login")
	public Object login(String name, String password) {
	    if (name == null || password == null) {
	        return "Please fill in username and password";
	    }
	    Map<String, Object> user = userService.findByName(name);
	    if(user == null){
	    	return "User " + name + " not found";
	    }
	    String pwd = user.get("password").toString();
	    if (!password.equals(pwd)) {
	    	return "Invalid login. Please check your name and password";
	    }
	    String token = Jwts.builder().setSubject(name).claim("roles", "user").setIssuedAt(new Date())
        .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
	    Map<String, Object> m = new HashMap<String, Object>();
	    m.put("token", token);
	    
	    return m;
    }
	
	@PostMapping("/resetpwd")
	public Object resetpwd(String id, String password) {
		userService.resetpwd(id, password);
		return new Result(true, "resetpwd");
	}
}
