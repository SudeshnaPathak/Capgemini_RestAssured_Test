package dataUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;

public class ReadDataFromPropertiesFile {
	public static String email;
	public static String password;
	public static String baseUrl;
	public static String browser;
	public static String role;
	public static String zoneId;
	
	@BeforeSuite
	public static void fetchData() throws IOException{
		//Convert physical representation of properties file into java representation
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Sudeshna Pathak\\eclipse-workspace\\Capgemini_RestAssured\\src\\test\\resources\\configData\\configData.properties");
		
		//Using Properties class load all the keys
		Properties p = new Properties();
		p.load(fis);
		
		//Fetch the value using keys
		email = p.getProperty("email");
		System.out.println("Email:" + email);
		
		password = p.getProperty("password");
		System.out.println("Password: " + password);
		
		baseUrl = p.getProperty("url");
		System.out.println("Url: " + baseUrl);
		
		browser = p.getProperty("browser");
		System.out.println("Browser: " + browser);
		
		role = p.getProperty("role");
		System.out.println("Role: " + role);
		
		zoneId = p.getProperty("zoneId");
		System.out.println("zoneId: " + zoneId);

	}

}
