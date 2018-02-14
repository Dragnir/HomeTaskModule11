package hometaskmodule11.tests;

import hometaskmodule11.model.user.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTest {

	@BeforeTest
	public void initTest() {
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
	}
	
	@Test
	public void checkStatusCode() {
		Response rp = given().get("/users").andReturn();
		String statusLine = rp.getStatusLine();
		System.out.println(statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	public void checkResponseHeader() {
		Response rp = given().get("/users").andReturn();
		String valueOfContentTypeHeader = rp.getHeader("content-type");
		System.out.println(valueOfContentTypeHeader);
		Assert.assertTrue(valueOfContentTypeHeader.contains("application/json"));
	}

	@Test
	public void checkResponseBody() {
		Response rp = given().get("/users").andReturn();
		User[] users = rp.as(User[].class);
		System.out.println(users.length);
		Assert.assertEquals(users.length, 10);
	}
	
}
