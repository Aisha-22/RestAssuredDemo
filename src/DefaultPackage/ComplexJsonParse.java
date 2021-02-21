package DefaultPackage;
import java.util.stream.Stream;

import file.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {

        //Create JsonPath class -> Pass the response of Json in String format
        JsonPath js = new JsonPath(payload.CoursePrice()); //Mocked the response

        //Print No. of courses returned by API (Course is array list is dynamic and changes with the number of copies sold)
        int count = js.getInt("courses.size()");/*'size()' is a method used in JsonPath to get the count the size is only applied on Arrays (Nothing but just a
         number of elements nested in an Array*/
        System.out.println(count);
        //Print Purchase Amount -> *NB: Make sure you put a 'DOT' when traversing from parent to child
        int totalAmount = js.getInt("dashboard.purchaseAmount"); //Stored in one variable
        System.out.println(totalAmount);
        //Print Title of the first course ->
        String firstCourseTitle = js.getString("courses[0].title");
        System.out.println(firstCourseTitle);
        //Print All course titles and their respective Prices

        //Used Loops to dynamically drive everything in the for Loop
        for(int i = 0; i < count; i++)
        {
            String courseTitles = js.get("courses["+ i +"].title");
            System.out.println(courseTitles);
            System.out.println(js.get("courses["+ i +"].price").toString());
        }
        System.out.println("Print no of copies sold by RPA Course");
        for(int i = 0; i < count; i++)
        {
            String courseTitles = js.get("courses["+ i +"].title");
            //Insert a check point
            if(courseTitles.equalsIgnoreCase("RPA"))//equalsIgnoreCase() -> if it's lower/upper Case ignore
            {
                //Come into this block and print copies
                int copies = js.get("courses["+ i +"].copies");
                System.out.println(copies);
                break;
            }
        }

    }
}
