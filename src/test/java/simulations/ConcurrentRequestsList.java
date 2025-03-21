package simulations;

import feeders.PlaySessionFeeder;
import io.gatling.javaapi.core.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.recorder.internal.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration.seconds;

public class ConcurrentRequestsList extends BaseSimulation {
    ScenarioBuilder listScenario = scenario("ConcurrentRequestsList")
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
                //listScenario.injectOpen(constantUsersPerSec(1).during(60)) // 10 пользователей в течение 30 секунд
                listScenario.injectClosed(rampConcurrentUsers(1).to(cntUsers).during(rumpTime),
                constantConcurrentUsers(cntUsers).during(stepTime))
        ).protocols(httpProtocol);
    }
}
