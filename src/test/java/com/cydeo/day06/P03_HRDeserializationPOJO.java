package com.cydeo.day06;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Region;
import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class P03_HRDeserializationPOJO extends HrTestBase {
    @DisplayName("GET regions to desserializate to POJO - LOMBOK -JSON PROPERTY")
    @Test
    public void test1(){

        JsonPath jsonPath = get("/regions")
                .then().statusCode(200)
                .extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println("region1 = " + region1);
    }

    @DisplayName("GET employee to deserialization to POJO with only required fields")
    @Test
    public void test2() {

        JsonPath jsonPath = get("/employees")
                .then().statusCode(200)
                .extract().jsonPath();

        Employee employee1 = jsonPath.getObject("items[0]", Employee.class);

        System.out.println("employee1 = " + employee1);

    }

    /*
    TASK
    Given accept is application/json
    When send request  to /regions endpoint
    Then status should be 200
            verify that region ids are 1,2,3,4
            verify that regions names Europe ,Americas , Asia, Middle East and Africa
            verify that count is 4
        -- Create Regions POJO
        -- And ignore field that you dont need
     */

    @Test
    public void test3() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when()
                .get("/regions")
                .then()
                .statusCode(200)
                .extract().jsonPath();

//        List<Region> regions = jsonPath.getList("items",Region.class);

        //verify that region ids are 1,2,3,4
        List<Object> regionIds = jsonPath.getList("items.region_id");
        assertThat(regionIds, containsInRelativeOrder(1, 2, 3, 4));

        //verify that regions names Europe ,Americas , Asia, Middle East and Africa
        List<String> regionNames = jsonPath.getList("items.region_name");
        assertThat(regionNames, containsInRelativeOrder("Europe", "Americas", "Asia", "Middle East and Africa"));

        int count = jsonPath.getInt("count");
        assertThat(count, is(4));






    }
}
