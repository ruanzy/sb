package com.rz.sb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rz.sb.util.ds.TargetDataSource;
import com.rz.sb.util.sql.SqlLoader;
import com.rz.sb.util.sql.SqlPara;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> list() {
		String sql = "select * from users";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	@TargetDataSource("d2")
	public List<Map<String, Object>> list22() {
		String sql = "select * from users";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	public List<Map<String, Object>> list2() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "sam");
		SqlPara sqlPara = SqlLoader.getSql("user.list", params);
		String sql = sqlPara.getSql();
		Object[] args = sqlPara.getPara();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
		return list;
	}
}
