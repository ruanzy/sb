package com.rz.sb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rz.sb.dao.RoleDao;

@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao;

	public Map<String, Object> list() {
		return roleDao.list();
	}
	
	public void add(Map<String, Object> o)
	{
		roleDao.add(o);
	}
	
	public boolean exist(String name)
	{
		return roleDao.exist(name);
	}
	
	public void del(String id)
	{
		roleDao.del(id);
	}
	
	public void setPermission(String id, String permissions)
	{
		roleDao.setPermission(id, permissions);
	}

	public List<Map<String, Object>> getPermission(String id)
	{
		return roleDao.getPermission(id);
	}
}