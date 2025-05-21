package se.lexicon.Exceptions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class ExceptionHandler {
    public static void handleExceptions(Exception e, String method) {
        switch (e) {
            case SQLException ex -> System.out.println("Error: " + method + " database: " + ex.getMessage());
            case IOException ex -> System.out.println("Error: " + method + " from file: " + ex.getMessage());
            case InputMismatchException ex -> System.out.println("Error: " + method + ": " + ex.getMessage());
            default -> {
                System.out.println("Unexpected error occurred: ");
                e.printStackTrace();
            }
        }
    }
}
