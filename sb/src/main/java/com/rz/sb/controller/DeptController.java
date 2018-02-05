package com.rz.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rz.sb.service.DeptService;

@RequestMapping("dept")
@RestController
public class DeptController {
	@Autowired
	private DeptService deptService;

	@RequestMapping("/tree")
	public Object list() {
		return deptService.tree();
	}
}
