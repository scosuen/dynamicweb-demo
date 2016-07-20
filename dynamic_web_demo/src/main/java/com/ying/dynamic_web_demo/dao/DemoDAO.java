package com.ying.dynamic_web_demo.dao;

import java.util.List;

import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ying.dynamic_web_demo.exception.NoDataFoundException;
import com.ying.dynamic_web_demo.model.Customer;
import com.ying.dynamic_web_demo.model.Order;
import com.ying.dynamic_web_demo.model.Product;

@Repository
public class DemoDAO extends HibernateDAO {
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertRecords(List<Customer> customers) {
		for (Customer customer : customers) {
			getSession().save(customer);
			for (Order order : customer.getOrders()) {
				order.setCustomer(customer);
				getSession().save(order);
				for (Product product : order.getProducts()) {
					product.setOrder(order);
					getSession().save(product);
				}
			}
		}
	}

	@Transactional(readOnly=true)
	public List<Customer> queryByCustomerName(List<String> customerNames) throws NoDataFoundException {
		List<Customer> customers = getSession().createCriteria(Customer.class).add(Restrictions.in("customerName", customerNames)).list();
		if (customers == null || customers.size() <= 0)
			throw new NoDataFoundException("Customer names:" + customerNames);
		
		return customers;
	}
}
