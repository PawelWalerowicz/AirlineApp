package controller;

import model.Account;
import utilities.ResourcesIndex;
import view.EditAccountMenu;
import view.MainMenu;
import view.UserMenu;

import java.util.Scanner;

import static utilities.ClearConsole.cleanConsole;
import static utilities.InputOutputTools.readUserIntegerInput;
import static utilities.InputOutputTools.showMenuOptions;
import static utilities.InputValidator.*;
import static utilities.ResourcesIndex.*;

public class UserController {
    Account account;
    MainMenu mm = new MainMenu();
    UserMenu um;
    EditAccountMenu eam;

    public UserController(Account account) {
        this.account = account;
    }

    public void viewUserOptions() {
        int amountOfOptions = showMenuOptions(LIST_OF_USER_MENU_OPTIONS);
        try {
            int option = readUserIntegerInput(amountOfOptions);
            changeViewLoggedInUser(option);
        } catch (NumberFormatException exc) {
            System.out.println("Wrong input, please try again.\n");
            UserMenu um = new UserMenu(account);
            um.viewUserMenu();
        }
    }

    public void changeViewLoggedInUser(int option) {
        switch(option) {
            case 0:
                eam = new EditAccountMenu(account);
                eam.viewEditAccountMenu();
                break;
            case 1:
                System.out.println("TODO: View or Edit your flights menu");
                break;
            case 2:
                System.out.println("TODO: View flights menu");
                break;
            case 3:
                cleanConsole();
                mm.viewMainMenu();
            case 4:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Something went wrong.");
                break;
        }
    }


}
