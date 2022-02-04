package ro.tuc.ds2020.dtos;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
// this DTO is used for the individual SMART DEVICE page and to display SENSORS
public class SmartDeviceDetailsDTO extends RepresentationModel<PersonDTO> {
    private UUID id;
    private String description;
    @NotNull
    private String address;
    @NotNull
    private float maxEnergyConsumption;
    @NotNull
    private float avgConsumption;
    private UUID[] sensorIds; 
    private Float[] maxValues;
    private String[] descriptions;

}
