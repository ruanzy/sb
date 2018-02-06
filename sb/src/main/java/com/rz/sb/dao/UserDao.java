package com.rz.sb.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

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
	
	public void add(Map<String, Object> o)
	{
		Object name = o.get("name");
		Object password = o.get("password");
		String _password = DigestUtils.md5DigestAsHex(password.toString().getBytes());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		Object deptid = o.get("deptid");
		String sql = "insert into users(name,password,depart,status,regtime) values(?,?,?,1,?)";
		jdbcTemplate1.update(sql, name, _password, deptid, time);
	}
	
	public boolean exist(String name)
	{
		String sql = "select count(*) from users where name=?";
		Integer c = jdbcTemplate1.queryForObject(sql, new Object[]{ name }, Integer.class);
		return c > 0;
	}
	
	public void del(String id)
	{
		String sql1 = "delete from userrole where userid = ?";
		String sql2 = "delete from users where name = ?";
		jdbcTemplate1.update(sql1, id);
		jdbcTemplate1.update(sql2, id);
	}
	
	public void setRoles(String id, String roles)
	{
		String[] arr = roles.split(",");
		String sql1 = "delete from userrole where userid = ?";
		String sql2 = "insert into userrole values(?, ?)";
		jdbcTemplate1.update(sql1, id);
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		for (String str : arr)
		{
			batchArgs.add(new Object[]{ id, str });
		}
		jdbcTemplate1.batchUpdate(sql2, batchArgs);
	}

	public List<Map<String, Object>> getRoles(String id)
	{
		String sql = "select r.id, r.name, exists (select 1 from userrole where userid=? and roleid=r.id) as checked from role r";
		return jdbcTemplate1.queryForList(sql, id);
	}

	public Object projects() {
		String sql = "select PO_TYPE as id, DESCRIPTION as text from PURCHASE_ORDER_TYPE order by PO_TYPE";
		List<Map<String, Object>> list = jdbcTemplate2.queryForList(sql);
		return list;
	}

	public Map<String, Object> findByName(String name) {
		String sql = "select * from users where name=?";
		List<Map<String, Object>> list = jdbcTemplate1.queryForList(sql, name);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	public void resetpwd(String id, String password)
	{
		String sql = "update users set password=? where name=?";
		String _password = DigestUtils.md5DigestAsHex(password.toString().getBytes());
		jdbcTemplate1.update(sql, _password, id);
	}
}
