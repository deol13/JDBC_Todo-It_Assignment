package se.lexicon.Interfaces;

import se.lexicon.Model.Person;

import java.util.Collection;

public interface People {
    Person create(Person person);
    Collection<Person> findAll();
    Person findById(int id);
    Collection<String> findByName(String name);
    Person update(Person person);
    boolean delete(int id);
}
