package com.cambioservice.service;

import com.cambioservice.model.Cambio;
import com.cambioservice.repository.CambioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CambioService {

    private final CambioRepository CAMBIO_REPOSITORY;

    public CambioService(CambioRepository cambioRepository){
        this.CAMBIO_REPOSITORY = cambioRepository;
    }

    /* Comunicação JPA */

    public Cambio findByFromAndTo(String from, String to){

        Cambio cambio = CAMBIO_REPOSITORY.findByFromAndTo(from, to);

        if(cambio == null){
            throw new RuntimeException("Currency Unsupported");
        }

        return cambio;

    }

    /* Utils */

    public Cambio factoryCambio(Cambio cambio, String port, BigDecimal amount){

        cambio.setEnviromnet(port);

        BigDecimal conversionFactor = cambio.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(amount);

        cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));

        return cambio;

    }


}
