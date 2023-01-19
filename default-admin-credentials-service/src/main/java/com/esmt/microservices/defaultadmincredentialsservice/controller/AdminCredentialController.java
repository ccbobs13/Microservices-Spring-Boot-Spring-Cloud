package com.esmt.microservices.defaultadmincredentialsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esmt.microservices.defaultadmincredentialsservice.bean.AdminCredential;
import com.esmt.microservices.defaultadmincredentialsservice.configuration.Configuration;

@RestController
public class AdminCredentialController {

	@Autowired 
	private Configuration configuration;
	
	@GetMapping("/admincredential")
	public AdminCredential getAdminCredential() {
		return new AdminCredential(configuration.getLogin(),configuration.getPassword());
	}
}
