package utilities;

import java.util.Date;
import java.util.Random;

public class createFakeData {
    public static void createFakeDates(int amount, Date startingDate, Date endingDate) {
        //todo: method that create fake dates and hours of departure in set time period. Amount of dates to create set in input
    }

    public static double createFakePrice(double distance) {
        return distance * randomNumberInRange(0.8,2);
    }

    public static double randomNumberInRange(double rangeMin, double rangeMax) {
        Random random = new Random();
        return rangeMin + (rangeMax-rangeMin)*random.nextDouble();
    }

}
