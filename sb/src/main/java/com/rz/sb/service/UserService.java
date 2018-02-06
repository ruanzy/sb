package com.rz.sb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rz.sb.dao.UserDao;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public Map<String, Object> list() {
		return userDao.list();
	}
	
	public void add(Map<String, Object> o)
	{
		userDao.add(o);
	}
	
	public boolean exist(String name)
	{
		return userDao.exist(name);
	}
	
	public void del(String id)
	{
		userDao.del(id);
	}
	
	public void setRoles(String id, String roles)
	{
		userDao.setRoles(id, roles);
	}

	public List<Map<String, Object>> getRoles(String id)
	{
		return userDao.getRoles(id);
	}

	public Object projects() {
		return userDao.projects();
	}
	
	public Map<String, Object> findByName(String name) {
		return userDao.findByName(name);
	}
	
	public void resetpwd(String id, String password)
	{
		userDao.resetpwd(id, password);
	}
}