import com.github.tsohr.JSONArray;
import com.github.tsohr.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.lang.Object;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class GetAndPostRequests {
    @Test
    public static void getTest1() {

        baseURI = "https://reqres.in/api";
        RestAssured.given().get("/users?page=1/2").
                then().
                //assert status code is 200 -OK
                statusCode(200).
                //assert the fifth object has first_name = George
                body("data[4].first_name", equalTo("George")).
                body("data.first_name", hasItems("George", "Rachel")).log().all();

    }
    @Test
    public static void getSingleUser() {

        baseURI = "https://reqres.in/api";
        RestAssured.given().get("/users/2").
                then().
                statusCode(200).
                body("data.email", equalTo("janet.weaver@reqres.in")).log().all();

    }
    @Test
    public static void getSingleUserNotFound() {

        baseURI = "https://reqres.in/api";
        RestAssured.given().get("/users/20000").
                then().
                statusCode(404).
                body("data.email", equalTo(null)).log().all();

    }
    @Test
    public static void getUser() {

        baseURI = "https://reqres.in/api";
        RestAssured.given().get("/users?page=1&id=2").
                then().
                statusCode(200).log().all().
                body("data.email", endsWith(".in")).log().all();

    }

    @Test
    public static void postTest1(){
        Map<String,Object > map = new HashMap<String,Object>();


        //convert the hashmap into a json file
        JSONObject jsonRequest = new JSONObject(map);
        System.out.println(jsonRequest);

        jsonRequest.put("name","Ragnav");
        jsonRequest.put("job","Teacher");
        System.out.println(jsonRequest);

        baseURI = "https://reqres.in/api";
        RestAssured.given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(jsonRequest).when().
               post("/users").then().statusCode(201).log().all();

    }
    @Test
    public static void postTest2(){


        JSONObject jsonRequest = new JSONObject();

        jsonRequest.put("first_name","Ameno");
        jsonRequest.put("last_name","Dorime");
        jsonRequest.put("email","emma@abv.bg");
        jsonRequest.put("avatar", "");
        System.out.println(jsonRequest);
      System.out.println(jsonRequest.length());

        baseURI = "https://reqres.in/api";
        RestAssured.given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(jsonRequest).when().
                post("/users").then().statusCode(201).log().all().body("first_name", equalTo("Ameno")).log().all();;

    }

    //test json schema get and assert that the schema is correct to the .json file and match
    @Test
    public static void getTest2(){

        baseURI = "https://reqres.in/api";
        RestAssured.given().get("/users?page=2").
                then().
                assertThat().body(matchesJsonSchemaInClasspath("demo.json")).
                statusCode(200).log().all();
    }





}
