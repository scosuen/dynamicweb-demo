package com.ying.dynamic_web_demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ying.dynamic_web_demo.exception.NoDataFoundException;
import com.ying.dynamic_web_demo.model.Customer;
import com.ying.dynamic_web_demo.service.DemoService;
import com.ying.dynamic_web_demo.utils.CommonJSONs;

@RestController
public class DemoController {

	@Autowired
	private DemoService demoService;
	
	private static Logger logger = LoggerFactory.getLogger(DemoController.class);

	@RequestMapping(value = "/setup", method = RequestMethod.POST)
	public String setup(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException {
		logger.debug("json:" + json);
		ObjectMapper mapper = new ObjectMapper();
		List<Customer> customers = mapper.readValue(json, 
				mapper.getTypeFactory().constructParametrizedType(List.class, List.class, Customer.class));
		
		demoService.setup(customers);
		return CommonJSONs.getSuccResponseJSON();
	}
	
	@RequestMapping(value = "/search/{customer_name}", method = RequestMethod.GET)
	public String search (@PathVariable("customer_name") String customerName) throws NoDataFoundException, JsonProcessingException {
		
		logger.debug("customerName:" + customerName);
		String[] nameArr = customerName.split(",");
		List<String> customerNames = new ArrayList<String>();
		for (String name : nameArr) {
			customerNames.add(name.trim());
		}
		
		List<Customer> customers = demoService.searchByCustomerName (customerNames);
		
		return new ObjectMapper().writeValueAsString(customers);
	}
}
