package se.lexicon.Data;

import se.lexicon.Model.Person;
import se.lexicon.Model.TodoItem;

import java.util.Collection;

public interface TodoItemDao extends BaseDAO<TodoItem, Integer>{
    //TodoItem create(TodoItem item);
    //Collection<TodoItem> findAll();
    TodoItem findById(int id);
    Collection<TodoItem> findByDoneStatus(boolean status);
    Collection<TodoItem> findByAssignee(int id);
    Collection<TodoItem> findByAssignee(Person person);
    Collection<TodoItem> findByUnassignedTodoItems();
    //TodoItem update(TodoItem item);
    //boolean deleteById(int id);
}
