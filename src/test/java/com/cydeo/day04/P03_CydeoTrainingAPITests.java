package com.cydeo.day04;

import com.cydeo.utilities.CydeoTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_CydeoTrainingAPITests extends CydeoTestBase {
    /*
    Given accept type is application/json
    And path param id is 2
    When user send request /student/{id}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is envoy
    And verify following
                firstName Mark
                batch 13
                major math
                emailAddress mark@email.com
                companyName Cydeo
                street 777 5th Ave
                zipCode 33222
    */
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when().get("/student/{id}");

        assertEquals(200, response.statusCode());

        assertEquals("application/json;charset=UTF-8", response.contentType());

        assertTrue(response.headers().hasHeaderWithName("Date"));

        assertEquals("envoy", response.header("Server"));

        JsonPath jsonPath = response.jsonPath();

        assertEquals("Mark", jsonPath.getString("students[0].firstName"));
        assertEquals(13, jsonPath.getInt("students[0].batch"));
        assertEquals("math", jsonPath.getString("students[0].major"));
        assertEquals("mark@email.com", jsonPath.getString("students[0].contact.emailAddress"));
        assertEquals("Cydeo", jsonPath.getString("students[0].company.companyName"));
        assertEquals("777 5th Ave", jsonPath.getString("students[0].company.address.street"));
        assertEquals(33222, jsonPath.getInt("students[0].company.address.zipCode"));
    }

    /*
    TASK
    Given accept type is application/json
    And path param is 22
    When user send request /student/batch/{batch}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is envoy
    And verify all the batch number is 22
    */
    @Test
    public void test2() {
        given().accept(ContentType.JSON)
                .pathParam("batch", 22)
        .when()
                .get("/student/batch/{batch}")
        .then()
                .statusCode(200)
                .contentType("application/json;charset=UTF-8");

//        assertEquals(200, response.statusCode());
//
//        assertEquals("application/json;charset=UTF-8",response.contentType());
//
//        response.headers().hasHeaderWithName("Date");
//
//        assertEquals("envoy", response.header("Server"));

//        assertTrue();

    }
}
