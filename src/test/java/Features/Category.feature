Feature: Test the Category service API
	
Scenario: Verify that the GET category API endpoint returns categories
	Given request HEADER is set without auth
	When user calls the "GetCategoriesAPI" resource with a HTTP "GET" request
	Then response status code equals "200"
	And response body contains categories
		
		
Scenario: Verify that the GET single category API endpoint returns a category
	Given request HEADER is set without auth
	And user has obtained a categoryId
	When user calls the "GetCategoryAPI" resource with a HTTP "GET" request to get a single category
	Then response status code equals "200"
	And response body contains category fields


@DeleteNewCat
Scenario Outline: Verify that a new category can be added/created using the POST category API endpoint
	Given a valid authorisation token has been obtained
	And request HEADER is set with auth
	When user calls the "AddCategoryAPI" resource with a HTTP "POST" request to "add" a category "<name>" "<color>" "<icon>"
	Then response status code equals "200"
	And response body contains the new category

Examples:
	| name         | color     | icon     |
	| New Category | New Color | New Icon |	   
	

Scenario Outline: Verify that a category can be updated using the PUT category API endpoint
	Given a valid authorisation token has been obtained
	And request HEADER is set with auth
	When user calls the "UpdateCategoryAPI" resource with a HTTP "PUT" request to "update" a category "<name>" "<color>" "<icon>"
	Then response status code equals "200"
	And response body contains the updated category
	
Examples:
	| name                   | color               | icon               |
	| New Category - UPDATED | New Color - UPDATED | New Icon - UPDATED |	 
	
	
Scenario: Verify that a category can be deleted using the DELETE category API endpoint
	Given a valid authorisation token has been obtained
	And request HEADER is set with auth
	When user calls the "DeleteCategoryAPI" resource with a HTTP "DELETE" request
	Then response status code equals "200"
