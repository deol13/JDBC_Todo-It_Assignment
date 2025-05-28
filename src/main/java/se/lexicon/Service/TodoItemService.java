package se.lexicon.Service;

import se.lexicon.Data.BaseDAO;
import se.lexicon.Model.Person;
import se.lexicon.Model.TodoItem;

import java.util.Collection;

public interface TodoItemService extends BaseDAO<TodoItem, Integer> {
    TodoItem findById(int id);
    Collection<TodoItem> findByDoneStatus(boolean status);
    Collection<TodoItem> findByAssignee(int id);
    Collection<TodoItem> findByAssignee(Person person);
    Collection<TodoItem> findByUnassignedTodoItems();
}
