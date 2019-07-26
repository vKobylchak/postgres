package ua.crawler.repositories;

import org.springframework.data.repository.CrudRepository;
import ua.crawler.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
