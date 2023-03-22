package Payloads;

public class UserPayloads {
	public static String loginCredentials()
	{
		return "{\r\n"
				+ "    \"email\": \"test1@email.com\",\r\n"
				+ "    \"password\": \"pass\" \r\n"
				+ "}";
	}
}
