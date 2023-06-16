package com.ragalzi.controlcenterservice.controllers;

import com.ragalzi.controlcenterservice.models.FireSensorData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/control-center/alarms")
public class ControlCenterController {

    private List<FireSensorData> alarms = new ArrayList<>();

    @PostMapping
    public ResponseEntity<String> handleAlarm(@RequestBody FireSensorData fireSensorData) {
        System.out.println("Nuovo allarme ricevuto: " + fireSensorData);
        alarms.add(fireSensorData);
        return ResponseEntity.status(HttpStatus.OK).body("Allarme ricevuto");
    }

    @GetMapping
    public ResponseEntity<List<FireSensorData>> getAlarms() {
        if (!alarms.isEmpty()) {
            return ResponseEntity.ok(alarms);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
