package ru.netunix.webservice;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@Log4j2
public class WebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebserviceApplication.class, args);
		log.info("Simple log statement with input {}", "test message");
		log.info("my.param = ["+System.getProperty("my.param")+"]");

	}


}