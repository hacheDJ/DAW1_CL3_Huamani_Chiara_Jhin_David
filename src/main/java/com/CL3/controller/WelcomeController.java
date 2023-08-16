package com.CL3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	@GetMapping("/")
	public String Welcome(Model model) {
		model.addAttribute("style", "css/style.css");
		
		return "welcome";
	}
	
}
