package Utils;

import io.restassured.path.json.JsonPath;



public class ResusableMethods {

	public static JsonPath RawToJson(String response)
	{
		JsonPath js = new JsonPath(response);

		return js;
	}
}
