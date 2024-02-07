package com.endproject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RA_Dummy_API {

	public static void main(String[] args) {
		String URL = "https://dummy.restapiexample.com/api/v1";
		RestAssured.baseURI = URL;
		Response response = RestAssured.given().when().get("/employees");
		System.out.println(response.getStatusCode());
		System.out.println(response.body().asString());
		JsonPath jsonPath = response.jsonPath();

	}

}
