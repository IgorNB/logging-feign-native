package com.lig.feignlogging;

import com.lig.feignlogging.controller.outbound.StoreClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class FeignLoggingApplicationTests {

	@Autowired
	private StoreClient storeClient;

	@Test
	void contextLoads() {
		storeClient.getStores();
		storeClient.getStores();
	}

}
