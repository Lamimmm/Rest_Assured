package com.cydeo.day06;

import com.cydeo.utilities.HrTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class P01_HRDeserialization extends HrTestBase {

    /**
     * Create a test called getLocation
     * 1. Send request to GET /locations
     * 2. log uri to see
     * 3. Get all Json as a map and print out screen all the things with the help of  map
     * System.out.println("====== GET FIRST LOCATION  ======");
     * System.out.println("====== GET FIRST LOCATION LINKS  ======");
     * System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
     * System.out.println("====== FIRST LOCATION ======");
     * System.out.println("====== FIRST LOCATION ID ======");
     * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
     * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
     */

    @DisplayName("GET /locations to deserialization into Java Collections")
    @Test
    public void  test1() {
        Response response = given().log().uri()
                .get("/locations");

        // * System.out.println("====== GET FIRST LOCATION  ======");
        Map<String,Object> firstLocation = response.jsonPath().getMap("items[0]");
       // System.out.println("firstLocation => " + firstLocation);

        // * System.out.println("====== GET FIRST LOCATION LINKS  ======");
       // System.out.println(firstLocation.get("links"));

        //* System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
        List<Map<String,Object>> allLocationSukaBlyat = response.jsonPath().getList("items");
       // System.out.println(allLocationSukaBlyat);

        //* System.out.println("====== FIRST LOCATION ======");
      //  System.out.println(allLocationSukaBlyat.get(0));

        //* System.out.println("====== FIRST LOCATION ID ======");
        //System.out.println(allLocationSukaBlyat.get(0).get("location_id"));

        //* System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
      //  System.out.println(allLocationSukaBlyat.get(0).get("country_id"));

        //* System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
        List<Map<String ,Object>> firstLocationLinkS = (List<Map<String, Object>>) allLocationSukaBlyat.get(0).get("links");
        System.out.println(firstLocationLinkS.get(0));
    }

}
