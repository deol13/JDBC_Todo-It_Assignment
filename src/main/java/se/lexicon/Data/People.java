package se.lexicon.Data;

import se.lexicon.Model.Person;

import java.util.Collection;

public interface People extends BaseDAO<Person, Integer> {
    Collection<Person> findByName(String name);
}
