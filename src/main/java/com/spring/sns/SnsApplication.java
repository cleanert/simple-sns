package com.spring.sns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.sql.DataSource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnsApplication.class, args);
	}

}
