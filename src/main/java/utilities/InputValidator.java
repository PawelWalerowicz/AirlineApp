package utilities;

public class InputValidator {

    public static boolean isNameValid(String name) {
        if(name.matches("^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]+$")) {
            return true;
        } else {
            System.out.println("Please write correct name.");
            return false;
        }
    }

    public static boolean isEmailValid(String email) {
        if(email.toUpperCase().matches("^\\w+@\\w+\\.\\w+$")) {
            return true;
        } else {
            System.out.println("Please write correct email address.");
            return false;
        }
    }


    public static boolean isAirportOrCountryValid(String name) {
        return true;  //todo: create valid regex for Airport or Country Name or IATA
//        if(name.matches("[^\\s]")) {
//            return true;
//        } else {
//            System.out.println("Please write correct Airport or Country name/code.");
//            return false;
//        }
    }


    public static boolean isPasswordValid(String password) {
        return true;    //todo: create valid regex for passwords
    }

    public static boolean passwordConfirmation(String password, String passwordConfirmation) {
        if(password.equals(passwordConfirmation)) {
            return true;
        } else {
            System.out.println("Passwords don't match, please repeat.");
            return false;
        }
    }

    public static boolean checkForQuit(String string) {
        return !string.toLowerCase().equals("q");
    }

}