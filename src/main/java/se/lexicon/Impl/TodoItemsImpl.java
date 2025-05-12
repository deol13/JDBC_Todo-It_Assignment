package se.lexicon.Impl;

import se.lexicon.Interfaces.TodoItems;
import se.lexicon.Model.Person;
import se.lexicon.Model.TodoItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoItemsImpl implements TodoItems {
    private List<TodoItem> listOfTodoItems;

    public TodoItemsImpl() { listOfTodoItems = new ArrayList<TodoItem>(); }

    @Override
    public TodoItem create(TodoItem item) {
        return null;
    }

    @Override
    public Collection<TodoItem> findAll() {
        return List.of();
    }

    @Override
    public TodoItem findById(int id) {
        return null;
    }

    @Override
    public Collection<TodoItem> findByDoneStatus(boolean status) {
        return List.of();
    }

    @Override
    public Collection<TodoItem> findByAssignee(int id) {
        return List.of();
    }

    @Override
    public Collection<TodoItem> findByAssignee(Person person) {
        return List.of();
    }

    @Override
    public Collection<TodoItem> findByUnassignedTodoItems() {
        return List.of();
    }

    @Override
    public TodoItem update(TodoItem item) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
