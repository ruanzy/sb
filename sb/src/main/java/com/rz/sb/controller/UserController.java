package com.rz.sb.controller;

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
}
