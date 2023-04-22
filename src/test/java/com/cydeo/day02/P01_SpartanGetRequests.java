package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanGetRequests {

    String url = "http://54.157.67.42:8000";



    /*
    Given content type is application/json
    When user send GET request /api/spartans  endpoint
    Then status code should be 200
    And Content type should be application/json

     */

    @Test
    public void getAllSpartans(){
        Response response = RestAssured.given()
                .accept(ContentType.JSON) //hey api please send me json response
                .when()
                .get(url + "/api/spartans");


        //print the response body
      //  response.prettyPrint();


        //how to get status code
        int actualStatusCode = response.statusCode();

        Assertions.assertEquals(200,actualStatusCode);

        //how to get response content type header?
        String actualContentType = response.contentType();
        System.out.println(actualContentType);

        //assert the content type
        Assertions.assertEquals("application/json",actualContentType);

        //how to get Connection header value?
        //if we want to get any response header value, we can use header ("headerNAme")
        //method from response object. it will return header value as string
        System.out.println(response.header("Content-type"));
        System.out.println(response.header("Connection"));
        System.out.println(response.header("Date"));

        //how to verify header exist?
        //hasHeaderWithNAme method help us to verify header exist or not
        //it is useful for dynamic header values like Date, we are only verifiying header ->
        // exist or not, not showing the value
        boolean isDate = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(isDate);
    }

    /*




     */

    @Test
    public void getSpartan(){

      //  Given content type is application/json
     //  When user sends GET request /api/spartans/3 endpoint
        Response response = RestAssured.given()
                .accept(ContentType.JSON) //hey api please send me json response
                .when()
                .get(url + "/api/spartans/3");


        // Then status code should be 200
        int actualStatusCode = response.statusCode();

        Assertions.assertEquals(200,actualStatusCode);

        // And Content type should be application/json
        String actualContentType = response.contentType();
        Assertions.assertEquals("application/json",actualContentType);


        // And response body needs to contains Fidole
        response.prettyPrint();
     //Verify body contains "Fidole"
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

        /*
        This is  not good was to make assertion. In this way we are just converting response to String
        and with the help of String contains we are just looking into Response.
      But we should be able to get json
      "name" key value then verify that one is equal to "Fidole
         */

    }

    /*
    Given no headers provided
    When User send GET request to /api/hello
    Then response status code should be 200
    ANd Content type header should be "text/plain:charset=UTF-8"
    ANd header should contain Date
    And Content - Length should be 17
    And body should be " Hello from Sparta "

     */

    @Test
    public void helloSpartan(){


        // Given no headers provided
        //    When User send GET request to /api/hello

        Response response = RestAssured.when().get(url+ "/api/hello");
        //print result

        //    Then response status code should be 200
        Assertions.assertEquals(200,response.getStatusCode());

        //    ANd Content type header should be "text/plain:charset=UTF-8"
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

        //    And header should contain Date
        boolean date = response.headers().hasHeaderWithName("Date");
        Assertions.assertTrue(date);

        //    And Content - Length should be 17
       // int length = response.contentType().length();
        Assertions.assertEquals("17",response.header("Content-Length"));

        //    And body should be "Hello from Sparta"
        String actualBody = response.body().asString();
        Assertions.assertEquals("Hello from Sparta",actualBody);



    }

}
