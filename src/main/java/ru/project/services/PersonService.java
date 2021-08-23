package ru.project.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.project.dao.PersonDao;
import ru.project.models.Person;

public class PersonService {
    final static Logger logger = LoggerFactory.getLogger(PersonService.class);

    public Person getById(Integer id) {
        return new PersonDao().getById(id);
    }

    public Person getByFirstname(String firstname) {
        return new PersonDao().getByFirstname(firstname);
    }

    public void changeSurname(Integer id, String newSurname){
        new PersonDao().changeSurname(id, newSurname);
    }
}
