package controller;

import model.Account;
import view.EditAccountMenu;
import view.MainMenu;
import view.UserMenu;

import java.util.Scanner;

import static utilities.ClearConsole.newChapter;
import static utilities.InputOutputTools.readUserIntegerInput;
import static utilities.InputOutputTools.showMenuOptions;
import static utilities.InputValidator.*;
import static utilities.ResourcesIndex.LIST_OF_EDIT_ACCOUNT_MENU_OPTIONS;

public class UserController {
    Account account;
    MainMenu mm = new MainMenu();
    UserMenu um;
    EditAccountMenu eam;

    public UserController(Account account) {
        this.account = account;
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
                newChapter();
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
