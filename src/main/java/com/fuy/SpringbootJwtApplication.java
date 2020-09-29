package com.fuy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fuy.dao")
public class SpringbootJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJwtApplication.class, args);
	}

}
