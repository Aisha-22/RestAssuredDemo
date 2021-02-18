package file;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

    //Create a Method

    public static JsonPath rawToJson(String response)
    {
        //Declare the JsonPath
        JsonPath js1 = new JsonPath(response);
        return js1;
    }
}
