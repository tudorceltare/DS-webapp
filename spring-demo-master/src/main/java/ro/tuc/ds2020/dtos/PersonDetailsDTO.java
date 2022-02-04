package ro.tuc.ds2020.dtos;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.dtos.validators.annotation.AgeLimit;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
// this DTO is used for the individual PERSON page and to display DEVICES
public class PersonDetailsDTO extends RepresentationModel<PersonDetailsDTO> {
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String username;
    @NotNull
    private String address;
    @NotNull
    private String password;
    @NotNull
    @AgeLimit(limit = 18)
    private int age;
    @NotNull
    private String email;
    @NotNull
    private String role;
    private UUID[] devicesIds;
    private String[] devicesAddresses;
    private Float[] avgConsumption;

}
