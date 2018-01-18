package com.rz.sb.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.rz.sb.entity.User;
import com.rz.sb.util.sql.SqlLoader;
import com.rz.sb.util.sql.SqlPara;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<User> list() {
		String sql = "select id, name, age from users";
		List<User> list = jdbcTemplate.query(sql, new UserRowMapper());
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

class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet resultSet, int i) throws SQLException {
		User user = new User();
		user.setId(resultSet.getInt("id"));
		user.setName(resultSet.getString("name"));
		user.setAge(resultSet.getInt("age"));
		return user;
	}

}