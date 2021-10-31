package controller;

import view.CreateAccountMenu;
import view.LoginMenu;

import static utilities.InputOutputTools.readUserIntegerInput;
import static utilities.InputOutputTools.showMenuOptions;


public class MainMenuController {

    public void changeViewMainMenu(int option) {

        switch(option) {
            case 0:
                CreateAccountMenu cam = new CreateAccountMenu();
                cam.viewCreateAccountMenu();
                break;
            case 1:
                LoginMenu lm = new LoginMenu();
                lm.viewLoginMenu();
                break;
            case 2:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Something went wrong");
                break;
        }
    }


}
