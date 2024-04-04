package com.cambioservice.controller;

import com.cambioservice.config.ConfigurationEnv;
import com.cambioservice.model.Cambio;
import com.cambioservice.service.CambioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "cambio-service/")
@Tag(name = "Cambio endpoint")
public class CambioController {

    private final ConfigurationEnv ENVS;

    private final CambioService CAMBIO_SERVICE;

    public CambioController(ConfigurationEnv configurationEnv, CambioService cambioService){
        this.ENVS = configurationEnv;
        this.CAMBIO_SERVICE = cambioService;
    }

    /* Requisições */

    @GetMapping(value = "{amount}/{from}/{to}")
    @Operation(description = "Get cambio from currency")
    public Cambio retornaCambio(

            @PathVariable("amount") BigDecimal amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to

            ){

        Cambio cambio = CAMBIO_SERVICE.findByFromAndTo(from, to);

        String port = ENVS.returnPort();

        cambio = CAMBIO_SERVICE.factoryCambio(cambio, port, amount);

        return cambio;
    }

}
