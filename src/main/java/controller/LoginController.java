package controller;

import model.Account;

import java.util.Scanner;

import static controller.AccountDatabaseController.getAccountFromDatabase;
import static utilities.ClearConsole.cleanConsole;
import static utilities.ClearConsole.newChapter;
import static utilities.InputValidator.*;

public class LoginController {

    public void showLoginMenu() {
        cleanConsole(); //todo: find a way to clean console after choosing correct menu, meanwhile:
        newChapter();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter following information to log into your account:");

        String email=null;
        do{
            System.out.print("Email: ");
            email = scanner.nextLine();
        } while(!isEmailValid(email));

        String password=null;
        do{
            System.out.print("Password: ");
            password = scanner.nextLine();
        } while(!isPasswordValid(password));

        Account account = getAccountFromDatabase(email,password);
        UserController userController = new UserController(account);
        userController.showUserMenu();
    }


}
