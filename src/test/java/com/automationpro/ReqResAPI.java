package com.automationpro;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ReqResAPI {

    @BeforeClass
    public void setup() {
        
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void createUserTest() {
      
        String requestBody = "{\n" +
                "  \"name\": \"John\",\n" +
                "  \"job\": \"leader\"\n" +
                "}";

       
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/api/users");

        
        Assert.assertEquals(response.statusCode(), 201, "Status code is not 201");

        Assert.assertEquals(response.jsonPath().getString("name"), "John", "Name is not matching");
        Assert.assertEquals(response.jsonPath().getString("job"), "leader", "Job is not matching");

 
        System.out.println("Response: " + response.asString());
    }
}

