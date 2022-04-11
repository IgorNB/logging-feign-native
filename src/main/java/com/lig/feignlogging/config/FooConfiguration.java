package com.lig.feignlogging.config;


import feign.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.openfeign.FeignLogbookLogger;

@Configuration
public class FooConfiguration {

	@Bean
	public Logger feignLogger(@Autowired Logbook logbook)  {
		return new FeignLogbookLogger(logbook);
	}

}
