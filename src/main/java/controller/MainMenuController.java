package controller;

import static utilities.InputOutputTools.readUserInput;
import static utilities.InputOutputTools.showMenuOptions;


public class MainMenuController {
        private String LIST_OF_MENU_OPTIONS = "f:\\java\\AirlineApp\\src\\main\\resources\\MainMenuOptions.csv";

        public void showMenu() {
        System.out.println("Welcome to AirAir reservation service.");
        System.out.println("Please choose one of the following options:");
        int amountOfOptions = showMenuOptions(LIST_OF_MENU_OPTIONS);
        int option = readUserInput(amountOfOptions);
        changeViewMainMenu(option);
    }


    private void changeViewMainMenu(int option) {
        switch(option) {
            case 0:
                CreateAccountController createAccountController = new CreateAccountController();
                createAccountController.showCreateAccountMenu();
                break;
            case 1:
                LoginController loginController = new LoginController();
                loginController.showLoginMenu();
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
