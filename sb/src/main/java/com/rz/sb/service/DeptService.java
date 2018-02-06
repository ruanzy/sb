package com.rz.sb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rz.sb.dao.DeptDao;

@Service
public class DeptService {
	@Autowired
	private DeptDao deptDao;

	public List<Map<String, Object>> tree() {
		return deptDao.tree();
	}

	public void add(Map<String, Object> param) {
		deptDao.add(param);
	}

	public List<Map<String, Object>> getPermission(String id) {
		return deptDao.getPermission(id);
	}

	public void setPermission(String deptid, String permissions) {
		deptDao.setPermission(deptid, permissions);
	}

	public void del(String id) {
		boolean isByUser = deptDao.isByUser(id);
		if(!isByUser){
			deptDao.del(id);
		}
	}
}