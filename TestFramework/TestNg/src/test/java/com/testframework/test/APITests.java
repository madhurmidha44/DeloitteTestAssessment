package com.testframework.test;
import com.relevantcodes.extentreports.LogStatus;
import com.testframework.corelibrary.api.GetAPIMethods;
import com.testframework.corelibrary.api.PostAPIMethods;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//Sample test cases for APIs'
public class APITests extends TestBaseForAPITesting{

	//Validate Get API (without params) testing
	@Test
	public void GetDetailsOfAllBooks() throws InterruptedException
	{
		//Fetch test data
		String endpoint=properties.getProperty("endpointBookstore");
		String pathForBooks=properties.getProperty("pathForBooks");
		//Sending request
		Response response= GetAPIMethods.responseForGetMethod(endpoint, pathForBooks);
		// Print the status and message body of the response received from the server
		System.out.println("Status received => " + response.getStatusLine());
		ResponseBody body = response.getBody();
		// By using the ResponseBody.asString() method, we can convert the  body
		// into the string representation.
		System.out.println("Response Body is: " + body.asString());
		System.out.println("Response=>" + response.prettyPrint());
		test.log(LogStatus.INFO, response.prettyPrint());
		test.log(LogStatus.PASS, "Passed");
	}
	@Test
	public void getDetailsOfSpecifiedBook() throws InterruptedException
	{
		//fetching test data
		String endpoint=properties.getProperty("endpointBookstore");
		String pathForBook=properties.getProperty("pathForBook");
		String queryParams=properties.getProperty("queryParamsForBook");

		//Sending request and receving response
		Response response= GetAPIMethods.responseForGetMethod(endpoint,pathForBook, queryParams);

		//Retrieving the response body using getBody() method
		ResponseBody body = response.body();

		//Converting the response body to string object
		String rbdy = body.asString();

		//Creating object of JsonPath and passing the string response body as parameter
		JsonPath jPath = new JsonPath(rbdy);

		//Storing publisher name in a string variable
		String title = jPath.getString("title");

		//Print the retrieved title of the book
		System.out.println("The book title is - "+title);

		//Report title of the book in the test case  in the report
		test.log(LogStatus.INFO, "The book title is - "+title);

	}

	@Test
	public void postDetailsOfUser()
	{
		//fetching test data
		String endpoint=properties.getProperty("endpointJsonPlaceHolder");
		String requestParams=properties.getProperty("requestParamsJsonPlaceHolder");
		//Get  response of Post API
		Response response= PostAPIMethods.responseForPostMethodWithRequestBody(endpoint, requestParams);
		// Get status code and response body
		String responseBody=response.getBody().prettyPrint();
		System.out.println("Response body is - "+responseBody);
		test.log(LogStatus.INFO, "Response body is - "+responseBody);
		int statusCode=response.statusCode();
		System.out.println("Status code is - "+statusCode);
	}



}
