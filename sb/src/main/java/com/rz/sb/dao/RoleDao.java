package com.rz.sb.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate1;

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate2;

	public Map<String, Object> list() {
		Map<String, Object> ret = new HashMap<String, Object>();
		String sql1 = "select count(*) from role";
		String sql2 = "select * from role";
		Integer total = jdbcTemplate1.queryForObject(sql1, Integer.class);
		List<Map<String, Object>> list = null;
		if (total > 0) {
			list = jdbcTemplate1.queryForList(sql2);
		}
		ret.put("total", total);
		ret.put("data", list);
		return ret;
	}
	
	public void add(Map<String, Object> o)
	{
		Object rolename = o.get("rolename");
		String sql = "insert into role(name) values(?)";
		jdbcTemplate1.update(sql, rolename);
	}
	
	public boolean exist(String name)
	{
		String sql = "select count(*) from role where name=?";
		Integer c = jdbcTemplate1.queryForObject(sql, new Object[]{ name }, Integer.class);
		return c > 0;
	}
	
	public void del(String id)
	{
		String sql1 = "delete from userrole where roleid = ?";
		String sql2 = "delete from roleres where roleid = ?";
		String sql3 = "delete from role where id = ?";
		jdbcTemplate1.update(sql1, id);
		jdbcTemplate1.update(sql2, id);
		jdbcTemplate1.update(sql3, id);
	}
	
	public void setPermission(String id, String permissions)
	{
		String[] arr = permissions.split(",");
		String sql1 = "delete from roleres where roleid = ?";
		String sql2 = "insert into roleres values(?, ?)";
		jdbcTemplate1.update(sql1, id);
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		for (String str : arr)
		{
			batchArgs.add(new Object[]{ id, str });
		}
		jdbcTemplate1.batchUpdate(sql2, batchArgs);
	}

	public List<Map<String, Object>> getPermission(String id)
	{
		String sql = "select r.*, exists (select 1 from roleres where roleid=? and resid=r.id) as checked from resources r";
		return jdbcTemplate1.queryForList(sql, id);
	}
}
