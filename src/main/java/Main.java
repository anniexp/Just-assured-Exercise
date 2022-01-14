import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class Main {


    @Test
public void test1 (){

    Response response = RestAssured.get("https://reqres.in/api/users?page=2");
    response.getStatusCode();
    response.getTime();
    System.out.println(response.getStatusCode());
    System.out.println( response.getTime());
    int statusCode = response.getStatusCode();

    int expStatusCode = 200;
        Assert.assertEquals(statusCode,expStatusCode);
}
@Test
public static void test2 (){
        RestAssured.baseURI = "https://reqres.in/api";
        RestAssured.given().get("/users?page=2").then().statusCode(200).body("data[1].id",equalTo(8)).log().all();

}

}
