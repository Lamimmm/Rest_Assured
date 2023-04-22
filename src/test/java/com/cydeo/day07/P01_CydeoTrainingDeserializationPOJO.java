package com.cydeo.day07;

import com.cydeo.pojo.Student;
import com.cydeo.pojo.Students;
import com.cydeo.utilities.CydeoTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P01_CydeoTrainingDeserializationPOJO extends CydeoTestBase {

    @Test
    public void test1() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
        .when()
                .pathParam("id", 2)
                .get("/student/{id}")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        Student student = jsonPath.getObject("students[0]", Student.class);
    }

    @Test
    public void test2() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .when()
                .get("/student/all")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        Students students = jsonPath.getObject("", Students.class);

        students.getStudents().get(0).getCompany().getAddress().getCity();
    }

}
