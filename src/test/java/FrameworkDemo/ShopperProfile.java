package FrameworkDemo;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import FrameworkDemo.pojos.ProfileUpdatePojo;
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
		
		//Response Headers, Status Code & Status Line Validation
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
		Map<String, String> profile = res.jsonPath().getMap("data");
		
		ProfileUpdatePojo payload = new ProfileUpdatePojo(
				profile.get("city"),
				profile.get("state"),
				profile.get("firstName"),
				profile.get("lastName"),
				profile.get("country"),
				8835350158L
				);
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
