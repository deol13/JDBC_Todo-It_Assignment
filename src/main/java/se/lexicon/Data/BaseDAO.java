package se.lexicon.Data;

import java.util.Collection;


public interface BaseDAO<T, ID> {
    T create(T object);
    Collection<T> findAll();
    T update(T object);
    boolean deleteById(ID id);
}
