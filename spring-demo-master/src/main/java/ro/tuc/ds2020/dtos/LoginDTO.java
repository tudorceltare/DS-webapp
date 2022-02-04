package ro.tuc.ds2020.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
