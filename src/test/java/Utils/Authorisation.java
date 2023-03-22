package Utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestData;

public class Authorisation extends Utils {
	
	public static String GetBearerToken(String email, String password) 
	{
		// Build Request Spec Builder
		RequestSpecification reqBuilder;
		
		// Build Response Spec Builder
		ResponseSpecification resBuilder;
		
		// Object used for creating payloads for sending to the API
		TestData testData = new TestData();
		
		// Resource path
		String postUserLoginResource = "users/login";

		// Build Request Spec Builder
		reqBuilder = new RequestSpecBuilder().setContentType(ContentType.JSON)
				.addHeader("Content-Type", "application/json")
				.setBaseUri("https://eshop-backend-101.herokuapp.com/api/v1/")
				.build();
		
		// Build Response Spec Builder
		resBuilder = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		
		// login with a user to get authorisation token
		Response res = given()
				.spec(reqBuilder)	
				.body(testData.loginUserPayload(email, password))
				.when().post(postUserLoginResource)

				.then().assertThat()
				.statusCode(200)		
				.extract().response();
		
		String responseString = res.asString();
		
		// Get the 'token' value from the response
		JsonPath js = new JsonPath(responseString);
		String bearerToken = js.getString("token");
		
		return bearerToken;
	}

}
