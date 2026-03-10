package shopper;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class ShopperLoginTest {
	static String baseUrl = "https://www.shoppersstack.com/shopping";
	static String accessToken;
	static String shopperId;
	@Test()
	public void loginTest()
	{
		HashMap<String, Object> body = new HashMap<>();

		body.put("email", "pathaksudeshna92@gmail.com");
		body.put("password", "Sudeshna@123");
		body.put("role", "SHOPPER");
		
		Response res = 
			 given()
				.contentType("application/json")
				.relaxedHTTPSValidation() //allows to access the server
				.body(body)
			.when()
				.post(baseUrl+"/users/login");
			
		res.then()
			.assertThat().statusCode(200)
			.log().all();
		
		accessToken = res.jsonPath().getString("data.jwtToken");
		shopperId = res.jsonPath().getString("data.userId");
	}
	
	@Test(dependsOnMethods = "loginTest")
	//invocationCount=3
	//enabled=false
	public void fetchData()
	{
		given()
			.contentType("application/json")
			.auth().oauth2(accessToken)
			.pathParam("shopperId", shopperId)
			.relaxedHTTPSValidation()
		.when()
			.get(baseUrl+"/shoppers/{shopperId}")
		.then()
		.assertThat().statusCode(200)
		.log().all();
	}

}
