

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {
	public static JsonPath rawToJson(Response r)
	{ 
		String respone=r.asString();
		JsonPath x=new JsonPath(respone);
		System.out.println(respone);
		return x;
	}
}
