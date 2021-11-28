package utilities;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class createFakeData {

    public static Calendar createFakeDate(Calendar earliestDate, Calendar latestDate) {
        Calendar fakeDate = new GregorianCalendar();
        long earliestDateMiliseconds = earliestDate.getTime().getTime();
        long latestDateMiliseconds = latestDate.getTime().getTime();
        long randomDateMiliseconds = randomLongInRange(earliestDateMiliseconds, latestDateMiliseconds);
        fakeDate.setTime(new Date(randomDateMiliseconds));
        int unRoundedMintes = fakeDate.get(Calendar.MINUTE);
        fakeDate.add(Calendar.MINUTE, additionalMinutesToRoundUpToFive(unRoundedMintes));
        return fakeDate;
    }

    private static int additionalMinutesToRoundUpToFive(int unRoundedMinutes) {
        return 5-unRoundedMinutes%5;
    }

    public static Price createFakePrice(Distance distance) {
        double rangeMin;
        double rangeMax;
        double distanceInKm = distance.getDistance();
        if (distanceInKm < 800) {
            rangeMin = 0.3;
            rangeMax = 0.6;
        } else if (distanceInKm >= 800 && distanceInKm < 1500) {
            rangeMin = 0.15;
            rangeMax = 0.4;
        } else {
            rangeMin = 0.05;
            rangeMax = 0.3;
        }

        return new Price(distance.getDistance() * randomDoubleInRange(rangeMin, rangeMax));
    }


    public static TimeInterval createFakeJourneyTime(Distance distance) {
        int averageSpeed = 500;
        double hoursMinutesDecimal = distance.getDistance() / 500 * randomDoubleInRange(0.85, 1.15);
        return new TimeInterval(hoursMinutesDecimal);
    }

    public static double randomDoubleInRange(double rangeMin, double rangeMax) {
        Random random = new Random();
        return rangeMin + (rangeMax - rangeMin) * random.nextDouble();
    }

    public static long randomLongInRange(double rangeMin, double rangeMax) {
        Random random = new Random();
        long answer;
        answer = (long) (rangeMin + (rangeMax - rangeMin) * random.nextDouble());
        return answer;
    }


}
