package se.lexicon.Service.Impl;


import org.springframework.stereotype.Component;
import se.lexicon.Data.TodoItemDao;
import se.lexicon.Model.Person;
import se.lexicon.Model.TodoItem;
import se.lexicon.Service.TodoItemService;

import java.util.Collection;

public class TodoItemServiceImpl implements TodoItemService {
    private final TodoItemDao todoItemDao;

    public TodoItemServiceImpl(TodoItemDao todoItemDao) {
        this.todoItemDao = todoItemDao;
    }

    @Override
    public TodoItem findById(int id) {
        if(id < 0) throw new IllegalArgumentException("Id can't be lower than zero");
        return todoItemDao.findById(id);
    }

    @Override
    public Collection<TodoItem> findByDoneStatus(boolean status) {
        return todoItemDao.findByDoneStatus(status);
    }

    @Override
    public Collection<TodoItem> findByAssignee(int id) {
        if(id < 0) throw new IllegalArgumentException("Id can't be lower than zero");
        return todoItemDao.findByAssignee(id);
    }

    @Override
    public Collection<TodoItem> findByAssignee(Person person) {
        if(person == null) throw new IllegalArgumentException("Person should not be null");
        return todoItemDao.findByAssignee(person);
    }

    @Override
    public Collection<TodoItem> findByUnassignedTodoItems() {
        return todoItemDao.findByUnassignedTodoItems();
    }

    @Override
    public Collection<TodoItem> findAll() {
        return todoItemDao.findAll();
    }

    @Override
    public TodoItem create(TodoItem item) {
        if(item == null) throw new IllegalArgumentException("Item should not be null");
        return todoItemDao.create(item);
    }

    @Override
    public TodoItem update(TodoItem item) {
        if(item == null) throw new IllegalArgumentException("Item can't be null");
        return todoItemDao.update(item);
    }

    @Override
    public boolean deleteById(Integer id) {
        if(id < 0) throw new IllegalArgumentException("Id can't be lower than zero");
        return todoItemDao.deleteById(id);
    }
}
