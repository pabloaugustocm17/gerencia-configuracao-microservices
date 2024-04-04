package com.bookservice.service;

import com.bookservice.proxy.CambioProxy;
import com.bookservice.response.Cambio;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class ResponseService {

    private final CambioProxy CAMBIO_PROXY;

    public ResponseService(CambioProxy cambioProxy){
        this.CAMBIO_PROXY = cambioProxy;
    }

    public Cambio retornaCambio(String currency, Double price){

        return CAMBIO_PROXY.retornaCambio(price, "USD", currency);

    }

    @Deprecated
    public static Cambio getCambio(String currency, Double price){

        HashMap<String, String> params = new HashMap<>();

        params.put("to", currency);
        params.put("amount", price.toString());
        params.put("from", "USD");

        return new RestTemplate()
                .getForEntity("http://localhost:8001/cambio-service/" +
                                "{amount}/{from}/{to}",
                                Cambio.class,
                                params)
                .getBody();

    }

}
