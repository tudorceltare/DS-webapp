package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.SmartDeviceDTO;
import ro.tuc.ds2020.dtos.SmartDeviceDetailsDTO;
import ro.tuc.ds2020.services.SmartDeviceService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value="/smart-device")
public class SmartDeviceController {
    private final SmartDeviceService smartDeviceService;

    @Autowired
    public SmartDeviceController(SmartDeviceService smartDeviceService){
        this.smartDeviceService = smartDeviceService;
    }

    @GetMapping()
    public ResponseEntity<List<SmartDeviceDTO>> getSmartDevices(){
        List<SmartDeviceDTO> dtos = smartDeviceService.findSmartDevices();
//        for (SmartDeviceDetailsDTO dto : dtos){
//            Link smartDeviceLink = linkTo(methodOn(SmartDeviceController.class)
//                    .getSmartDevice(dto.getId())).withRel("smartDeviceDetails");
//            dto.add(smartDeviceLink);
//        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<SmartDeviceDetailsDTO> getSmartDevice(@PathVariable("id") UUID smartDeviceId){
        SmartDeviceDetailsDTO dto = smartDeviceService.findSmartDeviceById(smartDeviceId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody SmartDeviceDTO smartDeviceDTO) {
        UUID smartDeviceId = smartDeviceService.insert(smartDeviceDTO);
        return new ResponseEntity<>(smartDeviceId, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteDevice(@PathVariable("id") UUID deviceId){
        boolean response = smartDeviceService.deleteSmartDeviceById(deviceId);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<UUID> updateDevice(@Valid @RequestBody SmartDeviceDTO deviceDTO){
        UUID id = smartDeviceService.updateSmartDevice(deviceDTO);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
