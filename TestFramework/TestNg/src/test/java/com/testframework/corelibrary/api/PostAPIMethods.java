package com.testframework.corelibrary.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.HashMap;

public class PostAPIMethods {
    //Retrieve response for Post API request with input request body
    public static Response responseForPostMethodWithRequestBody(String endpoint, String requestParams)
    {
        //creating request with endpoint
        RestAssured.baseURI = endpoint;
        //convert params in hashmap
        HashMap<String,String> requestBodyParams= PostAPIMethods.getParamsInHashMap(requestParams);
        // Create a JSON object with the request body
        JSONObject requestBody = new JSONObject(requestBodyParams);
        // Send the POST request
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/posts");
        return response;
    }

    //Retrieve response for Post API request with input JSON data in the request
    public static Response responseForPostMethodWithJsonRequest(String endpoint, String jsonRequest)
    {
        //creating request with endpoint
        RestAssured.baseURI = endpoint;
        // Send the POST request
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonRequest)
                .post("/posts");
        return response;
    }

    //Convert Params in key-value pair from properties file to HashMap
    public static HashMap<String,String> getParamsInHashMap(String params)
    {
        String[] paramsData=params.split(";");
        HashMap<String,String> paramsInHashmap=new HashMap<String, String>();
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
