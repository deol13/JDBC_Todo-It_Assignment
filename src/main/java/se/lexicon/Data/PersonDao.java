package se.lexicon.Data;

import se.lexicon.Model.Person;

import java.util.Collection;

public interface PersonDao extends BaseDAO<Person, Integer> {
    //Person create(Person person);
    //Collection<Person> findAll();
    Person findById(int id);
    Collection<Person> findByName(String name);
    //Person update(Person person);
    //boolean deleteById(int id);
}
