import file.ReUsableMethods;
import file.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class DynamicJson {

    @Test//You can create a test without using Public Static void main
    public void addBook()
    {
        //Store the response in a variable
      RestAssured.baseURI = "http://216.10.245.166";
        //POST request you use header() method and pass parameters
        String response = given().log().all().header("Content-Type", "application/json")
                //pass the body
        .body(payload.addBook("Great Story about Aisha", "Edition#01.08/03/2021"))//Sending parameters to payload
                .when()
                //give the statement post -> you need to pass the resource
        .post("Library/Addbook.php")
                //Start the assertion to verify status code
        .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        //The ReUsableMethod to convert this into Json, rawToJson is created to pass your string
        JsonPath js1 = ReUsableMethods.rawToJson(response);
        System.out.println(js1);
        //The goal is to retrieve "ID" and store it in a String variable
        String id = js1.getString("ID");

        //Add and Delete, for the next time to be ready with clean data.

    }
}
