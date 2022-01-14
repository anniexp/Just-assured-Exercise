import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Soap {

    @Test
    public static void validateSoapXML () throws FileNotFoundException {


        File file = new File("target/classes/xmlDemo.xml");
        FileInputStream fileInputStream = new FileInputStream(file);

        String requestBody =fileInputStream.toString();

        RestAssured.given().header("Accept","text/xml").contentType("text/xml").accept(ContentType.XML).
                get("/events?CATALOG/CD[1]/TITLE");
    }
}
