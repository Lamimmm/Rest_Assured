package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SpartanWithParameters extends SpartanTestBase {
    /*   Given accept type is Json
        And Id parameter value is 24
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 200
        And response content-type: application/json
        And "Julio" should be in response payload(body)
     */

    @Test
    public void test1(){

        // Given accept type is Json
        //        And Id parameter value is 24
        //        When user sends GET request to /api/spartans/{id}

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 24)
                .when().get("/api/spartans/{id}");


       // Then response status code should be 200
        assertEquals(200,response.statusCode());

        //And response content-type: application/json
        assertEquals("application/json", response.contentType());

        //        And "Julio" should be in response payload(body)
        assertTrue(response.body().asString().contains("Julio"));
    }

    /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
      */
    @Test
    public void test2(){
        // Given accept type is Json
        // And Id parameter value is 500
        // When user sends GET request to /api/spartans/{id}

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 500)
                .when().get("/api/spartans/{id}");

        //Then response status code should be 404
        assertEquals(404, response.statusCode());

        // And response content-type: application/json
        assertEquals("application/json",response.contentType());

        // And "Not Found" message should be in response payload
        assertEquals("Not Found", response.body().asString());
    }

    /*
        Given Accept type is Json
        And query parameter values are:
            gender|Female
            nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("gender", "Female")
                .and()
                .queryParam("nameContains", "e")
        .when()
                .get("/api/spartans/search");

        assertEquals(200,response.getStatusCode());
        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }

    @Test
    public void test4(){
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("gender", "Female");
        queryMap.put("nameContains", "e");

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParams(queryMap)
        .when()
                .get("/api/spartans/search");

        assertEquals(200,response.getStatusCode());
        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }


}
