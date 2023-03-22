package StepDefinitions;

import io.cucumber.java.After;

public class Hooks {

	@After("@DeleteNewCat")
	public void afterScenario()
	{
		// Get all categories and loop through them and delete the categories with a name equal to "New Category" or "New Category - UPDATED"
		
	}
}
