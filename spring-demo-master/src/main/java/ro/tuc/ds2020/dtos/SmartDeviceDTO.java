package ro.tuc.ds2020.dtos;

import lombok.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
// This DTO is used only to view the details in the individual PERSON page
// It is also used to INSERT a new SMART DEVICE into the database
public class SmartDeviceDTO extends RepresentationModel<SmartDeviceDTO> {
    private UUID id;
    @NotNull
    private String description;
    @NotNull
    private String address;
    @NotNull
    private float maxEnergyConsumption;
    @NotNull
    private float avgConsumption;
    @NotNull
    private UUID user_id;

}