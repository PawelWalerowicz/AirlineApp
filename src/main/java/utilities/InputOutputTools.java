package utilities;

import java.io.*;
import java.util.Scanner;

public class InputOutputTools {

    public static int readUserIntegerInput(int amountOfOptions) throws NumberFormatException{
        Scanner userInput = new Scanner(System.in);
        int answer;
        boolean validOption;
        do {
                answer = Integer.parseInt(userInput.nextLine());
                validOption = isOptionValid(answer, amountOfOptions);
        } while(!validOption);

        return answer;
    }

    private static boolean isOptionValid(int answer, int amountOfOptions) {
        if(answer>=0 && answer<=amountOfOptions-1) {
            return true;
        } else {
            System.out.println("Invalid input, please try again.");
            return false;
        }

    }

    public static void printTextFromFile(String path) {
        Scanner inputFile = null;
        try {
            inputFile = new Scanner(new FileInputStream(path)); //todo: find a way to load file with relative path
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        while(inputFile.hasNextLine()) {
            System.out.println(inputFile.nextLine());
        }
    }

    public static int printMenuOptions(String path) {
        Scanner inputFile = null;
        int amountOfOptions=0;
        try {
            inputFile = new Scanner(new FileInputStream(path)); //todo: find a way to load file with relative path
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        while(inputFile.hasNextLine()) {
            System.out.println(inputFile.nextLine());
            amountOfOptions++;
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

}
