package com.rz.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rz.sb.service.ResourceService;

@RequestMapping("resource")
@RestController
public class ResourceController {
	@Autowired
	private ResourceService resourceService;

	@RequestMapping("/list")
	public Object list() {
		return resourceService.list();
	}
}
