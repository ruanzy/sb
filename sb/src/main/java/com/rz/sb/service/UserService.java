package com.rz.sb.service;

import java.util.List;
import java.util.Map;

import com.rz.sb.entity.User;

public interface UserService {
	List<User> list();

	List<Map<String, Object>> list2();
}
