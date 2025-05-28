package se.lexicon.Service.Impl;

import org.springframework.stereotype.Component;
import se.lexicon.Data.PersonDao;
import se.lexicon.Model.Person;
import se.lexicon.Service.PersonService;

import java.util.Collection;

public class PersonServiceImpl implements PersonService {
    private final PersonDao personDao;

    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public Person findById(int id) {
        if(id < 0) throw new IllegalArgumentException("Id can't be lower than zero");
        return personDao.findById(id);
    }

    @Override
    public Collection<Person> findByName(String name) {
        if(name == null) throw new IllegalArgumentException("Name can't be null");
        if(name.trim().isEmpty()) throw new IllegalArgumentException("Name can't be empty");
        return personDao.findByName(name);
    }

    @Override
    public Collection<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public Person create(Person person) {
        if(person == null) throw new IllegalArgumentException("Person should not be null");
        return personDao.create(person);
    }

    @Override
    public Person update(Person person) {
        if(person == null) throw new IllegalArgumentException("Person can't be null");
        return personDao.update(person);
    }

    @Override
    public boolean deleteById(Integer id) {
        if(id < 0) throw new IllegalArgumentException("Id can't be lower than zero");
        return personDao.deleteById(id);
    }
}
