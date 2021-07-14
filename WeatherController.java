package com.tts.weatherapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;

@Controller // Says this object is a controller
public class WeatherController {
	
	// Inversion of control... The idea is that some objects in
	//		a Spring Boot project have their life cycles managed
	//		by Spring Boot, not by the programmer.
	
	// Usually, a programmer has to create an object using "new"
	//		but with inversion control, the programmer just asks
	//		for the object to be created, and its the
	//		responsibility of the Spring framework to figure out
	//		how to make it provide the object.
	
	@Autowired
	private WeatherService weatherService;
	
	// @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST}) // Handle all requests to "/"
	
	// Handle all requests to "/"
	@GetMapping(value = "/")
	public String getIndex(Model model)
	{
		model.addAttribute("last", weatherService.getLastEntries());
		// Response response = weatherService.getForecast("43210");
		model.addAttribute("request", new Request("77338"));
		return "index";
	}
	
	@PostMapping(value="/")
	public String postIndex(Request request, Model model)
	{
		Response data = weatherService.getForecast(request.getZipCode());
		model.addAttribute("last", weatherService.getLastEntries());
		model.addAttribute("data", data);
		return "index";
	}

}
