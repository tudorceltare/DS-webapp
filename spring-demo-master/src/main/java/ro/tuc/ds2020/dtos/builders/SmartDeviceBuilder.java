package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.SmartDeviceDTO;
import ro.tuc.ds2020.dtos.SmartDeviceDetailsDTO;
import ro.tuc.ds2020.dtos.SmartSensorDTO;
import ro.tuc.ds2020.entities.SmartDevice;
import ro.tuc.ds2020.entities.SmartSensor;

import java.util.*;

public class SmartDeviceBuilder {
    public SmartDeviceBuilder() {
    }
    // used to GET DEVICES for display on the individual PERSON page
    public static SmartDeviceDTO toSmartDeviceDTO(SmartDevice smartDevice){
        return SmartDeviceDTO.builder()
                .id(smartDevice.getId())
                .description(smartDevice.getDescription())
                .address(smartDevice.getAddress())
                .maxEnergyConsumption(smartDevice.getMaxEnergyConsumption())
                .avgConsumption(smartDevice.getAvgConsumption())
                .user_id(smartDevice.getPerson().getId())
                .build();
    }

    // used to GET a DEVICE and it's details to display ont the individual DEVICE page
    public static SmartDeviceDetailsDTO toSmartDeviceDetailsDTO(SmartDevice smartDevice){
        List<UUID> sensorIds = new ArrayList<UUID>();
        List<String> sensorDescriptions = new ArrayList<String>();
        List<Float> maxValues = new ArrayList<Float>();
        for (SmartSensor sensor : smartDevice.getSmartSensors()){
            sensorIds.add(sensor.getId());
            sensorDescriptions.add(sensor.getDescription());
            maxValues.add(sensor.getMaxValue());
        }
        UUID[] sensorsIdsArray = new UUID[sensorIds.size()];
        String[] sensorDescriptionsArray = new String[sensorDescriptions.size()];
        Float[] maxValuesArray = new Float[maxValues.size()];
        sensorIds.toArray(sensorsIdsArray);
        sensorDescriptions.toArray(sensorDescriptionsArray);
        maxValues.toArray(maxValuesArray);
        return SmartDeviceDetailsDTO.builder()
                .id(smartDevice.getId())
                .description(smartDevice.getDescription())
                .address(smartDevice.getAddress())
                .maxEnergyConsumption(smartDevice.getMaxEnergyConsumption())
                .avgConsumption(smartDevice.getAvgConsumption())
                .sensorIds(sensorsIdsArray)
                .maxValues(maxValuesArray)
                .descriptions(sensorDescriptionsArray)
                .build();
    }

    // used for INSERT
    public static SmartDevice toEntity(SmartDeviceDTO smartDeviceDTO){
        return new SmartDevice(
                smartDeviceDTO.getDescription(),
                smartDeviceDTO.getAddress(),
                smartDeviceDTO.getMaxEnergyConsumption(),
                smartDeviceDTO.getAvgConsumption(),
                new HashSet<>()
        );
    }

    // used for UPDATE
    public static SmartDevice toEntityWithId(SmartDeviceDTO sddDTO){
        return new SmartDevice(
                sddDTO.getId(),
                sddDTO.getDescription(),
                sddDTO.getAddress(),
                sddDTO.getMaxEnergyConsumption(),
                sddDTO.getAvgConsumption()
        );
    }
}
