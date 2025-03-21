package simulations;

import config.SystemConfigReader;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.http.HttpDsl.http;

public class BaseSimulation extends Simulation {
      Integer rumpTime = Integer.valueOf(System.getProperty("rumpTime","1"));
      Integer stepTime = Integer.valueOf(System.getProperty("stepTime","1"));
      Integer cntUsers = Integer.valueOf(System.getProperty("cntUsers","1"));
      String environment = System.getProperty("environment","test");

    public BaseSimulation() {
        // Логируем информацию
        System.out.println("TEST PARAMETERS:\n" +
                "    - Среда " + environment + "\n" +
                "    - Тест запускается по адресу " + SystemConfigReader.getProps().restUrl() + "\n" +
                "    - Разогрев, плавное увеличение нагрузки с 1 до " + cntUsers + " виртуальных пользователей\n" +
                "    - Разогрев идёт в течение " + rumpTime + " секунд\n" +
                "    - Стабильный этап будет длиться " + stepTime + " секунд\n");
    }

      HttpProtocolBuilder httpProtocol = http
            .baseUrl(SystemConfigReader.getProps().restUrl())
            .header("Content-Type", "application/json");
}
