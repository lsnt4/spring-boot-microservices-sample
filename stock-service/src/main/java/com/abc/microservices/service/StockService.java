package com.abc.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.abc.microservices.dto.ProductCreationRequest;
import com.abc.microservices.dto.ProductCreationResponse;
import com.abc.microservices.dto.StockRequest;
import com.abc.microservices.dto.StockResponse;

@Service
public class StockService {

	@Autowired
	private RestTemplate restTemplate;
	
	public StockResponse createProduct(StockRequest stockRequest) {
		ProductCreationRequest productCreationRequest = new ProductCreationRequest();
		productCreationRequest.setId(stockRequest.getProductId());
		productCreationRequest.setName(stockRequest.getProductName());
		productCreationRequest.setPrice(stockRequest.getProductPrice());
		
		ResponseEntity<ProductCreationResponse> productCreationResponse = restTemplate.postForEntity("http://localhost:8002/api/products/", productCreationRequest, ProductCreationResponse.class);
	
		StockResponse stockResponse = new StockResponse();
		stockResponse.setStockId(stockRequest.getId());
		stockResponse.setProductId(productCreationResponse.getBody().getProductId());
		stockResponse.setMessage("Stock created");
		
		System.out.println("Stock created");
		return stockResponse;
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
