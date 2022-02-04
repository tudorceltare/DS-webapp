package ro.tuc.ds2020.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ro.tuc.ds2020.Ds2020TestConfig;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.services.PersonService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PersonControllerUnitTest extends Ds2020TestConfig {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private PersonService service;
//
//    @Test
//    public void insertPersonTest() throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        PersonDTO personDTO = new PersonDTO("John", "username", "Somewhere Else street", "pa$$word", 22, "email");
//
//        mockMvc.perform(post("/person")
//                .content(objectMapper.writeValueAsString(personDTO))
//                .contentType("application/json"))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void insertPersonTestFailsDueToAge() throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        PersonDTO personDTO = new PersonDTO("John", "username", "Somewhere Else street", "some password",17, "email");
//
//        mockMvc.perform(post("/person")
//                .content(objectMapper.writeValueAsString(personDTO))
//                .contentType("application/json"))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void insertPersonTestFailsDueToNull() throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        PersonDTO personDTO = new PersonDTO("John", "username", null, "password3", 17, "email");
//
//        mockMvc.perform(post("/person")
//                .content(objectMapper.writeValueAsString(personDTO))
//                .contentType("application/json"))
//                .andExpect(status().isBadRequest());
//    }
}
