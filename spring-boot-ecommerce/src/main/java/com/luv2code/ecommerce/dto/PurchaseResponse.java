package com.luv2code.ecommerce.dto;

public class PurchaseResponse {
	
	private String orderTrackingNumber;
	
	public PurchaseResponse() {
		
	}
	
	public PurchaseResponse(String otn) {
		this.orderTrackingNumber = otn;
	}

	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}

	public void setOrderTrackingNumber(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}

}
