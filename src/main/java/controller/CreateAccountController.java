package controller;

import model.Account;
import view.MainMenu;

import java.util.Scanner;

import static controller.AccountDatabaseController.saveAccountToDatabase;
import static utilities.ClearConsole.newChapter;
import static utilities.InputValidator.*;

public class CreateAccountController {
    MainMenu mm = new MainMenu();

    public void createAccount() {

        Scanner scanner = new Scanner(System.in);
        String name = null;
        do {
            System.out.print("First name: ");
            name = scanner.nextLine();  //todo: find a way to break out of creation and get back to main menu
        } while (!isNameValid(name));

        String surname = null;
        do {
            System.out.print("Surname: ");
            surname = scanner.nextLine();
        } while (!isNameValid(surname));

        String email = null;
        do {
            System.out.print("Email: ");
            email = scanner.nextLine();
        } while (!isEmailValid(email));

        String password = null;
        do {
            System.out.print("Create password: ");
            password = scanner.nextLine();
        } while (!isPasswordValid(password));

        String confirmPassword = null;
        do {
            System.out.print("Repeat password: ");
            confirmPassword = scanner.nextLine();
        } while (!passwordConfirmation(password, confirmPassword));

        Account account = new Account(name, surname, email, password);
        System.out.println("Account created!");
        saveAccountToDatabase(account);
        newChapter();

        mm.viewMainMenu();
    }

}
