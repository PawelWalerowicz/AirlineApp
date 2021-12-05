package utilities;

public class InputValidator {

    public static boolean isNameValid(String name) {
        if (name.matches("^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+$")) {
            return true;
        } else {
            System.out.println("Please write correct name.");
            return false;
        }
    }

    public static boolean isEmailValid(String email) {
        if (email.toUpperCase().matches("^\\w+@\\w+\\.\\w+$")) {
            return true;
        } else {
            System.out.println("Please write correct email address.");
            return false;
        }
    }


    public static boolean isPasswordValid(String password) {
        return true;    //todo: create valid regex for passwords
    }

    public static boolean passwordConfirmation(String password, String passwordConfirmation) {
        if (password.equals(passwordConfirmation)) {
            return true;
        } else {
            System.out.println("Passwords don't match, please repeat.");
            return false;
        }
    }

    public static boolean checkForQuit(String string) {
        return !string.equalsIgnoreCase("q");
    }


    public static boolean isDateValid(String date) {
        return date.matches("^[0-3][0-9][-\\.]{1}[0-1][0-9][-\\.]{1}\\d{4}$");
    }
}