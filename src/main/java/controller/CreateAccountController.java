package controller;

import model.Account;

import java.io.*;
import java.util.Scanner;

import static utilities.ClearConsole.cleanConsole;
import static utilities.ClearConsole.newChapter;
import static utilities.InputValidator.*;

public class CreateAccountController {
    public void showCreateAccountMenu() {
        cleanConsole(); //todo: find a way to clean console after choosing correct menu, meanwhile:
        newChapter();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter following information to create an account:");
        String name=null;
        do{
            System.out.print("First name: ");
            name = scanner.nextLine();
        } while(!isNameValid(name));

        String surname=null;
        do{
            System.out.print("Surname: ");
            surname = scanner.nextLine();
        } while(!isNameValid(surname));

        String email=null;
        do{
            System.out.print("Email: ");
            email = scanner.nextLine();
        } while(!isEmailValid(email));

        String password=null;
        do{
            System.out.print("Create password: ");
            password = scanner.nextLine();
        } while(!isPasswordValid(password));

        String confirmPassword=null;
        do{
            System.out.print("Repeat password: ");
            confirmPassword=scanner.nextLine();
        } while(!passwordConfirmation(password, confirmPassword));

        Account account = new Account(1,name, surname, email, password);
        System.out.println("Account created!");
        saveAccountToDatabase(account);
        newChapter();
        MainMenuController mainMenuController = new MainMenuController();
        mainMenuController.showMenu();
    }

    private void saveAccountToDatabase(Account account) {
        account.setId(getNumberOfAccountsInDatabase()+1);
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("f:\\java\\AirlineApp\\src\\main\\resources\\UserDatabase.txt",true));
            writer.println(account.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getNumberOfAccountsInDatabase() {
        Scanner scanner = null;
        int accountsInDatabase = 0;
        try {
            scanner = new Scanner(new FileInputStream("f:\\java\\AirlineApp\\src\\main\\resources\\UserDatabase.txt"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scanner.hasNextLine()) {
            scanner.nextLine();
            accountsInDatabase++;
        }
        return accountsInDatabase;
    }

}
