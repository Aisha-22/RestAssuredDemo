package file;

public class payload {

    //Creating a Method - making the method static so I can call method without creating any object for the class/by calling className.MethodName
    public static String AddPlace()
    { //Body will be sent from Json file
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house\",\n" +
                "  \"phone_number\": \"(+27) 11 680 3937\",\n" +
                "  \"address\": \"48, Brietta street, Ridgeway Ext4\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://rahulshettyacademy.com\",\n" +
                "  \"language\": \"English-IN\"\n" +
                "}";
    }
}
