package simulations;

import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class ConstantRequestMock extends BaseSimulation {
    ScenarioBuilder listScenario = scenario("ConcurrentRequestMock")
            .exec(
                    http("hello mock")
                            .get("/hello")
                            .check(status().is(200)) // Проверяем, что ответ 200 OK
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
