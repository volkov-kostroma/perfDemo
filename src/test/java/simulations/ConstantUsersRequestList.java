package simulations;

import feeders.PlaySessionFeeder;
import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ConstantUsersRequestList extends BaseSimulation {
    ScenarioBuilder listScenario = scenario("ConstantUsersRequestList")
            // Встраиваем ChainBuilder в сценарий
            .exec(PlaySessionFeeder.createSessionFeeder()) // Подключаем фидер как часть цепочки
            .exec(
                    http("List Requests")
                            .post("/api/requests/list")
                            .header("Cookie", "PLAY_SESSION=${playSessionCookie}") // Используем данные из фидера
                            .body(StringBody("{\"sortColumn\": \"default\", \"sortDirection\": \"\", \"offset\": 0, \"pageLength\": 10, \"filters\": []}"))
                            .check(status().is(200)) // Проверяем, что ответ 200 OK
                            //.check(jsonPath("$.data").exists()) // Проверяем, что поле data существует
            );

    {
        setUp(
                listScenario.injectOpen(
                        rampUsers(cntUsers).during(rumpTime), // Исправлено на rampUsers
                        constantUsersPerSec(cntUsers).during(stepTime)
                )
        ).protocols(httpProtocol);
    }
}
