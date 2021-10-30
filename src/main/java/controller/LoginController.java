package controller;

import model.Account;

import java.util.Scanner;

import static utilities.ClearConsole.cleanConsole;
import static utilities.ClearConsole.newChapter;
import static utilities.InputOutputTools.readUserInput;
import static utilities.InputOutputTools.showMenuOptions;
import static utilities.InputValidator.*;

public class LoginController {

    private String LIST_OF_USER_MENU_OPTIONS = "f:\\java\\AirlineApp\\src\\main\\resources\\LoggedInUserOptions.csv";

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

        Account account = Account.getAccountFromDatabase(email,password);
        showUserMenu(account);
    }


    private void showUserMenu(Account account) {
        newChapter();
        System.out.println("Welcome again " + account.getName() + " " + account.getSurname()+". How can we help you?" );
        int amountOfOptions = showMenuOptions(LIST_OF_USER_MENU_OPTIONS);
        int option = readUserInput(amountOfOptions);
        changeViewUser(option);
    }

    private void changeViewUser(int option) {
        switch(option) {
            case 0:
                System.out.println("TODO: Edit account menu");
                break;
            case 1:
                System.out.println("TODO: View or Edit your flights menu");
                break;
            case 2:
                System.out.println("TODO: View flights menu");
                break;
            case 3:
                newChapter();
                MainMenuController mainMenuController = new MainMenuController();
                mainMenuController.showMenu();
            case 4:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Something went wrong");
                break;
        }
    }


}
