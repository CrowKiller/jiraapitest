import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MyStepdefs {

    private static Response response;
    private static String key;
    private static HashMap<String, String> map;

    @Given("Выполнить запрос post с телом json файл {string}")
    public void выполнитьЗапросPostСТеломJsonФайл(String arg0) {
        response = apiHelper.createIssue(arg0);
    }

    @Then("Ответ имеет статус {int}")
    public void ответИмеетСтатус(int arg0) {
        response.then().statusCode(arg0);
    }

    @And("Ответ возвращает uri созданной задачи")
    public void ответВозвращаетUriСозданнойЗадачи() {
                key = response.body().jsonPath().get("key");
       // Assert.assertNotNull(key);
        System.out.println("Created issue " + key);
    }

    @And("Параметры созданной задачи соответствуют телу post запроса")
    public void параметрыСозданнойЗадачиСоответствуютТелуPostЗапроса() {
        //apiHelper.getIssueTypes();
      //  response = apiHelper.getIssue(key);

        JsonPath requestJSON = JsonPath.with(new File("create_issue.json"));
        JsonPath responseJSON = apiHelper.getIssue(key).jsonPath();


        Assert.assertEquals("project.id", (String) requestJSON.get("fields.project.id"), (String) responseJSON.get("fields.project.id"));
        Assert.assertEquals("issuetype.id", (String) requestJSON.get("fields.issuetype.id"), (String) responseJSON.get("fields.issuetype.id"));
        Assert.assertEquals("priority.id", (String) requestJSON.get("fields.priority.id"), (String) responseJSON.get("fields.priority.id"));
        Assert.assertEquals("summary", (String) requestJSON.get("fields.summary"), (String) responseJSON.get("fields.summary"));
        ArrayList<String> labels1 = responseJSON.get("fields.labels");
        ArrayList<String> labels2 = requestJSON.get("fields.labels");
        Assert.assertTrue("Labels", labels1.containsAll(labels2));

   //     System.out.println((ArrayList<String>)responseJSON.get("fields.labels"));
    //    Assert.assertEquals("error",0, 1);
      //  Assert.assertEquals("labels", (String) requestJSON.get("fields.project.id"), (String) responseJSON.get("fields.project.id"));






    }
}
