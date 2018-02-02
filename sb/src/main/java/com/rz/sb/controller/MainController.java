package com.rz.sb.controller;

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
	
}
