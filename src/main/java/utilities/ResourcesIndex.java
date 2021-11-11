package utilities;

public class ResourcesIndex {

    public static final String MAIN_MENU_WELCOME_MESSAGE = convertRelativePathToNonrelative("../ViewData/WelcomeMessage.csv");
    public static final String LIST_OF_MAIN_MENU_OPTIONS = convertRelativePathToNonrelative("../ViewData/MainMenuOptions.csv");
    public static final String LIST_OF_USER_MENU_OPTIONS =  convertRelativePathToNonrelative("../ViewData/LoggedInUserOptions.csv");
    public static final String LIST_OF_EDIT_ACCOUNT_MENU_OPTIONS = convertRelativePathToNonrelative("../ViewData/EditAccountOptions.csv");

    public static final String USER_DATABASE_FILE = convertRelativePathToNonrelative("../UserData/UserDatabase");

    public static final String AIRLINES_DATABASE_FILE = convertRelativePathToNonrelative("../AerialData/Airlines");
    public static final String COUNTRIES_DATABASE_FILE = convertRelativePathToNonrelative("../AerialData/Countries");
    public static final String AIRPORTS_DATABASE_FILE = convertRelativePathToNonrelative("../AerialData/Airports");
    public static final String ROUTES_DATABASE_FILE = convertRelativePathToNonrelative("../AerialData/Routes");


    private static String convertRelativePathToNonrelative(String relativePath) {
        String unreliativeFileReference="";
        try {
            unreliativeFileReference = ResourcesIndex.class.getResource(relativePath).toString();
        } catch (NullPointerException exc) {
            System.out.println(exc);
        }

        return deleteFileTagFromReference(unreliativeFileReference);
    }

    private static String deleteFileTagFromReference(String fileReference) {
        if (fileReference.startsWith("file:/")) {
            return fileReference.substring(6);
        } else return fileReference;

    }
}
