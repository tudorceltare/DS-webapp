package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.SmartDevice;

import java.util.UUID;

@Repository
public interface SmartDeviceRepository extends JpaRepository<SmartDevice, UUID> {
}
