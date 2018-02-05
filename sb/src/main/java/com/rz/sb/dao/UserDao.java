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

	public Map<String, Object> list() {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		SqlPara sqlPara1 = SqlLoader.getSql("user.count", params);
		String sql1 = sqlPara1.getSql();
		Object[] args1 = sqlPara1.getPara();
		Long total = jdbcTemplate1.queryForObject(sql1, args1, Long.class);
		SqlPara sqlPara2 = SqlLoader.getSql("user.list", params);
		String sql2 = sqlPara2.getSql();
		Object[] args2 = sqlPara2.getPara();
		List<Map<String, Object>> list = jdbcTemplate1.queryForList(sql2, args2);
		ret.put("total", total);
		ret.put("data", list);
		return ret;
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
		String sql = "select * from users where account=?";
		List<Map<String, Object>> list = jdbcTemplate1.queryForList(sql, account);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
