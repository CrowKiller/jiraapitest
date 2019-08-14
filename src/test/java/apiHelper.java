
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class apiHelper {

    private static final String uri="https://testbase.atlassian.net/rest/api/3";
    private static final String authorization = "Basic bWFpbC5mb3IudGVzdGJhc2VAeWFuZGV4LnJ1OkR0T2ljclJZNXU0MkdBQnFhZ0ZCMUJDQg==";


    public static Response createIssue(String fileName){
        return given()
                    .log().all()
                    .request()
                    .header("Authorization", authorization)
                    .contentType(ContentType.JSON)
                    .body(new File(fileName))
                .when()
                  .post("https://testbase.atlassian.net/rest/api/3/issue/")
                .then()
                    .log().all()
                 //   .statusCode(201)
                    .extract().response();
    }

    public static Response getIssue(String key){
       return given()
                    .header("Authorization", authorization)
                    .contentType(ContentType.JSON)
                .when()
                    .get("https://testbase.atlassian.net/rest/api/3/issue/"+ key)
        .then().extract().response();

    }

    public static void getIssueTypes() {
        JsonPath errors =  given()
                .header("Authorization", authorization)
                .contentType(ContentType.JSON)
                .when()
                .get("https://testbase.atlassian.net/rest/api/3/issuetype")
                .then()
                .log().all()
                .extract()
                .body()
                .jsonPath();
        //.getMap(".");

        //List<String> list = errors.getList("[id,name]");
//        for (String s1 : ) {
//
//        }

        // System.out.println(errors.getMap("id").toString());
        //Map<Object, Object> list = errors.getList().getMap("id");

        List<String> idList = errors.getList("id");
        List<String> nameList = errors.getList("name");
        Iterator<String> idIteratro = idList.iterator();
        Iterator<String> nameIterator = nameList.iterator();
        HashMap<String, String> map = new HashMap<String, String>();

        while (idIteratro.hasNext() && nameIterator.hasNext()){
            map.put(idIteratro.next(),nameIterator.next());
        }

        for (Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey().toString() + " " + entry.getValue().toString());
        }
    }

    public static void verifyIssue(Response response, String json){
        
    }




}
