package com.esmt.microservices.userauthentificationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.esmt.microservices.userauthentificationservice.bean.User;
import com.esmt.microservices.userauthentificationservice.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository repo;

	@GetMapping("/user-authentification-service/login/{login}/password/{password}")
	public boolean authenticate(@PathVariable String login, @PathVariable String password) {

		User user = repo.findByLoginAndPassword(login, password);
		if (user == null)
			return false;
		return true;
	}
}
