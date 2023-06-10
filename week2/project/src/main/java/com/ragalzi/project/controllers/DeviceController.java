package com.ragalzi.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ragalzi.project.models.Device;
import com.ragalzi.project.repositories.DeviceRepository;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping
    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

    @PostMapping
    public Device createDevice(@RequestBody Device device) {
        return deviceRepository.save(device);
    }

    @GetMapping("/{id}")
    public Device getDevice(@PathVariable Long id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Device updateDevice(@PathVariable Long id, @RequestBody Device device) {
        if (!deviceRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        device.setId(id);
        return deviceRepository.save(device);
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable Long id) {
        if (!deviceRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        deviceRepository.deleteById(id);
    }

    @GetMapping("/available")
    public List<Device> getAvailableDevices() {
        return deviceRepository.findByStatus("available");
    }

    @GetMapping("/maintenance")
    public List<Device> getDevicesInMaintenance() {
        return deviceRepository.findByStatus("maintenance");
    }

    @GetMapping("/unassigned")
    public List<Device> getUnassignedDevices() {
        return deviceRepository.findByUserIsNull();
    }
}
