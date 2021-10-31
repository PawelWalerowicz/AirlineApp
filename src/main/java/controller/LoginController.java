package controller;

import model.Account;
import view.UserMenu;

import java.util.Scanner;

import static controller.AccountDatabaseController.getAccountFromDatabase;
import static utilities.ClearConsole.cleanConsole;
import static utilities.ClearConsole.newChapter;
import static utilities.InputValidator.*;

public class LoginController {

    public void login() {   //todo: add validator to check if account exist
        Scanner scanner = new Scanner(System.in);
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
        System.out.println("Account found.");
        UserMenu um = new UserMenu(account);
        System.out.println("User created");
        um.viewUserMenu();
    }


}
