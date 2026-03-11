package FrameworkDemo;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;

public class BaseClass {
	public static String baseUrl = "https://www.shoppersstack.com/shopping";
	public static String zoneId = "alpha";
	public static String accessToken;
	public static int shopperId;
	public static int productId;
	public static int itemId;
	
	@BeforeClass
	public void login()
	{
		ShopperLoginPojo body = new ShopperLoginPojo("pathaksudeshna92@gmail.com", "Sudeshna@123", "SHOPPER");
		
		Response res = given()
				.contentType("application/json")
				.relaxedHTTPSValidation() //allows to access the server
				.body(body)
				.baseUri(baseUrl)
				.when()
				.post("/users/login");
			
		res.then()
			.assertThat().statusCode(200)
			.log().all();
		
		accessToken = res.path("data.jwtToken"); 
		shopperId = res.jsonPath().getInt("data.userId");
	}
}
