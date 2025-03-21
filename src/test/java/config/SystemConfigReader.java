package config;

import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;

public class SystemConfigReader {


    @Getter
    private static final SystemConfigInterface props = ConfigFactory.newInstance().create(SystemConfigInterface.class);

}
