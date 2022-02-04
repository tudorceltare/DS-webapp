package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.SmartDeviceDTO;
import ro.tuc.ds2020.dtos.SmartDeviceDetailsDTO;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.entities.SmartDevice;

import java.util.*;

public class PersonBuilder {

    private PersonBuilder() {
    }

    // used to GET PERSONS for display on the HOME page
    public static PersonDTO toPersonDTO(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .username(person.getUsername())
                .password(person.getPassword())
                .age(person.getAge())
                .address(person.getAddress())
                .email(person.getEmail())
                .role(person.getRole())
                .build();
    }

    // used to GET a PERSON and it's details to display ont the individual PERSON page
    public static PersonDetailsDTO toPersonDetailsDTO(Person person) {
        List<UUID> devicesIds = new ArrayList<UUID>();
        List<String> devicesAddresses = new ArrayList<String>();
        List<Float> avgConsumption = new ArrayList<Float>();
        for (SmartDevice device : person.getSmartDevices()){
            devicesIds.add(device.getId());
            devicesAddresses.add(device.getAddress());
            avgConsumption.add(device.getAvgConsumption());
        }
        UUID[] devicesIdsArray = new UUID[devicesIds.size()];
        String[] deviceAddressesArray = new String[devicesAddresses.size()];
        Float[] avgConsumptionArray = new Float[avgConsumption.size()];
        devicesIds.toArray(devicesIdsArray);
        devicesAddresses.toArray(deviceAddressesArray);
        avgConsumption.toArray(avgConsumptionArray);

        return PersonDetailsDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .username(person.getUsername())
                .address(person.getAddress())
                .password(person.getPassword())
                .age(person.getAge())
                .email(person.getEmail())
                .role(person.getRole())
                .devicesIds(devicesIdsArray)
                .devicesAddresses(deviceAddressesArray)
                .avgConsumption(avgConsumptionArray)
                .build();
    }

    // used for INSERT
    public static Person toEntity(PersonDTO personDTO) {
        return new Person(personDTO.getName(),
                personDTO.getUsername(),
                personDTO.getAddress(),
                personDTO.getPassword(),
                personDTO.getAge(),
                personDTO.getEmail(),
                personDTO.getRole(),
                new HashSet<>()
        );
    }

    // used for UPDATE
    public static Person toEntityWithId(PersonDTO personDTO) {
        return new Person(personDTO.getId(),
                personDTO.getName(),
                personDTO.getUsername(),
                personDTO.getAddress(),
                personDTO.getPassword(),
                personDTO.getAge(),
                personDTO.getEmail(),
                personDTO.getRole()
        );
    }
}
