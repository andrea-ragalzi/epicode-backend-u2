package com.ragalzi.controlprocessservice.controllers;

import com.ragalzi.controlprocessservice.models.FireSensorData;
import com.ragalzi.controlprocessservice.utils.EventQueue;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/control-process")
public class ControlProcessController {

    private EventQueue eventQueue; // Coda di eventi per memorizzare i dati dei sensori

    private RestTemplate restTemplate;

    public ControlProcessController(EventQueue eventQueue, RestTemplate restTemplate) {
        this.eventQueue = eventQueue;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/sensors")
    public ResponseEntity<List<FireSensorData>> readSensorData() {
        List<FireSensorData> sensorReadings = eventQueue.getAllEvents();
        if (!sensorReadings.isEmpty()) {
            return ResponseEntity.ok(sensorReadings);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping("/sensors")
    public ResponseEntity<FireSensorData> addSensorData(@RequestBody FireSensorData fireSensorData) {
        eventQueue.addEvent(fireSensorData); // Aggiungi l'evento alla coda

        if (fireSensorData.getSmokeLevel() > 5) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<FireSensorData> requestEntity = new HttpEntity<>(fireSensorData, headers);

            String url = "http://localhost:8080/control-center/alarms";
            restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    Void.class);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(fireSensorData);
    }
}
