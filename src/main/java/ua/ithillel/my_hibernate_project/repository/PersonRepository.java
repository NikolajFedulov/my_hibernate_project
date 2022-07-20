package ua.ithillel.my_hibernate_project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.ithillel.my_hibernate_project.model.Person;

@Repository
public interface PersonRepository extends CrudRepository <Person, Long>{
}
