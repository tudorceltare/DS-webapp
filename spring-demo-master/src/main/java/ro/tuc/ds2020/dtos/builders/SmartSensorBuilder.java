package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.SmartSensorDTO;
import ro.tuc.ds2020.entities.SmartSensor;

import java.util.UUID;

public class SmartSensorBuilder {
    public SmartSensorBuilder() {
    }

    // used for displaying all sensors for a device on the device page
    public static SmartSensorDTO toSmartSensorDTO(SmartSensor smartSensor){
        return SmartSensorDTO.builder()
                .id(smartSensor.getId())
                .description(smartSensor.getDescription())
                .maxValue(smartSensor.getMaxValue())
                .device_id(smartSensor.getDevice().getId())
                .build();
    }

    // used for INSERT
    public static SmartSensor toEntity(SmartSensorDTO smartSensorDTO){
        return new SmartSensor(smartSensorDTO.getDescription(),
                smartSensorDTO.getMaxValue());
    }

    // used for UPDATE
    public static SmartSensor toEntityWithId(SmartSensorDTO smartSensorDTO){
        return new SmartSensor(
                smartSensorDTO.getId(),
                smartSensorDTO.getDescription(),
                smartSensorDTO.getMaxValue());
    }
}
