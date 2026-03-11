package FrameworkDemo;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class ShopperProfile extends BaseClass{
	
	Response res;
	@Test
	public void fetchProfile()
	{
		res=given()
			.contentType("application/json")
			.auth().oauth2(accessToken)
			.pathParam("shopperId", shopperId)
			.baseUri(baseUrl)
			.relaxedHTTPSValidation()
		.when()
			.get("/shoppers/{shopperId}");
		
		res.then()
			.assertThat().statusCode(200)
			.assertThat().statusLine("HTTP/1.1 200 ")
			.assertThat().header("Server", "nginx")
			.assertThat().header("Content-Type", "application/json");
		
		Headers headers = res.headers();
		for(Header header : headers)
		{
			System.out.println(header.getName() + " : " + header.getValue());
		}
		
		System.out.println(res.getBody().asString());
	}
	
	@Test(dependsOnMethods="fetchProfile")
	public void updateProfile()
	{
		// First get the JsonPath object instance from the Response interface
		Map<String, String> profile = res.jsonPath().getMap("data");
		
		// Then simply query the JsonPath object to get a String value of the node
		ProfileUpdatePojo payload = new ProfileUpdatePojo(
				profile.get("city"),
				profile.get("state"),
				profile.get("firstName"),
				profile.get("lastName"),
				profile.get("country"),
				8825250158L
				);
		System.out.println(payload);
		given()
			.contentType("application/json")
			.auth().oauth2(accessToken)
			.pathParam("shopperId", shopperId)
			.baseUri(baseUrl)
			.relaxedHTTPSValidation()
			.body(payload)
		.when()
			.patch("shoppers/{shopperId}")
		.then()
			.statusCode(200)
			.log().all();
	}
}
