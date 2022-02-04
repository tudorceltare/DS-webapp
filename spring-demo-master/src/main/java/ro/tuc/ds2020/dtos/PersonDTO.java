package ro.tuc.ds2020.dtos;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.dtos.validators.annotation.AgeLimit;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonDTO extends RepresentationModel<PersonDTO> {
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

    public PersonDTO( String name,
                      String username,
                      String address,
                      String password,
                      int age,
                      String email,
                      String role) {
        this.name = name;
        this.username = username;
        this.address = address;
        this.password = password;
        this.age = age;
        this.email = email;
        this.role = role;
    }

}
