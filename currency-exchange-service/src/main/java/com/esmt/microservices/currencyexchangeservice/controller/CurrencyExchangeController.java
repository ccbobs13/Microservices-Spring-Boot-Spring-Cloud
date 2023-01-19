package com.esmt.microservices.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.esmt.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.esmt.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	

	@Autowired
	private Environment environment;
		
	@Autowired
	CurrencyExchangeRepository repo;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")  
	public CurrencyExchange getCurrencyExchange(@PathVariable String from, @PathVariable String to) {
		
		CurrencyExchange currencyExchange = repo.findByFromAndTo(from, to);
		if(currencyExchange == null)
			throw new RuntimeException("Taux de change non trouve pour " + from + "->" + to);
		
		String port = environment.getProperty("local.server.port");
//		return new CurrencyExchange(configuration.getId(), from, to, configuration.getRateExchange());
		currencyExchange.setEnvironnement(port);
		return currencyExchange;
	}

}
