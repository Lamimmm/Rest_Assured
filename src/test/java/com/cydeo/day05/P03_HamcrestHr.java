package com.cydeo.day05;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P03_HamcrestHr extends HrTestBase {

    /*
       Given accept type is Json
       And parameters: q = {"job_id":"IT_PROG"}
       When users sends a GET request to "/employees"
       Then status code is 200
       And Content type is application/json
       Verify
           - each employees has manager
           - each employees working as IT_PROG
           - each of them getting salary greater than 3000
           - first names are .... (find proper method to check list against list)
           - emails without checking order (provide emails in different order,just make sure it has same emails)
           List<String> names = Arrays.asList("Alexander","Bruce","David","Valli","Diana");
           "DAUSTIN","AHUNOLD","BERNST","VPATABAL","DLORENTZ"
    */
    @Test
    public void test1() {
        List<String> names = Arrays.asList("Alexander","Bruce","David","Valli","Diana");

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
        .when()
                .get("/employees")
        .then()
                .assertThat()
                .statusCode(is(200))
                .and()
                .assertThat()
                .contentType(is("application/json"))
                .assertThat()
                .body("items.manager_id", everyItem(notNullValue()))
                .body("items.job_id", everyItem(is("IT_PROG")))
                .body("items.salary", everyItem(is(greaterThan(3000))))
                .body("items.first_name", equalTo(names))
                .body("items.email", containsInAnyOrder("DAUSTIN","AHUNOLD","BERNST","VPATABAL","DLORENTZ"))
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> jsonData = jsonPath.getMap("");

        List<Map<String, Object>> items = jsonPath.getList("items");

        for (Map<String, Object> each : items) {
            System.out.println("email => " + each.get("email"));
        }
    }

    /*
      Given
               accept type is application/json
       When
               user sends get request to /regions
       Then
               response status code must be 200
               verify Date has values
               first region name is Europe
               first region id is 1
               four regions we have
               region names are not null
               Regions name should be same order as "Europe","Americas","Asia","Middle East and Africa"
               region ids needs to be 1,2,3,4
               print all the regions names
               ...
               ..
               .
    */

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/regions")
                .then()
                .statusCode(is(200))
                .header("date", is(notNullValue()))
                .body("items[0].region_name", is("Europe"))
                .body("items[0].region_id", is(1))
                .body("items", hasSize(4))
                .body("items.region_name", everyItem(notNullValue()))
                .body("items.region_name", containsInRelativeOrder("Europe", "Americas", "Asia", "Middle East and Africa"))
                .body("items.region_id", containsInRelativeOrder(1, 2, 3, 4))
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.getList("items.region_name"));


    }

}
