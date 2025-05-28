package se.lexicon.Service;


import se.lexicon.Data.BaseDAO;
import se.lexicon.Model.Person;

import java.util.Collection;

public interface PersonService extends BaseDAO<Person, Integer> {
    Person findById(int id);
    Collection<Person> findByName(String name);
}
