package com.cydeo.day04;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_HrWithJsonPath extends HrTestBase {
    @Test
    public void test1() {
        Response response = get("/countries");

        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        // gpath syntax
        jsonPath.getString("items[1].country_name");
        jsonPath.getList("items[2,3].country_name");

        List<String> countries = jsonPath.getList("items.findAll {it.region_id==2}.country_name");

        System.out.println("countries: " + countries);
    }

    /*
    Given accept type is application/json
    And query param limit is 200
    When user send request /employees
    Then user should see ............
     */
    @Test
    public void teat2(){
        Response response = given().accept(ContentType.JSON)
                .queryParam("limit", 200)
                .when().get("/employees");

        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        //get all emails from response
        jsonPath.getList("items.email");

        //get all emails who is working as IT_PROG
        List<String> emails = jsonPath.getList("items.findAll {it.job_id=='IT_PROG'}.email");
        System.out.println(emails);


        //get me all employees first names whose salary is more than 10000
        List<String> employees = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println(employees);

        //get me all information from response who has max salary
        String allInfo = jsonPath.getString("items.max {it.salary}");

        //get me first name from response who has max salary
        String nameMaxSalary = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println(nameMaxSalary);


        //get me firstname from response who has min salary

        String minSalary = jsonPath.getString("items.min {it.salary}.first_name");
        System.out.println(minSalary);


    }

    /*
    TASK
    Given
             accept type is application/json
     When
             user sends get request to /locaitons
     Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK
      */
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/locations");

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        JsonPath jsonPath = response.jsonPath();

        //get the second city with JsonPath
        System.out.println(jsonPath.getString("items[1].city"));

        //get the last city with JsonPath
        System.out.println(jsonPath.getString("items[-1].city"));

        //get all country ids
        System.out.println(jsonPath.getList("items.location_id"));

        //get all city where their country id is UK
        System.out.println(jsonPath.getList("items.findAll {it.country_id=='UK'}.city"));
    }

}
