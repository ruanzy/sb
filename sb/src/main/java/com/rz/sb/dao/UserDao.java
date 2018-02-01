package com.rz.sb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rz.sb.sql.SqlLoader;
import com.rz.sb.sql.SqlPara;

@Repository
public class UserDao {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate1;

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate2;

	public List<Map<String, Object>> list() {
		String sql = "select * from users";
		List<Map<String, Object>> list = jdbcTemplate1.queryForList(sql);
		return list;
	}
	
	public List<Map<String, Object>> list22() {
		String sql = "select * from users";
		List<Map<String, Object>> list = jdbcTemplate1.queryForList(sql);
		return list;
	}

	public List<Map<String, Object>> list2() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "sam");
		SqlPara sqlPara = SqlLoader.getSql("user.list", params);
		String sql = sqlPara.getSql();
		Object[] args = sqlPara.getPara();
		List<Map<String, Object>> list = jdbcTemplate1.queryForList(sql, args);
		return list;
	}

	public Object projects() {
		String sql = "select PO_TYPE as id, DESCRIPTION as text from PURCHASE_ORDER_TYPE order by PO_TYPE";
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	public Map<String, Object> findByAccount(String account) {
		String sql = "select * from users where username=?";
		Map<String, Object> u = jdbcTemplate1.queryForMap(sql, account);
		return u;
	}
}
