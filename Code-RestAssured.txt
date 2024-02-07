package com.endproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ALL_Method {

	Response response;
	RestAssured restAssured;
	String URL = "https://reqres.in/api";

	@Before
	public void setup() {
		RestAssured.baseURI = URL;
	}

	@Test
	public void GET_all_record() {

		Response response = RestAssured.given().when().get("/users/2");

		System.out.println(response.getStatusCode());
		System.out.println(response.body().asString());

		JsonPath jsonPath = response.jsonPath();
		saveResponseToFile(response);
	}

	@Test
	public void Create_New_Reocord() {
		String requestBody = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
		response = RestAssured.given().body(requestBody).when().post("/api/users").then().statusCode(201).extract()
				.response();
		System.out.println(response.getStatusCode());
		System.out.println(response.body().asString());
		JsonPath jsonPath = response.jsonPath();
		saveResponseToFile(response);
	}

	@Test
	public void PUT() {
		String requestBody = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
		response = RestAssured.given().body(requestBody).when().put("/api/users/2").then().statusCode(200).extract()
				.response();
		System.out.println(response.getStatusCode());
		System.out.println(response.body().asString());
		JsonPath jsonPath = response.jsonPath();
		saveResponseToFile(response);
	}

	@Test
	public void Delete() {
		String requestBody = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
		response = RestAssured.given().body(requestBody).when().delete("/api/users/2").then().statusCode(204).extract()
				.response();
		System.out.println(response.getStatusCode());
		System.out.println(response.body().asString());
		JsonPath jsonPath = response.jsonPath();
		saveResponseToFile(response);
	}

	private String resultsFilePath = "F:/SImplilearn project/Java Projectes/RestAssured/results.txt";

	private void saveResponseToFile(Response response) {

		try {
			String content = "Status Code: " + response.getStatusCode() + "\n" + "Response Body:\n"
					+ response.body().asString() + "\n\n";

			// Use Files.write to write content to the file

			Files.write(Path.of(resultsFilePath), content.getBytes(), StandardOpenOption.APPEND);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
