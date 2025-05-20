package se.lexicon.Data.Impl;

import se.lexicon.Data.BaseDAO;
import se.lexicon.Data.People;
import se.lexicon.Exceptions.ExceptionHandler;
import se.lexicon.Model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PeopleImpl implements People {
    private Connection connection;

    public PeopleImpl(Connection connection) { this.connection = connection; }

    @Override
    public Person create(Person person) {
        String sql = "INSERT INTO person (first_name, last_name) VALUES(?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS) ) {

            preparedStatement.setString(1, person.getFirst_name());
            preparedStatement.setString(2, person.getLast_name());

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows);

            if (affectedRows > 0) {
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int generatedPersonId = resultSet.getInt(1);
                        System.out.println("generatedPersonId = " + generatedPersonId);
                        person.setPerson_id(generatedPersonId);
                    }
                }
            }
        } catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "Saving new person to");
        }
        return person;
    }

    @Override
    public Collection<Person> findAll() {
        List<Person> people = new ArrayList<>();

        String sql = "SELECT * FROM person";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql) ) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int personId = resultSet.getInt("person_id");
                    String first_name = resultSet.getString("first_name");
                    String last_name = resultSet.getString("last_name");
                    people.add(new Person(personId, first_name, last_name));
                }
            }

        } catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "Collecting all person entries from");
        }
        return people;
    }

    @Override
    public Person findById(Integer id) {
        Person person = null;

        String sql = "SELECT * FROM person WHERE person_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql) ) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int personId = resultSet.getInt("person_id");
                    String first_name = resultSet.getString("first_name");
                    String last_name = resultSet.getString("last_name");
                    person = new Person(personId, first_name, last_name);
                }
            }

        } catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "Collecting a person based on id from");
        }

        return person;
    }

    @Override
    public Collection<Person> findByName(String name) throws IllegalArgumentException {
        List<Person> people = new ArrayList<>();

        String sql = "SELECT * FROM person WHERE first_name LIKE ? AND last_name LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql) ) {

            String[] nameArr = name.split(" ");

            if(nameArr.length != 2) throw new IllegalArgumentException("Error: Need first and last name!");
            preparedStatement.setString(1, nameArr[0]);
            preparedStatement.setString(2, nameArr[1]);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int personId = resultSet.getInt("person_id");
                    String first_name = resultSet.getString("first_name");
                    String last_name = resultSet.getString("last_name");
                    people.add(new Person(personId, first_name, last_name));
                }
            }

        } catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "Collecting all person's with a specific name from");
        }
        return people;
    }

    @Override
    public Person update(Person person) {
        //Person newPerson = person;
        String sql = "UPDATE person SET first_name = ? , last_name = ? WHERE person_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql) ) {
            preparedStatement.setString(1, person.getFirst_name());
            preparedStatement.setString(2, person.getLast_name());
            preparedStatement.setInt(3, person.getPerson_id());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Update successful");
//                try (ResultSet resultSet = preparedStatement.getResultSet()) {
//                    while (resultSet.next()) {
//                        int personId = resultSet.getInt("person_id");
//                        String first_name = resultSet.getString("first_name");
//                        String last_name = resultSet.getString("last_name");
//                        newPerson = new Person(personId, first_name, last_name);
//                    }
//                }
            }
            else System.out.println("Update failed");

        } catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "Updating person to");
        }

        return person;//newPerson;
    }

    @Override
    public boolean deleteById(Integer id) {
        String sql = "DELETE FROM person WHERE person_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql) ) {
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "Deleting person from");
        }
        return false;
    }
}
