package com.rz.sb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rz.sb.service.RoleService;
import com.rz.sb.util.Result;

@RequestMapping("role")
@RestController
public class RoleController {
	@Autowired
	private RoleService roleService;

	@RequestMapping("/list")
	public Object list() {
		return roleService.list();
	}
	
	@RequestMapping("/add")
	public Object add(@RequestParam Map<String, Object> param) {
		roleService.add(param);
		return new Result(true, "add");
	}
	
	@RequestMapping("/exist")
	public boolean exist(String rolename)
	{
		return roleService.exist(rolename);
	}
	
	@RequestMapping("/del")
	public Object del(String id) {
		roleService.del(id);
		return new Result(true, "del");
	}
	
	@RequestMapping("/getPermission")
	public Object getPermission(String roleid) {
		return roleService.getPermission(roleid);
	}
	
	@RequestMapping("/setPermission")
	public Object setPermission(String roleid, String permissions) {
		roleService.setPermission(roleid, permissions);
		return new Result(true, "setPermission");
	}
}
