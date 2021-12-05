package utilities;

import model.Route;

import java.time.Duration;
import java.util.*;

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
        return 5 - unRoundedMinutes % 5;
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

    public static int createProbableAmountOfFlightsInTimePeriod(Calendar earliestDate, Calendar latestDate) {
        int daysAvailable = (int) Duration.between(earliestDate.toInstant(), latestDate.toInstant()).toDays();
        if (daysAvailable < 3) {
            return (int) randomLongInRange(1, 2);
        } else if (daysAvailable < 7) {
            return (int) randomLongInRange(2, 5);
        } else if (daysAvailable < 10) {
            return (int) randomLongInRange(3, 8);
        } else if (daysAvailable < 30) {
            return (int) randomLongInRange(8, 12);
        } else if (daysAvailable < 90) {
            return (int) randomLongInRange(10, 30);
        } else {
            return (int) randomLongInRange(20, 100);
        }
    }

    public static Route chooseRandomRoute(List<Route> availableRoutes) {
        int randomIndex = (int) randomLongInRange(0,availableRoutes.size());
        return availableRoutes.get(randomIndex);
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
