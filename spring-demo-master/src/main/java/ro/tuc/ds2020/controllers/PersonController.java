package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.LoginDTO;
import ro.tuc.ds2020.dtos.LoginResponseDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.services.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // get ALL PERSONS
    @GetMapping()
    public ResponseEntity<List<PersonDTO>> getPersons() {
        List<PersonDTO> dtos = personService.findPersons();
//        for (PersonDTO dto : dtos) {
//            Link personLink = linkTo(methodOn(PersonController.class)
//                    .getPerson(dto.getId())).withRel("personDetails");
//            dto.add(personLink);
//        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // get PERSON by Id
    // in order to display DETAILS
    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDetailsDTO> getPerson(@PathVariable("id") UUID personId) {
        PersonDetailsDTO dto = personService.findPersonById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // insert a PERSON using PersonDTO
    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody PersonDTO personDTO) {
        UUID personID = personService.insert(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    // delete a PERSON if found by Id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable("id") UUID personId){
        boolean response = personService.deletePersonById(personId);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    // update a PERSON if found by Id
    @PostMapping(value = "/update")
    public ResponseEntity<UUID> updatePerson(@Valid @RequestBody PersonDTO personDto) {
        UUID id = personService.updatePerson(personDto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    // login a PERSON if username found and password matches
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> loginPerson(@Valid @RequestBody LoginDTO loginDTO) {
        LoginResponseDTO responseDTO = personService.loginPerson(loginDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
