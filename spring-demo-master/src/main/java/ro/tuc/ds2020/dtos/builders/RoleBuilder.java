package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.RoleDTO;
import ro.tuc.ds2020.entities.Role;

public class RoleBuilder {

    public RoleBuilder() {
    }

    public static RoleDTO toRoleDTO(Role role){
        return new RoleDTO(
                role.getId(),
                role.getName(),
                role.getUserId()
        );
    }

    public static Role toEntity(RoleDTO roleDTO){
        return new Role(
                roleDTO.getName(),
                roleDTO.getUserId()
        );
    }

    public static Role toEntityWithId(RoleDTO roleDTO){
        return new Role(
                roleDTO.getId(),
                roleDTO.getName(),
                roleDTO.getUserId()
        );
    }
}
