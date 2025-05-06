package com.greenhouse.greenhouseapp.models;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Plant {
    private String name;
    private LocalDateTime birthdate;
    private float humidity;
    private float temperature;
    private boolean light;
    private boolean irigation;

    public Plant(String name, LocalDateTime birthdate, float humidity, float temperature, boolean light, boolean irigation) {
        this.name = name;
        this.birthdate = birthdate;
        this.humidity = humidity;
        this.temperature = temperature;
        this.light = light;
        this.irigation = irigation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public boolean isLight() {
        return light;
    }

    public void setLight(boolean light) {
        this.light = light;
    }

    public boolean isIrigation() {
        return irigation;
    }

    public void setIrigation(boolean irigation) {
        this.irigation = irigation;
    }

    @NonNull
    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                ", light=" + light +
                ", irigation=" + irigation +
                '}' + "\n";
    }
}
