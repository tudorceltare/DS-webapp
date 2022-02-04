package ro.tuc.ds2020.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Role;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class RoleDTO extends RepresentationModel<RoleDTO> {
    private UUID id;
    private String name;
    private UUID userId;

    RoleDTO(){
    }

    public RoleDTO(UUID id, String name, UUID userId){
        this.id = id;
        this.name = name;
        this.userId = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return userId == roleDTO.userId &&
                Objects.equals(name, roleDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, userId);
    }
}
