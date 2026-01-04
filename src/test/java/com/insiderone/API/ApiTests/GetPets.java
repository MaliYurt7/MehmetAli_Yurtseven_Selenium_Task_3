package com.insiderone.API.ApiTests;

import com.insiderone.utilities.Driver;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static com.insiderone.utilities.Driver.testEnvironmentDetails;
import static io.restassured.RestAssured.given;

public class GetPets {


    @BeforeClass
    public void beforeClass() {
        //First way, getting baseURI from configuration.json file
//        Driver.setTestEnvironment();
//        RestAssured.baseURI = Driver.testEnvironmentDetails.get("apiBaseUrl");

        //Second way, setting baseURI in each BeforeMethod()
        RestAssured.baseURI="https://petstore.swagger.io/v2";

    }

    @Test
    public void deserilisationWayList() {

        Response response = given().queryParam("status", "available").contentType(ContentType.JSON)
                .when().log().all().get("/pet/findByStatus");

        Assert.assertEquals(response.statusCode(), 200);

        response.prettyPrint();


        List<Map<String, Object>> jsonToListOfMap = response.body().as(List.class); // deserilization completed with this line code
        System.out.println("jsonToListOfMap = " + jsonToListOfMap);


        Map<String, Object> firstElement = jsonToListOfMap.get(0);

        String expectedFirstCategoryName = "string";

// Get the 'category' map from the first element
        Map<String, Object> categoryMap = (Map<String, Object>) firstElement.get("category");


// Get the 'name' value from the 'category' map
        String categoryName = (String) categoryMap.get("name");

//Validate name in the first category
        Assert.assertTrue(categoryName.equalsIgnoreCase(expectedFirstCategoryName));
// Print the category name
        System.out.println("name=" + categoryName);

    }

}
