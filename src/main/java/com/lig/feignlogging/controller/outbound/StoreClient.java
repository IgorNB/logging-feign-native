package com.lig.feignlogging.controller.outbound;

import com.lig.feignlogging.controller.StoreApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "stores", url = "http://localhost:8080")
public interface StoreClient extends StoreApi {

}
