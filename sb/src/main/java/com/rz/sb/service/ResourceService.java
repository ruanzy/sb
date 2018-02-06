package com.rz.sb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rz.sb.dao.ResourceDao;

@Service
public class ResourceService {
	@Autowired
	private ResourceDao resourceDao;

	public List<Map<String, Object>> list() {
		return resourceDao.list();
	}
}
