package FrameworkDemo;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ProductViewActions extends BaseClass{
	
	@Test
	public void fetchAllProducts() throws IOException
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
		List<Integer> quantities = res.path("data.quantity");
		int min = 0 , max = productIds.size();
		int idx = (int) (Math.random() * (max - min)) + min;
		productId = productIds.get(idx);
		quantity = quantities.get(idx);
		System.out.println(productId);
		
		res.then()
		.assertThat().statusCode(200)
		.log().all();
		
		//Store in a file for excessive long responses
		FileWriter file = new FileWriter("src/test/resources/response.json");
		file.write(res.asPrettyString());
		file.close();
	}
}
