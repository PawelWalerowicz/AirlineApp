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
    Price price;
    JourneyTime journeyTime;

    private final int AIRLINE_POSITION = 0;
    private final int SOURCE_AIRPORT_IATA_POSITION = 2;
    private final int DESTINATION_AIRPORT_IATA_POSITION = 4;

    public Route(Airline airline, Airport sourceAirport, Airport destinationAirport) {
        this.airline = airline;
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.distance = new Distance(sourceAirport.getGeolocation(), destinationAirport.getGeolocation());
        this.price = createFakePrice(distance);
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
                System.out.println("Connection found!");
                Airline currentAirline = new Airline(route[AIRLINE_POSITION]);
                Airport currentSourceAirport = new Airport(route[SOURCE_AIRPORT_IATA_POSITION]);
                Airport currentDestinationAirport = new Airport(route[DESTINATION_AIRPORT_IATA_POSITION]);

                Route currentRoute = new Route(currentAirline,currentSourceAirport,currentDestinationAirport);
                System.out.println(currentRoute.toString());
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

    @Override
    public String toString() {
        return "Route: " + sourceAirport.toString()
                + " - " + destinationAirport.toString()
                + " (" + distance.toString() + ", "
                + journeyTime.toString() + ")"
                + " with " + airline.toString() + " airline. The price is "
                + price.toString() + ".";

    }
}
