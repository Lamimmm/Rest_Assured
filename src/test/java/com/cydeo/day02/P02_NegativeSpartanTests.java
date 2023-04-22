package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class P02_NegativeSpartanTests {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.157.67.42:8000";
    }

    /*
    Given accept content type is application/json
    When user sends GET request /api/spartans endpoint
    Then status code should be 200
     */

    @Test
    public void getAllSpartans(){


        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans");

        Assertions.assertEquals(200,response);

        response.prettyPrint();



    }

    @Test
    public void xmlTest(){
        Response response1 = RestAssured.given().accept(ContentType.XML)
                .when().get("/api/spartans/10");


        Assertions.assertEquals(406,response1.statusCode());

        //Assertions.assertEquals();
    }



    /*
    Given Accept type application/xml
    When user send GET request to /api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
     */

}
