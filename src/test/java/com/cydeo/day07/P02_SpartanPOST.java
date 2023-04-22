package com.cydeo.day07;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class P02_SpartanPOST extends SpartanTestBase {

    /**
     Given accept type is JSON
     And Content type is JSON
     And request json body is:
     {
     "gender":"Male",
     "name":"John Doe",
     "phone":8877445596
     }
     When user sends POST request to '/api/spartans'
     Then status code 201
     And content type should be application/json
     And json payload/response/body should contain:
     verify the success value is 'A Spartan is Born!'
     "name": "John Doe",
     "gender": "Male",
     "phone": 8877445596
     */
    @DisplayName("POST Spartan with String body")
    @Test
    public void test1(){

        Map<String, Object> newSpartan = new LinkedHashMap<>();
        newSpartan.put("gender", "Male");
        newSpartan.put("name", "John Doe");
        newSpartan.put("phone", "8877445596");

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(newSpartan)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        assertThat(jsonPath.getString("success"), is("A Spartan is Born!"));
        assertThat(jsonPath.getString("data.name"), is("John Doe"));
        assertThat(jsonPath.getString("data.gender"), is("Male"));
        assertThat(jsonPath.getString("data.phone"), is("8877445596"));

    }
    @DisplayName("POST Spartan with Spartan POJO and GET same Spartan")
    @Test
    public void test4(){

        Spartan newSpartan = new Spartan();

        newSpartan.setName("Beka");
        newSpartan.setGender("Male");
        newSpartan.setPhone(Long.parseLong("5666677911"));

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(newSpartan)
                .when()
                .post("/api/spartans").prettyPeek()
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        assertThat(jsonPath.getString("data.name"), is("Beka"));
        assertThat(jsonPath.getString("data.gender"), is("Male"));
        assertThat(jsonPath.getString("data.phone"), is("5666677911"));

        int id = jsonPath.getInt("data.id");

        System.out.println("id = " + id);


        Response response = given().accept(ContentType.JSON)
                .pathParam("id", id)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        Spartan spartan = response.as(Spartan.class);

        System.out.println("spartan = " + spartan);

    }


}
