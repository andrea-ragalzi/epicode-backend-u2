package com.ragalzi.firesensorservice.models;

import org.springframework.beans.factory.annotation.Value;
import lombok.Data;

@Data
public class FireSensorData {

    @Value("${sensor.id}")
    private long id;

    @Value("${sensor.latitude}")
    private double latitude;

    @Value("${sensor.longitudine}")
    private double longitude;

    private int smokeLevel;

    public FireSensorData(int smokeLevel) {
        this.smokeLevel = smokeLevel;
    }
}
