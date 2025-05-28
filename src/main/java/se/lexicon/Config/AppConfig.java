package se.lexicon.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.lexicon.DB.MySQLConnection;
import se.lexicon.Data.Impl.PersonDaoImpl;
import se.lexicon.Data.Impl.TodoItemDaoImpl;
import se.lexicon.Data.TodoItemDao;
import se.lexicon.Service.Impl.PersonServiceImpl;
import se.lexicon.Service.Impl.TodoItemServiceImpl;

import java.sql.SQLException;

@Configuration
public class AppConfig {
    @Bean
    public MySQLConnection mySQLConnection(){
        return new MySQLConnection();
    }
    @Bean
    public PersonDaoImpl personDao() throws SQLException {
            return new PersonDaoImpl(mySQLConnection());
    }
    @Bean
    public TodoItemDaoImpl todoItemDao() throws SQLException {
        return new TodoItemDaoImpl(mySQLConnection());
    }

    @Bean
    public PersonServiceImpl personService() throws SQLException{
        return new PersonServiceImpl(personDao());
    }

    @Bean
    public TodoItemServiceImpl todoItemService() throws SQLException{
        return new TodoItemServiceImpl(todoItemDao());
    }
}
