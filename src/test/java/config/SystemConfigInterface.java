package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:./src/test/resources/${environment}.properties"})
public interface SystemConfigInterface extends Config {
    @Key("rest.baseUrl")
    String restUrl();
}
