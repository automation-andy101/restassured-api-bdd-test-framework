package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	public static RequestSpecification reqBuilder;
	ResponseSpecification resBuilder;
	
	public RequestSpecification requestSpecificationNoAuth() throws IOException
	{
//		if(reqBuilder ==null)
//		{
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			
			// Build Request Spec Builder
			reqBuilder = new RequestSpecBuilder().setContentType(ContentType.JSON)
					.addHeader("Content-Type", "application/json")
					.setBaseUri(getGlobalPropertyValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.build();
			
			return reqBuilder;	
//		}
//		return reqBuilder;
	}
	
	
	public RequestSpecification requestSpecificationWithAuth(String bearerToken) throws IOException
	{
//		if(reqBuilder ==null)
//		{
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			
			// Build Request Spec Builder
			reqBuilder = new RequestSpecBuilder().setContentType(ContentType.JSON)
					.addHeader("Authorization", "Bearer " + bearerToken)
					.addHeader("Content-Type", "application/json")
					.setBaseUri(getGlobalPropertyValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.build();
			
			return reqBuilder;
//		}
//		return reqBuilder;
	}
	
	
	public ResponseSpecification responseSpecification()
	{
		// Build Response Spec Builder
		resBuilder = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON) 
				.build();
		
		return resBuilder;
	}
	
	public static String getGlobalPropertyValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("D:\\Programming\\Automated Testing\\RestAssured\\Automation101ReastAssuredAPITests\\Automation101APICucumber\\src\\test\\java\\resources\\global.properties"); 
	
		prop.load(fis);
		
		return prop.getProperty(key);
	}
	
}
