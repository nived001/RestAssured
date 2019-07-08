package RestPkg;

import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutRequestRestAssured {
	@Test
	public void PostDetails() {

		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
		RequestSpecification httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Kushan"); // Cast
		requestParams.put("LastName", "Amarasiri");
		requestParams.put("UserName", "test123");
		requestParams.put("Password", "password1");

		requestParams.put("Email", "tester101@gmail.com");
		httpRequest.body(requestParams.toJSONString());
		Response response = httpRequest.post("/register");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	public void PostDetails2() {

		RestAssured.baseURI = "https://reqres.in/api";
		RequestSpecification httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Kushan1"); // Cast
		requestParams.put("job", "leader");

		httpRequest.body(requestParams.toJSONString());
		Response response = httpRequest.post("/users");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);

	}

	@Test
	public void Login() {

		RestAssured.baseURI = "https://reqres.in/api";
		RequestSpecification httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "peter@klaven"); // Cast
		requestParams.put("password", "cityslicka");

		httpRequest.body(requestParams.toJSONString());
		Response response = httpRequest.post("/login");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

	}
}
