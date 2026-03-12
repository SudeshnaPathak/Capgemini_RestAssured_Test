 package FrameworkDemo;

import org.testng.annotations.Test;

import FrameworkDemo.pojos.AddressPojo;
import FrameworkDemo.pojos.OrderRequestPojo;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class ShopperOrder extends BaseClass{
    Response res;
	@Test
	public void addAddress()
	{
		AddressPojo adr = new AddressPojo(
		        1,
		        "Flat 302, Sunrise Apartments",
		        "Patna",
		        "India",
		        "Near Gandhi Maidan",
		        "Jolly Pathak",
		        "9876543210",
		        "432101",
		        "Bihar",
		        "Boring Road",
		        "HOME"
		);
		
	   res = given()
		.auth().oauth2(accessToken)
		.contentType("application/json")
		.relaxedHTTPSValidation()
		.baseUri(baseUrl)
		.auth().oauth2(accessToken)
		.body(adr)
		.pathParam("shopperId", shopperId)
		.when()
			.post("shoppers/{shopperId}/address");
			
		res.then()
			.statusCode(201)
			.log().all();
		
		
		addressId = res.path("data.addressId");
		System.out.println(addressId);
	}
	@Test(dependsOnMethods={"addAddress", "FrameworkDemo.ShopperCart.addProductToCart"})
	public void placeOrder()
	{
		AddressPojo adr = res.jsonPath().getObject("data", AddressPojo.class);
		OrderRequestPojo odr = new OrderRequestPojo( adr , "COD");
		
		res = given()
			.auth().oauth2(accessToken)
			.contentType("application/json")
			.relaxedHTTPSValidation()
			.baseUri(baseUrl)
			.auth().oauth2(accessToken)
			.body(odr)
			.pathParam("shopperId", shopperId)
		.when()
			.post("shoppers/{shopperId}/orders");
		res.then()
			.statusCode(201)
			.log().all();
		
		orderId = res.path("data.orderId");
			
	}
	
	@Test(dependsOnMethods="placeOrder")
	public void generateInvoice() throws IOException
	{
		byte[] pdf =given()
			.log().all()
			.auth().oauth2(accessToken)
			.contentType("application/json")
			.relaxedHTTPSValidation()
			.baseUri(baseUrl)
			.auth().oauth2(accessToken)
			.pathParams("shopperId", shopperId, "orderId", orderId)
		.when()
			.get("shoppers/{shopperId}/orders/{orderId}/invoice")
		.then()
			.statusCode(200)
			.log().all()
			.extract()
			.asByteArray();
		
		FileOutputStream fos = new FileOutputStream("src/test/resources/invoice.pdf");
		fos.write(pdf);
		fos.close();
	}
}
