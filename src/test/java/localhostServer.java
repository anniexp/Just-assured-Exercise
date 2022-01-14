import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.cliftonlabs.json_simple.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.Matchers.*;

public class localhostServer {


    @Test
    public static void getTest() {

        baseURI = "http://localhost:3000/";
        RestAssured.given().get("/posts").
                then().
                statusCode(200).log().all()
             ;

    }
    @Test
    public static void postTest2(){


        JsonObject value = new JsonObject();
        value.put("first_name","Ameme");
        value.put("last_name","Dorime");
        value.put("email", "emma@abv.bg");


        baseURI = "http://localhost:3000/";
        RestAssured.given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(value).when().
                post("/posts").then().statusCode(201).log().all();

    }
    //testing post request with asserting first_name
    @Test
    public static void postTest3(){


        JsonObject value = new JsonObject();
        value.put("first_name","Koll");
        value.put("last_name","George");
        value.put("email", "emmhhhha@gmil.com");


        baseURI = "http://localhost:3000/";
        RestAssured.given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(value).when().
                post("/posts").then().statusCode(201).log().all().body("first_name", equalTo("Koll")).log().all();

    }
    //test put(update) request code and assert first_name
    @Test
    public static void putTest1( ){
        String firstNameValue = "Koll";
        String lastNameValue = "George";
        String email =  "emmhhhha@gmil.com";

        JsonObject value = new JsonObject();
        value.put("first_name",firstNameValue);
        value.put("last_name",lastNameValue);
        value.put("email", email);


        baseURI = "http://localhost:3000/";
        RestAssured.given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(value).when().
                put("/posts/2").then().statusCode(200).log().all().body("first_name", equalTo("Koll")).log().all();;;


    }

    @Test
    public static void patchTest1(){
        String firstNameValue = "Shokollade";

        JsonObject value = new JsonObject();
        value.put("first_name",firstNameValue);

        baseURI = "http://localhost:3000/";
        RestAssured.given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(value).when().
                patch("/posts/2").then().statusCode(200).log().all().
                body("first_name", equalTo("Shokollade")).
                body("last_name", equalTo("George")).log().all();


    }
    //test patch request with not existing property
    @Test
    public static void patchTest2(){
        String quote = "Shokollade";

        JsonObject value = new JsonObject();
        //patch an attribute which wasn't in the file
        value.put("quote",quote);


        //uri to local json server
        baseURI = "http://localhost:3000/";
        RestAssured.given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(value).when().
                patch("/posts/2").then().statusCode(200).log().all().

                body("last_name", equalTo("George")).log().all();


    }
    // test delete request
    @Test
    public static void deleteTest(){
        baseURI = "http://localhost:3000/";
        RestAssured.given().when().delete("/posts/6").then().statusCode(200).log().all();


    }
    //test delete request for not existing record
    @Test
    public static void deleteTest2(){
        baseURI = "http://localhost:3000/";
        RestAssured.given().
                //try to delete record which does not exist in the db.json file, so a http response 404 is expected
                when().delete("/posts/7").then().statusCode(404).log().all();


    }



}
