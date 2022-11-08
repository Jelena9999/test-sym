package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class TestOne {

    @Test
    public void test_1(){

        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
    }

    @Test
    public void test_2(){

        var baseURI="https://reqres.in/api";
        RestAssured
                .given()
                    .baseUri(baseURI)
                    .get("/users?page=2")
                .then()
                    .statusCode(200)
                .body("data[1].id",equalTo(8));
    }

    @Test
    public void test_3(){

        var baseURI="https://reqres.in/api";

        RestAssured.given()
                        .baseUri(baseURI)
                         .get("/users?page=2")
                .then().
                        statusCode(200).
                        body("data[4].first_name",equalTo("George")).
                        body("data.first_name",hasItems("George","Rachel"));
    }

    @Test
    public void testPost(){
        Map<String,Object> map = new HashMap<String,Object> ();

        map.put("name","Raghav");
        map.put("job","Teacher");

        System.out.println(map);
    }
}
