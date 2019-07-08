package RestPkg;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import io.restassured.path.json.JsonPath;

public class JavaHTTPAPIClient {

	public void getQuestionsUsingUnirest() throws Exception {
		HttpResponse<JsonNode> response = Unirest.get("https://api.stackexchange.com/2.2/questions")
				.header("accept", "application/json").queryString("order", "desc").queryString("sort", "creation")
				.queryString("filter", "default").queryString("site", "stackoverflow").asJson();
		// System.out.println(response.getBody().getObject().toString(1));

		String responseBody = response.getBody().toString();

		JSONObject str = new JSONObject(responseBody);

		JSONArray repsoneArray = (JSONArray) str.get("items");

		// System.out.println(repsoneArray.get(0));

		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 0; i < repsoneArray.length(); i++) {
			list.add(repsoneArray.getJSONObject(i).getJSONObject("owner").getInt("user_id"));

			Integer userId = list.get(i);
			// System.out.println(name);
			if (list.get(i).equals(userId)) {
				System.out.println(
						userId + " " + repsoneArray.getJSONObject(i).getJSONObject("owner").get("display_name"));
			} else {
				System.out.println("fail");
			}
		}

		System.out.println(list);

	}

	public static void main(String args[]) throws Exception {
		JavaHTTPAPIClient client = new JavaHTTPAPIClient();
		client.getQuestionsUsingUnirest();
	}

}
