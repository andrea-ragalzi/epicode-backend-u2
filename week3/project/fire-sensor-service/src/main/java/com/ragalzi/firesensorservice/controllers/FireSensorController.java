package com.ragalzi.firesensorservice.controllers;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ragalzi.firesensorservice.models.FireSensorData;

@RestController
@RequestMapping("/sensors")
public class FireSensorController {

    @Autowired
    @Qualifier("FireSensorRandomBean")
    private ObjectProvider<FireSensorData> fireSensorRandomProvider;

    @Autowired
    private Jackson2ObjectMapperBuilder objectMapperBuilder;

    @PostMapping("/{sensorId}/readings")
    public @ResponseBody ResponseEntity<String> postFireReading(
            @PathVariable String sensorId) throws JsonProcessingException {
        FireSensorData fireSensorData = fireSensorRandomProvider.getObject();
        System.out.println("FireSensorData: " + fireSensorData);

        // Converti l'oggetto FireSensorData in formato JSON
        String json = objectMapperBuilder.build().writeValueAsString(
                fireSensorData);

        // Crea una ResponseEntity contenente il JSON come corpo della risposta
        return ResponseEntity.ok(json);
    }

}
