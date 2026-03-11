package FrameworkDemo;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;

public class ProductViewActions extends BaseClass{
	
	@Test
	public void fetchAllProducts()
	{
		Response res = given()
		.auth().oauth2(accessToken)
		.relaxedHTTPSValidation()
		.contentType("application/json")
		.baseUri(baseUrl)
		.queryParam("zoneId",zoneId)
		.when()
		.get("products");
		
//		List<Integer> productIds = res.jsonPath().getList("data.productId");
		List<Integer> productIds = res.path("data.productId");
		int min = 0 , max = productIds.size();
		productId = productIds.get((int) (Math.random() * (max - min)) + min);
		System.out.println(productId);
		
		res.then()
		.assertThat().statusCode(200)
		.log().all();
	}
}
