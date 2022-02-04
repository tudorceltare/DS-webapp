package ro.tuc.ds2020.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ro.tuc.ds2020.Ds2020TestConfig;
import ro.tuc.ds2020.dtos.PersonDTO;

import static org.springframework.test.util.AssertionErrors.assertEquals;

import java.util.List;
import java.util.UUID;

//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/test-sql/create.sql")
//@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:/test-sql/delete.sql")
public class PersonServiceIntegrationTests extends Ds2020TestConfig {
//
//    @Autowired
//    PersonService personService;
//
//    @Test
//    public void testGetCorrect() {
//        List<PersonDTO> personDTOList = personService.findPersons();
//        assertEquals("Test Insert Person", 1, personDTOList.size());
//    }
//
//    @Test
//    public void testInsertCorrectWithGetById() {
//        PersonDTO p = new PersonDTO("John", "username", "Somewhere Else street", "pa$$word",22, "some email");
//        UUID insertedID = personService.insert(p);
//
//        PersonDTO insertedPerson = new PersonDTO(insertedID, p.getName(), p.getUsername(), p.getAddress(), p.getPassword(), p.getAge(), p.getEmail());
//        PersonDTO fetchedPerson = personService.findPersonById(insertedID);
//
//        assertEquals("Test Inserted Person", insertedPerson, fetchedPerson);
//    }
//
//    @Test
//    public void testInsertCorrectWithGetAll() {
//        PersonDTO p = new PersonDTO("John", "username", "Somewhere Else street", "some password", 22, "some email");
//        personService.insert(p);
//
//        List<PersonDTO> personDTOList = personService.findPersons();
//        assertEquals("Test Inserted Persons", 2, personDTOList.size());
//    }
}
