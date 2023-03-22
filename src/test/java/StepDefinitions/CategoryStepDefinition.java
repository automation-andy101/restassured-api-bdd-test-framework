package StepDefinitions;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// import org.testng.Assert;

import POJO.Category;
import Utils.Authorisation;
import Utils.Utils;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestData;

public class CategoryStepDefinition extends Utils {
	
	// Object used for creating payloads for sending to the API
	TestData testData = new TestData();
	
	// Authorisation declaration
	Authorisation auth;
	String bearerToken;
	
	// API resource path
	APIResources resourcePath;
	
	// user credentials
	String email = "test1@email.com";
	String password = "pass";
	
	// Category resource path
	String categoryResource = "categories/";
			
	// Declare payloads
	Category newCategoryJsonObj;
	Category updatedCategoryJsonObj;	
	
	// Declare new category ID
	String newCategoryId;
	
	// Declare existing category ID
	String existingCategoryId;
			
	// Build Request Spec Builder
	RequestSpecification reqBuilder;
	
	// Build Response Spec Builder
	ResponseSpecification resBuilder;
	
	// Response declarations
	Response categoryResp;
	Response getCategoriesResp;

	// Declare category response bodies
	Category catResBody;
	
	// Arrays
	List<Category> getCatorgoriesRespBody;
	

	@Given("^request HEADER is set without auth$")
    public void request_header_is_set_without_auth() throws IOException {

		// Build Request Spec Builder
		reqBuilder = requestSpecificationNoAuth();
		
		// Build Response Spec Builder
		resBuilder = responseSpecification();
    }

	@Given("^request HEADER is set with auth$")
    public void request_header_is_set_with_auth() throws IOException {
		// Build Request Spec Builder
		reqBuilder = requestSpecificationWithAuth(bearerToken);
		
		// Build Response Spec Builder
		resBuilder = responseSpecification();
    }
	
	
    @Given("^a valid authorisation token has been obtained$")
    public void a_valid_authorisation_token_has_been_obtained() {
		// Get Authorisation token
		auth = new Authorisation();
		bearerToken = auth.GetBearerToken(email, password);
    }


    @When("user calls the {string} resource with a HTTP {string} request")
    public void user_calls_the_resource_with_a_HTTP_request(String resourceName, String requestType) {
    	
    	// Get the resource path from the APIResource Enum
    	resourcePath = APIResources.valueOf(resourceName);
    	    	
    	if(requestType.equalsIgnoreCase("get")) {
        	// Get all Categories
        	categoryResp = given().spec(reqBuilder)
    			.expect().defaultParser(Parser.JSON)
    			.when().get(resourcePath.getResource())
    			.then().extract().response();
        			
    		getCatorgoriesRespBody = categoryResp.as(new TypeRef<List<Category>>() {});
    		
    	} else if(requestType.equalsIgnoreCase("delete")) {
        	// Create a new add/category before updating it
        	categoryResp = given().spec(reqBuilder)
    				.body(testData.categoryPayload("New Category", "New Color", "New Icon"))
    				.when().post(categoryResource);
        	catResBody = categoryResp.as(Category.class);

    		// Get ID of newly created category
    		newCategoryId = catResBody.getId();
    		
        	// Delete Category
    		categoryResp = given().spec(reqBuilder)
    			.when().delete(resourcePath.getResource() + newCategoryId);
    	}
    }
    
    
    @When("user calls the {string} resource with a HTTP {string} request to get a single category")
    public void user_calls_the_resource_with_a_HTTP_request_to_get_a_single_category(String resourceName, String requestType) {
    	
    	// Get the resource path from the APIResource Enum
    	resourcePath = APIResources.valueOf(resourceName);
    	
    	// Get a single category
    	categoryResp = given().spec(reqBuilder)
			.expect().defaultParser(Parser.JSON)
			
			.when().get(resourcePath.getResource() + existingCategoryId)
			
			.then().extract().response();
			
    	catResBody = categoryResp.as(Category.class);
    }

    
    @When("user calls the {string} resource with a HTTP {string} request to {string} a category {string} {string} {string}")
    public void user_calls_the_resource_with_a_http_request_to_a_category(String resourceName, String restRequestType, String method,String name, String color, String icon) {

		// Get the resource path from the APIResource Enum
    	resourcePath = APIResources.valueOf(resourceName);
    	
    	if(method.equalsIgnoreCase("add")) {
    		
        	categoryResp = given().spec(reqBuilder)
        			.body(testData.categoryPayload(name, color, icon))
        			.when().post(resourcePath.getResource());  
        	
    	} else if(method.equalsIgnoreCase("update")) {
    		
        	// Create a new category before updating it
        	categoryResp = given().spec(reqBuilder)
    				.body(testData.categoryPayload("New Category", "New Color", "New Icon"))
    				.when().post(categoryResource);
 
        	catResBody = categoryResp.as(Category.class);
        	
    		// Get ID of newly created category
    		newCategoryId = catResBody.getId();
    		
        	// Update Category
    		// Serialise - update a Category JSON object using POJO class	
    		categoryResp = given().spec(reqBuilder)
    			.body(testData.categoryPayload(name, color, icon))
    			.when().put(resourcePath.getResource() + newCategoryId);
    		
    	}
    	catResBody = categoryResp.as(Category.class);
    }
    
 

