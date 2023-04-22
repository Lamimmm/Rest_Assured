package com.cydeo.day06;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P02_SpartanDeserializationPOJO extends SpartanTestBase {

    @DisplayName("GET Single spartan for deserialization to POJO (Spartan class)")
    @Test
    public void test1() {
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}").prettyPeek()
                .then()
                .statusCode(200)
                .extract().response();

        Spartan spartan = response.as(Spartan.class);

        System.out.println(spartan.toString());
    }

    @Test
    public void test2(){
       Response response = given().accept(ContentType.JSON)
               .queryParam("nameContains", "a")
               .queryParam("gender", "Female")
               .when()
               .get("/api/spartans/search")
               .then()
               .statusCode(200)
               .extract().response();

       Search search = response.as(Search.class);

       System.out.println(search.content.get(1).getName());

       System.out.println("search.totalElement = " + search.totalElement);
   }
}
