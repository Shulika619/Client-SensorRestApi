package org.example.dto;


import lombok.Getter;
import lombok.Setter;


public class MeasurementsDto {

    private Double value;

    private Boolean raining;

    private SensorDto sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDto getSensor() {
        return sensor;
    }

    public void setSensor(SensorDto sensor) {
        this.sensor = sensor;
    }
}
