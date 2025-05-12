package se.lexicon.Interfaces;

import se.lexicon.Model.Person;
import se.lexicon.Model.TodoItem;

import java.util.Collection;

public interface TodoItems {
    TodoItem create(TodoItem item);
    Collection<TodoItem> findAll();
    TodoItem findById(int id);
    Collection<TodoItem> findByDoneStatus(boolean status);
    Collection<TodoItem> findByAssignee(int id);
    Collection<TodoItem> findByAssignee(Person person);
    Collection<TodoItem> findByUnassignedTodoItems();
    TodoItem update(TodoItem item);
    boolean deleteById(int id);
}
