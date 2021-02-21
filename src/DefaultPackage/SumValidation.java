package DefaultPackage;

import file.payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    @Test//This is who you can wrap your test in testNG Irritation, & iterate test without using without using 'public static void main'
    public void sumOfCourses()
    {
        //Created a sum variable and initialized it to '0'
        int sum = 0;
        //Create JsonPath class -> Pass the response of Json in String format
        JsonPath js = new JsonPath(payload.CoursePrice());
        //Print No. of courses returned by API (Course is array list is dynamic and changes with the number of copies sold)
        int count = js.getInt("courses.size()");


        //Iterate through the courses to get 'price and copies'
        for(int i = 0; i < count; i++)
        {
            int price = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");

            int totalAmount = price * copies;
            System.out.println(totalAmount);

            sum = sum + totalAmount;

        }
        System.out.println(sum);
        int actualTotalAmount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum, actualTotalAmount);//Validation of application, you have to compare expected value and actual value
        /*Issue of Response -> java.lang.AssertionError: expected [910] but found [1162]
        Alert developer team/raise a bug that automation caught a defect, when it sum's up its not matching with actual amount.
        Which the testcase maybe asking you to validate your automation and if that's the requirement then the above is how you
        will bring up your logic
         */
    }
}
