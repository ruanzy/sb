package com.rz.sb.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DeptDao {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate1;

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate2;

	public List<Map<String, Object>> tree() {
		String sql = "select * from dept";
		List<Map<String, Object>> list = jdbcTemplate1.queryForList(sql);
		return list;
	}

	public void add(Map<String, Object> param) {
		String sql = "insert into dept(name, deep, pid, isparent) values(?,?,?,?)";
		Integer plevel = Integer.valueOf(param.get("plevel").toString());
		jdbcTemplate1.update(sql, param.get("name"), plevel + 1,
				param.get("pid"), param.get("isparent"));
	}

	public List<Map<String, Object>> getPermission(String id) {
		String sql = "select r.*, exists (select 1 from deptres where deptid=? and resid=r.id) as checked from resources r";
		return jdbcTemplate1.queryForList(sql, id);
	}

	public void setPermission(String deptid, String permissions) {
		String[] arr = permissions.split(",");
		String sql1 = "delete from deptres where deptid = ?";
		String sql2 = "insert into deptres values(?, ?)";
		jdbcTemplate1.update(sql1, deptid);
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		for (String str : arr) {
			batchArgs.add(new Object[] { deptid, str });
		}
		jdbcTemplate1.batchUpdate(sql2, batchArgs);
	}

	public boolean isByUser(String id) {
		String sql = "select count(1) from users where depart in (select id from dept where pid=? or id=?)";
		Integer c = jdbcTemplate1.queryForObject(sql, new Object[] { id, id },
				Integer.class);
		return c > 0;
	}

	public void del(String id) {
		String sql1 = "delete from deptres where deptid = ?";
		String sql2 = "delete from dept where id = ?";
		jdbcTemplate1.update(sql1, id);
		jdbcTemplate1.update(sql2, id);
	}
}
