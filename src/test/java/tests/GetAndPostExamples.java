package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;


public class GetAndPostExamples {
	
  //  @Test
	public void testGet()
	{
		baseURI ="https://reqres.in/api";
		given().
		  get("/users?page=2").
		then().
		  statusCode(200).
		  body("data[3].first_name", equalTo("Byron")).
		  body("data.first_name",hasItems("George","Rachel"));
		  
	}
    
    @Test
    public void testPost()
    {
  	Map<String, Object> map = new HashMap<String, Object>();
//    	map.put("name", "Saritha");
//    	map.put("job", "Testing");
//    	System.out.println(map);
    	
    	JSONObject request = new JSONObject();
    	request.put("name", "Saritha");
    	request.put("job", "Testing");
    	System.out.println(request.toJSONString());
    	
    	baseURI ="https://reqres.in/api";
    	
    	given().
    	  header("Content-Type","application/json").
    	  contentType(ContentType.JSON).
    	  accept(ContentType.JSON).
    	  body(request.toJSONString()).
    	when().
    	  post("/users").
    	then().
    	  statusCode(201).log().all();
    	
    }
}
