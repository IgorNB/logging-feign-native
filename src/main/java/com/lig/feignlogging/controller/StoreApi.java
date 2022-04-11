package com.lig.feignlogging.controller;

import com.lig.feignlogging.dto.Store;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

public interface StoreApi {

	@RequestMapping(method = RequestMethod.GET, value = "/stores")
	List<Store> getStores();
}
