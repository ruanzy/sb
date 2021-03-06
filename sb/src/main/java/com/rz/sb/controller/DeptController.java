package com.rz.sb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rz.sb.service.DeptService;
import com.rz.sb.util.Result;

@RequestMapping("dept")
@RestController
public class DeptController {
	@Autowired
	private DeptService deptService;

	@RequestMapping("/tree")
	public Object list() {
		return deptService.tree();
	}
	
	@RequestMapping("/add")
	public Object add(@RequestParam Map<String, Object> param) {
		deptService.add(param);
		return new Result(true, "add");
	}
	
	@RequestMapping("/del")
	public Object del(String id) {
		deptService.del(id);
		return new Result(true, "del");
	}
	
	@RequestMapping("/getPermission")
	public Object getPermission(String id) {
		return deptService.getPermission(id);
	}
	
	@RequestMapping("/setPermission")
	public Object setPermission(String id, String permissions) {
		deptService.setPermission(id, permissions);
		return new Result(true, "setPermission");
	}
}
