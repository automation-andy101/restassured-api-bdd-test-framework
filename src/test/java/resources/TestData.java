package resources;

import POJO.Category;
import POJO.Login;

public class TestData {

	public Category addCategoryPayload() {
		Category categoryJsonObj = new Category();
		categoryJsonObj.setName("New Category");
		categoryJsonObj.setColor("New Color");
		categoryJsonObj.setIcon("New Icon");	
		
		return categoryJsonObj;
	}
	
	public Category updateCategoryPayload() {
		Category categoryJsonObj = new Category();
		categoryJsonObj.setName("New Category - UPDATED");
		categoryJsonObj.setColor("New Color - UPDATED");
		categoryJsonObj.setIcon("New Icon - UPDATED");	
		
		return categoryJsonObj;
	}
	
	public Category categoryPayload(String name, String color, String icon) {
		Category categoryJsonObj = new Category();
		categoryJsonObj.setName(name);
		categoryJsonObj.setColor(color);
		categoryJsonObj.setIcon(icon);	
		
		return categoryJsonObj;
	}
	
	public Login loginUserPayload(String email, String password) {
		Login loginJsonObj = new Login();
		loginJsonObj.setEmail(email);
		loginJsonObj.setPassword(password);
		
		return loginJsonObj;
	}
}
