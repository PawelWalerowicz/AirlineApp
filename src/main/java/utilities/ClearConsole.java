package utilities;

import java.io.IOException;

public class ClearConsole {
        private static String DOUBLE_LINE="\n====================================================================\n";
        public static void cleanConsole() {
//            try {
//                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

        public static void newChapter() {
            System.out.println(DOUBLE_LINE);
        }
}
