package utilities;

import java.io.*;
import java.util.Scanner;

public class InputOutputTools {

    public static int readUserIntegerInput(int amountOfOptions) throws NumberFormatException {
        Scanner userInput = new Scanner(System.in);
        int answer = -1;
        boolean validOption;

        do {
            try {
                answer = Integer.parseInt(userInput.nextLine());
                validOption = isOptionValid(answer, amountOfOptions);
            } catch(NumberFormatException exc ) {
                System.out.println("Invalid input, please try again.");
                validOption=false;
            }
        } while (!validOption);

        return answer;
    }

    public static boolean isValidFlightOption(int answer, int amountOfFlights) {
        if (answer >= 1 && answer <= amountOfFlights) {
            return true;
        } else {
            System.out.println("Invalid input, please try again.");
            return false;
        }
    }

    public static boolean isOptionValid(int answer, int amountOfOptions) {
        if (answer >= 0 && answer <= amountOfOptions - 1) {
            return true;
        } else {
            System.out.println("Invalid input, please try again.");
            return false;
        }

    }

    public static void printTextFromFile(String path) {
        Scanner inputFile;
        try {
            inputFile = new Scanner(new FileInputStream(path));

            while (inputFile.hasNextLine()) {
                System.out.println(inputFile.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found at " + path);
        }
    }

    public static int printMenuOptions(String path) {
        Scanner inputFile;
        int amountOfOptions = 0;
        try {
            inputFile = new Scanner(new FileInputStream(path));

            while (inputFile.hasNextLine()) {
                System.out.println(inputFile.nextLine());
                amountOfOptions++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Menu options not found at " + path);
        }
        return amountOfOptions;
    }

    public static void clearFile(String path) throws IOException {
        PrintWriter cleaner = new PrintWriter(new FileWriter(path));
        cleaner.print("");
    }


    public static Scanner loadDatabaseIntoScanner(String filePath) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return scanner;
    }

    public static String capitaliseFirstLetter(String string) {

        //todo: add funcionality for multi-word names like New York
        if (!string.isEmpty()) {
            return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
        } else return string;
    }

}
