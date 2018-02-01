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

	public List<Map<String, Object>> list() {
		return userDao.list();
	}
	
	public List<Map<String, Object>> list22() {
		return userDao.list22();
	}

	public List<Map<String, Object>> list2() {
		return userDao.list2();
	}

	public Object projects() {
		return userDao.projects();
	}
	
	public Map<String, Object> findByAccount(String account) {
		return userDao.findByAccount(account);
	}
}