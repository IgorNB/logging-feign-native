package com.lig.feignlogging.config;

import com.lig.feignlogging.util.CustomLogger;
import feign.Logger;
import feign.Logger.Level;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FooConfiguration {

	@Bean
	public Logger feignLogger()  {
		return new CustomLogger();
	}

}
