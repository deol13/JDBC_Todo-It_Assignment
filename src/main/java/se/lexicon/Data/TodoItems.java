package se.lexicon.Data;

import se.lexicon.Model.Person;
import se.lexicon.Model.TodoItem;

import java.util.Collection;

public interface TodoItems extends BaseDAO<TodoItem, Integer>{
    Collection<TodoItem> findByDoneStatus(boolean status);
    Collection<TodoItem> findByAssignee(int id);
    Collection<TodoItem> findByAssignee(Person person);
    Collection<TodoItem> findByUnassignedTodoItems();
}
