package se.lexicon;

import se.lexicon.DB.MySQLConnection;
import se.lexicon.Data.Impl.PeopleImpl;
import se.lexicon.Data.Impl.TodoItemsImpl;
import se.lexicon.Model.Person;
import se.lexicon.Model.TodoItem;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        //personCreate();
        //personFindAll();
        //personFindById();
        //personFindByName();
        //personUpdate();
        //personDelete();

        //todoItemCreate();
        todoItemFindAll();
        //todoItemFindById();
        //todoItemFindByStatus();
        //todoItemFindByAssigneeId();
        //todoItemFindByAssigneePerson();
        //todoItemFindByUnassignedTodoItems();

        //todoItemUpdate();
        //todoItemDelete();
    }

    public static void todoItemCreate(){
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

            System.out.println("savedItem = " + savedItem.getTitle());
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void todoItemFindAll() {
        try {
            TodoItemsImpl items = new TodoItemsImpl(MySQLConnection.getConnection());

            List<TodoItem> itemList = items.findAll().stream().toList();

            itemList.forEach(i -> System.out.println("id: " + i.getTodo_id() + " , title: " + i.getTitle() + " , person_id: " + i.getAssignee_id()));
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void todoItemFindById(){
        try{
            TodoItemsImpl items = new TodoItemsImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an id");
            int id = scanner.nextInt();

            TodoItem savedItem = items.findById(id);

            System.out.println("savedItem = " + savedItem.getTodo_id() + ": " +  savedItem.getTitle());
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void todoItemFindByStatus(){
        try{
            TodoItemsImpl items = new TodoItemsImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter done or not?:");
            String strDone = scanner.nextLine();
            boolean done = strDone.equalsIgnoreCase("done") ? true : false;

            List<TodoItem> foundItems = items.findByDoneStatus(done).stream().toList();

            foundItems.forEach(i -> System.out.println("id: " + i.getTodo_id() + " , title: " + i.getTitle() + " , person_id: " + i.getAssignee_id()));
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void todoItemFindByAssigneeId() {
        try{
            TodoItemsImpl items = new TodoItemsImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter person id:");
            int personId = scanner.nextInt();

            List<TodoItem> foundItems = items.findByAssignee(personId).stream().toList();

            foundItems.forEach(i -> System.out.println("id: " + i.getTodo_id() + " , title: " + i.getTitle() + " , person_id: " + i.getAssignee_id()));
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void todoItemFindByAssigneePerson() {
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

            foundItems.forEach(i -> System.out.println("id: " + i.getTodo_id() + " , title: " + i.getTitle() + " , person_id: " + i.getAssignee_id()));
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void todoItemFindByUnassignedTodoItems() {
        try{
            TodoItemsImpl items = new TodoItemsImpl(MySQLConnection.getConnection());

            List<TodoItem> foundItems = items.findByUnassignedTodoItems().stream().toList();

            foundItems.forEach(i -> System.out.println("id: " + i.getTodo_id() + " , title: " + i.getTitle() + " , person_id: " + i.getAssignee_id()));
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }


    public static void todoItemUpdate() {
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

            System.out.println("savedPerson = " + updatedItem.getTodo_id() + ": " +  updatedItem.getTitle());
            System.out.println("Operation is Done!");
        }
        catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void todoItemDelete() {
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
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    // ------------------------------------------------------------------------------

    public static void personCreate() {
        try{
            PeopleImpl people = new PeopleImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a first name:");
            String fName = scanner.nextLine();
            System.out.println("Enter a last name:");
            String lName = scanner.nextLine();

            Person savedPerson = people.create(new Person(fName, lName));

            System.out.println("savedPerson = " + savedPerson.getFirst_name() + " " + savedPerson.getLast_name());
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void personFindAll() {
        try {
            PeopleImpl people = new PeopleImpl(MySQLConnection.getConnection());

            List<Person> personList = people.findAll().stream().toList();

            personList.forEach(p -> System.out.println(p.getPerson_id() + ": " + p.getFirst_name() + " " + p.getLast_name()));
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void personFindById(){
        try{
            PeopleImpl people = new PeopleImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an id");
            int pId = scanner.nextInt();

            Person savedPerson = people.findById(pId);

            System.out.println("savedPerson = " + savedPerson.getPerson_id() + ": " +  savedPerson.getFirst_name() + " " + savedPerson.getLast_name());
            System.out.println("Operation is Done!");
        }catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void personFindByName(){
        try{
            PeopleImpl people = new PeopleImpl(MySQLConnection.getConnection());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a name");
            String name = scanner.nextLine();

            List<Person> personList = people.findByName(name).stream().toList();

            personList.forEach(p -> System.out.println(p.getPerson_id() + ": " + p.getFirst_name() + " " + p.getLast_name()));
            System.out.println("Operation is Done!");
        } catch (IllegalArgumentException e){
            System.out.println("Wrong amount of input, need a fist and last name with a space between them.");
        }
        catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void personUpdate(){
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

            System.out.println("savedPerson = " + updatedPerson.getPerson_id() + ": " + updatedPerson.getFirst_name() + " " + updatedPerson.getLast_name());
            System.out.println("Operation is Done!");
        }
        catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void personDelete(){
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
            System.out.println("MySQL DB Connection Failed.");
        }
    }
}