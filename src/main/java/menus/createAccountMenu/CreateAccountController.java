package menus.createAccountMenu;

import menus.editAccountMenu.EditAccountController;
import model.Account;
import menus.mainMenu.MainMenu;

import java.util.Scanner;

import static database.account.AccountDatabaseController.isInDatabase;
import static database.account.AccountDatabaseController.saveAccountToDatabase;
import static utilities.ClearConsole.cleanConsole;
import static utilities.InputValidator.*;

public class CreateAccountController {
    boolean proceed = true;
    Scanner scanner;
    private static CreateAccountController createAccountControllerInstance;

    public static CreateAccountController getInstance() {
        if(createAccountControllerInstance==null) {
            createAccountControllerInstance = new CreateAccountController();
        }
        return createAccountControllerInstance;
    }


    private CreateAccountController() {
    }

    public void createAccount() {
        scanner = new Scanner(System.in);
        String name = askForName();
        String surname = askForSurname();
        String email = askForEmail();
        String password = askForPasswordWithConfirmation();
        createAccountAndSaveToDatabase(name,surname,email,password);
        cleanConsole();
        MainMenu.getInstance().viewMenu();
    }

    private String askForName() {
        String name = null;
        if (proceed) {
            do {
                System.out.print("First name: ");
                name = scanner.nextLine();
                proceed = checkForQuit(name);
            } while (proceed && !isNameValid(name));
        }
        return name;
    }

    private String askForSurname() {
        String surname = null;
        if (proceed) {
            do {
                System.out.print("Surname: ");
                surname = scanner.nextLine();
                proceed = checkForQuit(surname);
            } while (proceed && !isNameValid(surname));
        }
        return surname;
    }

    private String askForEmail() {
        String email = null;
        if (proceed) {
            do {
                System.out.print("Email: ");
                email = scanner.nextLine();
                proceed = checkForQuit(email);
                if (isInDatabase(email)) {
                    System.out.println("Account with this email address already exists.");
                    proceed = false;
                }
            } while (proceed && !isEmailValid(email));
        }
        return email;
    }

    private String askForPasswordWithConfirmation() {
        String password = null;
        String confirmPassword;
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
        }
        return password;
    }

    private void createAccountAndSaveToDatabase(String name, String surname, String email, String password) {
        if(proceed) {
            Account account = new Account(name, surname, email, password);
            System.out.println("Account created!");
            saveAccountToDatabase(account);
        }
    }

 }
