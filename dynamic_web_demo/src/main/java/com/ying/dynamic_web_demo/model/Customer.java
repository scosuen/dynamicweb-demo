package com.ying.dynamic_web_demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "T_CUSTOMER")
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CUSTOMER_ID")
	private Long customerId = 0L;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@OneToMany(mappedBy="customer")
	private List<Order> orders = new ArrayList<Order>();

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder (Order order) {
		if (orders == null)
			orders = new ArrayList<Order>();
		orders.add(order);
	}
	
	@Override
	public String toString() {
		return "customerId:" + getCustomerId() + ", customerName:" + getCustomerName();
	}

}
