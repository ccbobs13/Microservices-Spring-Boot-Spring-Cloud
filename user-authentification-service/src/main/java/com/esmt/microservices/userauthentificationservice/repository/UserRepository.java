package com.esmt.microservices.userauthentificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esmt.microservices.userauthentificationservice.bean.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByLogin(String login);
	User findByLoginAndPassword(String login, String password);
}
