package com.tts.weatherapp;

public class Request {
	
	private String zipCode;

	public Request(String zipCode) {
		super();
		this.zipCode = zipCode;
	}

	public Request() {
		super();
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