    @Then("^response status code equals \"([^\"]*)\"$")
    public void response_status_code_equals_something(String expectedStatusCode) {
    	Integer statusCodeInt = categoryResp.getStatusCode();
    	String statusCodeToString = statusCodeInt.toString();
      	assertEquals(expectedStatusCode, statusCodeToString);
    }

    @And("^response body contains categories$")
    public void response_body_contains_categories() {
		//Assert that there are category objects returned i.e. the length of the categoriesResp array is greater than 0
		// TestNg Assertion
		Integer numOfCategoryObjects = getCatorgoriesRespBody.size();
		Boolean greaterThanZero = false;
		if(numOfCategoryObjects > 0)
		{
			greaterThanZero = true;
		}
		assertTrue(greaterThanZero);
    }


    @And("^response body contains the new category$")
    public void response_body_contains_the_new_category() {
    	catResBody = categoryResp.as(Category.class);
		
		// Assert that response body contains the new category
		// Assert that Name property value in response matches the Name in request body
    	assertEquals(catResBody.getName(),  testData.addCategoryPayload().getName());
		
		// Assert that Name property value in response matches the Name in request body
    	assertEquals(catResBody.getColor(), testData.addCategoryPayload().getColor());
		
		// Assert that Name property value in response matches the Name in request body
		assertEquals(catResBody.getIcon(), testData.addCategoryPayload().getIcon());
    }


    @And("^response body contains the updated category$")
    public void response_body_contains_the_updated_category() {
    	catResBody = categoryResp.as(Category.class);
    	
		// Assert that response body contains the updated category
		// Assert that Name property value in response matches the Name in request body
		assertEquals(catResBody.getName(), testData.updateCategoryPayload().getName());
		// Assert that Name property value in response matches the Name in request body
		assertEquals(catResBody.getName(), testData.updateCategoryPayload().getName());
		// Assert that Name property value in response matches the Name in request body
		assertEquals(catResBody.getName(), testData.updateCategoryPayload().getName());
    }
    
    @And("^user has obtained a categoryId$")
    public void user_has_obtained_a_categoryid() {
    	// Get all Categories
    	categoryResp = given().spec(reqBuilder)
			.expect().defaultParser(Parser.JSON)

			.when().get(categoryResource)
			
			.then().extract().response();
		
		getCatorgoriesRespBody = categoryResp.as(new TypeRef<List<Category>>() {});
		
		// Get id of first category object returned
		existingCategoryId = getCatorgoriesRespBody.get(0).getId();
    }
    
    @And("^response body contains category fields$")
    public void response_body_contains_category_fields() {
    	//Assert that category object containing the following fields exists:-
		// id, name, color, icon
		assertNotNull(catResBody.getId());
		assertNotNull(catResBody.getName());
		assertNotNull(catResBody.getColor());
		assertNotNull(catResBody.getIcon());
    }
    
    @And("^create new category payload is configured$")
    public void create_new_category_payload_is_configured() {
        
    }
}
