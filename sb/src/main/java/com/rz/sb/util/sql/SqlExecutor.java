package com.rz.sb.util.sql;

import java.util.HashMap;
import java.util.Map;

public class SqlExecutor {
	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<String, Object>();
		//params.put("name", "rzy");
		SqlPara sqlPara = SqlLoader.getSql("user.list", params);
		System.out.println(sqlPara);
	}
}
