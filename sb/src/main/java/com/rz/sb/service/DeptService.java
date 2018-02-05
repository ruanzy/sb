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
}