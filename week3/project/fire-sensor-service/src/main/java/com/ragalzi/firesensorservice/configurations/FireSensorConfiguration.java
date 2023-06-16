package com.ragalzi.firesensorservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.ragalzi.firesensorservice.models.FireSensorData;

@Configuration
public class FireSensorConfiguration {

    @Bean("FireSensorParamsBean")
    @Scope("prototype")
    FireSensorData fireSensorData(int smokeLevel) {
        return new FireSensorData(smokeLevel);
    }

    @Bean("FireSensorRandomBean")
    @Scope("prototype")
    FireSensorData fireSensorDataRandom() {
        int smokeLevel = (int) (Math.random() * 10);
        return new FireSensorData(smokeLevel);
    }

}
