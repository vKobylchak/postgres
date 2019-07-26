package ua.crawler.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.crawler.entities.Person;
import ua.crawler.repositories.PersonRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
public class PersonController {

    private PersonRepository personRepository;

    private final static Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping("json")
    public void json(){

        File jsonFile = null;
        try {
            jsonFile = ResourceUtils.getFile("classpath:people.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();

            try {
                List<Person> people = objectMapper.readValue(jsonFile, new TypeReference<List<Person>>() {
                });

                personRepository.saveAll(people);
                personRepository.count();
                logger.info("All records saved.");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
