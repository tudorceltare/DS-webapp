package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.RoleDTO;
import ro.tuc.ds2020.dtos.builders.RoleBuilder;
import ro.tuc.ds2020.entities.Role;
import ro.tuc.ds2020.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // get ALL ROLES
    public List<RoleDTO> findRoles(){
        List<Role> roleList = roleRepository.findAll();
        return roleList.stream()
                .map(RoleBuilder::toRoleDTO)
                .collect(Collectors.toList());
    }

    // get ROLE by Id
    public RoleDTO findRoleById(UUID id){
        Optional<Role> roleOptional = roleRepository.findById(id);
        if(!roleOptional.isPresent()) {
            LOGGER.error("Role with id {} was not found in db", id);
            throw new ResourceNotFoundException(Role.class.getSimpleName() + " with id: " + id);
        }
        return RoleBuilder.toRoleDTO(roleOptional.get());
    }

    // insert a ROLE using RoleDTO
    public UUID insert(RoleDTO roleDTO){
        Role role = RoleBuilder.toEntity(roleDTO);
        role = roleRepository.save(role);
        LOGGER.debug("Role with id {} was inserted in db", role.getId());
        return role.getId();
    }

    // delete a ROLE if found by Id
    public boolean deleteRoleById(UUID id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (!roleOptional.isPresent()) {
            LOGGER.error("Role with id {} was not found in db", id);
            throw new ResourceNotFoundException(Role.class.getSimpleName() + " with id: " + id);
        }
        roleRepository.deleteById(roleOptional.get().getId());
        return true;
    }

    // update a ROLE if found by Id
    public UUID updateRole(RoleDTO roleDTO) {
        Optional<Role> roleOptional = roleRepository.findById(roleDTO.getId());
        if (!roleOptional.isPresent()) {
            LOGGER.error("Role with id {} was not found in db", roleDTO.getId());
            throw new ResourceNotFoundException(Role.class.getSimpleName() + " with id: " + roleDTO.getId());
        }
        Role role = RoleBuilder.toEntityWithId(roleDTO);
        role = roleRepository.save(role);
        LOGGER.debug("Role with id {} was updated in db", role.getId());
        return role.getId();
    }
}
