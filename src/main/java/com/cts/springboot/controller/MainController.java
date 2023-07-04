package com.cts.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cts.springboot.services.PropertyService;

@Controller
public class MainController {
	@Autowired
	private PropertyService propertyService;

	@GetMapping("/")
	public String home(Model m) {
		m.addAttribute("properties",propertyService.getAllProperties());
		return "index";
	}
	@GetMapping("/login_all")
	public String loginPrompt() {
		return "login_all";
	}

	
}
