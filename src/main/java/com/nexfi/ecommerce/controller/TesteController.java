package com.nexfi.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {
	
	@GetMapping("/ola")
	public String testando() {
		return "primeiro teste";
	}

}
