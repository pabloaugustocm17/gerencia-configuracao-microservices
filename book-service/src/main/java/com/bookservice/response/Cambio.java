package com.bookservice.response;

import java.io.Serial;
import java.io.Serializable;

public class Cambio implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String from;

    private String to;

    private Double conversionFactor;

    private String enviromnet;

    private Double convertedValue;

    public Cambio(){

    }

    public Cambio(Long id, String from, String to, Double conversionFactor, String enviromnet, Double convertedValue) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionFactor = conversionFactor;
        this.enviromnet = enviromnet;
        this.convertedValue = convertedValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(Double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public String getEnviromnet() {
        return enviromnet;
    }

    public void setEnviromnet(String enviromnet) {
        this.enviromnet = enviromnet;
    }

    public Double getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(Double convertedValue) {
        this.convertedValue = convertedValue;
    }
}
