package com.crystal.tigers.s1.ws.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HomeContronller {
	@RequestMapping(value ="/")
	public String getURLValue(HttpServletRequest request){
	    //String test = request.getRequestURI();
	    return "Welcome to S1";
	}
}
