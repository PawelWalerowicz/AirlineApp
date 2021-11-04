package utilities;

import java.io.IOException;

public class ClearConsole {
        private static String DOUBLE_LINE="\n====================================================================\n";
        public static void cleanConsole() {
                //todo: create function that cleans console, meanwhile:
                System.out.println(DOUBLE_LINE);
//            try {
//                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

}
