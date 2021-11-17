package utilities;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class createFakeData {
    public static void createFakeDates(int amount, Calendar startingDate, Calendar endingDate) {
        //todo: method that create fake dates and hours of departure in set time period. Amount of dates to create set in input
    }
    //TODO: non linear price (lower multiplier for higher distance; try sin from 0 to 90deg
    public static Price createFakePrice(Distance distance) {
        return new Price(distance.getDistance() * randomNumberInRange(0.3,0.6));
    }


    public static JourneyTime createFakeJourneyTime(Distance distance) {
        int averageSpeed = 500;
        return new JourneyTime(distance.getDistance() / 500 * randomNumberInRange(0.85,1.15));
    }

    public static double randomNumberInRange(double rangeMin, double rangeMax) {
        Random random = new Random();
        return rangeMin + (rangeMax-rangeMin)*random.nextDouble();
    }


}
