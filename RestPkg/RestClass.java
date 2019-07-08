package RestPkg;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClass {

	@Test
	public void GetWeatherDetails() {

		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.GET, "/Pune");

		int statusCode = response.getStatusCode();

		String responseBody = response.getBody().asString();

		assertEquals(statusCode, 200);
		Assert.assertEquals(responseBody.toLowerCase().contains("pune"), true, "Response body contains P");

	}

	@Test
	public void pathParams() {
		String str[] = { "Pune", "Delhi" };
		given().pathParam("str", str[1]).log().all().when()
				.get("http://restapi.demoqa.com/utilities/weather/city/{str}").then().log().body();
	}

	@Test
	public void logginALL() {
		given().log().all().when().get("http://restapi.demoqa.com/utilities/weather/city/Pune").then().log().body();

	}

	@Test
	public void extractFromResponse() {
		String str = given().when().get("http://restapi.demoqa.com/utilities/weather/city/Pune").then().extract()
				.path("City");
		System.out.println(str);
	}

	@DataProvider
	public static Object[] tsetData() {
		return new Object[] { "Pune", "Delhi" };
	}

	@org.testng.annotations.Test
	@DataProvider(name = "tsetData")
	public void dataTest(String city) {
		given().pathParam("city", city).when().get("http://restapi.demoqa.com/utilities/weather/city/{city}").then()
				.assertThat().body("City", equalTo("Pune"));
	}

	@Test
	public void AllUsers() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/users/1");
		String responseBody = response.getBody().asString();

		JSONObject data = new JSONObject(responseBody);
		if (data.get("id").toString().equalsIgnoreCase("2")) {
			System.out.println(data.get("name"));
		} else {
			System.out.println("fail");
		}

	}

	@Test
	public void GetDetails() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.GET, "/users/1");

		int statusCode = response.getStatusCode();

		String responseBody = response.getBody().asString();
		// System.out.println(responseBody);
		JSONObject sam = new JSONObject(responseBody);

		// to print parent value

		Object uname = sam.get("name");
		System.out.println(sam.getJSONObject("address").get("city"));// toprintchild

		// Assert.assertEquals("Leanne Graham", uname);

		assertEquals(statusCode, 300);

	}

	@Test
	public void allUsers() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.GET, "/users");

		String responseBody = response.getBody().asString();

		JsonPath jp = response.jsonPath();

		// System.out.println(jp.getString("address.geo.lat"));
		List<Map<String, Object>> data = com.jayway.jsonpath.JsonPath.parse(responseBody).read("$[?(@.id == 2)]");// return
																													// the
																													// complete
																													// object
																													// of
																													// id=2

		for (Map<String, Object> book : data) {
			System.out.println("" + book);
		}

		// JSONObject str = new JSONObject(responseBody);
		// System.out.println(str.get("name"));

	}

	@Test
	public void AllBooks() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/books";
		RequestSpecification httpRequest = RestAssured.given();

		// httpRequest.when().get("").then().assertThat().body("test", equalTo);

		Response response = httpRequest.request(Method.GET, "/getallbooks");
		String responseBody = response.getBody().asString();

		List<Map<String, Object>> data = com.jayway.jsonpath.JsonPath.parse(responseBody).read("$[?(@.id == 2)] &&");

		// $.books[?(@.pages> 460)]

		// List<Integer> data =
		// com.jayway.jsonpath.JsonPath.parse(responseBody).read("$..pages");
		for (Map<String, Object> book : data) {
			System.out.println("" + book);
		}
		/*
		 * JsonPath jp = response.jsonPath();
		 * 
		 * String str = jp.getJsonObject("$.books[0]");
		 */
		System.out.println(data);

		System.out.println("\"hello\"");

		/*
		 * List<String> allbooks=jp.getList("$.books"); for(String book:
		 * allbooks) { System.out.println(""+book); }
		 * 
		 * Root node ($): This symbol denotes the root member of a JSON
		 * structure no matter it is an object or array. Its usage examples were
		 * included in the previous sub-section.
		 * 
		 * Current node (@): Represents the node that is being processed, mostly
		 * used as part of input expressions for predicates. Suppose we are
		 * dealing with book array in the above JSON document, the expression
		 * book[?(@.price == 49.99)] refers to the first book in that array.
		 * 
		 * Wildcard (*): Expresses all elements within the specified scope. For
		 * instance, book[*] indicates all nodes inside a book array.
		 */

	}

}