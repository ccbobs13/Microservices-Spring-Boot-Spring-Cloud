package com.esmt.microservices.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter (RouteLocatorBuilder builder) {
		return builder.routes()
				.route(
						p -> p
						.path("/get")
						.filters(f -> f
								.addRequestHeader("myHeaderParam", "HeaderParamValue")
								.addRequestParameter("Myparameter", "ParamValue"))
						.uri("http://httpbin.org:80"))
				.route(
						p -> p
						.path("/currency-exchange/**")				
						.uri("lb://currency-exchange"))
				.route(
						p -> p
						.path("/currency-conversion-feign/**")
						.uri("lb://currency-conversion"))
				.route(
						p -> p
						.path("/user-authentification-service/**")
						.uri("lb://user-authentification-service"))
				.build();
	}
}
