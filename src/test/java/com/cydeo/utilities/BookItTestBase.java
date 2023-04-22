package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BookItTestBase {


    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "https://api.qa.bookit.cydeo.com";
    }
}
