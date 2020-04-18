package com.abc.microservices.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.microservices.dto.ProductRequest;
import com.abc.microservices.dto.ProductResponse;


@RestController
@RequestMapping("/api/products")
public class ProductController {

	ConcurrentMap<String, ProductRequest> products = new ConcurrentHashMap<>();

	@GetMapping("/{id}")
	public @ResponseBody ProductRequest getProduct(@PathVariable String id) {
		return products.get(id);
	}
	
	@GetMapping("/")
	public @ResponseBody List<ProductRequest> getAllProducts() {
		return new ArrayList<ProductRequest>(products.values());
	}
	
	@PostMapping("/")
	public @ResponseBody ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
		products.put(productRequest.getId(), productRequest);

		ProductResponse productResponse = new ProductResponse();
		productResponse.setProductId(productRequest.getId());
		productResponse.setMessage("Product created");
		
		System.out.println("Product created");
		return productResponse;
	}
}
