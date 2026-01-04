package com.insiderone.API.ApiTests;


import com.insiderone.API.DataFiles.jsonPayload;
import com.insiderone.utilities.Driver;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class PostPets {

    private static Response response;

    @BeforeClass
    public void beforeClass() {
        //First way, getting baseURI from configuration.json file
//        Driver.setTestEnvironment();
//        System.out.println("Driver.testEnvironmentDetails.get(\"apiBaseUrl\") = " + Driver.testEnvironmentDetails.get("apiBaseUrl"));
//        RestAssured.baseURI = Driver.testEnvironmentDetails.get("apiBaseUrl");

        //Second way, setting baseURI in each BeforeMethod()
        RestAssured.baseURI="https://petstore.swagger.io/v2";
    }

    @Test
    public void positifTesting() {

    /*
    Post method ==> adding new pet
    */
        String response = given().log().all().header("Content-Type", "application/json").body(jsonPayload.addPets())
                .when().log().all().post("/pet")
                .then().log().all().assertThat().statusCode(200)
                .body("id", equalTo(123456)).body("status", equalTo("pending"), ("category.name"), equalTo("Kangal"))
                .header("Server", "Jetty(9.2.9.v20150224)")
                .extract().response().asString();

        JsonPath jsonPath = JsonPath.from(response);
        int petiUniqueId = jsonPath.getInt("id");

    /*
    Put method ==> updating the above pet info
    */
        given().log().all().header("Content-Type", "application/json").body(jsonPayload.updateExistingPets())
                .when().log().all().put("/pet")
                .then().log().all().assertThat().statusCode(200)
                .body("id", equalTo(petiUniqueId)).body("status", equalTo("available"), ("category.name"), equalTo("Kangal"))
                .header("Server", "Jetty(9.2.9.v20150224)").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");




    /*
    Get method ==> retrieving the above pet info
    */
        given().log().all().header("Content-Type", "application/json").body(jsonPayload.updateExistingPets())
                .when().log().all().get("/pet/" + petiUniqueId)
                .then().log().all().assertThat().statusCode(200)
                .body("id", equalTo(petiUniqueId)).body("status", equalTo("available"), ("category.name"), equalTo("Kangal"), "name", equalTo("KARABAS"))
                .header("Server", "Jetty(9.2.9.v20150224)").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").header("Access-Control-Allow-Headers", "Content-Type, api_key, Authorization");



    /*
    Delete method ==> removing the above pet info from DB
    */
        given().log().all().header("Content-Type", "application/json").body(jsonPayload.updateExistingPets())
                .when().log().all().delete("/pet/" + petiUniqueId)
                .then().log().all().assertThat().statusCode(200)
                .and().assertThat().body("code", equalTo(200),
                        "type", equalTo("unknown"), "message", equalTo(String.valueOf(petiUniqueId)))
                .and().assertThat().contentType("application/json")
                .and().assertThat().header("access-control-allow-headers", "Content-Type, api_key, Authorization");

    /*
    Get method ==> to make sure that the deleted pet info should be retrieved
    */
        given().log().all().header("Content-Type", "application/json").body(jsonPayload.updateExistingPets())
                .when().log().all().get("/pet/" + petiUniqueId)
                .then().log().all().assertThat().statusCode(404)
                .body("code", equalTo(1)).body("type", equalTo("error"), ("message"), equalTo("Pet not found"))
                .header("Server", "Jetty(9.2.9.v20150224)").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").header("Access-Control-Allow-Headers", "Content-Type, api_key, Authorization");


//        {
//            "code": 1,
//             "type": "error",
//             "message": "Pet not found"
//        }


    }

    @Test
    public void getpetResponseSchemaValidatio(){
          /*
    Post method ==> adding new pet
    */
         response = given().log().all().header("Content-Type", "application/json").body(jsonPayload.addPets())
                .when().log().all().post("/pet")
                .then()
                .extract().response();
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("apiTestData/get-json-schema-Pet.json"));

    }




    @Test
    public void negatifTestingForInvalidPetStatusValue() {
    /*
    Expected Result:
            StatusCode=400
    */

        Response post = given().log().all().header("Content-Type", "application/json").body(jsonPayload.addInvalidIdPetStatus())
                .when().log().all().post("/pet");
        System.out.println("post.statusCode() = " + post.statusCode()); //200

        Assert.assertEquals(post.statusCode(), 400);


    }

    @Test
    public void negatifTestingForInvalidPetStatusValueBodyResponseValidation() {

        /*
   Expected Body response:
   {
    "code": 400,
    "type": "unknown",
    "message": "bad input"
    }

*/
        given().log().all().header("Content-Type", "application/json").body(jsonPayload.addInvalidIdPetStatus())
                .when().log().all().post("/pet")
                .then().log().all().assertThat()
                .body("code", equalTo(400))
                .body("type", equalTo("unknown"))
                .body("message", containsString("bad input"));


    }

    @Test
    public void negatifTestingForInvalidStringNumberPetId() {
    /*
    For 'petId': Value must be an integer.
    and if 'petId' is string , statusCode should not be 200
    in this example, 'petId'= "123456" string consisting of number
     */
        given().log().all().header("Content-Type", "application/json").body(jsonPayload.addInvalidIdPetId())
                .when().log().all().post("/pet")
                .then().log().all()
                .assertThat()
                .statusCode(not(equalTo(200)));

    }


    @Test
    public void negatifTestingForInvalidStringCharcPetId() {
    /*
    For 'petId': Value must be an integer.
    and if 'petId' is string , statusCode should not be 200
    in this example, 'petId'= "abcde" string consisting of number
     */
        given().log().all().header("Content-Type", "application/json").body(jsonPayload.addInvalidIdPetIdWithLetters())
                .when().log().all().post("/pet")
                .then().log().all()
                .assertThat()
                .statusCode(not(equalTo(200)));

    }
}



