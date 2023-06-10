package com.ragalzi.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ragalzi.project.models.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByStatus(String status);

    List<Device> findByUserIsNull();

    Optional<Device> findBySerialNumber(String serialNumber);
}
