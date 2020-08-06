package com.example.demo.order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;




@RestController
@RequestMapping("/api") // your call 
public class MultiplecallsTest {
	
	
	@Autowired
	RestTemplate restTemplate;
	
	//first call
	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAllUsers() throws Exception {
//
//		int uriVariables = 0;
//		Object result = restTemplate.getForObject("http://142.93.217.162:3000/api/dashboards/uid/9CWBz0bi", Object.class, uriVariables);
////		restTemplate.postForLocation(url, request)
//		restTemplate.postForLocation("", result);
//		
//		Object body = new Object();
//		ObjectMapper mapper = new ObjectMapper();
//		String type = "";
//		if(type.equals("circle"))
//			Circle errorObj = mapper.convertValue(result, Circle.class);
//		
//		
		System.out.println("GEt all endpoint called");
		
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.APPLICATION_JSON);
		mediaTypes.add(MediaType.APPLICATION_ATOM_XML);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.set("Authorization", "Basic YWRtaW46YWRtaW4=");
		headers.setAccept(mediaTypes);
		headers.setBasicAuth("admin", "admin");
		HttpEntity request = new HttpEntity(headers);
		
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);
		try {
				response = restTemplate.exchange("http://142.93.217.162:3000/api/dashboards/uid/rYdddlPWk", HttpMethod.GET, request, String.class, 1);
				if(response != null)
				{
					try {
						response = restTemplate.exchange("http://164.90.234.182:3000/api/dashboards/uid/rYdddlPWk", HttpMethod.POST, response, String.class);
					}catch(HttpStatusCodeException exception) {
						int statusCode = exception.getStatusCode().value();
						response = new ResponseEntity<String>(exception.getMessage(), exception.getStatusCode());
					}
					
				}
		}catch (HttpStatusCodeException exception) {
			int statusCode = exception.getStatusCode().value();
			System.out.println("debug1");
			response = new ResponseEntity<String>(exception.getMessage(), exception.getStatusCode());
		}
//		catch(Exception e) {
//			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_GATEWAY);
//		}
//		System.out.println("Call done with code: "+response.getStatusCode());
//		
//		if (response.getStatusCode() == HttpStatus.OK) {
//		    System.out.println("Request Successful.");
//		    System.out.println(response.getBody());
//		} else {
//		    System.out.println("Request Failed");
//		    System.out.println(response.getStatusCode());
//		}
////		
////		if(response.getStatusCode() == HttpStatus.OK) {
////
////			return  response.getBody();
////		}
////		else {
////			return response.getStatusCode();
//		}
//		
		return response;
	}
	
	
//
//	@PostMapping(value="/create", produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody Object saveUser(@RequestBody Map result) {
//		try {
//			
//			return null;
//		}
//		catch(Exception e) {
//			
//			return "Please pass proper object.";
//		}
//		
//	}
}
