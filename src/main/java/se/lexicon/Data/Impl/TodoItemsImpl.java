package se.lexicon.Data.Impl;

import se.lexicon.Data.BaseDAO;
import se.lexicon.Data.TodoItems;
import se.lexicon.Model.Person;
import se.lexicon.Model.TodoItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoItemsImpl implements TodoItems{
    private Connection connection;

    public TodoItemsImpl(Connection connection) { this.connection = connection; }

    // TODO: implement email sender using java email sender
    @Override
    public TodoItem create(TodoItem item) {
        String sql = "INSERT INTO todo_item (title, description, deadline, done, assignee_id) VALUES(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS) ) {

            int done = item.isDone() ? 1 : 0;

            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setDate(3, item.getDeadline());
            preparedStatement.setInt(4, done);
            preparedStatement.setInt(5, item.getAssignee_id());

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows);

            if (affectedRows > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int generatedTodoItemId = resultSet.getInt(1);
                        System.out.println("generatedTodoItemId = " + generatedTodoItemId);
                        item.setTodo_id(generatedTodoItemId);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving todo item");
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Collection<TodoItem> findAll() {
        List<TodoItem> items = new ArrayList<>();

        String sql = "SELECT * FROM todo_item";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql) ) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int itemId = resultSet.getInt("todo_id");
                    String title = resultSet.getString("title");
                    String desc = resultSet.getString("description");
                    Date deadline = resultSet.getDate("deadline");
                    int intDone = resultSet.getInt("done");
                    boolean done = intDone == 1;
                    int personId = resultSet.getInt("assignee_id");

                    items.add(new TodoItem(personId, title, desc, deadline, done, personId));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error finding all items");
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public TodoItem findById(int id) {
        TodoItem item = null;

        String sql = "SELECT * FROM todo_item WHERE todo_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql) ) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int itemId = resultSet.getInt("todo_id");
                    String title = resultSet.getString("title");
                    String desc = resultSet.getString("description");
                    Date deadline = resultSet.getDate("deadline");
                    int intDone = resultSet.getInt("done");
                    boolean done = intDone == 1;
                    int personId = resultSet.getInt("assignee_id");
                    item = new TodoItem(itemId, title, desc, deadline, done, personId);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error finding item with " + id + " id");
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public Collection<TodoItem> findByDoneStatus(boolean status) {
        List<TodoItem> todoItemList = new ArrayList<>();
        int intDone = status ? 1 : 0;

        String sql = "SELECT * FROM todo_item WHERE done = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql) ) {

            preparedStatement.setInt(1, intDone);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int itemId = resultSet.getInt("todo_id");
                    String title = resultSet.getString("title");
                    String desc = resultSet.getString("description");
                    Date deadline = resultSet.getDate("deadline");
                    int intDone2 = resultSet.getInt("done");
                    boolean done = intDone2 == 1;
                    int personId = resultSet.getInt("assignee_id");
                    todoItemList.add(new TodoItem(itemId, title, desc, deadline, done, personId));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error finding items with status: " + status);
            e.printStackTrace();
        }

        return todoItemList;
    }

    @Override
    public Collection<TodoItem> findByAssignee(int id) {
        List<TodoItem> todoItemList = new ArrayList<>();
        String sql = "SELECT * FROM todo_item WHERE assignee_id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    int itemId = resultSet.getInt("todo_id");
                    String title = resultSet.getString("title");
                    String desc = resultSet.getString("description");
                    Date deadline = resultSet.getDate("deadline");
                    int intDone2 = resultSet.getInt("done");
                    boolean done = intDone2 == 1;
                    int personId = resultSet.getInt("assignee_id");
                    todoItemList.add(new TodoItem(itemId, title, desc, deadline, done, personId));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding unassigned items");
            e.printStackTrace();
        }

        return todoItemList;
    }

    @Override
    public Collection<TodoItem> findByAssignee(Person person) {
        List<TodoItem> todoItemList = new ArrayList<>();
        String sql = "SELECT * FROM todo_item WHERE assignee_id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, person.getPerson_id());

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    int itemId = resultSet.getInt("todo_id");
                    String title = resultSet.getString("title");
                    String desc = resultSet.getString("description");
                    Date deadline = resultSet.getDate("deadline");
                    int intDone2 = resultSet.getInt("done");
                    boolean done = intDone2 == 1;
                    int personId = resultSet.getInt("assignee_id");
                    todoItemList.add(new TodoItem(itemId, title, desc, deadline, done, personId));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error finding unassigned items");
            e.printStackTrace();
        }

        return todoItemList;
    }

    @Override
    public Collection<TodoItem> findByUnassignedTodoItems() {
        List<TodoItem> todoItemList = new ArrayList<>();
        String sql = "SELECT * FROM todo_item WHERE assignee_id is null";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int itemId = resultSet.getInt("todo_id");
                    String title = resultSet.getString("title");
                    String desc = resultSet.getString("description");
                    Date deadline = resultSet.getDate("deadline");
                    int intDone2 = resultSet.getInt("done");
                    boolean done = intDone2 == 1;
                    int personId = resultSet.getInt("assignee_id");
                    todoItemList.add(new TodoItem(itemId, title, desc, deadline, done, personId));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding unassigned items");
            e.printStackTrace();
        }
        return todoItemList;
    }

    @Override
    public TodoItem update(TodoItem item) {
        String sql = "UPDATE todo_item SET title = ? , description = ? , deadline = ? , done = ? , assignee_id = ? WHERE todo_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql) ) {
            preparedStatement.setString(1, item.getTitle());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setDate(3, item.getDeadline());
            preparedStatement.setInt(4, item.isDone() ? 1 : 0);
            preparedStatement.setInt(5, item.getAssignee_id());
            preparedStatement.setInt(6, item.getTodo_id());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Update successful");
            }
            else System.out.println("Update failed");

        } catch (SQLException e) {
            System.out.println("Error updating item");
            e.printStackTrace();
        } // TODO: Create exception handler class

        return item;
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql = "DELETE FROM todo_item WHERE todo_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql) ) {
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            System.out.println("Error deleting from todo_item");
            e.printStackTrace();
        }
        return false;
    }
}
