package ru.stqa.pft.addressbook.appmanager;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;

public class BugTrackerHelper {



    private AplicationManager app;

    public BugTrackerHelper(AplicationManager app){ this.app = app; }

    public String getIssueStatus(int issueId){
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
        String json = RestAssured.get("http://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonObject issue = issues.getAsJsonArray().get(0).getAsJsonObject();
        return issue.get("state_name").getAsString();
    }


}
