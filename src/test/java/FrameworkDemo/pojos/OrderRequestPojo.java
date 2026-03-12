package FrameworkDemo.pojos;

public class OrderRequestPojo {
	
	private AddressPojo address;
    private String paymentMode;
    
	public OrderRequestPojo(AddressPojo address, String paymentMode) {
		super();
		this.address = address;
		this.paymentMode = paymentMode;
	}

	public AddressPojo getAddress() {
		return address;
	}

	public void setAddress(AddressPojo address) {
		this.address = address;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
    
    
}
