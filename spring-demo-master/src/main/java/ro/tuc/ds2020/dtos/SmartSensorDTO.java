package ro.tuc.ds2020.dtos;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmartSensorDTO extends RepresentationModel<SmartSensorDTO> {
    private UUID id;
    private String description;
    private float maxValue;
    private UUID device_id;

    public SmartSensorDTO(UUID id, String description, float maxValue) {
        this.id = id;
        this.description = description;
        this.maxValue = maxValue;
    }

}
