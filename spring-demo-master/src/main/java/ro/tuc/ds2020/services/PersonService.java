package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.LoginDTO;
import ro.tuc.ds2020.dtos.LoginResponseDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.entities.SmartDevice;
import ro.tuc.ds2020.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;
    private final SmartDeviceService smartDeviceService;

    @Autowired
    public PersonService(PersonRepository personRepository, SmartDeviceService smartDeviceService) {
        this.personRepository = personRepository;
        this.smartDeviceService = smartDeviceService;
    }

    // get ALL PERSONS
    public List<PersonDTO> findPersons() {
        List<Person> personList = personRepository.findAll();
        for (Person person : personList){
            System.out.println(person.toString());
        }
        return personList.stream()
                .map(PersonBuilder::toPersonDTO)
                .collect(Collectors.toList());
    }

    // get PERSON by Id
    // in order to display DETAILS
    public PersonDetailsDTO findPersonById(UUID id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (!personOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        return PersonBuilder.toPersonDetailsDTO(personOptional.get());
    }

    // insert a PERSON using PersonDTO
    public UUID insert(PersonDTO personDTO) {
        Person person = PersonBuilder.toEntity(personDTO);
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was inserted in db", person.getId());
        return person.getId();
    }

    // delete a PERSON if found by Id
    public boolean deletePersonById(UUID id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (!personOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        for (SmartDevice device : personOptional.get().getSmartDevices()) {
            smartDeviceService.deleteSmartDeviceById(device.getId());
        }
        personRepository.deleteById(personOptional.get().getId());
        return true;
    }

    // update a PERSON if found by Id
    public UUID updatePerson(PersonDTO personDTO) {
        Optional<Person> personOptional = personRepository.findById(personDTO.getId());
        if (!personOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", personDTO.getId());
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + personDTO.getId());
        }
        Person person = PersonBuilder.toEntityWithId(personDTO);
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was updated in db", person.getId());
        return person.getId();
    }

    // login a PERSON if username found and password matches
    public LoginResponseDTO loginPerson(LoginDTO personDTO) {
        Optional<Person> personOptional = personRepository.findByUsername(personDTO.getUsername());
        if (!personOptional.isPresent()) {
            LOGGER.error("Person with username {} was not found in db", personDTO.getUsername());
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with username: " + personDTO.getUsername());
        }
        if (personOptional.get().getPassword().equals(personDTO.getPassword())) {
            return LoginResponseDTO.builder()
                    .response(personOptional.get().getRole())
                    .user_id(personOptional.get().getId())
                    .build();
        }
        return LoginResponseDTO.builder()
                .response("Wrong credentials")
                .user_id(UUID.fromString("0"))
                .build();
    }

    // get all PERSON objects from db
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

}
