package com.tts.weatherapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService
{
	
	@Autowired
	ZipCodeRepository zipCodeRepository;
	
	@Value("${api_key}")
	private String apiKey;
	
	public Response getForecast(String zipCode)
	{
		String url = "http://api.openweathermap.org/data/2.5/weather?zip=" 
				     + zipCode + "&units=imperial&appid=" + apiKey;
		
		ZipCode zipCodeEntry = new ZipCode(zipCode);
		zipCodeRepository.save(zipCodeEntry);
		
		RestTemplate restTemplate = new RestTemplate();
		try {
			return restTemplate.getForObject(url, Response.class);
		}
		catch (RestClientException ex)
		{
			Response response = new Response();
			response.setName("error");
			return response;
		}
	}
	
	public List<ZipCode> getLastEntries()
	{
		Pageable limit = PageRequest.of(0, 10);
		return zipCodeRepository.findAllByOrderByIdAsc(limit);
	}
}
