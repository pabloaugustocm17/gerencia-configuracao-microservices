package com.bookservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class EnvConfiguration {

    private final Environment ENVS;

    public EnvConfiguration(Environment environment){
        this.ENVS = environment;
    }

    public String returnPort(){
        return ENVS.getProperty("local.server.port");
    }

}
