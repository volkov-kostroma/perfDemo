package providers;

import config.SystemConfigReader;
import helpers.Csv;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListMapFromAPI {
    public static List<Map<String, Object>> getPlaySessionList() {
        List<Map<String, Object>> playSessionList = new ArrayList<>();
        Csv.ReadUsers("src/test/resources/users.csv").forEach(user -> {
            // Отправляем POST-запрос с телом авторизации
            Response response = RestAssured
                    .given()
                    .contentType("application/json")
                    .accept("application/json")
                    .body(user.toString())
                    .post(SystemConfigReader.getProps().restUrl() + "/api/login");
            if (response.statusCode() == 200) {
                // Возвращаем cookie с именем PLAY_SESSION
                Map<String, Object> sessionMap = new HashMap<>();
                sessionMap.put("playSessionCookie", response.getCookie("PLAY_SESSION"));
                playSessionList.add(sessionMap);
            } else {
                System.out.println("Failed to authenticate: " + user.getLogin()+"/"+user.getPassword()+ " mess: " + response.statusLine());
                //throw new RuntimeException("Failed to authenticate: " + response.statusLine());
            }
        });
        return playSessionList;
    }
}
