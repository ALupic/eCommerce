package com.luv2code.ecommerce.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.ecommerce.dao.CustomerRepository;
import com.luv2code.ecommerce.dto.Purchase;
import com.luv2code.ecommerce.dto.PurchaseResponse;
import com.luv2code.ecommerce.entity.Customer;
import com.luv2code.ecommerce.entity.Order;
import com.luv2code.ecommerce.entity.OrderItem;

@Service
public class CheckoutServiceImpl implements CheckoutService{
	
	private CustomerRepository customerRepository;
	
	public CheckoutServiceImpl(CustomerRepository cr) {
		this.customerRepository = cr;
	}

	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		Order order = purchase.getOrder();
		
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		Customer customer = purchase.getCustomer();
		customer.add(order);
		
		customerRepository.save(customer);
		
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {
		
		return UUID.randomUUID().toString();
	}

}
