package model;

import utilities.Distance;

import java.util.List;

import static controllers.aerial.AerialDatabaseController.getRoutesFromDatabase;

public class Route {
    Airline airline;
    Airport sourceAirport;
    Airport destinationAirport;
    Distance distance;

    private final int AIRLINE_POSITION = 0;
    private final int SOURCE_AIRPORT_POSITION = 2;
    private final int DESTINATION_AIRPORT_POSITION = 4;

    public Route(Airline airline, Airport sourceAirport, Airport destinationAirport) {
        this.airline = airline;
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.distance = new Distance(sourceAirport.getGeolocation(), destinationAirport.getGeolocation());
    }

    public boolean exist() {
        List<String[]> allRoutes = getRoutesFromDatabase();
        int counter=1;
        for(String[] route:allRoutes) {
            Airline currentAirline = new Airline(route[AIRLINE_POSITION]);
            Airport currentSourceAirport = new Airport(route[SOURCE_AIRPORT_POSITION]);
            Airport currentDestinationAirport = new Airport(route[DESTINATION_AIRPORT_POSITION]);
            Route currentRoute = new Route(currentAirline,currentSourceAirport,currentDestinationAirport);
            System.out.println(counter + " " + currentRoute.toString());
            counter++;
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
                + " (" + distance.toString() + ")"
                + " with " + airline.toString() + " airline.";

    }
}
