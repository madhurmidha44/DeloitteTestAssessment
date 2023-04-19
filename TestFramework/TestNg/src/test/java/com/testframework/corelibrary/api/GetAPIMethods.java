package com.testframework.corelibrary.api;
import java.util.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAPIMethods {

    //Retrieve response for Get API request
    public static Response responseForGetMethod(String endpoint, String path)
    {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = endpoint;

        // Get the RequestSpecification of the request to be sent to the server.
        RequestSpecification httpRequest = RestAssured.given();

        // specify the method type (GET) and the parameters if any.
        //In this case the request does not take any parameters
        Response response = httpRequest.request(Method.GET, path);
        return response;
    }

    //Retrieve response for Get API request with input query params in the request
    //Example of static Polymorphism: Method  Overloading
    public static Response responseForGetMethod(String endpoint, String path, String queryParams)
    {
        //Defining the base URI
        RestAssured.baseURI= endpoint;
        RequestSpecification httpRequest = RestAssured.given();
        Response response=null;
        //get params in hashmap
        HashMap<String,String> queryParamsData=getParamsInHashMap(queryParams);
        //passing the parameters from hashmap in to request
        httpRequest.queryParams(queryParamsData);
        //Passing the resource details
        response = httpRequest.get(path);
        return response;
    }

    //Retrieve response for Get API request with input path params in the request
    public static Response responseForGetMethodWithPathParams(String endpoint, String path, String queryParams)
    {
        //Defining the base URI
        RestAssured.baseURI= endpoint;
        RequestSpecification httpRequest = RestAssured.given();
        Response response=null;
        //get params in hashmap
        HashMap<String,String> queryParamsData=getParamsInHashMap(queryParams);
        //passing the parameters from hashmap in to request
        httpRequest.pathParams(queryParamsData);
        //Passing the resource details
        response = httpRequest.get(path);
        return response;
    }

    //Convert Params in key-value pair from properties file to HashMap
    public static HashMap<String,String> getParamsInHashMap(String params)
    {
        String[] paramsData=params.split(";");
        HashMap<String,String> paramsInHashmap=new HashMap();
        for(String param:paramsData) {
            paramsInHashmap.put(param.split(",")[0], param.split(",")[1].replace(";", ""));
        }
        return paramsInHashmap;
    }


    /* Implement authentication details in the API request
    RequestSpecification httpRequest = RestAssured.given().auth().basic("postman", "password");
		RestAssured.given().auth().preemptive().basic("your username", "your password").get("your end point URL");
		RestAssured.given().given().auth().digest("your username", "your password").get("your end point URL");
		RestAssured.given() .auth().form("your username", "your password").post("your end point URL");
		//OAUTH 1.0
		RestAssured.given().auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret").get("your end point URL");
		//OAUTH 2.0
		RestAssured.given().auth().oauth2("Access token").get("your end point URL");
     */

}
