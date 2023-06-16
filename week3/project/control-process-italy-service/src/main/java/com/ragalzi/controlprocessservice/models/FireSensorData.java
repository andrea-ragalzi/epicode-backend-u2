package com.ragalzi.controlprocessservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FireSensorData {

    private long id;

    private double latitude;

    private double longitude;

    private int smokeLevel;

}
