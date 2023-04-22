package com.cydeo.day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P04_DeserializationToCollections extends SpartanTestBase {

      /*
     Given accept type is application/json
     And Path param id = 10
     When I send GET request to /api/spartans
     Then status code is 200
     And content type is json
     And spartan data matching:
         id > 10
         name>Lorenza
         gender >Female
         phone >3312820936

       */

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 10)
        .when()
                .get("/api/spartans/{id}")
        .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .extract().response();

        List<Object> spartans = response.as(List.class);

    }


}
