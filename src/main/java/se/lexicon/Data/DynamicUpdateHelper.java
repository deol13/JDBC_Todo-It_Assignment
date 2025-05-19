package se.lexicon.Data;

import se.lexicon.Model.Person;
import se.lexicon.Model.TodoItem;

public class DynamicUpdateHelper {

    public static String PeopleUpdate(Person person) {
        String sql = "UPDATE person SET ";

        if(!person.getFirst_name().isEmpty() && !person.getLast_name().isEmpty()) sql += "first_name = ? , last_name = ?";
        else {
            if(!person.getFirst_name().isEmpty()) sql += "first_name = ? ";
            if(!person.getLast_name().isEmpty()) sql += "last_name = ? ";
        }
        sql += "WHERE person_id = ?";
        return sql;
    }

    public static String TodoItemUpdate(TodoItem item) {
        //"UPDATE todo_item SET title = ? , description = ? , deadline = ? , done = ? , assignee_id = ? WHERE todo_id = ?";
        String sql = "UPDATE todo_item SET ";

        if(!item.getTitle().isEmpty()) sql += " title = ? ,";
        if(!item.getDescription().isEmpty()) sql += " description = ? ,";
        if(item.getDeadline() != null) sql += " deadline = ? ,";
        sql += "done = ? ,";
        if(item.getAssignee_id() != -1) sql += " assignee_id = ?";

        if(sql.lastIndexOf(",") == (sql.length() - 1)) sql = sql.substring(0, sql.length() -1);

        sql += "WHERE todo_id = ?";
        return sql;
    }
}
