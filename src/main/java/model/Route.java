package model;

import utilities.Distance;
import utilities.JourneyTime;
import utilities.Price;

import java.util.Calendar;
import java.util.List;

import static controllers.aerial.AerialDatabaseController.getRoutesFromDatabase;
import static utilities.Distance.calculateDistance;
import static utilities.createFakeData.createFakeJourneyTime;
import static utilities.createFakeData.createFakePrice;

public class Route {
    Airline airline;
    Airport sourceAirport;
    Airport destinationAirport;
    Distance distance;
    JourneyTime journeyTime;
    Price price;


    public static final int AIRLINE_POSITION = 0;
    public static final int SOURCE_AIRPORT_IATA_POSITION = 2;
    public static final int DESTINATION_AIRPORT_IATA_POSITION = 4;

    public Route(Airline airline, Airport sourceAirport, Airport destinationAirport) {
        this.airline = airline;
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.distance = new Distance(sourceAirport.getGeolocation(), destinationAirport.getGeolocation());
        this.journeyTime = createFakeJourneyTime(distance);
    }

    public Route(Airport sourceAirport, Airport destinationAirport) {
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        addRouteData();
    }

    public boolean exist() {
        List<String[]> allRoutes = getRoutesFromDatabase();
        String sourceAirportIATA = sourceAirport.getIATA().toString();
        String destinationAirportIATA = destinationAirport.getIATA().toString();
        boolean routeExist = false;
        for (String[] route : allRoutes) {
            if (route[SOURCE_AIRPORT_IATA_POSITION].equals(sourceAirportIATA) && route[DESTINATION_AIRPORT_IATA_POSITION].equals(destinationAirportIATA)) {
                routeExist = true;
            }
        }
        return routeExist;
    }

    public void addRouteData() {
        List<String[]> allRoutes = getRoutesFromDatabase();
        String sourceAirportIATA = sourceAirport.getIATA().toString();
        String destinationAirportIATA = destinationAirport.getIATA().toString();
        for (String[] route : allRoutes) {
            if (route[SOURCE_AIRPORT_IATA_POSITION].equals(sourceAirportIATA) && route[DESTINATION_AIRPORT_IATA_POSITION].equals(destinationAirportIATA)) {
                this.airline = new Airline(route[AIRLINE_POSITION]);
                this.distance = new Distance(sourceAirport.getGeolocation(),destinationAirport.getGeolocation());
                this.price = createFakePrice(distance);
                this.journeyTime = createFakeJourneyTime(distance);
            }
        }
    }

    private void addCalendarAndPriceDetails(Calendar earliestDate, Calendar latestDate) {

    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Airport getSourceAirport() {
        return sourceAirport;
    }

    public void setSourceAirport(Airport sourceAirport) {
        this.sourceAirport = sourceAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Distance getDistance() {
        return distance;
    }


    @Override
    public String toString() {
        String ending = "";
        if (airline.toString().contains("irline") || airline.toString().contains("irways") || airline.toString().contains("Air")) {
            ending = ".";
        } else {
            ending = " airline.";
        }
        return "Route: " + sourceAirport.toString()
                + " - " + destinationAirport.toString()
                + " (" + distance.toString() + ", "
                + journeyTime.toString() + ")"
                + " with " + airline.toString() + ending;
    }
}
