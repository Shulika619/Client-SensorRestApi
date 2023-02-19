package org.example.dto;

import org.example.dto.MeasurementsDto;

import java.util.List;


public class MeasurementResponse {
    private List<MeasurementsDto> measurements;

    public List<MeasurementsDto> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementsDto> measurements) {
        this.measurements = measurements;
    }
}

