package com.rz.sb.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

public class SqlExecutor {
	
	@Autowired
	@Qualifier("primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate1;

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate2;
	
	public Map<String, Object> pageList(String countSql, String pageSql, Map<String, Object> params) {
		Map<String, Object> ret = new HashMap<String, Object>();
		SqlPara sqlPara1 = SqlLoader.getSql(countSql, params);
		String sql1 = sqlPara1.getSql();
		Object[] args1 = sqlPara1.getPara();
		Long total = jdbcTemplate1.queryForObject(sql1, args1, Long.class);
		SqlPara sqlPara2 = SqlLoader.getSql(pageSql, params);
		String sql2 = sqlPara2.getSql();
		Object[] args2 = sqlPara2.getPara();
		List<Map<String, Object>> list = jdbcTemplate1.queryForList(sql2, args2);
		ret.put("total", total);
		ret.put("data", list);
		return ret;
	}
}
