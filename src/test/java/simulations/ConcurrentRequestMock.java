package simulations;

import feeders.PlaySessionFeeder;
import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ConcurrentRequestMock extends BaseSimulation {
    ScenarioBuilder listScenario = scenario("ConcurrentRequestMock")
            .exec(
                    http("hello mock")
                            .get("/hello")
                            .check(status().is(200)) // Проверяем, что ответ 200 OK
            );

    {
        setUp(
                //listScenario.injectOpen(constantUsersPerSec(1).during(60)) // 10 пользователей в течение 30 секунд
                listScenario.injectClosed(rampConcurrentUsers(1).to(cntUsers).during(rumpTime),
                constantConcurrentUsers(cntUsers).during(stepTime))
        ).protocols(httpProtocol);
    }
}
