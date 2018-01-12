package com.rz.sb;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

@Component
public class StarterRunner implements CommandLineRunner {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... arg0) throws Exception {
		String sql = "SELECT COUNT(1) FROM  information_schema.tables where table_schema='PUBLIC' and table_name='USERS'";
		Integer c = jdbcTemplate.queryForObject(sql, Integer.class);
		if (c == 0) {
			DataSource ds = jdbcTemplate.getDataSource();
			ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
			populator.addScript(new ClassPathResource("init.sql"));
			populator.populate(ds.getConnection());
		}
	}

}
