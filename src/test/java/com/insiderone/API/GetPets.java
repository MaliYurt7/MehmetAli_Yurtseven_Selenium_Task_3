package com.insiderone.API;

import com.insiderone.utilities.Driver;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static com.insiderone.utilities.Driver.testEnvironmentDetails;
import static io.restassured.RestAssured.given;

public class GetPets {


    @BeforeMethod
    public void beforeClass() {
        Driver.setTestEnvironment();
        RestAssured.baseURI = Driver.testEnvironmentDetails.get("apiBaseUrl");
        ;

    }

    @Test
    public void deserilisationWayList() {

        Response response = given().queryParam("status", "available").contentType(ContentType.JSON)
                .when().log().all().get("/pet/findByStatus");

        Assert.assertEquals(response.statusCode(), 200);

        response.prettyPrint();


        List<Map<String, Object>> jsonToListOfMap = response.body().as(List.class); // deserilization completed with this line code
        System.out.println("jsonToListOfMap = " + jsonToListOfMap);
        //jsonToListOfMap = [{id=3.0, category={id=2.0, name=Cats}, name=Cat 3, photoUrls=[url1, url2], tags=[{id=1.0, name=tag3}, {id=2.0, name=tag4}], status=pending}, {id=6.0, category={id=1.0, name=Dogs}, name=Dog 3, photoUrls=[url1, url2], tags=[{id=1.0, name=tag3}, {id=2.0, name=tag4}], status=pending}]


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
