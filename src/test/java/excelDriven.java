import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;


public class excelDriven {
@Test
public void addPlace() throws IOException
	{
		// TODO Auto-generated method stub
	dataDriven d = new dataDriven();
	ArrayList data=d.getData("RestAddBook","RestAssured");
	
	HashMap<String, Object> jsonAsMap = new HashMap<>();
	jsonAsMap.put("name",data.get(1));
	jsonAsMap.put("isbn",data.get(2));
	jsonAsMap.put("aisle",data.get(3));
	jsonAsMap.put("author",data.get(4));
	
//  For nested json	
//	HashMap<String, Object> jsonAsMap2 = new HashMap<>();
//	jsonAsMap2.put("lat","12");
//	jsonAsMap2.put("lng","34");
//	jsonAsMap.put("location",jsonAsMap2);
	
	 RestAssured.baseURI = "http://216.10.245.166";
	 Response resp=given().header("Content-Type","application/json")
			 .body(jsonAsMap)
			 .when().post("/Library/Addbook.php")
			 .then().assertThat().statusCode(200).extract().response();
	 
	 JsonPath js=ReusableMethods.rawToJson(resp);
	 String id=js.getString("ID");
	 System.out.println(id);
	 
			 
     

	}

}
