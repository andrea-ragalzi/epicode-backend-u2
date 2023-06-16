package com.ragalzi.firesensorservice.controllers;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ragalzi.firesensorservice.models.FireSensorData;
import com.ragalzi.firesensorservice.utils.EventQueue;

@RestController
@RequestMapping("/sensors")
public class FireSensorController {

    private EventQueue eventQueue; // Coda di eventi per memorizzare i dati dei sensori

    private RestTemplate restTemplate;

    public FireSensorController(EventQueue eventQueue, RestTemplate restTemplate) {
        this.eventQueue = eventQueue;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{sensorId}")
    public ResponseEntity<List<FireSensorData>> getFireReadings(
            @PathVariable String sensorId) {
        List<FireSensorData> sensorReadings = eventQueue.getAllEvents();
        if (!sensorReadings.isEmpty()) {
            return ResponseEntity.ok(sensorReadings);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{sensorId}")
    public ResponseEntity<FireSensorData> postFireReading(
            @PathVariable String sensorId,
            @RequestBody FireSensorData fireSensorData) {
        eventQueue.addEvent(fireSensorData); // Aggiungi l'evento alla coda

        // Crea l'header per la richiesta POST
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Crea l'entità HTTP con i dati da inviare
        HttpEntity<FireSensorData> requestEntity = new HttpEntity<>(fireSensorData, headers);

        // Invia la richiesta POST all'altra rotta
        String url = "http://localhost:8091/control-process/sensors";
        restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                FireSensorData.class);

        return ResponseEntity.ok(fireSensorData);
    }

}
