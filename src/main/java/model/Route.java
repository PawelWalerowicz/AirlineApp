package model;

import utilities.Distance;
import utilities.JourneyTime;
import utilities.Price;

import java.util.List;

import static controllers.aerial.AerialDatabaseController.getRoutesFromDatabase;
import static utilities.createFakeData.createFakeJourneyTime;
import static utilities.createFakeData.createFakePrice;

public class Route {
    Airline airline;
    Airport sourceAirport;
    Airport destinationAirport;
    Distance distance;
    JourneyTime journeyTime;

    private final int AIRLINE_POSITION = 0;
    private final int SOURCE_AIRPORT_IATA_POSITION = 2;
    private final int DESTINATION_AIRPORT_IATA_POSITION = 4;

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
    }

    public boolean exist() {
        List<String[]> allRoutes = getRoutesFromDatabase();
        String sourceAirportIATA = sourceAirport.getIATA().toString();
        String destinationAirportIATA = destinationAirport.getIATA().toString();
        for(String[] route:allRoutes) {
            if(route[SOURCE_AIRPORT_IATA_POSITION].equals(sourceAirportIATA) && route[DESTINATION_AIRPORT_IATA_POSITION].equals(destinationAirportIATA)) {
                Airline currentAirline = new Airline(route[AIRLINE_POSITION]);
                Airport currentSourceAirport = new Airport(route[SOURCE_AIRPORT_IATA_POSITION]);
                Airport currentDestinationAirport = new Airport(route[DESTINATION_AIRPORT_IATA_POSITION]);
                Route currentRoute = new Route(currentAirline,currentSourceAirport,currentDestinationAirport);
                System.out.println(currentRoute.toString() + " Price: " + createFakePrice(currentRoute.getDistance()).toString());
            }
        }

        return true;
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
        String ending="";
        if(airline.toString().contains("irline") || airline.toString().contains("irways") || airline.toString().contains("Air")) {
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
