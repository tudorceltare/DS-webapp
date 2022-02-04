package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.SmartSensorDTO;
import ro.tuc.ds2020.services.SmartSensorService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/smart-sensor")
public class SmartSensorController {
    private final SmartSensorService smartSensorService;

    @Autowired
    public SmartSensorController(SmartSensorService smartSensorService){
        this.smartSensorService = smartSensorService;
    }

    @GetMapping()
    public ResponseEntity<List<SmartSensorDTO>> getSmartSensors() {
        List<SmartSensorDTO> dtos = smartSensorService.findSmartSensors();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SmartSensorDTO> getSensor(@PathVariable("id")UUID sensorId){
        SmartSensorDTO dto = smartSensorService.findSmartSensorById(sensorId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody SmartSensorDTO sensorDTO){
        UUID sensorId = smartSensorService.insert(sensorDTO);
        return new ResponseEntity<>(sensorId, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSensor(@PathVariable("id") UUID sensorId){
        boolean response = smartSensorService.deleteSmartSensorById(sensorId);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<UUID> updateSensor(@Valid @RequestBody SmartSensorDTO sensorDTO) {
        UUID id = smartSensorService.updateSmartSensor(sensorDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
