package com.rz.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.rz.sb.util.ds.DynamicDataSourceRegister;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Import({DynamicDataSourceRegister.class})
public class Application
{

	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}
}
