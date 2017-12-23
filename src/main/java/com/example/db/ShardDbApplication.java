package com.example.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ShardDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardDbApplication.class, args);
	}
}
