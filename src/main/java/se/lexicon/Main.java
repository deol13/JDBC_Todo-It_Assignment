package se.lexicon;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.lexicon.Config.AppConfig;
import se.lexicon.DB.MySQLConnection;
import se.lexicon.Data.Impl.PersonDaoImpl;
import se.lexicon.Data.Impl.TodoItemDaoImpl;
import se.lexicon.Data.PersonDao;
import se.lexicon.Model.Person;
import se.lexicon.Model.TodoItem;
import se.lexicon.Service.Impl.PersonServiceImpl;
import se.lexicon.Service.PersonService;
import se.lexicon.Service.TodoItemService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static PersonService personService;
    private static TodoItemService todoItemService;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        personService = context.getBean(PersonService.class);
        todoItemService = context.getBean(TodoItemService.class);

        boolean continueProgram = true;
        Scanner scanner = new Scanner(System.in);
        while (continueProgram) {

            System.out.println("Menu:\n" +
                    "1. todo item create\n" +
                    "2. todo item update\n" +
                    "3. todo item delete\n" +
                    "4. todo item findById\n" +
                    "5. todo item findByDoneStatus\n" +
                    "6. todo item findByAssignee(id)\n" +
                    "7. todo item findByAssignee(person)\n" +
                    "8. todo item findByUnassignedTodoItems\n" +
                    "9. todo item findAll\n" +
                    "10. person create\n" +
                    "11. person update\n" +
                    "12. person delete\n" +
                    "13. person findById\n" +
                    "14. person findByName\n" +
                    "15. person findAll\n" +
                    "0. Exit");
            String menuChoice = scanner.nextLine();
            switch (menuChoice) {
                case "1":
                    System.out.println("\nCreate item:");
                    todoItemCreate();
                    break;
                case "2":
                    System.out.println("\nUpdate item:");
                    todoItemUpdate();
                    break;
                case "3":
                    System.out.println("\nDelete item:");
                    todoItemDelete();
                    break;
                case "4":
                    System.out.println("\nFind item by id:");
                    todoItemFindById();
                    break;
                case "5":
                    System.out.println("\nFind item by status:");
                    todoItemFindByStatus();
                    break;
                case "6":
                    System.out.println("\nFind item by assignee id:");
                    todoItemFindByAssigneeId();
                    break;
                case "7":
                    System.out.println("\nFind item by assignee person:");
                    todoItemFindByAssigneePerson();
                    break;
                case "8":
                    System.out.println("\nFind item by unAssigned:");
                    todoItemFindByUnassignedTodoItems();
                    break;
                case "9":
                    System.out.println("\nFind all items:");
                    todoItemFindAll();
                    break;
                case "10":
                    System.out.println("\nCreate person:");
                    personCreate();
                    break;
                case "11":
                    System.out.println("\nUpdate person:");
                    personUpdate();
                    break;
                case "12":
                    System.out.println("\nDelete person:");
                    personDelete();
                    break;
                case "13":
                    System.out.println("\nFind person by id:");
                    personFindById();
                    break;
                case "14":
                    System.out.println("\nFind person by name:");
                    personFindByName();
                    break;
                case "15":
                    System.out.println("\nFind all people:");
                    personFindAll();
                    break;
                case "0":
                    System.out.println("Exit program");
                    continueProgram = false;
                    break;
                default:
                    System.out.println("Not a valid choice");
                    break;
            }
        }

    }
  /*
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

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
*/
    public static void todoItemCreate(){
        try{
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
            TodoItem savedItem = todoItemService.create(new TodoItem(title, desc, Date.valueOf(deadline), done, personId));

            System.out.println("savedItem = " + savedItem);
            System.out.println("Operation is Done!\n");
        } catch(IllegalArgumentException e) {
            System.out.println("Error: personCreate: " + e.getMessage());
        }
    }

    public static void todoItemFindAll() {
        try {
            List<TodoItem> itemList = todoItemService.findAll().stream().toList();

            itemList.forEach(System.out::println);
            System.out.println("Operation is Done!\n");
        }catch (IllegalArgumentException e) {
            System.out.println("Error: todoItemFindAll: " + e.getMessage());
        }
    }

    public static void todoItemFindById(){
        try{
            
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an id");
            int id = scanner.nextInt();

            TodoItem savedItem = todoItemService.findById(id);

            System.out.println("savedItem = " + savedItem);
            System.out.println("Operation is Done!\n");
        }catch (IllegalArgumentException e) {
            System.out.println("Error: todoItemFindById: " + e.getMessage());
        }
    }

    public static void todoItemFindByStatus(){
        try{
            
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter done or not?:");
            String strDone = scanner.nextLine();
            boolean done = strDone.equalsIgnoreCase("done") ? true : false;

            List<TodoItem> foundItems = todoItemService.findByDoneStatus(done).stream().toList();

            foundItems.forEach(System.out::println);
            System.out.println("Operation is Done!\n");
        }catch (IllegalArgumentException e) {
            System.out.println("Error: todoItemFindByStatus: " + e.getMessage());
        }
    }

    public static void todoItemFindByAssigneeId() {
        try{

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter person id:");
            int personId = scanner.nextInt();

            List<TodoItem> foundItems = todoItemService.findByAssignee(personId).stream().toList();

            foundItems.forEach(System.out::println);
            System.out.println("Operation is Done!\n");
        }catch (IllegalArgumentException e) {
            System.out.println("Error: todoItemFindByAssigneeId: " + e.getMessage());
        }
    }

    public static void todoItemFindByAssigneePerson() {
        try{

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an id:");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter a first name:");
            String fName = scanner.nextLine();
            System.out.println("Enter a last name:");
            String lName = scanner.nextLine();

            List<TodoItem> foundItems = todoItemService.findByAssignee(new Person(id, fName, lName)).stream().toList();

            foundItems.forEach(System.out::println);
            System.out.println("Operation is Done!\n");
        }catch (IllegalArgumentException e) {
            System.out.println("Error: todoItemFindByAssigneePerson: " + e.getMessage());
        }
    }

    public static void todoItemFindByUnassignedTodoItems() {
        try{

            List<TodoItem> foundItems = todoItemService.findByUnassignedTodoItems().stream().toList();

            foundItems.forEach(System.out::println);
            System.out.println("Operation is Done!\n");
        }catch (IllegalArgumentException e) {
            System.out.println("Error: todoItemFindByUnassignedTodoItems: " + e.getMessage());
        }
    }


    public static void todoItemUpdate() {
        try{

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

            TodoItem updatedItem = todoItemService.update(new TodoItem(id, title, desc, Date.valueOf(deadline), done, assignee_id));

            System.out.println("savedPerson = " + updatedItem);
            System.out.println("Operation is Done!\n");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: todoItemUpdate: " + e.getMessage());
        }
    }

    public static void todoItemDelete() {
        try{

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an id to delete");
            int id = scanner.nextInt();

            if(todoItemService.deleteById(id)) System.out.println("Delete Success");
            else System.out.println("Delete Failure");

            System.out.println("Operation is Done!\n");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error: todoItemDelete: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------------------

    public static void personCreate() {
        try{

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a first name:");
            String fName = scanner.nextLine();
            System.out.println("Enter a last name:");
            String lName = scanner.nextLine();

            Person savedPerson = personService.create(new Person(fName, lName));

            System.out.println("savedPerson = " + savedPerson);
            System.out.println("Operation is Done!\n");
        }catch(IllegalArgumentException e) {
            System.out.println("Error: personCreate: " + e.getMessage());
        }
    }

    public static void personFindAll() {
        try {

            List<Person> personList = personService.findAll().stream().toList();

            personList.forEach(System.out::println);
            System.out.println("Operation is Done!\n");
        }catch(IllegalArgumentException e) {
            System.out.println("Error: personFindAll: " + e.getMessage());
        }
    }

    public static void personFindById(){
        try{

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an id");
            int pId = scanner.nextInt();

            Person savedPerson = personService.findById(pId);

            System.out.println("savedPerson = " + savedPerson);
            System.out.println("Operation is Done!\n");
        }catch(IllegalArgumentException e) {
            System.out.println("Error: personFindById: " + e.getMessage());
        }
    }

    public static void personFindByName(){
        try{

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a name");
            String name = scanner.nextLine();

            List<Person> personList = personService.findByName(name).stream().toList();

            personList.forEach(System.out::println);
            System.out.println("Operation is Done!\n");
        }
        catch(IllegalArgumentException e) {
            System.out.println("Error: personFindByName: " + e.getMessage());
        }
    }

    public static void personUpdate(){
        try{

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an id to change");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter a first name");
            String fName = scanner.nextLine();
            System.out.println("Enter a last name");
            String lName= scanner.nextLine();

            Person updatedPerson = personService.update(new Person(id, fName, lName));

            System.out.println("savedPerson = " + updatedPerson);
            System.out.println("Operation is Done!\n");
        }
        catch(IllegalArgumentException e) {
            System.out.println("Error: personUpdate: " + e.getMessage());
        }
    }

    public static void personDelete(){
        try{

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an id to delete");
            int id = scanner.nextInt();

            if(personService.deleteById(id)) System.out.println("Delete Success");
            else System.out.println("Delete Failure");

            System.out.println("Operation is Done!\n");
        }
        catch(IllegalArgumentException e) {
            System.out.println("Error: personDelete: " + e.getMessage());
        }
    }

}