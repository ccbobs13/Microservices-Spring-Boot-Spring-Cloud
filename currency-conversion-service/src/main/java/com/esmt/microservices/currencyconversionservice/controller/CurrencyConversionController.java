package com.esmt.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.esmt.microservices.currencyconversionservice.bean.CurrencyConversion;
import com.esmt.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;
import com.esmt.microservices.currencyconversionservice.proxy.UserAuthentificationServiceProxy;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeProxy proxy;
	@Autowired
	private UserAuthentificationServiceProxy userProxy;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/amount/{amount}")
	public CurrencyConversion getCurrencyConversion(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal amount) {

		HashMap<String, String> uriVariables = new HashMap<>();

		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);

		CurrencyConversion currencyConversion = responseEntity.getBody();

		return new CurrencyConversion(currencyConversion.getId(), from, to, amount,
				currencyConversion.getRateExchange(), amount.multiply(currencyConversion.getRateExchange()),
				"REST Template", currencyConversion.getEnvironnement());

	}

	@GetMapping("/currency-conversion-feign/user/{login}/from/{from}/to/{to}/amount/{amount}")
	public CurrencyConversion getCurrencyConversionFeign(@PathVariable String login, @PathVariable String from,
			@PathVariable String to, @PathVariable BigDecimal amount) {

		boolean state = userProxy.authenticate(login);
		if (state) {

			CurrencyConversion currencyConversion = proxy.getCurrencyConversion(from, to);

			return new CurrencyConversion(currencyConversion.getId(), from, to, amount,
					currencyConversion.getRateExchange(), amount.multiply(currencyConversion.getRateExchange()),
					"Feign REST Client", currencyConversion.getEnvironnement());
		}
		throw new RuntimeException("Vous devez etre authentifie pour pouvoir faire une conversion");

	}


}
