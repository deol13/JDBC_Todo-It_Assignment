package se.lexicon.Impl;

import se.lexicon.Interfaces.People;
import se.lexicon.Model.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PeopleImpl implements People {
    private List<Person> listOfPeople;

    public PeopleImpl() { listOfPeople = new ArrayList<Person>(); }

    @Override
    public Person create(Person person) {
        return null;
    }

    @Override
    public Collection<Person> findAll() {
        return List.of();
    }

    @Override
    public Person findById(int id) {
        return null;
    }

    @Override
    public Collection<String> findByName(String name) {
        return List.of();
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
