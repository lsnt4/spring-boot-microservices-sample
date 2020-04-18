package com.abc.microservices.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.microservices.dto.StockRequest;
import com.abc.microservices.dto.StockResponse;
import com.abc.microservices.service.StockService;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
	
	@Autowired
	private StockService stockService;
	
	ConcurrentMap<String, StockRequest> stocks = new ConcurrentHashMap<>();

	@GetMapping("/{id}")
	public @ResponseBody StockRequest getStock(@PathVariable String id) {
		return stocks.get(id);
	}
	
	@GetMapping("/")
	public @ResponseBody List<StockRequest> getAllStocks() {
		return new ArrayList<StockRequest>(stocks.values());
	}
	
	@PostMapping("/")
	public @ResponseBody StockResponse createStock(@RequestBody StockRequest stockRequest) {
		stocks.put(stockRequest.getId(), stockRequest);
		
		return stockService.createProduct(stockRequest);
	}
}
