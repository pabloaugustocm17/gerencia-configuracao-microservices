package com.cambioservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ConfigurationEnv {

    private final Environment ENVS;

    public ConfigurationEnv(Environment environment){
        this.ENVS = environment;
    }

    public String returnPort(){
        return ENVS.getProperty("local.server.port");
    }

}
