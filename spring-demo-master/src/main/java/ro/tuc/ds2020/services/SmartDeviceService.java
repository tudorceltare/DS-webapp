package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.SmartDeviceDTO;
import ro.tuc.ds2020.dtos.SmartDeviceDetailsDTO;
import ro.tuc.ds2020.dtos.builders.SmartDeviceBuilder;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.entities.SmartDevice;
import ro.tuc.ds2020.entities.SmartSensor;
import ro.tuc.ds2020.repositories.PersonRepository;
import ro.tuc.ds2020.repositories.SmartDeviceRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SmartDeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmartDeviceService.class);
    private final SmartDeviceRepository smartDeviceRepository;
    private final PersonRepository personRepository;
    private final SmartSensorService smartSensorService;

    @Autowired
    public SmartDeviceService(SmartDeviceRepository smartDeviceRepository, PersonRepository personRepository, SmartSensorService smartSensorService) {
        this.smartDeviceRepository = smartDeviceRepository;
        this.personRepository = personRepository;
        this.smartSensorService = smartSensorService;
    }

    // get ALL SMART DEVICES
    public List<SmartDeviceDTO> findSmartDevices(){
        List<SmartDevice> smartDevices = smartDeviceRepository.findAll();
        return smartDevices.stream()
                .map(SmartDeviceBuilder::toSmartDeviceDTO)
                .collect(Collectors.toList());
    }

    // get SMART DEVICE by Id
    public SmartDeviceDetailsDTO findSmartDeviceById(UUID id){
        Optional<SmartDevice> smartDeviceOptional = smartDeviceRepository.findById(id);
        if(!smartDeviceOptional.isPresent()) {
            LOGGER.error("SmartDevice with id {} was not found in db", id);
            throw new ResourceNotFoundException(SmartDevice.class.getSimpleName() + " with id: " + id);
        }
        return SmartDeviceBuilder.toSmartDeviceDetailsDTO(smartDeviceOptional.get());
    }

    // insert a SMART DEVICE using SmartDeviceDetailsDTO
    public UUID insert(SmartDeviceDTO smartDeviceDTO){
        SmartDevice smartDevice = SmartDeviceBuilder.toEntity(smartDeviceDTO);
        // find owner
        Optional<Person> owner = personRepository.findById(smartDeviceDTO.getUser_id());

        if(owner.isPresent()){
            // TODO: link to owner
            smartDevice.setPerson(owner.get());

            // TODO: add the device to owner's set
            owner.get().getSmartDevices().add(smartDevice);
        }

        smartDevice = smartDeviceRepository.save(smartDevice);
        LOGGER.debug("SmartDevice with id {} was inserted in db", smartDevice.getId());
        return smartDevice.getId();
    }

    // delete a SMART DEVICE if found by Id
    public boolean deleteSmartDeviceById(UUID id){
        Optional<SmartDevice> smartDeviceOptional = smartDeviceRepository.findById(id);
        if(!smartDeviceOptional.isPresent()) {
            LOGGER.error("SmartDevice with id {} was not found in db", id);
            throw new ResourceNotFoundException(SmartDevice.class.getSimpleName() + "with id:" + id);
        }

        // TODO: delete all sensors from device
        for (SmartSensor sensor : smartDeviceOptional.get().getSmartSensors()) {
            smartSensorService.deleteSmartSensorById(sensor.getId());
        }

        smartDeviceRepository.deleteById(smartDeviceOptional.get().getId());
        return true;
    }

    // update a SMART DEVICE if found by Id
    public UUID updateSmartDevice(SmartDeviceDTO smartDeviceDTO){
        Optional<SmartDevice> smartDeviceOptional = smartDeviceRepository.findById(smartDeviceDTO.getId());
        if(!smartDeviceOptional.isPresent()) {
            LOGGER.error("SmartDevice with id {} was not found in db", smartDeviceDTO.getId());
            throw new ResourceNotFoundException(SmartDevice.class.getSimpleName() + "with id:" + smartDeviceDTO.getId());
        }
        SmartDevice smartDevice = SmartDeviceBuilder.toEntityWithId(smartDeviceDTO);
        smartDevice = smartDeviceRepository.save(smartDevice);
        LOGGER.debug("SmartDevice with id {} was updated in db", smartDevice.getId());
        return smartDevice.getId();
    }
}
