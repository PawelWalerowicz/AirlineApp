package model;

import java.util.Calendar;

import static utilities.createFakeData.createFakeJourneyTime;
import static utilities.createFakeData.createFakePrice;

public class FlightFactory {

    public static Flight createFlightWithRandomParameters(Route route, Calendar departureDate) {
        Flight flight = new Flight();
        flight.setRoute(route);
        flight.setDepartureDate(departureDate);

        TimeInterval flightTime = createFakeJourneyTime(route.getDistance());
        flight.setFlightTime(flightTime);

        Calendar landingDate = calculateLandingDate(departureDate, flightTime, route);
        flight.setLandingDate(landingDate);

        Price price = createFakePrice(route.getDistance());
        flight.setPrice(price);

        return flight;
    }

    public static Flight createFlightWithSetParameters(Route route, Calendar departureDate, TimeInterval flightTime, Calendar landingDate, Price price) {
        Flight flight = new Flight();
        flight.setRoute(route);
        flight.setDepartureDate(departureDate);
        flight.setPrice(price);
        flight.setFlightTime(flightTime);
        flight.setLandingDate(landingDate);
        return flight;
    }

    private static Calendar calculateLandingDate(Calendar departureDate, TimeInterval flightTime, Route route) {
        Calendar landingDate = (Calendar) departureDate.clone();
        landingDate.add(Calendar.HOUR,flightTime.getHours());
        landingDate.add(Calendar.MINUTE,flightTime.getMinutes());
        landingDate.add(Calendar.HOUR,route.getTimeZoneDifference().getHours());
        landingDate.add(Calendar.MINUTE,route.getTimeZoneDifference().getMinutes());
        return landingDate;
    }

}
