package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

    // Example: JPA generate Query by Field
    List<Person> findByName(String name);
    Optional<Person> findByUsername(String username);

    // Example: Write Custom Query
    // This query finds people with the age over 60
    @Query(value = "SELECT p " +
            "FROM Person p " +
            "WHERE p.name = :name " +
            "AND p.age >= 60  ")
    Optional<Person> findSeniorsByName(@Param("name") String name);

}
