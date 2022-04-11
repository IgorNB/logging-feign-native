package com.lig.feignlogging;

import com.lig.feignlogging.controller.StoreApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackageClasses = StoreApi.class)
@SpringBootApplication
public class FeignLoggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignLoggingApplication.class, args);
	}

}
