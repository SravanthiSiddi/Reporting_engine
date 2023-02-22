package com.jasper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jasper.*")
public class DynamicReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicReportApplication.class, args);
	}

}
