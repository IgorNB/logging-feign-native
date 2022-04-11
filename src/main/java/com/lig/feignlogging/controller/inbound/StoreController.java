package com.lig.feignlogging.controller.inbound;

import com.lig.feignlogging.controller.StoreApi;
import com.lig.feignlogging.dto.Store;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class StoreController implements StoreApi {

	@Override
	public List<Store> getStores() {
		return List.of(
				new Store().setAddress("random adress" + UUID.randomUUID()),
				new Store().setAddress("random adress" + UUID.randomUUID())
		);
	}

}
