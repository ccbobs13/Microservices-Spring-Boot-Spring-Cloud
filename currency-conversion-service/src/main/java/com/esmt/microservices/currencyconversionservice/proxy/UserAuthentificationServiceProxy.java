package com.esmt.microservices.currencyconversionservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-authentification-service")
public interface UserAuthentificationServiceProxy {

	@GetMapping("/user-authentification-service/login/{login}")
	public boolean authenticate(@PathVariable String login);

}
