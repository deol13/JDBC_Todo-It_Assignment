package se.lexicon;

import se.lexicon.DB.MySQLConnection;
import se.lexicon.Data.Impl.PeopleImpl;
import se.lexicon.Data.Impl.TodoItemsImpl;
import se.lexicon.Exceptions.ExceptionHandler;
import se.lexicon.Model.Person;
import se.lexicon.Model.TodoItem;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //personCreate();
        //personFindAll();
        //personFindById();
        //personFindByName();
        //personUpdate();
        //personDelete();

        //todoItemCreate();
        //todoItemFindAll();
        //todoItemFindById();
        //todoItemFindByStatus();
        //todoItemFindByAssigneeId();
        //todoItemFindByAssigneePerson();
        //todoItemFindByUnassignedTodoItems();

        //todoItemUpdate();
        //todoItemDelete();
    }

    public static void todoItemCreate(){
        System.out.println("Create item operation");

        try{
            TodoItemsImpl items = new TodoItemsImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a title:");
            String title = scanner.nextLine();
            System.out.println("Enter a description:");
            String desc = scanner.nextLine();
            System.out.println("Done or not?:");
            String strDone = scanner.nextLine();
            boolean done = strDone.equalsIgnoreCase("done");
            System.out.println("Person id:");
            int personId = scanner.nextInt();

            LocalDate deadline = LocalDate.now();
            TodoItem savedItem = items.create(new TodoItem(title, desc, Date.valueOf(deadline), done, personId));

            System.out.println("savedItem = " + savedItem.toString());
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "MySQL DB Connection Failed while saving item.");
        } catch (InputMismatchException e) {
            ExceptionHandler.handleExceptions(e,"Scanner error in todoItemCreate");
        }
    }

    public static void todoItemFindAll() {
        System.out.println("Find all items operation");

        try {
            TodoItemsImpl items = new TodoItemsImpl(MySQLConnection.getConnection());

            List<TodoItem> itemList = items.findAll().stream().toList();

            itemList.forEach(i -> System.out.println(i.toString()));
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "MySQL DB Connection Failed while finding all items.");
        }
    }

    public static void todoItemFindById(){
        System.out.println("Find item by id operation");
        try{
            TodoItemsImpl items = new TodoItemsImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an id");
            int id = scanner.nextInt();

            TodoItem foundItem = items.findById(id);

            System.out.println("foundItem = " + foundItem.toString());
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "MySQL DB Connection Failed while finding item by id.");
        } catch (InputMismatchException e) {
            ExceptionHandler.handleExceptions(e,"Scanner error in todoItemFindById");
        }
    }

    public static void todoItemFindByStatus(){
        System.out.println("Find item by status operation");
        try{
            TodoItemsImpl items = new TodoItemsImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter done or not?:");
            String strDone = scanner.nextLine();
            boolean done = strDone.equalsIgnoreCase("done");

            List<TodoItem> foundItems = items.findByDoneStatus(done).stream().toList();

            foundItems.forEach(i -> System.out.println(i.toString()));
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "MySQL DB Connection Failed while finding all items by status.");
        }
    }

    public static void todoItemFindByAssigneeId() {
        System.out.println("Find item by assignee id operation");
        try{
            TodoItemsImpl items = new TodoItemsImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter person id:");
            int personId = scanner.nextInt();

            List<TodoItem> foundItems = items.findByAssignee(personId).stream().toList();

            foundItems.forEach(i -> System.out.println(i.toString()));
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "MySQL DB Connection Failed while finding all assignee id.");
        } catch (InputMismatchException e) {
            ExceptionHandler.handleExceptions(e,"Scanner error in todoItemFindByAssigneeId");
        }
    }

    public static void todoItemFindByAssigneePerson() {
        System.out.println("Find item by Assignee person operation");
        try{
            TodoItemsImpl items = new TodoItemsImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an id:");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter a first name:");
            String fName = scanner.nextLine();
            System.out.println("Enter a last name:");
            String lName = scanner.nextLine();

            List<TodoItem> foundItems = items.findByAssignee(new Person(id, fName, lName)).stream().toList();

            foundItems.forEach(i -> System.out.println(i.toString()));
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "MySQL DB Connection Failed while finding all items by assignee person.");
        } catch (InputMismatchException e) {
            ExceptionHandler.handleExceptions(e,"Scanner error in todoItemFindByAssigneePerson");
        }
    }

    public static void todoItemFindByUnassignedTodoItems() {
        System.out.println("Find item by unassigned to do item operation");
        try{
            TodoItemsImpl items = new TodoItemsImpl(MySQLConnection.getConnection());

            List<TodoItem> foundItems = items.findByUnassignedTodoItems().stream().toList();

            foundItems.forEach(i -> System.out.println(i.toString()));
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "MySQL DB Connection Failed while finding all items by unassigned.");
        }
    }

    public static void todoItemUpdate() {
        System.out.println("Update item operation");
        try{
            TodoItemsImpl items = new TodoItemsImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an id to change");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter a title");
            String title = scanner.nextLine();
            System.out.println("Enter a description");
            String desc= scanner.nextLine();
            System.out.println("Enter a deadline");
            String deadline= scanner.nextLine();
            System.out.println("Done or not?:");
            String strDone = scanner.nextLine();
            boolean done = strDone.equalsIgnoreCase("done");
            System.out.println("Enter a persons id");
            int assignee_id= scanner.nextInt();
            scanner.nextLine();

            TodoItem updatedItem = items.update(new TodoItem(id, title, desc, Date.valueOf(deadline), done, assignee_id));

            System.out.println("savedPerson = " + updatedItem.toString());
            System.out.println("Operation is Done!");
        }
        catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "MySQL DB Connection Failed while updating item.");
        } catch (InputMismatchException e) {
            ExceptionHandler.handleExceptions(e,"Scanner error in todoItemUpdate");
        }
    }

    public static void todoItemDelete() {
        System.out.println("Delete item operation");
        try{
            TodoItemsImpl items = new TodoItemsImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an id to delete");
            int id = scanner.nextInt();

            if(items.deleteById(id)) System.out.println("Delete Success");
            else System.out.println("Delete Failure");

            System.out.println("Operation is Done!");
        }
        catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "MySQL DB Connection Failed while deleting item.");
        } catch (InputMismatchException e) {
            ExceptionHandler.handleExceptions(e,"Scanner error in todoItemDelete");
        }
    }

    // ------------------------------------------------------------------------------

    public static void personCreate() {
        System.out.println("Create person operation");
        try{
            PeopleImpl people = new PeopleImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a first name:");
            String fName = scanner.nextLine();
            System.out.println("Enter a last name:");
            String lName = scanner.nextLine();

            Person savedPerson = people.create(new Person(fName, lName));

            System.out.println("savedPerson = " + savedPerson.toString());
            System.out.println("Operation is Done!");
            SendEMail.sendEmail("email", "JDBC todo assignment", "New person saved in the database");
        }catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "MySQL DB Connection Failed while saving person.");
        }
    }

    public static void personFindAll() {
        System.out.println("Find all people operation");
        try {
            PeopleImpl people = new PeopleImpl(MySQLConnection.getConnection());

            List<Person> personList = people.findAll().stream().toList();

            personList.forEach(p -> System.out.println(p.toString()));
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "MySQL DB Connection Failed while finding all person's.");
        }
    }

    public static void personFindById(){
        System.out.println("Find person by id operation");
        try{
            PeopleImpl people = new PeopleImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an id");
            int pId = scanner.nextInt();

            Person foundPerson = people.findById(pId);

            System.out.println("foundPerson = " + foundPerson.toString());
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "MySQL DB Connection Failed while finding person by id.");
        } catch (InputMismatchException e) {
            ExceptionHandler.handleExceptions(e,"Scanner error in personFindById");
        }
    }

    public static void personFindByName(){
        System.out.println("Find people by name operation");
        try{
            PeopleImpl people = new PeopleImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a name");
            String name = scanner.nextLine();

            List<Person> personList = people.findByName(name).stream().toList();

            personList.forEach(p -> System.out.println(p.toString()));
            System.out.println("Operation is Done!");
        } catch (IllegalArgumentException e){
            System.out.println("Wrong amount of input, need a fist and last name with a space between them.");
        }
        catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "MySQL DB Connection Failed while finding person by name.");
        }
    }

    public static void personUpdate(){
        System.out.println("Update person operation");
        try{
            PeopleImpl people = new PeopleImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an id to change");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter a first name");
            String fName = scanner.nextLine();
            System.out.println("Enter a last name");
            String lName= scanner.nextLine();

            Person updatedPerson = people.update(new Person(id, fName, lName));

            System.out.println("savedPerson = " + updatedPerson.toString());
            System.out.println("Operation is Done!");
        }
        catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "MySQL DB Connection Failed while updating person.");
        } catch (InputMismatchException e) {
            ExceptionHandler.handleExceptions(e,"Scanner error in personUpdate");
        }
    }

    public static void personDelete(){
        System.out.println("Delete person operation");
        try{
            PeopleImpl people = new PeopleImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an id to delete");
            int id = scanner.nextInt();

            if(people.deleteById(id)) System.out.println("Delete Success");
            else System.out.println("Delete Failure");

            System.out.println("Operation is Done!");
        }
        catch (SQLException e) {
            ExceptionHandler.handleExceptions(e, "MySQL DB Connection Failed while deleting person.");
        } catch (InputMismatchException e) {
            ExceptionHandler.handleExceptions(e,"Scanner error in personDelete");
        }
    }
}