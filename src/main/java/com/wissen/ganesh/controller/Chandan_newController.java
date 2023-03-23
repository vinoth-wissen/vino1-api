package com.wissen.ganesh.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wissen.ganesh.beans.Dummy;
import com.wissen.ganesh.util.BasePath;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "${cross.origin.angular.hosturl}")
@BasePath
@Api
public class Chandan_newController {
	
	@PostMapping("undefined")
	//@ApiImplicitParam(name = "Authorization", value = "Bearer access_token", required = true, paramType = "header")
	public Dummy getRespose(@RequestBody Map<String, Object> payload ) {
		System.out.println(payload);
		return new Dummy("Success");
	}

}
