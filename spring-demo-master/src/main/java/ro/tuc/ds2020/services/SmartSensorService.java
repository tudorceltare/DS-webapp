package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.SmartSensorDTO;
import ro.tuc.ds2020.dtos.builders.SmartSensorBuilder;
import ro.tuc.ds2020.entities.SmartDevice;
import ro.tuc.ds2020.entities.SmartSensor;
import ro.tuc.ds2020.repositories.SmartDeviceRepository;
import ro.tuc.ds2020.repositories.SmartSensorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SmartSensorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmartSensorService.class);
    private final SmartSensorRepository smartSensorRepository;
    private final SmartDeviceRepository smartDeviceRepository;

    @Autowired
    public SmartSensorService(SmartSensorRepository smartSensorRepository, SmartDeviceRepository smartDeviceRepository){
        this.smartSensorRepository = smartSensorRepository;
        this.smartDeviceRepository = smartDeviceRepository;
    }

    // get ALL SMART SENSORS
    public List<SmartSensorDTO>findSmartSensors(){
        List<SmartSensor> smartSensors = smartSensorRepository.findAll();
        return smartSensors.stream()
                .map(SmartSensorBuilder::toSmartSensorDTO)
                .collect(Collectors.toList());
    }

    // get SMART SENSOR by Id
    public SmartSensorDTO findSmartSensorById(UUID id){
        Optional<SmartSensor> smartSensorOptional = smartSensorRepository.findById(id);
        if(!smartSensorOptional.isPresent()){
            LOGGER.error("SmartSensor with id {} was not found in db", id);
            throw new ResourceNotFoundException(SmartSensor.class.getSimpleName() + "with id: "+ id);
        }
        return SmartSensorBuilder.toSmartSensorDTO(smartSensorOptional.get());
    }

    // insert a SMART SENSOR using SmartDeviceDetailsDTO
    public UUID insert(SmartSensorDTO smartSensorDTO){
        SmartSensor smartSensor = SmartSensorBuilder.toEntity(smartSensorDTO);

        // find owner
        Optional<SmartDevice> owner = smartDeviceRepository.findById(smartSensorDTO.getDevice_id());

        if (owner.isPresent()){
            smartSensor.setDevice(owner.get());
            owner.get().getSmartSensors().add(smartSensor);
        }

        smartSensor = smartSensorRepository.save(smartSensor);
        LOGGER.debug("SmartSensor with id {} was inserted in db", smartSensor.getId());
        return smartSensor.getId();
    }

    // delete a SMART SENSOR if found by Id
    public boolean deleteSmartSensorById(UUID id){
        Optional<SmartSensor> smartSensorOptional = smartSensorRepository.findById(id);
        if(!smartSensorOptional.isPresent()) {
            LOGGER.error("SmartService with id {} was not found in db", id);
            throw new ResourceNotFoundException(SmartSensor.class.getSimpleName() + "with id:" + id);
        }

        smartSensorRepository.deleteById(smartSensorOptional.get().getId());
        return true;
    }

    // update a SMART DEVICE if found by Id
    public UUID updateSmartSensor(SmartSensorDTO smartSensorDTO){
        Optional<SmartSensor> smartSensorOptional = smartSensorRepository.findById(smartSensorDTO.getId());
        if(!smartSensorOptional.isPresent()) {
            LOGGER.error("SmartSensor with id {} was not found in db", smartSensorDTO.getId());
            throw new ResourceNotFoundException(SmartDevice.class.getSimpleName() + "with id:" + smartSensorDTO.getId());
        }
        SmartSensor smartSensor = SmartSensorBuilder.toEntityWithId(smartSensorDTO);
        smartSensor = smartSensorRepository.save(smartSensor);
        LOGGER.debug("SmartSensor with id {} was updated in db", smartSensor.getId());
        return smartSensor.getId();
    }
}
