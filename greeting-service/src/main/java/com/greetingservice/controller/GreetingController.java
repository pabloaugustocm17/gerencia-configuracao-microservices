package com.greetingservice.controller;

import com.greetingservice.configuration.GreetingConfiguration;
import com.greetingservice.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    /* VARS */

    private static final String TEMPLATE = "%s, %S";
    private final AtomicLong CONTADOR = new AtomicLong();

    /* CONFIGURATION */

    private final GreetingConfiguration GREETING_CONFIGURATION;

    public GreetingController(GreetingConfiguration greetingConfiguration) {
        this.GREETING_CONFIGURATION = greetingConfiguration;
    }

    @RequestMapping("/greeting")
    public Greeting greeting(
            @RequestParam(value = "name", defaultValue = "") String name
    ){

        if(name.isEmpty()){
            name = GREETING_CONFIGURATION.getDefault_value();
        }


        return new Greeting(
                CONTADOR.getAndIncrement(),
                String.format(TEMPLATE, GREETING_CONFIGURATION.getGreeting() ,name)
        );

    }


}
