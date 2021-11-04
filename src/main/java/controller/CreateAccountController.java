package controller;

import model.Account;
import view.MainMenu;

import java.util.Scanner;

import static controller.AccountDatabaseController.isInDatabase;
import static controller.AccountDatabaseController.saveAccountToDatabase;
import static utilities.ClearConsole.cleanConsole;
import static utilities.InputValidator.*;

public class CreateAccountController {
    MainMenu mm = new MainMenu();

    public void createAccount() {

        Scanner scanner = new Scanner(System.in);
        String name = null;
        String surname = null;
        String email = null;
        String password = null;
        String confirmPassword = null;

        boolean proceed = true;
        do {
            System.out.print("First name: ");
            name = scanner.nextLine();
            proceed = checkForQuit(name);
        } while (proceed && !isNameValid(name));

        if (proceed) {

            do {
                System.out.print("Surname: ");
                surname = scanner.nextLine();
                proceed = checkForQuit(surname);
            } while (proceed && !isNameValid(surname));
        }

        if (proceed) {

            do {
                System.out.print("Email: ");
                email = scanner.nextLine();
                proceed = checkForQuit(email);
                if (isInDatabase(email.toLowerCase())) {
                    System.out.println("Account with this email address already exists.");
                    proceed = false;
                }
            } while (proceed && !isEmailValid(email));
        }

        if (proceed) {

            do {
                System.out.print("Create password: ");
                password = scanner.nextLine();
                proceed = checkForQuit(password);
            } while (proceed && !isPasswordValid(password));
        }
        if (proceed) {
            do {
                System.out.print("Repeat password: ");
                confirmPassword = scanner.nextLine();
                proceed = checkForQuit(confirmPassword);
            } while (proceed && !passwordConfirmation(password, confirmPassword));

            Account account = new Account(name, surname, email, password);
            System.out.println("Account created!");
            saveAccountToDatabase(account);
        }

        cleanConsole();
        mm.viewMainMenu();
    }

}
