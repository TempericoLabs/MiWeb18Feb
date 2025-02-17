package com.tuMeteo.MiWeb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private Double temperature;
    private Integer humidity;

    // Constructor vacío
    public Weather() {}

    // Constructor con parámetros
    public Weather(String city, Double temperature, Integer humidity) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    // Añade este metodo setter para id
    public void setId(Long id) {
        this.id = id;
    }

    // Resto de getters y setters
    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }
}