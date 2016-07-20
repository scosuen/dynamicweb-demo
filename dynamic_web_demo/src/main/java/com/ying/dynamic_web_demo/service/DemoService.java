package com.ying.dynamic_web_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ying.dynamic_web_demo.dao.DemoDAO;
import com.ying.dynamic_web_demo.exception.NoDataFoundException;
import com.ying.dynamic_web_demo.model.Customer;

@Service
public class DemoService {

	@Autowired
	private DemoDAO demoDAO;

	public void setup(List<Customer> customers) {
		demoDAO.insertRecords(customers);
	}

	public List<Customer> searchByCustomerName(List<String> customerNames) throws NoDataFoundException {
		return demoDAO.queryByCustomerName(customerNames);
	}

}
