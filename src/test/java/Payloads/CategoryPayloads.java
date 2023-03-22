package Payloads;

public class CategoryPayloads {

	public static String AddCategory()
	{
		return "{\r\n"
				+ "    \"name\": \"api-reastassured-test-category\",\r\n"
				+ "    \"icon\": \"icon-test\",\r\n"
				+ "    \"color\": \"#777\"\r\n"
				+ "}";
	}
	
	public static String UpdateCategory(String newName)
	{
		return "{\r\n"
				+ "    \"name\": \""+newName+"\",\r\n"
				+ "    \"icon\": \"icon-test\",\r\n"
				+ "    \"color\": \"#777\"\r\n"
				+ "}";
	}
}
