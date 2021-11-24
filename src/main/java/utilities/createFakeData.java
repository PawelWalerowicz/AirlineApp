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
        long randomDateMiliseconds = randomLongInRange(earliestDateMiliseconds,latestDateMiliseconds);
        fakeDate.setTime(new Date(randomDateMiliseconds));
        return fakeDate;
    }

    public static Price createFakePrice(Distance distance) {
        double rangeMin;
        double rangeMax;
        double distanceInKm=distance.getDistance();
        if(distanceInKm<800) {
            rangeMin=0.3;
            rangeMax=0.6;
        } else if(distanceInKm>=800 && distanceInKm<1500) {
            rangeMin=0.15;
            rangeMax=0.4;
        } else {
            rangeMin=0.05;
            rangeMax=0.3;
        }

        return new Price(distance.getDistance() * randomDoubleInRange(rangeMin,rangeMax));
    }


    public static JourneyTime createFakeJourneyTime(Distance distance) {
        int averageSpeed = 500;
        return new JourneyTime(distance.getDistance() / 500 * randomDoubleInRange(0.85,1.15));
    }

    public static double randomDoubleInRange(double rangeMin, double rangeMax) {
        Random random = new Random();
        return rangeMin + (rangeMax-rangeMin)*random.nextDouble();
    }

    public static long randomLongInRange(double rangeMin, double rangeMax) {
        Random random = new Random();
        long answer;
        answer = (long) (rangeMin + (rangeMax-rangeMin)*random.nextDouble());
        return answer;
    }


}
