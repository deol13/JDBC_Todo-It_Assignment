package se.lexicon.Data;

import java.util.Collection;

public interface BaseFunctionality<T> {
    T create(T object);
    Collection<T> findAll();
    T update(T object);
    boolean deleteById(int id);
}
