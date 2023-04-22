package com.cydeo.day04;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class P01_SpartansWithJsonPath extends SpartanTestBase {
    /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
    */
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("api/spartans/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

//        response.body().prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        assertEquals(10, jsonPath.getInt("id"));
        assertEquals(Long.parseLong("3312820936"), jsonPath.getLong("phone"));
        assertEquals("Lorenza", jsonPath.getString("name"));
        assertEquals("Female", jsonPath.getString("gender"));




    }

}
