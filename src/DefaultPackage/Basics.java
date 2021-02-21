package DefaultPackage;

import file.ReUsableMethods;
import file.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {
    public static void main(String[] args) {
        //Validated if AddPlace API is working as expected
        /*
        The principle of REST Assured
        //give - is all input details
        //when - Submit the API
        //Then - validated the response
         */
        //Add place-> Update Place with New Address-> Get place to validate if New address is presented in response

        //This is called the Chain process - CRUD Operations is used.
        //Create -> POST
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        //Declared a String 'response'
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(payload.AddPlace()).when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
                .body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();//Retrieve -> GET

        //Print to response in the output
        System.out.println(response);
        //Extracting 'Place id' - By passing Json
        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");//Id created from POST

        System.out.println(placeId);

        //Update -> PUT
        String newAddress = "06 Southdale Drive, ACACIA House A6, Southdale";

                given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeId + "\",\n" +
                        "\"address\":\"" + newAddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

        //Get place API -> to make sure change were made
       String getPlaceResponse =  given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();

       //To return as one object
        JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");

        //Assertions -> Validation/check point for an Application -> to match weather expected to actual if not test will fail
        Assert.assertEquals(actualAddress, newAddress);

        System.out.println(actualAddress);
    }
}
