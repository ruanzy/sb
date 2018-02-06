package com.rz.sb.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Dept implements RowMapper<Dept>, Serializable {

	private static final long serialVersionUID = 8809101560720973267L;

	private Integer id;

	private String name;

	private Integer pid;

	private Integer deep;

	private Integer isparent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getDeep() {
		return deep;
	}

	public void setDeep(Integer deep) {
		this.deep = deep;
	}

	public Integer getIsparent() {
		return isparent;
	}

	public void setIsparent(Integer isparent) {
		this.isparent = isparent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Dept mapRow(ResultSet rs, int rowNum) throws SQLException {
		Dept o = new Dept();
		o.setId(rs.getInt("id"));
		o.setName(rs.getString("name"));
		o.setPid(rs.getInt("pid"));
		o.setDeep(rs.getInt("deep"));
		return o;
	}
}