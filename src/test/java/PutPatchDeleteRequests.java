import com.github.tsohr.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class PutPatchDeleteRequests {

    @Test
    public static void putTest1(){
        Map<String,Object > map = new HashMap<String,Object>();
        JSONObject jsonRequest = new JSONObject(map);

        jsonRequest.put("name","Ragnav");
        jsonRequest.put("job","Teacher");

        baseURI = "https://reqres.in/api";
        RestAssured.given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(jsonRequest).when().
                put("/users/2").then().statusCode(200).log().all();

    }

    @Test
    public static void putTest2(){
        Map<String,Object > map = new HashMap<String,Object>();


        //convert the hashmap into a json file
        JSONObject jsonRequest = new JSONObject(map);
          jsonRequest.put("first_name","Ameno");
        jsonRequest.put("last_name","Dorime");
        jsonRequest.put("email","emma@abv.bg");
        jsonRequest.put("avatar", "");
        System.out.println(jsonRequest);

        baseURI = "https://reqres.in/api";
        RestAssured.given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(jsonRequest).when().
                put("/users/2").then().statusCode(200).log().all();

    }

    @Test
    public static void patchTest1(){
        Map<String,Object > map = new HashMap<String,Object>();


        //convert the hashmap into a json file
        JSONObject jsonRequest = new JSONObject(map);
        // System.out.println(jsonRequest);

        jsonRequest.put("first_name","Kkekeke");
        jsonRequest.put("last_name","LLLLLLL");
        //  System.out.println(jsonRequest);

        baseURI = "https://reqres.in/api";
        RestAssured.given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(jsonRequest).when().
                patch("/users/1").then().statusCode(200).log().all();

    }
    @Test
    public static void deleteTest1(){

        baseURI = "https://reqres.in/api";
        RestAssured.given().

               when().
                delete("/users/2").
                then().statusCode(204).
                log().all();

    }

}
