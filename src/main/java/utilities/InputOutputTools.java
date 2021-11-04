package utilities;

import java.io.*;
import java.util.Scanner;

public class InputOutputTools {

    public static int readUserIntegerInput(int amountOfOptions) throws NumberFormatException{
        Scanner userInput = new Scanner(System.in);
        int answer = 0;
        boolean validOption=false;
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

    public static int showMenuOptions(String path) {
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

}
