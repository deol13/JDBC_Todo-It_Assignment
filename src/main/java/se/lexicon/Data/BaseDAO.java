package se.lexicon.Data;

import se.lexicon.Model.Person;

import java.util.Collection;


public interface BaseDAO<T, ID> {
    T create(T object);
    T findById(ID id);
    Collection<T> findAll();
    T update(T object);
    boolean deleteById(ID id);
}
