import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import java.io.File;
import java.io.FileReader;
import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Try {
    private static final String authorization = "Basic bWFpbC5mb3IudGVzdGJhc2VAeWFuZGV4LnJ1OkR0T2ljclJZNXU0MkdBQnFhZ0ZCMUJDQg==";

    public static void main(String[] args) throws Exception {

        Response response = apiHelper.getIssue("TEST-2549");
//        //Assert.assertEquals("Тип соответствует", );
//        JSONParser parser = new JSONParser() ;
//
//        Object obj = new JSONParser().parse(new FileReader("create_issue.json"));
//        JSONObject jsonObject = (JSONObject) obj;
//        System.out.println(jsonObject.get("fields:summary"));
//       // JsonPath jsonPath = jsonObject;
        //Gson gson = new Gson();
//        JsonReader reader = new JsonReader(new FileReader("create_issue.json"));
//        Gson gson = new Gson().toJson(new File("create_issue.json"));

        //   for (String s : list) System.out.println(s);

        JsonPath requestJSON = JsonPath.with(new File("create_issue.json"));
        JsonPath responseJSON = response.jsonPath();



        //.get("fields.project.id");

        System.out.println("В файле project.id = " + requestJSON.get("fields.project.id") + "\nНа сервере project.id = " + responseJSON.get("fields.project.id"));
    }
}
